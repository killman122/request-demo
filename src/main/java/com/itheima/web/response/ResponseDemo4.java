package com.itheima.web.response;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 响应字节数据: 设置字节数据的响应体
 */
@WebServlet(value = "/resp4")
public class ResponseDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建一个文件输入流
        //读取文件
        FileInputStream inputStream = new FileInputStream("D:\\quiet.jpg");

        //获取response的字节输出流
        ServletOutputStream outputStream = response.getOutputStream();

        //将读取文件写入response的字节输出流中完成对流的copy操作
//        byte[] bytes = new byte[1024];
//        int len = 0;
//        while ((len = inputStream.read(bytes)) != -1) {
//            outputStream.write(bytes, 0, len);
//        }
//        inputStream.close();//字节输入流需要关闭因为是自己通过new方法创建出的实例,而输出流是通过response得到的,当关闭response后自动关闭,所以不需要手动将输出流关闭

        //TODO 通过commons-io工具类的mavenjar包进行流之间的拷贝关闭
        // 通过commons-io工具类的mavenjar包关闭流
        IOUtils.copy(inputStream, outputStream);
        inputStream.close();
//        IOUtils.closeQuietly(inputStream);
//        IOUtils.closeQuietly(outputStream);
//        outputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
