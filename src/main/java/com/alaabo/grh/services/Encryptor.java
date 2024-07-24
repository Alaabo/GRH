package com.alaabo.grh.services;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
@Service
public class Encryptor {

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int IV_SIZE = 12;
    private static final int TAG_SIZE = 128;
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String encrypt(String data) throws Exception {
        SecretKey key = getKeyFromEnv();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        byte[] iv = generateIV();
        GCMParameterSpec spec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        byte[] ivAndCiphertext = concatenate(iv, encryptedBytes);

        return Base64.getEncoder().encodeToString(ivAndCiphertext);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKey key = getKeyFromEnv();
        byte[] ivAndCiphertext = Base64.getDecoder().decode(encryptedData);
        byte[] iv = new byte[IV_SIZE];
        byte[] ciphertext = new byte[ivAndCiphertext.length - IV_SIZE];

        System.arraycopy(ivAndCiphertext, 0, iv, 0, IV_SIZE);
        System.arraycopy(ivAndCiphertext, IV_SIZE, ciphertext, 0, ciphertext.length);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);

        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes);
    }

    private static SecretKey getKeyFromEnv() {
        String encodedKey = System.getProperty("ENCRYPTION_KEY");
        if (encodedKey == null) {
            throw new IllegalStateException("Environment variable ENCRYPTION_KEY not set");
        }
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

//    public static SecretKey generateKey() throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(256); // Key size
//        SecretKey key = keyGen.generateKey();
//        System.out.println("Base64 Encoded Key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
//        return key;
//    }

    private static byte[] generateIV() {
        byte[] iv = new byte[IV_SIZE];
        secureRandom.nextBytes(iv);
        return iv;
    }

    private static byte[] concatenate(byte[] iv, byte[] encryptedBytes) {
        byte[] ivAndCiphertext = new byte[IV_SIZE + encryptedBytes.length];
        System.arraycopy(iv, 0, ivAndCiphertext, 0, IV_SIZE);
        System.arraycopy(encryptedBytes, 0, ivAndCiphertext, IV_SIZE, encryptedBytes.length);
        return ivAndCiphertext;
    }
//    public static void main(String[] args) throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(256);
//        SecretKey secretKey = keyGen.generateKey();
//        String base64EncodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//        System.out.println("Base64 Encoded Key: " + base64EncodedKey);
//    }
}




