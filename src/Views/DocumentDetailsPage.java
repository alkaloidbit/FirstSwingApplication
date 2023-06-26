package Views;

import Models.Author;
import Models.Document;

import javax.swing.*;
import java.awt.*;

public class DocumentDetailsPage extends JFrame {

    private JPanel panelOut;
    private JLabel lblTitle;
    private JLabel lblAuthor;
    private JLabel lblYear;
    private JLabel lblYearValue;
    private JLabel lblAuthorValue;
    private JLabel lblTitleValue;
    private JLabel lblNbPages;
    private JLabel lblNbPagesValue;
    private JLabel lblEdition;
    private JLabel lblEditionValue;
    private JLabel lblGenreValue;
    private JLabel lblGenre;

    public DocumentDetailsPage(Document doc) throws HeadlessException {
        lblNbPagesValue.setText(String.valueOf(doc.getPages_nbr()));
        lblTitleValue.setText(doc.getTitle());
        lblYearValue.setText(String.valueOf(doc.getYear()));
        String authors = "";
        for (Author author : doc.getAuthors()){
            authors += author.getFirst_name() + " " + author.getFirst_name() + " / ";
        }
        lblAuthorValue.setText(authors);
        lblEditionValue.setText(doc.getEdition().getName());
        lblGenre.setText(doc.getGenre().getName());
        setContentPane(panelOut);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
