package com.rays.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.DemoResponse;

@RestController
@RequestMapping(value = "Demo")
public class DemoCtl {

	@GetMapping
	public String display() {
		return "Spring Boot Practicals Application";
	}

	@GetMapping("test")
	public DemoResponse test() {

		DemoResponse res = new DemoResponse();
		res.setName("spring boot practicals");
		res.setStatus("data added successfully");

		return res;
	}
}