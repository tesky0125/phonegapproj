package test;

import mgv.Client;
import mgv.Login;
import mgv.MGV_Global;
import mgv.MGV_Utility;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ClientTest {

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
	public void test_client_add_new_floder() throws InterruptedException{
		client_add_new_floder(browser);
	}

	private void client_add_new_floder(WebDriver browser) throws InterruptedException{

         Client _client = new Client(browser);
         _client.goto_client();
         Thread.sleep(3000);
         MGV_Global.wait_to_load_page(browser);

         String folder_name = MGV_Utility.get_random_name("-new-folder-name");
         String folder_desc = MGV_Utility.get_random_name("-new-folder-desc");
         MGV_Global.set_new_client_folder_name(folder_name);
         _client.add_new_folder(folder_name, folder_desc);

         Thread.sleep(1000);

         boolean add_new_folder_ok = MGV_Utility.is_text_exist_in_view(browser, "clientView", folder_name);
         Assert.assertEquals("Assert add new folder", true, add_new_folder_ok);
         add_new_folder_ok = MGV_Utility.is_text_exist_in_tree(browser, "clienttree",folder_name);
         Assert.assertEquals("Assert add new folder", true, add_new_folder_ok);
         _client.delelte_folder(folder_name);
   }

	@Test
	public void test_client_add_new_group() throws InterruptedException{
		client_add_new_group(browser);
	}

	private void client_add_new_group(WebDriver browser) throws InterruptedException{
		Client _client = new Client(browser);
		_client.goto_client();
		 Thread.sleep(3000);
		MGV_Global.wait_to_load_page(browser);
		String group_name = MGV_Utility.get_random_name("-new-group-name");
		String group_desc = MGV_Utility.get_random_name("-new-group-desc");
		MGV_Global.set_new_client_group_name(group_name);
    	_client.add_new_group(group_name, group_desc);

        Thread.sleep(1000);

        boolean add_new_group_ok = MGV_Utility.is_text_exist_in_view(browser, "clientView", group_name);
        Assert.assertEquals("Assert add new folder", true, add_new_group_ok);
        add_new_group_ok = MGV_Utility.is_text_exist_in_tree(browser, "clienttree",group_name);
		Assert.assertEquals("Assert add new folder", true, add_new_group_ok);
		_client.delelte_group(group_name);
	}

	@Test
	public void test_client_delete_group() throws InterruptedException{
		client_delete_group(browser);
	}

	private void client_delete_group(WebDriver browser) throws InterruptedException{
		Client _client = new Client(browser);
		_client.goto_client();
		 Thread.sleep(3000);
		MGV_Global.wait_to_load_page(browser);
		String group_name = MGV_Utility.get_random_name("-new-group-name");
		String group_desc = MGV_Utility.get_random_name("-new-group-desc");
		MGV_Global.set_new_client_group_name(group_name);
    	_client.add_new_group(group_name, group_desc);

        Thread.sleep(2000);

        _client.delelte_group(group_name);
     
       Thread.sleep(3000);
  

        boolean add_new_group_ok = MGV_Utility.is_text_exist_in_view(browser, "clientView", group_name);
        Assert.assertEquals("Assert delete group", false, add_new_group_ok);
        add_new_group_ok = MGV_Utility.is_text_exist_in_tree(browser, "clienttree",group_name);
		Assert.assertEquals("Assert delete group", false, add_new_group_ok);
		
	}

	@Test
	public void test_client_delete_folder() throws InterruptedException{
		client_delete_folder(browser);
	}

	private void client_delete_folder(WebDriver browser) throws InterruptedException{
		Client _client = new Client(browser);
		_client.goto_client();
		 Thread.sleep(3000);
		MGV_Global.wait_to_load_page(browser);
		String folder_name = MGV_Utility.get_random_name("-new-group-name");
		String folder_desc = MGV_Utility.get_random_name("-new-group-desc");
		MGV_Global.set_new_client_folder_name(folder_name);
    	_client.add_new_folder(folder_name, folder_desc);

        Thread.sleep(2000);

        _client.delelte_folder(folder_name);
        Thread.sleep(2000);

        boolean add_new_folder_ok = MGV_Utility.is_text_exist_in_view(browser, "clientView", folder_name);
        Assert.assertEquals("Assert delete folder", false, add_new_folder_ok);
        add_new_folder_ok = MGV_Utility.is_text_exist_in_tree(browser, "clienttree",folder_name);
		Assert.assertEquals("Assert delete folder", false, add_new_folder_ok);
	}

	@Test
	public void test_client() throws InterruptedException {
		test_client(browser);
	}

	public void test_client(WebDriver browser) throws InterruptedException {

		Client _client = new Client(browser);
		_client.goto_client();
		Thread.sleep(3000);

		client_add_new_floder(browser);
		Thread.sleep(3000);

		client_add_new_group(browser);
		Thread.sleep(3000);

		client_delete_folder(browser);
		Thread.sleep(3000);


		client_delete_group(browser);
		Thread.sleep(3000);

		_client.return_to_default();

		System.out.println("Client Success");
	}


}
