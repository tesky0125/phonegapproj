package test;

import java.io.File;

import mgv.Login;
import mgv.MGV_Global;
import mgv.MGV_Utility;
import mgv.Media;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
/**
 * modified by RylanYan,2015-03-20
 */
public class MediaTest {

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
	public void test_add_new_folder() throws InterruptedException {
		add_new_folder(browser);
	}
	
	private void add_new_folder(WebDriver browser) throws InterruptedException {

		Media _media = new Media(browser);
		_media.goto_media();
		Thread.sleep(3000);
		MGV_Global.wait_to_load_page(browser);

		String folder_name = MGV_Utility.get_random_name("-new-folder-name");
		String folder_desc = MGV_Utility.get_random_name("-new-folder-desc");
		
		MGV_Global.set_new_media_fodler_name(folder_name);
		
		_media.add_new_folder(folder_name, folder_desc);

		Thread.sleep(2000);

		boolean add_new_folder_ok = MGV_Utility.is_text_in_gridview(browser,
				"GridListTable", folder_name);
		Assert.assertEquals("Assert add new folder", true, add_new_folder_ok);
	}

	@Test
	public void test_upload_media_file() throws Exception {
		upload_media_file(browser);
	}
	
	private void upload_media_file(WebDriver browser) throws Exception {
		Media _media = new Media(browser);
		_media.goto_media();
		Thread.sleep(3000);
		MGV_Global.wait_to_load_page(browser);
		
		_media.click_media_tree_title("media");
		Thread.sleep(1000);

		String ready_to_delete_file_path = _media.upload_media_file(MGV_Global
				.get_upload_file_path());

		_media.close_popup("close_upload_status");

		Thread.sleep(1000);

		String upload_file_name = new File(ready_to_delete_file_path).getName();
		
		MGV_Global.set_upload_meida_file_name(upload_file_name);
		
		boolean upload_file_ok = MGV_Utility.is_text_in_gridview(browser,
				"GridListTable_audit", upload_file_name);
		Assert.assertEquals("Assert upload media file", true, upload_file_ok);

		delete_file(ready_to_delete_file_path);
	}

	private void delete_file(String ready_to_delete_file_path) {
		try{
			FileHandler.delete(new File(ready_to_delete_file_path));
		}catch(Exception ex){
			
		}
		
	}

	@Test
	public void test_add_new_scrolltext() throws InterruptedException {
		add_new_scrolltext(browser);
	}
	private void add_new_scrolltext(WebDriver browser) throws InterruptedException {
		Media _media = new Media(browser);
		_media.goto_media();
		Thread.sleep(3000);
		MGV_Global.wait_to_load_page(browser);
		
		_media.click_media_tree_title("scrolltext");
		Thread.sleep(1000);

		String scrolltext_name = MGV_Utility
				.get_random_name("-new-scrolltext-name");
		String scrolltext_desc = MGV_Utility
				.get_random_name("-new-scrolltext-desc");
		String scrolltext_content = MGV_Utility
				.get_random_name("-new-scrolltext-content");

		_media.add_new_scrolltext(scrolltext_name, scrolltext_desc,
				scrolltext_content);
		
		MGV_Global.set_new_scrolltext_name(scrolltext_name);

		Thread.sleep(2000);

		boolean add_new_scrolltext_ok = MGV_Utility.is_text_in_gridview(
				browser, "GridListTable_audit", scrolltext_name);
		Assert.assertEquals("Assert add new scrolltext", true,
				add_new_scrolltext_ok);
	}
	
	@Test
	public void test_add_new_http_stream() throws Exception {
		add_new_stream(browser,"http");
	}
	
	@Test
	public void test_add_new_rstp_stream() throws Exception {
		add_new_stream(browser,"rstp");
	}
	
