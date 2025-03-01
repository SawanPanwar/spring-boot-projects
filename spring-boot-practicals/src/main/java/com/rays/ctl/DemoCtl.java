package com.rays.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.DemoResponse;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;

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

	@GetMapping("testOrsResponse")
	public ORSResponse testOrsResponse() {

		ORSResponse res = new ORSResponse();

		res.setSuccess(true);

		res.addMessage("data added successfully");

		/*
		 * UserDTO dto = new UserDTO(); dto.setId(1); dto.setFirstName("abc");
		 * dto.setLastName("xyz"); dto.setLoginId("abc@gmail.com");
		 * dto.setPassword("1234");
		 * 
		 * res.addData(dto);
		 * 
		 * UserDTO dto1 = new UserDTO(); dto1.setId(1); dto1.setFirstName("abc");
		 * dto1.setLastName("xyz"); dto1.setLoginId("abc@gmail.com");
		 * dto1.setPassword("1234");
		 * 
		 * UserDTO dto2 = new UserDTO(); dto2.setId(2); dto2.setFirstName("abc");
		 * dto2.setLastName("xyz"); dto2.setLoginId("abc@gmail.com");
		 * dto2.setPassword("1234");
		 * 
		 * UserDTO dto3 = new UserDTO(); dto3.setId(3); dto3.setFirstName("abc");
		 * dto3.setLastName("xyz"); dto3.setLoginId("abc@gmail.com");
		 * dto3.setPassword("1234");
		 * 
		 * List list = new ArrayList(); list.add(dto1); list.add(dto2); list.add(dto3);
		 * 
		 * res.addData(list);
		 */
		Map errors = new HashMap();
		errors.put("firstName", "First Name is required");
		errors.put("lastName", "Last Name is required");
		errors.put("loginId", "Login ID is required");
		errors.put("password", "Password is required");

		res.addInputError(errors);

		//res.addResult("userList", list);

		return res;
	}
}