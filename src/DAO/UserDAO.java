package DAO;

import Models.Document;
import Models.User;
import Models.PasswordHasher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends DAO<User> {
    PasswordHasher passwordHasher;
    byte[] salt;
    public UserDAO () {
       this.passwordHasher = new PasswordHasher();
       this.salt = "monsel".getBytes();
    }

    @Override
    public User find(long id) {
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM user WHERE id_user = '" + String.valueOf(id) + "'"
                    );
            while ( results.next() ) {
                User user = new User();
                user.setFirst_name(results.getString("first_name"));
                user.setLast_name(results.getString("last_name"));
                user.setId_user(results.getInt("id_user"));
                user.setEmail(results.getString("email"));
                user.setIs_admin(results.getInt("is_admin"));
                user.setPassword(results.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public User findByEmail(String userEmail) {
        try {


            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM user WHERE email = '" + userEmail + "'"
                    );
            while ( results.next() ) {
                User user = new User();
                user.setFirst_name(results.getString("first_name"));
                user.setLast_name(results.getString("last_name"));
                user.setId_user(results.getInt("id_user"));
                user.setEmail(results.getString("email"));
                user.setIs_admin(results.getInt("is_admin"));
                user.setPassword(results.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Email inconnu");
        }

        return null;
    }
    public boolean checkValidUser(String userEmail, String userPassword) {
        User userInDB = findByEmail(userEmail);
        String hashedPassword = this.passwordHasher.hashPassword(userPassword, this.salt);

        if(userInDB != null && userInDB.getPassword().equals(hashedPassword)){
            return true;
        }
        return false;
    }
    public ArrayList<User> findNameWithParam(String searchParam) {
        List<User> users = new ArrayList<>();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM user WHERE first_name LIKE '%" + searchParam + "%' OR last_name LIKE '%" + searchParam +"%'" 
                    );
            while ( results.next() ) {
                User user = new User();
                user.setId_user(results.getInt("id_user"));
                user.setLast_name(results.getString("last_name"));
                user.setFirst_name(results.getString("first_name"));
                user.setIs_admin(results.getInt("is_admin"));
                user.setPassword(results.getString("password"));
                user.setEmail(results.getString("email"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<User>) users;
    }
    @Override
    public ArrayList<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            ResultSet results = connect
                    .createStatement()
                    .executeQuery(
                            "SELECT * FROM user"
                    );
            while ( results.next() ) {
                User user = new User();
                user.setFirst_name(results.getString("first_name"));
                user.setLast_name(results.getString("last_name"));
                user.setId_user(results.getInt("id_user"));
                user.setEmail(results.getString("email"));
                user.setIs_admin(results.getInt("is_admin"));
                user.setPassword(results.getString("password"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<User>) users;
    }

    @Override
    public int create(User user) {
        String hashedPassword = this.passwordHasher.hashPassword(user.getPassword(), this.salt);
        try {
            PreparedStatement prepare = connect
                .prepareStatement("INSERT INTO user (is_admin, first_name,last_name,email,password) VALUES(?, ?, ?, ?, ?)");

            prepare.setInt(1, user.getIs_admin());
            prepare.setString(2, user.getFirst_name());
            prepare.setString(3, user.getLast_name());
            prepare.setString(4, user.getEmail());
            prepare.setString(5, hashedPassword);
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(User user) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("UPDATE user SET is_admin = ?, first_name = ?,last_name = ?,email = ?,password = ? WHERE id_user = ?");

            prepare.setInt(1, user.getIs_admin());
            prepare.setString(2, user.getFirst_name());
            prepare.setString(3, user.getLast_name());
            prepare.setString(4, user.getEmail());
            prepare.setString(5, user.getPassword());
            prepare.setInt(6, user.getId_user());
            return prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(User user) {
        try {
            PreparedStatement prepare = connect
                    .prepareStatement("DELETE FROM user WHERE id_user = ?");

            prepare.setInt(1, user.getId_user());
            prepare.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
