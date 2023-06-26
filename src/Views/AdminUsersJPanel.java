package Views;
import DAO.UserDAO;
import Models.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminUsersJPanel extends JPanel {

    private JLabel lblUserSearch;
    private JTextField tfUserSearch;
    private JButton btnUserSearch;
    private JTable tbUsers;
    private JLabel lblUserFirstName;
    private JTextField tfUserFirstName;
    private JLabel lblUserLastName;
    private JTextField tfUserLastName;
    private JLabel lblUserId;
    private JLabel lblUserIdValue;
    private JLabel lblInfo;
    private JLabel lblUserEmail;
    private JComboBox cbAdmin;
    private JLabel lblUserPass;
    private JTextField tfEmail;
    private JButton btnUserCreate;
    private JButton btnUserUpdate;
    private JButton btnUserClear;
    private JButton btnUserDel;
    private JLabel lblUserAdmin;
    private JTextField tfUserPass;
    private JPanel pAdminUsers;
    private JPanel pUser1;
    private JPanel pUser2;
    private JPanel pUser3;

    public AdminUsersJPanel(){
        // Récupération du critère de recherche
        String SearchParam = tfUserSearch.getText();
        // Entêtes de la table d'affichage des données
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("Email");
        model.addColumn("Admin");
        tbUsers.setModel(model);
        // Recherche dans la BDD
        UserDAO userDAO = new UserDAO();
        // Récupération de l'ensemble des utilisateurs pour affichage dans la jtable
        ArrayList<User> users = userDAO.findAll();
        for(User user :users){
            model.addRow(new Object[]{user.getLast_name(), user.getFirst_name(), user.getEmail(),user.getIs_admin()});
        }


        // Chercher par nom d'un utilisateur
        btnUserSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                String searchParam = tfUserSearch.getText();
                ArrayList<User> usersResult = userDAO.findNameWithParam(searchParam);
                for(User user :usersResult){
                    model.addRow(new Object[]{user.getLast_name(), user.getFirst_name(), user.getEmail(),user.getIs_admin()});
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
                refreshListUsers( model,  userDAO);
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
                refreshListUsers( model,  userDAO);
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
                refreshListUsers( model,  userDAO);
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
            }
        });


    }
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
        viewUser.setPassword(tfUserPass.getText());
        viewUser.setEmail(tfEmail.getText());
        viewUser.setFirst_name(tfUserFirstName.getText());
        viewUser.setLast_name(tfUserLastName.getText());
        viewUser.setIs_admin((Integer) cbAdmin.getSelectedItem());
        return viewUser;
    }

    // Actualiser l'affichage de la table utilisateurs
    public void refreshListUsers(DefaultTableModel model, UserDAO userDAO) {
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        String searchParam = tfUserSearch.getText();
        ArrayList<User> usersResult = userDAO.findAll();
        for(User user :usersResult){
            model.addRow(new Object[]{user.getLast_name(), user.getFirst_name(), user.getEmail(),user.getIs_admin()});
        }
    }


}
