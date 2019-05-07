package me.zohar.catering.useraccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.catering.common.vo.Result;
import me.zohar.catering.config.security.UserAccountDetails;
import me.zohar.catering.useraccount.param.AddUserAccountParam;
import me.zohar.catering.useraccount.param.UserAccountEditParam;
import me.zohar.catering.useraccount.param.UserAccountQueryCondParam;
import me.zohar.catering.useraccount.service.UserAccountService;
import me.zohar.catering.useraccount.vo.UserAccountInfoVO;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;
	
	@GetMapping("/findUserAccountDetailsInfoById")
	@ResponseBody
	public Result findUserAccountDetailsInfoById(String userAccountId) {
		return Result.success().setData(userAccountService.findUserAccountDetailsInfoById(userAccountId));
	}

	@GetMapping("/findUserAccountDetailsInfoByPage")
	@ResponseBody
	public Result findUserAccountDetailsInfoByPage(UserAccountQueryCondParam param) {
		return Result.success().setData(userAccountService.findUserAccountDetailsInfoByPage(param));
	}


	@PostMapping("/modifyLoginPwd")
	@ResponseBody
	public Result modifyLoginPwd(String userAccountId, String newLoginPwd) {
		userAccountService.modifyLoginPwd(userAccountId, newLoginPwd);
		return Result.success();
	}

	
	@PostMapping("/updateUserAccount")
	@ResponseBody
	public Result updateUserAccount(UserAccountEditParam param) {
		userAccountService.updateUserAccount(param);
		return Result.success();
	}
	
	@GetMapping("/getUserAccountInfo")
	@ResponseBody
	public Result getUserAccountInfo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ("anonymousUser".equals(principal)) {
			return Result.success();
		}
		UserAccountDetails user = (UserAccountDetails) principal;
		UserAccountInfoVO userAccountInfo = userAccountService.getUserAccountInfo(user.getUserAccountId());
		return Result.success().setData(userAccountInfo);
	}
	
	@GetMapping("/delUserAccount")
	@ResponseBody
	public Result delUserAccount(String userAccountId) {
		userAccountService.delUserAccount(userAccountId);
		return Result.success();
	}
	
	@PostMapping("/addUserAccount")
	@ResponseBody
	public Result addUserAccount(AddUserAccountParam param) {
		userAccountService.addUserAccount(param);
		return Result.success();
	}

}
