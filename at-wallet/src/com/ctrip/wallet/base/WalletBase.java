package com.ctrip.wallet.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ctrip.wallet.model.Constant;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class WalletBase {
	private WebDriver browser;

	public WalletBase(WebDriver browser){
		this.browser = browser;
		browser.manage().timeouts()
				.pageLoadTimeout(Global.getPageLoadTimeout(), TimeUnit.SECONDS);
	}
	
	public WalletBase(WebDriver browser, String expectElemCssName) {
		this(browser);
		Utility.elementToBePresence(browser, expectElemCssName);
	}
	
	public void clickElement(String clickElemCssName){
		Utility.elementToBeClickable(browser, clickElemCssName)/* .click() */;
		Utility.waitAMoment();
		Utility.clickElement(browser, clickElemCssName);
	}
	
	public void inputElement(String elemCssName,String strInput){
		Utility.elementToBeClickable(browser, elemCssName)/* .click() */;
		Utility.waitAMoment();
		Utility.inputElement(browser, elemCssName, strInput);
	}
	
	public void selectElement(String elemCssName,int selIndex){
		Utility.elementToBeClickable(browser, elemCssName)/* .click() */;
		Utility.waitAMoment();
		Utility.selectElement(browser, elemCssName, selIndex);
	}
	
	private WebElement elementToExpect(String expectElemCssName, int expectElemStatus){
		return Utility.elementToExpect(browser, expectElemCssName, expectElemStatus);
	}
	
	public WebElement elementToBePresence(String expectElemCssName){
		return this.elementToExpect(expectElemCssName, Constant.ElemStatus.Presence);
	}
	
	public WebElement elementToBeVisible(String expectElemCssName){
		return this.elementToExpect(expectElemCssName, Constant.ElemStatus.Visible);
	}
	
	public WebElement elementToBeClickable(String expectElemCssName){
		return this.elementToExpect(expectElemCssName, Constant.ElemStatus.Clickable);
	}

	private void clickElementToExpect(String clickElemCssName, String expectElemCssName,
			int expectElemStatus) {
		this.clickElement(clickElemCssName);
		this.elementToExpect(expectElemCssName, expectElemStatus);
	}
	
	public void clickElementToExpectClickable(String clickElemCssName, String expectElemCssName) {
		this.clickElementToExpect(clickElemCssName, expectElemCssName, Constant.ElemStatus.Clickable);
	}
	
	public void clickElementToExpectVisible(String clickElemCssName, String expectElemCssName) {
		this.clickElementToExpect(clickElemCssName, expectElemCssName, Constant.ElemStatus.Visible);
	}
	
	public void clickElementToExpectPresence(String clickElemCssName, String expectElemCssName) {
		this.clickElementToExpect(clickElemCssName, expectElemCssName, Constant.ElemStatus.Presence);
	}
	
	public boolean existElement(String elemCssName){
		return Utility.existElement(browser, elemCssName);
	}
	
	public void back(){
		this.elementToBeClickable(".js_back");
		this.clickElement(".js_back");
		Utility.waitAMoment();
	}
	
	public void backToExpect(String expectCssName) {
		this.back();
		this.elementToExpect(expectCssName, Constant.ElemStatus.Presence);
	}
}
