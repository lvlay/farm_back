package com.taoroot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: taoroot
 * @date: 2017/11/19
 * @description: MD5加密工具类
 */
public class MD5Util {


    /**
     * md5加密
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            // 32位加密
            // return buf.toString().toLowerCase();
            // 16位的加密
            return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}