package cn.epalmpay.analoy.entity;

import java.util.Date;

public class EquipMent {

	public static final int OPEN_STATUS = 1;// 开通
	public static final int NOT_OPEN_STATUS = 2;// 未开通
	public static final int ACTIVATED_STATUS_1 = 1;// 未激活
	public static final int ACTIVATED_STATUS_2 = 2;// 激活

	public static final int ACTIVATE_STATUS_NO_ACTIVED = 1; // 未激活
	public static final int ACTIVATE_STATUS_NO_REGISTED = 2; // 激活未注册
	public static final int ACTIVATE_STATUS_REGISTED = 3; // 已注册

	public static final int EQTYPE_QIANDAIBAO = 1;// 钱袋宝
	public static final int EQTYPE_ZHONGHUI = 2;// 中汇
	public static final int EQTYPE_HANXIN = 3;// 韩鑫
	private Integer id;
	private String eqno;
	private String agentno;
	private String agentname;
	private Integer eqtype;
	private Integer status;
	private String loginname;
	private String password;
	private String position;
	private String appversion;
	private String product;
	private String username;
	private String receivecardno;
	private Date createdat;
	private Date updatedat;
	private String licenseCode;
	private int activated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public Integer getEqtype() {
		return eqtype;
	}

	public void setEqtype(Integer eqtype) {
		this.eqtype = eqtype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReceivecardno() {
		return receivecardno;
	}

	public void setReceivecardno(String receivecardno) {
		this.receivecardno = receivecardno;
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

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public int getActivated() {
		return activated;
	}

	public void setActivated(int activated) {
		this.activated = activated;
	}

}
