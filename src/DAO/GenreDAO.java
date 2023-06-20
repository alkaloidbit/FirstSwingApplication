package DAO;

import Models.Genre;
import Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO extends DAO<Genre> {
    @Override
    public Genre find(long id) {

        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM genre WHERE id_genre = '" + String.valueOf(id) + "'"
                    );
            while ( results.next() ) {
                Genre genre = new Genre();
                genre.setId_genre(results.getInt("id_genre"));
                genre.setName(results.getString("name"));
                return genre;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM genre"
                    );
            while ( results.next() ) {
                Genre genre = new Genre();
                genre.setId_genre(results.getInt("id_genre"));
                genre.setName(results.getString("name"));
                genres.add(genre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Genre>) genres;
    }

    @Override
    public int create(Genre genre) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("INSERT INTO genre (name) VALUES(?)");

            prepare.setString(1, genre.getName());
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Genre genre) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("UPDATE genre SET name = ? WHERE id_genre = ?");

            prepare.setString(1, genre.getName());
            prepare.setInt(2, genre.getId_genre());
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(Genre genre) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("DELETE FROM genre WHERE id_genre = ?");

            prepare.setInt(1, genre.getId_genre());
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
