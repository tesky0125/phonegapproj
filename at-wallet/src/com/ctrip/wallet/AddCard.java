/**
* @module add card
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.8
*/
package com.ctrip.wallet;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.model.Constant;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class AddCard extends WalletBase{
	private WebDriver browser;
	
	public AddCard(WebDriver browser) {
		super(browser, ".J_NextBtn");
		this.browser = browser;
	}
	
	public void goToAddCard(){
		this.inputElement(".J_BankCard", Global.getBankCardNo());
		this.clickElementToExpectClickable(".J_NextBtn", ".J_Next");
		Utility.waitAMoment();
	}

}
