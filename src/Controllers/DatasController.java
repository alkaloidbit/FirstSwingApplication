package Controllers;
import Models.Author;
import Models.Document;
import Models.Edition;
import Models.User;
import java.util.ArrayList;

public class DatasController {
    private static ArrayList<Document> listDocuments;
    private static ArrayList<User> listUsers;

    private static ArrayList<Edition> ListEditionCombo;

    private static ArrayList<Author> ListAuthorCombo;

    public ArrayList<Document> getListDocuments() {
        return listDocuments;
    }

    public  void setListDocuments(ArrayList<Document> listDocuments) {
        DatasController.listDocuments = listDocuments;
    }

    public  ArrayList<User> getListUsers() {
        return listUsers;
    }

    public  void setListUsers(ArrayList<User> listUsers) {
        DatasController.listUsers = listUsers;
    }

    public ArrayList<Edition> getListEditionCombo() {
        return ListEditionCombo;
    }
    public void setListEditionCombo(ArrayList<Edition> listEditionCombo) {
        ListEditionCombo = listEditionCombo;
    }

    public void setListAuthorCombo(ArrayList<Author> listAuthorCombo) {
        ListAuthorCombo = listAuthorCombo;
    }
    public ArrayList<Author> getListAuthorCombo() {
        return ListAuthorCombo;
    }

    public Edition getEditionById(int id){
        for(Edition ed: DatasController.ListEditionCombo){
            if(ed.getId() == id){
                return ed;
            }
        }
        return null;
    }

    public void addEditionToCombo(Edition editionView) {
        ListEditionCombo.add(editionView);
    }

    public void addAuthorToCombo(Author authorView) {
        ListAuthorCombo.add(authorView);
    }
}
