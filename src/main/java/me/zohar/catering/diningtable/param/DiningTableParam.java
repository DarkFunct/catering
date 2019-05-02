package me.zohar.catering.diningtable.param;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import me.zohar.catering.common.utils.IdUtils;
import me.zohar.catering.constants.Constant;
import me.zohar.catering.diningtable.domain.DiningTable;
import me.zohar.catering.diningtable.enums.DiningTableState;

public class DiningTableParam {

	/**
	 * 主键id
	 */
	private String id;

	private Double orderNo;

	/**
	 * 餐桌名
	 */
	private String diningTableName;

	public DiningTable convertToPo() {
		DiningTable po = new DiningTable();
		BeanUtils.copyProperties(this, po);
		po.setId(IdUtils.getId());
		po.setCreateTime(new Date());
		po.setState(DiningTableState.餐桌状态_空闲.getCode());
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

	public String getDiningTableName() {
		return diningTableName;
	}

	public void setDiningTableName(String diningTableName) {
		this.diningTableName = diningTableName;
	}

}
