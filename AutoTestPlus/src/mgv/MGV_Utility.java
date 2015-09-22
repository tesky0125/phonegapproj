package mgv;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MGV_Utility {
	
	public static boolean is_text_in_gridview(WebDriver browser,String element_name, String test_name) {		
		WebElement element = new WebDriverWait(browser, MGV_Global.get_wait_max_timeout()).until(ExpectedConditions.visibilityOfElementLocated(By.id(element_name)));
		List<WebElement> subElements = element.findElements(By.cssSelector("tr td span"));
		for(int i = 0;i<subElements.size();i++){
			String element_title = subElements.get(i).getAttribute("title");
			if(element_title.contains(test_name)){
				return true;
			}
		}
		return false;
	}  
	
	public static boolean is_gridview_blank(WebDriver browser,String element_name){
		WebElement element = new WebDriverWait(browser, MGV_Global.get_wait_max_timeout()).until(ExpectedConditions.visibilityOfElementLocated(By.id(element_name)));
		List<WebElement> subElements = element.findElements(By.cssSelector("tr"));
		if(subElements.size() == 2)
			return true;
		return false;
	}
	
	public static boolean is_text_exist_in_view(WebDriver browser,String element_name,String test_name){
		WebElement element = new WebDriverWait(browser, MGV_Global.get_wait_max_timeout()).until(ExpectedConditions.visibilityOfElementLocated(By.id(element_name)));
		List<WebElement> subElements = element.findElements(By.cssSelector("li span a"));
		for(int i = 0;i<subElements.size();i++){
			String element_title = subElements.get(i).getAttribute("title");
			if(element_title.contains(test_name)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean is_text_exist_in_tree(WebDriver browser,String element_name,String test_name){
		WebElement element = new WebDriverWait(browser, MGV_Global.get_wait_max_timeout()).until(ExpectedConditions.visibilityOfElementLocated(By.id(element_name)));
		List<WebElement> subElements = element.findElements(By.cssSelector("li a"));
		for(int i = 0;i<subElements.size();i++){
			String element_title = subElements.get(i).getAttribute("title");
			if(element_title.contains(test_name)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean is_event_exist_in_calendar(WebDriver browser,String element_name,String test_name){
		WebElement element = new WebDriverWait(browser, MGV_Global.get_wait_max_timeout()).until(ExpectedConditions.visibilityOfElementLocated(By.id(element_name)));
		List<WebElement> subElements = element.findElements(By.xpath("//*[@id='calendar']/div/div/div/div/div/div/div/div[1]/div[1]"));
		for(int i = 0;i<subElements.size();i++){
			String element_html = subElements.get(i).getText();
			if(element_html.compareTo(test_name) == 0){
				return true;
			}
		}
		return false;
	}
	
	public static void close_file_dialog() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_F4);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static String get_random_name(String name){
		return MGV_Global.username + "-test" + "_" + name + "-" +  new Date().getTime();
	}
}
