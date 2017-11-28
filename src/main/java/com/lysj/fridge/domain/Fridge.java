package com.lysj.fridge.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 冰箱表
 * 
 * @author Linyz
 */
@Entity
public class Fridge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fridgeId;
	private String number;
	private Date createDate;
	private String description;// 描述

	private String itemJson;// 该冰箱货物的json数据

	private String province; // 省
	private String city;// 市
	private String country;// 县
	private String company;// 所属公司
	private String address; // 详细地址
	private double longitude; // 经度
	private double latitude; // 纬度

	private int status; // -1:已删除;0:暂停;1:正常

	public Long getFridgeId() {
		return fridgeId;
	}

	public void setFridgeId(Long fridgeId) {
		this.fridgeId = fridgeId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemJson() {
		return itemJson;
	}

	public void setItemJson(String itemJson) {
		this.itemJson = itemJson;
	}

	@Override
	public String toString() {
		return "Fridge [fridgeId=" + fridgeId + ", number=" + number + ", createDate=" + createDate + ", description=" + description + ", itemJson=" + itemJson + ", province=" + province + ", city=" + city + ", country=" + country + ", company=" + company + ", address=" + address + ", longitude=" + longitude + ", latitude=" + latitude + ", status=" + status + "]";
	}

}
