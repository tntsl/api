package com.demo.api.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Lye
 */
public class MD5Utils {
private MD5Utils(){}
    public static String getMD5(String sourceStr) {
        StringBuilder resultStr = new StringBuilder();
        try {
            byte[] temp = sourceStr.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(temp);
            byte[] b = md5.digest();
            for (int i = 0; i < b.length; i++) {
                char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] ob = new char[2];
                ob[0] = digit[(b[i] >>> 4) & 0X0F];
                ob[1] = digit[b[i] & 0X0F];

                resultStr.append(new String(ob));
            }
            return resultStr.toString();
        } catch (NoSuchAlgorithmException e) {
            return "1";
        }
    }
}
