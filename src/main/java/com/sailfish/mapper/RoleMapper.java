package com.sailfish.mapper;

import com.sailfish.model.SysRole;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sailfish
 * @create 2017-08-05-下午5:29
 */
public interface RoleMapper {

    /**
     * 按照条件查询
     * @param userName
     * @param userEmail
     * @return
     */
    List<SysRole> selectByCondition(@Param("roleName") String roleName, @Param("enabled") Integer enabled);

    List<SysRole> selectByCondition2(@Param("roleName") String roleName, @Param("enabled") Integer enabled);


    int updateByCondition(SysRole sysRole);
    int updateByCondition2(SysRole sysRole);
    int updateByCondition3(SysRole sysRole);

    List<SysRole> selectUseChoose(SysRole role);

    /**
     * 使用where标签
     * @param role
     * @return
     */
    List<SysRole> selectByWhere(SysRole role);

    /**
     * foreach查询
     * @param ids
     * @return
     */
    List<SysRole> selectByList(List<Integer> ids);
    //这里指定了mapper中collection的名称
    List<SysRole> selectByList2(@Param("ids") List<Integer> ids);

    List<SysRole> selectBySet(Set<Integer> ids);

    /**
     * 使用Array查询
     * @param arrays
     * @return
     */
    List<SysRole> selectByArray(Integer[] arrays);

    List<SysRole> selectByMap(@Param("map") Map<String, List<Integer>> map);
    List<SysRole> selectByMap2(@Param("map") Map<String, String> map);

    int batchInsertRole(List<SysRole> roles);

    /**
     * 使用Map动态更新
     * @param map
     * @return
     */
    int updateByMap( Map<String, Object> map);
}
