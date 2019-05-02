package me.zohar.catering.diningtable.enums;


public enum DiningTableState {

	餐桌状态_空闲("1", "空闲"),

	餐桌状态_已预定("2", "已预定");

	private String code;

	private String name;

	public static DiningTableState getState(String code) {
		for (DiningTableState state : DiningTableState.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	private DiningTableState(String code, String name) {
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
