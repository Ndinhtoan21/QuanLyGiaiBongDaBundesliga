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

import com.footballranking.dao.MatchDAO;
import com.footballranking.model.Match;



/**
 * Servlet implementation class MatchServlet
 */
@WebServlet("/1")
public class MatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatchDAO matchDAO;
	
	public void init() {
				matchDAO = new MatchDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		doGet(request, response);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action1 = request.getServletPath();
		try {
			switch (action1) {
			case "/1newmatch":
				showNewFormMatch(request, response);
				break;
			case "/1insertmatch":
				insertMatch(request, response);
				break;
			case "/1deletematch":
				deleteMatch(request, response);
				break;
			case "/1editmatch":
				showEditMatch(request, response);
				break;
			case "/1updatematch":
				updateMatch(request, response);
				break;
			default :
				listMatch(request, response);
				break;
			} 
		}catch (SQLException ex) {
			throw new ServletException(ex);
			}
		}
	

private void listMatch(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		List<Match> listMatch = matchDAO.selectAllMatchs();
		request.setAttribute("listMatch", listMatch);
		RequestDispatcher dispatcher = request.getRequestDispatcher("match-list.jsp");
		dispatcher.forward(request, response);
}

private void showNewFormMatch(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	   RequestDispatcher dispatcher = request.getRequestDispatcher("match-form.jsp");
	   dispatcher.forward(request, response);
	}

private void showEditMatch(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		int match_id = Integer.parseInt(request.getParameter("match_id"));
		Match existingMatch = matchDAO.selectMatch(match_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("match-form.jsp");
		request.setAttribute("match", existingMatch);
		dispatcher.forward(request, response);
}


private void insertMatch(HttpServletRequest request, HttpServletResponse response) 
		throws SQLException, IOException, ServletException {
    int home_team_id = Integer.parseInt(request.getParameter("home_team_id"));
    int away_team_id = Integer.parseInt(request.getParameter("away_team_id"));
    int home_team_score = Integer.parseInt(request.getParameter("home_team_score"));
    int away_team_score = Integer.parseInt(request.getParameter("away_team_score"));
    String match_date = request.getParameter("match_date");
    Match newMatch = new Match(home_team_id, away_team_id, home_team_score, away_team_score, match_date);
    matchDAO.insertMatch(newMatch);
    response.sendRedirect("matchlist");
}



private void updateMatch(HttpServletRequest request, HttpServletResponse response)
	      throws SQLException, IOException {
		int match_id = Integer.parseInt(request.getParameter("match_id"));
		int home_team_id = Integer.parseInt(request.getParameter("home_team_id"));
	    int away_team_id = Integer.parseInt(request.getParameter("away_team_id"));
	    int home_team_score = Integer.parseInt(request.getParameter("home_team_score"));
	    int away_team_score = Integer.parseInt(request.getParameter("away_team_score"));
	    String match_date = request.getParameter("match_date");

	   Match updatedMatch = new Match(match_id, home_team_id, away_team_id, home_team_score, away_team_score, match_date);
	   matchDAO.updateMatch(updatedMatch);	    
	   response.sendRedirect("matchlist");
	}




private void deleteMatch(HttpServletRequest request, HttpServletResponse response)
	      throws  IOException, SQLException {
	         int match_id = Integer.parseInt(request.getParameter("match_id"));
	         matchDAO.deleteMatch(match_id);
	         response.sendRedirect("matchlist");
	}

}
