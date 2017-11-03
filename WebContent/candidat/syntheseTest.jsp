<%@ page import ="fr.eni.jee.bo.*, java.util.*, java.text.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ENI Ecole - Synthèse Test</title>
</head>
<body>

<% List<Question> list = (List<Question>)request.getSession().getAttribute("listeQuestion");%>
			
<form action="<%=request.getContextPath() %>/test/gestionTest" method="post">	
	<% int i =1;
	for(Question question :list){ %>
    <input type="checkbox" class="reponse" name="reponse" value="<%=question.getId()%>">
    <label for="reponse">question <%=i %></label>
	<%i++;} %>
    <button type="submit">Question suivante</button>


</form>			
			
<%@ include file="/structure/menu.jspf" %>

</body>
</html>