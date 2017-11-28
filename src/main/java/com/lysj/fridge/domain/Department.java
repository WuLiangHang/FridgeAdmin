package com.lysj.fridge.domain;

import javax.persistence.*;

/**
 * @author Linyz
 * @create 2017年3月6日
 * @description 部门实体类
 */
@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;

	private String name;// 部门名称

	@Column(length = 500)
	private String description;// 职能说明

	private Long staffId; // 部门负责人工号
	private String staffName; // 部门负责人姓名
	private String mobile;// 部门联系电话

	private Long companyId;// 所属公司
	private String companyName;// 所属公司

	private Long parentId;// 父部门Id
	private String parentName;// 父部门名称

	private int totalCount;// 部门总量
	private int unAssignedCount;// 未分配的数量(部门剩余)
	private int staffRemainCount;// 员工剩余(员工剩余总和)
	private String pictureAddress;//图片地址

	public String getPictureAddress() {
		return pictureAddress;
	}

	public void setPictureAddress(String pictureAddress) {
		this.pictureAddress = pictureAddress;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
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

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getUnAssignedCount() {
		return unAssignedCount;
	}

	public void setUnAssignedCount(int unAssignedCount) {
		this.unAssignedCount = unAssignedCount;
	}

	public int getStaffRemainCount() {
		return staffRemainCount;
	}

	public void setStaffRemainCount(int staffRemainCount) {
		this.staffRemainCount = staffRemainCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "Department{" +
				"departmentId=" + departmentId +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", staffId=" + staffId +
				", staffName='" + staffName + '\'' +
				", mobile='" + mobile + '\'' +
				", companyId=" + companyId +
				", companyName='" + companyName + '\'' +
				", parentId=" + parentId +
				", parentName='" + parentName + '\'' +
				", totalCount=" + totalCount +
				", unAssignedCount=" + unAssignedCount +
				", staffRemainCount=" + staffRemainCount +
				'}';
	}
}
