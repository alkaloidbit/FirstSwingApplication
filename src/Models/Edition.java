package Models;

public class Edition {

    private int id_edition;
    private String name;

    public Edition() {
        this.id_edition = 0;
        this.name = "";
    }
    public Edition(int id_edition, String name) {
        this.id_edition = id_edition;
        this.name = name;
    }

    public int getId_edition() {
        return id_edition;
    }

    public void setId_edition(int id_edition) {
        this.id_edition = id_edition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
