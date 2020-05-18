package yuntongxun.admin.service.sys.impl;

import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yuntongxun.ytx.mapper.sys.SysUserMapper;
import com.yuntongxun.ytx.constants.EnumConstants;
import com.yuntongxun.ytx.pojo.dto.req.sys.*;
import com.yuntongxun.ytx.pojo.po.sys.SysRole;
import com.yuntongxun.ytx.pojo.po.sys.SysUser;
import com.yuntongxun.ytx.pojo.po.sys.SysUserRole;
import com.yuntongxun.ytx.pojo.vo.sys.SysUserVo;
import com.yuntongxun.ytx.utils.ParamsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import yuntongxun.admin.service.sys.ISysPermissionService;
import yuntongxun.admin.service.sys.ISysRoleService;
import yuntongxun.admin.service.sys.ISysUserRoleService;
import yuntongxun.admin.service.sys.ISysUserService;
import yuntongxun.ytx.fast.config.shiro.JWTUtil;
import yuntongxun.ytx.fast.constenum.ConstEnum;
import yuntongxun.ytx.fast.exception.BusinessException;
import yuntongxun.ytx.service.impl.BaseServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * 用户service
 * @author cc
 */
@Service
@Slf4j
public class SysUserServiceImpl extends BaseServiceImpl implements ISysUserService {
	
	/**
	 * 加密盐值长度
	 */
	private static final int SALT_LENGTH = 30;

	@Autowired
	SysUserMapper sysUserMapper;

	@Autowired
	ISysRoleService sysRoleService;

	@Autowired
	ISysUserRoleService sysUserRoleService;

	@Autowired
	@Lazy
	ISysPermissionService sysPermissionService;

	@Override
	public RepUserDto login(ReqLoginDto reqLoginDto) {

		String username = reqLoginDto.getUsername();
		String password = reqLoginDto.getPassword();

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			throw new AuthenticationException("用户名或密码为空");
		}

		SysUser sysUser = findByUserName(username);


		if (sysUser == null) {
			throw new AuthenticationException("账户不存在");
		}

		if (ConstEnum.STATUS_DISABLE.getValue().equals(sysUser.getDeleteStatus())) {
			throw new UnauthenticatedException("账户不可用");
		}

		// base64解密
		password = Base64.decodeStr(password);
		// 匹配密码
		String pwd = new Sha256Hash(password,sysUser.getSalt()).toHex();
		if (!pwd.equals(sysUser.getPassword())) {
			throw new AuthenticationException("密码不正确");
		}

		// 查询角色
		List<SysRole> roles = sysRoleService.findByUserId(sysUser.getId());

		// Access-Token
		String accessToken = JWTUtil.sign(
				String.valueOf(sysUser.getId()),
				sysUser.getUsername(),
				sysUser.getPassword()
		);
		RepUserDto repUserDto = RepUserDto.builder()
				.userId(sysUser.getId())
				.username(sysUser.getUsername())
				.nickName(sysUser.getNickname())
				.roles(roles)
				.accessToken(accessToken)
				.build();

