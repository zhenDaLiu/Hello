package yuntongxun.admin.service.sys;


import com.github.pagehelper.PageInfo;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqIdDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqMenuDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqMenuPageDto;
import com.yuntongxun.ytx.pojo.dto.rsp.sys.SysMenuRspDto;
import com.yuntongxun.ytx.pojo.po.sys.SysMenu;
import com.yuntongxun.ytx.pojo.vo.sys.Tree;

import java.util.List;
import java.util.Map;

public interface ISysMenuService {


    PageInfo<SysMenu> selectPage(ReqMenuPageDto reqMenuPageDto);

    Integer add(ReqMenuDto reqMenuDto);

    Integer del(ReqIdDto reqIdDto);

    /**
     * 查询全部菜单列表
     * @param deleteStatus
     * @return
     */
    List<SysMenu> findAll(Integer deleteStatus);

    Tree<SysMenu> loadMenuTree();

    Map<String, Object> loadMenuTree2();


    /**
     * 获取用户可显示菜单列表
     * @return
     */
    List<SysMenuRspDto> selectUserMenus();

    /**
     * 获取用户可显示菜单树形结构列表
     * @return
     */
    Tree<SysMenuRspDto> selectUserMenuTree();

    /**
     * 获取当前角色已选择的菜单列表
     * @param roleId
     * @return
     */
    List<SysMenuRspDto> getRoleMenus(Long roleId);
}
