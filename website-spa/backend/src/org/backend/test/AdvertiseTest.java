package org.backend.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.backend.domain.Advertise;
import org.backend.service.AdvertiseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdvertiseTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
        AdvertiseService userInfoService = new AdvertiseService();
  
        List list = userInfoService.list();
        System.out.println(list);
	}
	
	@Test
	public void testGet() {
        AdvertiseService userInfoService = new AdvertiseService();
  
        Advertise userInfo = userInfoService.get(1);
        System.out.println(userInfo.getTitle());
	}
	
	@Test
	public void testInsert() {
		Advertise user_info = new Advertise();  
        user_info.setId(0);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");    
        user_info.setDate_time(new Date());  

        AdvertiseService userInfoService = new AdvertiseService();
  
        userInfoService.insert(user_info);
	}
	
	@Test
	public void testDelete() {

        AdvertiseService userInfoService = new AdvertiseService();
  
        userInfoService.delete(1);
	}
	
	@Test
	public void testUpdate() {
		Advertise user_info = new Advertise();  
        user_info.setId(1);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");  
        user_info.setDate_time(new Date());  

        AdvertiseService userInfoService = new AdvertiseService();
  
        userInfoService.update(user_info);
	}

}
