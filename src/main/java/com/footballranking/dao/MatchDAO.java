package com.footballranking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.footballranking.model.Match;


public class MatchDAO {

		private String jdbcURL = "jdbc:mysql://localhost:3306/bundesligadb";
		private String jdbcUsername = "root";
		private String jdbcPassword = "121311";
		

			
		private static final String INSERT_MATCHS_SQL = "INSERT INTO matchs" + "( home_team_id, away_team_id, home_team_score, away_team_score, match_date) VALUES"+ "(?, ?, ?, ?, ?);" ;
		private static final String SELECT_MATCH_BY_ID = "select match_id, home_team_id, away_team_id, home_team_score, away_team_score, match_date from matchs where match_id=?";
		private static final String SELECT_ALL_MATCHS = "SELECT * FROM matchs";
		private static final String DELETE_MATCHS_SQL="delete from matchs where match_id = ?";
		private static final String UPDATE_MATCHS_SQL= "update matchs set  home_team_id= ?, away_team_id= ?, home_team_score = ?, away_team_score= ?, match_date= ? where match_id = ?";
		
		public MatchDAO () {
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
		
		// Create or insert match
				public void insertMatch(Match match) throws SQLException {
					System.out.println(INSERT_MATCHS_SQL);
					try(Connection connection =getConnection(); 
							PreparedStatement prepareStatement = connection.prepareStatement(INSERT_MATCHS_SQL)) {
						prepareStatement.setInt(1, match.getHome_team_id());
						prepareStatement.setInt(2, match.getAway_team_id());
						prepareStatement.setInt(3, match.getHome_team_score());
						prepareStatement.setInt(4, match.getAway_team_score());
						prepareStatement.setString(5, match.getMatch_date());
						System.out.println(prepareStatement);
						prepareStatement.executeUpdate();
					} catch (SQLException e) {
							printSQLException(e);
					}	
				}
				
				// Select team by id 
				public Match selectMatch(int match_id) {
				    Match match = null;
				    // step 1: Establishing a Connection
				    try (Connection connection = getConnection();
				         // Step 2: Create a statement using connection object
				         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MATCH_BY_ID)) {
				        preparedStatement.setInt(1, match_id);
				        System.out.print(preparedStatement);
				        // Step 3: Execute the query or update query
				        ResultSet rs = preparedStatement.executeQuery();
				            // Step 4: Process the ResultSet object
				                while (rs.next()) {
				                    int home_team_id = rs.getInt("home_team_id");
				                    int away_team_id = rs.getInt("away_team_id");
				                    int home_team_score = rs.getInt("home_team_score");
				                    int away_team_score = rs.getInt("away_team_score");
				                    String match_date = rs.getString("match_date");
				                   
				                    match = new Match(match_id, home_team_id, away_team_id, home_team_score, away_team_score, match_date);
				                }
				        } catch (SQLException e) {
				            printSQLException(e);
				        }
				    return match;
				    } 	
				
				
				// select team
				public List<Match> selectAllMatchs() {
				    List<Match> matchs = new ArrayList<>();
				    // step 1: Establishing a Connection
				    try (Connection connection = getConnection();
				         // Step 2: Create a statement using connection object
				         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MATCHS)) {
				        System.out.print(preparedStatement);
				        // Step 3: Execute the query or update query
				        ResultSet rs = preparedStatement.executeQuery();
				            // Step 4: Process the ResultSet object.
				                while (rs.next()) {
				                    int match_id = rs.getInt("match_id");
				                    int home_team_id = rs.getInt("home_team_id");
				                    int away_team_id = rs.getInt("away_team_id");
				                    int home_team_score = rs.getInt("home_team_score");
				                    int away_team_score = rs.getInt("away_team_score");
				                    String match_date = rs.getString("match_date");
				                    matchs.add(new Match(match_id, home_team_id, away_team_id, home_team_score, away_team_score, match_date));
				                }
				            } catch (SQLException e) {
				            				printSQLException(e);
				            }
				    			return matchs;
				        }
				
				// delete team
				public boolean deleteMatch(int match_id) throws SQLException {
					boolean rowDeleted;
					try (Connection connection = getConnection ();
							PreparedStatement statement =connection.prepareStatement(DELETE_MATCHS_SQL)) {
						statement.setInt(1, match_id);
						rowDeleted = statement.executeUpdate() > 0;
					}
					return rowDeleted;
				}
		
				
				// Update team
				public boolean updateMatch(Match match) throws SQLException {
					boolean rowUpdated;
					try (Connection connection = getConnection();
							PreparedStatement statement = connection.prepareStatement(UPDATE_MATCHS_SQL)) {
						statement.setInt(1, match.getHome_team_id());
						statement.setInt(2, match.getAway_team_id());
						statement.setInt(3, match.getHome_team_score());
						statement.setInt(4, match.getAway_team_score());
						statement.setString(5, match.getMatch_date());
						statement.setInt(6, match.getMatch_id());
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
