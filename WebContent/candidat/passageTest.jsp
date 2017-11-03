<%@ page import ="fr.eni.jee.bo.*, java.util.*, java.text.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/css/style.css">
  <script src="<%=request.getContextPath() %>/theme/bootstrap/js/bootstrap.min.js"></script>
<title>ENI Ecole - Test</title>
</head>
<body>

<% Qcm test = (Qcm)request.getSession().getAttribute("qcm");%>
<% Question question = (Question)request.getSession().getAttribute("question");%>

<center><font color="#6495ED"><h1>Question N°<%=question.getId()%></h1></font></center>
<div class="container">
<center><font color="#6495ED"><h3><%=question.getEnonce().toString()%></h3></font></center>	
			
<center><form action="<%=request.getContextPath() %>/test/gestionTest" method="post">	
	<%for(Reponse reponse :question.getReponses()){ %>
    <input type="checkbox" class="reponse" name="reponse" value="<%=reponse.getLibelle()%>">
    <label for="reponse"><%=reponse.getLibelle() %></label>
	<%} %>
     <button type="submit" class="btn btn-primary">Validé</button>
     </center>
</div>

</form>			
			
<%@ include file="/structure/menu.jspf" %>
</body>
</html>