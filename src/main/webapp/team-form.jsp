<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Bundesliga</title>
<link rel="stylesheet"  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
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

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${team != null}">
					<form action="update" method="get">
				</c:if>
				<c:if test="${team == null}">
					<form action="insert" method="get">
				</c:if>

				<caption>
					<h2>
						<c:if test="${team != null}">
            			Edit Team
            		</c:if>
						<c:if test="${team == null}">
            			Add New Team
            		</c:if>
					</h2>
				</caption>

				<c:if test="${team != null}">
					<input type="hidden" name="team_id" value="<c:out value='${team.team_id}' />" />
				</c:if>
				

				<fieldset class="form-group">
					<label>Team Name</label> <input type="text"
						value="<c:out value='${team.team_name}' />" class="form-control"
						name="team_name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Team Founding Date</label> <input type="text"
						value="<c:out value='${team.founding_date}' />" class="form-control"
						name="founding_date">
				</fieldset>

				<fieldset class="form-group">
					<label>Team Stadium</label> <input type="text"
						value="<c:out value='${team.home_stadium}' />" class="form-control"
						name="home_stadium">
				</fieldset>
				
				<button type="submit" class="btn btn-success" value ="submit">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>