package cn.epalmpay.analoy.zhonghui.entity;

public class Transaction {
	private String qdOrderId;
	private String feePhone;// 电话号码
	private String faceValue;
	private double price;
	private double rebateMoney;

	public String getQdOrderId() {
		return qdOrderId;
	}

	public void setQdOrderId(String qdOrderId) {
		this.qdOrderId = qdOrderId;
	}

	public String getFeePhone() {
		return feePhone;
	}

	public void setFeePhone(String feePhone) {
		this.feePhone = feePhone;
	}

	public String getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRebateMoney() {
		return rebateMoney;
	}

	public void setRebateMoney(double rebateMoney) {
		this.rebateMoney = rebateMoney;
	}

	@Override
	public String toString() {
		return "Transaction [qdOrderId=" + qdOrderId + ", feePhone=" + feePhone
				+ ", faceValue=" + faceValue + ", price=" + price
				+ ", rebateMoney=" + rebateMoney + "]";
	}
}
