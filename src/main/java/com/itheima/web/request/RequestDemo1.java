package com.itheima.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/req1"})
public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求方式: `GET` String getMethod()
        System.out.println(req.getMethod());
        //获取虚拟目录(项目访问路径): `/request-demo`   String getContextPath()
        System.out.println(req.getContextPath());
        //获取URL(统一资源定位符): `http://localhost:8080/request-demo/req1`     StringBuffer getRequestURL()    //对于StringBuffer类型的数据转换为String需要调用toString()方法实现
        System.out.println(req.getRequestURL());
        //获取URI(统一资源标识符): `/request-demo/req1`    String getRequestURI()
        System.out.println(req.getRequestURI());
        //获取请求参数(GET方式): `username=zhangsan&password=123`   String getQueryString()
        System.out.println(req.getQueryString());

        //-------------

        String agent = req.getHeader("user-agent");//获取对应的浏览器内核的版本, 获取请求头, 打印浏览器的版本信息
        System.out.println(agent);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取post 请求体: 请求参数, 由于表单中都是字符串, 所以在这里使用字符流接收数据
        //获取字符输入流
        BufferedReader bufferedReader = req.getReader();
        //读取数据
        String s = bufferedReader.readLine();
        System.out.println(s);//输出接收到的请求方发送过来的的请求体
    }
}
