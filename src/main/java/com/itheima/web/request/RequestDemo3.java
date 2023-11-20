package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

/**
 * 中文乱码的解决方案
 */
@WebServlet(urlPatterns = {"/req3"})
public class RequestDemo3 extends HttpServlet {
    /**
     * GET请求获取参数的方式默认是: getQueryString
     * 乱码原因: tomcat读取流的编码ISO-8859-1不支持中文所以导致输出乱玛
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 获取username
        String username = req.getParameter("username");
        System.out.println("解决乱码前"+username);//当在网页传入的表单中的数据为中文时会出现乱码
        //转换为字节数据
//        byte[] bytes = username.getBytes("ISO-8859-1");
        byte[] bytes = username.getBytes(StandardCharsets.ISO_8859_1);
        //将字节数组转换为字符串, 主要是使用new String的构造器
//        String s = new String(bytes);
        String s = new String(bytes,StandardCharsets.UTF_8);
        System.out.println("解决乱码后"+s);
    }


    /**
     * POST方法底层通过getReader()方法获得一个字符输入流, 通过流的方式读取数据, 但是tomcat读取流的编码不支持中文所以导致输出乱玛
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //POST请求解决乱码:
        //1. 设置字符集
        req.setCharacterEncoding("UTF-8");
        //2. 获取参数
        String username = req.getParameter("username");
        System.out.println(username);

    }
}
