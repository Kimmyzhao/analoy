package cn.epalmpay.analoy.entity.po;

public class PageTrade {
	String paytype;
	String eqno;
	String bankName;
	String cardno;
	String cardtype;
	String tradetype;
	double money;
	double feeRate;

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getEqno() {
		return eqno;
	}

	public void setEqno(String eqno) {
		this.eqno = eqno;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public PageTrade() {
		super();
	}

	public double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(double feeRate) {
		this.feeRate = feeRate;
	}

	public PageTrade(String paytype, String eqno, String bankName, String cardno, String cardtype, String tradetype, double money) {
		this.paytype = paytype;
		this.eqno = eqno;
		this.bankName = bankName;
		this.cardno = cardno;
		this.cardtype = cardtype;
		this.tradetype = tradetype;
		this.money = money;
	}

	public PageTrade(String paytype, String eqno, String bankName, String cardno, String cardtype, String tradetype, double money, double feeRate) {
		super();
		this.paytype = paytype;
		this.eqno = eqno;
		this.bankName = bankName;
		this.cardno = cardno;
		this.cardtype = cardtype;
		this.tradetype = tradetype;
		this.money = money;
		this.feeRate = feeRate;
	}

}
