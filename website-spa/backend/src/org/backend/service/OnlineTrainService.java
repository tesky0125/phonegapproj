package org.backend.service;

import java.util.List;

import org.backend.domain.OnlineTrain;
import org.backend.uitl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class OnlineTrainService {
	SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

	public List list(){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    List result = session.createQuery("from OnlineTrain").list();
	    session.getTransaction().commit();  
	    session.close();
	    
		return result;
	}
	public OnlineTrain get(int id){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    OnlineTrain onlineTrain = (OnlineTrain) session.get(OnlineTrain.class, id);
	    session.getTransaction().commit();  
	    session.close();
	    
		return onlineTrain;
	}
	public int insert(OnlineTrain onlineTrain){
	    Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.save(onlineTrain);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int delete(int id){
		Session session = sessionfactory.openSession();  
		OnlineTrain onlineTrain = new OnlineTrain();
		onlineTrain.setId(id);
		
	    session.beginTransaction();  
	    session.delete(onlineTrain);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int update(OnlineTrain onlineTrain){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.update(onlineTrain);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
}
