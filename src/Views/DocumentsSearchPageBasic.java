package Views;

import DAO.DocumentDAO;

import Models.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DocumentsSearchPageBasic extends JFrame {
    private JLabel lblDocSearch;
    private JTextField tfDocSearch;
    private JPanel panDocList;
    private JButton btnDocSearch;
    private JTable tbDocuments;

    public DocumentsSearchPageBasic() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 1000);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setContentPane(panDocList);
        // Récupération du critère de recherche
        String SearchParam = tfDocSearch.getText();
        // Entêtes de la table d'affichage des données PB!!!
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


        // Chercher par titre d'un document
        btnDocSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >=0; i--) {
                    model.removeRow(i);
                }
                String searchParam = tfDocSearch.getText();
                ArrayList<Document> documentsResult = documentDAO.findTitleWithParam(searchParam);
                for (Document doc : documentsResult) {
                    model.addRow(new Object[]{doc.getTitle(), doc.getPages_nbr(), doc.getYear()});
                }
            }
        });

        // Chargement des champs lorsqu'une ligne est sélectionnée
        tbDocuments.addComponentListener(new ComponentAdapter() {
        });
        tbDocuments.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tbDocuments.getSelectedRow();
                Document document = documents.get(index);
                DocumentDetailsPage detail = new DocumentDetailsPage(document);


            }
        });
    }}
