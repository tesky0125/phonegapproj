package test;

import mgv.Login;
import mgv.MGV_Global;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class LoginTest {

	private WebDriver browser = null;
	
	@Before
	public void setUp() throws Exception {
		String web_url = MGV_Global.get_mgv_url();
		browser = MGV_Global.start_browser(web_url);
	}

	@After
	public void tearDown() throws Exception {
		browser.quit();
	}

	@Test
	public void test_login() throws InterruptedException {		
		test_login(browser);
	}
	
	public void test_login(WebDriver browser) throws InterruptedException{
		Login _login = new Login(browser);
		_login.login_to_mgv();
        
        Thread.sleep(1000);
        
        boolean login_ok = _login.check_login_status();
		Assert.assertEquals("Assert Login", true, login_ok);
		
		_login.return_to_default();
	}
	
}
