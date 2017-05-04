/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package com.mancheng.utils;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author tallauze
 */
public class KeyGenerator {

  public static final String ALGORITHM = "RSA";

  public static final String CIPHER_DECRYPT_ALGORYTHM = "RSA/ECB/PKCS1Padding";
  public static final String CIPHER_ENCRYPT_ALGORYTHM = "RSA/ECB/PKCS1Padding";

  public static KeyPair generateKeys() {
    KeyPairGenerator kpg = null;
    try {
      kpg = KeyPairGenerator.getInstance(ALGORITHM);
      kpg.initialize(1024);
    } catch (NoSuchAlgorithmException ex) {
      Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
    }
    KeyPair kp = kpg.generateKeyPair();
    return kp;
  }

  public static String encrypt(String text, PublicKey key) {
    Cipher cipher = null;
    byte[] cipherText = null;
    try {
      cipher = Cipher.getInstance(CIPHER_ENCRYPT_ALGORYTHM);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      cipherText = cipher.doFinal(text.getBytes("UTF-8"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    String temp = null;
    try {
      temp = new String(Base64.encodeBase64(cipherText), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return temp;
  }

  public static String decrypt(String text, PrivateKey key) {
    byte[] cryptedText = Base64.decodeBase64(text);
    byte[] decryptedText = null;
    Cipher cipher = null;
    String temp = null;
    try {
      cipher = Cipher.getInstance(CIPHER_DECRYPT_ALGORYTHM);
      cipher.init(Cipher.DECRYPT_MODE, key);
      decryptedText = cipher.doFinal(cryptedText);
      temp = new String(decryptedText);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return temp;
  }

  public static String genRandomPwd(int pwd_len) {
    // 35是因为数组是从0开始的，26个字母+10个数字
    final int maxNum = 36;
    int i; // 生成的随机数
    int count = 0; // 生成的密码的长度
    char[] str =
        {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9'};
    StringBuffer pwd = new StringBuffer("");
    Random r = new Random();
    while (count < pwd_len) {
      // 生成随机数，取绝对值，防止生成负数，
      i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
      if (i >= 0 && i < str.length) {
        pwd.append(str[i]);
        count++;
      }
    }
    return pwd.toString();
  }
}
