package com.rays.ctl;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.ORSResponse;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentService;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "User")
public class UserCtl {

	@Autowired
	public UserService userService;

	@Autowired
	public AttachmentService attachmentService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody UserForm form) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = new UserDTO();
		dto.setId(form.getId());
		dto.setFirstName(form.getFirstName());
		dto.setLastName(form.getLastName());
		dto.setLoginId(form.getLoginId());
		dto.setPassword(form.getPassword());
		dto.setDob(form.getDob());
		dto.setRoleId(form.getRoleId());

		if (dto.getId() != null && dto.getId() > 0) {
			userService.update(dto);
			res.addData(dto.getId());
			res.addMessage("Data Updated Successfully..!!");
		} else {
			long pk = userService.add(dto);
			res.addData(pk);
			res.addMessage("Data added Successfully..!!");
		}
		return res;
	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = userService.findById(id);

		res.addData(dto);

		return res;
	}

	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		for (long id : ids) {
			userService.delete(id);
		}

		res.addMessage("data deleted successfully");

		return res;
	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody UserForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();

		UserDTO dto = new UserDTO();

		dto.setFirstName(form.getFirstName());

		List list = userService.search(dto, pageNo, 5);

		if (list.size() == 0) {
			res.addMessage("Result not found...!!!");
		} else {
			res.addData(list);
		}
		return res;
	}

	@PostMapping("/profilePic/{userId}")
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file,
			HttpServletRequest req) {

		AttachmentDTO attachmentDto = new AttachmentDTO(file);

		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(userId);

		UserDTO userDto = userService.findById(userId);

		if (userDto.getImageId() != null && userDto.getImageId() > 0) {

			attachmentDto.setId(userDto.getImageId());

		}

		Long imageId = attachmentService.save(attachmentDto);

		if (userDto.getImageId() == null) {

			userDto.setImageId(imageId);

			userService.update(userDto);
		}

		ORSResponse res = new ORSResponse();

		res.addResult("imageId", imageId);

		return res;
	}

	@GetMapping("/profilePic/{userId}")
	public void downloadPic(@PathVariable Long userId, HttpServletResponse response) {

		try {

			UserDTO userDto = userService.findById(userId);

			AttachmentDTO attachmentDTO = null;

			if (userDto != null) {
				attachmentDTO = attachmentService.findById(userDto.getImageId());
			}

			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());
				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();
			} else {
				response.getWriter().write("ERROR: File not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}