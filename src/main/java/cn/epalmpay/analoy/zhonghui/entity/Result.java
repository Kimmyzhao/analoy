package cn.epalmpay.analoy.zhonghui.entity;

public class Result {
	private String payStatus;
	private String payResultCode;
	private String payResultDes;
	private String deliveryStatus;
	private String deliveryResultCode;
	private String deliveryResultDes;
	private String refundStatus;
	private String refundResultCode;
	private String refundResultDes;

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayResultCode() {
		return payResultCode;
	}

	public void setPayResultCode(String payResultCode) {
		this.payResultCode = payResultCode;
	}

	public String getPayResultDes() {
		return payResultDes;
	}

	public void setPayResultDes(String payResultDes) {
		this.payResultDes = payResultDes;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryResultCode() {
		return deliveryResultCode;
	}

	public void setDeliveryResultCode(String deliveryResultCode) {
		this.deliveryResultCode = deliveryResultCode;
	}

	public String getDeliveryResultDes() {
		return deliveryResultDes;
	}

	public void setDeliveryResultDes(String deliveryResultDes) {
		this.deliveryResultDes = deliveryResultDes;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getRefundResultCode() {
		return refundResultCode;
	}

	public void setRefundResultCode(String refundResultCode) {
		this.refundResultCode = refundResultCode;
	}

	public String getRefundResultDes() {
		return refundResultDes;
	}

	public void setRefundResultDes(String refundResultDes) {
		this.refundResultDes = refundResultDes;
	}

	@Override
	public String toString() {
		return "Result [payStatus=" + payStatus + ", payResultCode="
				+ payResultCode + ", payResultDes=" + payResultDes
				+ ", deliveryStatus=" + deliveryStatus
				+ ", deliveryResultCode=" + deliveryResultCode
				+ ", deliveryResultDes=" + deliveryResultDes
				+ ", refundStatus=" + refundStatus + ", refundResultCode="
				+ refundResultCode + ", refundResultDes=" + refundResultDes
				+ "]";
	}

}
