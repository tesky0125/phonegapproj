package org.backend.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.backend.domain.ScrollShow;
import org.backend.service.ScrollShowService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScrollShowTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
        ScrollShowService userInfoService = new ScrollShowService();
  
        List list = userInfoService.list();
        System.out.println(list);
	}
	
	@Test
	public void testGet() {
        ScrollShowService userInfoService = new ScrollShowService();
  
        ScrollShow userInfo = userInfoService.get(1);
        System.out.println(userInfo.getTitle());
	}
	
	@Test
	public void testInsert() {
		ScrollShow user_info = new ScrollShow();  
        user_info.setId(0);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");    
        user_info.setImage("./content/1.jpg");    
        user_info.setDate_time(new Date());  

        ScrollShowService userInfoService = new ScrollShowService();
  
        userInfoService.insert(user_info);
	}
	
	@Test
	public void testDelete() {

        ScrollShowService userInfoService = new ScrollShowService();
  
        userInfoService.delete(1);
	}
	
	@Test
	public void testUpdate() {
		ScrollShow user_info = new ScrollShow();  
        user_info.setId(2);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");  
        user_info.setImage("./content/2.jpg");    
        user_info.setDate_time(new Date());  

        ScrollShowService userInfoService = new ScrollShowService();
  
        userInfoService.update(user_info);
	}

}
