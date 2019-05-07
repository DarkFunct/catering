package me.zohar.catering.useraccount.param;

import org.hibernate.validator.constraints.NotBlank;

public class UserAccountEditParam {

	/**
	 * 主键id
	 */
	@NotBlank
	private String userAccountId;

	/**
	 * 用户名
	 */
	@NotBlank
	private String userName;

	/**
	 * 真实姓名
	 */
	@NotBlank
	private String realName;

	/**
	 * 账号类型
	 */
	@NotBlank
	private String accountType;

	/**
	 * 状态
	 */
	@NotBlank
	private String state;

	public String getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
