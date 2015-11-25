/**
* @module retcash basic test case
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
import com.ctrip.wallet.RetCash;
import com.ctrip.wallet.base.WalletEntry;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class TestRetCash {

	private WebDriver browser = null;
	private RetCash retcash = null;
	
	@Before
	public void setUp() {
		browser = Global.startBrowser();
		WalletEntry login = new WalletEntry(browser);
		login.login();
		Index index = new Index(browser);
		index.goToRetCash();
		retcash = new RetCash(browser);
	}

	@After
	public void tearDown() {
		browser.quit();
	}

	@Test
	public void testGoToExchangePhone() {
		retcash.goToExchangePhone();
	}

	@Test
	public void testGoToExchangeTicket() {
		retcash.goToExchangeTicket();
	}

	@Test
	public void testGoToTransferTocard() {
		retcash.goToTransferTocard();
	}

	@Test
	public void testGoTorefundRecords() {
		retcash.goTorefundRecords();
	}

	@Test
	public void testGoToPayoutRecords() {
		retcash.goToPayoutRecords();
	}

}
