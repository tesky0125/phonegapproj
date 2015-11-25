/**
* @module index basic test case
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.Index;
import com.ctrip.wallet.base.WalletEntry;
import com.ctrip.wallet.util.Global;

public class TestIndex {

	private WebDriver browser = null;
	private Index index = null;
	
	@Before
	public void setUp() {
		browser = Global.startBrowser();
		WalletEntry login = new WalletEntry(browser);
		login.login();
		index = new Index(browser);
	}

	@After
	public void tearDown() {
		browser.quit();
	}

	@Test
	public void testGoToBalance() {		
		index.goToBalance();
	}
	
	@Test
	public void testGoToRetCash() {		
		index.goToRetCash();
	}
	
	@Test
	public void testGoToBankCard() {		
		index.goToBankCard();
	}
	
	@Test
	public void testGoToSecuritySetting() {		
		index.goToSecuritySetting();
	}
	
	@Test
	public void testGoToFastPay() {		
		index.goToFastPay();
	}

}
