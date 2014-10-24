package org.hillel.it.mycity.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoHelper {
	private CryptoHelper() {
		
	}

	public static String shaOne(String str){
		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-1");
			sha.update(str.getBytes());
			byte[] msgDigest = sha.digest();
			return byteArrayToHexString(msgDigest);			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	public static String byteArrayToHexString(byte[] b) {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
	}	
	

}
