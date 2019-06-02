package com.gz.lss.util.security;

import com.gz.lss.pojo.Tb_worker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * @ClassName PasswordHelper
 * @Author Y
 * @Date 2019/5/20 22:09
 * @Description TODO
 */
public class PasswordHelper {
    public static String getPasswordDigest(String password, String secret_key){
        if (password == null || secret_key == null) {
            return null;
        }

        String result = null;
        try {
            MessageDigest sha2 = MessageDigest.getInstance("SHA-256");
            sha2.update((secret_key+password).getBytes());
            byte[] digest = sha2.digest();
            result = Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getSecretKey(int keyLength){
        String base = "!@#$%&*()_+-=?abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" ;
        Random random = new Random();
        StringBuffer keySb = new StringBuffer();
        for(int i = 0; i<keyLength; i++){ //生成指定位数的随机秘钥字符串
            int number = random.nextInt(base.length());
            keySb.append(base.charAt(number));
        }
        return keySb.toString();
    }
}
