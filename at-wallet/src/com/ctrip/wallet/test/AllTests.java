package com.ctrip.wallet.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBalance.class, TestBankCard.class, TestFastPay.class,
		TestIndex.class, TestLogin.class, TestRetCash.class,
		TestSecurityCenter.class })
public class AllTests {
	public static void main(String args[]){
		
	}
}
