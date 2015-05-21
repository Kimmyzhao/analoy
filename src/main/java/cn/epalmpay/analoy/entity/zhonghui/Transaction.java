package cn.epalmpay.analoy.entity.zhonghui;

/**
 * 交易类型type为 feephone是手机充值
 * 
 * @author DELL
 *
 */
public class Transaction {
	private String qdOrderId;// 系统流水号
	private String feePhone;// 到账通知短信
	private String faceValue;// 面值,充值金额
	private double price;// 支付金额
	private double rebateMoney;// 分润返回金额

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
		return "Transaction [qdOrderId=" + qdOrderId + ", feePhone=" + feePhone + ", faceValue=" + faceValue + ", price=" + price + ", rebateMoney=" + rebateMoney + "]";
	}
}
