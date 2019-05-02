package me.zohar.catering.diningtable.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import me.zohar.catering.diningtable.domain.DiningTable;
import me.zohar.catering.diningtable.enums.DiningTableState;

public class DiningTableVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private Double orderNo;

	/**
	 * 餐桌名
	 */
	private String diningTableName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 状态
	 */
	private String state;

	private String stateName;

	public static List<DiningTableVO> convertFor(List<DiningTable> pos) {
		if (CollectionUtil.isEmpty(pos)) {
			return new ArrayList<>();
		}
		List<DiningTableVO> vos = new ArrayList<>();
		for (DiningTable po : pos) {
			vos.add(convertFor(po));
		}
		return vos;
	}

	public static DiningTableVO convertFor(DiningTable po) {
		if (po == null) {
			return null;
		}
		DiningTableVO vo = new DiningTableVO();
		BeanUtils.copyProperties(po, vo);
		vo.setStateName(DiningTableState.getState(vo.getState()).getName());
		return vo;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
