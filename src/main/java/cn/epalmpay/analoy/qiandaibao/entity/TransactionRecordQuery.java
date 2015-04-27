package cn.epalmpay.analoy.qiandaibao.entity;

import java.util.List;

public class TransactionRecordQuery {
	private String code;// 响应码

	private String msg;// 响应描述

	private String eqno;// 设备编号

	private String querytype;// 查询类型

	private String begintime;// 开始时间.例如：2012-03-09 16:26:34

	private String endtime;// 结束时间

	private String remark;// 备注

	private List<OrderList> orderlist;// json字符串

	private String sign;// 签名(MD5方式)

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

	public String getQuerytype() {
		return querytype;
	}

	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OrderList> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<OrderList> orderlist) {
		this.orderlist = orderlist;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public TransactionRecordQuery() {
		super();
	}

	@Override
	public String toString() {
		return "TransactionRecordQuery [code=" + code + ", msg=" + msg + ", eqno=" + eqno + ", querytype=" + querytype + ", begintime=" + begintime + ", endtime=" + endtime + ", remark=" + remark + ", orderlist=" + orderlist + ", sign=" + sign + "]";
	}

	public TransactionRecordQuery(String code, String msg, String eqno, String querytype, String begintime, String endtime, String remark, List<OrderList> orderlist, String sign) {
		super();
		this.code = code;
		this.msg = msg;
		this.eqno = eqno;
		this.querytype = querytype;
		this.begintime = begintime;
		this.endtime = endtime;
		this.remark = remark;
		this.orderlist = orderlist;
		this.sign = sign;
	}

}
