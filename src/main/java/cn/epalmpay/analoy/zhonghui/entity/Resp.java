package cn.epalmpay.analoy.zhonghui.entity;

/**
 * 用户登录响应返回
 * 
 * @author DELL
 *
 */
public class Resp {

	private String respTime;
	private String respCode;
	private String respMsg;
	private boolean isSuccess;

	public String getRespTime() {
		return respTime;
	}

	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Resp(String respTime, boolean isSuccess, String respCode, String respMsg) {
		super();
		this.respTime = respTime;
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.isSuccess = isSuccess;
	}

	public Resp() {
		super();
		// TODO Auto-generated constructor stub
	}

}
