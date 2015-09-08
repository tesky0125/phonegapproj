package org.backend.service;

import java.util.List;

import org.backend.domain.UserInfo;
import org.backend.uitl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class UserInfoService {
	SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

	public List list(){
		Session session = sessionfactory.openSession();//getCurrentSession  

	    session.beginTransaction();  
	    List result = session.createQuery("from UserInfo").list();
	    session.getTransaction().commit();  
	    session.close();
	    
		return result;
	}
	public UserInfo get(int id){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    UserInfo userInfo = (UserInfo) session.get(UserInfo.class, id);
	    session.getTransaction().commit();  
	    session.close();
	    
		return userInfo;
	}
	public int insert(UserInfo user_info){
	    Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.save(user_info);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int delete(int id){
		Session session = sessionfactory.openSession();  
		UserInfo user_info = new UserInfo();
		user_info.setId(id);
		
	    session.beginTransaction();  
	    session.delete(user_info);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int update(UserInfo user_info){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.update(user_info);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
}
