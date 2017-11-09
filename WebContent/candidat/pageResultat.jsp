<%@ page import ="fr.eni.jee.bo.*, java.util.*, java.text.*" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/css/style.css">
  <script src="<%=request.getContextPath() %>/theme/js/jquery-3.2.1.min.js"></script>
  <script src="<%=request.getContextPath() %>/theme/js/style.js"></script>
  <script src="<%=request.getContextPath() %>/theme/bootstrap/js/bootstrap.min.js"></script>
<title>ENI Ecole - Resultat du test</title>
</head>
<body>

<%@ include file="/structure/logo.jspf" %>

<center><font color="#6495ED"><h1>Resultats</h1></font></center>

<div class ="container">
<% Integer pourcentage = (Integer)request.getSession().getAttribute("pourcentage");
String resultat = (String)request.getSession().getAttribute("resultat");%>
		
<center><font color="#6495ED">
<ul>
<li><h3>Pourcentage de reussite : <%=pourcentage %> % </h3></li>
<li><h3>Evaluation : <%=resultat %></h3></li>
</ul>
</font></center>	

<form action="<%=request.getContextPath() %>/candidat/menuCandidat.jsp" method="post">	
    <center><font color="#6495ED"><button type="submit" class="btn btn-primary">Retour au menu</button>
</form></font></center>	

	
</div>	
	
	
<%@ include file="/structure/menu.jspf" %>

</body>
</html>