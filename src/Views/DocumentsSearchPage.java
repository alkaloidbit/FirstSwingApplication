package Views;

import DAO.AuthorDAO;
import DAO.DocumentDAO;
import DAO.EditionDAO;
import Models.Author;
import Models.Document;
import Models.Edition;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DocumentsSearchPage extends JFrame {
    private JLabel lblDocSearch;
    private JTextField tfDocSearch;
    private JPanel panDocList;
    private JButton btnDocSearch;
    private JTable tbDocuments;
    private JTextField tfTitle;
    private JTextField tfDocNbPages;
    private JTextField tfYear;
    private JButton btnDocCreate;
    private JButton btnDocUpdate;
    private JButton btnDocDel;
    private JButton btnDocClear;
    private JLabel lblDocId;
    private JLabel lblDocIdValue;
    private JLabel lblDocTitle;
    private JLabel lblDocNbPages;
    private JLabel lblDocEdition;
    private JLabel lblDocYear;
    private JLabel lblInfo;
    private JComboBox<Edition> cbEdtion;
    private JLabel lblAuteur;
    private JComboBox cbAuteur;
    private JPanel pAuthor;
    private JTextField tfAuthor;
    private JButton btnAuthor;
    private JPanel pEdition;
    private JTextField tfEdition;
    private JButton btnEdition;

    public DocumentsSearchPage(){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 1000);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setContentPane(panDocList);
        // Récupération du critère de recherche
        String SearchParam = tfDocSearch.getText();
        // Entêtes de la table d'affichage des données
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Titre");
        model.addColumn("Nb de pages");
        model.addColumn("Année");
        tbDocuments.setModel(model);

        // Recherche dans la BDD
        DocumentDAO documentDAO = new DocumentDAO();
        // Récupération de l'ensemble des documents pour affichage dans la jtable
        ArrayList<Document> documents = documentDAO.findAll();
        for(Document doc :documents){
            model.addRow(new Object[]{doc.getTitle(), doc.getPages_nbr(), doc.getYear()});
        }

        // Récupération des éditions pour le comboBox
        EditionDAO editionDAO = new EditionDAO();
        ArrayList<Edition> editionsList = editionDAO.findAll();
        // Injection dans le combobox
        for(Edition ed: editionsList){
            cbEdtion.addItem(ed);
        }
        // Récupération des auteurs pour le comboBox
        AuthorDAO authorDAO = new AuthorDAO();
        ArrayList<Author> authorList = authorDAO.findAll();
        // Injection dans le combobox
        for(Author aut: authorList){
            cbAuteur.addItem(aut);
        }


        btnDocSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                String searchParam = tfDocSearch.getText();
                ArrayList<Document> documentsResult = documentDAO.findTitleWithParam(searchParam);
                for(Document doc :documentsResult){
                    model.addRow(new Object[]{doc.getTitle(), doc.getPages_nbr(), doc.getYear()});
                }
            }
        });


        btnDocCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = getTextFieldsValues();
                if(document != null){
                    DocumentDAO documentDAO1 = new DocumentDAO();
                    documentDAO1.create(document);
                }
                else{
                    lblInfo.setText("Informations incomplètes");
                }
            }
        });

        btnDocUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = getTextFieldsValues();
                if(document == null){
                    lblInfo.setText("Informations incomplètes");
                }
                else if(document.getId_document()== 0){
                    lblInfo.setText("L'id n'est pas renseigné");
                }
                else{
                    DocumentDAO documentDAO1 = new DocumentDAO();
                    documentDAO1.update(document);
                }
            }
        });


        btnDocDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = getTextFieldsValues();
                if(document == null){
                    lblInfo.setText("Informations incomplètes");
                }
                else if(document.getId_document()== 0){
                    lblInfo.setText("L'id n'est pas renseigné");
                }
                else{
                    DocumentDAO documentDAO1 = new DocumentDAO();
                    documentDAO1.delete(document);
                }
            }
        });
        btnDocClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfTitle.setText("");
                tfDocNbPages.setText("");
                tfYear.setText("");
            }
        });

        btnAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Author authorView = new Author();
                String[] authorNames = tfAuthor.getText().split(" ");
                authorView.setSurname(authorNames[0]);
                authorView.setName(authorNames[1]);
                AuthorDAO authorDAO1 = new AuthorDAO();
                authorDAO1.create(authorView);
                
            }
        });
        btnEdition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edition editionView = new Edition();
                editionView.setName(tfEdition.getText());
                EditionDAO editionDAO1 = new EditionDAO();
                editionDAO1.create(editionView);

            }
        });
    }
    // Fonction qui permet de les récupérer les valeurs de champs afin de les envoyer vers la BDD
    public Document getTextFieldsValues(){
        // Création d'un objet document qui recevra les valeurs des champs documents
        Document viewDoc = new Document();

        // chargement de ses paramètres
        if(tfTitle.getText().equals("") ||tfDocNbPages.getText().equals("")||tfYear.getText().equals("") || cbEdtion.getSelectedItem().equals(""))
            return null;
        viewDoc.setId_document(Integer.parseInt(lblDocId.getText()));
        viewDoc.setTitle(tfTitle.getText());
        viewDoc.setPages_nbr(Integer.parseInt(tfDocNbPages.getText()));
        viewDoc.setYear(Integer.parseInt(tfYear.getText()));
        viewDoc.setId_edition(((Edition) cbEdtion.getSelectedItem()).getId_edition());
        return viewDoc;
    }


}
