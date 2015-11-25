/**
* @module fastpay page
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.plugin.PaypwdInput;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class FastPay extends WalletBase {
	private WebDriver browser;
	
	public FastPay(WebDriver browser) {
		super(browser, ".J_AddCard");
		this.browser = browser;
	}
	
	public void goToAddCard(){
		this.clickElementToExpectClickable(".J_AddCard", ".J_Widget_VirtualTarget");
		
		PaypwdInput paypwd = new PaypwdInput(browser, true);
		paypwd.inputPaypwdToSubmit(Global.getPaypwd(), "");
		
		this.inputElement("J_BankCard", Global.getCreditCardNo());
		this.clickElement(".J_NextBtn");
	}
}
