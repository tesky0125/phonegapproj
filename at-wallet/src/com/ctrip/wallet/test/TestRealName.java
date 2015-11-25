package com.ctrip.wallet.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.Balance;
import com.ctrip.wallet.Index;
import com.ctrip.wallet.RealName;
import com.ctrip.wallet.base.WalletEntry;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class TestRealName {

	private WebDriver browser = null;
	private RealName realName = null;
	
	@Before
	public void setUp() {
		browser = Global.startBrowser();
		WalletEntry login = new WalletEntry(browser);
		login.login();
		Index index = new Index(browser);
		index.goToRealName();
		realName = new RealName(browser);
	}

	@After
	public void tearDown() {
		browser.quit();
	}

	@Test
	public void testGoToRealNameById() {
		realName.goToRealNameById();
	}

	@Test
	public void testGoToRealNameByBank() {
		realName.goToRealNameByBank();
	}

}
