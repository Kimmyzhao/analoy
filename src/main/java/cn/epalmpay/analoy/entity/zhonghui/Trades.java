package cn.epalmpay.analoy.entity.zhonghui;

public class Trades {
	private String devKSN;// 设备KSN号
	private String transflowno;// 交易流水号
	private String transtime;// 交易时间
	private String transmoney;// 交易金额
	private String payCardNo;// 消费卡号
	private String transtype;// 交易类型
	private String transState;// 交易状态
	private String merchantno;// 商户号
	private String merchantname;// 商户名
	private String referenceno;// 参考号
	private String transno;// 交易号

	public String getDevKSN() {
		return devKSN;
	}

	public void setDevKSN(String devKSN) {
		this.devKSN = devKSN;
	}

	public String getTransflowno() {
		return transflowno;
	}

	public void setTransflowno(String transflowno) {
		this.transflowno = transflowno;
	}

	public String getTranstime() {
		return transtime;
	}

	public void setTranstime(String transtime) {
		this.transtime = transtime;
	}

	public String getTransmoney() {
		return transmoney;
	}

	public void setTransmoney(String transmoney) {
		this.transmoney = transmoney;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public String getTranstype() {
		return transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	public String getTransState() {
		return transState;
	}

	public void setTransState(String transState) {
		this.transState = transState;
	}

	public String getMerchantno() {
		return merchantno;
	}

	public void setMerchantno(String merchantno) {
		this.merchantno = merchantno;
	}

	public String getMerchantname() {
		return merchantname;
	}

	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}

	public String getReferenceno() {
		return referenceno;
	}

	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}

	public String getTransno() {
		return transno;
	}

	public void setTransno(String transno) {
		this.transno = transno;
	}

	@Override
	public String toString() {
		return "Trades [devKSN=" + devKSN + ", transflowno=" + transflowno + ", transtime=" + transtime + ", transmoney=" + transmoney + ", payCardNo=" + payCardNo + ", transtype=" + transtype + ", transState=" + transState + ", merchantno=" + merchantno + ", merchantname=" + merchantname + ", referenceno=" + referenceno + ", transno=" + transno + "]";
	}

	public Trades() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trades(String devKSN, String transflowno, String transtime, String transmoney, String payCardNo, String transtype, String transState, String merchantno, String merchantname, String referenceno, String transno) {
		super();
		this.devKSN = devKSN;
		this.transflowno = transflowno;
		this.transtime = transtime;
		this.transmoney = transmoney;
		this.payCardNo = payCardNo;
		this.transtype = transtype;
		this.transState = transState;
		this.merchantno = merchantno;
		this.merchantname = merchantname;
		this.referenceno = referenceno;
		this.transno = transno;
	}

}
