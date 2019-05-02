package me.zohar.catering.diningtable.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import me.zohar.catering.diningtable.domain.OrderDishesRecord;
import me.zohar.catering.diningtable.enums.OrderDishesRecordState;

/**
 * 点菜记录
 * 
 * @author zohar
 * @date 2019年5月1日
 *
 */
public class OrderDishesRecordVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date orderDishesTime;

	private String state;

	private String stateName;

	private String cookingId;

	private String cookingName;

	private Double price;

	private String bookDiningTableRecordId;

	public static List<OrderDishesRecordVO> convertFor(List<OrderDishesRecord> pos) {
		if (CollectionUtil.isEmpty(pos)) {
			return new ArrayList<>();
		}
		List<OrderDishesRecordVO> vos = new ArrayList<>();
		for (OrderDishesRecord po : pos) {
			vos.add(convertFor(po));
		}
		return vos;
	}

	public static OrderDishesRecordVO convertFor(OrderDishesRecord po) {
		if (po == null) {
			return null;
		}
		OrderDishesRecordVO vo = new OrderDishesRecordVO();
		BeanUtils.copyProperties(po, vo);
		vo.setStateName(OrderDishesRecordState.getState(vo.getState()).getName());
		if (po.getCooking() != null) {
			vo.setCookingName(po.getCooking().getCookingName());
			vo.setPrice(po.getCooking().getPrice());
		}
		return vo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getOrderDishesTime() {
		return orderDishesTime;
	}

	public void setOrderDishesTime(Date orderDishesTime) {
		this.orderDishesTime = orderDishesTime;
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

	public String getCookingId() {
		return cookingId;
	}

	public void setCookingId(String cookingId) {
		this.cookingId = cookingId;
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

	public String getBookDiningTableRecordId() {
		return bookDiningTableRecordId;
	}

	public void setBookDiningTableRecordId(String bookDiningTableRecordId) {
		this.bookDiningTableRecordId = bookDiningTableRecordId;
	}

}
