package xyz.tinyorb.hibernate.App;


import java.util.List;

import org.apache.catalina.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.user;

public class AuthenticUser {
	public String Auth(String str, String pass)
	{
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(user.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		String strReturn = "";
		
		try{
			session.beginTransaction();
			user uObj = (user)session.get(user.class, str );
			
			if( uObj != null)
			{
				if(uObj.getPass().equals(pass))
				strReturn = "successful";
				else
					strReturn = "no user";
			}
			else
			{
				strReturn = "no user";
			}
			session.beginTransaction().commit();
		}
		finally{
			factory.close();
		}
		return strReturn;
	}
	
	public String CheckEmail(String email){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(user.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		String username = null;
		List<user> userList = null;
		try{
			session.beginTransaction();
			Query query = session.createQuery("from user u where u.Email =" +"'"+ email+"'");
			userList = query.list();
			System.out.print(query);
			session.beginTransaction().commit();
		}
		finally{
			factory.close();
		}
		System.out.println(userList.size());
		if(userList.size() == 1)
		{
			for(user u : userList){
				username = u.getUsername();
			}
		}
		return username;
	}
}
