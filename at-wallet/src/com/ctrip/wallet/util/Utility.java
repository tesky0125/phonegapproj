/**
* @module utility
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ctrip.wallet.model.Constant;

public class Utility {
	private static final Logger LOGGER = Logger.getLogger(Global.class);
	
	private Utility(){}
	/*
	 * presenceOfElementLocated
	 * visibilityOfElementLocated
	 * elementToBeClickable
	 * textToBePresentInElement
	 */
	public static WebElement elementToBePresence(WebDriver browser,String elemCssName) {
		try{
			WebElement elem = new WebDriverWait(browser, Global.getWaitMaxTimeout()).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elemCssName)));
			LOGGER.info(elemCssName+" ToBePresence isDisplayed:"+elem.isDisplayed());
			return elem;
		}catch(TimeoutException e){
			LOGGER.info(elemCssName+" TimeoutException!");
			throw e;
		}
	} 
	
	public static WebElement elementToBeVisible(WebDriver browser,String elemCssName) {		
		try{
			WebElement elem = new WebDriverWait(browser, Global.getWaitMaxTimeout()).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elemCssName)));
			LOGGER.info(elemCssName+" ToBeVisible isDisplayed:"+elem.isDisplayed());
			return elem;
		}catch(TimeoutException e){
			LOGGER.info(elemCssName+" TimeoutException!");
			throw e;
		}
	} 
	
	public static WebElement elementToBeClickable(WebDriver browser,String elemCssName) {		
		try{
			WebElement elem = new WebDriverWait(browser, Global.getWaitMaxTimeout()).until(ExpectedConditions.elementToBeClickable(By.cssSelector(elemCssName)));
			LOGGER.info(elemCssName+" ToBeClickable isDisplayed:"+elem.isDisplayed());
			return elem;
		}catch(TimeoutException e){
			LOGGER.info(elemCssName+" TimeoutException!");
			throw e;
		}
	} 
	
	public static WebElement elementToExpect(WebDriver browser, String expectElemCssName, int expectElemStatus){
		WebElement element = null;
		if (!expectElemCssName.equals("")) {
			switch (expectElemStatus) {
			case Constant.ElemStatus.Presence:
				element = Utility.elementToBePresence(browser, expectElemCssName);
				break;
			case Constant.ElemStatus.Visible:
				element = Utility.elementToBeVisible(browser, expectElemCssName);
				break;
			case Constant.ElemStatus.Clickable:
				element = Utility.elementToBeClickable(browser, expectElemCssName);
				break;
			default:
				element = Utility.elementToBePresence(browser, expectElemCssName);
				break;
			}
		}
		return element;
	}
	
	public static void clickElement(WebDriver browser,String elemCssName){
		WebElement element = browser.findElement(By.cssSelector(elemCssName));
		new Actions(browser).moveToElement(element).click().build().perform();
	}
	
	public static void inputElement(WebDriver browser,String elemCssName,String strInput){
		WebElement element = browser.findElement(By.cssSelector(elemCssName));
		new Actions(browser).moveToElement(element).sendKeys(element,strInput).build().perform();
	}
	
	public static void selectElement(WebDriver browser,String elemCssName,int selIndex){
		WebElement element = browser.findElement(By.cssSelector(elemCssName));
		new Select(element).selectByIndex(selIndex);
	}
	
	public static boolean existElement(WebDriver browser,String elemCssName){
		try{
			WebElement elem = new WebDriverWait(browser, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elemCssName)));
			LOGGER.info(elemCssName+" isDisplayed:"+elem.isDisplayed());
			return elem.isDisplayed();
		}catch(TimeoutException e){
			LOGGER.info(elemCssName+" isDisplayed:false");
			return false;
		}
	}
	
	public static void waitMaskToHide(WebDriver browser){
		//TODO
		WebElement elem = new WebDriverWait(browser, Global.getWaitMaxTimeout()).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cui-mask")));
		LOGGER.info(elem.getTagName()+":.cui-mask:"+elem.isDisplayed());
	}

	public static void waitAMoment(){
		waitAMoment(2);
	}
	
	public static void waitAMoment(int secs){
		try {
			Thread.sleep(secs*1000);
		} catch (InterruptedException e) {
			//e.printStackTrace();
			LOGGER.info(e.getMessage());
		}
	}
	
	public static String getLineInfo()
    {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        return ste.getFileName() + ": Line " + ste.getLineNumber();
    }
}
