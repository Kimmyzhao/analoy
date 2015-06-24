package cn.epalmpay.analoy.entity.qiandaibao;

/**
 * 商户费率变化通知类
 * 
 * @author DELL
 *
 */
public class AgentFee {
	/**
	 * 信用卡封顶标识 有标识
	 */
	public static int CREDIT_CARD_TOP_FLAG_1 = 1;
	/**
	 * 信用卡封顶标识 无标识
	 */
	public static int CREDIT_CARD_TOP_FLAG_0 = 0;
	/**
	 * 借记卡封顶标识 有标识
	 */
	public static int DEBIT_CARD_TOP_FLAG_1 = 1;
	/**
	 * 借记卡封顶标识 无标识
	 */
	public static int DEBIT_CARD_TOP_FLAG_0 = 0;
	private String agentno;// 商户编号
	private String time;// 变化时间
	private float credit_card_fee;// 信用卡费率
	private float debit_card_fee;// 借记卡费率
	private int credit_card_top_flag;// 信用卡封顶标识
	private int debit_card_top_flag;// 借记卡封顶标识

	private float credit_card_top_money;// 当credit_card_top_flag为1时,此值有效
	private float debit_card_top_money;// 当debit_card_top_flag为1时,此值有效

	private String remark;// 备注
	private String sign;// 签名

	public String getAgentno() {
		return agentno;
	}

	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public float getCredit_card_fee() {
		return credit_card_fee;
	}

	public void setCredit_card_fee(float credit_card_fee) {
		this.credit_card_fee = credit_card_fee;
	}

	public float getDebit_card_fee() {
		return debit_card_fee;
	}

	public void setDebit_card_fee(float debit_card_fee) {
		this.debit_card_fee = debit_card_fee;
	}

	public int getCredit_card_top_flag() {
		return credit_card_top_flag;
	}

	public void setCredit_card_top_flag(int credit_card_top_flag) {
		this.credit_card_top_flag = credit_card_top_flag;
	}

	public int getDebit_card_top_flag() {
		return debit_card_top_flag;
	}

	public void setDebit_card_top_flag(int debit_card_top_flag) {
		this.debit_card_top_flag = debit_card_top_flag;
	}

	public float getCredit_card_top_money() {
		return credit_card_top_money;
	}

	public void setCredit_card_top_money(float credit_card_top_money) {
		this.credit_card_top_money = credit_card_top_money;
	}

	public float getDebit_card_top_money() {
		return debit_card_top_money;
	}

	public void setDebit_card_top_money(float debit_card_top_money) {
		this.debit_card_top_money = debit_card_top_money;
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

}
