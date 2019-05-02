package me.zohar.catering.diningtable.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import me.zohar.catering.common.utils.IdUtils;
import me.zohar.catering.constants.Constant;
import me.zohar.catering.diningtable.enums.PlaceOrderRecordState;

/**
 * 下单记录
 * 
 * @author zohar
 * @date 2019年4月28日
 *
 */
@Entity
@Table(name = "place_order_record")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PlaceOrderRecord {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 订桌时间
	 */
	private Date bookTime;

	private String state;

	/**
	 * 结账时间
	 */
	private Date settleAccountTime;

	/**
	 * 消费
	 */
	private Double consume;

	@Column(name = "dining_table_id", length = 32)
	private String diningTableId;

	private String diningTableName;

	private Boolean availableFlag;

	public static PlaceOrderRecord build(DiningTable diningTable) {
		PlaceOrderRecord po = new PlaceOrderRecord();
		po.setId(IdUtils.getId());
		po.setOrderNo(IdUtils.getId());
		po.setBookTime(new Date());
		po.setState(PlaceOrderRecordState.未点菜.getCode());
		po.setDiningTableId(diningTable.getId());
		po.setDiningTableName(diningTable.getDiningTableName());
		po.setAvailableFlag(Constant.逻辑删除标记_正常);
		return po;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getSettleAccountTime() {
		return settleAccountTime;
	}

	public void setSettleAccountTime(Date settleAccountTime) {
		this.settleAccountTime = settleAccountTime;
	}

	public Double getConsume() {
		return consume;
	}

	public void setConsume(Double consume) {
		this.consume = consume;
	}

	public String getDiningTableId() {
		return diningTableId;
	}

	public void setDiningTableId(String diningTableId) {
		this.diningTableId = diningTableId;
	}

	public String getDiningTableName() {
		return diningTableName;
	}

	public void setDiningTableName(String diningTableName) {
		this.diningTableName = diningTableName;
	}

	public Boolean getAvailableFlag() {
		return availableFlag;
	}

	public void setAvailableFlag(Boolean availableFlag) {
		this.availableFlag = availableFlag;
	}

}
