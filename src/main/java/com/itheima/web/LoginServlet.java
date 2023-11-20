package com.itheima.web;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet(value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2. 调用Mybatis完成查询
        //2.1 获取sqlSessionFactory对象
//        String resource = "mybatis-config.xml";/*由于配置目录在resource根目录下所以对于配置文件直接导入即可不需要在创建目录文件*/
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过工具类的方式调用
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactoryUtils();

        //2.2 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);/*在参数中添加boolean类型变量为了自动提交事务*/
        //2.3 获取Mapper执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //2.4 获取执行结果, 调用方法
        User user = mapper.select(username, password);/*根据用户名和密码查询,直接通过mapper对象调用在UserMapper接口中定义的查询的方法*/
        //2.5 关闭资源,释放资源
        sqlSession.close();

        //设置编码格式和返回数据的响应格式
        response.setContentType("text/html;charset=utf-8");

        //获取对应响应数据的字符输出流,并设置对应的Content-type
        PrintWriter writer = response.getWriter();

        //3. 返回结果,判断user是否为null, 如果为null则表示登录失败, 反之登录成功
        if (user != null) {
            //登录成功,设置存储域并进行请求转发
//            request.setAttribute("user", user);
//            request.getRequestDispatcher("/success.jsp").forward(request, response);
            writer.write("登录成功,即将跳转");

            /*writer.write("<script language='javascript'>\n" +
                    "    setTimeout(\"location.href='http://www.baidu.com',3000\");\n" +
                    "</script>");*//*这里通过写入一个script标签设置location属性实现跳转, 但是理论上通过重定向的方式也可以实现,那样就不需要使用js实现跳转*/
            response.setStatus(302);
            response.setHeader("refresh","5;url=http://www.baidu.com");

        } else {
            //登录失败,设置存储域和消息也一并进行请求转发
//            request.setAttribute("msg", "用户名或密码错误");
//            request.getRequestDispatcher("/fail.jsp").forward(request, response);
            writer.write("登录失败,注册账号后再来看看");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
