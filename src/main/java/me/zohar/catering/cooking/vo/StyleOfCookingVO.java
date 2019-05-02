package me.zohar.catering.cooking.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.hutool.core.collection.CollectionUtil;
import me.zohar.catering.cooking.domain.StyleOfCooking;

public class StyleOfCookingVO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String id;

	private Double orderNo;

	/**
	 * 菜系名称
	 */
	private String styleOfCookingName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	public static List<StyleOfCookingVO> convertFor(List<StyleOfCooking> pos) {
		if (CollectionUtil.isEmpty(pos)) {
			return new ArrayList<>();
		}
		List<StyleOfCookingVO> vos = new ArrayList<>();
		for (StyleOfCooking po : pos) {
			vos.add(convertFor(po));
		}
		return vos;
	}

	public static StyleOfCookingVO convertFor(StyleOfCooking po) {
		if (po == null) {
			return null;
		}
		StyleOfCookingVO vo = new StyleOfCookingVO();
		BeanUtils.copyProperties(po, vo);
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

	public String getStyleOfCookingName() {
		return styleOfCookingName;
	}

	public void setStyleOfCookingName(String styleOfCookingName) {
		this.styleOfCookingName = styleOfCookingName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
