package com.yuntongxun.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yuntongxun.ytx.constants.EnumConstants;
import com.yuntongxun.ytx.mapper.sys.SysRoleMapper;
import com.yuntongxun.ytx.mapper.sys.YtxSysRoleMenuMapper;
import com.yuntongxun.ytx.pojo.dto.req.sys.*;
import com.yuntongxun.ytx.pojo.po.sys.*;
import com.yuntongxun.ytx.pojo.vo.sys.SysRoleVo;

import com.yuntongxun.ytx.utils.ParamsUtil;
import com.yuntongxun.ytx.utils.SnowFlakeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.yuntongxun.admin.service.sys.ISysRoleService;
import com.yuntongxun.ytx.fast.constenum.ConstEnum;
import com.yuntongxun.ytx.fast.exception.BusinessException;
import com.yuntongxun.ytx.service.impl.BaseServiceImpl;

import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl implements ISysRoleService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SysRoleMapper sysRoleMapper;

	@Autowired
	private YtxSysRoleMenuMapper roleMenuMapper;
	
	/**
	 * @Description:查询账户的角色列表
	 */
	@Override
	public List<SysRole> findByUserId(Long userId) {
		return sysRoleMapper.findByUserId(userId);
	}

	/**
	 * @Description:给角色分配权限
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void assign(ReqRolePermissionDto reqRolePermissionDto) {

		// 不允许修改超级管理员的权限
//		if(ConstStr.SUPER_ADMIN_ROLE_ID.equals(reqRolePermissionDto.getRoleId())) {
//			throw new BusinessException("超管角色的权限, 禁止更改");
//		}

		// 重置权限
		sysRoleMapper.clearPermission(reqRolePermissionDto.getRoleId());

		// 封装sysRolePermissions
		Long roleId = reqRolePermissionDto.getRoleId();
		List<Long> permissionIds = reqRolePermissionDto.getPermissionIds();
		List<SysRolePermission> sysRolePermissions = Lists.newArrayList();
		SysRolePermission sysRolePermission;
		for(int i=0; i< permissionIds.size(); i++) {
			sysRolePermission = new SysRolePermission(
					roleId,
					permissionIds.get(i),
					new Date(),
					new Date()
			);
			sysRolePermissions.add(sysRolePermission);
		}
		// 分配权限
		sysRoleMapper.givePermission(sysRolePermissions);
	}

	@Override
	public List<SysPermission> findPermissionByRoleId(ReqIdDto reqIdDto) {
		return sysRoleMapper.findPermissionByRoleId(reqIdDto.getId());
	}

	@Override
	public PageInfo<SysRoleVo> selectPage(ReqRolePageDto reqRolePageDto) {

		// 因角色中也需要权限，这里手动封装分页查询
		Integer pageNum = reqRolePageDto.getPageNum();
		Integer pageSize = reqRolePageDto.getPageSize();


		Integer startRow = (pageNum - 1) * pageSize;
		Integer offsetLimit = pageSize;

		List<SysRoleVo> sysRoleVos = sysRoleMapper.selectPList(
				startRow,
				offsetLimit,
				ParamsUtil.joinLike(reqRolePageDto.getRoleName())
		);

		Integer total = sysRoleMapper.selectPCount(
				ParamsUtil.joinLike(reqRolePageDto.getRoleName())
		);

		// 封装pageInfo
		PageInfo<SysRoleVo> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(reqRolePageDto.getPageNum());
		pageInfo.setPageSize(pageSize);
		pageInfo.setList(sysRoleVos);
		pageInfo.setTotal(total);
		pageInfo.setPages((total-1)/pageSize+1);
		pageInfo.setHasPreviousPage(pageNum > 1);
		pageInfo.setHasNextPage(pageNum < pageInfo.getPages());
		pageInfo.setIsFirstPage(pageNum == 1);
		pageInfo.setIsLastPage(pageNum == pageInfo.getPages() || pageInfo.getPages() == 0);
		pageInfo.setPrePage((pageNum > 1) ? (pageNum - 1) : pageNum);
		pageInfo.setNextPage((pageNum < pageInfo.getPages()) ? (pageNum + 1) : pageInfo.getPages());

		return pageInfo;
	}

	@Override
	public Integer add(ReqRoleDto reqRoleDto) {
		// 按照roleName查重
		SysRole sysRole = sysRoleMapper.selectOneByRoleName(reqRoleDto.getRoleName());
		if(null != sysRole) {
			throw  new BusinessException("角色名称已存在");
		}

		sysRole = new SysRole(
				null,
				null,
				reqRoleDto.getRoleName(),
				ConstEnum.STATUS_ENABLE.getValue(),
				new Date(),
				new Date()
		);
		return sysRoleMapper.insert(sysRole);
	}

	@Override
	public Integer del(ReqIdDto reqIdDto) {
		return sysRoleMapper.deleteById(reqIdDto.getId());
	}

	@Override
	public Integer disable(ReqStatusDto reqStatusDto) {
		return sysRoleMapper.updateStatusById(reqStatusDto.getId(), reqStatusDto.getStatus());
	}

	/**
	 * 更新角色与菜单关系
	 *
	 * @param reqRoleMenuDto
	 */
	@Override
	public void updateRoleMenu(ReqRoleMenuDto reqRoleMenuDto) {
		Long roleId = reqRoleMenuDto.getRoleId();
		List<Long> menuIds = reqRoleMenuDto.getMenuIds();
		// 删除角色所配置的所有菜单
		YtxSysRoleMenu roleMenu = new YtxSysRoleMenu();
		roleMenu.setRoleId(roleId);
		UpdateWrapper<YtxSysRoleMenu> wrapper = new UpdateWrapper<>(roleMenu);
		roleMenuMapper.delete(wrapper);

		if(menuIds != null && menuIds.size() > 0){
			// 插入用户角色对应关系

			List<SysRoleMenuVo> sysRoleMenuVos = Lists.newArrayList();
			SysRoleMenuVo sysRoleMenuVo = null;
			for(Long menuId : menuIds){
				if(!StringUtils.isEmpty(menuId)){
					sysRoleMenuVo = new SysRoleMenuVo(SnowFlakeGenerator.getInstance().nextId(),menuId);
					sysRoleMenuVos.add(sysRoleMenuVo);
				}
			}
			roleMenuMapper.batchInsertRoleMenu(roleId,sysRoleMenuVos);
		}
	}

	/**
	 * 检查当前用户是否是管理员角色
	 *
	 * @return
	 */
	@Override
	public SysRole checkUserIsAdmin() {
		return sysRoleMapper.checkUserIsAdmin(EnumConstants.ManagerType.ADMIN.name().toLowerCase(),getLoginName());
	}
}
