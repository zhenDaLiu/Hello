package yuntongxun.admin.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuntongxun.admin.service.sys.ISysUserRoleService;
import com.yuntongxun.ytx.fast.exception.ClientBusinessException;
import com.yuntongxun.ytx.mapper.sys.SysRoleMapper;
import com.yuntongxun.ytx.mapper.sys.SysUserRoleMapper;
import com.yuntongxun.ytx.pojo.po.sys.SysRole;
import com.yuntongxun.ytx.pojo.po.sys.SysUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SysUserRoleMapper sysUserRoleMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public Integer insertBatch(List<SysUserRole> sysUserRoles) {

		if(CollectionUtils.isEmpty(sysUserRoles)) {
			return 0;
		}

		return sysUserRoleMapper.insertSysUserRoleBatch(sysUserRoles);
	}

	@Override
	public Integer deleteByUserId(Long userId) {

		if(userId == null) {
			return 0;
		}

		return sysUserRoleMapper.deleteByUserId(userId);
	}

	/**
	 * 保存后台管理员角色
	 * @param userId
	 * @param roleCode 角色编码
	 */
	@Override
	public void saveAdminRole(Long userId, String roleCode) {
		if(StringUtils.isEmpty(roleCode)){
			throw new ClientBusinessException("角色编码为空");
		}
		SysRole sysRole = new SysRole();
		sysRole.setRoleCode(roleCode);
		QueryWrapper<SysRole> wrapper = new QueryWrapper<>(sysRole);
		sysRole = sysRoleMapper.selectOne(wrapper);
		if(sysRole != null){
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleId(sysRole.getId());
			sysUserRole.setCreateTime(new Date());
			sysUserRoleMapper.insert(sysUserRole);
		}
	}
}
