package me.zohar.catering;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/dining-table")
	public String appealRecord() {
		return "admin/dining-table";
	}
	
	@GetMapping("/admin/style-of-cooking")
	public String styleOfCooking() {
		return "admin/style-of-cooking";
	}
	
	@GetMapping("/admin/cooking")
	public String cooking() {
		return "admin/cooking";
	}
	
	@GetMapping("/admin/place-order-record")
	public String placeOrderRecord() {
		return "admin/place-order-record";
	}

}
