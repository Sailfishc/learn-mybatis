package com.sailfish.mapper;

import com.sailfish.dto.SysRoleExtend;
import com.sailfish.model.SysPrivilege;
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


    /**
     * 这里需要注意几点：
     *      目前来说一个User有多个Role：
     *      例如： admin用户有两个角色：管理员和普通用户
     *      test有一个角色：普通用户
     *      但是为什么解析出来是2条数据，而不是3条或者1条？
     *      依靠的是userMapper中配置的baseUserMap配置的<id property="id" column="id"/>，
     *      如果将密码（数据库中有可能存多个相同值）就会出现错误的情况
     *      下一个案例就是错误的.
     *      处理逻辑：按照id，如果没有设置id，会对比字段，如果一直，会合并，否则不会，设置id后性能会好很多
     *       @throws Exception
     */
    @Test
    public void selectAllUserRoles() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUsers = mapper.selectAllUserRoles();

            for (SysUser sysUser : sysUsers) {
                System.out.println("用户数："+sysUsers.size());
                System.out.println("用户名："+sysUser.getUserName());
                List<SysRole> roleList = sysUser.getRoleList();
                for (SysRole role : roleList) {
                    System.out.println("角色名："+role.getRoleName());
                }
            }

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    /**
     * 用户数：1
      用户名：admin
     角色名：管理员
     角色名：普通用户
     角色名：普通用户
     * @throws Exception
     */
    @Test
    public void selectAllUserNoIdRoles() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUsers = mapper.selectAllUserNoIdRoles();

            for (SysUser sysUser : sysUsers) {
                System.out.println("用户数："+sysUsers.size());
                System.out.println("用户名："+sysUser.getUserName());
                List<SysRole> roleList = sysUser.getRoleList();
                for (SysRole role : roleList) {
                    System.out.println("角色名："+role.getRoleName());
                }
            }

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void selectAllUserRolesPrivileges() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUsers = mapper.selectAllUserRolesPrivileges();

            for (SysUser sysUser : sysUsers) {
                System.out.println("用户数："+sysUsers.size());
                System.out.println("用户名："+sysUser.getUserName());
                List<SysRole> roleList = sysUser.getRoleList();
                for (SysRole role : roleList) {
                    System.out.println("    角色名："+role.getRoleName());
                    List<SysPrivilege> privilegeList = role.getPrivilegeList();
                    for (SysPrivilege sysPrivilege : privilegeList) {
                        System.out.println("        资源名称："+sysPrivilege.getPrivilegeName());
                    }
                }
            }

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void selectAllUserRolesPrivilegesWithInner() throws Exception {
        SqlSession sqlSession = getSqlSession();
        try {

            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUsers = mapper.selectAllUserRolesPrivilegesWithInner(1);

            for (SysUser sysUser : sysUsers) {
                sysUser.toString();
                System.out.println("用户数："+sysUsers.size());
                System.out.println("用户名："+sysUser.getUserName());
                List<SysRole> roleList = sysUser.getRoleList();
                for (SysRole role : roleList) {
                    role.toString();
                    System.out.println("    角色名："+role.getRoleName());
                    List<SysPrivilege> privilegeList = role.getPrivilegeList();
                    for (SysPrivilege sysPrivilege : privilegeList) {
                        sysPrivilege.toString();
                        System.out.println("        资源名称："+sysPrivilege.getPrivilegeName());
                    }
                }
            }

        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
/*
用户数：1
        用户名：admin
            角色名：管理员
            资源名称：用户管理
            资源名称：系统日志
            资源名称：角色管理
        角色名：普通用户
            资源名称：人员维护
             资源名称：单位维护*/
