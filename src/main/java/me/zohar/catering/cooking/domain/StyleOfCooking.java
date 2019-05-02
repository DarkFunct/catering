package me.zohar.catering.cooking.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 菜系
 * 
 * @author zohar
 * @date 2019年4月28日
 *
 */
@Entity
@Table(name = "style_of_cooking")
@DynamicInsert(true)
@DynamicUpdate(true)
public class StyleOfCooking {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	private Double orderNo;

	/**
	 * 菜系名称
	 */
	private String styleOfCookingName;

	private Date createTime;

	private Boolean availableFlag;

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

}
