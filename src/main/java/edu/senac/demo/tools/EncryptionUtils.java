package edu.senac.demo.tools;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.springframework.util.Base64Utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class EncryptionUtils {

    private final int KEY_SIZE = 256; // Tamanho da chave em bits
    private final int IV_SIZE = 16; // Tamanho do vetor de inicialização em bytes
    private String token;

    public EncryptionUtils() throws NoSuchAlgorithmException {
        setToken();
    }

    private void setToken() throws NoSuchAlgorithmException {
        this.token = generateBase64EncodedKey();
    }

    private byte[] generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Tamanho da chave em bits
        SecretKey secretKey = keyGenerator.generateKey();

        return secretKey.getEncoded();
    }

    private byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
        cipher.init(true, new ParametersWithIV(new KeyParameter(key), iv));
        byte[] output = new byte[cipher.getOutputSize(data.length)];
        int bytesWritten = cipher.processBytes(data, 0, data.length, output, 0);
        cipher.doFinal(output, bytesWritten);
        return output;
    }

    private byte[] decrypt(byte[] encryptedData, byte[] key, byte[] iv) throws Exception {
        BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()));
        cipher.init(false, new ParametersWithIV(new KeyParameter(key), iv));
        byte[] output = new byte[cipher.getOutputSize(encryptedData.length)];
        int bytesWritten = cipher.processBytes(encryptedData, 0, encryptedData.length, output, 0);
        cipher.doFinal(output, bytesWritten);
        return output;
    }

    public String encryptData(String data, String keyString) throws Exception {
        byte[] key = Arrays.copyOfRange(Base64Utils.decodeFromString(keyString), 0, KEY_SIZE / 8);
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        byte[] encryptedData = encrypt(data.getBytes(), key, iv);
        byte[] output = new byte[IV_SIZE + encryptedData.length];
        System.arraycopy(iv, 0, output, 0, IV_SIZE);
        System.arraycopy(encryptedData, 0, output, IV_SIZE, encryptedData.length);
        return Base64Utils.encodeToString(output);
    }

    public String decryptData(String encryptedDataString, String keyString) throws Exception {
        byte[] key = Arrays.copyOfRange(Base64Utils.decodeFromString(keyString), 0, KEY_SIZE / 8);
        byte[] encryptedData = Base64Utils.decodeFromString(encryptedDataString);
        byte[] iv = Arrays.copyOfRange(encryptedData, 0, IV_SIZE);
        byte[] encryptedBytes = Arrays.copyOfRange(encryptedData, IV_SIZE, encryptedData.length);
        byte[] decryptedData = decrypt(encryptedBytes, key, iv);
        return new String(decryptedData);
    }

    public String generateBase64EncodedKey() throws NoSuchAlgorithmException {
        byte[] key = generateKey();
        return Base64Utils.encodeToString(key);
    }

    public SecretKey generateSecretKeyFromBase64String(String base64Key) {
        byte[] decodedKey = Base64Utils.decodeFromString(base64Key);
        return new SecretKeySpec(decodedKey, "AES");
    }

    public void clearKey(String key) throws NoSuchAlgorithmException {
        byte[] keyConvert = key.getBytes();
        Arrays.fill(keyConvert, (byte) 0);
    }
}
