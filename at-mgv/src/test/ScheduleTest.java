package test;


import java.awt.AWTException;

import mgv.Login;
import mgv.MGV_Global;
import mgv.MGV_Utility;
import mgv.Media;
import mgv.Playlist;
import mgv.Schedule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ScheduleTest {
	
	private WebDriver browser = null;
	
	@Before
	public void setUp() throws Exception {
		String web_url = MGV_Global.get_mgv_url();
		browser = MGV_Global.start_browser(web_url);
		new LoginTest().test_login(browser);
		new MediaTest().test_media(browser);
		new PlaylistTest().test_playlist(browser);
		new BlockTest().test_block(browser);
		new ClientTest().test_client(browser);
		Thread.sleep(3000);
	}

	@After
	public void tearDown() throws Exception {
		browser.quit();
	}

	@Test
	public void test_add_schedule() throws InterruptedException, AWTException {
		Schedule _schedule = new Schedule(browser);
		_schedule.goto_schedule();
		MGV_Global.wait_to_load_page(browser);

		_schedule.select_event();
		Thread.sleep(3000);
		String playlistName = MGV_Global.get_new_playlist_name();
		_schedule.choice_playlist(playlistName);
		
		boolean add_new_schedule_ok = MGV_Utility.is_event_exist_in_calendar(browser, "calendar", playlistName);
		Assert.assertEquals("Assert add new schedule", true, add_new_schedule_ok);
	}
	
	@Test
	public void test_schedule() throws InterruptedException, AWTException {
		test_schedule(browser);
	}
	
	public void test_schedule(WebDriver browser) throws InterruptedException, AWTException {
		Schedule _schedule = new Schedule(browser);
		_schedule.goto_schedule();
		Thread.sleep(3000);

		_schedule.select_elite_group();
		Thread.sleep(1000);
		_schedule.select_event();
		Thread.sleep(3000);
		String playlistName = MGV_Global.get_new_playlist_name();
		_schedule.choice_playlist(playlistName);
		Thread.sleep(1000);
		_schedule.publish_schedule();
		Thread.sleep(1000);
		_schedule.resize_schedule();
		Thread.sleep(1000);
		_schedule.publish_schedule();
		Thread.sleep(1000);
		_schedule.drag_schedule();
		Thread.sleep(1000);
		_schedule.publish_schedule();
		Thread.sleep(1000);
		_schedule.delete_schedule();
		Thread.sleep(1000);
		_schedule.publish_schedule();
		Thread.sleep(1000);

		_schedule.return_to_default();

		System.out.println("Schedule Success");

	}
}
