package com.fengqiao.entity;

/**
 * 
 * Author zouyang
 * 电子运单
 * 
 */
public class Waybill {

	private int id;
	private String mailNo;
	private String returnTrackingNo;
	private String deliverCompany;
	private String deliverName;
	private String deliverTel;
	private String deliverMobile;
	private String deliverShipperCode;
	private String deliverCountry;
	private String deliverProvince;
	private String deliverCity;
	private String deliverCounty;
	private String deliverAddress;
	private String zipCode;
	private String consignerCompany;
	private String consignerName;
	private String consignerTel;
	private String consignerMobile;
	private String consignerShipperCode;
	private String consignerCountry;
	private String consignerProvince;
	private String consignerCity;
	private String consignerCounty;
	private String consignerAddress;
	private String destCode;
	private String monthAccount;
	private String payMethod;
	private String topLogo;
	private String totalFee;
	private String appId;
	private String appKey;
	private String expressType;
	private String insureValue;
	private String codValue;
	private String codMonthAccount;
	private String encryptMobile;
	private String encryptCustName;
	private String cargo;
	private String cargoCount;
	private String cargoUnit;
	private String cargoWeight;
	private String cargoAmount;
	private String cargoTotalWeight;
	private String sku;
	private String remark;
	private String cargo1;
	private String cargoCount1;
	private String cargoUnit1;
	private String cargoWeight1;
	private String cargoAmount1;
	private String cargoTotalWeight1;
	private String sku1;
	private String remark1;
	//分页
	private int pageSize;
	private int rowStart;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMailNo() {
		return mailNo;
	}
	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}
	public String getReturnTrackingNo() {
		return returnTrackingNo;
	}
	public void setReturnTrackingNo(String returnTrackingNo) {
		this.returnTrackingNo = returnTrackingNo;
	}
	public String getDeliverCompany() {
		return deliverCompany;
	}
	public void setDeliverCompany(String deliverCompany) {
		this.deliverCompany = deliverCompany;
	}
	public String getDeliverName() {
		return deliverName;
	}
	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}
	public String getDeliverTel() {
		return deliverTel;
	}
	public void setDeliverTel(String deliverTel) {
		this.deliverTel = deliverTel;
	}
	public String getDeliverMobile() {
		return deliverMobile;
	}
	public void setDeliverMobile(String deliverMobile) {
		this.deliverMobile = deliverMobile;
	}
	public String getDeliverShipperCode() {
		return deliverShipperCode;
	}
	public void setDeliverShipperCode(String deliverShipperCode) {
		this.deliverShipperCode = deliverShipperCode;
	}
	public String getDeliverCountry() {
		return deliverCountry;
	}
	public void setDeliverCountry(String deliverCountry) {
		this.deliverCountry = deliverCountry;
	}
	public String getDeliverProvince() {
		return deliverProvince;
	}
	public void setDeliverProvince(String deliverProvince) {
		this.deliverProvince = deliverProvince;
	}
	public String getDeliverCity() {
		return deliverCity;
	}
	public void setDeliverCity(String deliverCity) {
		this.deliverCity = deliverCity;
	}
	public String getDeliverCounty() {
		return deliverCounty;
	}
	public void setDeliverCounty(String deliverCounty) {
		this.deliverCounty = deliverCounty;
	}
	public String getDeliverAddress() {
		return deliverAddress;
	}
	public void setDeliverAddress(String deliverAddress) {
		this.deliverAddress = deliverAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getConsignerCompany() {
		return consignerCompany;
	}
	public void setConsignerCompany(String consignerCompany) {
		this.consignerCompany = consignerCompany;
	}
	public String getConsignerName() {
		return consignerName;
	}
	public void setConsignerName(String consignerName) {
		this.consignerName = consignerName;
	}
	public String getConsignerTel() {
		return consignerTel;
	}
	public void setConsignerTel(String consignerTel) {
		this.consignerTel = consignerTel;
	}
	public String getConsignerMobile() {
		return consignerMobile;
	}
	public void setConsignerMobile(String consignerMobile) {
		this.consignerMobile = consignerMobile;
	}
	public String getConsignerShipperCode() {
		return consignerShipperCode;
	}
	public void setConsignerShipperCode(String consignerShipperCode) {
		this.consignerShipperCode = consignerShipperCode;
	}
	public String getConsignerCountry() {
		return consignerCountry;
	}
	public void setConsignerCountry(String consignerCountry) {
		this.consignerCountry = consignerCountry;
	}
	public String getConsignerProvince() {
		return consignerProvince;
	}
	public void setConsignerProvince(String consignerProvince) {
		this.consignerProvince = consignerProvince;
	}
	public String getConsignerCity() {
		return consignerCity;
	}
	public void setConsignerCity(String consignerCity) {
		this.consignerCity = consignerCity;
	}
	public String getConsignerCounty() {
		return consignerCounty;
	}
	public void setConsignerCounty(String consignerCounty) {
		this.consignerCounty = consignerCounty;
	}
	public String getConsignerAddress() {
		return consignerAddress;
	}
	public void setConsignerAddress(String consignerAddress) {
		this.consignerAddress = consignerAddress;
	}
	public String getDestCode() {
		return destCode;
	}
	public void setDestCode(String destCode) {
		this.destCode = destCode;
	}
	public String getMonthAccount() {
		return monthAccount;
	}
	public void setMonthAccount(String monthAccount) {
		this.monthAccount = monthAccount;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getTopLogo() {
		return topLogo;
	}
	public void setTopLogo(String topLogo) {
		this.topLogo = topLogo;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getExpressType() {
		return expressType;
	}
	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}
	public String getInsureValue() {
		return insureValue;
	}
	public void setInsureValue(String insureValue) {
		this.insureValue = insureValue;
	}
	public String getCodValue() {
		return codValue;
	}
	public void setCodValue(String codValue) {
		this.codValue = codValue;
	}
	public String getCodMonthAccount() {
		return codMonthAccount;
	}
	public void setCodMonthAccount(String codMonthAccount) {
		this.codMonthAccount = codMonthAccount;
	}
	public String getEncryptMobile() {
		return encryptMobile;
	}
	public void setEncryptMobile(String encryptMobile) {
		this.encryptMobile = encryptMobile;
	}
	public String getEncryptCustName() {
		return encryptCustName;
	}
	public void setEncryptCustName(String encryptCustName) {
		this.encryptCustName = encryptCustName;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCargoCount() {
		return cargoCount;
	}
	public void setCargoCount(String cargoCount) {
		this.cargoCount = cargoCount;
	}
	public String getCargoUnit() {
		return cargoUnit;
	}
	public void setCargoUnit(String cargoUnit) {
		this.cargoUnit = cargoUnit;
	}
	public String getCargoWeight() {
		return cargoWeight;
	}
	public void setCargoWeight(String cargoWeight) {
		this.cargoWeight = cargoWeight;
	}
	public String getCargoAmount() {
		return cargoAmount;
	}
	public void setCargoAmount(String cargoAmount) {
		this.cargoAmount = cargoAmount;
	}
	public String getCargoTotalWeight() {
		return cargoTotalWeight;
	}
	public void setCargoTotalWeight(String cargoTotalWeight) {
		this.cargoTotalWeight = cargoTotalWeight;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCargo1() {
		return cargo1;
	}
	public void setCargo1(String cargo1) {
		this.cargo1 = cargo1;
	}
	public String getCargoCount1() {
		return cargoCount1;
	}
	public void setCargoCount1(String cargoCount1) {
		this.cargoCount1 = cargoCount1;
	}
	public String getCargoUnit1() {
		return cargoUnit1;
	}
	public void setCargoUnit1(String cargoUnit1) {
		this.cargoUnit1 = cargoUnit1;
	}
	public String getCargoWeight1() {
		return cargoWeight1;
	}
	public void setCargoWeight1(String cargoWeight1) {
		this.cargoWeight1 = cargoWeight1;
	}
	public String getCargoAmount1() {
		return cargoAmount1;
	}
	public void setCargoAmount1(String cargoAmount1) {
		this.cargoAmount1 = cargoAmount1;
	}
	public String getCargoTotalWeight1() {
		return cargoTotalWeight1;
	}
	public void setCargoTotalWeight1(String cargoTotalWeight1) {
		this.cargoTotalWeight1 = cargoTotalWeight1;
	}
	public String getSku1() {
		return sku1;
	}
	public void setSku1(String sku1) {
		this.sku1 = sku1;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowStart() {
		return rowStart;
	}
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	
	
}
