package cn.epalmpay.analoy.entity.zhonghui;

public class ResponseResult extends Resp {
	private String status;
	private String name;
	private String cardTail;
	private String ksnNo;
	private String serialType;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardTail() {
		return cardTail;
	}

	public void setCardTail(String cardTail) {
		this.cardTail = cardTail;
	}

	public String getKsnNo() {
		return ksnNo;
	}

	public void setKsnNo(String ksnNo) {
		this.ksnNo = ksnNo;
	}

	public String getSerialType() {
		return serialType;
	}

	public void setSerialType(String serialType) {
		this.serialType = serialType;
	}

	@Override
	public String getRespTime() {
		// TODO Auto-generated method stub
		return super.getRespTime();
	}

	@Override
	public void setRespTime(String respTime) {
		// TODO Auto-generated method stub
		super.setRespTime(respTime);
	}

	@Override
	public String getRespCode() {
		// TODO Auto-generated method stub
		return super.getRespCode();
	}

	@Override
	public void setRespCode(String respCode) {
		// TODO Auto-generated method stub
		super.setRespCode(respCode);
	}

	@Override
	public String getRespMsg() {
		// TODO Auto-generated method stub
		return super.getRespMsg();
	}

	@Override
	public void setRespMsg(String respMsg) {
		// TODO Auto-generated method stub
		super.setRespMsg(respMsg);
	}

	@Override
	public boolean getIsSuccess() {
		// TODO Auto-generated method stub
		return super.getIsSuccess();
	}

	@Override
	public void setIsSuccess(boolean isSuccess) {
		// TODO Auto-generated method stub
		super.setIsSuccess(isSuccess);
	}

}
