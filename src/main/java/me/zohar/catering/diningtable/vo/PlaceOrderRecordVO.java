package me.zohar.catering.diningtable.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import me.zohar.catering.diningtable.domain.PlaceOrderRecord;
import me.zohar.catering.diningtable.enums.PlaceOrderRecordState;

public class PlaceOrderRecordVO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 订桌时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date bookTime;

	private String state;

	private String stateName;

	/**
	 * 结账时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date settleAccountTime;

	/**
	 * 消费
	 */
	private Double consume;

	private String diningTableId;

	/**
	 * 餐桌名
	 */
	private String diningTableName;

	public static List<PlaceOrderRecordVO> convertFor(List<PlaceOrderRecord> pos) {
		if (CollectionUtil.isEmpty(pos)) {
			return new ArrayList<>();
		}
		List<PlaceOrderRecordVO> vos = new ArrayList<>();
		for (PlaceOrderRecord po : pos) {
			vos.add(convertFor(po));
		}
		return vos;
	}

	public static PlaceOrderRecordVO convertFor(PlaceOrderRecord po) {
		if (po == null) {
			return null;
		}
		PlaceOrderRecordVO vo = new PlaceOrderRecordVO();
		BeanUtils.copyProperties(po, vo);
		vo.setStateName(PlaceOrderRecordState.getState(vo.getState()).getName());
		return vo;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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

}
