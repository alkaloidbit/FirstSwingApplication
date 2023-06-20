package DAO;

import Models.Edition;
import Models.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditionDAO extends DAO<Edition> {
    @Override
    public Edition find(long id) {
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM edition WHERE id_edition = '" + String.valueOf(id) + "'"
                    );
            while ( results.next() ) {
                Edition edition = new Edition();
                edition.setId_edition(results.getInt("id_edition"));
                edition.setName(results.getString("name"));
                return edition;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Edition> findAll() {
        List<Edition> editions = new ArrayList<>();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM edition"
                    );
            while ( results.next() ) {
                Edition edition = new Edition();
                edition.setId_edition(results.getInt("id_edition"));
                edition.setName(results.getString("name"));
                editions.add(edition);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Edition>) editions;
    }

    @Override
    public int create(Edition edition) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("INSERT INTO edition (name) VALUES(?)");

            prepare.setString(1, edition.getName());
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Edition edition) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("UPDATE edition SET name = ? WHERE id_edition = ?");

            prepare.setString(1, edition.getName());
            prepare.setInt(2, edition.getId_edition());
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(Edition edition) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("DELETE FROM edition WHERE id_edition = ?");

            prepare.setInt(1, edition.getId_edition());
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
