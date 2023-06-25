package DAO;

import Models.Author;
import Models.Document;
import Models.Edition;
import Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO extends DAO<Document> {


    @Override
    public Document find(long id) {
        Document document = new Document();
        ArrayList<Author> authors = new ArrayList<Author>();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * " +
                                    "FROM document d " +
                                    "INNER JOIN edition e " +
                                    "ON d.id_edition = e.id_edition " +
                                    "INNER JOIN compose c " +
                                    "ON c.id_document = d.id_document " +
                                    "INNER JOIN author a " +
                                    "ON a.id_author = c.id_author " +
                                    "WHERE d.id_document = '"+ String.valueOf(id) + "'"
                    );

            while(results.next()){
                if (document.getId_document()==0){
                    document.setId_document(results.getInt("id_document"));
                    document.setTitle(results.getString("title"));
                    document.setPages_nbr(results.getInt("pages_nbr"));
                    document.setEdition(new Edition(results.getInt("e.id_edition"), results.getString("e.name")));
                    document.setYear(results.getInt("year"));
                }
                authors.add(new Author(results.getInt("a.id_author"), results.getString("a.last_name"), results.getString("a.first_name")));
            }
            document.setAuthors(authors);
            return document;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Document> findAll() {
        ArrayList<Document> documents = new ArrayList<Document>();
        ArrayList<Author> authors = new ArrayList<Author>();
        Document document = new Document();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * " +
                                    "FROM document d " +
                                    "INNER JOIN edition e " +
                                    "ON d.id_edition = e.id_edition " +
                                    "INNER JOIN compose c " +
                                    "ON c.id_document = d.id_document " +
                                    "INNER JOIN author a " +
                                    "ON a.id_author = c.id_author"
                    );

            while ( results.next() ) {
                if(results.getInt("id_document")!=document.getId_document()){
                    if (document.getId_document()!=0) {
                        document.setAuthors(authors);
                        documents.add(document);
                        authors = new ArrayList<Author>();
                        document = new Document();
                    }
                    document.setId_document(results.getInt("id_document"));
                    document.setTitle(results.getString("title"));
                    document.setPages_nbr(results.getInt("pages_nbr"));
                    document.setEdition(new Edition(results.getInt("e.id_edition"), results.getString("e.name")));
                    document.setYear(results.getInt("year"));
                }
                authors.add(new Author(results.getInt("a.id_author"), results.getString("a.last_name"), results.getString("a.first_name")));
                if(results.isLast()){
                    document.setAuthors(authors);
                    documents.add(document);
                }
            }
            return documents;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documents;
    }

    public ArrayList<Document> findTitleWithParam(String searchParam) {
        List<Document> documents = new ArrayList<Document>();
        ArrayList<Author> authors = new ArrayList<Author>();
        Document document = new Document();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * " +
                                    "FROM document d " +
                                    "INNER JOIN edition e " +
                                    "ON d.id_edition = e.id_edition " +
                                    "INNER JOIN compose c " +
                                    "ON c.id_document = d.id_document " +
                                    "INNER JOIN author a " +
                                    "ON a.id_author = c.id_author " +
                                    " WHERE d.title LIKE \"%" + searchParam + "%\""
                    );
            while ( results.next() ) {
                if(results.getInt("id_document")!=document.getId_document()){
                    if (document.getId_document()!=0) {
                        document.setAuthors(authors);
                        documents.add(document);
                        authors = new ArrayList<Author>();
                        document = new Document();
                    }
                    document.setId_document(results.getInt("id_document"));
                    document.setTitle(results.getString("title"));
                    document.setPages_nbr(results.getInt("pages_nbr"));
                    document.setEdition(new Edition(results.getInt("e.id_edition"), results.getString("e.name")));
                    document.setYear(results.getInt("year"));
                }
                authors.add(new Author(results.getInt("a.id_author"), results.getString("a.last_name"), results.getString("a.first_name")));
                if(results.isLast()){
                    document.setAuthors(authors);
                    documents.add(document);
                }
            }
            return (ArrayList<Document>) documents;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Document>) documents;
    }


    @Override
    public int create(Document document) {
        String state = "START TRANSACTION; INSERT INTO document (title,pages_nbr,id_edition,year) VALUES(?,?,?,?);";
        for(int i = 0; i< document.getAuthors().size(); i++){
            state = state + "INSERT INTO compose (id_author, id_document) VALUES(?,?);";
        }
        state = state + "COMMIT;";
        System.out.println(state);
        try {
            PreparedStatement prepare = connect
                    .prepareStatement(state);

            prepare.setString(1, document.getTitle());
            prepare.setInt(2, document.getPages_nbr());
            prepare.setInt(3, document.getEdition().getId_edition());
            prepare.setInt(4, document.getYear());
            int index = 5;
            for(int i = 0; i< document.getAuthors().size(); i++){
                prepare.setInt(index, document.getAuthors().get(i).getId_author());
                prepare.setInt(index+1, document.getId_document());
                index += 2;
            }
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Document document) {
        String state = "START TRANSACTION; UPDATE document SET title = ?, pages_nbr = ?, id_edition = ?, year = ? WHERE id_document = ?;";
        state = state + "DELETE FROM compose WHERE id_document = ?;";
        for(int i = 0; i< document.getAuthors().size(); i++){
            state = state + "INSERT INTO compose (id_author, id_document) VALUES(?,?);";
        }
        state = state + "COMMIT;";
        try {
            PreparedStatement prepare = connect
                    .prepareStatement(state);

            prepare.setString(1, document.getTitle());
            prepare.setInt(2, document.getPages_nbr());
            prepare.setInt(3, document.getEdition().getId_edition());
            prepare.setInt(4, document.getYear());
            prepare.setInt(5, document.getId_document());
            prepare.setInt(6, document.getId_document());
            int index = 7;
            for(int i = 0; i< document.getAuthors().size(); i++){
                prepare.setInt(index, document.getAuthors().get(i).getId_author());
                prepare.setInt(index+1, document.getId_document());
                index += 2;
            }
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(Document document) {
        String state = "BEGIN; DELETE FROM document WHERE id_document = ?;" +
                        "DELETE FROM compose WHERE id_document = ?;COMMIT;";
        try {
            PreparedStatement prepare = connect
                    .prepareStatement(state);

            prepare.setInt(1, document.getId_document());
            prepare.setInt(2, document.getId_document());
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
