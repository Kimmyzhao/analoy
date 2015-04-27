package cn.epalmpay.analoy.zhonghui.entity;

public class TradeRecord {
	private String keyDeviceSerialNo;// 设备KSN号
	private String payCardNo;//
	private String userPhone;
	private String partnerNo;
	private String orderId;
	private String createTime;
	private String finalTime;
	private Transaction transaction;
	private Result result;
	private String remark;
	private String signature;

	public String getKeyDeviceSerialNo() {
		return keyDeviceSerialNo;
	}

	public void setKeyDeviceSerialNo(String keyDeviceSerialNo) {
		this.keyDeviceSerialNo = keyDeviceSerialNo;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getPartnerNo() {
		return partnerNo;
	}

	public void setPartnerNo(String partnerNo) {
		this.partnerNo = partnerNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(String finalTime) {
		this.finalTime = finalTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "TradeRecord [keyDeviceSerialNo=" + keyDeviceSerialNo
				+ ", payCardNo=" + payCardNo + ", userPhone=" + userPhone
				+ ", partnerNo=" + partnerNo + ", orderId=" + orderId
				+ ", createTime=" + createTime + ", finalTime=" + finalTime
				+ ", transaction=" + transaction + ", result=" + result
				+ ", remark=" + remark + ", signature=" + signature + "]";
	}

}
