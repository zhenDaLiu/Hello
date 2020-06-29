package com.yuntongxun.ytx.fast.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 多Realm处理类
 * @author sintang
 * @date 2019-07-21
 **/
public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 判断getRealms()是否返回为空
        assertRealmsConfigured();

        // 强制转换回自定义的CustomizedToken
        JWTToken userToken = (JWTToken) authenticationToken;

        // 登录类型
        String loginType = userToken.getLoginType();

        // 所有Realm
        Collection<Realm> realms = getRealms();

        // 登录类型对应的所有Realm
        Collection<Realm> typeRealms = new ArrayList<>();
        for(Realm realm : realms){
            if(realm.getName().equalsIgnoreCase(loginType)){
                typeRealms.add(realm);
            }
        }
        return doSingleRealmAuthentication(typeRealms.iterator().next(),userToken);
    }
}
