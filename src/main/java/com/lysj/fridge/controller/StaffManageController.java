package com.lysj.fridge.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lysj.base.BaseContent;
import com.lysj.fridge.domain.Company;
import com.lysj.fridge.domain.Department;
import com.lysj.fridge.domain.Role;
import com.lysj.fridge.domain.Staff;

import cn.soul.util.web.ParamUtil;
import cn.soul.util.web.Resp;

@RestController
@RequestMapping("/staffManage")
public class StaffManageController extends BaseContent {

	@PostMapping(value = "/add")
	public Resp add(Staff model) {
		if (ParamUtil.isBlack(model.getUsername())) {
			return new Resp(Resp.PARAM_ERROR, "请输入用户名!");
		}
		if (ParamUtil.isBlack(model.getRealname())) {
			return new Resp(Resp.PARAM_ERROR, "请输入真实姓名!");
		}
		if (ParamUtil.isBlack(model.getPhone())) {
			return new Resp(Resp.PARAM_ERROR, "请输入电话!");
		}
		if (ParamUtil.isBlack(model.getIdNum())) {
			return new Resp(Resp.PARAM_ERROR, "请输入身份证号码!");
		}
		if (ParamUtil.isBlack(model.getRoleId())) {
			return new Resp(Resp.PARAM_ERROR, "请选择角色名称!");
		}
		if (staffRepository.findByUsername(model.getUsername()) != null) {
			return new Resp(Resp.PARAM_ERROR, "账号已经存在!");
		}

		Role role = roleRepository.findOne(model.getRoleId());
		if (role == null) {
			return new Resp(Resp.PARAM_ERROR, "请选择角色名称!");
		}
		model.setRoleName(role.getName());
		model.setPrivIds(role.getPrivIds());
		model.setPrivJson(role.getPrivJson());

		Department department = departmentRepository.findOne(model.getDepartmentId());
		if (department == null) {
			return new Resp(Resp.PARAM_ERROR, "请选择所属部门!");
		}
		model.setDepartmentName(department.getName());

		Company company = companyRepository.findOne(model.getCompanyId());
		if (company == null) {
			return new Resp(Resp.PARAM_ERROR, "请选择所属公司!");
		}
		model.setCompanyName(company.getName());

		model.setPassword(ParamUtil.md5("jince123"));

		model.setCreateDate(new Date());
		model.setStatus(1);
		staffRepository.save(model);
		return new Resp("添加成功!");
	}

	@PostMapping(value = "/initPassword")
	public Resp initPassword(Staff model) {
		Staff staff = staffRepository.findOne(model.getStaffId());
		if (staff == null) {
			return new Resp(Resp.PARAM_ERROR, "员工错误!");
		}
		staff.setPassword(ParamUtil.md5(DEFAULT_PASSWORD));
		staffRepository.save(staff);
		return new Resp(staff);
	}

	@GetMapping(value = "/list")
	public Resp list() {
		return new Resp(staffRepository.findAll());
	}

}
