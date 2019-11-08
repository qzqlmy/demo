package com.example.demo.controller;



import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class AES {
    //算法
    private static final String ALGORITHM  = "AES";
    //默认的加密算法
    private static final String CIPHER_ALGORITHM  = "AES/CBC/PKCS5Padding";
    // 编码
    private static final String ENCODING = "UTF-8";
    // 密匙
    private static final String KEY = "7E174BEFA9F62C846F61BD441DC41E62";
    // 偏移量
    private static final String OFFSET = "0102030405060708";

    /**
     * AES加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
        // 初始化cipher
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //转化成JAVA的密钥格式
        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("ASCII"), ALGORITHM);
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(data.getBytes(ENCODING));
        //此处使用BASE64做转码。
        String result = new BASE64Encoder().encode(encrypted);
        return result;
    }

    /**
     * AES解密
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("ASCII"), ALGORITHM);
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(OFFSET.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] buffer = new BASE64Decoder().decodeBuffer(data);
        byte[] encrypted = cipher.doFinal(buffer);
        //此处使用BASE64做转码。
        String result = new String(encrypted, ENCODING);
        return result;
    }
    private static void removeJceLimit()
    {
        //去除JCE加密限制，只限于Java1.8
        try {
            Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, false);



        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            ex.printStackTrace(System.err);
        }
    }
    public static void main(String[] args) {
        removeJceLimit();
        String s1 = "{staff_id=1, treeLevel=1, tree_id=888, sqlName=report.hdBranchChannelZnkdTime, ts=NzY2NTg4NTYyMDM1MTphYzJlNDIxNTIzMTI3YjJhZDJiMzI4NjI1OTIzODJlNTJkMTI4YjJiMzJlZDI1YzI=}";
        String str = "";
        try {
            str = encrypt(s1);
            System.out.println("111111:"+str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = "";
        try {
            result = decrypt("o8ymSFwYgYuZVJyLf40CQiyK/xto3u5QWeFSJ0I5jWRjgFIbI4q7wzLNzdy/1z9SwBQMJn+zzdrOOw/+6hjUCTC9giBE4HSU8qsW2PrPZGTxjOaAglQneGiGOaEzDZQIRkmxf5Hvf6oVuzh9a2MCXd4MHxdQb7IQLnzEPeidpto2/Ka0CT0cI5cMVxZW8tYTUsa7Zg+gnTxpuPzlg6PCiEhdwUUG0IEaagjCOflaW1wV2yoW7pxXbkwlpCu/4yv");
            System.out.println("222222:"+result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
