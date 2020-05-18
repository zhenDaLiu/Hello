package yuntongxun.admin.service.sys.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yuntongxun.ytx.fast.constenum.ConstEnum;
import com.yuntongxun.ytx.fast.exception.BusinessException;
import com.yuntongxun.ytx.mapper.sys.SysPermissionMapper;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqIdDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqPermissionDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqPermissionPageDto;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqUserIdDto;
import com.yuntongxun.ytx.pojo.po.sys.SysPermission;
import com.yuntongxun.ytx.pojo.po.sys.SysRole;
import com.yuntongxun.admin.service.sys.ISysPermissionService;
import com.yuntongxun.admin.service.sys.ISysRoleService;
import com.yuntongxun.ytx.utils.ParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
  * Author:luocc
  * Description:权限业务
  * Data:Created in 15:06 2018/4/30
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ISysRoleService sysRoleService;
	
	@Autowired
	SysPermissionMapper sysPermissionMapper;
	
	/**
	 * @Description:查询用户的权限列表
	 */
	@Cacheable(value = "cache_user_permission", key = "#reqUserIdDto.userId")
	@Override
	public List<SysPermission> findByUserId(ReqUserIdDto reqUserIdDto) {

		// 查找账户的角色列表
		List<SysRole> roles = sysRoleService.findByUserId(reqUserIdDto.getUserId());
		// 过滤被禁用角色
		roles = roles.stream()
				.filter(role -> ConstEnum.STATUS_ENABLE.getValue().equals(role.getDeleteStatus()))
				.collect(Collectors.toList());
		// 账户无角色则无权限
		if(CollectionUtils.isEmpty(roles)) {
			return Lists.newArrayList();
		}
		// 查找角色对应的权限列表()
		List<SysPermission> permissions = sysPermissionMapper.findByRoleIds(
				roles.stream()
						.map(entity -> entity.getId()).collect(Collectors.toList())
		);

		return permissions;
	}

	@Override
	public PageInfo<SysPermission> selectPage(ReqPermissionPageDto reqPermissionPageDto) {
		// 启动分页
		PageHelper.startPage(reqPermissionPageDto.getPageNum(), reqPermissionPageDto.getPageSize(), true);

		// 查询结果集
		List<SysPermission> sysPermissions = sysPermissionMapper.selectPage(
				ParamsUtil.joinLike(reqPermissionPageDto.getMenuName()),
				ParamsUtil.joinLike(reqPermissionPageDto.getPermissionName()));

		// 封装pageInfo
		PageInfo<SysPermission> pageInfo = new PageInfo<>(sysPermissions);

		return pageInfo;
	}

	@CacheEvict(value = "cache_user_permission", key = "#userId")
	@Override
	public void clearPermissionCacheByUserId(String userId) {
		// 清除账户的权限缓存
	}

	@Override
	public Integer save(ReqPermissionDto reqPermissionDto) {

		SysPermission sysPermission = sysPermissionMapper.findOneByPermissionCode(reqPermissionDto.getPermissionCode());
		if(null != sysPermission) {
			throw new BusinessException("权限Code已存在");
		}

		sysPermission = sysPermissionMapper.findOneByPermissionName(reqPermissionDto.getPermissionName());
		if(null != sysPermission) {
			throw new BusinessException("权限Name已存在");
		}

		sysPermission = new SysPermission(
				null,
				reqPermissionDto.getMenuCode(),
				reqPermissionDto.getMenuName(),
				reqPermissionDto.getPermissionCode(),
				reqPermissionDto.getPermissionName(),
				reqPermissionDto.getRequiredPermission()

		);

		return sysPermissionMapper.insert(sysPermission);
	}

	@Override
	public Integer del(ReqIdDto reqIdDto) {
		return sysPermissionMapper.deleteById(reqIdDto.getId());
	}

}
