package com.sailfish.proxy;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author sailfish
 * @create 2017-08-05-下午9:00
 */
public class MyMapperProxy<T> implements InvocationHandler {

    private Class<T> mapperInterface;
    private SqlSession sqlSession;

    public MyMapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<T> result = sqlSession.selectList(
                mapperInterface.getCanonicalName() + "." + method.getName()
        );

        return result;
    }
}
