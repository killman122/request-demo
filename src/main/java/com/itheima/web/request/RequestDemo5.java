
package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发
 */
@WebServlet(urlPatterns = {"/req5"})
public class RequestDemo5 extends HttpServlet {
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
        System.out.println("Demo5被访问了");

        //获取数据
        Object msg = req.getAttribute("msg");
        System.out.println(msg);
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
    }
}
