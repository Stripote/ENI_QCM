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

<% List<Question> list = (List<Question>)request.getSession().getAttribute("listeQuestion");%>
		
		<% int i =1;
	for(Question question :list){ %>	
<form action="<%=request.getContextPath() %>/test/gestionTest?question=<%= list.indexOf(question) %>" method="post">	
	<a href ="/test/gestionTest?question=<%= list.indexOf(question) %>"><p>question <%=i %></p></a>	
</form>	
<% } %>	

<form action="<%=request.getContextPath() %>/test/calculScore" method="post">	
    <button type="submit">Fin du test</button>
</form>	
		
		
		
	<%@ include file="/structure/menu.jspf"%>		

</body>
</html>