package cn.epalmpay.analoy.qiandaibao.entity;

public class PosQuery {
	private String code;// 响应码

	private String msg;// 响应描述

	private String eqno;// 设备编号

	private String agentno;// 商户编号

	private String name;// 商户名称

	private String username;// 手机号

	private String remark;// 签名(md5方式)

	private String sign;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getEqno() {
		return eqno;
	}

	public void setEqno(String eqno) {
		this.eqno = eqno;
	}

	public String getAgentno() {
		return agentno;
	}

	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public PosQuery() {
		super();
	}

	public PosQuery(String code, String msg, String eqno, String agentno,
			String name, String username, String remark, String sign) {
		super();
		this.code = code;
		this.msg = msg;
		this.eqno = eqno;
		this.agentno = agentno;
		this.name = name;
		this.username = username;
		this.remark = remark;
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "PosQuery [code=" + code + ", msg=" + msg + ", eqno=" + eqno
				+ ", agentno=" + agentno + ", name=" + name + ", username="
				+ username + ", remark=" + remark + ", sign=" + sign + "]";
	}
}
