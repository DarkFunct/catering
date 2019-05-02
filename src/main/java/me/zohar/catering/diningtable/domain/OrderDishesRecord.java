package me.zohar.catering.diningtable.domain;

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

import me.zohar.catering.common.utils.IdUtils;
import me.zohar.catering.cooking.domain.Cooking;
import me.zohar.catering.diningtable.enums.OrderDishesRecordState;

/**
 * 点菜记录
 * 
 * @author zohar
 * @date 2019年5月1日
 *
 */
@Entity
@Table(name = "order_dishes_record")
@DynamicInsert(true)
@DynamicUpdate(true)
public class OrderDishesRecord {

	/**
	 * 主键id
	 */
	@Id
	@Column(name = "id", length = 32)
	private String id;

	private Date orderDishesTime;

	private String state;

	@Column(name = "cooking_id", length = 32)
	private String cookingId;

	private String bookDiningTableRecordId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cooking_id", updatable = false, insertable = false, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private Cooking cooking;

	public static OrderDishesRecord placeOrder(String cookingId, String bookDiningTableRecordId) {
		OrderDishesRecord po = new OrderDishesRecord();
		po.setId(IdUtils.getId());
		po.setOrderDishesTime(new Date());
		po.setState(OrderDishesRecordState.未上菜.getCode());
		po.setCookingId(cookingId);
		po.setBookDiningTableRecordId(bookDiningTableRecordId);
		return po;
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

	public String getCookingId() {
		return cookingId;
	}

	public void setCookingId(String cookingId) {
		this.cookingId = cookingId;
	}

	public String getBookDiningTableRecordId() {
		return bookDiningTableRecordId;
	}

	public void setBookDiningTableRecordId(String bookDiningTableRecordId) {
		this.bookDiningTableRecordId = bookDiningTableRecordId;
	}

	public Cooking getCooking() {
		return cooking;
	}

	public void setCooking(Cooking cooking) {
		this.cooking = cooking;
	}

}
