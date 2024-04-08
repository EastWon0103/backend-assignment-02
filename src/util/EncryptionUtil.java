package util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

    private static byte[] SECRET_KEY;

    public EncryptionUtil() {
        try {
            this.SECRET_KEY = getSecretKey();
        } catch (Exception e) {
            throw new RuntimeException("암호화 모듈 에러");
        }

    }

    private static byte[] getSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    public static byte[] encrypt(String plainText) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(plainText.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("암호화 과정 중 문제가 생겼습니다.");
        }

    }

    public static String decrypt(byte[] cipherText) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(cipherText));
        } catch (Exception e) {
            throw new RuntimeException("복호화 과정 중 문제가 생겼습니다.");
        }
    }
}
