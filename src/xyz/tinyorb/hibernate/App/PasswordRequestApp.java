package xyz.tinyorb.hibernate.App;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.PasswordRecoveryRequest;

public class PasswordRequestApp {

	public PasswordRecoveryRequest GetCode(String username){
		PasswordRecoveryRequest code = null;
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(PasswordRecoveryRequest.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		List<PasswordRecoveryRequest> code_list = null;
		try{
			session.beginTransaction();
			Query query = session.getNamedQuery("getPasswordRequestCode").setParameter("username", username);
			code_list = query.list();
			session.beginTransaction().commit();
		}
		finally{
			factory.close();
		}
		
		if(code_list != null)
		for(PasswordRecoveryRequest gc : code_list)
		{
			code = gc;
		}
		
		return code;
	}
	
	public String GetUser(String date, String Code){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(PasswordRecoveryRequest.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		List<String> username = null;
		try{
			session.beginTransaction();
			String q = "select PRR.user from PasswordRecoveryRequest PRR where PRR.RequestedDate = '" + date +"' and PRR.code = '"+Code+"'";
			username = session.createQuery(q).list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			factory.close();
		}
		return username.get(0);
	}
	
}
