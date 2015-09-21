package org.backend.service;

import java.util.List;

import org.backend.domain.DynamicMesg;
import org.backend.domain.DynamicMesg;
import org.backend.uitl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DynamicMesgService {
	SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

	public List list(){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    List result = session.createQuery("from DynamicMesg").list();
	    session.getTransaction().commit();  
	    session.close();
	    
		return result;
	}
	public DynamicMesg get(int id){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    DynamicMesg dynamicMesg = (DynamicMesg) session.get(DynamicMesg.class, id);
	    session.getTransaction().commit();  
	    session.close();
	    
		return dynamicMesg;
	}
	public int insert(DynamicMesg dynamicMesg){
	    Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.save(dynamicMesg);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int delete(int id){
		Session session = sessionfactory.openSession();  
		DynamicMesg dynamicMesg = new DynamicMesg();
		dynamicMesg.setId(id);
		
	    session.beginTransaction();  
	    session.delete(dynamicMesg);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int update(DynamicMesg dynamicMesg){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.update(dynamicMesg);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
}
