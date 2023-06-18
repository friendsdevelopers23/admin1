package com.calsoft.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionManagerCookie {

	private SecretKeySpec secretKeySpec;
	private Cipher cipher;

	@Inject
	public EncryptionManagerCookie(@Value("${secret_key}") String secret_key)
			throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException {
		secretKeySpec = new SecretKeySpec(secret_key.getBytes("UTF-8"), "AES");
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	}

	private String myEncryptionKey;
	private String myEncryptionScheme;

	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	byte[] arrayBytes;
	private KeySpec ks;
	private SecretKeyFactory skf;

	SecretKey key;

	public void TrippleDes() throws Exception {
		myEncryptionKey = "ThisIsSpartaThisIsSparta";
		myEncryptionScheme = "AES/ECB/PKCS5Padding";
		arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		ks = new DESedeKeySpec(arrayBytes);
		skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		cipher = Cipher.getInstance(myEncryptionScheme);
		key = skf.generateSecret(ks);
	}

	public String encrypt(String unencryptedString) {
		String encryptedString = null;
		try {
			unencryptedString=unencryptedString.replace("xMl3Jk", "+" ).replace("Por21Ld", "/").replace("Ml32", "=");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encryptedString = new String(Base64.encodeBase64(encryptedText));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	public String decrypt(String encryptedString) {
		String decryptedText = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] encryptedText = Base64.decodeBase64(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}

}
