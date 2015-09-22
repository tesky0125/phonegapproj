package test;

import java.awt.AWTException;

import mgv.Login;
import mgv.MGV_Global;
import mgv.MGV_Utility;
import mgv.Media;
import mgv.Playlist;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class PlaylistTest {

	private WebDriver browser = null;

	@Before
	public void setUp() throws Exception {
		String web_url = MGV_Global.get_mgv_url();
		browser = MGV_Global.start_browser(web_url);
		new LoginTest().test_login(browser);
		new MediaTest().test_media(browser);
		Thread.sleep(3000);
	}

	@After
	public void tearDown() throws Exception {
		browser.quit();
	}

	@Test
	public void test_playlist_add_playlist() throws InterruptedException {
		playlist_add_playlist(browser);
	}
	
	public void playlist_add_playlist(WebDriver browser) throws InterruptedException {
		Playlist _playlist = new Playlist(browser);
		_playlist.goto_playlist();
		Thread.sleep(2000);
		MGV_Global.wait_to_load_page(browser);

		String playlist_name = MGV_Utility.get_random_name("-new-playlist-name");
		String playlist_desc = MGV_Utility.get_random_name("-new-playlist-desc");
		_playlist.add_new_playlist(playlist_name, playlist_desc);

		_playlist.return_to_playlist();
		Thread.sleep(1000);

		boolean add_playlist_ok = MGV_Utility.is_text_in_gridview(browser,
				"AuditPlaylistGridView", playlist_name);
		Assert.assertEquals("Assert add new playlist", true, add_playlist_ok);

		_playlist.approve_all_playlists();

	}
	
	@Test
	public void test_search_playlist() throws InterruptedException, AWTException {
		playlist_search_playlist_after_new(browser);		
	}
	
	public void playlist_search_playlist_after_new(WebDriver browser) throws InterruptedException, AWTException {
		Playlist _playlist = new Playlist(browser);
		_playlist.goto_playlist();
		Thread.sleep(2000);
		MGV_Global.wait_to_load_page(browser);
		
		String playlist_name = MGV_Utility.get_random_name("-new-playlist-name");
		String playlist_desc = MGV_Utility.get_random_name("-new-playlist-name");
		
		_playlist.add_new_playlist(playlist_name, playlist_desc);
		Thread.sleep(1000);
		_playlist.return_to_playlist();
		Thread.sleep(1000);

		_playlist.switch_to_audit();
		Thread.sleep(1000);
		_playlist.approve_all_playlists();
		Thread.sleep(1000);
		
		String search_name = "new";
		_playlist.search_playlist(search_name);
		Thread.sleep(1000);
		
		boolean search_ok = MGV_Utility.is_text_in_gridview(browser,
				"SearchPlaylistGridView", search_name);
		Assert.assertEquals("Assert search playlist", true, search_ok);
	}
	
	public void playlist_create_new_playlist(WebDriver browser) throws InterruptedException {
		Playlist _playlist = new Playlist(browser);
		String folder_name = MGV_Utility.get_random_name("-new-folder-name");
		String folder_desc = MGV_Utility.get_random_name("-new-folder-desc");
		_playlist.add_new_folder(folder_name, folder_desc);
		Thread.sleep(1000);

		String playlist_name = MGV_Utility.get_random_name("-new-playlist-name");
		String playlist_desc = MGV_Utility.get_random_name("-new-playlist-name");
		MGV_Global.set_new_playlist_name(playlist_name);
		MGV_Global.set_new_playlist_folder_name(folder_name);
		_playlist.add_new_playlist(playlist_name, playlist_desc);
		Thread.sleep(1000);
		_playlist.return_to_playlist();
		Thread.sleep(1000);

		_playlist.switch_to_audit();


	}
	
	public void playlist_edit_and_save_playlist(WebDriver browser) throws InterruptedException, AWTException {
		Playlist _playlist = new Playlist(browser);
		String playlist_name = MGV_Global.get_new_playlist_name();
		_playlist.enter_playlist_designer(playlist_name);
		Thread.sleep(3);
		_playlist.add_new_scene();
		Thread.sleep(1000);
		_playlist.add_new_layer("media");
		Thread.sleep(1000);
		String media_name = MGV_Global.get_upload_meida_file_name();
		_playlist.drag_media_to_layer(media_name);
		Thread.sleep(1000);
		_playlist.add_new_layer("scrolltext");
		Thread.sleep(1000);
		_playlist.click_media_tree_title("scrolltext");
		Thread.sleep(3000);
		String scrolltext_name = MGV_Global.get_new_scrolltext_name();
		_playlist.drag_scrolltext_to_layer(scrolltext_name);
		Thread.sleep(1000);
		// drag media to layer
		_playlist.save_playlist();

	}
	
	public void playlist_return_to_playlist(WebDriver browser) throws InterruptedException {
		Playlist _playlist = new Playlist(browser);
		_playlist.return_to_playlist();
	}
	
	public void playlist_delete_folder(WebDriver browser) throws InterruptedException {
		Playlist _playlist = new Playlist(browser);
		String folder_name = MGV_Global.get_new_playlist_folder_name();
		_playlist.click_playlist_tree_title();
		Thread.sleep(1000);
		_playlist.delete_folder(folder_name);
	}
	
	public void playlist_approve_all(WebDriver browser) throws InterruptedException {
		Playlist _playlist = new Playlist(browser);
		_playlist.switch_to_audit();
		Thread.sleep(3000);
		_playlist.approve_all_playlists();
	}
	
	public void playlist_reject_all(WebDriver browser) throws InterruptedException {
		Playlist _playlist = new Playlist(browser);
		_playlist.reject_all_playlists();
		Thread.sleep(1000);
		_playlist.delete_all_rejected_playlists();
	}
	
	public void playlist_search_playlist(WebDriver browser) throws InterruptedException {
		Playlist _playlist = new Playlist(browser);
		String playlist_name = "new";
		_playlist.search_playlist(playlist_name);
	}
	
	public void playlist_switch_to_normal(WebDriver browser) throws InterruptedException {
		Playlist _playlist = new Playlist(browser);
		_playlist.switch_to_normal();
	}

	@Test
	public void test_playlist() throws InterruptedException, AWTException {
		test_playlist(browser);
	}
	
	public void test_playlist(WebDriver browser) throws InterruptedException, AWTException {
		Playlist _playlist = new Playlist(browser);
		_playlist.goto_playlist();
		Thread.sleep(3);
		playlist_create_new_playlist(browser);
		Thread.sleep(1000);
		playlist_edit_and_save_playlist(browser);
		Thread.sleep(7000);
		playlist_return_to_playlist(browser);
		Thread.sleep(1000);
		playlist_delete_folder(browser);
		Thread.sleep(1000);
		playlist_approve_all(browser);
		Thread.sleep(1000);
//		playlist_switch_to_normal(browser);
//		Thread.sleep(1000);
//		playlist_search_playlist(browser);
//		Thread.sleep(1000);
//		playlist_switch_to_normal(browser);
//		Thread.sleep(1000);
		_playlist.return_to_default();
		System.out.println("Playlist Success");

	}
	
	@Test
	public void test_approve_all() throws InterruptedException, AWTException {
		Playlist _playlist = new Playlist(browser);
		_playlist.goto_playlist();
		Thread.sleep(3);
		playlist_approve_all(browser);
	}

}
