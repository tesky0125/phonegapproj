package com.ctrip.wallet;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.plugin.PaypwdInput;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class RealName extends WalletBase{
	private WebDriver browser;
	
	public RealName(WebDriver browser) {
		super(browser, ".J_GoAuthByBank");
		this.browser = browser;
	}

	public void goToRealNameById(){
		this.clickElement(".J_GoSet");
		Utility.waitAMoment();
		PaypwdInput paypwd = new PaypwdInput(browser, true);
		paypwd.inputPaypwdToSubmit(Global.getPaypwd(), ".J_Next");
		
		//TODO
	}
	
	public void goToRealNameByBank(){
		this.clickElement(".J_GoAuthByBank");
		Utility.waitAMoment();
		PaypwdInput paypwd = new PaypwdInput(browser, true);
		paypwd.inputPaypwdToSubmit(Global.getPaypwd(), ".J_NextBtn");
		
		//TODO
	}
}
