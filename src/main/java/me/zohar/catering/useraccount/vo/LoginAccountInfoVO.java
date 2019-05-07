package me.zohar.catering.useraccount.vo;

import org.springframework.beans.BeanUtils;

import me.zohar.catering.useraccount.domain.UserAccount;
import me.zohar.catering.useraccount.enums.AccountState;
import me.zohar.catering.useraccount.enums.AccountType;

/**
 * 登录账号信息,包含密码
 * 
 * @author zohar
 * @date 2019年1月25日
 *
 */
public class LoginAccountInfoVO {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 登录密码
	 */
	private String loginPwd;

	/**
	 * 账号类型
	 */
	private String accountType;

	private String accountTypeName;

	/**
	 * 状态
	 */
	private String state;

	private String stateName;

	public static LoginAccountInfoVO convertFor(UserAccount userAccount) {
		if (userAccount == null) {
			return null;
		}
		LoginAccountInfoVO vo = new LoginAccountInfoVO();
		BeanUtils.copyProperties(userAccount, vo);
		vo.setAccountTypeName(AccountType.getType(vo.getAccountType()).getName());
		vo.setStateName(AccountState.getState(vo.getState()).getName());
		return vo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
