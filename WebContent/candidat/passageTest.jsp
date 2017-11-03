<%@ page import ="fr.eni.jee.bo.*, java.util.*, java.text.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ENI Ecole - Test</title>
</head>
<body>

<% Qcm test = (Qcm)request.getSession().getAttribute("qcm");%>
<% Question question = (Question)request.getSession().getAttribute("question");%>

<h1></h1>

<p><%=question.getEnonce().toString()%></p>	
			
<form action="<%=request.getContextPath() %>/test/gestionTest" method="post">	
	<%for(Reponse reponse :question.getReponses()){ %>
    <input type="checkbox" class="reponse" name="reponse" value="<%=reponse.getLibelle()%>">
    <label for="reponse"><%=reponse.getLibelle() %></label>
	<%} %>
    <button type="submit">Question suivante</button>


</form>			
			
<%@ include file="/structure/menu.jspf" %>
</body>
</html>