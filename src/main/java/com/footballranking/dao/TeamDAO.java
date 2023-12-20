package com.footballranking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.footballranking.model.TeamInfo;


public class TeamDAO {
		private String jdbcURL = "jdbc:mysql://localhost:3306/bundesligadb";
		private String jdbcUsername = "root";
		private String jdbcPassword = "121311";
		

			
		private static final String INSERT_TEAMS_SQL = "INSERT INTO team_info" + "( team_name, founding_date, home_stadium) VALUES"+ "(?, ?, ?);" ;
		private static final String SELECT_TEAM_BY_ID = "select team_id, team_name, founding_date, home_stadium from team_info where team_id=?";
		private static final String SELECT_ALL_TEAMS = "SELECT * FROM team_info";
		private static final String DELETE_TEAMS_SQL="delete from team_info where team_id = ?";
		private static final String UPDATE_TEAMS_SQL= "update team_info set team_name = ?, founding_date = ?, home_stadium=? where team_id = ?";
		
		public TeamDAO () {
		}
		
		protected Connection getConnection() {
			Connection connection = null;
			try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
			}
		
		// Create or insert team
		public void insertTeam(TeamInfo team) throws SQLException {
			System.out.println(INSERT_TEAMS_SQL);
			try(Connection connection =getConnection(); 
					PreparedStatement prepareStatement = connection.prepareStatement(INSERT_TEAMS_SQL)) {
				prepareStatement.setString(1, team.getTeam_name());
				prepareStatement.setString(2, team.getFounding_date());
				prepareStatement.setString(3, team.getHome_stadium());
				System.out.println(prepareStatement);
				prepareStatement.executeUpdate();
			} catch (SQLException e) {
					printSQLException(e);
			}	
		}

		// Select team by id 
		public TeamInfo selectTeam(int team_id) {
		    TeamInfo team = null;
		    // step 1: Establishing a Connection
		    try (Connection connection = getConnection();
		         // Step 2: Create a statement using connection object
		         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEAM_BY_ID)) {
		        preparedStatement.setInt(1, team_id);
		        System.out.print(preparedStatement);
		        // Step 3: Execute the query or update query
		        ResultSet rs = preparedStatement.executeQuery();
		            // Step 4: Process the ResultSet object
		                while (rs.next()) {
		                    String team_name = rs.getString("team_name");
		                    String founding_date = rs.getString("founding_date");
		                    String home_stadium = rs.getString("home_stadium");
		                    team = new TeamInfo(team_id, team_name, founding_date, home_stadium);
		                }
		        } catch (SQLException e) {
		            printSQLException(e);
		        }
		    return team;
		    } 
		    


		// select team
		public List<TeamInfo> selectAllTeams() {
		    List<TeamInfo> teams = new ArrayList<>();
		    // step 1: Establishing a Connection
		    try (Connection connection = getConnection();
		         // Step 2: Create a statement using connection object
		         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TEAMS)) {
		        System.out.print(preparedStatement);
		        // Step 3: Execute the query or update query
		        ResultSet rs = preparedStatement.executeQuery();
		            // Step 4: Process the ResultSet object.
		                while (rs.next()) {
		                    int team_id = rs.getInt("team_id");
		                    String team_name = rs.getString("team_name");
		                    String founding_date = rs.getString("founding_date");
		                    String home_stadium = rs.getString("home_stadium");
		                    teams.add(new TeamInfo(team_id, team_name, founding_date, home_stadium));
		                }
		            } catch (SQLException e) {
		            				printSQLException(e);
		            }
		    			return teams;
		        } 
		
		// delete team
		public boolean deleteTeam(int team_id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection ();
					PreparedStatement statement =connection.prepareStatement(DELETE_TEAMS_SQL)) {
				statement.setInt(1, team_id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}
		
		// Update team
		public boolean updateTeam(TeamInfo team) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_TEAMS_SQL)) {
				statement.setString(1, team.getTeam_name());
				statement.setString(2, team.getFounding_date());
				statement.setString(3, team.getHome_stadium());
				statement.setInt(4, team.getTeam_id());
				
				rowUpdated = statement.executeUpdate() > 0;				
			}
			return rowUpdated;
	}
		
		
		private void printSQLException(SQLException ex) {
			// TODO Auto-generated method stub
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
}







