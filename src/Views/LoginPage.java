package Views;

import DAO.UserDAO;

import Models.LoggedUser;
import Models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPage extends JFrame {
    private JLabel userEmailLabel;
    private JTextField userNameTextField;
    private JButton loginButton;
    private JPasswordField userPasswordField;
    private JLabel passwordLabel;
    private JPanel loginPanel;
    private JLabel infoLoginLabel;
    private JLabel lblLoginInfo;

    public LoginPage() {
        super("Bibliothèque Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setContentPane(loginPanel);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEmail = userNameTextField.getText();
                String userPassword = String.valueOf(userPasswordField.getPassword());
                UserDAO userDAO = new UserDAO();
                boolean userOK = userDAO.checkValidUser(userEmail,userPassword);
                if(userOK == false){
                    infoLoginLabel.setText("Utilisateur inconnu");
                }
                else{
                    User userLogged = userDAO.findByEmail(userEmail);
                    infoLoginLabel.setText("Bienvenue "+userLogged.getFirst_name()+ " " + userLogged.getLast_name());

                    if(userLogged.getIs_admin() == 1){
                        DocumentsSearchPage documentsSearchPage = new DocumentsSearchPage();
                        modifyVisiblility(false);
                    }
                    else{
                        DocumentsSearchPageBasic documentsSearchPage = new DocumentsSearchPageBasic();
                        modifyVisiblility(false);
                    }

                }
            }
        });
        // Pour soumettre avec "entrée"
        getRootPane().setDefaultButton(loginButton);

    }

    public User user;

    public void modifyVisiblility(boolean bo){
        this.setVisible(bo);
    }
}