		// 清除账户的权限缓存
		sysPermissionService.clearPermissionCacheByUserId(String.valueOf(sysUser.getId()));
		// 保存登录日志
	 	saveOperateLog(EnumConstants.OperateLogModuleName.user.name(),EnumConstants.OperateLogOperateType.login.name(),"登录",sysUser.getUsername());
		return repUserDto;
	}


	/**
	 * 根据登录账号获取用户信息
	 * @param username
	 * @return
	 */
	@Override
	public SysUser findByUserName(String username) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		QueryWrapper<SysUser> wrapper = new QueryWrapper<>(sysUser);
		return sysUserMapper.selectOne(wrapper);
	}

	/**
	 * @Description:给账户分配角色
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void assign(ReqUserRoleDto reqUserRoleDto) {
		// 重置权限
		sysUserMapper.clearRole(reqUserRoleDto.getUserId());

		// 封装sysUserRoles
        Long userId = reqUserRoleDto.getUserId();
		List<Long> roleIds = reqUserRoleDto.getRoleIds();
		List<SysUserRole> sysUserRoles = Lists.newArrayList();
		SysUserRole sysUserRole;
		for(int i=0; i< roleIds.size(); i++) {
			sysUserRole = new SysUserRole(
					null,
					userId,
					roleIds.get(i),
					new Date(),
					new Date()
			);
			sysUserRoles.add(sysUserRole);
		}
		// 分配角色
		sysUserMapper.giveRole(sysUserRoles);
	}

	@Override
	public PageInfo<SysUserVo> selectPage(ReqUserPageDto reqUserPageDto) {

		// 因账户信息中也需要角色，这里手动封装分页查询
		Integer pageNum = reqUserPageDto.getPageNum();
		Integer pageSize = reqUserPageDto.getPageSize();


		Integer startRow = (pageNum - 1) * pageSize;
		Integer offsetLimit = pageSize;

		List<SysUserVo> sysUserVos = sysUserMapper.selectPList(
				startRow,
				offsetLimit,
				ParamsUtil.joinLike(reqUserPageDto.getUsername())
		);

		Integer total = sysUserMapper.selectPCount(
				ParamsUtil.joinLike(reqUserPageDto.getUsername())
		);

		// 封装pageInfo
		PageInfo<SysUserVo> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(reqUserPageDto.getPageNum());
		pageInfo.setPageSize(pageSize);
		pageInfo.setList(sysUserVos);
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
	public Integer add(ReqUserDto reqUserDto) {

		// 账户查重
		SysUser sysUser = this.findByUserName(reqUserDto.getUsername());
		if(null != sysUser) {
			throw new BusinessException("账户名已存在");
		}

		// 账户新增
		String salt = RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
		String pwd = Base64.decodeStr(reqUserDto.getPassword());
		String passWd = new Sha256Hash(pwd,salt).toHex();
		sysUser = new SysUser();
		sysUser.setUsername(reqUserDto.getUsername());
		sysUser.setPassword(passWd);
		sysUser.setSalt(salt);
		sysUser.setNickname(reqUserDto.getNickname());
		sysUser.setUserType(EnumConstants.UserType.ADMIN.getValue());
		sysUser.setDeleteStatus(ConstEnum.STATUS_ENABLE.getValue());
		sysUser.setCreateTime(new Date());

		int insert = sysUserMapper.insert(sysUser);

		List<SysUserRole> sysUserRoles = Lists.newArrayList();
		List<Long> roleIds = reqUserDto.getRoleIds();
		SysUserRole sysUserRole;
		if(CollectionUtils.isEmpty(roleIds)) {
			return insert;
		}
		for(Long roleId : roleIds) {
			sysUserRole = new SysUserRole(
					sysUser.getId(),
					roleId,
					new Date(),
					new Date()
			);
			sysUserRoles.add(sysUserRole);
		}

		// 账户角色新增
		sysUserRoleService.insertBatch(sysUserRoles);

		return insert;
	}

	@Override
	public Integer del(ReqIdDto reqIdDto) {

		// 账户-角色-删除
		sysUserRoleService.deleteByUserId(reqIdDto.getId());

		// 账户-删除
		return sysUserMapper.deleteById(reqIdDto.getId());
	}

	@Override
	public Integer disable(ReqStatusDto reqStatusDto) {
		return sysUserMapper.updateStatusById(reqStatusDto.getId(), reqStatusDto.getStatus());
	}

	@Override
	public Integer updatePwd(ReqUserPwdDto reqUserPwdDto) {

		if(StringUtils.isEmpty(reqUserPwdDto.getPwdOld())) {
			throw new BusinessException("旧密码不能为空");
		}
		if(StringUtils.isEmpty(reqUserPwdDto.getPwdNew())) {
			throw new BusinessException("新密码不能为空");
		}

		// 不允许禁用系统超级账户
//		if(ConstStr.SUPER_ADMIN_ROLE_ID.equals(reqUserPwdDto.getUserId())) {
//			throw new BusinessException("系统超级账户, 禁止更改");
//		}

		// 账户
		SysUser sysUser = sysUserMapper.selectById(reqUserPwdDto.getUserId());
		if(null == sysUser) {
			throw new BusinessException("账户不存在");
		}
		String pwdOld = Base64.decodeStr(reqUserPwdDto.getPwdOld());
		pwdOld = new Sha256Hash(pwdOld,sysUser.getSalt()).toHex();
		if(!pwdOld.equals(sysUser.getPassword())) {
			throw new BusinessException("旧密码错误");
		}

		String pwdNew = Base64.decodeStr(reqUserPwdDto.getPwdNew());
		pwdNew = new Sha256Hash(pwdNew,sysUser.getSalt()).toHex();
		if(pwdNew.equals(sysUser.getPassword())) {
			throw new BusinessException("新密码不能与旧密码一致");
		}

		// 生产盐值
		String salt = RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
		pwdNew = Base64.decodeStr(reqUserPwdDto.getPwdNew());
		pwdNew = new Sha256Hash(pwdNew,salt).toHex();
		// 更新密码
		return sysUserMapper.updatePasswordAndSaltById(reqUserPwdDto.getUserId(), pwdNew,salt);
	}


}
