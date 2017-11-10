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
  <script src="<%=request.getContextPath() %>/theme/js/style.js"></script>
  <title>ENI Ecole - Menu Formateur</title>
</head>
<body>
<center><font color="#6495ED"><h1>Menu Formateur</h1></font></center>


<div class="container">
<h3>Questionnaires existants</h3>
	<center><table>
<%
	ArrayList<Qcm> listeQcms = new ArrayList<Qcm>();
	listeQcms = (ArrayList<Qcm>)request.getSession().getAttribute("listeQcms");
	for(Qcm Q : listeQcms) 
	{
%>
		<tr>
			<td><p class="listQcm_element" idQcm="<%= Q.getId() %>"><%=Q.getNom()%></p></td>
			<td><a href=# title="Modifier" class="btn btn-info glyphicon glyphicon-edit"></a></td>
			<td><a href=# title="Supprimer" class="btn btn-danger glyphicon glyphicon-erase"></a></td>
		</tr>
<% } %>
	</table></center>
</div>
<br />

<ul>
<li><a href="/ENI_QCM/listerTheme">Créer un QCM</a></li>
<li><a href="<%=request.getContextPath() %>/administrerQcm">Ajouter un thème / Creer des questions</a></li>
</ul>
</body>
<%@ include file="/structure/menu.jspf" %>
</html>