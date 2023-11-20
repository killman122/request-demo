package com.itheima.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private SqlSessionFactoryUtils() {
    }

    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        //静态代码块会随着类的加载而自动执行且只执行一次
        try {
            //2.1 获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";/*由于配置目录在resource根目录下所以对于配置文件直接导入即可不需要在创建目录文件*/
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//需要对这里对象进行提权
            SqlSessionFactoryUtils.sqlSessionFactory = sqlSessionFactory;//这里使用this的方式会爆出static访问非static变量的异常,所以直接通过 类名.成员变量 的方式调用
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactoryUtils(){//再下面的getSqlSessionFactoryUtils方法中调用上面的静态代码块中的局部变量sqlSessionFactory便于工具类的调用
        return sqlSessionFactory;
    }
}
