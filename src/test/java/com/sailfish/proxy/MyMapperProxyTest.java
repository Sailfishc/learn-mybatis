package com.sailfish.proxy;

import com.sailfish.mapper.BaseMapperTest;
import com.sailfish.mapper.UserMapper;
import com.sailfish.model.SysUser;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sailfish
 * @create 2017-08-05-下午9:04
 */
public class MyMapperProxyTest extends BaseMapperTest{


    @Test
    public void invoke() throws Exception {

        SqlSession sqlSession = getSqlSession();
        MyMapperProxy userMapperProxy = new MyMapperProxy(UserMapper.class, sqlSession);

        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{UserMapper.class},
                userMapperProxy
        );

        //NoSuchMethodException
        List<SysUser> sysUsers = userMapper.selectAll();
        for (SysUser sysUser : sysUsers) {
            System.out.println(sysUser.toString());
        }
    }

}