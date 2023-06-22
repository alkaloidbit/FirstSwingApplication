package DAO;

import Models.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends DAO<Author>{
    @Override
    public Author find(long id) {
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM author WHERE id_author = '"+ String.valueOf(id) +"'"
                    );
            while ( results.next() ) {
                Author author = new Author();
                author.setId_author(results.getInt("id_author"));
                author.setLast_name(results.getString("last_name"));
                author.setFirst_name(results.getString("first_name"));
                return author;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM author"
                    );
            while ( results.next() ) {
                Author author = new Author();
                author.setId_author(results.getInt("id_author"));
                author.setLast_name(results.getString("last_name"));
                author.setFirst_name(results.getString("first_name"));
                authors.add(author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Author>) authors;
    }

    @Override
    public int create(Author author) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("INSERT INTO author (last_name, first_name) VALUES(?, ?)");

            prepare.setString(1, author.getLast_name());
            prepare.setString(2, author.getFirst_name());
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Author author) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("UPDATE author SET last_name = ?, first_name = ? WHERE id_author = ?");

            prepare.setString(2, author.getLast_name());
            prepare.setString(2, author.getFirst_name());
            prepare.setInt(6, author.getId_author());
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(Author author) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("DELETE FROM author WHERE id_author = ?");

            prepare.setInt(1, author.getId_author());
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
