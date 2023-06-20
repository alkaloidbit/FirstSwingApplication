package Views;

import DAO.UserDAO;
import Models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                    infoLoginLabel.setText("Bienvenue");
                }


            }
        });
    }

    public User user;


}
