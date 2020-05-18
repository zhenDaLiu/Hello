package yuntongxun.ytx.service.impl;

import com.yuntongxun.ytx.constants.EnumConstants;
import com.yuntongxun.ytx.mapper.sys.SysUserRoleMapper;
import com.yuntongxun.ytx.mapper.sys.YtxSysOperateLogMapper;
import com.yuntongxun.ytx.pojo.po.sys.SysUser;
import com.yuntongxun.ytx.pojo.po.sys.YtxSysOperateLog;
import com.yuntongxun.ytx.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuntongxun.admin.service.sys.ISysUserService;
import yuntongxun.ytx.fast.config.shiro.JWTUtil;
import yuntongxun.ytx.fast.exception.ClientBusinessException;

import java.util.Date;
import java.util.List;

/**
 * 基础service 实现
 * @author tangxy
 * @date 2019-03-19
 **/
@Service
@Slf4j
public class BaseServiceImpl {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private YtxSysOperateLogMapper operateLogMapper;

    @Autowired
    private RedisOperator redisOperator;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * 获取当前登录用户的登录名
     * @return
     */
    protected String getLoginName(){
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        return JWTUtil.getUsername(token);
    }


    /**
     * 获取当前登录用户id
     * @return
     */
    public Long getUserId(){
        SysUser sysUser = this.getLoginUser();
        return sysUser.getId();
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    protected SysUser getLoginUser(){
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String username = JWTUtil.getUsername(token);
        SysUser sysUser = sysUserService.findByUserName(username);
        if(sysUser == null){
            throw new ClientBusinessException("用户未登录");
        }
        return sysUser;
    }

    /**
     * 保存操作日志
     * @param moduleName
     * @param operateType
     * @param operateDesc
     */
    protected void saveOperateLog(String moduleName,String operateType,String operateDesc){
        YtxSysOperateLog operateLog = new YtxSysOperateLog();
        operateLog.setModuleName(moduleName);
        operateLog.setOperateType(operateType);
        operateLog.setOperateDesc(operateDesc);
        operateLog.setCreateUser(getLoginName());
        operateLog.setCreateTime(new Date());
        operateLogMapper.insert(operateLog);
    }

    /**
     * 保存操作日志
     * @param moduleName
     * @param operateType
     * @param operateDesc
     * @param username
     */
    protected void saveOperateLog(String moduleName,String operateType,String operateDesc,String username){
        YtxSysOperateLog operateLog = new YtxSysOperateLog();
        operateLog.setModuleName(moduleName);
        operateLog.setOperateType(operateType);
        operateLog.setOperateDesc(operateDesc);
        operateLog.setCreateUser(username);
        operateLog.setCreateTime(new Date());
        operateLogMapper.insert(operateLog);
    }


    /**
     * 判断当前登录用户是否是超级管理员
     * @return
     */
    protected Boolean checkLoginUserIsAdmin(Long userId){
        Boolean isAdmin = Boolean.FALSE;
        List<String> roleCodes = userRoleMapper.getUserRoleCode(userId);
        if(roleCodes == null || roleCodes.size() == 0){
            log.info("当前用户无角色:{}",userId);
            return isAdmin;
        }

        // 判断是否拥有超级管理员角色
        if(roleCodes.contains(EnumConstants.ManagerType.ADMIN.name().toLowerCase())){
            isAdmin = Boolean.TRUE;
        }
        return isAdmin;
    }
}
