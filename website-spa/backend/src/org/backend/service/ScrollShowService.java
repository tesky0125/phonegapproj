package org.backend.service;

import java.util.List;

import org.backend.domain.ScrollShow;
import org.backend.uitl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ScrollShowService {
	SessionFactory sessionfactory = HibernateUtil.getSessionFactory();

	public List list(){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    List result = session.createQuery("from ScrollShow").list();
	    session.getTransaction().commit();  
	    session.close();
	    
		return result;
	}
	public ScrollShow get(int id){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    ScrollShow scrollShow = (ScrollShow) session.get(ScrollShow.class, id);
	    session.getTransaction().commit();  
	    session.close();
	    
		return scrollShow;
	}
	public int insert(ScrollShow scrollShow){
	    Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.save(scrollShow);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int delete(int id){
		Session session = sessionfactory.openSession();  
		ScrollShow scrollShow = new ScrollShow();
		scrollShow.setId(id);
		
	    session.beginTransaction();  
	    session.delete(scrollShow);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
	public int update(ScrollShow scrollShow){
		Session session = sessionfactory.openSession();  

	    session.beginTransaction();  
	    session.update(scrollShow);  
	    session.getTransaction().commit();  
	    session.close();
	    
		return 0;
	}
}
