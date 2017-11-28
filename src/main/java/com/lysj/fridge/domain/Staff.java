package com.lysj.fridge.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long staffId; // 所属员工Id(必选,登录成功后会得到)
	private String username; // 用户名
	private String password; // 密码
	private int sex; // 性别
	private String phone; // 电话号码
	private String realname;// 真实姓名
	private String idNum; // 身份证号码
	private String description; //
	private Date createDate; // 注册时间
	private int status; // 员工状态: -3:已删除;-2:离职;-1:未激活;0:暂停;1:正常

	private Long roleId; // 用户的角色
	private String roleName; // 角色名称
	private Long departmentId; // 部门
	private String departmentName; // 部门名称
	private Long companyId; // 所属公司
	private String companyName; // 公司名称
	private String privIds; // 权限Ids
	private String privJson; // 权限明细

	private String loginIp; // 登录Ip
	private Long loginDate; // 登录时间
	private String token; // 一串随机的uuid
	private Long expires; // token的到期时间的ms值

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPrivIds() {
		return privIds;
	}

	public void setPrivIds(String privIds) {
		this.privIds = privIds;
	}

	public String getPrivJson() {
		return privJson;
	}

	public void setPrivJson(String privJson) {
		this.privJson = privJson;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Long getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Long loginDate) {
		this.loginDate = loginDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExpires() {
		return expires;
	}

	public void setExpires(Long expires) {
		this.expires = expires;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", username=" + username + ", password=" + password + ", sex=" + sex + ", phone=" + phone + ", realname=" + realname + ", idNum=" + idNum + ", description=" + description + ", createDate=" + createDate + ", status=" + status + ", roleId=" + roleId + ", roleName=" + roleName + ", departmentId=" + departmentId + ", departmentName=" + departmentName + ", companyId=" + companyId + ", companyName=" + companyName + ", privIds=" + privIds + ", privJson=" + privJson + ", loginIp=" + loginIp + ", loginDate=" + loginDate + ", token=" + token + ", expires=" + expires + "]";
	}

}
