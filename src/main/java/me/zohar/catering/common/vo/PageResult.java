package me.zohar.catering.common.vo;

import java.util.List;

public class PageResult<T> {

	/**
	 * 页码
	 */
	private int pageNum;

	/**
	 * 每页大小
	 */
	private int pageSize;

	/**
	 * 总页数
	 */
	private long totalPage;

	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 实际记录数
	 */
	private int size;

	/**
	 * 数据集
	 */
	private List<T> content;

	public PageResult(List<T> content, int pageNum, int pageSize, long total) {
		this.content = content;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalPage = total == 0 ? 0 : total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1);
		this.total = total;
		this.size = content.size();
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
