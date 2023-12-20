<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Bundesliga</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a class="navbar-brand"> Bundesliga Match </a>
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

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${match != null}">
					<form action="updatematch" method="get">
				</c:if>
				<c:if test="${match == null}">
					<form action="insertmatch" method="get">
				</c:if>

				<caption>
					<h2>
						<c:if test="${match != null}">
            			Edit Match
            		</c:if>
						<c:if test="${match == null}">
            			Add New Match
            		</c:if>
					</h2>
				</caption>

				<c:if test="${match != null}">
					<input type="hidden" name="match_id" value="<c:out value='${match.match_id}' />" />
				</c:if>
				

				<fieldset class="form-group">
					<label>Home Team ID</label> <input type="text"
						value="<c:out value='${match.home_team_id}' />" class="form-control"
						name="home_team_id" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Away Team ID</label> <input type="text"
						value="<c:out value='${match.away_team_id}' />" class="form-control"
						name="away_team_id">
				</fieldset>

				<fieldset class="form-group">
					<label>Home Team Score</label> <input type="text"
						value="<c:out value='${match.home_team_score}' />" class="form-control"
						name="home_team_score">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Away Team Score</label> <input type="text"
						value="<c:out value='${match.away_team_score}' />" class="form-control"
						name="away_team_score">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Match Date</label> <input type="date"
						value="<c:out value='${match.match_date}' />" class="form-control"
						name="match_date">
				</fieldset>
				
				<button type="submit" class="btn btn-success" value ="submit">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>