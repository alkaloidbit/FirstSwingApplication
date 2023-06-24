package Models;

public class User {
    private int id_user;
    private int is_admin;
    private String first_name;
    private String last_name;
    private String email;
    private String password;

    public User() {
    }

    public User(int id_user, int is_admin, String first_name, String last_name, String email, String password) {
        this.id_user = id_user;
        this.is_admin = is_admin;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        String str = "Id_user: " + this.getId_user() + "\n";
        str += "NOM: " + this.getLast_name() + "\n";
        str += "PRENOM: " + this.getFirst_name() + "\n";
        str += "Mail: " + this.getEmail() + "\n";
        str += "Password: " + this.getPassword() + "\n";
        str += "is_admin" + this.getIs_admin() + "\n";
        str += "....................................";

        return str;
    }

}
