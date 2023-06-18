package com.calsoft.pos.config;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionManager {

	private SecretKeySpec secretKeySpec;
	private Cipher cipher;

	@Inject
	public EncryptionManager(@Value("${secret_key}") String secret_key)
			throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException {
		secretKeySpec = new SecretKeySpec(secret_key.getBytes("UTF-8"), "AES");
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	}


	
	public String decrypt(String encryptedString) {
		String decryptedText = null;
		try {
			
			encryptedString=encryptedString.replace("xMl3Jk", "+" ).replace("Por21Ld", "/").replace("Ml32", "=");
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
