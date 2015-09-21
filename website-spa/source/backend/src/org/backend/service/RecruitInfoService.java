package org.backend.service;

import java.util.List;

import org.backend.domain.RecruitInfo;
import org.backend.uitl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RecruitInfoService {
	SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

	public List list(){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    List result = session.createQuery("from RecruitInfo").list();
	    session.getTransaction().commit();  
	    session.close();
	    
		return result;
	}
	public RecruitInfo get(int id){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    RecruitInfo recruitInfo = (RecruitInfo) session.get(RecruitInfo.class, id);
	    session.getTransaction().commit();  
	    session.close();
	    
		return recruitInfo;
	}
	public int insert(RecruitInfo recruitInfo){
	    Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.save(recruitInfo);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int delete(int id){
		Session session = sessionfactory.openSession();  
		RecruitInfo recruitInfo = new RecruitInfo();
		recruitInfo.setId(id);
		
	    session.beginTransaction();  
	    session.delete(recruitInfo);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int update(RecruitInfo recruitInfo){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.update(recruitInfo);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
}
