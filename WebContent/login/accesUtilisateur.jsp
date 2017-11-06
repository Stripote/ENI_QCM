<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/css/style.css">
  <script src="<%=request.getContextPath() %>/theme/bootstrap/js/bootstrap.min.js"></script>

<title>ENI Ecole - Acces Utilisateur</title>
</head>
<body>
<%@ include file="/structure/logo.jspf" %>
<h1><font color="#6495ED">Acces Utilisateur</font></h1> 
	<div class="container">
		<div class="row">
			<div class="col-xs-9">
				<form action="<%=request.getContextPath() %>/login/validerAcces" method="post">
				  	<div class="form-group">
				    	<label for="identifiant">Identifiant :</label>
				    	<input type="text" class="form-control" id="identifiant" name="identifiant">
				  	</div>
				  	<div class="form-group">
				    	<label for="motdepasse">Mot de passe :</label>
				    	<input type="password" class="form-control" id="motdepasse" name="motdepasse">
				  	</div>
				  	<button type="submit" class="btn btn-primary">se connecter</button>
				</form>
			</div>
			</div>
			</div>
		   <br>		   

<%@ include file="/structure/menu.jspf" %>

</body>
</html>