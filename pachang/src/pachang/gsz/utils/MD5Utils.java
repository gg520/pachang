package pachang.gsz.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.digest.Md5Crypt;

public class MD5Utils {
	public static void main(String[] args) {
		String str = "0001";
		
		System.out.println(md5Encode(str));

	}
	    
	     
	     /**
	      * MD5加密 生成32位md5码
	      * 
	      * @author guosuzhou
	      * @param inStr 待加密字符串
	      * @return  返回32位md5
	      * 
	      * @data 2017年12月29日下午8:35:30
	      */
	    public static String md5Encode(String inStr) {
	     try{
		        MessageDigest md5 = null;
		        try {
		            md5 = MessageDigest.getInstance("MD5");
		        } catch (Exception e) {
		            System.out.println(e.toString());
		            e.printStackTrace();
		            return "";
		        }
		        
		        byte[] byteArray = inStr.getBytes("UTF-8");
		        byte[] md5Bytes = md5.digest(byteArray);
		        StringBuffer hexValue = new StringBuffer();
		        for (int i = 0; i < md5Bytes.length; i++) {
		            int val = ((int) md5Bytes[i]) & 0xff;
		            if (val < 16) {
		                hexValue.append("0");
		            }
		            hexValue.append(Integer.toHexString(val));
		        }
		        
	             return hexValue.toString();
	     }catch(Exception e){
	        	e.printStackTrace();
	      }
	     return inStr;
	    }
	    
	 
	    
	    
}
