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

import test.LoginTest;

public class Schedule {

	private WebDriver browser;

	public Schedule(WebDriver browser) {
		this.browser = browser;
	}

	public void goto_schedule() {
		this.browser.switchTo().defaultContent();
		this.browser.switchTo().frame("iframeMain");
		this.browser.findElement(By.id("GoToScheduleButton")).click();
		this.browser.switchTo().frame("iframeSchedule");
	}

	public void return_to_default() {
		this.browser.switchTo().defaultContent();
	}

	public void select_elite_group() {
		// #how to find text include zh-ch
		this.browser.findElement(By.xpath("//*[contains(text(), 'elite')]"))
				.click();
	}

	public void select_block() {
		// pass
	}

	public void select_event() throws InterruptedException {
		WebElement choice_day_btn = this.browser.findElement(By
				.id("choiceDate"));
		Thread.sleep(1000);
		new Actions(this.browser).moveToElement(choice_day_btn)
				.moveByOffset(0, 100).clickAndHold().moveByOffset(0, 300)
				.release().build().perform();
		Thread.sleep(1000);
	}

	public void choice_playlist(String name) throws InterruptedException {
		List<WebElement> element = this.browser.findElements(By.cssSelector("#playlistView ul li span"));
		for(int i=0;i<element.size();i++){
			if(element.get(i).findElement(By.tagName("a")).getAttribute("title").equals(name))
			{
				element.get(i).click();
				break;
			}		
		}
		Thread.sleep(1000);
		this.browser.findElement(By.id("btnScheduleDlgOK")).click();
	}

	public void publish_schedule() {
		this.browser.findElement(By.id("publishButtonDiv")).click();
	}

	public void resize_schedule() throws InterruptedException, AWTException{
		WebElement choice_day_btn = this.browser.findElement(By
				.id("choiceDate"));
		Thread.sleep(1000);
		
		Point coordinates = choice_day_btn.getLocation();
		Robot robot = new Robot();
		int iframeScheduleHeihgt = 130;//px
		robot.mouseMove(coordinates.getX()+20,coordinates.getY()+iframeScheduleHeihgt+430);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		robot.mouseMove(coordinates.getX()+20,coordinates.getY()+iframeScheduleHeihgt+430+150);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
	}
	
	public void drag_schedule() throws InterruptedException, AWTException {
		WebElement choice_day_btn = this.browser.findElement(By
				.id("choiceDate"));
		Thread.sleep(1000);
		
		Point coordinates = choice_day_btn.getLocation();
		Robot robot = new Robot();
		int iframeScheduleHeihgt = 130;//px
		robot.mouseMove(coordinates.getX()+20,coordinates.getY()+iframeScheduleHeihgt+150);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		robot.mouseMove(coordinates.getX()+20,coordinates.getY()+iframeScheduleHeihgt+150+150);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
	}

	public void delete_schedule() throws InterruptedException, AWTException {
		WebElement choice_day_btn = this.browser.findElement(By
				.id("choiceDate"));
		Thread.sleep(1000);
//		// can"t work
//		new Actions(this.browser).moveToElement(choice_day_btn)
//				.moveByOffset(20, 150+150).click().build().perform();
		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		Thread.sleep(1000);
		this.browser.findElement(By.id("deleteButtonDiv")).click();
		Thread.sleep(1000);
		this.browser.findElement(By.id("msgBoxBtnOk")).click();
	}

	
}
