package Views;

import Controllers.DatasController;
import DAO.AuthorDAO;
import DAO.DocumentDAO;
import DAO.EditionDAO;
import DAO.UserDAO;
import Models.Author;
import Models.Document;
import Models.Edition;
import Models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
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
    private JTabbedPane tpMainFrame;
    private JPanel pUser1;
    private JLabel lblUserSearch;
    private JTextField tfUserSearch;
    private JButton btnUserSearch;
    private JTable tbUsers;
    private JPanel pUser2;
    private JLabel lblUserFirstName;
    private JTextField tfUserFirstName;
    private JLabel lblUserLastName;
    private JTextField tfUserLastName;
    private JLabel lblUserId;
    private JLabel lblUserIdValue;
    private JLabel lblUserEmail;
    private JLabel lblUserPass;
    private JLabel lblUserAdmin;
    private JTextField tfEmail;
    private JTextField tfUserPass;
    private JComboBox cbAdmin;
    private JPanel pUser3;
    private JButton btnUserCreate;
    private JButton btnUserUpdate;
    private JButton btnUserClear;
    private JButton btnUserDel;
    private JPanel pDoc1;
    private JPanel pDoc2;
    private JPanel pDoc3;
    private JPanel pUser;

    public DocumentsSearchPage(){

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 1000);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setContentPane(panDocList);
        /****************************************************************************** DOCUMENTS **********************************************************************/
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
        DatasController dc = new DatasController();
        dc.setListDocuments(documents);
        for(Document doc :documents){
            model.addRow(new Object[]{doc.getTitle(), doc.getPages_nbr(), doc.getYear()});
        }

        // Récupération des éditions pour le comboBox
        EditionDAO editionDAO = new EditionDAO();
        ArrayList<Edition> editionsList = editionDAO.findAll();
        // Injection dans le combobox
        for (Edition ed : editionsList) {
            cbEdtion.addItem(ed);
        }
        // Envoi vers DataController
        DatasController dce = new DatasController();
        dce.setListEditionCombo(editionsList);

        // Récupération des auteurs pour le comboBox
        AuthorDAO authorDAO = new AuthorDAO();
        ArrayList<Author> authorList = authorDAO.findAll();
        // Injection dans le combobox
        for (Author aut : authorList) {
            cbAuteur.addItem(aut);
        }
        // Envoi vers DataController
        dce.setListAuthorCombo(authorList);

        // Chercher par titre d'un document
        btnDocSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                String searchParam = tfDocSearch.getText();
                ArrayList<Document> documentsResult = documentDAO.findTitleWithParam(searchParam);
                for (Document doc : documentsResult) {
                    model.addRow(new Object[]{doc.getTitle(), doc.getPages_nbr(), doc.getYear()});
                }
            }
        });

        // Créer document
        btnDocCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = getTextFieldsValues();
                if (document != null) {
                    DocumentDAO documentDAO1 = new DocumentDAO();
                    documentDAO1.create(document);
                } else {
                    lblInfo.setText("Informations incomplètes");
                }
                refreshListDocuments(model, documentDAO);
            }
        });

        // Mettre à jour documents
        btnDocUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = getTextFieldsValues();
                if (document == null) {
                    lblInfo.setText("Informations incomplètes");
                } else if (document.getId_document() == 0) {
                    lblInfo.setText("L'id n'est pas renseigné");
                } else {
                    DocumentDAO documentDAO1 = new DocumentDAO();
                    documentDAO1.update(document);
                }
                refreshListDocuments(model, documentDAO);
            }
        });

        // Supprimer document
        btnDocDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = getTextFieldsValues();
                if (document == null) {
                    lblInfo.setText("Informations incomplètes");
                } else if (document.getId_document() == 0) {
                    lblInfo.setText("L'id n'est pas renseigné");
                } else {
                    DocumentDAO documentDAO1 = new DocumentDAO();
                    documentDAO1.delete(document);
                }
                refreshListDocuments(model, documentDAO);
            }
        });
        // Vider les champs texte
        btnDocClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfTitle.setText("");
                tfDocNbPages.setText("");
                tfYear.setText("");
                lblDocIdValue.setText("");
            }
        });
        // Fonction de création d'auteur
        btnAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Author authorView = new Author();
                String[] authorNames = tfAuthor.getText().split(" ");
                authorView.setFirst_name(authorNames[0]);
                authorView.setLast_name(authorNames[1]);
                AuthorDAO authorDAO1 = new AuthorDAO();
                authorDAO1.create(authorView);
                // Mise à jour DataController
                DatasController dce = new DatasController();
                dce.addAuthorToCombo(authorView);
                // Actualisation du combo
                ArrayList<Author> statComAut = dce.getListAuthorCombo();
                for (int i=0; i<statComAut.size()-1; i++) {
                    cbAuteur.removeItemAt(0);
                }

                for (Author aut : dce.getListAuthorCombo()) {
                    cbAuteur.addItem(aut);
                }

            }
        });
        // Fonction de création d'edition
        btnEdition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edition editionView = new Edition();
                editionView.setName(tfEdition.getText());
                EditionDAO editionDAO1 = new EditionDAO();
                editionDAO1.create(editionView);
                // Mise à jour DataController
                DatasController dce = new DatasController();
                dce.addEditionToCombo(editionView);
                // Actualisation du combo
                ArrayList<Edition> statComEd = dce.getListEditionCombo();
                for (int i=0; i<statComEd.size()-1; i++) {
                    cbEdtion.removeItemAt(0);
                }

                for (Edition ed : dce.getListEditionCombo()) {
                    cbEdtion.addItem(ed);
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
                DatasController dt = new DatasController();
                ArrayList<Document> statDocs = dt.getListDocuments();
                Document doc = statDocs.get(index);
                tfTitle.setText(doc.getTitle());
                tfDocNbPages.setText(String.valueOf(doc.getPages_nbr()));
                tfYear.setText(String.valueOf(doc.getYear()));
                lblDocIdValue.setText(String.valueOf(doc.getId_document()));
                DatasController dc = new DatasController();
                Edition ed = dc.getEditionById(doc.getId_edition());
                cbEdtion.setSelectedItem(ed);
            }
        });

        /*********************************************************************************************** USERS *********************************************************************************/

        // Récupération du critère de recherche
        String SearchUParam = tfUserSearch.getText();
        // Entêtes de la table d'affichage des données
        DefaultTableModel modelU = new DefaultTableModel();
        modelU.addColumn("Nom");
        modelU.addColumn("Prénom");
        modelU.addColumn("Email");
        modelU.addColumn("Admin");
        tbUsers.setModel(modelU);
        // Recherche dans la BDD
        UserDAO userDAO = new UserDAO();
        // Récupération de l'ensemble des utilisateurs pour affichage dans la jtable
        ArrayList<User> users = userDAO.findAll();
        dc.setListUsers(users);
        for(User user :users){
            modelU.addRow(new Object[]{user.getLast_name(), user.getFirst_name(), user.getEmail(),user.getIs_admin()});
        }

        cbAdmin.addItem(0);
        cbAdmin.addItem(1);
        // Chercher par nom d'un utilisateur
        btnUserSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = modelU.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    modelU.removeRow(i);
                }
                String SearchUParam = tfUserSearch.getText();
                ArrayList<User> usersResult = userDAO.findNameWithParam(SearchUParam);
                for(User user :usersResult){
                    modelU.addRow(new Object[]{user.getLast_name(), user.getFirst_name(), user.getEmail(),user.getIs_admin()});
                }
            }
        });

        // Créer utilisateur
        btnUserCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = getUserTextFieldsValues();
                if(user != null){
                    UserDAO userDAO1 = new UserDAO();
                    userDAO1.create(user);
                }
                else{
                    lblInfo.setText("Informations incomplètes");
                }
                refreshListUsers( modelU,  userDAO);
            }
        });
        // Mettre à jour utilisateurs
        btnUserUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = getUserTextFieldsValues();
                if(user == null){
                    lblInfo.setText("Informations incomplètes");
                }
                else if(user.getId_user()== 0){
                    lblInfo.setText("L'id n'est pas renseigné");
                }
                else{
                    UserDAO userDAO1 = new UserDAO();
                    userDAO1.update(user);
                }
                refreshListUsers( modelU,  userDAO);
            }
        });

        // Supprimer utilisateur
        btnUserDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = getUserTextFieldsValues();
                if(user == null){
                    lblInfo.setText("Informations incomplètes");
                }
                else if(user.getId_user()== 0){
                    lblInfo.setText("L'id n'est pas renseigné");
                }
                else{
                    UserDAO userDAO1 = new UserDAO();
                    userDAO1.delete(user);
                }
                refreshListUsers( modelU,  userDAO);
            }
        });
        // Vider les champs texte
        btnUserClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfEmail.setText("");
                tfUserPass.setText("");
                tfUserFirstName.setText("");
                tfUserLastName.setText("");
                lblUserIdValue.setText("");
            }
        });

        // Chargement des champs lorsqu'une ligne est sélectionnée
        tbUsers.addComponentListener(new ComponentAdapter() {
        });
        tbUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tbUsers.getSelectedRow();
                DatasController dt = new DatasController();
                ArrayList<User> statUsers = dt.getListUsers();
                User user = statUsers.get(index);
                tfEmail.setText(user.getEmail());
                tfUserFirstName.setText(user.getFirst_name());
                tfUserLastName.setText(user.getLast_name());
                lblUserIdValue.setText(String.valueOf(user.getId_user()));
                cbAdmin.setSelectedItem(user.getId_user());
                tfUserPass.setText(user.getPassword());

            }
        });


    }
    /*********************************************************************************************** FONCTIONS DOCUMENTS *********************************************************************************/

    // Fonction qui permet de les récupérer les valeurs de champs afin de les envoyer vers la BDD
    public Document getTextFieldsValues(){
        // Création d'un objet document qui recevra les valeurs des champs documents
        Document viewDoc = new Document();

        // chargement de ses paramètres
        if(tfTitle.getText().equals("") ||tfDocNbPages.getText().equals("")||tfYear.getText().equals("") || cbEdtion.getSelectedItem().equals(""))
            return null;
        if(lblDocIdValue.getText().equals(""))
            viewDoc.setId_document(0);
        else{
            viewDoc.setId_document(Integer.parseInt(lblDocIdValue.getText()));
        }
        viewDoc.setTitle(tfTitle.getText());
        viewDoc.setPages_nbr(Integer.parseInt(tfDocNbPages.getText()));
        viewDoc.setYear(Integer.parseInt(tfYear.getText()));
        viewDoc.setId_edition(((Edition) cbEdtion.getSelectedItem()).getId_edition());
        return viewDoc;
    }

    // Actualiser l'affichage de la table de documents
    public void refreshListDocuments(DefaultTableModel model, DocumentDAO documentDAO) {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        String searchParam = tfDocSearch.getText();
        ArrayList<Document> documentsResult = documentDAO.findAll();
        DatasController dc = new DatasController();
        dc.setListDocuments(documentsResult);
        for(Document doc :documentsResult){
            model.addRow(new Object[]{doc.getTitle(), doc.getPages_nbr(), doc.getYear()});
        }

    }




