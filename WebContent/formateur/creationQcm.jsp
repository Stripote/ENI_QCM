<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.jee.bo.*, java.util.*, java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"
	http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/theme/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/theme/css/style.css">
<script
	src="<%=request.getContextPath()%>/theme/bootstrap/js/bootstrap.min.js"></script>

<title>ENI Ecole - Creation Qcm</title>
</head>
<body>
	<%@ include file="/structure/logo.jspf"%>
	<center>
		<font color="#6495ED"><h1>Creation Qcm</h1></font>
	</center>


	<form action="Qcm" method="POST" >
		<div class="container">

			<h3>Choisir un Nom de Qcm</h3>


			<div class="form-group">
				<label for="nom">Nom</label><input type="nom" class="form-control"
					id="nom">
				
			</div>




			<h3>Choisir un theme</h3>
			<select class="form-control">

				<%
					ArrayList<Theme> listeThemes = (ArrayList<Theme>) request.getAttribute("listeThemes");
					for (Theme T : listeThemes) {
				%>
				<%
					int idTheme = T.getId();
				%>
				<option value="<%=idTheme%>"><a
						href="/ENI_QCM/test/creationQcm?idTheme=<%=idTheme%>"><%=T.getNom()%></a></option>

				<%
					}
				%>
			</select> <br />

			<h3>Choisir un nombre de questions</h3>
			<select class="form-control">



				<%
					for (int i = 1; i <= 10; ++i) {
				%>


				<option value="<%=i%>"><%=i%></option>


				<%
					}
				%>

			</select>
<br>
			<button type="submit" class="btn btn-primary" value="Qcm">Envoy�</button>

		</div>

	</form>

	<br />

	<%@ include file="/structure/menu.jspf"%>

</body>
</html>