package src.passwordmanager.crypt;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt extends SecretKey {
    private static SecretKeySpec secretKey = null;

    public String decrypt(String hashToDecrypt, String inputKey) {
        secretKey = getSecretKey(inputKey);
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            cipher.update(Base64.getDecoder().decode(hashToDecrypt));
            return new String(cipher.doFinal());
        } catch (Exception e) {
            System.out.println("Invalid secret key");
            return null;
        }
    }
}
