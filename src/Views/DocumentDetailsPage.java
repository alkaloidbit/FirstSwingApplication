package Views;

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

    public DocumentDetailsPage(Document doc) throws HeadlessException {
        lblNbPagesValue.setText(String.valueOf(doc.getPages_nbr()));
        lblTitleValue.setText(doc.getTitle());
        lblYearValue.setText(String.valueOf(doc.getYear()));
        lblAuthorValue.setText("A compléter");
        lblEditionValue.setText("A compléter");
        setContentPane(panelOut);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
