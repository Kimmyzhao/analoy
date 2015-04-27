package cn.epalmpay.analoy.qiandaibao.entity;

public class OrderList {
	private String time;// 交易时间，例如：2012-03-09 16:26:34
	private String orderid;// 订单号,钱袋宝支付订单的订单号
	private String agentno;// 商户编号,钱袋的商户唯一编号
	private String presettletime;// 预计结算时间 例如：2012-03-09 16:26:34
	private String settletime;// 实际结算时间 例如：2012-03-09 16:26:34
	private double money;// 订单支付金额 单位:元例如:0.00
	private double settlemoney;// 应结算金额
	private double fee;// 手续费
	private String eqno;// 设备编号
	private String cardno;// 银行卡号，前6后4中间*号
	private String cardtype;// 银行卡类型1:借记卡,2:贷记卡
	private String bankname;// 银行卡号名称

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getAgentno() {
		return agentno;
	}

	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}

	public String getPresettletime() {
		return presettletime;
	}

	public void setPresettletime(String presettletime) {
		this.presettletime = presettletime;
	}

	public String getSettletime() {
		return settletime;
	}

	public void setSettletime(String settletime) {
		this.settletime = settletime;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getSettlemoney() {
		return settlemoney;
	}

	public void setSettlemoney(double settlemoney) {
		this.settlemoney = settlemoney;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getEqno() {
		return eqno;
	}

	public void setEqno(String eqno) {
		this.eqno = eqno;
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

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public OrderList(String time, String orderid, String agentno,
			String presettletime, String settletime, double money,
			double settlemoney, double fee, String eqno, String cardno,
			String cardtype, String bankname) {
		super();
		this.time = time;
		this.orderid = orderid;
		this.agentno = agentno;
		this.presettletime = presettletime;
		this.settletime = settletime;
		this.money = money;
		this.settlemoney = settlemoney;
		this.fee = fee;
		this.eqno = eqno;
		this.cardno = cardno;
		this.cardtype = cardtype;
		this.bankname = bankname;
	}

	public OrderList() {
		super();
	}

	@Override
	public String toString() {
		return "OrderList [time=" + time + ", orderid=" + orderid
				+ ", agentno=" + agentno + ", presettletime=" + presettletime
				+ ", settletime=" + settletime + ", money=" + money
				+ ", settlemoney=" + settlemoney + ", fee=" + fee + ", eqno="
				+ eqno + ", cardno=" + cardno + ", cardtype=" + cardtype
				+ ", bankname=" + bankname + "]";
	}

}
