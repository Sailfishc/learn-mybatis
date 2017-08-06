package com.sailfish.mapper;

import com.sailfish.model.SysRole;
import com.sailfish.model.SysUser;
import com.sailfish.type.Enabled;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * 如果mapper-config.xml写错或者是mapper写错在获取sqlsesionFactory时会获取为空：NullPointerException
 *
 * @author sailfish
 * @create 2017-08-06-上午9:42
 */

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void selectByCondition() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoles = mapper.selectByCondition(null, 1);
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectByCondition2() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoles = mapper.selectByCondition2(null, 1);
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectUseChoose() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
//            role.setId(1L);
            // SELECT * FROM sys_role WHERE 1 = 1 -- 这里给定一个排除条件，如果前面的都不成立，会查询出所有的，这里的条件就是让返回结果返回null and 1 = 2
            List<SysRole> sysRoles = mapper.selectUseChoose(role);  //
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void selectByWhere() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
//            role.setId(1L);
            role.setRoleName("管理");
            List<SysRole> sysRoles = mapper.selectByWhere(role);  //
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void updateByCondition() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setId(1L);
            try {
                int result = mapper.updateByCondition(role);  //UPDATE SYS_ROLE SET  where id = ?  SQL出错
                System.out.println(result);
            } catch (Exception e) {
//                e.printStackTrace();
            }

            role.setRoleName("管理");
            int i = mapper.updateByCondition(role);   //UPDATE SYS_ROLE  SET  role_name = ?,  where  id = ? SQL出错
            System.out.println(i);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void updateByCondition2() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setId(1L);
            int result = mapper.updateByCondition2(role);  //UPDATE SYS_ROLE SET id = ? where id = ?
            System.out.println(result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void updateByCondition3() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = new SysRole();
            role.setId(1L);
            int result = mapper.updateByCondition3(role);  //UPDATE SYS_ROLE SET id = ? where id = ?
            System.out.println(result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectByList() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);

            List<SysRole> sysRoles = mapper.selectByList(list);  //SELECT * FROM sys_role WHERE id IN ( ? , ? )
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectByList2() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);

            List<SysRole> sysRoles = mapper.selectByList2(list);  //SELECT * FROM sys_role WHERE id IN ( ? , ? )
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void selectBySet() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            Set<Integer> set = new HashSet<>();
            set.add(1);
            set.add(2);

            List<SysRole> sysRoles = mapper.selectBySet(set);  //SELECT * FROM sys_role WHERE id IN ( ? , ? )
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void selectByArray() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            Integer[] arrays = {1, 2};

            List<SysRole> sysRoles = mapper.selectByArray(arrays);  //SELECT * FROM sys_role WHERE id IN ( ? , ? )
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    /**
     * // TODO: 2017/8/6 这种目前不知道怎么搞
     */
    @Test
    public void selectByMap() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            Map<String, List<Integer>> map = new HashMap<>();
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            map.put("key1", list);
            List<SysRole> sysRoles = mapper.selectByMap(map);
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectByMap2() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            Map<String, String> map = new HashMap<>();
            map.put("key1", "1");
            map.put("key2", "2");
            List<SysRole> sysRoles = mapper.selectByMap2(map);  //SELECT * FROM sys_role WHERE id IN ( ? , ? )
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.toString());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void batchInsertRole() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roles = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                SysRole role = new SysRole();
                role.setRoleName("哈哈");
                role.setEnabled(Enabled.enabled);
                role.setCreateBy("1");
                role.setCreateTime(new Date());
                roles.add(role);
            }
            //INSERT INTO sys_role( role_name, enabled, create_by, create_time ) VALUES (?, ?, ?, ?) , (?, ?, ?, ?) , (?, ?, ?, ?)
            int result = mapper.batchInsertRole(roles);
            //可以批量返回主键
            for (SysRole role : roles) {
                System.out.println(role.getId());
            }
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void updateByMap() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1L);

            map.put("role_name", "厂子");


            int result = mapper.updateByMap(map);

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }




    @Test
    public void selectUserAndRoleById() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);


            List<SysUser> sysUsers = mapper.selectUserAndRoleById(1);
            for (SysUser sysUser : sysUsers) {
                System.out.println(sysUser.toString());
            }

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void selectUserAndRoleById2() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);


            List<SysUser> sysUsers = mapper.selectUserAndRoleById2(1);
            for (SysUser sysUser : sysUsers) {
                System.out.println(sysUser.toString());
            }

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void selectUserAndRoleById3() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);


            List<SysUser> sysUsers = mapper.selectUserAndRoleById3(1);
            for (SysUser sysUser : sysUsers) {
                System.out.println(sysUser.toString());
            }

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectUserAndRoleByIdSelect() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);


            List<SysUser> sysUsers = mapper.selectUserAndRoleByIdSelect(1001);
            for (SysUser sysUser : sysUsers) {
//                这里如果调用了toString方法懒加载就不会生效
//                System.out.println(sysUser.toString());
            }

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void selectRoleById() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);


            SysRole role = mapper.selectRoleById(1);

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    //根据用户id查询角色列表
    @Test
    public void selectRolesByUserId() throws Exception {
        SqlSession sqlSession = getSqlSession();

        try {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);


            List<SysRole> role = mapper.selectRolesByUserId(1);

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}