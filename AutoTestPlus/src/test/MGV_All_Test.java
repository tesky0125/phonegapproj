package test;

import java.awt.AWTException;

import mgv.MGV_Global;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MGV_All_Test {

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
	public void test() throws InterruptedException, AWTException {
		test(browser);
	}
	
	public void test(WebDriver browser) throws InterruptedException, AWTException {
		new LoginTest().test_login(browser);
		new MediaTest().test_media(browser);
		new BlockTest().test_block(browser);
		new ClientTest().test_client(browser);
		new PlaylistTest().test_playlist(browser);
		new ScheduleTest().test_schedule(browser);
	}
	
	public static void main(String[] args) throws InterruptedException, AWTException{
		String web_url = MGV_Global.get_mgv_url();
		WebDriver browser = MGV_Global.start_browser(web_url);
		new MGV_All_Test().test(browser);
		browser.quit();
	}
	
}
