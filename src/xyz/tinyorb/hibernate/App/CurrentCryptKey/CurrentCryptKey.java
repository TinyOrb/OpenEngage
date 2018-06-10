package xyz.tinyorb.hibernate.App.CurrentCryptKey;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.RCode;

public class CurrentCryptKey {
	public List<String> GetKey(){
		List<String> key = null;
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(RCode.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try{
			session.beginTransaction();
			key = session.createQuery("select RC.cryptKey from RCode RC").list();
			session.beginTransaction().commit();
		}
		finally{
			factory.close();
		}
		
		return key;
	}
}
