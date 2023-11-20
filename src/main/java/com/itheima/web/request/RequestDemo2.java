package com.itheima.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = {"/req2"})
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //GET逻辑请求
        System.out.println("调用了doGet方法...");

        //获取所有参数的Map集合
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> set = parameterMap.keySet();
        for (String key : set) {
            // username: 张三
            System.out.print(key+":");
            //通过key获取值value
            String[] strings = parameterMap.get(key);
            for (String value : strings) {
                System.out.print(value+" ");
            }

            System.out.println();
        }

        System.out.println("-------------------");

        //根据key获取所有的参数数组的参数值
        String[] strings = req.getParameterValues("hobby");
        for (String value : strings) {
            System.out.print(value+" ");
        }

        //根据key获取单个参数的值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //POST逻辑请求
        this.doGet(req, resp);

        /*//获取所有参数的Map集合
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> set = parameterMap.keySet();
        for (String key : set) {
            // username: 张三
            System.out.print(key+":");
            //通过key获取值value
            String[] strings = parameterMap.get(key);
            for (String value : strings) {
                System.out.print(value+" ");
            }

            System.out.println();
        }

        System.out.println("-------------------");

        //根据key获取所有的参数数组的参数值
        String[] strings = req.getParameterValues("hobby");
        for (String value : strings) {
            System.out.print(value+" ");
        }

        //根据key获取单个参数的值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);*/
    }
}
