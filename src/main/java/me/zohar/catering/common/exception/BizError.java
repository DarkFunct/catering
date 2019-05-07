package me.zohar.catering.common.exception;

/**
 * 业务处理错误枚举类
 * 
 * @author zohar
 * @date 2019年1月7日
 *
 */
public enum BizError {

	参数异常("1000", "参数异常"),
	
	用户名已存在("1000", "用户名已存在"),
	
	旧的登录密码不正确("1000", "旧的登录密码不正确"),

	餐桌已被预订("1000", "餐桌已被预订"),

	只能上传图片类型的附件("1000", "只能上传图片类型的附件"),

	字典项code不能重复("1000", "字典项code不能重复");
	private String code;

	private String msg;

	private BizError(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
