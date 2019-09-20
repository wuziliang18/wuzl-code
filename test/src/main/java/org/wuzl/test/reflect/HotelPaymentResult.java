package org.wuzl.test.reflect;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class HotelPaymentResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String paymentNo;
	private long groupId;
	private long hotelId;
	private long orderId;
	private String orderNo;
	private int type;
	private int item;
	private int cateringType;// 餐类
	private String deptCode1;// 一级部门编码
	private String deptName1;// 一级部门名称
	private String deptCode2;// 二级部门编码
	private String deptName2;// 二级部门名称
	private String subjectCode1;// 一级科目代码
	private String subjectCode2;// 二级科目代码
	private String subjectCode3;// 三级科目代码
	private String subjectName1;// 一级科目代码
	private String subjectName2;// 二级科目代码
	private String subjectName3;// 三级科目代码
	private String subjectCommonCode2;// 二级科目汇总代码
	private String subjectSummaryCode2;// 二级科目汇总代码
	private String subjectSummaryCode3;// 三级科目汇总代码
	private String consumeCode;
	private String consumeName;
	private String consumeId = "0";
	@Deprecated
	private String payWayCode;
	@Deprecated
	private String payWayName;
	@Deprecated
	private long receivablePrice;
	private long gatheringPrice;
	private int amount;
	private long unitPrice;
	private int settleType;
	private long taxPrice;// 总税额
	private long servicePrice;// 总服务费
	private String currency;// 货币编码
	private String symbol;// 货币符号
	private BigDecimal exchangeRate = BigDecimal.ZERO;// 汇率
	private long orderRoomId;
	private long teamOrderRoomId;
	private String orderRoomNo;
	private long roomId;
	private String roomName;
	private int status;
	private long relatedPayemntId;
	private String channelCode;
	private String channel;
	private int channelType;
	private int payType;
	private long payBankId;
	private String payBankCode;
	private String payBankName;
	private int payBankType;
	private String payBankNo;
	private String paySerialNo;
	private String tradingTime;
	private String posCardCompany;
	private String posBankName;
	private String posCode;
	private String posParams;
	private int operationType;
	private String businessDate; // 营业日
	private long shiftId; // 班次 ID
	private String shiftName; // 班次名称
	private double roomNights;
	private String systemRemark;
	private String businessRemark;
	private String remark;
	private long operator;
	private String operatorName;
	private int verifyFlag;
	private String verifyTimeStr;
	private long verifyOperator;
	private String verifyOperatorName;
	private Date createTime;
	private String createTimeStr;
	private Date updateTime;
	private int received; // 是否已收款
	private int source; // 款项来源

	// 新加AR账户
	private long arAccountId;
	private String arAccountName;
	private long arCustomerId = 0L; // 协议客户ID
	private String arCustomerName = ""; // 协议客户名称

	// 临时字段 - 查询
	private String itemName;

	// 临时字段 - 统计
	private String contactorName = ""; // 姓名
	private int paymentType; // 收支类型（1=收入，2=支出）

	// 临时字段
	private int titleOrSumFlag; // 标题或者合计标志位，1-标题，2-小计，3-合计，显示报表使用
	private int serialNumber; // 序号
	private String title; // 标题

	private String cateringTypeName; // 餐饮项目名称

	// 临时字段 - 印度门店专用
	private BigDecimal tariff = new BigDecimal(0); // 消费净额
	private BigDecimal cgstRate = new BigDecimal(0); // CGST税率
	private BigDecimal sgstRate = new BigDecimal(0); // SGST税率
	private BigDecimal vatRate = new BigDecimal(0); // VAT税率
	private BigDecimal cgstAmount = new BigDecimal(0); // CGST税额
	private BigDecimal sgstAmount = new BigDecimal(0); // SGST税额
	private BigDecimal vatAmount = new BigDecimal(0); // VAT税额

	// 临时字段，报表查合计专用
	private long purePrice;// 净额
	private int count;// 数量

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getDeptCode1() {
		return deptCode1;
	}

	public void setDeptCode1(String deptCode1) {
		this.deptCode1 = deptCode1;
	}

	public String getDeptName1() {
		return deptName1;
	}

	public void setDeptName1(String deptName1) {
		this.deptName1 = deptName1;
	}

	public String getDeptCode2() {
		return deptCode2;
	}

	public void setDeptCode2(String deptCode2) {
		this.deptCode2 = deptCode2;
	}

	public String getDeptName2() {
		return deptName2;
	}

	public void setDeptName2(String deptName2) {
		this.deptName2 = deptName2;
	}

	public int getCateringType() {
		return cateringType;
	}

	public void setCateringType(int cateringType) {
		this.cateringType = cateringType;
	}

	public String getSubjectCode1() {
		return subjectCode1;
	}

	public void setSubjectCode1(String subjectCode1) {
		this.subjectCode1 = subjectCode1;
	}

	public String getSubjectCode2() {
		return subjectCode2;
	}

	public void setSubjectCode2(String subjectCode2) {
		this.subjectCode2 = subjectCode2;
	}

	public String getSubjectCode3() {
		return subjectCode3;
	}

	public void setSubjectCode3(String subjectCode3) {
		this.subjectCode3 = subjectCode3;
	}

	public String getSubjectCommonCode2() {
		return subjectCommonCode2;
	}

	public void setSubjectCommonCode2(String subjectCommonCode2) {
		this.subjectCommonCode2 = subjectCommonCode2;
	}

	public String getSubjectSummaryCode2() {
		return subjectSummaryCode2;
	}

	public void setSubjectSummaryCode2(String subjectSummaryCode2) {
		this.subjectSummaryCode2 = subjectSummaryCode2;
	}

	public String getSubjectSummaryCode3() {
		return subjectSummaryCode3;
	}

	public void setSubjectSummaryCode3(String subjectSummaryCode3) {
		this.subjectSummaryCode3 = subjectSummaryCode3;
	}

	public String getConsumeCode() {
		return consumeCode;
	}

	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	public String getConsumeName() {
		return consumeName;
	}

	public void setConsumeName(String consumeName) {
		this.consumeName = consumeName;
	}

	public String getConsumeId() {
		return consumeId;
	}

	public void setConsumeId(String consumeId) {
		this.consumeId = consumeId;
	}

	public String getPayWayCode() {
		return payWayCode;
	}

	public void setPayWayCode(String payWayCode) {
		this.payWayCode = payWayCode;
	}

	public String getPayWayName() {
		return payWayName;
	}

	public void setPayWayName(String payWayName) {
		this.payWayName = payWayName;
	}

	public long getReceivablePrice() {
		return receivablePrice;
	}

	public void setReceivablePrice(long receivablePrice) {
		this.receivablePrice = receivablePrice;
	}

	public long getGatheringPrice() {
		return gatheringPrice;
	}

	public void setGatheringPrice(long gatheringPrice) {
		this.gatheringPrice = gatheringPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getSettleType() {
		return settleType;
	}

	public void setSettleType(int settleType) {
		this.settleType = settleType;
	}

	public long getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(long taxPrice) {
		this.taxPrice = taxPrice;
	}

	public long getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(long servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public long getOrderRoomId() {
		return orderRoomId;
	}

	public void setOrderRoomId(long orderRoomId) {
		this.orderRoomId = orderRoomId;
	}

	public long getTeamOrderRoomId() {
		return teamOrderRoomId;
	}

	public void setTeamOrderRoomId(long teamOrderRoomId) {
		this.teamOrderRoomId = teamOrderRoomId;
	}

	public String getOrderRoomNo() {
		return orderRoomNo;
	}

	public void setOrderRoomNo(String orderRoomNo) {
		this.orderRoomNo = orderRoomNo;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getRelatedPayemntId() {
		return relatedPayemntId;
	}

	public void setRelatedPayemntId(long relatedPayemntId) {
		this.relatedPayemntId = relatedPayemntId;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public long getPayBankId() {
		return payBankId;
	}

	public void setPayBankId(long payBankId) {
		this.payBankId = payBankId;
	}

	public String getPayBankCode() {
		return payBankCode;
	}

	public void setPayBankCode(String payBankCode) {
		this.payBankCode = payBankCode;
	}

	public String getPayBankName() {
		return payBankName;
	}

	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}

	public int getPayBankType() {
		return payBankType;
	}

	public void setPayBankType(int payBankType) {
		this.payBankType = payBankType;
	}

	public String getPayBankNo() {
		return payBankNo;
	}

	public void setPayBankNo(String payBankNo) {
		this.payBankNo = payBankNo;
	}

	public String getPaySerialNo() {
		return paySerialNo;
	}

	public void setPaySerialNo(String paySerialNo) {
		this.paySerialNo = paySerialNo;
	}

	public String getTradingTime() {
		return tradingTime;
	}

	public void setTradingTime(String tradingTime) {
		this.tradingTime = tradingTime;
	}

	public String getPosCardCompany() {
		return posCardCompany;
	}

	public void setPosCardCompany(String posCardCompany) {
		this.posCardCompany = posCardCompany;
	}

	public String getPosBankName() {
		return posBankName;
	}

	public void setPosBankName(String posBankName) {
		this.posBankName = posBankName;
	}

	public String getPosCode() {
		return posCode;
	}

	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}

	public String getPosParams() {
		return posParams;
	}

	public void setPosParams(String posParams) {
		this.posParams = posParams;
	}

	public int getOperationType() {
		return operationType;
	}

	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public double getRoomNights() {
		return roomNights;
	}

	public void setRoomNights(double roomNights) {
		this.roomNights = roomNights;
	}

	public String getSystemRemark() {
		return systemRemark;
	}

	public void setSystemRemark(String systemRemark) {
		this.systemRemark = systemRemark;
	}

	public String getBusinessRemark() {
		return businessRemark;
	}

	public void setBusinessRemark(String businessRemark) {
		this.businessRemark = businessRemark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getOperator() {
		return operator;
	}

	public void setOperator(long operator) {
		this.operator = operator;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public int getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(int verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	public String getVerifyTimeStr() {
		return verifyTimeStr;
	}

	public void setVerifyTimeStr(String verifyTimeStr) {
		this.verifyTimeStr = verifyTimeStr;
	}

	public long getVerifyOperator() {
		return verifyOperator;
	}

	public void setVerifyOperator(long verifyOperator) {
		this.verifyOperator = verifyOperator;
	}

	public String getVerifyOperatorName() {
		return verifyOperatorName;
	}

	public void setVerifyOperatorName(String verifyOperatorName) {
		this.verifyOperatorName = verifyOperatorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public long getArAccountId() {
		return arAccountId;
	}

	public void setArAccountId(long arAccountId) {
		this.arAccountId = arAccountId;
	}

	public String getArAccountName() {
		return arAccountName;
	}

	public void setArAccountName(String arAccountName) {
		this.arAccountName = arAccountName;
	}

	public long getArCustomerId() {
		return arCustomerId;
	}

	public void setArCustomerId(long arCustomerId) {
		this.arCustomerId = arCustomerId;
	}

	public String getArCustomerName() {
		return arCustomerName;
	}

	public void setArCustomerName(String arCustomerName) {
		this.arCustomerName = arCustomerName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getContactorName() {
		return contactorName;
	}

	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public String getSubjectName1() {
		return subjectName1;
	}

	public void setSubjectName1(String subjectName1) {
		this.subjectName1 = subjectName1;
	}

	public String getSubjectName2() {
		return subjectName2;
	}

	public void setSubjectName2(String subjectName2) {
		this.subjectName2 = subjectName2;
	}

	public String getSubjectName3() {
		return subjectName3;
	}

	public void setSubjectName3(String subjectName3) {
		this.subjectName3 = subjectName3;
	}

	public int getReceived() {
		return received;
	}

	public void setReceived(int received) {
		this.received = received;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getTitleOrSumFlag() {
		return titleOrSumFlag;
	}

	public void setTitleOrSumFlag(int titleOrSumFlag) {
		this.titleOrSumFlag = titleOrSumFlag;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCateringTypeName() {
		return cateringTypeName;
	}

	public void setCateringTypeName(String cateringTypeName) {
		this.cateringTypeName = cateringTypeName;
	}

	public BigDecimal getTariff() {
		return tariff;
	}

	public void setTariff(BigDecimal tariff) {
		this.tariff = tariff;
	}

	public BigDecimal getCgstRate() {
		return cgstRate;
	}

	public void setCgstRate(BigDecimal cgstRate) {
		this.cgstRate = cgstRate;
	}

	public BigDecimal getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(BigDecimal sgstRate) {
		this.sgstRate = sgstRate;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public BigDecimal getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(BigDecimal cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public BigDecimal getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(BigDecimal sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	public long getPurePrice() {
		return purePrice;
	}

	public void setPurePrice(long purePrice) {
		this.purePrice = purePrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
