package cn.epalmpay.analoy.entity;

import java.util.Date;

public class TradeOrder {

	private Integer id;

	private Integer equipmentid;
	private String transactionalNumber;
	private String settlebankname;
	private String settlecardno;
	private Integer paytype;
	private Integer cardtype;
	private Integer tradetype;
	private double money;
	private double fee;
	private double settlemoney;
	private Date createdat;
	private Date updatedat;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getTransactionalNumber() {
		return transactionalNumber;
	}

	public void setTransactionalNumber(String transactionalNumber) {
		this.transactionalNumber = transactionalNumber;
	}

	public String getSettlebankname() {
		return settlebankname;
	}

	public void setSettlebankname(String settlebankname) {
		this.settlebankname = settlebankname;
	}

	public String getSettlecardno() {
		return settlecardno;
	}

	public void setSettlecardno(String settlecardno) {
		this.settlecardno = settlecardno;
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public Integer getCardtype() {
		return cardtype;
	}

	public void setCardtype(Integer cardtype) {
		this.cardtype = cardtype;
	}

	public Integer getTradetype() {
		return tradetype;
	}

	public void setTradetype(Integer tradetype) {
		this.tradetype = tradetype;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getSettlemoney() {
		return settlemoney;
	}

	public void setSettlemoney(double settlemoney) {
		this.settlemoney = settlemoney;
	}

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	public Date getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}

}
