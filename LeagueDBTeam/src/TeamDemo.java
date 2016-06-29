import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;

public class TeamDemo
{
	public static void main(String[] args)
	{
		Session session = null;
		SessionFactory sessionFactory = null;
		ServiceRegistry serviceRegistry = null;

	    try 
	    {
		    Configuration configuration = new Configuration().addResource("hibernate.cfg.xml");
		    		
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    
		    session = sessionFactory.openSession();
		    
		    List<Team> teams = session.createQuery("from Team where teamName = ?").setString(0, "Chicago Blackhawks").list();

		    for(Team currentTeam : teams)
		    {
			    System.out.println(currentTeam.getTeamName());
			    System.out.println(currentTeam.getHeadCoach().getFullName());
			    System.out.println(currentTeam.getAsstCoach().getFullName());
			    System.out.println(currentTeam.getManager().getFullName());
			    System.out.println(currentTeam.getTrainer().getFullName());
			    System.out.println(currentTeam.getSponsor());
			    System.out.println(currentTeam.getLeague().getLeagueName());
		    }
		       
		    //Insert a new fictitious team
		    Team newTeam = new Team();
		      
		    League newLeague = new League();
		    newLeague.setLeagueID("NHL");
		       
		    newTeam.setTeamID("WIN123");
		    newTeam.setTeamName("NewTeam");
		    newTeam.setLeague(newLeague);

		       
		    Staff newStaff = new Staff();
		    newStaff.setFirstName("John");
		    newStaff.setLastName("Smith");
		    newTeam.setHeadCoach(newStaff);
		    newTeam.setAsstCoach(newStaff);
		    newTeam.setManager(newStaff);
		    newTeam.setTrainer(newStaff);
		       
		    session.save(newStaff);
		       
		    session.save(newTeam);		       
		    
		    session.beginTransaction().commit();
		       
		    //Pull data that was just committed
	        List<Team> addedTeam = session.createQuery("from Team where teamID = ?").setString(0, newTeam.getTeamID()).list();
		       
	        for(Team currentTeam : addedTeam)
	        {
	    	    System.out.println(currentTeam.getTeamName());
	    	    System.out.println(currentTeam.getHeadCoach().getFullName());
	    	    System.out.println(currentTeam.getAsstCoach().getFullName());
	     	    System.out.println(currentTeam.getManager().getFullName());
	            System.out.println(currentTeam.getTrainer().getFullName());
	    	    System.out.println(currentTeam.getSponsor());
	    	    System.out.println(currentTeam.getLeague().getLeagueName());		    	   
	        }
		       
		    //Delete record just added to database
//	        query = "DELETE from Team t where t.teamID = ?";
//	        session.createQuery(query).setString(0, newTeam.getTeamID()).executeUpdate();	       
//	        session.delete(newTeam);	        	       
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	    finally
	    {
		    sessionFactory.close();
	    }
	}

}
