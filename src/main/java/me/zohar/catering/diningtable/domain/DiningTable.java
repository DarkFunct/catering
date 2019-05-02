package me.zohar.catering.diningtable.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 餐桌
 * 
 * @author zohar
 * @date 2019年4月28日
 *
 */
@Entity
@Table(name = "dining_table")
@DynamicInsert(true)
@DynamicUpdate(true)
public class DiningTable {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	private Double orderNo;

	/**
	 * 餐桌名
	 */
	private String diningTableName;

	private Date createTime;

	/**
	 * 状态
	 */
	private String state;

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

	public String getDiningTableName() {
		return diningTableName;
	}

	public void setDiningTableName(String diningTableName) {
		this.diningTableName = diningTableName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(Boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

}
