import DAO.UserDAO;
import Models.PasswordHasher;
import Models.User;

public class testCreateUserWithHashedPassword {

    public void testCreateUser() {
        // Hash password
        PasswordHasher ph = new PasswordHasher();
        String password = "foo";
        byte[] salt = "monsel".getBytes();
        String hashedPassword = ph.hashPassword(password, salt);

        // Creare user obj
        UserDAO udao = new UserDAO();

        User user = new User();
        user.setFirst_name("Frederic");
        user.setLast_name("Oudjoudi");
        user.setEmail("fo@mail.com");
        user.setPassword(hashedPassword);
        user.setIs_admin(1);
        User res = udao.create(user);
        System.out.println(user);
    }
}
