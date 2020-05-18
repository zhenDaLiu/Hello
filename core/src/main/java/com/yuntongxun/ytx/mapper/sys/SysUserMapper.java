package com.yuntongxun.ytx.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuntongxun.ytx.pojo.dto.rsp.sys.AdminsRspDto;
import com.yuntongxun.ytx.pojo.po.sys.SysUser;
import com.yuntongxun.ytx.pojo.po.sys.SysUserRole;
import com.yuntongxun.ytx.pojo.vo.sys.SysUserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户Mapper
 * @author tangxy
 * @date 2019-07-10
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户id清空角色信息
     * @param userId
     * @return
     */
    Integer clearRole(@Param(value = "user_id") Long userId);

    /**
     * 赋予用户角色列表
     * @param sysUserRoles
     * @return
     */
    Integer giveRole(@Param(value = "list") List<SysUserRole> sysUserRoles);

    /**
     * 查询用户列表
     * @param startRow
     * @param offsetLimit
     * @param username
     * @return
     */
    List<SysUserVo> selectPList(@Param(value = "start_row") Integer startRow, @Param(value = "offset_limit") Integer offsetLimit, @Param(value = "username") String username);

    /**
     * 查询用户列表数量
     * @param username
     * @return
     */
    Integer selectPCount(@Param(value = "username") String username);

    /**
     * 更新用户可用状态
     * @param id
     * @param deleteStatus
     * @return
     */
    Integer updateStatusById(@Param(value = "id") Long id, @Param(value = "delete_status") Integer deleteStatus);

    /**
     * 修改用户密码
     * @param id
     * @param password
     * @return
     */
    Integer updatePasswordById(@Param(value = "id") Long id, @Param(value = "password") String password);

    /**
     * 根据id修改密码、盐值
     * @param id
     * @param password
     * @param salt
     * @return
     */
    Integer updatePasswordAndSaltById(@Param(value = "id") Long id, @Param(value = "password") String password, @Param(value = "salt") String salt);

    /**
     * 获取管理员列表
     * @param keyword
     * @param deleteStatus
     * @param platforms
     * @param industryList
     * @return
     */
    List<AdminsRspDto> getAdminList(@Param("keyword") String keyword, @Param("deleteStatus") Integer deleteStatus,
                                    @Param("platforms") List<Integer> platforms,
                                    @Param("industryList") List<Integer> industryList);
}