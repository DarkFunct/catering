package me.zohar.catering.diningtable.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.zohar.catering.common.vo.Result;
import me.zohar.catering.diningtable.param.DiningTableParam;
import me.zohar.catering.diningtable.param.DiningTableQueryCondParam;
import me.zohar.catering.diningtable.param.PlaceOrderParam;
import me.zohar.catering.diningtable.param.PlaceOrderRecordQueryCondParam;
import me.zohar.catering.diningtable.service.DiningTableService;
import me.zohar.catering.diningtable.vo.OrderDishesRecordVO;

@Controller
@RequestMapping("/diningTable")
public class DiningTableController {

	@Autowired
	private DiningTableService diningTableService;
	
	@GetMapping("/noticeSettleAccount")
	@ResponseBody
	public Result noticeSettleAccount(String id) {
		diningTableService.noticeSettleAccount(id);
		return Result.success();
	}
	
	@GetMapping("/settleAccount")
	@ResponseBody
	public Result settleAccount(String id) {
		diningTableService.settleAccount(id);
		return Result.success();
	}

	@GetMapping("/updateOrderDishesRecordState")
	@ResponseBody
	public Result updateOrderDishesRecordState(String id, String state) {
		diningTableService.updateOrderDishesRecordState(id, state);
		return Result.success();
	}

	@GetMapping("/findPlaceOrderRecordById")
	@ResponseBody
	public Result findPlaceOrderRecordById(String id) {
		return Result.success().setData(diningTableService.findPlaceOrderRecordById(id));
	}

	@GetMapping("/findPlaceOrderRecordByPage")
	@ResponseBody
	public Result findPlaceOrderRecordByPage(PlaceOrderRecordQueryCondParam param) {
		return Result.success().setData(diningTableService.findPlaceOrderRecordByPage(param));
	}

	@GetMapping("/withdrawTheTable")
	@ResponseBody
	public Result withdrawTheTable(String tableId) {
		diningTableService.withdrawTheTable(tableId);
		return Result.success();
	}

	@GetMapping("/findOrderDishesRecordByBookingId")
	@ResponseBody
	public Result findOrderDishesRecordByBookingId(String bookingId) {
		List<OrderDishesRecordVO> orderDishesRecords = diningTableService.findOrderDishesRecordByBookingId(bookingId);
		return Result.success().setData(orderDishesRecords);
	}

	@GetMapping("/findAllFreeDiningTable")
	@ResponseBody
	public Result findAllFreeDiningTable() {
		return Result.success().setData(diningTableService.findAllFreeDiningTable());
	}

	@GetMapping("/booking")
	@ResponseBody
	public Result booking(String id) {
		return Result.success().setData(diningTableService.booking(id));
	}

	@PostMapping("/orderDishes")
	@ResponseBody
	public Result orderDishes(@RequestBody PlaceOrderParam param) {
		diningTableService.orderDishes(param);
		return Result.success();
	}

	@GetMapping("/delById")
	@ResponseBody
	public Result delById(String id) {
		diningTableService.delById(id);
		return Result.success();
	}

	@PostMapping("/addOrUpdateDiningTable")
	@ResponseBody
	public Result addOrUpdateDiningTable(@RequestBody DiningTableParam param) {
		diningTableService.addOrUpdateDiningTable(param);
		return Result.success();
	}

	@GetMapping("/findById")
	@ResponseBody
	public Result findById(String id) {
		return Result.success().setData(diningTableService.findById(id));
	}

	@GetMapping("/findDiningTableByPage")
	@ResponseBody
	public Result findDiningTableByPage(DiningTableQueryCondParam param) {
		return Result.success().setData(diningTableService.findDiningTableByPage(param));
	}

}
