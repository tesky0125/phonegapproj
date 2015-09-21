package org.backend.service;

import java.util.List;

import org.backend.domain.EmployGuide;
import org.backend.uitl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class EmployGuideService {
	SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

	public List list(){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    List result = session.createQuery("from EmployGuide").list();
	    session.getTransaction().commit();  
	    session.close();
	    
		return result;
	}
	public EmployGuide get(int id){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    EmployGuide employGuide = (EmployGuide) session.get(EmployGuide.class, id);
	    session.getTransaction().commit();  
	    session.close();
	    
		return employGuide;
	}
	public int insert(EmployGuide employGuide){
	    Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.save(employGuide);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int delete(int id){
		Session session = sessionfactory.openSession();  
		EmployGuide employGuide = new EmployGuide();
		employGuide.setId(id);
		
	    session.beginTransaction();  
	    session.delete(employGuide);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int update(EmployGuide employGuide){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.update(employGuide);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
}
