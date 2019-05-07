package me.zohar.catering.useraccount.enums;

public enum AccountState {

	启用("1", "启用"),

	禁用("0", "禁用");

	private String code;

	private String name;

	public static AccountState getState(String code) {
		for (AccountState state : AccountState.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	private AccountState(String code, String name) {
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
