package me.zohar.catering.cooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.catering.common.vo.Result;
import me.zohar.catering.cooking.param.CookingParam;
import me.zohar.catering.cooking.param.CookingQueryCondParam;
import me.zohar.catering.cooking.param.StyleOfCookingParam;
import me.zohar.catering.cooking.param.StyleOfCookingQueryCondParam;
import me.zohar.catering.cooking.service.CookingService;

@Controller
@RequestMapping("/cooking")
public class CookingController {

	@Autowired
	private CookingService cookingService;

	@GetMapping("/findCookingByStyleOfCookingId")
	@ResponseBody
	public Result findCookingByStyleOfCookingId(String styleOfCookingId) {
		return Result.success().setData(cookingService.findCookingByStyleOfCookingId(styleOfCookingId));
	}

	@GetMapping("/findAllStyleOfCooking")
	@ResponseBody
	public Result findAllStyleOfCooking() {
		return Result.success().setData(cookingService.findAllStyleOfCooking());
	}

	@GetMapping("/delStyleOfCookingById")
	@ResponseBody
	public Result delStyleOfCookingById(String id) {
		cookingService.delStyleOfCookingById(id);
		return Result.success();
	}

	@PostMapping("/addOrUpdateStyleOfCooking")
	@ResponseBody
	public Result addOrUpdateStyleOfCooking(@RequestBody StyleOfCookingParam param) {
		cookingService.addOrUpdateStyleOfCooking(param);
		return Result.success();
	}

	@GetMapping("/findStyleOfCookingById")
	@ResponseBody
	public Result findStyleOfCookingById(String id) {
		return Result.success().setData(cookingService.findStyleOfCookingById(id));
	}

	@GetMapping("/findStyleOfCookingByPage")
	@ResponseBody
	public Result findStyleOfCookingByPage(StyleOfCookingQueryCondParam param) {
		return Result.success().setData(cookingService.findStyleOfCookingByPage(param));
	}

	@GetMapping("/delCookingById")
	@ResponseBody
	public Result delCookingById(String id) {
		cookingService.delCookingById(id);
		return Result.success();
	}

	@PostMapping("/addOrUpdateCooking")
	@ResponseBody
	public Result addOrUpdateCooking(@RequestBody CookingParam param) {
		cookingService.addOrUpdateCooking(param);
		return Result.success();
	}

	@GetMapping("/findCookingById")
	@ResponseBody
	public Result findCookingById(String id) {
		return Result.success().setData(cookingService.findCookingById(id));
	}

	@GetMapping("/findCookingByPage")
	@ResponseBody
	public Result findCookingByPage(CookingQueryCondParam param) {
		return Result.success().setData(cookingService.findCookingByPage(param));
	}

}
