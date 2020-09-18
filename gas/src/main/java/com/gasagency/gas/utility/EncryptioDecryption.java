package com.gasagency.gas.utility;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
public class EncryptioDecryption {
	
	private static final String ALGO="AES";
	//@Value("${security.keyvalue}")
	public static String keyvalue="lv39eptlvuhaqqsr";

	public static String encrypt(String password)throws Exception
	{
		Key key=generateKey();
		Cipher c=Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE,key);
		byte[] encVal=c.doFinal(password.getBytes());
		String encryptedValue=new BASE64Encoder().encode(encVal);
		return encryptedValue;
	}
	
	public static String decrypt(String encryptedPassword)throws Exception
	{
		Key key=generateKey();
		Cipher c=Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE,key);
		byte[] decoderValue=new BASE64Decoder().decodeBuffer(encryptedPassword);
		byte[] decValue=c.doFinal(decoderValue);
		String decryptedValue=new String(decValue);
		return decryptedValue;
	}
	
	private static Key generateKey() throws Exception
	{
		Key key=new SecretKeySpec(keyvalue.getBytes(),ALGO);
		return key;
	}
}
