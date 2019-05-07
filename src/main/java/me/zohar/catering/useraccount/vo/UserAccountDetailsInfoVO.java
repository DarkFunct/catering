package me.zohar.catering.useraccount.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import me.zohar.catering.useraccount.domain.UserAccount;
import me.zohar.catering.useraccount.enums.AccountState;
import me.zohar.catering.useraccount.enums.AccountType;

/**
 * 用户账号明细信息vo
 * 
 * @author zohar
 * @date 2019年2月22日
 *
 */
public class UserAccountDetailsInfoVO {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 真实姓名
	 */
	private String realName;

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

	/**
	 * 注册时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date registeredTime;

	/**
	 * 最近登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date latelyLoginTime;

	public static List<UserAccountDetailsInfoVO> convertFor(List<UserAccount> userAccounts) {
		if (CollectionUtil.isEmpty(userAccounts)) {
			return new ArrayList<>();
		}
		List<UserAccountDetailsInfoVO> vos = new ArrayList<>();
		for (UserAccount userAccount : userAccounts) {
			vos.add(convertFor(userAccount));
		}
		return vos;
	}

	public static UserAccountDetailsInfoVO convertFor(UserAccount userAccount) {
		if (userAccount == null) {
			return null;
		}
		UserAccountDetailsInfoVO vo = new UserAccountDetailsInfoVO();
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

	public Date getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}

	public Date getLatelyLoginTime() {
		return latelyLoginTime;
	}

	public void setLatelyLoginTime(Date latelyLoginTime) {
		this.latelyLoginTime = latelyLoginTime;
	}

}
