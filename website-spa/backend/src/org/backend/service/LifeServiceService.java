package org.backend.service;

import java.util.List;

import org.backend.domain.LifeService;
import org.backend.uitl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LifeServiceService {
	SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

	public List list(){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    List result = session.createQuery("from LifeService").list();
	    session.getTransaction().commit();  
	    session.close();
	    
		return result;
	}
	public LifeService get(int id){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    LifeService lifeService = (LifeService) session.get(LifeService.class, id);
	    session.getTransaction().commit();  
	    session.close();
	    
		return lifeService;
	}
	public int insert(LifeService lifeService){
	    Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.save(lifeService);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int delete(int id){
		Session session = sessionfactory.openSession();  
		LifeService lifeService = new LifeService();
		lifeService.setId(id);
		
	    session.beginTransaction();  
	    session.delete(lifeService);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int update(LifeService lifeService){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.update(lifeService);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
}
