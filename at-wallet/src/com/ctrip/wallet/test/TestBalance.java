/**
* @module balance basic test case
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.Balance;
import com.ctrip.wallet.Index;
import com.ctrip.wallet.base.WalletEntry;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class TestBalance {

	private WebDriver browser = null;
	private Balance balance = null;
	
	@Before
	public void setUp() {
		browser = Global.startBrowser();
		WalletEntry login = new WalletEntry(browser);
		login.login();
		Index index = new Index(browser);
		index.goToBalance();
		balance = new Balance(browser);
	}

	@After
	public void tearDown() {
		browser.quit();
	}

	@Test
	public void testGoToRecharge() {
		balance.goToRecharge();
	}
	
	@Test
	public void testGoToWithdraw() {
		balance.goToWithdraw();
	}

	@Test
	public void testGoToTranxList()  {
		balance.goToTranxList();
	}
	
	@Test
	public void testGoToRechargeRecord() {
		balance.goToRechargeRecord();
	}
	
	@Test
	public void testGoToWithdrawRecord() {
		balance.goToWithdrawRecord();
	}

}
