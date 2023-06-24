package gestion_bu.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
    public static void main(String[] args) {
        String password = "monMotDePasse";
        byte[] salt = "monSel".getBytes();
        String hashedPassword = hashPassword(password, salt);

        System.out.println("Mot de passe: " + password);
        System.out.println("Sel: " + new String(salt));
        System.out.println("Mot de passe haché: " + hashedPassword);
    }

    private static String hashPassword(String password, byte[] salt) {
        try {
            // Concaténation du sel et du mot de passe
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