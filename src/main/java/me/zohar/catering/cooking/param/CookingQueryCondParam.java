package me.zohar.catering.cooking.param;

import me.zohar.catering.common.param.PageParam;

public class CookingQueryCondParam extends PageParam {

	private String styleOfCookingId;

	/**
	 * 菜名
	 */
	private String cookingName;

	public String getStyleOfCookingId() {
		return styleOfCookingId;
	}

	public void setStyleOfCookingId(String styleOfCookingId) {
		this.styleOfCookingId = styleOfCookingId;
	}

	public String getCookingName() {
		return cookingName;
	}

	public void setCookingName(String cookingName) {
		this.cookingName = cookingName;
	}

}
