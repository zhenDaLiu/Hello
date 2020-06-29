package com.yuntongxun.admin.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yuntongxun.ytx.constants.YtxConstants;
import com.yuntongxun.ytx.mapper.sys.SysMenuMapper;
import com.yuntongxun.ytx.mapper.sys.YtxSysRoleMenuMapper;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqIdDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqMenuDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqMenuPageDto;
import com.yuntongxun.ytx.pojo.dto.rsp.sys.SysMenuRspDto;
import com.yuntongxun.ytx.pojo.po.sys.SysMenu;
import com.yuntongxun.ytx.pojo.po.sys.SysRole;
import com.yuntongxun.ytx.pojo.vo.sys.Tree;
import com.yuntongxun.ytx.utils.ParamsUtil;
import com.yuntongxun.ytx.utils.TreeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yuntongxun.admin.service.sys.ISysMenuService;
import com.yuntongxun.admin.service.sys.ISysRoleService;
import com.yuntongxun.ytx.fast.exception.BusinessException;
import com.yuntongxun.ytx.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl implements ISysMenuService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SysMenuMapper sysMenuMapper;

	@Autowired
	YtxSysRoleMenuMapper roleMenuMapper;

	@Autowired
	ISysRoleService roleService;
	
	@Override
	public PageInfo<SysMenu> selectPage(ReqMenuPageDto reqMenuPageDto) {

		// 启动分页
		PageHelper.startPage(reqMenuPageDto.getPageNum(), reqMenuPageDto.getPageSize(), true);
		// 查询结果集
		List<SysMenu> sysMenus = sysMenuMapper.selectPage(ParamsUtil.joinLike(reqMenuPageDto.getMenuName()));
		// 封装pageInfo
		PageInfo<SysMenu> pageInfo = new PageInfo<>(sysMenus);

		return pageInfo;
	}

	@Override
	public Integer add(ReqMenuDto reqMenuDto) {

		SysMenu sysMenu = sysMenuMapper.selectOneByMenuCode(reqMenuDto.getMenuCode());
		if(null != sysMenu) {
			throw new BusinessException("菜单代码已存在");
		}

		sysMenu = sysMenuMapper.selectOneByMenuName(reqMenuDto.getMenuName());
		if(null != sysMenu) {
			throw new BusinessException("菜单名称已存在");
		}

		if(null != reqMenuDto.getParentId()) {
			sysMenu = sysMenuMapper.selectById(reqMenuDto.getParentId());
			if(null == sysMenu) {
				throw new BusinessException("父级菜单不存在");
			}
		}
		if(null == reqMenuDto.getSortNum() || 0 >= reqMenuDto.getSortNum()){
			throw new BusinessException("排序号不合法");
		}
		sysMenu = new SysMenu();
		BeanUtils.copyProperties(reqMenuDto,sysMenu);
		Date nowDate = new Date();
		sysMenu.setCreateTime(nowDate);
		sysMenu.setUpdateTime(nowDate);
		return sysMenuMapper.insert(sysMenu);
	}

	@Override
	public Integer del(ReqIdDto reqIdDto) {
		return sysMenuMapper.deleteById(reqIdDto.getId());
	}

	@Override
	public List<SysMenu> findAll(Integer deleteStatus) {
		return sysMenuMapper.findAll(deleteStatus);
	}

	@Override
	public Tree<SysMenu> loadMenuTree() {

		List<SysMenu> sysMenus = this.findAll(YtxConstants.YES);

		List<Tree<SysMenu>> trees = new ArrayList<>();
		buildTrees(trees, sysMenus);
		return TreeUtils.build(trees);
	}

	@Override
	public Map<String, Object> loadMenuTree2() {
		List<SysMenu> sysMenus = this.findAll(YtxConstants.YES);

		List<Tree<SysMenu>> trees = new ArrayList<>();
		buildTrees(trees, sysMenus);
        Tree<SysMenu> sysMenuTree = TreeUtils.build(trees);
        // 根节点
        Map<String, Object> rootNode = Maps.newHashMap();
        rootNode.put("value", sysMenuTree.getId());
        rootNode.put("label", sysMenuTree.getText());

        // 根节点children
        List<Map<String, Object>> rootNodeChildren = Lists.newArrayList();
        // 一级节点
        Map<String, Object> firstNode;
        // 一级节点children
        List<Map<String, Object>> firstNodeChildren;
        // 二级节点
        Map<String, Object> secondNode;
        List<Tree<SysMenu>> children01 = sysMenuTree.getChildren();
        List<Tree<SysMenu>> children02;
        for(Tree<SysMenu> tree01 : children01) {
            firstNode = Maps.newHashMap();
			firstNodeChildren = Lists.newArrayList();
			firstNode.put("value", rootNode.get("value") + "-" + tree01.getId());
			firstNode.put("label", tree01.getText());
			rootNodeChildren.add(firstNode);
			children02 = tree01.getChildren();
			for(Tree<SysMenu> tree02 : children02) {
				secondNode = Maps.newHashMap();
                secondNode.put("value", firstNode.get("value") + "-" + tree02.getId());
                secondNode.put("label", tree02.getText());
                firstNodeChildren.add(secondNode);
            }
            firstNode.put("children", firstNodeChildren);
        }
        rootNode.put("children", rootNodeChildren);

        return rootNode;
    }

	/**
	 * 获取用户可显示菜单列表
	 *
	 * @return
	 */
	@Override
	public List<SysMenuRspDto> selectUserMenus() {
		SysRole role = roleService.checkUserIsAdmin();
		if(null == role){
			return roleMenuMapper.getUserMenusByRoles(this.getUserRoleIds());
		}
		// 表示是管理员，返回全部菜单
		List<SysMenuRspDto> sysMenuRspDtos = Lists.newArrayList();
		List<SysMenu> sysMenus = this.findAll(YtxConstants.YES);
		if(sysMenus != null && sysMenus.size() > 0){
			SysMenuRspDto sysMenuRspDto = null;
			for(SysMenu sysMenu : sysMenus){
				sysMenuRspDto = new SysMenuRspDto();
				sysMenuRspDtos.add(revertSysMenuToDto(sysMenu,sysMenuRspDto));
			}
		}
		return sysMenuRspDtos;
	}

	/**
	 * 转换sysmenu对象为dto对象
	 * @param sysMenu
	 * @param sysMenuRspDto
	 * @return
	 */
	private SysMenuRspDto revertSysMenuToDto(SysMenu sysMenu,SysMenuRspDto sysMenuRspDto){
		BeanUtils.copyProperties(sysMenu,sysMenuRspDto);
		return sysMenuRspDto;
	}

	/**
	 * 获取用户可显示菜单树形结构列表
	 *
	 * @return
	 */
	@Override
	public Tree<SysMenuRspDto> selectUserMenuTree() {
		List<SysMenuRspDto> sysMenus = selectUserMenus();
		List<Tree<SysMenuRspDto>> trees = new ArrayList<>();
		buildUserMenuTree(trees, sysMenus);
		return TreeUtils.build(trees);
	}

	/**
	 * 获取当前角色已选择的菜单列表
	 *
	 * @param roleId
	 * @return
	 */
	@Override
	public List<SysMenuRspDto> getRoleMenus(Long roleId) {
		List<Long> roleIds = Lists.newArrayList(roleId);
		return roleMenuMapper.getUserMenusByRoles(roleIds);
	}

	/**
	 * 获取当前用户的角色列表
	 * @return
	 */
	private List<Long> getUserRoleIds(){
		List<Long> roleIds = Lists.newArrayList();
		Long userId = super.getUserId();
		if(userId == null){
			logger.info("当前登录用户id为空......");
			throw new BusinessException("用户编码为空");
		}
		List<SysRole> roles = roleService.findByUserId(userId);
		if(roles != null && roles.size() > 0){
			roles.forEach(role -> roleIds.add(role.getId()));
		}
		return roleIds;
	}

	private void buildTrees(List<Tree<SysMenu>> trees, List<SysMenu> menus) {
		menus.forEach(menu -> {
			Tree<SysMenu> tree = new Tree<>();
			tree.setId(menu.getId());
			tree.setIcon(menu.getMenuIcon());
			tree.setUrl(menu.getMenuCode());
			tree.setParentId(menu.getParentId());
			tree.setText(menu.getMenuName());
			trees.add(tree);
		});
	}

	/**
	 * 获取用户菜单树
	 * @param trees
	 * @param menus
	 */
	private void buildUserMenuTree(List<Tree<SysMenuRspDto>> trees, List<SysMenuRspDto> menus) {
		menus.forEach(menu -> {
			Tree<SysMenuRspDto> tree = new Tree<>();
			tree.setId(menu.getId());
			tree.setIcon(menu.getMenuIcon());
			tree.setUrl(menu.getMenuCode());
			tree.setParentId(menu.getParentId());
			tree.setText(menu.getMenuName());
			trees.add(tree);
		});
	}

}
