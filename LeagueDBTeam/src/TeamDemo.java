import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.*;
//import org.hibernate.SessionFactory;
import org.hibernate.cfg.*;
import org.hibernate.service.*;
//import org.hibernate.service.ServiceRegistryBuilder;

import IO.Team;

public class TeamDemo
{

	public static void main(String[] args) //throws Exception
	{
		// TODO Auto-generated method stub
		Session session = null;
		SessionFactory sessionFactory = null;
		ServiceRegistry serviceRegistry = null;

		    try {

		     /* We create a SessionFactory based on the hibernate.cfg.xml
		        file; then from a SessionFactory, we can create an individual
		        Session object. The Configuration object changed significantly with
		        Hibernate Version 4; examples from older sources will not work with 
		        Version 4 libraries. */

		    	Configuration configuration = new Configuration()
		    		.addResource("hibernate.cfg.xml")
		    		.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
		    	configuration.configure();
		    	serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    	
		    	//System.out.println(Team.class.getSimpleName());
		       session = sessionFactory.openSession();
		      
		       String query = "from Team t where t.teamName = ? and t.league = 'NHL'";
		       List<Team> teams = session.createQuery(query).setString(0, "Chicago Blackhawks").list();
		       //System.out.println(Team.class.getSimpleName());
		       for(Team currentTeam: teams)
		       {
		    	   System.out.println(currentTeam.getTeamName());
		    	   System.out.println(currentTeam.getHeadCoach().getFullName());
		       }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
