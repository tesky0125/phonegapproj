package org.backend.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.backend.domain.DynamicMesg;
import org.backend.service.DynamicMesgService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DynamicMesgTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
		DynamicMesgService userInfoService = new DynamicMesgService();
  
        List list = userInfoService.list();
        System.out.println(list);
	}
	
	@Test
	public void testGet() {
        DynamicMesgService userInfoService = new DynamicMesgService();
  
        DynamicMesg userInfo = userInfoService.get(1);
        System.out.println(userInfo.getTitle());
	}
	
	@Test
	public void testInsert() {
		DynamicMesg user_info = new DynamicMesg();  
        user_info.setId(0);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");    
        user_info.setDate_time(new Date());  

        DynamicMesgService userInfoService = new DynamicMesgService();
  
        userInfoService.insert(user_info);
	}
	
	@Test
	public void testDelete() {

        DynamicMesgService userInfoService = new DynamicMesgService();
  
        userInfoService.delete(1);
	}
	
	@Test
	public void testUpdate() {
		DynamicMesg user_info = new DynamicMesg();  
        user_info.setId(2);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");  
        user_info.setDate_time(new Date());  

        DynamicMesgService userInfoService = new DynamicMesgService();
  
        userInfoService.update(user_info);
	}

}
