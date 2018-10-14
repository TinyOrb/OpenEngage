package xyz.tinyorb.hibernate.App.Publisher;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.Publish;

public class GetPublish {
	
	public List<Publish> getTenRecentPublicPublishList() {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Publish.class).buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		List<Publish> ls = null;
		try{
			session.beginTransaction();
			
			ls = session.createQuery("from Publish ORDER BY Date DESC").setMaxResults(10).list();
			session.beginTransaction().commit();
		}
		finally{
			factory.close();
		}
		return ls;
	}

}
