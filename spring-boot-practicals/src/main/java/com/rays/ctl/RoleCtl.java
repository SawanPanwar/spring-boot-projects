package com.rays.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.RoleDTO;
import com.rays.form.RoleForm;
import com.rays.service.RoleService;

@RestController
@RequestMapping(value = "Role")
public class RoleCtl {

	@Autowired
	public RoleService roleService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody RoleForm form) {

		ORSResponse res = new ORSResponse();

		RoleDTO dto = new RoleDTO();
		dto.setName(form.getName());
		dto.setDescription(form.getDescription());

		long pk = roleService.add(dto);

		res.addData(pk);
		res.addMessage("Role addedd successfully..!!!");
		return res;
	}
}