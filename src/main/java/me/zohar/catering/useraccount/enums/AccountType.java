package me.zohar.catering.useraccount.enums;

public enum AccountType {

	管理员("admin", "管理员"),

	服务员("waiter", "服务员");

	private String code;

	private String name;

	public static AccountType getType(String code) {
		for (AccountType state : AccountType.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	private AccountType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
