package com.littlenb.tools.codec;

import com.littlenb.tools.ToolsException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/***
 * AES加解密
 *
 * @author svili
 *
 */
public class AESUtil {

    public static byte[] generateKey() {
        SecretKey secretKey = getGenerator().generateKey();
        return secretKey.getEncoded();
    }

    public static byte[] encrypt(byte[] data, byte[] encodedKey)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 初始化加密算法
        SecretKeySpec keySpec = new SecretKeySpec(encodedKey, "AES");
        Cipher cipher = getCipher();

        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        // 加密
        return cipher.doFinal(data);

    }

    public static byte[] decrypt(byte[] data, byte[] encodedKey)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 初始化解密算法
        SecretKeySpec keySpec = new SecretKeySpec(encodedKey, "AES");
        Cipher cipher = getCipher();

        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        // 解密
        return cipher.doFinal(data);
    }

    private static KeyGenerator getGenerator() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new ToolsException("KeyGenerator for AES init error.", e);
        }
        // AES秘钥长度:128bit(位)=16byte(字节)
        keyGenerator.init(128);
        return keyGenerator;
    }

    private static Cipher getCipher() {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new ToolsException("Cipher for AES init error.", e);
        } catch (NoSuchPaddingException e) {
            throw new ToolsException("Cipher for AES init error.", e);
        }
        return cipher;
    }

}
