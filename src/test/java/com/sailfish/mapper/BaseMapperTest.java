package com.sailfish.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.Reader;

/**
 * @author sailfish
 * @create 2017-08-05-下午6:08
 */
public class BaseMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader read = Resources.getResourceAsReader("mybatis-config.xml");
//            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//            sqlSessionFactory = builder.build(read);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(read);
            read.close();
        } catch (Exception e) {

        }
    }

    protected SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
