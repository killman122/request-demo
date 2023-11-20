package com.itheima.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String name = "张三";
        System.out.println(name);

        //URL编码
        String encode = URLEncoder.encode(name, "utf-8");
        System.out.println(encode);

        //URL解码--这里模拟tomcat所以不使用utf-8的方式进行解码
//        String decode = URLDecoder.decode(encode, "utf-8");
        String decode = URLDecoder.decode(encode, "ISO-8859-1");
        System.out.println(decode);

        //转换为字节数据
        byte[] bytes = decode.getBytes("ISO-8859-1");
        System.out.println(bytes);

        //将字节数组转换为字符串
        String s = new String(bytes);
        System.out.println(s);
    }
}
