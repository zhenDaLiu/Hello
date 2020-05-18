package com.yuntongxun.ytx.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuntongxun.ytx.pojo.po.sys.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单Mapper
 * @author tangxy
 * @date 2019-07-10
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据菜单名称获取菜单列表
     * @param menuName
     * @return
     */
    List<SysMenu> selectPage(@Param(value = "menu_name") String menuName);

    /**
     * 根据菜单编码查询
     * @param menuCode
     * @return
     */
    SysMenu selectOneByMenuCode(@Param(value = "menu_code") String menuCode);

    /**
     * 根据菜单名称查询
     * @param menuName
     * @return
     */
    SysMenu selectOneByMenuName(@Param(value = "menu_name") String menuName);

    /**
     * 查询全部菜单
     * @param deleteStatus
     * @return
     */
    List<SysMenu> findAll(@Param("deleteStatus") Integer deleteStatus);
}