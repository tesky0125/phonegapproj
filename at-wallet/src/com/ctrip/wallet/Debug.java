/**
* @module debug page
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.8
*/
package com.ctrip.wallet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ctrip.wallet.base.ILogin;
import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.util.Global;
import com.ctrip.wallet.util.Utility;

public class Debug extends WalletBase implements ILogin{
private WebDriver browser;
	
	public Debug(WebDriver browser) {
		super(browser, ".J_Submit");
		this.browser = browser;
	}
		
	public void login(){
		String useDomain = Global.getUseDomain();
		String useMock = Global.getUseMock();
		String useAuth = Global.getUseAuth();
		
        WebElement chkDomain = browser.findElement(By.cssSelector(".J_Open"));
        WebElement inputDomain = browser.findElement(By.cssSelector(".J_Domain"));
        WebElement inputPath = browser.findElement(By.cssSelector(".J_Path"));
        WebElement chkMock = browser.findElement(By.cssSelector(".J_ChkMock"));
        WebElement chkAuth = browser.findElement(By.cssSelector(".J_ChkAuth"));
        WebElement inputAuth = browser.findElement(By.cssSelector(".J_Auth"));
        
        if(useDomain.equals("true")){
        	chkDomain.click();
        	inputDomain.sendKeys(Global.getDomainName());
        	inputPath.sendKeys(Global.getDomainPath());
        }
        Utility.waitAMoment(1);
        
        if(useMock.equals("true")){
        	chkMock.click();
        }
        Utility.waitAMoment(1);
        
        if(useAuth.equals("true")){
        	chkAuth.click();
        	inputAuth.clear();
        	inputAuth.sendKeys(Global.getAuth());
        }
        Utility.waitAMoment(1);
        
        this.clickElement(".J_Submit");
        Utility.waitAMoment();
        
        this.back();
        Utility.waitAMoment();
        
        this.elementToBeClickable(".J_Balance");
	}
}
