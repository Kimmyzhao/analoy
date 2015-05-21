package cn.epalmpay.analoy.entity.zhonghui;

public class ActivateResult extends Resp {
	private String baseKey;
	private String keyCheck;
	private String serialType;
	private boolean signatureFlag;

	public String getBaseKey() {
		return baseKey;
	}

	public void setBaseKey(String baseKey) {
		this.baseKey = baseKey;
	}

	public String getKeyCheck() {
		return keyCheck;
	}

	public void setKeyCheck(String keyCheck) {
		this.keyCheck = keyCheck;
	}

	public String getSerialType() {
		return serialType;
	}

	public void setSerialType(String serialType) {
		this.serialType = serialType;
	}

	public boolean isSignatureFlag() {
		return signatureFlag;
	}

	public void setSignatureFlag(boolean signatureFlag) {
		this.signatureFlag = signatureFlag;
	}

}
