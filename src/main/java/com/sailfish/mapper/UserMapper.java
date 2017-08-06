package com.sailfish.mapper;

import com.sailfish.dto.SysRoleExtend;
import com.sailfish.model.SysRole;
import com.sailfish.model.SysUser;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sailfish
 * @create 2017-08-05-下午5:29
 */
public interface UserMapper {

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    SysUser selectById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 查询用户下的所有角色
     * @param id
     * @return
     */
    List<SysRole> selectRolesByUserId(Integer id);

    /**
     * 针对上一个方法的扩展方式一
     * @param id
     * @return
     */
    List<SysRoleExtend> selectRolesByUserId2(Integer id);

    /**
     * selectRolesByUserId3
     * 针对上一个方法的扩展方式二
     * @param id
     * @return
     */
    List<SysRole> selectRolesByUserId3(Integer id);

    int insert(SysUser user);
    int insert2(SysUser user);
    int insert3(SysUser user);

    /**
     * 多查询条件
     * org.apache.ibatis.binding.BindingException: Parameter 'id' not found. Available parameters are [arg1, arg0, param1, param2]
     * @param id
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndEnabled(Integer id, Integer enabled);

    List<SysRole> selectRolesByUserIdAndEnabled2(Integer id, Integer enabled);
    List<SysRole> selectRolesByUserIdAndEnabled3(@Param("id") Integer id, @Param("enabled") Integer enabled);

    /**
     * 使用JavaBean
     * @param id
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesWithBean(@Param("user") SysUser user, @Param("role") SysRole role);


    //查询一
    List<SysUser> selectUserAndRoleById(Integer id);
    List<SysUser> selectUserAndRoleById2(Integer id);
    List<SysUser> selectUserAndRoleById3(Integer id);


    List<SysUser> selectUserAndRoleByIdSelect(Integer id);

    List<SysUser> selectAllUserRoles();
    List<SysUser> selectAllUserNoIdRoles();
    List<SysUser> selectAllUserRolesPrivileges();
    List<SysUser> selectAllUserRolesPrivilegesWithInner(Integer id);

}
