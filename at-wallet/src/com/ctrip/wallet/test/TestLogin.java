/**
* @module login basic test case
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.Login;
import com.ctrip.wallet.util.Global;

public class TestLogin {

	private WebDriver browser = null;
	private Login login = null;

	@Before
	public void setUp() {
		browser = Global.startBrowser();
		Global.navigateTo(browser, Global.getLoginUrl());
		login = new Login(browser);
	}

	@After
	public void tearDown() {
		browser.quit();
	}

	@Test
	public void testLogin() {		
		login.login();
	}
	

}
