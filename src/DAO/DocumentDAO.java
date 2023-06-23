package DAO;

import Models.Document;
import Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO extends DAO<Document> {
    @Override
    public Document find(long id) {

        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM document WHERE id_document = '"+ String.valueOf(id) + "'"
                    );
            while ( results.next() ) {
                Document document = new Document();
                document.setId_document(results.getInt("id_document"));
                document.setTitle(results.getString("title"));
                document.setPages_nbr(results.getInt("pages_nbr"));
                document.setId_edition(results.getInt("id_edition"));
                document.setYear(results.getInt("year"));
                return document;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Document> findAll() {

        List<Document> documents = new ArrayList<>();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM document"
                    );
            while ( results.next() ) {
                Document document = new Document();
                document.setId_document(results.getInt("id_document"));
                document.setTitle(results.getString("title"));
                document.setPages_nbr(results.getInt("pages_nbr"));
                document.setId_edition(results.getInt("id_edition"));
                document.setYear(results.getInt("year"));
                documents.add(document);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Document>) documents;
    }

    public ArrayList<Document> findTitleWithParam(String searchParam) {
        List<Document> documents = new ArrayList<>();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM document WHERE title LIKE \"%" + searchParam + "%\""
                    );
            while ( results.next() ) {
                Document document = new Document();
                document.setId_document(results.getInt("id_document"));
                document.setTitle(results.getString("title"));
                document.setPages_nbr(results.getInt("pages_nbr"));
                document.setId_edition(results.getInt("id_edition"));
                document.setYear(results.getInt("year"));
                documents.add(document);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Document>) documents;
    }


    @Override
    public int create(Document document) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("INSERT INTO document (title, pages_nbr,id_edition,year) VALUES(?, ?, ?, ?)");

            prepare.setString(1, document.getTitle());
            prepare.setInt(2, document.getPages_nbr());
            prepare.setInt(3, document.getId_edition());
            prepare.setInt(4, document.getYear());
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Document document) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("UPDATE document SET title = ?, pages_nbr = ?, id_edition = ?, year = ? WHERE id_document = ?");

            prepare.setString(1, document.getTitle());
            prepare.setInt(2, document.getPages_nbr());
            prepare.setInt(3, document.getId_edition());
            prepare.setInt(4, document.getYear());
            prepare.setInt(5, document.getId_document());
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(Document document) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("DELETE FROM document WHERE id_document = ?");

            prepare.setInt(1, document.getId_document());
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
