package cn.epalmpay.analoy.zhonghui.entity;

import java.util.List;

public class BankResult extends Resp {
	private int total;
	private String tip;
	private List<Bank> banks;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

}
