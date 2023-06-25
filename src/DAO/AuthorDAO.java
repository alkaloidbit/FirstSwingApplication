package DAO;

import Models.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO extends DAO<Author> {

    @Override
    public Author find(int id) {
        Author author = new Author();
        try {
            ResultSet result = this.connect
                                    .createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                                            ResultSet.CONCUR_UPDATABLE
                                    )
                    .executeQuery("SELECT * FROM author WHERE id_author = " + id);

            while(result.next()){
                author = new Author(
                        id,
                        result.getString("first_name"),
                        result.getString("last_name")
                );
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return author;
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
                author.setFirst_name(results.getString("first_name"));
                author.setLast_name(results.getString("last_name"));
                author.setId(results.getInt("id_author"));
                authors.add(author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Author>) authors;
    }

    @Override
    public Author create(Author obj) {
        try {
            PreparedStatement prepare = this.connect
                    .prepareStatement(
                            "INSERT INTO author (first_name, last_name) VALUES(?, ?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
            prepare.setString(1, obj.getLast_name());
            prepare.setString(2, obj.getFirst_name());

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
    public Author update(Author obj) {
        try {
            this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                            "UPDATE author SET first_name = '"+ obj.getFirst_name() +"', " +
                                              " last_name = '" + obj.getLast_name() + "' "+
                                                " Where id_author = " + obj.getId() + ""
                    );
            obj = this.find(obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Author obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                ).executeUpdate(
                    "DELETE FROM author WHERE id_author = " + obj.getId()
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
