package com.footballranking.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.footballranking.dao.TeamDAO;
import com.footballranking.model.TeamInfo;
 
/**
 * Servlet implementation class TeamServlet
 */
@WebServlet("/")
public class TeamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamDAO teamDAO;
	
	public void init() {
				teamDAO = new TeamDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getServletPath();
		
	try {
		switch (action) {
		case "/newteam":
			showNewForm(request, response);
			break;
		case "/insert":
			insertTeam(request, response);
			break;
		case "/delete":
			deleteTeam(request, response);
			break;
		case "/edit":
			showEditTeam(request, response);
			break;
		case "/update":
			updateTeam(request, response);
			break;
		default :
			listTeam(request, response);
			break;
		} 
	}catch (SQLException ex) {
		throw new ServletException(ex);
		}
	}
	
	private void listTeam(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
			List<TeamInfo> listTeam = teamDAO.selectAllTeams();
			request.setAttribute("listTeam", listTeam);
			RequestDispatcher dispatcher = request.getRequestDispatcher("team-list.jsp");
			dispatcher.forward(request, response );
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		   RequestDispatcher dispatcher = request.getRequestDispatcher("team-form.jsp");
		   dispatcher.forward(request, response);
		}
	
	private void showEditTeam(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
			int team_id = Integer.parseInt(request.getParameter("team_id"));
			TeamInfo existingTeam = teamDAO.selectTeam(team_id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("team-form.jsp");
			request.setAttribute("team", existingTeam);
			dispatcher.forward(request, response);
	}
	
	
	private void insertTeam(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
	    String team_name = request.getParameter("team_name");
	    String founding_date = request.getParameter("founding_date");
	    String home_stadium = request.getParameter("home_stadium");
	    TeamInfo newTeam = new TeamInfo(team_name, founding_date, home_stadium);
	    teamDAO.insertTeam(newTeam);
	    response.sendRedirect("teamlist");
	}
	
	
	private void updateTeam(HttpServletRequest request, HttpServletResponse response)
		      throws SQLException, IOException {
		   int team_id = Integer.parseInt(request.getParameter("team_id"));
		   String team_name = request.getParameter("team_name");
		   String founding_date = request.getParameter("founding_date");
		   String home_stadium = request.getParameter("home_stadium");

		   TeamInfo updatedTeam = new TeamInfo(team_id, team_name, founding_date, home_stadium);
		   teamDAO.updateTeam(updatedTeam);
		   response.sendRedirect("teamlist");
		}

	

	
	private void deleteTeam(HttpServletRequest request, HttpServletResponse response)
		      throws  IOException, SQLException {
		         int team_id = Integer.parseInt(request.getParameter("team_id"));
		         teamDAO.deleteTeam(team_id);
		         response.sendRedirect("teamlist");
		}

}
