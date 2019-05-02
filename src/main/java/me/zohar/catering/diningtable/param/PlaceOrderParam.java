package me.zohar.catering.diningtable.param;

import java.util.List;

/**
 * 下单入参
 * 
 * @author zohar
 * @date 2019年5月1日
 *
 */
public class PlaceOrderParam {

	private String bookDiningTableRecordId;

	private List<String> cookingIds;

	public String getBookDiningTableRecordId() {
		return bookDiningTableRecordId;
	}

	public void setBookDiningTableRecordId(String bookDiningTableRecordId) {
		this.bookDiningTableRecordId = bookDiningTableRecordId;
	}

	public List<String> getCookingIds() {
		return cookingIds;
	}

	public void setCookingIds(List<String> cookingIds) {
		this.cookingIds = cookingIds;
	}

}
