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
<title>ENI Ecole - Test</title>
</head>
<body>
<%@ include file="/structure/logo.jspf" %>
<% Qcm test = (Qcm)request.getSession().getAttribute("qcm");%>
<% Question question = (Question)request.getSession().getAttribute("question");%>
<% 
	int index = 1;
	if(request.getSession().getAttribute("indexQuestion") != null)
		index = (Integer)request.getSession().getAttribute("indexQuestion");
%>
<center><font color="#6495ED"><h1>Question N°<%=index%></h1></font></center>
<div class="container">
<center><font color="#6495ED"><h3><%=question.getEnonce().toString()%></h3></font></center>		
	<center>
		<form action="<%=request.getContextPath() %>/test/gestionTest" method="post">	
			<table class="tableQuestion">
				<%
				int k = 0;
				for(Reponse reponse :question.getReponses()){ 
					k++; 
				%>
			    <tr><td>
			    <input hidden type="checkbox" class="reponse" id="reponse<%=k%>" name="reponse" value="<%=reponse.getLibelle()%>">
			    <label for="reponse<%=k %>"><%=reponse.getLibelle() %></label><br>
				</td></tr>
				<%} %>
			</table>
	    	<button type="submit" class="btn btn-primary">Valider</button>
		</form>
	</center>	
</div>		
<%@ include file="/structure/menu.jspf" %>
</body>
</html>