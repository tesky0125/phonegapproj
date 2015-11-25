/**
* @module wallet global vars
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Global {
	
	private static final Logger LOGGER = Logger.getLogger(Global.class);
	
	private Global(){}
	
	private static final short PAGE_LOAD_TIMEOUT = 30;
	private static final short WAIT_MAX_TIMEOUT = 30;

	private static String driverPath;
	private static String walletUrl;
	private static String loginUrl;
	private static String debugUrl;
	
	private static String entryType;
	private static String useDomain;
	private static String domainName;
	private static String domainPath;
	private static String useMock;
	private static String useAuth;
	private static String auth;
	
	private  static String username;
	private static String password;
	private static String paypwd;
	
	private static String bankCardType;
	private static String bankCardNo;
	private static String bankCardPwd;
	private static String bankCardName;
	private static String bankCardIdCode;
	private static String bankCardPretel;
	
	private static String creditCardType;
	private static String creditCardNo;
	private static String creditCardName;
	private static String creditCardIdCode;
	private static String creditCardPretel;
	private static String creditCardCvn;
	private static String creditCardExp;
	
	public static String getBankCardType() {
		return bankCardType;
	}

	public static String getCreditCardType() {
		return creditCardType;
	}

	public static String getDriverPath() {
		return driverPath;
	}

	public static String getWalletUrl() {
		return walletUrl;
	}

	public static String getLoginUrl() {
		return loginUrl;
	}

	public static String getDebugUrl() {
		return debugUrl;
	}

	public static String getEntryType() {
		return entryType;
	}

	public static String getUseDomain() {
		return useDomain;
	}

	public static String getDomainName() {
		return domainName;
	}

	public static String getDomainPath() {
		return domainPath;
	}

	public static String getUseMock() {
		return useMock;
	}

	public static String getUseAuth() {
		return useAuth;
	}

	public static String getAuth() {
		return auth;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	public static String getPaypwd() {
		return paypwd;
	}

	public static String getBankCardNo() {
		return bankCardNo;
	}

	public static String getBankCardPwd() {
		return bankCardPwd;
	}

	public static String getBankCardName() {
		return bankCardName;
	}

	public static String getBankCardIdCode() {
		return bankCardIdCode;
	}

	public static String getBankCardPretel() {
		return bankCardPretel;
	}

	public static String getCreditCardNo() {
		return creditCardNo;
	}

	public static String getCreditCardName() {
		return creditCardName;
	}

	public static String getCreditCardIdCode() {
		return creditCardIdCode;
	}

	public static String getCreditCardPretel() {
		return creditCardPretel;
	}

	public static String getCreditCardCvn() {
		return creditCardCvn;
	}

	public static String getCreditCardExp() {
		return creditCardExp;
	}

	public static short getPageLoadTimeout() {
		return PAGE_LOAD_TIMEOUT;
	}
	
	public static short getWaitMaxTimeout() {
		return WAIT_MAX_TIMEOUT;
	}
	
	static {

		try {
			InputStream inputStream = new FileInputStream("log4j.properties");
			PropertyConfigurator.configure(inputStream);
			inputStream.close();

			inputStream = new FileInputStream("settings.properties");
			Properties settings = new Properties();
			settings.load(inputStream);

			driverPath = settings.getProperty("DriverPath");
			walletUrl = settings.getProperty("WalletUrl");
			loginUrl = settings.getProperty("LoginUrl");
			debugUrl = settings.getProperty("DebugUrl");

			entryType = settings.getProperty("EntryType");
			useDomain = settings.getProperty("UseDomain");
			domainName = settings.getProperty("DomainName");
			domainPath = settings.getProperty("DomainPath");
			useMock = settings.getProperty("UseMock");
			useAuth = settings.getProperty("UseAuth");
			auth = settings.getProperty("Auth");
			
			username = settings.getProperty("Username");
			password = settings.getProperty("Password");
			paypwd = settings.getProperty("Paypwd");
			
			bankCardType = settings.getProperty("BankCardType");
			bankCardNo = settings.getProperty("BankCardNo");
			bankCardPwd = settings.getProperty("BankCardPwd");
			bankCardName = settings.getProperty("BankCardName");
			bankCardIdCode = settings.getProperty("BankCardIdCode");
			bankCardPretel = settings.getProperty("BankCardPretel");
			
			creditCardType = settings.getProperty("CreditCardType");
			creditCardNo = settings.getProperty("CreditCardNo");
			creditCardName = settings.getProperty("CreditCardName");
			creditCardIdCode = settings.getProperty("CreditCardIdCode");
			creditCardPretel = settings.getProperty("CreditCardPretel");
			creditCardCvn = settings.getProperty("CreditCardCvn");
			creditCardExp = settings.getProperty("CreditCardExp");

			/* System.out.println */
			LOGGER.info("driver_path:" + driverPath);
			inputStream.close();

		} catch (IOException e) {
			//e.printStackTrace();
			LOGGER.info(e.getMessage());
		}

	};

	public static WebDriver startBrowser(/*String url*/) {
		String key = "webdriver.chrome.driver";
		String value = driverPath;
		System.setProperty(key, value);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability("chrome.binary", driverPath);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		WebDriver driver= new ChromeDriver(capabilities);
		driver.manage().window().maximize();
		//driver.navigate().to(url);
		return driver;
	}
	
	public static void navigateTo(WebDriver browser, String url) {
		browser.navigate().to(url);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			//e.printStackTrace();
			LOGGER.info(e.getMessage());
		}
		Utility.waitAMoment();
	}
}
