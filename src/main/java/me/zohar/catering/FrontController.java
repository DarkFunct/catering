package me.zohar.catering;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

	@GetMapping("/front/order-dishes")
	public String orderDishes() {
		return "front/order-dishes";
	}
	
	@GetMapping("/front/hall")
	public String hall() {
		return "front/hall";
	}

}
