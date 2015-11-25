package com.ctrip.wallet.model;

public class CardInfo {
	private String validity;//expire
	private String cardIdentify;//cvn2
	private String cardHolder;//name
	private String cardType;//id type
	private String cardNo;//id code
	private String preTel;//pre tel
	
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getCardIdentify() {
		return cardIdentify;
	}
	public void setCardIdentify(String cardIdentify) {
		this.cardIdentify = cardIdentify;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getPreTel() {
		return preTel;
	}
	public void setPreTel(String preTel) {
		this.preTel = preTel;
	}
}
