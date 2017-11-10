<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="fr.eni.jee.bo.*, java.util.*, java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/css/style.css">
  <script src="<%=request.getContextPath() %>/theme/bootstrap/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath() %>/theme/js/style.js"></script>
  <script src="<%=request.getContextPath()%>/theme/js/jquery-3.2.1.min.js"></script>
<title>ENI Ecole - Menu Candidat</title>
</head>
<body>
<%@ include file="/structure/logo.jspf" %>
<center><font color="#6495ED"><h1>Creation Utilisateur</h1></font></center>

<form action="<%=request.getContextPath() %>/creationUtilisateur"
		method="POST">
		<div class="container">
<div class="form-group">
				<label for="nom"><h3>Nom</h3></label><input name="nom" type="nom"
					class="form-control" id="nom">
			</div>
			
			<div class="form-group">
				<label for="prenom"><h3>Prenom</h3></label><input name="prenom" type="prenom"
					class="form-control" id="prenom">
			</div>
			
			<div class="form-group">
				<label for="login"><h3>Login</h3></label><input name="login" type="login"
					class="form-control" id="login">
			</div>
			
			<div class="form-group">
				<label for="password"><h3>Password</h3></label><input name="password" type="password"
					class="form-control" id="password">
			</div>
			
			<h3>Role Utilisateur</h3>
					<select name="role" class="form-control" name="role">
					<%for (int i = 1; i <= 3; ++i) {%>
					<%if (i == 1){ %>
						<option value="<%=i%>">Candidat</option>
						<%}%>
						<%if (i == 2){ %>
						<option value="<%=i%>">Administrateur</option>
						<%}%>
						<%if (i == 3){ %>
						<option value="<%=i%>">Gestionnaire</option>
						<%}%>
					<%}%>
					<br>
					</select>
			
			
			<br>
			<button type="submit" class="btn btn-primary" value="Utilisateur">Envoyer</button>
			
			</div>
			</form>
</body>