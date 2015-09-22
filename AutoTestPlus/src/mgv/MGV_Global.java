package mgv;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MGV_Global {

	private static final short PAGE_LOAD_TIMEOUT = 10;
	private static final short WAIT_MAX_TIMEOUT = 20;
	private static final boolean IS_AUDIT_ON = false;
	private static final boolean IS_ACL_ON = false;

	private static String driver_path;
	public  static String username;
	private static String password;
	private static String upload_file_path;
	private static String mgv_web_url;


	private static String new_media_fodler_name;
	private static String upload_media_file_name;
	private static String new_scrolltext_name;
	private static String new_stream_rstp_name;
	private static String new_stream_http_name;
	private static String new_widget_clock_name;
	private static String new_widget_weather_name;
	private static String new_widget_rss_name;
	private static String new_playlist_folder_name;
	private static String new_playlist_name;
	private static String new_client_folder_name;
	private static String new_client_group_name;

	static {

		try {
			InputStream inputStream = new FileInputStream("log4j.properties");
			PropertyConfigurator.configure(inputStream);
			inputStream.close();

			Logger logger = Logger.getLogger(MGV_Global.class);

			// InputStream inputStream = MGV_Global.class.getClassLoader()
			// .getResourceAsStream("settings.properties");
			inputStream = new FileInputStream("settings.properties");
			Properties settings = new Properties();
			settings.load(inputStream);

			driver_path = settings.getProperty("driver_path");
			username = settings.getProperty("username");
			password = settings.getProperty("password");
			upload_file_path = settings.getProperty("upload_file_path");
			mgv_web_url = settings.getProperty("mgv_web_url");

			/* System.out.println */
			logger.info("driver_path:" + driver_path);
			logger.info("username:" + username);
			logger.info("password:" + password);
			logger.info("upload_file_path:" + upload_file_path);
			logger.info("mgv_web_url:" + mgv_web_url);
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	};

	public static String get_username() {
		return username;
	}

	public static String get_password() {
		return password;
	}

	public static String get_upload_file_path() {
		return upload_file_path;
	}

	public static String get_mgv_url() {
		return mgv_web_url;
	}

	public static short get_page_load_timeout() {
		return PAGE_LOAD_TIMEOUT;
	}

	public static short get_wait_max_timeout() {
		return WAIT_MAX_TIMEOUT;
	}

	public static void wait_to_load_page(WebDriver browser){
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(),
				TimeUnit.SECONDS);
	}

	public static String get_new_media_fodler_name() {
		return new_media_fodler_name;
	}

	public static void set_new_media_fodler_name(String new_media_fodler_name) {
		MGV_Global.new_media_fodler_name = new_media_fodler_name;
	}

	public static String get_new_client_folder_name() {
		return new_client_folder_name;
	}

	public static void set_new_client_folder_name(String new_media_folder_name) {
		MGV_Global.new_client_folder_name = new_media_folder_name;
	}

	public static String get_new_client_group_name() {
    		return new_client_group_name;
    }

    public static void set_new_client_group_name(String new_media_group_name) {
    		MGV_Global.new_client_group_name = new_media_group_name;
    }

	public static String get_new_playlist_folder_name() {
		return new_playlist_folder_name;
	}

	public static void set_new_playlist_folder_name(String new_playlist_folder_name) {
		MGV_Global.new_playlist_folder_name = new_playlist_folder_name;
	}

	public static void set_upload_meida_file_name(String name){
		MGV_Global.upload_media_file_name = name;
	}

	public static String get_upload_meida_file_name(){
		return upload_media_file_name;
	}

	public static String get_new_scrolltext_name() {
		return new_scrolltext_name;
	}

	public static void set_new_scrolltext_name(String new_scrolltext_name) {
		MGV_Global.new_scrolltext_name = new_scrolltext_name;
	}

	public static String get_new_stream_rstp_name() {
		return new_stream_rstp_name;
	}

	public static void set_new_stream_rstp_name(String new_stream_rstp_name) {
		MGV_Global.new_stream_rstp_name = new_stream_rstp_name;
	}

	public static String get_new_stream_http_name() {
		return new_stream_http_name;
	}

	public static void set_new_stream_http_name(String new_stream_http_name) {
		MGV_Global.new_stream_http_name = new_stream_http_name;
	}

	public static String get_new_widget_clock_name() {
		return new_widget_clock_name;
	}

	public static void set_new_widget_clock_name(String new_widget_clock_name) {
		MGV_Global.new_widget_clock_name = new_widget_clock_name;
	}

	public static String get_new_widget_weather_name() {
		return new_widget_weather_name;
	}

	public static void set_new_widget_weather_name(String new_widget_weather_name) {
		MGV_Global.new_widget_weather_name = new_widget_weather_name;
	}

	public static String get_new_widget_rss_name() {
		return new_widget_rss_name;
	}

	public static void set_new_widget_rss_name(String new_widget_rss_name) {
		MGV_Global.new_widget_rss_name = new_widget_rss_name;
	}

	public static void set_new_playlist_name(String name){
		MGV_Global.new_playlist_name = name;
	}

	public static String get_new_playlist_name(){
		return new_playlist_name;
	}

	public static WebDriver start_browser(String url)
			throws InterruptedException {
		String key = "webdriver.chrome.driver";
		String value = driver_path;
		System.setProperty(key, value);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability("chrome.binary", driver_path);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver= new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.navigate().to(url);
		Thread.sleep(2000);
		return driver;
	}

}
