package mgv;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Client {

	private WebDriver browser;

	public Client(WebDriver browser) {
		this.browser = browser;
	}

	public void goto_client() {
		this.browser.switchTo().defaultContent();
		this.browser.switchTo().frame("iframeMain");
		this.browser.findElement(By.id("GoToClientButton")).click();
		this.browser.switchTo().frame("iframeClient");
	}

	public void return_to_default() {
		this.browser.switchTo().defaultContent();
	}

	public void add_new_folder(String name, String desc)
			throws InterruptedException {
		this.browser.findElement(By.id("CreateFolderButtonDiv")).click();
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
        
		this.browser.findElement(By.id("FolderName")).sendKeys(name);	
		this.browser.findElement(By.id("FolderDesc")).sendKeys(desc);
		this.browser.findElement(By.id("folderSubmit_create_folder")).click();
	}

	public void add_new_group(String name, String desc)
			throws InterruptedException {
		this.browser.findElement(By.id("CreateGroupButtonDiv")).click();
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
        
		this.browser.findElement(By.id("ClientGroupName")).sendKeys(name);
		this.browser.findElement(By.id("ClientGroupDesc")).sendKeys(desc);
		new Select(this.browser.findElement(By.id("ClientGroupTypeSelect")))
				.selectByIndex(0);
		this.browser.findElement(By.id("clientGroupSubmit_create_group"))
				.click();
	}

	public void delelte_folder(String name) throws InterruptedException {
		WebElement folder_title = this.browser.findElement(By
				.cssSelector("#clientView li a[title='" + name + "']"));
		
		new Actions(this.browser).moveToElement(folder_title, 0, -20).click()
				.build().perform();
		Thread.sleep(1000);
		this.browser.findElement(By.id("DeleteButtonDiv")).click();
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
		
		this.browser.findElement(By.id("delete_submit")).click();
	}

	public void delelte_group(String name) throws InterruptedException {
		WebElement group_title = this.browser.findElement(By
				.cssSelector("#clientView li a[title='" + name + "']"));
		
		new Actions(this.browser).moveToElement(group_title, 0, -20).click()
				.build().perform();
		Thread.sleep(1000);
		this.browser.findElement(By.id("DeleteButtonDiv")).click();
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
		this.browser.findElement(By.id("delete_submit")).click();
	}
	

}
