/**
* @module retcash page
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.model.Constant;
import com.ctrip.wallet.util.Utility;

public class RetCash  extends WalletBase{
	
	public RetCash(WebDriver browser) {
		super(browser, ".J_ShowVersion");
	}
	
	public void goToExchangePhone(){
		this.clickElementToExpectPresence("#exchg_phone", ".J_WalletPhoneNum");
		Utility.waitAMoment();
	}
	
	public void goToExchangeTicket(){
		this.clickElement("#exchange_ticket");
		Utility.waitAMoment();
	}
	
	public void goToTransferTocard(){
		this.clickElementToExpectClickable("#transfer_tocard", ".J_AddNewCard");
		Utility.waitAMoment();
	}
	
	public void goTorefundRecords(){
		this.clickElementToExpectPresence("#refund_records", "#refund_list");
		Utility.waitAMoment();
	}
	
	public void goToPayoutRecords(){
		this.clickElementToExpectPresence("#payout_records", "#payout_list");
		Utility.waitAMoment();
	}
	
}
