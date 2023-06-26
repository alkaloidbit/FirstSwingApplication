package DAO;

import Models.Author;
import Models.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO extends DAO<Document> {

    private EditionDAO editionDAO;

    private GenreDAO genreDAO;

    @Override
    public Document find(int id) {
        Document document = new Document();
        try {
            ResultSet result = this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE)
                    .executeQuery("SELECT d.*, e.name as edition, g.name as genre FROM document d" +
                            " LEFT JOIN edition e " +
                            " ON e.id_edition = d.id_edition " +
                            " LEFT JOIN genre g " +
                            " ON g.id_genre = d.id_genre " +
                            "  WHERE d.id_document = " + id);
            ResultSet authorslist = this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE)
                    .executeQuery("SELECT  a.* " +
                            "FROM author a INNER JOIN compose c" +
                            " on c.id_author  = a.id_author " +
                            " LEFT JOIN document d on d.id_document = c.id_document " +
                            "WHERE d.id_document = " + id);

            ArrayList<Author> al = new ArrayList<Author>();
            while (authorslist.next()) {
                Author author = new Author(
                        authorslist.getInt("id_author"),
                        authorslist.getString("first_name"),
                        authorslist.getString("last_name"));
                al.add(author);
            }

            editionDAO = new EditionDAO();
            genreDAO = new GenreDAO();

            if (result.first())
                document = new Document(
                        id,
                        result.getString("title"),
                        editionDAO.find(result.getInt("id_edition")),
                        genreDAO.find(result.getInt("id_genre")),
                        result.getInt("pages_nbr"),
                        result.getString("year"),
                        al);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return document;
    }

    @Override
    public ArrayList<Document> findAll() {
      ArrayList<Document> documents = new ArrayList<>();
        try {
            ResultSet results = this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery("SELECT id_document FROM document");

            while(results.next()) {
                Document document = this.find(results.getInt("id_document"));
                documents.add(document);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

    public ArrayList<Document> findTitleWithParam(String searchParam) {
      ArrayList<Document> documents = new ArrayList<>();
        try {
            ResultSet results = this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery(
                "SELECT id_document FROM document WHERE title LIKE '%" + searchParam + "%' "
            );

            while(results.next()) {
                Document document = this.find(results.getInt("id_document"));
                documents.add(document);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return documents;
    }

    @Override
    public Document create(Document obj) {
        try {
            if (obj.getGenre().getId() == 0) {
                GenreDAO genreDAO = new GenreDAO();
                obj.setGenre(genreDAO.create(obj.getGenre()));
            }

            if (obj.getEdition().getId() == 0) {
                EditionDAO editionDAO = new EditionDAO();
                obj.setEdition(editionDAO.create(obj.getEdition()));
            }

            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO document (title, pages_nbr, id_edition, id_genre, year) VALUES(?, ?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, obj.getTitle());
            prepare.setInt(2, obj.getPages_nbr());
            prepare.setInt(3, obj.getEdition().getId());
            prepare.setInt(4, obj.getGenre().getId());
            prepare.setString(5, obj.getYear());

            prepare.executeUpdate();
            ResultSet rs = prepare.getGeneratedKeys();
            if (rs != null && rs.next()) {
                int key = rs.getInt(1);
                obj = this.find(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Document update(Document obj) {
        try {
            GenreDAO genreDAO = new GenreDAO();
            if (obj.getGenre().getId() == 0) {
                obj.setGenre(genreDAO.create(obj.getGenre()));
            }
            genreDAO.update(obj.getGenre());

            EditionDAO editionDAO = new EditionDAO();
            if (obj.getEdition().getId() == 0) {
                obj.setEdition(editionDAO.create(obj.getEdition()));
            }
            editionDAO.update(obj.getEdition());

            this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE)
                    .executeUpdate(
                            "UPDATE document SET title = '" + obj.getTitle() + "'," +
                                    " pages_nbr = '" + obj.getPages_nbr() + "', " +
                                    " id_edition = '" + obj.getEdition().getId() + "', " +
                                    " id_genre = '" + obj.getGenre().getId() + "', " +
                                    " year = '" + obj.getYear() + "'" +
                                    " Where id_document = " + obj.getId() + "");
            obj = this.find(obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Document obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                ).executeUpdate(
                    "DELETE FROM document WHERE id_document = " + obj.getId()
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
