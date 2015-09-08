package org.backend.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.backend.domain.EmployGuide;
import org.backend.service.EmployGuideService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployGuideTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
        EmployGuideService userInfoService = new EmployGuideService();
  
        List list = userInfoService.list();
        System.out.println(list);
	}
	
	@Test
	public void testGet() {
        EmployGuideService userInfoService = new EmployGuideService();
  
        EmployGuide userInfo = userInfoService.get(1);
        System.out.println(userInfo.getTitle());
	}
	
	@Test
	public void testInsert() {
		EmployGuide user_info = new EmployGuide();  
        user_info.setId(0);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");    
        user_info.setDate_time(new Date());  

        EmployGuideService userInfoService = new EmployGuideService();
  
        userInfoService.insert(user_info);
	}
	
	@Test
	public void testDelete() {

        EmployGuideService userInfoService = new EmployGuideService();
  
        userInfoService.delete(1);
	}
	
	@Test
	public void testUpdate() {
		EmployGuide user_info = new EmployGuide();  
        user_info.setId(2);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");  
        user_info.setDate_time(new Date());  

        EmployGuideService userInfoService = new EmployGuideService();
  
        userInfoService.update(user_info);
	}

}
