package com.example.demo.controller;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.sun.xml.internal.messaging.saaj.util.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class CipherCode{
    //CipherCode.encryptMD5("_lda@tydic#chinatelecom!qh*971");
    private static final String DEFAULT_AES_KEY="7E174BEFA9F62C846F61BD441DC41E62";
    //======================================================RSA算法======================================================
    //RSA公钥/私钥对生成
    private static KeyPair generateRSAKeyPair(String rsaCode){
        KeyPair kpRSA = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(rsaCode.getBytes());
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
            generator.initialize(256,secureRandom);
            kpRSA = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return kpRSA;

    }
    //RSA解密
    public static String decryptRSA(String encryptText,String rsaCode) {
        String decryptStr="";
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA");
            //cipher.init(Cipher.DECRYPT_MODE, (RSAPrivateKey) keyPair.getPrivate());
            cipher.init(Cipher.DECRYPT_MODE, (RSAPrivateKey)generateRSAKeyPair(rsaCode).getPrivate());
            byte[] text = cipher.doFinal(parseHexStrToByte(encryptText));
            decryptStr=new String(text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return decryptStr;
    }
    //RSA加密
    public static String encryptRSA(String text,String rsaCode) {
        String encryptStr="";
        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, (RSAPublicKey)generateRSAKeyPair(rsaCode).getPublic());
            byte[] result = cipher.doFinal(text.getBytes("UTF-8"));
            encryptStr=parseByteToHexStr(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return encryptStr;
    }
    //======================================================DES算法======================================================
    public static String encryptAES(String text){
        return encryptAES(text,DEFAULT_AES_KEY);
    }
    //DES对加密
    public static String encryptAES(String text,String desCode){
        String encryptStr="";
        try {
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //初始化
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(desCode.getBytes("UTF-8"),"AES"));
            byte[] result = cipher.doFinal(text.getBytes("UTF-8"));
            encryptStr= new BASE64Encoder().encode(result);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptStr;
    }

    public static void main(String[] args) {
        String text="{\"staff_id\":\"1\",\"treeLevel\":\"1\",\"tree_id\":\"888\",\"sqlName\":\"report.hdBranchChannelZnkdTime\",\"ts\":\"NzY2NTg4NTYyMDM1MTphYzJlNDIxNTIzMTI3YjJhZDJiMzI4NjI1OTIzODJlNTJkMTI4YjJiMzJlZDI1YzI=\"}";
        System.out.println(encryptAES(text,DEFAULT_AES_KEY));
    }

    //DES解密
    public static String decryptAES(String text){
        return decryptAES(text,DEFAULT_AES_KEY);
    }
    public static String decryptAES(String encryptText,String desCode){
        String decryptStr="";
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(desCode.getBytes("UTF-8"),"AES"));//初始化
            byte[] result = cipher.doFinal(new BASE64Decoder().decodeBuffer(encryptText));
            decryptStr=new String(result);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return decryptStr;
    }

    //======================================================MD5算法======================================================
    //MD5加密 32位
    public static String encryptMD5(String text){
        String encryptStr="";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(text.getBytes("UTF-8"));
            encryptStr = parseByteToHexStr(md5Bytes);
        } catch (Exception e){
            e.printStackTrace();
        }

        return encryptStr;
    }
    //MD5加密 32位
    public static String multipleMD5(String text,int reCount,int offset){
        String encryptStr=text;
        StringBuilder sb=new StringBuilder();
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            for(int c=0;c<reCount;c++){
                byte[] digest=messageDigest.digest(encryptStr.getBytes("UTF-8"));
                for(int i=0;i<digest.length;i++){
                    int result = digest[i]&0xff;
                    String hexString = Integer.toHexString(result)+offset;
                    if(hexString.length()<2){
                        sb.append("0");
                    }
                    sb.append(hexString);
                }
                encryptStr = sb.toString();
                sb.delete(0, sb.length());
            }
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encryptStr;
    }
    //base64编码
    public static String bsEncode(String str){
        try {
            return new String(Base64.encode(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    //base64解码
    public static String bsDecode(String str){
        return Base64.base64Decode(str);
    }
    //======================================================工具方法======================================================
    //将二进制转换16进制显示 
    public static String parseByteToHexStr(byte bt[]){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<bt.length;i++){
            String hex=Integer.toHexString(bt[i]&0xFF);
            if(hex.length()==1){
                hex='0'+hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    //将16进制转换为二进制数组
    public static byte[] parseHexStrToByte(String str){
        if(str.length()<1)
            return null;
        byte[] result=new byte[str.length()/2];
        for(int i=0;i<str.length()/2;i++){
            int high=Integer.parseInt(str.substring(i*2, i*2+1), 16);
            int low=Integer.parseInt(str.substring(i*2+1, i*2+2), 16);
            result[i]=(byte)(high*16+low);
        }
        return result;
    }
}