/*********************************************************************************************** FONCTIONS USERS *********************************************************************************/
    // Fonction qui permet de les récupérer les valeurs de champs afin de les envoyer vers la BDD
    public User getUserTextFieldsValues(){
        // Création d'un objet document qui recevra les valeurs des champs documents
        User viewUser = new User();

        // chargement de ses paramètres
        if(tfEmail.getText().equals("") ||tfUserFirstName.getText().equals("")||tfUserLastName.getText().equals("") ||tfEmail.getText().equals("")|| cbAdmin.getSelectedItem().equals(""))
            return null;
        if(lblUserIdValue.getText().equals(""))
            viewUser.setId_user(0);
        else{
            viewUser.setId_user(Integer.parseInt(lblUserIdValue.getText()));
        }
        viewUser.setPassword(tfUserPass.getText());
        viewUser.setEmail(tfEmail.getText());
        viewUser.setFirst_name(tfUserFirstName.getText());
        viewUser.setLast_name(tfUserLastName.getText());
        viewUser.setIs_admin((Integer) cbAdmin.getSelectedItem());
        return viewUser;
    }

    // Actualiser l'affichage de la table utilisateurs
    public void refreshListUsers(DefaultTableModel modelU, UserDAO userDAO) {
        int rowCount = modelU.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            modelU.removeRow(i);
        }
        String searchParam = tfUserSearch.getText();
        ArrayList<User> usersResult = userDAO.findAll();
        DatasController dc = new DatasController();
        dc.setListUsers(usersResult);
        for(User user :usersResult){
            modelU.addRow(new Object[]{user.getLast_name(), user.getFirst_name(), user.getEmail(),user.getIs_admin()});
        }
    }


}
