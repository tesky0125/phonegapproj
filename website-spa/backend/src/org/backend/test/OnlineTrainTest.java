package org.backend.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.backend.domain.OnlineTrain;
import org.backend.service.OnlineTrainService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OnlineTrainTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
        OnlineTrainService userInfoService = new OnlineTrainService();
  
        List list = userInfoService.list();
        System.out.println(list);
	}
	
	@Test
	public void testGet() {
        OnlineTrainService userInfoService = new OnlineTrainService();
  
        OnlineTrain userInfo = userInfoService.get(1);
        System.out.println(userInfo.getTitle());
	}
	
	@Test
	public void testInsert() {
		OnlineTrain user_info = new OnlineTrain();  
        user_info.setId(0);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");    
        user_info.setDate_time(new Date());  

        OnlineTrainService userInfoService = new OnlineTrainService();
  
        userInfoService.insert(user_info);
	}
	
	@Test
	public void testDelete() {

        OnlineTrainService userInfoService = new OnlineTrainService();
  
        userInfoService.delete(1);
	}
	
	@Test
	public void testUpdate() {
		OnlineTrain user_info = new OnlineTrain();  
        user_info.setId(2);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");  
        user_info.setDate_time(new Date());  

        OnlineTrainService userInfoService = new OnlineTrainService();
  
        userInfoService.update(user_info);
	}

}
