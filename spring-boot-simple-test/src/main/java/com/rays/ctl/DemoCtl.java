package com.rays.ctl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.DemoResponse;
import com.rays.common.ORSResponse;
import com.rays.dto.Person;

@RestController
@RequestMapping(value = "Demo")
public class DemoCtl {

	@GetMapping
	public DemoResponse display() {

		DemoResponse res = new DemoResponse();

		Person dto1 = new Person();

		dto1.setFirstName("sagar");
		dto1.setLastName("yash");
		dto1.setLoginId("pqr@gmail.com");
		dto1.setPassword("pqr123");

		Person dto2 = new Person();

		dto2.setFirstName("abc");
		dto2.setLastName("xyz");
		dto2.setLoginId("abc@gmail.com");
		dto2.setPassword("abc123");

		Map map = new HashMap();
		map.put("dto1", dto1);
		map.put("dto2", dto2);

		res.setResult(map);

		res.setMessage("data addedd successfully");

		res.setData("person object");

		return res;
	}

	@GetMapping("testORSResponse")
	public ORSResponse testORSResponse() {

		ORSResponse res = new ORSResponse();

		Map errors = new HashMap();
		errors.put("firstName", "first name is required");
		errors.put("lastName", "last name is required");
		errors.put("loginId", "login id is required");
		errors.put("password", "password is required");

		res.addInputError(errors);

		Person dto = new Person();

		dto.setFirstName("sagar");
		dto.setLastName("yash");
		dto.setLoginId("pqr@gmail.com");
		dto.setPassword("pqr123");

		res.addData(dto);

		res.addMessage("login & password invalid");

		res.addResult("token", "highcfc1tr4564465ghcgf");
		
		res.setSuccess(true);

		return res;
	}

}