	private void add_new_stream(WebDriver browser,String type) throws InterruptedException {
		Media _media = new Media(browser);
		_media.goto_media();
		Thread.sleep(3000);
		MGV_Global.wait_to_load_page(browser);
		
		_media.click_media_tree_title("stream");
		Thread.sleep(1000);

		String name = MGV_Utility.get_random_name("-new-stream-" + type);
		String desc = MGV_Utility.get_random_name("-new-stream-desc");
		String url = MGV_Utility.get_random_name("-new-stream-url");

		_media.add_new_stream(type, name, desc, url);
		
		if (type == "rstp") {
			MGV_Global.set_new_stream_rstp_name(name);
		} else {
			MGV_Global.set_new_stream_http_name(name);
		}

		Thread.sleep(2000);

		boolean add_new_stream_ok = MGV_Utility.is_text_in_gridview(
				browser, "GridListTable_audit", name);
		Assert.assertEquals("Assert add new " + type + " stream", true, add_new_stream_ok);
	}
	
	@Test
	public void test_add_new_weather_widget() throws Exception {
		add_new_widget(browser,"weather");
	}
	
	@Test
	public void test_add_new_clock_widget() throws Exception {
		add_new_widget(browser,"clock");
	}
	
	private void add_new_widget(WebDriver browser,String type) throws InterruptedException {
		Media _media = new Media(browser);
		_media.goto_media();
		Thread.sleep(3000);
		MGV_Global.wait_to_load_page(browser);
		
		
		_media.click_media_tree_title("widget");
		Thread.sleep(1000);

		String name = MGV_Utility.get_random_name("-new-widget-" + type);

		_media.add_new_widget(type, name);
		
		if (type == "weather") {
			MGV_Global.set_new_widget_weather_name(name);
		} else {
			MGV_Global.set_new_widget_clock_name(name);
		}

		Thread.sleep(2000);

		boolean add_new_widget_ok = MGV_Utility.is_text_in_gridview(
				browser, "GridListTable_audit", name);
		Assert.assertEquals("Assert add new " + type + " widget", true, add_new_widget_ok);
	}
	
	@Test
	public void test_delete_folder() throws Exception {
		delete_folder(browser);
	}
	private void delete_folder(WebDriver browser) throws Exception {
		Media _media = new Media(browser);
		_media.goto_media();
		Thread.sleep(3000);
		_media.click_media_tree_title("media");
		
//		String folder_name = MGV_Utility.get_random_name("-new-folder-name");
//		String folder_desc = MGV_Utility.get_random_name("-new-folder-desc");
//
//		_media.add_new_folder(folder_name, folder_desc);

		Thread.sleep(2000);
		
		String folder_name = MGV_Global.get_new_media_fodler_name();

		_media.delete_folder(folder_name);
		
		Thread.sleep(2000);
		
		boolean del_folder_ok = MGV_Utility.is_text_in_gridview(browser,
				"GridListTable", folder_name);
		
		Assert.assertEquals("Assert delete folder", false, del_folder_ok);
	}
	
	@Test
	public void test_approve_all_file() throws InterruptedException {
		approve_all_file(browser);
	}
	private void approve_all_file(WebDriver browser) throws InterruptedException {
		Media _media = new Media(browser);
		_media.goto_media();
		Thread.sleep(3000);
		_media.switch_to_audit();
		
		Thread.sleep(1000);
		
		_media.approve_all_medias();
		
		Thread.sleep(2000);

		//ERROR!
//		boolean approve_ok = MGV_Utility.is_text_in_gridview(browser, "GridListTable_audit", "");
		boolean approve_ok = MGV_Utility.is_gridview_blank(browser, "GridListTable_audit");
		
		Assert.assertEquals("Assert approve_all", true, approve_ok);
	}


	@Test
	public void test_media() throws Exception{	
		test_media(browser);
	}

	public void test_media(WebDriver browser){
		
		try
		{
			Media _media = new Media(browser);
			_media.goto_media();
			Thread.sleep(3000);
			
			add_new_folder(browser);
			Thread.sleep(2000);
			
			delete_folder(browser);
			Thread.sleep(2000);
			
			upload_media_file(browser);
			Thread.sleep(2000);
	
			add_new_scrolltext(browser);
			Thread.sleep(2000);
		
			add_new_stream(browser,"http");
			Thread.sleep(2000);
			
			add_new_stream(browser,"rstp");
			Thread.sleep(2000);
	
			add_new_widget(browser,"weather");
			Thread.sleep(2000);
	
			add_new_widget(browser,"clock");
			Thread.sleep(2000);
			
			approve_all_file(browser);
			Thread.sleep(2000);
	
			_media.return_to_default();
		}catch(Exception ex){
			
		}
	}


}
