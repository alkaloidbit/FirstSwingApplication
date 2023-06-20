package Models;

public class Author {
    private int id_author;
    private String name;
    private String surname;


    public Author() {
    }

    public Author(int id_author, String name, String surname) {
        this.id_author = id_author;
        this.name = name;
        this.surname = surname;
    }

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
