<%@ page import ="fr.eni.jee.bo.*, java.util.*, java.text.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/css/style.css">
  <script src="<%=request.getContextPath() %>/theme/bootstrap/js/bootstrap.min.js"></script>
<title>ENI Ecole - Synthèse Test</title>
</head>
<body>
<%@ include file="/structure/logo.jspf" %>
<center><font color="#6495ED"><h1>Synthèse Test</h1></font></center>
<div class ="container">
<% List<Question> list = (List<Question>)request.getSession().getAttribute("listeQuestion");%>
	
		<% int i =1;
	for(Question question :list){ %>	
<center><font color="#6495ED"><form action="<%=request.getContextPath() %>/test/gestionSynthese?retour=<%= list.indexOf(question) %>" method="post">	
	<a href ="<%=request.getContextPath() %>/test/gestionSynthese?retour=<%= list.indexOf(question) %>"><p>question <%=i %></p></a>	
</form></font></center>
<%i++;} %>	


<form action="<%=request.getContextPath() %>/candidat/menuCandidat.jsp" method="post">	
    <center><font color="#6495ED"><button type="submit" class="btn btn-primary">Fin du test</button>
</form></font></center>	
	
</div>		
		
	<%@ include file="/structure/menu.jspf"%>		

</body>
</html>