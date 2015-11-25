package com.ctrip.wallet.plugin;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.util.Utility;

public class PaypwdHandler extends WalletBase {
	private WebDriver browser;
	
	public PaypwdHandler(WebDriver browser) {
		super(browser, ".J_Widget_VirtualTarget");
		this.browser = browser;
	}
	
	public boolean isShowing(){
		return this.existElement(".wallet_alert_btn_idx1");//setting button
	}
	
	public void goToSetPwd(){
		this.clickElementToExpectClickable(".wallet_alert_btn_idx1", ".J_Button");
		Utility.waitAMoment();
	}
	
	public void cancel(){
		this.clickElement(".wallet_alert_btn_idx0");
	}
}
