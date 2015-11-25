/**
* @module balance page
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.model.Constant;
import com.ctrip.wallet.util.Utility;

public class Balance extends WalletBase{
	private WebDriver browser;
	
	public Balance(WebDriver browser) {
		super(browser, ".J_Recharge");
		this.browser = browser;
	}
	
	public void goToRecharge(){
		this.clickElementToExpectClickable(".J_Recharge", ".J_Money");
		Utility.waitAMoment();
	}
	
	public void goToWithdraw(){
		this.clickElement(".J_Withdraw");
		Utility.waitAMoment(1);
		boolean isSelectShowing = this.existElement(".J_SelWdNormal");
		if(isSelectShowing){
			this.clickElementToExpectClickable(".J_SelWdNormal", ".J_AddNewCard");
		}else{
			this.elementToBePresence(".J_AddNewCard");
		}
		Utility.waitAMoment();
	}
	
	public void goToTranxList(){
		this.clickElementToExpectClickable(".J_TranxList", ".J_TrHead");
		Utility.waitAMoment();
	}
	
	public void goToRechargeRecord(){
		this.clickElementToExpectClickable(".J_RechargeRecord", ".J_TrHead");
		Utility.waitAMoment();
	}
	
	public void goToWithdrawRecord(){
		this.clickElementToExpectClickable(".J_WithdrawRecord", ".J_TrHead");
		Utility.waitAMoment();
	}
}
