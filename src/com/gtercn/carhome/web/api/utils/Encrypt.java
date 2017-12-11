package com.gtercn.carhome.web.api.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import com.gtercn.carhome.web.api.CarHomeApiProperties;

/**
 * @Project:
 * @Title: Encrypt.java
 * @Description: 对字符串进行加密、解密操作
 * @Company:
 * @Author:
 * @Date:
 * @version 1.0
 */
public class Encrypt {
	
	static byte[] key = CarHomeApiProperties.CAR_HOME_API.getValue("encrypt_keys").getBytes();

	/**
	 * 将字符串加密 不可恢复
	 * @param msg 需要加密的字符串
	 * @return 返回加密后的字符串
	 */
	public static String computeDigest(String msg) {
		try {
			java.security.MessageDigest alg = java.security.MessageDigest
					.getInstance("SHA-1");
			alg.reset();
			alg.update(msg.getBytes());
			byte[] hash = alg.digest();
			String digest = "";
			for (int i = 0; i < hash.length; i++) {
				int v = hash[i] & 0xFF;
				v = v & 0xAB;
				if (v < 16)
					digest += "0";
				digest += Integer.toString(v, 16).toUpperCase();
			}
			return digest;
		} catch (Exception e) {
			e.printStackTrace();
			return msg;
		}
	}

	/**
	 * 可逆的加密算法
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encode(String str) {
		String strTemp = "";
		try {
			byte[] input = str.getBytes();
			SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, "DES");
			Cipher c1 = Cipher.getInstance("DES");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			byte[] cipherByte = c1.doFinal(input);
			strTemp = byte2hex(cipherByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strTemp;
	}

	/**
	 * 针对encode方法的解密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String decode(String str) {
		if (str == null||"".equals(str))
			return null;
		String strTemp = null;
		try {
			byte[] input = hex2byte(str);
			SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, "DES");
			Cipher c1 = Cipher.getInstance("DES");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			byte[] clearByte = c1.doFinal(input);
			strTemp = new String(clearByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strTemp;
	}

	/**
	 * 字节码转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * 16进制字符串转换成字节码
	 * 
	 * @param h
	 * @return
	 */
	public static byte[] hex2byte(String h) {
		byte[] ret = new byte[h.length() / 2];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = Integer.decode("#" + h.substring(2 * i, 2 * i + 2))
					.byteValue();
		}
		return ret;
	}

	/**
	 * 解密token返回user_id
	 * @param token
	 * @return
	 * 2016-12-19 下午02:06:42
	 */
	public static String getEncryptUserId(String token){
		if(token==null ||token.equals(""))
			return null;
		String result=decode(token).split("_")[0];
		if(result.equals(""))
			result= null;
		return result;
	}
//	/**
//	 * 解密token返回device_token
//	 * @param token
//	 * @return
//	 * 2016-12-19 下午02:06:59
//	 */
//	public static String getEncryptDeviceToken(String token){
//		if(token==null ||token.equals(""))
//			return null;
//		String result=decode(token).split("_")[2];
//		if(result.equals(""))
//			result= null;
//		return result;
//	}
}
