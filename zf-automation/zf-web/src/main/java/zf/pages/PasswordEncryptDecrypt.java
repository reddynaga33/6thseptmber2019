package zf.pages;
import org.apache.commons.codec.binary.Base64;

import framework.TestLogger;

public class PasswordEncryptDecrypt {
	static String a=null;
	static byte[]encode=null;
	public String passwordEncrypt(String password) {
		encode=Base64.encodeBase64(password.getBytes());
		String encryptedValue = new String(encode);
		System.out.println(encryptedValue);
		return encryptedValue;
	}

	public String passwordDecript(String encryptedValue) {
		String decodeBase64 =null;
		try {
			byte[] bytes = encryptedValue.getBytes();
			decodeBase64 = new String(Base64.decodeBase64(bytes));
			System.out.println(decodeBase64);

		}catch(Exception e) {
			TestLogger.fileInfo(e.getMessage());
		}
		return decodeBase64;
	}
}

