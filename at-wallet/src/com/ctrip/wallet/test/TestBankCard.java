/**
* @module bankcard basic test case
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.BankCard;
import com.ctrip.wallet.Index;
import com.ctrip.wallet.base.WalletEntry;
import com.ctrip.wallet.util.Global;

public class TestBankCard {

	private WebDriver browser = null;
	private BankCard bankcard = null;
	
	@Before
	public void setUp() {
		browser = Global.startBrowser();
		WalletEntry login = new WalletEntry(browser);
		login.login();
		Index index = new Index(browser);
		index.goToBankCard();
		bankcard = new BankCard(browser);
	}

	@After
	public void tearDown() {
		browser.quit();
	}

	@Test
	public void testGoToAddNo() {
		bankcard.goToAddBankCardNo();
	}

}
