package xyz.tinyorb.hibernate.App.Publisher;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import xyz.tinyorb.hibernate.entity.Article;
import xyz.tinyorb.hibernate.entity.Publish;

public class Post_Publish {
	public Integer publish(String username, String Content, Date date, String heading, String category, Integer pID, Integer aID )
	{
		Integer strReturn = null;
		
				// create session factory of Publish post table
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Publish.class)
										.addAnnotatedClass(Article.class)
										.buildSessionFactory();
				
		// create session of Publish post table
		Session session = factory.getCurrentSession();
		
		Publish PP = new Publish();
		PP.setHeading(heading);
		PP.setCategory(category);
		PP.setDate(date);
		PP.setPAuthor(username);
		if(pID == -1)
		{
				try{
					// opening both transaction
					session.beginTransaction();
					
					//Getting Article object
					Article article = session.get(Article.class, aID);
					
					//Saving and getting publish ID
					session.save(PP);
					strReturn = PP.getIdPublish_Post();
					
					// Mapping pid to article object and updating object
					article.setPID(strReturn);
					article.setHeading(heading);
					article.setPDate(date);
					article.setsData(Content);
					session.update(article);
					
					// Closing transaction
					session.beginTransaction().commit();
					
			}
			finally{
				factory.close();
			}
			
		}
		else
		{
			
			try{
				// opening both transaction
				session.beginTransaction();
				
				//Getting Article object and Post Object
				Article article = session.get(Article.class, aID);
				PP = session.get(Publish.class, pID);
				
				// confirming authorization
				if(PP.getPAuthor().equals(username) && article.getAuthor().equals(username))
				{
					// update publish meta information in database
					PP.setHeading(heading);
					PP.setCategory(category);
					PP.setDate(date);
					
					session.save(PP);
					strReturn = PP.getIdPublish_Post();
					
					// re-updating article table with publish id
					article.setPID(strReturn);
					article.setHeading(heading);
					article.setPDate(date);
					article.setsData(Content);
					session.update(article);
					
					// Closing transaction
					session.beginTransaction().commit();
				}
				
			}
			finally{
				factory.close();
			}
		}
		
		return strReturn;
	}
}
