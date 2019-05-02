package me.zohar.catering.cooking.param;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import me.zohar.catering.common.utils.IdUtils;
import me.zohar.catering.constants.Constant;
import me.zohar.catering.cooking.domain.StyleOfCooking;

public class StyleOfCookingParam {

	/**
	 * 主键id
	 */
	private String id;

	private Double orderNo;

	/**
	 * 菜系名称
	 */
	private String styleOfCookingName;

	public StyleOfCooking convertToPo() {
		StyleOfCooking po = new StyleOfCooking();
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

	public Double getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Double orderNo) {
		this.orderNo = orderNo;
	}

	public String getStyleOfCookingName() {
		return styleOfCookingName;
	}

	public void setStyleOfCookingName(String styleOfCookingName) {
		this.styleOfCookingName = styleOfCookingName;
	}

}
