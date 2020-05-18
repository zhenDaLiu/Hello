package yuntongxun.ytx.fast.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 登录验证token
 * @author tangxy
 * @date 2019-07-21
 */
public class JWTToken extends UsernamePasswordToken {

	/**
	 * 密钥
	 */
	private String token;

	/**
	 * 登录方式，后台、客户端
	 */
	private String loginType;

	public JWTToken(String token, String loginType) {
		this.token = token;
		this.loginType = loginType;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}
	
	@Override
	public Object getCredentials() {
		return token;
	}
}