package com.ctrip.wallet.base;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.Debug;
import com.ctrip.wallet.Login;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class WalletEntry implements ILogin{
private WebDriver browser;
	private ILogin login = null;
	public WalletEntry(WebDriver browser) {
		this.browser = browser;
	}
	
	public void login(){
		String loginType = Global.getEntryType();
		if(loginType.equals("login")){
			Global.navigateTo(browser, Global.getLoginUrl());
			login = new Login(browser);
		}else{//debug
			Global.navigateTo(browser, Global.getDebugUrl());
			login = new Debug(browser);
		}
		login.login();
		
		Utility.waitAMoment();
	}
}
