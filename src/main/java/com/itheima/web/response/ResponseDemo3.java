package com.itheima.web.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应字符数据: 设置字符数据的响应体
 */
@WebServlet(value = "/resp3")
public class ResponseDemo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的头部信息content-type
//        response.setHeader("content-type", "text/html;charset=UTF-8");//通过setHeader的方式直接设置content-type是也可以实现中文解码,当然也可以通过setContentType()的方式实现设置中文浏览器使用中文解码
        response.setContentType("text/html;charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");//通过设置字符集解码方式设置为utf-8的解码是无法得到中文得显示

        //获取对应的字符输出流, 似乎是通过response对象的getWriter()方法获取的,直接通过获取到的字符输出流write调用write()方法实现向页面中写入数据
        PrintWriter writer = response.getWriter();
        //设置头部信息的简化形式,也能够设置中文编码

        writer.write("resp3....");
        writer.write("<h1>哈哈哈,豆类老谋</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
