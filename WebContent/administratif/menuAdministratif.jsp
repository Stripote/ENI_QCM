<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/css/style.css">
  <script src="<%=request.getContextPath() %>/theme/bootstrap/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath() %>/theme/js/style.js"></script>
  <script src="<%=request.getContextPath()%>/theme/js/jquery-3.2.1.min.js"></script>
<title>ENI Ecole - Menu Administratif</title>
</head>
<body>
<%@ include file="/structure/logo.jspf" %>
<center><font color="#6495ED"><h1>Menu Administratif</h1></font></center>

<h1>Menu Administratif</h1>

<ul>
<li><a href="<%=request.getContextPath() %>/administratif/accesCreationInscription">Inscription</a></li>
<li><a href="<%=request.getContextPath() %>/administratif/accesCreationUtilisateur">Creation des Utilisateurs</a></li>
</ul>
</body>
<%@ include file="/structure/menu.jspf" %>
</html>