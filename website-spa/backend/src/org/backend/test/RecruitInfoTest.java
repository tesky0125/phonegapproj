package org.backend.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.backend.domain.RecruitInfo;
import org.backend.service.RecruitInfoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RecruitInfoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
        RecruitInfoService userInfoService = new RecruitInfoService();
  
        List list = userInfoService.list();
        System.out.println(list);
	}
	
	@Test
	public void testGet() {
        RecruitInfoService userInfoService = new RecruitInfoService();
  
        RecruitInfo userInfo = userInfoService.get(1);
        System.out.println(userInfo.getTitle());
	}
	
	@Test
	public void testInsert() {
		RecruitInfo user_info = new RecruitInfo();  
        user_info.setId(0);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");    
        user_info.setDate_time(new Date());  

        RecruitInfoService userInfoService = new RecruitInfoService();
  
        userInfoService.insert(user_info);
	}
	
	@Test
	public void testDelete() {

        RecruitInfoService userInfoService = new RecruitInfoService();
  
        userInfoService.delete(1);
	}
	
	@Test
	public void testUpdate() {
		RecruitInfo user_info = new RecruitInfo();  
        user_info.setId(2);  
        user_info.setTitle("yang");  
        user_info.setContent("yang22");  
        user_info.setDate_time(new Date());  

        RecruitInfoService userInfoService = new RecruitInfoService();
  
        userInfoService.update(user_info);
	}

}
