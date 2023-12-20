<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Bundesliga</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a class="navbar-brand"> Bundesliga </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/teamlist"
					class="nav-link">Team</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/1matchlist"
					class="nav-link">Match</a></li>
			</ul>

		</nav>
	</header>
	<br>

	<div class="row">

		<div class="container">
			<h3 class="text-center">List of Match</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/1newform" class="btn btn-success">Add
					New Match</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Home Team ID</th>
						<th>Away Team ID</th>
						<th>Home Score</th>
						<th>Away Score</th>
						<th>Match Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="match" items="${listMatch}">
						<tr>
							<td><c:out value="${match.match_id}" /></td>
							<td><c:out value="${match.home_team_id}" /></td>
							<td><c:out value="${match.away_team_id}" /></td>
							<td><c:out value="${match.home_team_score}" /></td>
							<td><c:out value="${match.away_team_score}" /></td>
							<td><c:out value="${match.match_date}" /></td>
							<td><a href="<%=request.getContextPath()%>/1editmatch?match_id=${match.match_id}">Edit</a>
    						<a href="<%=request.getContextPath()%>/1deletematch?match_id=${match.match_id}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>