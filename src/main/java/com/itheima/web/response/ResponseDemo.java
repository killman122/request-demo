package com.itheima.web.response;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/resp1")
public class ResponseDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("resp1....");

        //重定向的方式第一步: 设置响应状态码
        //response.setStatus(302);
        //重定向的方式第二步: 设置响应头
        //response.setHeader("location", "/request-demo/resp2");//location无论大小写都是可以运行的, 但是似乎需要全部大写或者是全部小写

        //简化方式完成重定向
        response.sendRedirect("/request-demo/resp2");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
