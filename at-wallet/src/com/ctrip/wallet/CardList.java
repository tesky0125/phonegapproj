/**
* @module card list
* @author yanjj
* @description wallet autotest 
* @version since Wallet V6.8
*/
package com.ctrip.wallet;

import org.openqa.selenium.WebDriver;

import com.ctrip.wallet.base.WalletBase;
import com.ctrip.wallet.model.Constant;
import com.ctrip.wallet.util.Utility;

public class CardList  extends WalletBase{
	private String type;
	
	public CardList(WebDriver browser, String type) {
		super(browser);
		this.type = type;
		
		String expectElemCssName = this.getAddNoCssByType(type);
		Utility.elementToBePresence(browser, expectElemCssName);
	}
	
	private String getAddNoCssByType(String type){
		if(type.equals("bindcard")){
			return ".J_addNo";
		}else if(type.equals("fastpay")){
			return ".J_AddCard";
		}else if(type.equals("withdraw")){
			return ".J_AddNewCard";
		}else if(type.equals("transfer")){
			return ".J_AddNewCard";
		}else{
			return "";
		}
	}
	
	public void goToAddCard(){
		String addNoCssName = this.getAddNoCssByType(type);
		this.clickElementToExpectClickable(addNoCssName, ".J_NextBtn");
		Utility.waitAMoment();
	}
}
