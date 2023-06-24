package Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
    public String hashPassword(String password, byte[] salt) {
       try {
            // CONCaténation du sel et du mot de passe
            byte[] saltedPassword = new byte[salt.length + password.length()];
            System.arraycopy(salt, 0, saltedPassword, 0, salt.length);
            System.arraycopy(password.getBytes(), 0, saltedPassword, salt.length, password.length());

            // Calcul du hachage du sel+mot de passe
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(saltedPassword);

            // Conversion du hachage en une représentation hexadécimale ou en Base64
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
