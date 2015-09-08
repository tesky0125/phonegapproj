package org.backend.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.backend.domain.UserInfo;
import org.backend.service.UserInfoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserInfoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
        UserInfoService userInfoService = new UserInfoService();
  
        List list = userInfoService.list();
        System.out.println(list);
	}
	
	@Test
	public void testGet() {
        UserInfoService userInfoService = new UserInfoService();
  
        UserInfo userInfo = userInfoService.get(3);
        System.out.println(userInfo.getUser());
	}
	
	@Test
	public void testInsert() {
		UserInfo user_info = new UserInfo();  
        user_info.setId(0);  
        user_info.setUser("yang");  
        user_info.setPwd("yang22");  
        user_info.setEmail("yang@qq.com");  
        user_info.setDate_time(new Date());  

        UserInfoService userInfoService = new UserInfoService();
  
        userInfoService.insert(user_info);
	}
	
	@Test
	public void testDelete() {

        UserInfoService userInfoService = new UserInfoService();
  
        userInfoService.delete(3);
	}
	
	@Test
	public void testUpdate() {
		UserInfo user_info = new UserInfo();  
        user_info.setId(4);  
        user_info.setUser("yang");  
        user_info.setPwd("yang22");  
        user_info.setEmail("yang@qq.com");  
        user_info.setDate_time(new Date());  

        UserInfoService userInfoService = new UserInfoService();
  
        userInfoService.update(user_info);
	}

}
