
package com.itheima.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 请求转发, 将Demo4转发到Demo5
 */
@WebServlet(urlPatterns = {"/req4"})
public class RequestDemo4 extends HttpServlet {
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
        System.out.println("demo4被访问了");//当demo4中被访问时, 会执行下面的请求转发, 转发参数res和resp到指定的路径

        //存储数据
        req.setAttribute("msg", "hello");//在转发后将存储的数据也一并转发, 所以在Demo5中时可以获取到数据的

        //请求转发
        req.getRequestDispatcher("/req5").forward(req, resp);
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
