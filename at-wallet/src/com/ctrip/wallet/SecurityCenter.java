/**
* @module securitycenter page
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.model.Constant;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class SecurityCenter extends WalletBase {
	private WebDriver browser;
	
	public SecurityCenter(WebDriver browser) {
		super(browser, ".J_Reset-pay-password");
		this.browser = browser;
	}
	
	public void goToVerifingAuth(){
		this.clickElementToExpectClickable(".J_Verifing-auth", ".J_GoSet");
	}
	
	public void goToResetPayPassword(){
		this.clickElementToExpectClickable(".J_Reset-pay-password", ".J_BottomDivBtn1");
	}
	
	public void goToModifySecurityMobile(){
		this.clickElementToExpectClickable(".J_Modify-security-mobile", ".J_BottomDivBtn1");
	}
}
