package me.zohar.catering.cooking.param;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import me.zohar.catering.common.utils.IdUtils;
import me.zohar.catering.constants.Constant;
import me.zohar.catering.cooking.domain.Cooking;

public class CookingParam {

	/**
	 * 主键id
	 */
	private String id;

	/**
	 * 菜名
	 */
	private String cookingName;

	/**
	 * 价格
	 */
	private Double price;

	/**
	 * 简介
	 */
	private String introduction;

	private String styleOfCookingId;

	private String storageId;

	public Cooking convertToPo() {
		Cooking po = new Cooking();
		BeanUtils.copyProperties(this, po);
		po.setId(IdUtils.getId());
		po.setCreateTime(new Date());
		po.setAvailableFlag(Constant.逻辑删除标记_正常);
		return po;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCookingName() {
		return cookingName;
	}

	public void setCookingName(String cookingName) {
		this.cookingName = cookingName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getStyleOfCookingId() {
		return styleOfCookingId;
	}

	public void setStyleOfCookingId(String styleOfCookingId) {
		this.styleOfCookingId = styleOfCookingId;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

}
