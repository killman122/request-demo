package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.jws.soap.SOAPBinding;

/**
 * Mapper接口一般情况下用来编写抽象方法,pojo包下是数据库中数据类的映射
 * UserMapper.xml和UserMapper.java是具有联系的组合,xml中编写对应于Mapper的sql语句
 */
public interface UserMapper {
    /**
     * 由于sql语句较少也较简单, 所以使用注解的方式
     *     该方法为了进行用户登录的操作, 根据用户名和密码查询用户对象, 当数据库中存在当前的用户对象的时候, 会创建一个User的用户实体
     * @param username
     * @param password
     * @return
     */
    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User select(@Param("username") String username, @Param("password") String password);//使用MybatisCodeHelper需要添加注解, 否则使用select语句的时候报错

    /**
     * 根据用户名查询用户对象
     * 由于是一个简单的基础查询方法, 所以可以直接通过注解的方式进行数据库中的表的查询, 并且由于传入的参数只有一个所以不需要使用注解进行参数的绑定, 但是如果使用参数的绑定后这里的Mapper需要和xml进行绑定
     * @param username
     * @return
     */
    @Select("select * from tb_user where username = #{username}")
    User selectByUsername(String username);

    /**
     * 如果有多个参数,其中的一个参数是自增的,但是为了方便所以不对表中需要填写的参数进行限定,所以需要使用null对应自增的数据库中的属性,
     *     另外不想使用大量注解就使用对象的方式或者使用Map集合的方式传入参数都可以
 *     添加用户
     * @param user
     */
    @Select("insert into tb_user values (null,#{username}, #{password})")
    void add(User user);
}
