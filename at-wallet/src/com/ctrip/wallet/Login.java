/**
* @module login page
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.7
*/
package com.ctrip.wallet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ctrip.wallet.base.ILogin;
import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.util.Global;

public class Login extends WalletBase implements ILogin{
private WebDriver browser;
	
	public Login(WebDriver browser) {
		super(browser, ".login_tab");
		this.browser = browser;
	}
		
	public void login(){
		
        WebElement username = browser.findElement(By.id("username"));
        WebElement password = browser.findElement(By.id("password"));
        WebElement submit = browser.findElement(By.id("submit"));

        new Actions(browser)
        	.sendKeys(username, Global.getUsername())
            .sendKeys(password, Global.getPassword())
            .click(submit).build().perform();
        
        this.elementToBeClickable(".J_Balance");
	}
}
