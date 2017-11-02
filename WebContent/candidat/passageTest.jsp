<%@ page import ="fr.eni.jee.bo.*, java.util.*, java.text.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% Question question = (Question)request.getAttribute("question");%>
			
<form action=#>
	<p><%=question.getEnonce().toString()%></p>
	
	<%for(Reponse reponse :question.getReponses()){ %>
    <input type="checkbox" class="reponse" name="reponse" value="<%=reponse%>">
    <label for="reponse"><%=reponse.getLibelle() %></label>
	<%} %>
    <button type="submit">Question suivante</button>


</form>			
			
<%@ include file="/structure/menu.jspf" %>
</body>
</html>