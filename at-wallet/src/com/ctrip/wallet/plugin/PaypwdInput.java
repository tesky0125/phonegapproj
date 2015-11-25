/**
* @module paypwdfloat module
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet.plugin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class PaypwdInput extends WalletBase {
	private WebDriver browser;
	private boolean isFloat;
	
	public PaypwdInput(WebDriver browser, boolean isFloat) {
		super(browser, ".J_Widget_VirtualTarget");
		this.browser = browser;
		this.isFloat = isFloat;
	}
	
	public void inputPaypwdToSubmit(String paypwd, String expectCssName){
		WebElement password = null;
		WebElement submit = null;
		if(isFloat){
			password = this.elementToBeVisible(".J_Widget_VerfiedpsdInputFloat");
			submit = this.elementToBeClickable(".J_Widget_V_Confirm");
			
		}else{
			password = this.elementToBeVisible(".J_Widget_VerfiedpsdInputFloat");
			submit = this.elementToBeClickable(".J_Button");
		}
		
		Utility.waitAMoment();
		password.sendKeys(paypwd);
		submit.click();
        
		this.elementToBeVisible(expectCssName);
	}

}
