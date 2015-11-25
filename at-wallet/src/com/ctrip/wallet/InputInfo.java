/**
* @module inputinfo module(WalletInputView)
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class InputInfo extends WalletBase {
	private WebDriver browser;

	public InputInfo(WebDriver browser) {
		super(browser, ".J_NextBtn");
		this.browser = browser;
	}

	public void inputToSubmit(String money) {
		WebElement elemMoney = this.elementToBeVisible(".J_Money");
		WebElement submit = this.elementToBeClickable(".J_NextBtn");
		Utility.waitAMoment();

		elemMoney.sendKeys(money);
		submit.click();

		//TODO:don't support
	}
}
