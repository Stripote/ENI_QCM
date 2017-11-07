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
<title>ENI Ecole - Creation Qcm</title>
</head>
<body>
<%@ include file="/structure/logo.jspf" %>
<center><font color="#6495ED"><h1>Creation Qcm</h1></font></center>

<h3>Choisir un theme</h3>
<%

ArrayList<Theme> listeThemes = (ArrayList<Theme>)request.getAttribute("listeThemes");
	 	for(Theme T : listeThemes) {
	%>
	  <% int idTheme = T.getId();
	    
	  %>
		<div class="container">
		<div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Dropdown Example
    <span class="caret"></span></button>
    <ul class="dropdown-menu">
			<form action="/ENI_QCM/test/creationQcm?idTheme=<%= idTheme %>" method="POST" >
			<li><a href ="/ENI_QCM/test/creationQcm?idQcm=<%= idTheme %>"> <p><%=T.getNom()%></p></a></li>
			</form>
			</ul>
			</div>
			</div>
			<br />
	<% } %>
<h3>Choisir une ou des question(s)</h3>
<%

ArrayList<Question> listeQuestions = (ArrayList<Question>)request.getAttribute("listeQuestions");
	 	for(Question Q : listeQuestions) {
	%>
	  <% int idQuestion = Q.getId();
	    
	  %>
		<div class="container">
		<div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Dropdown Example
    <span class="caret"></span></button>
    <ul class="dropdown-menu">
			<li><form action="/ENI_QCM/test/creationQcm?idQuestion=<%= idQuestion %>" method="POST" >
			<a href ="/ENI_QCM/test/creationQcm?idQuestion=<%= idQuestion  %>"> <p><%=Q.getEnonce()%></p></a>
			</form></li>
			</ul>
			</div>
			</div>		
			<br />
	<% } %>
	
<%@ include file="/structure/menu.jspf" %>

</body>
</html>