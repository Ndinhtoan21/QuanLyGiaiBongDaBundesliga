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
			<h3 class="text-center">List of Teams</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/newteam" class="btn btn-success">Add
					New Team</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Team Name</th>
						<th>Team Founding Date</th>
						<th>Home Stadium</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="team" items="${listTeam}">

						<tr>
							<td><c:out value="${team.team_id}" /></td>
							<td><c:out value="${team.team_name}" /></td>
							<td><c:out value="${team.founding_date}" /></td>
							<td><c:out value="${team.home_stadium}" /></td>
							<td><a href="<%=request.getContextPath()%>/edit?team_id=${team.team_id}">Edit</a>
    						&nbsp;&nbsp;&nbsp;&nbsp; 
    						<a href="<%=request.getContextPath()%>/delete?team_id=${team.team_id}">Delete</a>
							</td>
						</tr>
					</c:forEach>

				</tbody>

			</table>
		</div>
	</div>
</body>
</html>