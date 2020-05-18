package yuntongxun.ytx.fast.config.shiro;


import com.yuntongxun.ytx.fast.constenum.ConstEnum;
import com.yuntongxun.ytx.pojo.dto.req.sys.ReqUserIdDto;
import com.yuntongxun.ytx.pojo.po.sys.SysPermission;
import com.yuntongxun.ytx.pojo.po.sys.SysUser;
import com.yuntongxun.admin.service.sys.ISysPermissionService;
import com.yuntongxun.admin.service.sys.ISysUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 认证Realm
 * @author tangxy
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * FIXME service/realm 调用 service 缓存会失效 加Lazy 解决  其他只要有 service 调用这个service 无论走不走那里缓存都失效
     * FIXME  shiro和cache在引用service实例顺序问题，shiro引入应在cache后，
     * FIXME shiro配置文件中引用realm属性bean中引用的service采用延迟加载策略。
     */
    @Autowired
    @Lazy
    private ISysPermissionService sysPermissionService;

    /**
     * 认证.登录
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {

        // 解密获得username，用于和数据库进行对比
        String token = (String) auth.getCredentials();
        String username = JWTUtil.getUsername(token);
        String userId = JWTUtil.getUserId(token);
        if (username == null || userId == null) {
            throw new AuthenticationException("token invalid");
        }

        SysUser sysUser = sysUserService.findByUserName(username);
        if (sysUser == null) {
            throw new AuthenticationException("账户不存在");
        }

        if (ConstEnum.STATUS_DISABLE.getValue().equals(sysUser.getDeleteStatus())) {
            throw new AuthenticationException("账户不可用");
        }


        if (!JWTUtil.verify(token, sysUser.getPassword())) {
            throw new AuthenticationException("未登录");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, getName());

        // 放入shiro.调用CredentialsMatcher检验密码
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        String userId = JWTUtil.getUserId(principal.toString());

        List<SysPermission> sysPermissions = sysPermissionService.findByUserId(new ReqUserIdDto(Long.valueOf(userId)));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (CollectionUtils.isNotEmpty(sysPermissions)) {
            sysPermissions.forEach(permission -> info.addStringPermission(permission.getPermissionCode()));
        }

        return info;
    }


}