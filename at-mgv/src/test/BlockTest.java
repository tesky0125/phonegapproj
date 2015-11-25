package test;

import mgv.Block;
import mgv.Login;
import mgv.MGV_Global;
import mgv.MGV_Utility;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class BlockTest {

	private WebDriver browser = null;

	@Before
	public void setUp() throws Exception {
		String web_url = MGV_Global.get_mgv_url();
		browser = MGV_Global.start_browser(web_url);
		new LoginTest().test_login(browser);
		Thread.sleep(3000);
	}

	@After
	public void tearDown() throws Exception {
		browser.quit();
	}

	@Test
	public void test_add_new_layout() throws InterruptedException {
		Block _block = new Block(browser);
		_block.goto_block();
		MGV_Global.wait_to_load_page(browser);

		String layout_name = MGV_Utility.get_random_name("-new-layout-name");

		_block.add_new_layout(layout_name);

		Thread.sleep(2000);

		boolean add_new_layout_ok = _block.check_add_new_layout(layout_name);
		Assert.assertEquals("Assert add new layout", true, add_new_layout_ok);
	}

	@Test
	public void test_drag_block() throws Exception {
		Block _block = new Block(browser);
		_block.goto_block();
		MGV_Global.wait_to_load_page(browser);

		String layout_name = MGV_Utility.get_random_name("-new-layout-name");

		_block.add_new_layout(layout_name);
		_block.add_new_block();

		Thread.sleep(2000);
		Point block_des_point = _block.drag_block();

		Thread.sleep(2000);

		boolean drag_block_ok = _block.check_drag_block(block_des_point);
		Assert.assertEquals("Assert drag block", true, drag_block_ok);

	}

	@Test
	public void test_block() throws InterruptedException {
		test_block(browser);
	}
	
	public void test_block(WebDriver browser) throws InterruptedException {

		Block _block = new Block(browser);
		_block.goto_block();
		Thread.sleep(3000);

		String layout_name = MGV_Utility.get_random_name("-new-layout-name");
		_block.add_new_layout(layout_name);
		Thread.sleep(1000);
		_block.add_new_block();
		_block.save_layout();
		Thread.sleep(1000);
		_block.return_to_layout();
		Thread.sleep(1000);

		_block.publish_layout(layout_name);
		Thread.sleep(1000);

		// # can't delete layout has published,so republish default to client
		_block.publish_layout("default");
		Thread.sleep(1000);
		_block.delete_layout(layout_name);
		Thread.sleep(1000);

		_block.return_to_default();

	}
}
