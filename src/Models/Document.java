package Models;

public class Document {
    private int id;
    private String title;
    private int pages_nbr;
    private int id_edition;
    private int year;

    public Document() {
    }

    public Document(int id, String title, int pages_nbr, int id_edition, int year) {
        this.id = id;
        this.title = title;
        this.pages_nbr = pages_nbr;
        this.id_edition = id_edition;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages_nbr() {
        return this.pages_nbr;
    }

    public void setPages_nbr(int pages_nbr) {
        this.pages_nbr = pages_nbr;
    }

    public int getId_edition() {
        return id_edition;
    }

    public void setId_edition(int id_edition) {
        this.id_edition = id_edition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
