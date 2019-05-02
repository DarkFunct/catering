package me.zohar.catering.diningtable.enums;

public enum PlaceOrderRecordState {

	未点菜("1", "未点菜"),

	已点菜("2", "已点菜"),

	通知结账("3", "通知结账"),

	已结账("4", "已结账");

	private String code;

	private String name;

	public static PlaceOrderRecordState getState(String code) {
		for (PlaceOrderRecordState state : PlaceOrderRecordState.values()) {
			if (state.getCode().equals(code)) {
				return state;
			}
		}
		return null;
	}

	private PlaceOrderRecordState(String code, String name) {
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
