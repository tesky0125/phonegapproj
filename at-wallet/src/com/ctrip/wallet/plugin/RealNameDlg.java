/**
* @module realname dialog
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.8
*/
package com.ctrip.wallet.plugin;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.model.Constant;
import com.ctrip.wallet.util.Utility;

public class RealNameDlg extends WalletBase{
	private WebDriver browser;
	
	public RealNameDlg(WebDriver browser) {
		super(browser, ".J_SelGo");
		this.browser = browser;
	}
	
	public boolean isShowing(){
		return this.existElement(".J_SelGo");
	}
	
	public void goToRealName(){
		this.clickElementToExpectPresence(".J_SelGo", ".J_GoAuthByBank");
		Utility.waitAMoment();
	}
	
	public void closeDialog(){
		this.clickElement(".J_SelX");
	}
}
