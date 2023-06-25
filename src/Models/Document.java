package Models;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private int id;

    private String title;

    private int pages_nbr;

    private ArrayList<Author> authors;

    private Edition edition;

    private Genre genre;

    private String year;

    public Document() {}
    public Document(int id, String title, Edition edition, Genre genre, int pages_nbr, String year, ArrayList<Author> authors) {
        this.id = id;
        this.title = title;
        this.edition = edition;
        this.genre = genre;
        this.pages_nbr = pages_nbr;
        this.year = year;
        this.authors = authors;
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
        return pages_nbr;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public void setPages_nbr(int pages_nbr) {
        this.pages_nbr = pages_nbr;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String toString() {
        String str =       "Id_Document :" + this.getId() + "\n";
        str +=             "Title : " + this.getTitle() + "\n";
        str +=             "pages_nbr : " + this.getPages_nbr() + "\n";
        str +=             "year " + this.getYear() + "\n";
        str +=              "Edition " + this.getEdition() + "\n";
        str +=              "Genre " + this.getGenre() + "\n";
        str +=             "Authors: ";
        ArrayList<Author> al = this.getAuthors();
            for (int i = 0; i < al.size(); i++) {
                Author auth = al.get(i);
                str += auth.getLast_name() + " " + auth.getFirst_name() + "\n";
            }
        str +=            "\n.....................................\n";

        return str;
    }

}
