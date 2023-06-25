package Models;

import java.util.ArrayList;

public class Document {
    private int id_document;
    private String title;
    private int pages_nbr;
    private Edition edition;
    private int year;
    private ArrayList<Author> authors;
    public Document() {
    }

    public Document(int id_document, String title, int pages_nbr, Edition edition, int year) {
        this.id_document = id_document;
        this.title = title;
        this.pages_nbr = pages_nbr;
        this.edition = edition;
        this.year = year;
    }

    public int getId_document() {
        return id_document;
    }

    public void setId_document(int id_document) {
        this.id_document = id_document;
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

    public Edition getEdition() {
        return this.edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }
}
