/**
* @module bankcard page
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.model.CardInfo;
import com.ctrip.wallet.plugin.PaypwdInput;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class BankCard extends WalletBase {
	private WebDriver browser;
	
	public BankCard(WebDriver browser) {
		super(browser, ".J_addNo");
		this.browser = browser;
	}
	
	public void goToAddBankCardNo(){
		CardList cardList = new CardList(browser,"bindcard");
		cardList.goToAddCard();
		
		PaypwdInput paypwd = new PaypwdInput(browser, true);
		paypwd.inputPaypwdToSubmit(Global.getPaypwd(), ".J_BankCard");
		
		AddCard addCard = new AddCard(browser);
		addCard.goToAddCard();
		
		BankCardInfo bankCardInfo = new BankCardInfo(browser);
		CardInfo cardInfo = new CardInfo();
		cardInfo.setCardHolder(Global.getBankCardName());
		cardInfo.setCardType(Global.getBankCardType());
		cardInfo.setCardNo(Global.getBankCardIdCode());
		cardInfo.setPreTel(Global.getBankCardPretel());
		bankCardInfo.inputCardInfoToSubmit(cardInfo);
		//TODO
	}
	
	public void goToAddCreditCardNo(){
		this.elementToBeClickable(".J_addNo")/*.click()*/;
		Utility.waitAMoment();
		this.clickElement(".J_addNo");
		
		PaypwdInput paypwd = new PaypwdInput(browser, true);
		paypwd.inputPaypwdToSubmit(Global.getPaypwd(), ".J_BankCard");
		
		this.inputElement(".J_BankCard", Global.getBankCardNo());
		this.clickElement(".J_NextBtn");
		
		BankCardInfo bankCardInfo = new BankCardInfo(browser);
		CardInfo cardInfo = new CardInfo();
		cardInfo.setCardHolder(Global.getCreditCardName());
		cardInfo.setCardIdentify(Global.getCreditCardCvn());
		cardInfo.setPreTel(Global.getCreditCardPretel());
		cardInfo.setValidity(Global.getCreditCardExp());
		cardInfo.setCardType(Global.getCreditCardType());
		cardInfo.setCardNo(Global.getCreditCardNo());
		bankCardInfo.inputCardInfoToSubmit(cardInfo);
		//TODO
	}
	
	public void deleteCardNo(){
		//TODO
	}
}
