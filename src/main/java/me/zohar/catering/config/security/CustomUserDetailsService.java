package me.zohar.catering.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.zohar.catering.constants.Constant;
import me.zohar.catering.useraccount.service.UserAccountService;
import me.zohar.catering.useraccount.vo.LoginAccountInfoVO;

/**
 * 通过实现UserDetailsService接口提供复杂认证
 * 
 * @author 黄振华
 * @date 2018年8月28日
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserAccountService userAccountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginAccountInfoVO loginAccountInfo = userAccountService.getLoginAccountInfo(username);
		if (loginAccountInfo == null) {
			throw new AuthenticationServiceException("用户名或密码不正确");
		}
		if (Constant.账号状态_禁用.equals(loginAccountInfo.getState())) {
			throw new AuthenticationServiceException("账号已被禁用");
		}
		return new UserAccountDetails(loginAccountInfo);
	}

}
