package com.lysj.fridge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Linyz
 * @create 2017年3月6日
 * @description 角色实体类
 */
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	private String name;// 角色名称

	@Column(length = 500)
	private String description;// 角色说明

	private String privIds;// 权限Ids
	private String privJson;// 权限明细

	private String deptIds;// 负责部门ids
	private String deptJson;// 负责部门明细
	private String compIds;// 负责公司ids

	private Long companyId;// 所属公司
	private String companyName;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrivIds() {
		return privIds;
	}

	public void setPrivIds(String privIds) {
		this.privIds = privIds;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getPrivJson() {
		return privJson;
	}

	public void setPrivJson(String privJson) {
		this.privJson = privJson;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getDeptJson() {
		return deptJson;
	}

	public void setDeptJson(String deptJson) {
		this.deptJson = deptJson;
	}

	public String getCompIds() {
		return compIds;
	}

	public void setCompIds(String compIds) {
		this.compIds = compIds;
	}
}
