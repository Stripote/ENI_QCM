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
<title>ENI Ecole - Menu Candidat</title>
</head>
<body>
<%@ include file="/structure/logo.jspf" %>
<center><font color="#6495ED"><h1>Menu Candidat</h1></font></center>
<%

ArrayList<Qcm> listeQcms = (ArrayList<Qcm>)request.getAttribute("listeQcms");
	 	for(Qcm Q : listeQcms) {
	%>
	  <% int idQcm = Q.getId();
	     //lien servlet axel
	  %>
		<div class="container">
			<center><form action="/ENI_QCM/test/creationTest?idQcm=<%= idQcm %>" method="POST" >
			<a href ="/ENI_QCM/test/creationTest?idQcm=<%= idQcm %>"> <p><%=Q.getNom()%></p></a>
			</form></center>
			</div>
			<br />
	<% } %>

<%@ include file="/structure/menu.jspf" %>

</body>
</html>