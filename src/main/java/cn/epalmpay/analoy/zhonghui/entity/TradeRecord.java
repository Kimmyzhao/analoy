package cn.epalmpay.analoy.zhonghui.entity;

public class TradeRecord {
	private String keyDeviceSerialNo;// 终端编号
	private String payCardNo;// 付款卡号
	private String userPhone;// 预留手机号码
	private String partnerNo;// 交易批次号
	private String orderId;// 交易流水号
	private String createTime;// 交易时间
	private String finalTime;// 最后修改时间,更新时间
	private Transaction transaction;// 交易类型
	private Result result;// 支付状态
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
		return "TradeRecord [keyDeviceSerialNo=" + keyDeviceSerialNo + ", payCardNo=" + payCardNo + ", userPhone=" + userPhone + ", partnerNo=" + partnerNo + ", orderId=" + orderId + ", createTime=" + createTime + ", finalTime=" + finalTime + ", transaction=" + transaction + ", result=" + result + ", remark=" + remark + ", signature=" + signature + "]";
	}

}
