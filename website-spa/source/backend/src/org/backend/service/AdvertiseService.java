package org.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.backend.domain.Advertise;
import org.backend.uitl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AdvertiseService {
	SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

	public List list(){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    List result = session.createQuery("from Advertise").list();
	    session.getTransaction().commit();  
	    session.close();
	    
		return result;
	}
	public Advertise get(int id){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    Advertise advertise = (Advertise) session.get(Advertise.class, id);
	    session.getTransaction().commit();  
	    session.close();
	    
		return advertise;
	}
	public int insert(Advertise advertise){
	    Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.save(advertise);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int delete(int id){
		Session session = sessionfactory.openSession();  
		Advertise advertise = new Advertise();
		advertise.setId(id);
		
	    session.beginTransaction();  
	    session.delete(advertise);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int update(Advertise advertise){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.update(advertise);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
}
