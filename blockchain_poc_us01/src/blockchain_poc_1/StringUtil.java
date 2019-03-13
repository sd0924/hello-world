package blockchain_poc_1;

import java.security.MessageDigest;

public class StringUtil {
	
	//many cryptographic algo
	//will go for SHA256
	
	//basically this method will take a string and applies sha-256 to it
	//and returns the generated signature in string
	
	public static String applySha256(String input){
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for(int i=0; i<hash.length; i++){
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1)
					hexString.append(hex);
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}
