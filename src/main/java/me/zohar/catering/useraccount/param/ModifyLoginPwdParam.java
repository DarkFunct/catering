package me.zohar.catering.useraccount.param;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改登录密码入参
 * 
 * @author zohar
 * @date 2019年1月19日
 *
 */
public class ModifyLoginPwdParam {

	/**
	 * 旧的登录密码
	 */
	@NotBlank
	private String oldLoginPwd;

	/**
	 * 新的登录密码
	 */
	@NotBlank
	private String newLoginPwd;

	/**
	 * 用户账号id
	 */
	@NotBlank
	private String userAccountId;

	public String getOldLoginPwd() {
		return oldLoginPwd;
	}

	public void setOldLoginPwd(String oldLoginPwd) {
		this.oldLoginPwd = oldLoginPwd;
	}

	public String getNewLoginPwd() {
		return newLoginPwd;
	}

	public void setNewLoginPwd(String newLoginPwd) {
		this.newLoginPwd = newLoginPwd;
	}

	public String getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(String userAccountId) {
		this.userAccountId = userAccountId;
	}

}
