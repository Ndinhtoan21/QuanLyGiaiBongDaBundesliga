package com.footballranking.model;


public class TeamInfo {
	private int team_id;
	private String team_name;
	private String founding_date;
	private String home_stadium;
	
	public TeamInfo() {
		super();
	}
	
	public TeamInfo(int team_id, String team_name, String founding_date, String home_stadium) {
		super();
		this.team_id = team_id;
		this.team_name = team_name;
		this.founding_date = founding_date;
		this.home_stadium = home_stadium;
	}
	
	
	public TeamInfo( String team_name, String founding_date, String home_stadium) {
		super();
		this.team_name = team_name;
		this.founding_date = founding_date;
		this.home_stadium = home_stadium;
	}


	

	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getFounding_date() {
		return founding_date;
	}
	public void setFounding_date(String founding_date) {
		this.founding_date = founding_date;
	}
	public String getHome_stadium() {
		return home_stadium;
	}
	public void setHome_stadium(String home_stadium) {
		this.home_stadium = home_stadium;
	}


}
