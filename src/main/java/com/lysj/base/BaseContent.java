package com.lysj.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.lysj.fridge.domain.Staff;
import com.lysj.fridge.domain.User;
import com.lysj.fridge.repository.CompanyRepository;
import com.lysj.fridge.repository.DepartmentRepository;
import com.lysj.fridge.repository.PrivilegeRepository;
import com.lysj.fridge.repository.RoleRepository;
import com.lysj.fridge.repository.StaffRepository;
import com.lysj.fridge.repository.UserRepository;

/**
 * 基础的容器,此容器包含所有Service和Repository的对象(单例)
 */
@Component
public class BaseContent {

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), true));
	}

	// =================默认配置============================
	protected static final long LOGIN_EXPIRES_USER = 1000 * 60 * 60 * 48;// 单位:ms/登录过期时间(用户)[48小时]
	protected static final long LOGIN_EXPIRES_STAFF = 1000 * 60 * 60 * 1;// 单位:ms/登录过期时间(员工)[1小时]

	protected static final String DEFAULT_PASSWORD = "123456"; // 默认密码
	// =============== 公共函数==========================

	/**
	 * 获取目前登录的用户
	 */
	protected User currentUser() {
		return (User) request.getAttribute("user");
	}

	/**
	 * 获取目前登录的员工
	 */
	protected Staff currentStaff() {
		return (Staff) request.getAttribute("staff");
	}

	@Resource
	protected HttpServletRequest request;
	@Resource
	protected HttpServletResponse response;
	@Resource
	protected StaffRepository staffRepository;
	@Resource
	protected UserRepository userRepository;
	@Resource
	protected PrivilegeRepository privilegeRepository;
	@Resource
	protected CompanyRepository companyRepository;
	@Resource
	protected RoleRepository roleRepository;
	@Resource
	protected DepartmentRepository departmentRepository;

}
