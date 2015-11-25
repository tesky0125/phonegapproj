/**
* @module bankcardinfo module(WalletCardInfo[View])
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.model.CardInfo;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class BankCardInfo extends WalletBase {
	private WebDriver browser;

	public BankCardInfo(WebDriver browser) {
		super(browser, ".J_Fragments");
		this.browser = browser;
	}

	public void inputCardInfoToSubmit(CardInfo cardInfo) {//TODO Check server field list by visible elements
		WebElement elemPretel = Utility.elementToBeVisible(browser, ".J_Widget_PreTel");
		elemPretel.sendKeys(cardInfo.getPreTel());
		this.inputElement(".J_Widget_CardHolder", cardInfo.getCardHolder());
		this.inputElement(".J_Widget_CardNo", cardInfo.getCardNo());
		this.inputElement(".J_Widget_Validity", cardInfo.getValidity());
		this.inputElement(".J_Widget_CardIdentify", cardInfo.getCardIdentify());
		
		WebElement submit = this.elementToBeClickable(".J_Next");
		
		Utility.waitAMoment();
		submit.click();

		this.elementToBeVisible(".J_BankCard");
	}
}
