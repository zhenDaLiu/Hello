package com.yuntongxun.ytx.fast.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.yuntongxun.admin.service.sys.ISysPermissionService;
import com.yuntongxun.admin.service.sys.ISysUserService;

/**
 * 认证Realm
 * @author tangxy
 */
public class ClientAuthRealm extends AuthorizingRealm {

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

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, getName());

        // 放入shiro.调用CredentialsMatcher检验密码
        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }


}