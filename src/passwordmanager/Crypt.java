package src.passwordmanager;

import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypt {
    private static SecretKeySpec secretKey;
    private static byte[] key;

    private static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cipher.update(strToEncrypt.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(cipher.doFinal());
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e);
            return null;
        }
    }

    public String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            cipher.update(Base64.getDecoder().decode(strToDecrypt));
            return new String(cipher.doFinal());
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e);
            return null;
        }
    }
}
