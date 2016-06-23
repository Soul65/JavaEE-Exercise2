
public class Team
{
	private int teamID;
	private League league;
	private String sponsor;
	private String teamName;
	private Staff headCoach;
	private Staff asstCoach;
	private Staff manager;
	private String website;
	
	public Team()
	{
	}
	
	
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public League getLeague() {
		return league;
	}
	public void setLeague(League league) {
		this.league = league;
	}
	private String getSponsor() {
		return sponsor;
	}
	private void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Staff getHeadCoach() {
		return headCoach;
	}
	public void setHeadCoach(Staff headCoach) {
		this.headCoach = headCoach;
	}
	public Staff getAsstCoach() {
		return asstCoach;
	}
	public void setAsstCoach(Staff asstCoach) {
		this.asstCoach = asstCoach;
	}
	public Staff getManager() {
		return manager;
	}
	public void setManager(Staff manager) {
		this.manager = manager;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
}
