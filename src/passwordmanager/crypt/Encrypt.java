package src.passwordmanager.crypt;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt extends SecretKey {
    private static SecretKeySpec secretKey = null;

    public String encrypt(String passwordToEncrypt, String inputKey) {
        secretKey = getSecretKey(inputKey);
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cipher.update(passwordToEncrypt.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(cipher.doFinal());
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e);
            return null;
        }
    }
}
