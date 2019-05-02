package me.zohar.catering.cooking.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * 菜品
 * 
 * @author zohar
 * @date 2019年4月28日
 *
 */
@Entity
@Table(name = "cooking")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Cooking {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
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

	/**
	 * 图片id
	 */
	private String storageId;

	private Date createTime;

	private Boolean availableFlag;

	/**
	 * 所属菜系id
	 */
	@Column(name = "style_of_cooking_id", length = 32)
	private String styleOfCookingId;

	/**
	 * 所属菜系
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "style_of_cooking_id", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private StyleOfCooking styleOfCooking;

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

	public Boolean getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(Boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

	public String getStyleOfCookingId() {
		return styleOfCookingId;
	}

	public void setStyleOfCookingId(String styleOfCookingId) {
		this.styleOfCookingId = styleOfCookingId;
	}

	public StyleOfCooking getStyleOfCooking() {
		return styleOfCooking;
	}

	public void setStyleOfCooking(StyleOfCooking styleOfCooking) {
		this.styleOfCooking = styleOfCooking;
	}

}
