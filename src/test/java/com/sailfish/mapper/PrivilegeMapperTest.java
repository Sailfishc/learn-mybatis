package com.sailfish.mapper;

import com.sailfish.model.SysPrivilege;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sailfish
 * @create 2017-08-06-下午8:25
 */
public class PrivilegeMapperTest extends BaseMapperTest{
    
    
    @Test
    public void selectPrivilegeListByRoleId() throws Exception {

        SqlSession sqlSession = getSqlSession();
        try {
            PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
            mapper.selectPrivilegeListByRoleId(1);

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
                
    }

}