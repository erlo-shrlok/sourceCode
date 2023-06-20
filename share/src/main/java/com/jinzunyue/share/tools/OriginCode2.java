package com.jinzunyue.share.tools;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OriginCode2 {
        private static final String SECRET_KEY = "1234567890123456";  // 16位密钥

        public static void getCode() throws Exception {
            String originalText = "这是一串需要加密的中文字符";
            String encryptedText = encrypt(originalText);
            String decryptedText = decrypt(encryptedText);

            System.out.println("Original Text: " + originalText);
            System.out.println("Encrypted Text: " + encryptedText);
            System.out.println("Decrypted Text: " + decryptedText);
        }

        // 加密
        public static String encrypt(String originalText) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(originalText.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }

        // 解密
        public static String decrypt(String encryptedText) throws Exception {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        }

}
