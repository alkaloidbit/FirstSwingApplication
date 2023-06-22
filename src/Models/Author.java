package Models;

public class Author {
    private int id_author;
    private String last_name;
    private String first_name;


    public Author() {
    }

    public Author(int id_author, String last_name, String first_name) {
        this.id_author = id_author;
        this.last_name = last_name;
        this.first_name = first_name;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String toString() {
        return first_name + " " + last_name;
    }
}
