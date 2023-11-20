package com.itheima;

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

@WebServlet(value = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收用户的数据
        // 接收用户的数据
        // 获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //封装用户对象,将获取到的参数封装入一个用户对象中, 主要使用set方法
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //2. 调用Mybatis完成查询
        //2.1 获取sqlSessionFactory对象
//        String resource = "mybatis-config.xml";/*由于配置目录在resource根目录下所以对于配置文件直接导入即可不需要在创建目录文件*/
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过工具类的方式调用
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactoryUtils();

        //2.2 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);/*在参数中添加boolean类型变量为了自动提交事务,否则需要手动调用sqlSession.commit()提交事务*/

        //2.3 获取Mapper执行sql
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //2.4 调用方法,获取执行结果
        User user1 = mapper.selectByUsername(username);


        //3. 返回结果,判断用户对象是否存在, 如果存在则不在创建,反之则创建新的对象到数据库中
        //设置编码格式和返回数据的响应格式
        response.setContentType("text/html;charset=utf-8");
        if (user1 != null) {//用户名已存在
            response.getWriter().write("<script>alert('用户名已存在,注册失败,2s后即将跳转登录页面');</script>");
//            response.setStatus(302);
//            response.setHeader("refresh","3;url=/request-demo/login.html");
            response.getWriter().write("<script language='javascript'>\n" + "setTimeout(\"location.href='/request-demo/login.html',2000\");\n" + "</script>");//*这里通过写入一个script标签设置location属性实现跳转, 但是理论上通过重定向的方式也可以实现,那样就不需要使用js实现跳转*/
        } else {//用户名不存在,通过mapper对象调用UserMapper接口中的抽象方法向数据库中添加用户数据
            mapper.add(user);
            response.getWriter().write("注册成功,三秒后跳转到登录页面");
//            response.sendRedirect("/request-demo/login.html");不带延迟的重定向
//            response.setStatus(302);
            response.setHeader("refresh", "3;url=login.html");//带有延迟的重定向,再使用setHeader的方式添加请求路径的是否不能设置虚拟路径, 直接调用webapp目录下的文件路径即可
        }

        //2.5 关闭资源,释放资源,不能提前关闭sqlSession, 如果提前关闭会爆出500的服务器内部错误, 因为在关闭后则无法再执行SQL语句, 并且mapper对象是由于sqlSession的getMapper()方法获取的,自然也就无法使用mapper对象
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
