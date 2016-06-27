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
import IO.Staff;
import IO.League;

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
		    		.addResource("hibernate.cfg.xml");
		    		
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
		    	   System.out.println(currentTeam.getAsstCoach().getFullName());
		    	   System.out.println(currentTeam.getManager().getFullName());
		    	   System.out.println(currentTeam.getTrainer().getFullName());
		    	   System.out.println(currentTeam.getSponsor());
		    	   System.out.println(currentTeam.getLeague().getLeagueName());
		       }
		       
		       //Insert new ficticious team
		       Team newTeam = new Team();
		       
		       League newLeague = new League();
		       newLeague.setLeagueID("NHL");
		       
		       newTeam.setTeamID("ALD506");
		       newTeam.setTeamName("newbies");
		       newTeam.setLeague(newLeague);

		       
		       Staff newHc = new Staff();
		       newHc.setFirstName("Newbie");
		       newHc.setLastName("lastname");
		       newTeam.setHeadCoach(newHc);
		       
		       Staff newAc = new Staff();
		       newAc.setFirstName("Bob");
		       newAc.setLastName("Loblaw");
		       newTeam.setAsstCoach(newAc);
		       
		       Staff newManager = new Staff();
		       newManager.setFirstName("Man");
		       newManager.setLastName("Age");
		       newTeam.setManager(newManager);
		       
		       Staff newTrainer = new Staff();
		       newTrainer.setFirstName("Thomas");
		       newTrainer.setLastName("Tank Engine");
		       newTeam.setTrainer(newTrainer);
		       
		       Transaction tx = session.beginTransaction();
		       
		       session.save(newHc);
		       session.save(newAc);
		       session.save(newManager);
		       session.save(newTrainer);
		       
		       session.save(newTeam);		       
		       
		       session.getTransaction().rollback();
		       
		       session.close();
		       
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
