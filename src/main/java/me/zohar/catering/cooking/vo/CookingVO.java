package me.zohar.catering.cooking.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import me.zohar.catering.cooking.domain.Cooking;

public class CookingVO implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 菜名
	 */
	private String cookingName;

	/**
	 * 简介
	 */
	private String introduction;

	/**
	 * 价格
	 */
	private Double price;

	/**
	 * 图片id
	 */
	private String storageId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	private String styleOfCookingId;

	/**
	 * 菜系名称
	 */
	private String styleOfCookingName;

	public static List<CookingVO> convertFor(List<Cooking> pos) {
		if (CollectionUtil.isEmpty(pos)) {
			return new ArrayList<>();
		}
		List<CookingVO> vos = new ArrayList<>();
		for (Cooking po : pos) {
			vos.add(convertFor(po));
		}
		return vos;
	}

	public static CookingVO convertFor(Cooking po) {
		if (po == null) {
			return null;
		}
		CookingVO vo = new CookingVO();
		BeanUtils.copyProperties(po, vo);
		if (po.getStyleOfCooking() != null) {
			vo.setStyleOfCookingName(po.getStyleOfCooking().getStyleOfCookingName());
		}
		return vo;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStyleOfCookingId() {
		return styleOfCookingId;
	}

	public void setStyleOfCookingId(String styleOfCookingId) {
		this.styleOfCookingId = styleOfCookingId;
	}

	public String getStyleOfCookingName() {
		return styleOfCookingName;
	}

	public void setStyleOfCookingName(String styleOfCookingName) {
		this.styleOfCookingName = styleOfCookingName;
	}

}
