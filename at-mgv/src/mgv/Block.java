package mgv;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Block {

	private WebDriver browser;

	public Block(WebDriver browser) {
		this.browser = browser;
	}

	public void goto_block() {
		this.browser.switchTo().defaultContent();
		this.browser.switchTo().frame("iframeMain");
		this.browser.findElement(By.cssSelector("#mainMenuBlock > li")).click();
		this.browser.switchTo().frame("iframeBlock");
	}

	public void return_to_default() {
		this.browser.switchTo().defaultContent();
	}

	public void add_new_layout(String name){
		this.browser.findElement(By.id("CreateBlockDiv")).click();
		
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
		
		this.browser.findElement(By.id("PlaylistName")).sendKeys(name);
		new Select(this.browser.findElement(By.id("PlaylistDimentionSelect")))
				.selectByIndex(0);
		this.browser.findElement(By.id("createBlockSaveButton")).click();
	}
	
	public boolean check_add_new_layout(String layout_name) throws InterruptedException {
		
		add_new_block();
		
    	MGV_Utility.is_text_exist_in_view(browser, "canvasContainer", "block");
    	save_layout();
    	
    	browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
    	
    	Thread.sleep(2000);
    	return_to_layout();
    	
    	browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
    	
    	if(MGV_Utility.is_text_exist_in_view(browser, "containerOfBlockListView", layout_name))
    		return true;
    	
		return false;
	}
	
	public void add_new_block() {
		browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
		
		this.browser.findElement(By.id("btnNewBlockDiv")).click();
	}

	public void save_layout() {
		this.browser.findElement(By.id("btnSaveBlockDiv")).click();
	}

	public void return_to_layout() throws InterruptedException {
		
		Thread.sleep(2000);
		this.browser.findElement(By.id("btnCloseBlockDiv")).click();
	}
	
	public Point drag_block() throws Exception {

		Point des_point = new Point(0, 0);
		
		try {
			Robot robot = new Robot();
			int iframeHeihgt = 130;//px
			int x_offset = 150;
			int y_offset = 150;
			
			WebElement designer_canvas = this.browser.findElement(By
					.id("designerDivcanvasContainer"));

			Point src_point = designer_canvas.getLocation();

			robot.mouseMove(src_point.getX() + 20, src_point.getY() + iframeHeihgt + 20);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			
			Thread.sleep(1000);
			
			robot.mouseMove(src_point.getX()+ 20 + x_offset, src_point.getY() + iframeHeihgt + 20 + y_offset);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);	
			
			des_point.x = des_point.getX() + x_offset;
			des_point.y = des_point.getY() + y_offset;
			
		} catch (Exception ex) {
			throw ex;
		}
		
		return des_point;
	}
	
	public boolean check_drag_block(Point des_point) throws InterruptedException {	
    	//WebElement txt_x_element = this.browser.findElement(By.id("txtX"));
    	
    	//WebElement txt_y_element = this.browser.findElement(By.id("txtY"));
    	
    	//if(des_point.getX() == Integer.parseInt(txt_x_element.getAttribute("value")) &&
    	  // des_point.getY() == Integer.parseInt(txt_y_element.getAttribute("value")))
    	  // return true;
		save_layout();
    	
    	browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
    	
    	Thread.sleep(2000);
    	return_to_layout();
    	
    	browser.manage().timeouts().pageLoadTimeout(MGV_Global.get_page_load_timeout(), 
				TimeUnit.SECONDS);
			
		return true;
	}
	
	public void publish_layout(String name) throws InterruptedException {
		WebElement layout_title = this.browser.findElement(By
				.cssSelector("#BlockListView li a[title='" + name + "']"));
		Thread.sleep(1000);
		new Actions(this.browser).moveToElement(layout_title, 0, -20).click()
				.build().perform();
		Thread.sleep(1000);
		this.browser.findElement(By.id("PublishBlockDiv")).click();
		Thread.sleep(1000);
		this.browser.findElement(By.xpath("//*[contains(text(), 'elite')]"))
				.click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("publishBlockOkButton")).click();
	}

	public void delete_layout(String name) throws InterruptedException {
		WebElement layout_title = this.browser.findElement(By
				.cssSelector("#BlockListView li a[title='" + name + "']"));
		Thread.sleep(1000);
		new Actions(this.browser).moveToElement(layout_title, 0, -20).click()
				.build().perform();
		Thread.sleep(1000);
		this.browser.findElement(By.id("DeleteBlockDiv")).click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("msgBoxBtnOk")).click();
	}
}
