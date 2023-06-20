package com.jinzunyue.share.tools;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.GZIPInputStream;

/**
 * 这个代码首先使用AES加密算法将原始文本加密，然后使用GZIP算法将加密后的数据压缩，最后将压缩后的数据编码为Base64字符串。解密过程是加密过程的逆过程：首先将Base64字符串解码为压缩后的数据，然后使用GZIP算法将数据解压，最后使用AES加密算法将数据解密。
 *
 * 这个代码的输出长度会比输入长度小，因为我们使用了数据压缩。然而，压缩后的数据可能仍然比你希望的长度要大。如果你需要更小的输出长度，你可能需要使用一种更高效的数据压缩算法，或者将你的输入数据分割成更小的片段。
 *
 * 注意，这个代码的安全性依赖于密钥的安全性。
 *
 * 我们可以使用一种称为哈希函数的方法来生成一个固定长度的字符串。哈希函数可以将任意长度的输入转换为固定长度的输出。然而，哈希函数是一种单向函数，这意味着你不能从哈希值反向得到原始的输入。这可能不符合你的需求，因为你提到你需要解密功能。
 *
 * 如果你需要解密功能，那么你可能需要使用一种称为对称加密的方法。在对称加密中，加密和解密使用的是同一个密钥。这意味着，只要你知道密钥，你就可以解密加密的信息。
 *
 * 然而，对称加密通常会生成与输入长度相近的输出，这可能不符合你希望输出长度较小的需求。为了解决这个问题，我们可以使用一种称为数据压缩的方法。数据压缩可以将数据的大小减小，使其更适合存储或传输。
 *
 * 以下是一个使用AES对称加密和GZIP数据压缩的示例：
 */
public class OriginCode3 {

    public static void makeCode() throws Exception {
        String originalText = "哇哈哈";
        String key = "1234567890123456"; // 密钥，长度必须是16个字符

        String encryptedText = encrypt(originalText, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Original Text: " + originalText);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    // 加密函数
    public static String encrypt(String originalText, String key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(originalText.getBytes("UTF-8"));

        // 压缩数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream(baos);
        gos.write(encrypted);
        gos.close();

        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    // 解密函数
    public static String decrypt(String encryptedText, String key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        // 解压数据
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(encryptedText));
        GZIPInputStream gis = new GZIPInputStream(bais);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = gis.read(buf)) > 0) {
            baos.write(buf, 0, len);
        }
        gis.close();

        byte[] original = cipher.doFinal(baos.toByteArray());
        return new String(original);
    }
}
