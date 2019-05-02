package me.zohar.catering.common.param;


/**
 * 分页条件入参
 * 
 * @author zohar
 * @date 2019年1月16日
 *
 */
public class PageParam {

	/**
	 * 页码
	 */
	private Integer pageNum;

	/**
	 * 每页大小
	 */
	private Integer pageSize;

	/**
	 * 排序字段
	 */
	private String propertie;

	/**
	 * 排序方式
	 */
	private String direction;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getPropertie() {
		return propertie;
	}

	public void setPropertie(String propertie) {
		this.propertie = propertie;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
