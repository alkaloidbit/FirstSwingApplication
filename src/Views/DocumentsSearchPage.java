package Views;

import Controllers.DatasController;
import DAO.*;
import Models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
    private JTextField pfUserPass;
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
    private JScrollPane spAuthors;
    private JLabel lblAuthors;
    private JList lstAuthors;
    private JList lstAuthorsDoc;
    private JButton btnAddAuthor;
    private JButton btnRemoveAuthor;
    private JScrollPane spAuthorsList;
    private JScrollPane spAuthorsDocList;
    private JPanel pGenre;
    private JComboBox cbGenre;
    private JTextField tfGenre;
    private JButton btnGenre;
    private JLabel lblGenre;
    private JLabel lblFieldsInfo;
    private JLabel lblFieldsInfoUser;

    private static ArrayList<Author> authorsList;
    private static ArrayList<Document> documentsList;
    private static ArrayList<Edition> editionsList;
    private static ArrayList<Genre> genresList;



    public DocumentsSearchPage(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        documentsList = documentDAO.findAll();
        DatasController dc = new DatasController();
        // dc.setListDocuments(documents);

        for(Document doc :documentsList){
            model.addRow(new Object[]{doc.getTitle(), doc.getPages_nbr(), doc.getYear()});
        }

        // Récupération des éditions pour le comboBox
        EditionDAO editionDAO = new EditionDAO();
        editionsList = editionDAO.findAll();
        // Injection dans le combobox
        for (Edition ed : editionsList) {
            cbEdtion.addItem(ed);
        }
        // Envoi vers DataController
        //DatasController dce = new DatasController();
        //dce.setListEditionCombo(editionsList);

        // Récupération des genres pour le comboBox
        GenreDAO genreDAO = new GenreDAO();
        genresList = genreDAO.findAll();
        // Injection dans le combobox
        for (Genre ge : genresList) {
            cbGenre.addItem(ge);
        }

        // Récupération des auteurs pour le comboBox
        AuthorDAO authorDAO = new AuthorDAO();
        authorsList = authorDAO.findAll();
        // Injection dans le combobox
        for (Author aut : authorsList) {
            cbAuteur.addItem(aut);
        }
        // Injection des auteur dans la jList auteur
        DefaultListModel<Author> autModel = new DefaultListModel<Author>();
        autModel.addAll(authorsList);
        lstAuthors.setModel(autModel);

        // Création d'un model pour la jList des auteurs du document traité
        DefaultListModel<Author> autModelDoc = new DefaultListModel<Author>();
        lstAuthorsDoc.setModel(autModelDoc);

        // Envoi vers DataController
        //dce.setListAuthorCombo(authorsList);


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
                emptyDocumentFields(autModelDoc);
            }
        });

        // Mettre à jour documents
        btnDocUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = getTextFieldsValues();
                if (document == null) {
                    lblInfo.setText("Informations incomplètes");
                } else if (document.getId() == 0) {
                    lblInfo.setText("L'id n'est pas renseigné");
                } else {
                    DocumentDAO documentDAO1 = new DocumentDAO();
                    documentDAO1.update(document);
                }
                refreshListDocuments(model, documentDAO);
                emptyDocumentFields(autModelDoc);
            }
        });

        // Supprimer document
        btnDocDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = getTextFieldsValues();
                if (document == null) {
                    lblInfo.setText("Informations incomplètes");
                } else if (document.getId() == 0) {
                    lblInfo.setText("L'id n'est pas renseigné");
                } else {
                    DocumentDAO documentDAO1 = new DocumentDAO();
                    documentDAO1.delete(document);
                }
                refreshListDocuments(model, documentDAO);
                emptyDocumentFields(autModelDoc);
            }
        });
        // Vider les champs document
        btnDocClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyDocumentFields(autModelDoc);
            }
        });
        // Fonction de création d'auteur
        btnAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Author authorView = new Author();
                if(tfAuthor.getText().contains(" ")){
                    String[] authorNames = tfAuthor.getText().split(" ");
                    authorView.setFirst_name(authorNames[0]);
                    authorView.setLast_name(authorNames[1]);
                }
                else{
                    authorView.setFirst_name(tfAuthor.getText());
                    authorView.setLast_name(" ");
                }
                AuthorDAO authorDAO1 = new AuthorDAO();
                Author resultCreateAut = authorDAO1.create(authorView);

                authorsList.add(resultCreateAut);
                cbAuteur.addItem(resultCreateAut);
                autModel.addElement(resultCreateAut);
                lstAuthors.setModel(autModel);
            }
        });
        // Fonction de création d'edition
        btnEdition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Edition editionView = new Edition();
                editionView.setName(tfEdition.getText());
                EditionDAO editionDAO1 = new EditionDAO();
                Edition resultCreateEdition = editionDAO1.create(editionView);
                cbEdtion.addItem(editionView);
            }
        });
        // Fonction de création de genre
        btnGenre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Genre genreView = new Genre();
                genreView.setName(tfGenre.getText());
                GenreDAO genreDAO = new GenreDAO();
                genreView = genreDAO.create(genreView);
                cbGenre.addItem(genreView);
            }
        });
        // Chargement des champs doc lorsqu'une ligne est sélectionnée
        tbDocuments.addComponentListener(new ComponentAdapter() {
        });
        tbDocuments.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                autModelDoc.removeAllElements();
                int index = tbDocuments.getSelectedRow();
                //DatasController dt = new DatasController();
                //ArrayList<Document> statDocs = dt.getListDocuments();
                Document doc = documentsList.get(index);
                tfTitle.setText(doc.getTitle());
                tfDocNbPages.setText(String.valueOf(doc.getPages_nbr()));
                tfYear.setText(String.valueOf(doc.getYear()));
                lblDocIdValue.setText(String.valueOf(doc.getId()));
                //DatasController dc = new DatasController();
                //Edition ed = dc.getEditionById(doc.getId_edition());
                cbEdtion.getModel().setSelectedItem(doc.getEdition());
                cbGenre.getModel().setSelectedItem(doc.getGenre());
                // Injection dans la jList Auteurs du doc
                autModelDoc.addAll(doc.getAuthors());
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
        //String[] header = {"Nom", "Prénom", "Email", "Admin"};

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
                emptyUserFields();
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
                else if(user.getId()== 0){
                    lblInfo.setText("L'id n'est pas renseigné");
                }
                else{
                    UserDAO userDAO1 = new UserDAO();
                    userDAO1.update(user);
                }
                refreshListUsers( modelU,  userDAO);
                emptyUserFields();
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
                else if(user.getId()== 0){
                    lblInfo.setText("L'id n'est pas renseigné");
                }
                else{
                    UserDAO userDAO1 = new UserDAO();
                    userDAO1.delete(user);
                }
                refreshListUsers( modelU,  userDAO);
                emptyUserFields();
            }
        });
        // Vider les champs user
        btnUserClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyUserFields();
            }
        });

        // Chargement des champs user lorsqu'une ligne est sélectionnée
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
                lblUserIdValue.setText(String.valueOf(user.getId()));
                cbAdmin.setSelectedItem(user.getId());
                pfUserPass.setText(user.getPassword());

            }
        });

        // Ajout des auteurs sélectionnés à la liste d'auteurs du document selectionné
        btnAddAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] newAuthorsIndices = lstAuthors.getSelectedIndices();
                for (int index : newAuthorsIndices){
                    if(!autModelDoc.contains(authorsList.get(index))){
                        autModelDoc.addElement(authorsList.get(index));
                    }

                }
            }
        });

        // Retirer les auteurs sélectionnés de la liste d'auteurs du document selectionné
        btnRemoveAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] newAuthorsDocIndices = lstAuthorsDoc.getSelectedIndices();
                for (int index = newAuthorsDocIndices.length - 1; index >=0; index--){
                    autModelDoc.remove(index);
                }
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
            viewDoc.setId(0);
        else{
            viewDoc.setId(Integer.parseInt(lblDocIdValue.getText()));
        }
        viewDoc.setTitle(tfTitle.getText());
        viewDoc.setPages_nbr(Integer.parseInt(tfDocNbPages.getText()));
        viewDoc.setYear(tfYear.getText());
        viewDoc.setEdition((Edition) cbEdtion.getSelectedItem());
        viewDoc.setGenre((Genre) cbGenre.getSelectedItem());
        ArrayList<Author> docAuthors = new ArrayList<Author>();
        for(int i = 0; i < lstAuthorsDoc.getModel().getSize(); i++){
            docAuthors.add((Author) lstAuthorsDoc.getModel().getElementAt(i));
        }
        viewDoc.setAuthors(docAuthors);
        return viewDoc;
    }

    // Actualiser l'affichage de la table de documents
    public void refreshListDocuments(DefaultTableModel model, DocumentDAO documentDAO) {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        String searchParam = tfDocSearch.getText();
        documentsList = documentDAO.findAll();
        //DatasController dc = new DatasController();
        // dc.setListDocuments(documentsResult);
        for(Document doc :documentsList){
            model.addRow(new Object[]{doc.getTitle(), doc.getPages_nbr(), doc.getYear()});
        }

    }

    // Vider les champs document
    public void emptyDocumentFields(DefaultListModel<Author> autModelDoc){
        tfTitle.setText("");
        tfDocNbPages.setText("");
        tfYear.setText("");
        lblDocIdValue.setText("");
        autModelDoc.removeAllElements();
        cbAuteur.setSelectedItem(cbAuteur.getItemAt(0));
        cbEdtion.setSelectedItem(cbEdtion.getItemAt(0));
        cbGenre.setSelectedItem(cbGenre.getItemAt(0));

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
            viewUser.setId(0);
        else{
            viewUser.setId(Integer.parseInt(lblUserIdValue.getText()));
        }

        viewUser.setPassword(pfUserPass.getText());
        viewUser.setEmail(tfEmail.getText());
        viewUser.setFirst_name(tfUserFirstName.getText());
        viewUser.setLast_name(tfUserLastName.getText());
        viewUser.setIs_admin((Integer) cbAdmin.getSelectedItem());
        return viewUser;

        /* if(verifyFieldsContent(pfUserPass.getText(),"password")
                && verifyFieldsContent(tfEmail.getText(),"email")
                && verifyFieldsContent(tfUserFirstName.getText(),"text")
                && verifyFieldsContent(tfUserLastName.getText(),"text")){
            viewUser.setPassword(pfUserPass.getText());
            viewUser.setEmail(tfEmail.getText());
            viewUser.setFirst_name(tfUserFirstName.getText());
            viewUser.setLast_name(tfUserLastName.getText());
            viewUser.setIs_admin((Integer) cbAdmin.getSelectedItem());
            return viewUser;
        }
        return null;
        */

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
    // Vider les champs utilisateur
    public void emptyUserFields(){
        tfEmail.setText("");
        pfUserPass.setText("");
        tfUserFirstName.setText("");
        tfUserLastName.setText("");
        lblUserIdValue.setText("");
        cbAdmin.setSelectedItem(cbAdmin.getItemAt(0));
    }

    /*********************************************************************************************** FONCTIONS USERS ET DOCUMENTS *********************************************************************************/

    public boolean verifyFieldsContent(String filedContent, String fieldType){
        switch(fieldType){
            case "password":
                boolean okPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$").matcher(filedContent).matches();
                if(okPassword == true){
                    return true;
                }
                else{
                    lblFieldsInfoUser.setText("Le mot de passe doit contenir au moins huit caractères, dont au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
                    return false;
                }

            case "text":
                boolean okText = Pattern.compile("[a-zA-Z_]").matcher(filedContent).matches();
                if(okText == true){
                    return true;
                }
                else{
                    lblFieldsInfoUser.setText("Email non conforme");
                    return false;
                }

            case "textNum":
                boolean okTextNum = Pattern.compile("[a-zA-Z_0-9]").matcher(filedContent).matches();
                if(okTextNum == true){
                    return true;
                }
                else{
                    lblFieldsInfoUser.setText("Email non conforme");
                    return false;
                }


            case "email":
                boolean okEmail = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$").matcher(filedContent).matches();
                if(okEmail == true){
                    return true;
                }
                else{
                    lblFieldsInfoUser.setText("Email non conforme");
                    return false;
                }


            case "number":
                boolean okNumber = filedContent.chars().allMatch(Character::isDigit);
                if(okNumber == true){
                    return true;
                }
                else{
                    lblFieldsInfoUser.setText("Valeur numérique non conforme");
                    return false;
                }

            default:
                System.out.println("Erreur fonction verifyFieldsContent");
                return false;

        }
    }

}
