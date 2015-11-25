package mgv;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Login {

	private WebDriver browser;
	
	public Login(WebDriver browser) {
		this.browser = browser;
	}
	
	public void login_to_mgv(){
		
		browser.switchTo().defaultContent();
		browser.switchTo().frame("iframeLogin");
		
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
		
        WebElement username = browser.findElement(By.id("username"));
        WebElement password = browser.findElement(By.id("password"));
        WebElement submit = browser.findElement(By.id("submit"));
        
        new Actions(browser).moveToElement(username)
            .sendKeys(username, MGV_Global.get_username())
            .moveToElement(password)
            .sendKeys(password, MGV_Global.get_password())
            .moveToElement(submit)
            .click(submit).build().perform();
	}
	
	public boolean check_login_status(){
		
		browser.switchTo().defaultContent();
		browser.switchTo().frame("iframeMain");
		
		return browser.findElement(By.id("GoToMediaButton")).isDisplayed();
	}
	
	public void return_to_default(){   
		browser.switchTo().defaultContent();	
	}
}
