package DAO;

import Models.User;
import Models.PasswordHasher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
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
    public User find(int id) {
        User user = new User();
        try {
            ResultSet result = this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    )
                    .executeQuery("SELECT * FROM user WHERE id_user = "+ id);
            if (result.first())
                user = new User(
                        id,
                        result.getInt("is_admin"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"),
                        result.getString("password")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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
                user.setId(results.getInt("id_user"));
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
                user.setId(results.getInt("id_user"));
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

    public User findByEmail(String userEmail) {
        try {
           ResultSet result = this.connect
                   .createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE
                   )
                   .executeQuery("SELECT * FROM user WHERE email = '" + userEmail + "'");
            if (result.first()) {
                User user = new User(
                        result.getInt("id_user"),
                        result.getInt("is_admin"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"),
                        result.getString("password")
                );
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User login(String email, String passwd) {
        User user = new User();
        String hashedPassword = this.passwordHasher.hashPassword(passwd, this.salt);
        try {
           ResultSet result = this.connect
                   .createStatement(
                           ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_UPDATABLE
                   )
                   .executeQuery("SELECT * FROM user WHERE email = '" + email + "' AND passwd = '" + hashedPassword + "'");
            if (result.first())
                user = new User(
                        result.getInt("id_user"),
                        result.getInt("is_admin"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getString("email"),
                        result.getString("passwd")
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public boolean checkValidUser(String userEmail, String userPassword) {
        User userInDB = findByEmail(userEmail);
        String hashedPassword = this.passwordHasher.hashPassword(userPassword, this.salt);

        if(userInDB != null && userInDB.getPassword().equals(hashedPassword)){
            return true;
        }
        return false;
    }

    @Override
    public User create(User obj) {
        String hashedPassword = this.passwordHasher.hashPassword(obj.getPassword(), this.salt);
        try {
            PreparedStatement prepare = connect
                .prepareStatement("INSERT INTO user (is_admin, first_name, last_name, email, password) VALUES(?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            prepare.setInt(1, obj.getIs_admin());
            prepare.setString(2, obj.getFirst_name());
            prepare.setString(3, obj.getLast_name());
            prepare.setString(4, obj.getEmail());
            prepare.setString(5, hashedPassword);
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
    public User update(User obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                ).executeUpdate(
                    "UPDATE user SET is_admin = '"+obj.getIs_admin()+"', " +
                                    " first_name = '" + obj.getFirst_name() + "', " +
                                    " last_name = '" + obj.getLast_name() + "', " +
                                    " email = '" + obj.getEmail() + "', " +
                                    " password = '" + obj.getPassword() + "'" +
                                " WHERE id_user = " + obj.getId() + ""
                );
                obj = this.find(obj.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(User obj) {
        try {
            this.connect
                .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                ).executeUpdate(
                    "DELETE FROM user WHERE id_user = " + obj.getId()
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
