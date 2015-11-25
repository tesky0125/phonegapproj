/**
* @module index page
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.model.Constant;
import com.ctrip.wallet.util.Utility;

public class Index extends WalletBase{
	private WebDriver browser;
	
	public Index(WebDriver browser) {
		super(browser, ".J_Balance");
		this.browser = browser;
	}
	
	public boolean checkLogin(){
		return !this.elementToBeClickable(".cui-pop-box").isDisplayed();
	}
	
	public void goToLogin(){
		this.elementToBeClickable(".wallet_alert_btn_idx0").click();
		Utility.waitAMoment();
	}
	
	public void goToBalance(){
		this.clickElementToExpectClickable(".J_Balance", ".J_Recharge");
		Utility.waitAMoment();
	}
	
	public void goToRetCash(){
		this.clickElementToExpectClickable(".J_FX", ".J_ShowVersion");
		Utility.waitAMoment();
	}
	
	public void goToBankCard(){
		this.clickElementToExpectClickable(".J_Mybank-card", ".J_addNo");
		Utility.waitAMoment();
	}
	
	public void goToSecuritySetting(){
		this.clickElementToExpectClickable(".J_SS", ".J_Reset-pay-password");
		Utility.waitAMoment();
	}
	
	public void goToFastPay(){
		this.clickElement(".J_FastPay");
		boolean isGuide = this.existElement(".J_GoSet");
		if(isGuide){
			this.clickElementToExpectClickable(".J_GoSet", ".J_AddCard");
		}else{
			this.elementToBeClickable(".J_AddCard");
		}
		Utility.waitAMoment();
	}
	
	public void goToRealName(){
		this.clickElementToExpectClickable(".J_RnFloat", ".J_GoAuthByBank");
		Utility.waitAMoment();
	}
}
