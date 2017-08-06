package com.sailfish.mapper;

import com.sailfish.model.SysPrivilege;

import java.util.List;

/**
 * @author sailfish
 * @create 2017-08-05-下午5:29
 */
public interface PrivilegeMapper {

    List<SysPrivilege> selectPrivilegeListByRoleId(Integer roleId);
}
