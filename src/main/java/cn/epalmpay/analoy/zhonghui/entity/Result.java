package cn.epalmpay.analoy.zhonghui.entity;

/**
 * 支付状态类1表示成功 其他表示失败
 * 
 * @author DELL
 *
 */
public class Result {
	private String payStatus;// 支付状态
	private String payResultCode;// 支付返回码
	private String payResultDes;// 支付时返回结果
	private String deliveryStatus;// 到账(结算)状态
	private String deliveryResultCode;// 到账(结算)返回码
	private String deliveryResultDes;// 到账(结算)描述信息
	private String refundStatus;// 退款状态1表示成功 0 表示失败或者 未发生退款
	private String refundResultCode;// 退款结果返回码
	private String refundResultDes;// 退款结果 信息描述

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
		return "Result [payStatus=" + payStatus + ", payResultCode=" + payResultCode + ", payResultDes=" + payResultDes + ", deliveryStatus=" + deliveryStatus + ", deliveryResultCode=" + deliveryResultCode + ", deliveryResultDes=" + deliveryResultDes + ", refundStatus=" + refundStatus + ", refundResultCode=" + refundResultCode + ", refundResultDes=" + refundResultDes + "]";
	}

}
