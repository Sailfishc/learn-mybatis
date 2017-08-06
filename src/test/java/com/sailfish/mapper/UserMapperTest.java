package com.sailfish.mapper;

import com.sailfish.dto.SysRoleExtend;
import com.sailfish.model.SysRole;
import com.sailfish.model.SysUser;
import com.sailfish.type.Enabled;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author sailfish
 * @create 2017-08-05-下午5:54
 */
public class UserMapperTest extends BaseMapperTest {


    @Test
    public void selectById() throws Exception {
        SqlSession sqlSession = getSqlSession();
        SysUser user = sqlSession.selectOne("com.sailfish.mapper.UserMapper.selectById", 1);
        System.out.println(user.toString());
        sqlSession.close();
    }

    /**
     * 更加的面向对象
     */
    @Test
    public void selectById2() throws Exception {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        SysUser sysUser = mapper.selectById(1001);
        System.out.println(sysUser.toString());
        sqlSession.close();
    }

    @Test
    public void selectRolesByUserId() throws Exception {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<SysRole> sysRoles = mapper.selectRolesByUserId(1);
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole.toString());
        }

    }


    /**
     * 对selectRolesByUserId方式的扩展方式一
     */
    @Test
    public void selectRolesByUserId2() throws Exception {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<SysRoleExtend> sysRoles = mapper.selectRolesByUserId2(1);
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole.toString());
        }

    }

    /**
     * 对selectRolesByUserId方式的扩展方式一
     */
    @Test
    public void selectRolesByUserId3() throws Exception {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<SysRole> sysRoles = mapper.selectRolesByUserId3(1);
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole.toString());
        }

    }


    @Test
    public void insert() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUser user = createUser();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int insert = mapper.insert(user);  //默认是不提交的，暂时还存在session中  autoCommit=false
            System.out.println(insert);


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }

    }


    @Test
    public void insert2() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUser user = createUser();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //这里输出的还是印象的行数
            int insert = mapper.insert2(user);  //默认是不提交的，暂时还存在session中  autoCommit=false
            System.out.println(user.getId());  //1039   //1040


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void insert3() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUser user = createUser();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //这里输出的还是印象的行数
            int insert = mapper.insert2(user);  //默认是不提交的，暂时还存在session中  autoCommit=false
            System.out.println(user.getId());  //1041


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    private SysUser createUser(){
        SysUser user = new SysUser();
        user.setUserName("张三");
        user.setUserPassword("123456");
        user.setUserEmail("hah@qq.com");
        user.setUserInfo("ok");
        byte[] bytes = {1, 2, 3};
        user.setHeadImg(bytes);
        user.setCreateTime(new Date());
        return user;
    }



    //出错
    @Test
    public void selectRolesByUserIdAndEnabled() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRoles = mapper.selectRolesByUserIdAndEnabled(1, 1);


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectRolesByUserIdAndEnabled2() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRoles = mapper.selectRolesByUserIdAndEnabled2(1, 1);


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectRolesByUserIdAndEnabled3() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRoles = mapper.selectRolesByUserIdAndEnabled3(1, 1);


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectRolesWithBean() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUser user = new SysUser();
            user.setId(1L);
            SysRole role = new SysRole();
            role.setEnabled(Enabled.enabled);

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysRoles = mapper.selectRolesWithBean(user, role);


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

}
