package me.zohar.catering.diningtable.enums;

public enum OrderDishesRecordState {

	未上菜("1", "未上菜"),

	已上菜("2", "已上菜");

	private String code;

	private String name;

	public static OrderDishesRecordState getState(String code) {
		for (OrderDishesRecordState state : OrderDishesRecordState.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	private OrderDishesRecordState(String code, String name) {
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
