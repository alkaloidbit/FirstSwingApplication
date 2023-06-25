package Models;

public class Genre {
    private int id;
    private String name;

    public Genre() {
        this.id = 0;
        this.name = "";
    }
    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId_genre(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
