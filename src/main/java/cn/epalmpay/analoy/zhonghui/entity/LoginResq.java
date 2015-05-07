package cn.epalmpay.analoy.zhonghui.entity;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LoginResq extends Resp {
	private String session;
	private String status;
	private String name;
	private String cardTail;
	private String ksnNo;
	private String sessionKey;
	private String keyCheck;
	private String model;
	private String serialType;
	private int nextReqNo;
	private String bluetoothName;
	private boolean isBluetooth;
	private Map<String, Object> argument;
	private AtomicInteger atomicReqNo;

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

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

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getKeyCheck() {
		return keyCheck;
	}

	public void setKeyCheck(String keyCheck) {
		this.keyCheck = keyCheck;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialType() {
		return serialType;
	}

	public void setSerialType(String serialType) {
		this.serialType = serialType;
	}

	public int getNextReqNo() {
		return nextReqNo;
	}

	public void setNextReqNo(int nextReqNo) {
		this.nextReqNo = nextReqNo;
	}

	public String getBluetoothName() {
		return bluetoothName;
	}

	public void setBluetoothName(String bluetoothName) {
		this.bluetoothName = bluetoothName;
	}

	public boolean isBluetooth() {
		return isBluetooth;
	}

	public void setBluetooth(boolean isBluetooth) {
		this.isBluetooth = isBluetooth;
	}

	public Map<String, Object> getArgument() {
		return argument;
	}

	public void setArgument(Map<String, Object> argument) {
		this.argument = argument;
	}

	public AtomicInteger getAtomicReqNo() {
		return atomicReqNo;
	}

	public void setAtomicReqNo(AtomicInteger atomicReqNo) {
		this.atomicReqNo = atomicReqNo;
	}

}
