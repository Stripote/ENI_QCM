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
<script
	src="<%=request.getContextPath()%>/theme/js/jquery-3.2.1.min.js"></script>
<script
	src="<%=request.getContextPath()%>/theme/js/qcmForm.js"></script>

<title>ENI Ecole - Creation Qcm</title>
</head>
<body>
	<%@ include file="/structure/logo.jspf"%>
	<center>
		<font color="#6495ED"><h1>Creation Qcm</h1></font>
	</center>
	<form action="<%=request.getContextPath()%>/formateur/creerQcm" method="POST" >
		<div class="container">
			<div class="form-group">
				<label for="nom"><h3>Donnez un nom au QCM</h3></label><input name="nom" type="text" class="form-control" id="nom">	
			</div>
			<div id="sections">
				<fieldset class="fsSection" id="firstSection"><legend>Section <span id="idSection">1</span></legend>
					<h4>Choisir un theme</h4>
					<select name="themes" class="form-control">
					<%
					ArrayList<Theme> listeThemes = (ArrayList<Theme>) request.getAttribute("listeThemes");
					for (Theme T : listeThemes) {
						int idTheme = T.getId();
						%>
						<option value="<%=idTheme%>">
							<a href="/ENI_QCM/test/creationQcm?idTheme=<%=idTheme%>"><%=T.getNom()%></a>
						</option>
					<%}%>
					</select> 
					<br />
					<h4>Choisir un nombre de questions</h4>
					<select name="nbQuestions" class="form-control">
					<%for (int i = 1; i <= 10; ++i) {%>
						<option value="<%=i%>"><%=i%></option>
					<%}%>
					</select>
				</fieldset>
			</div>
			<br><br>
			<a class="btn btn-info" id="addSection">Ajouter une section</a>
			<button type="submit" class="btn btn-primary" value="Qcm">Envoyer</button>
		</div>
	</form>

	<br />

	<%@ include file="/structure/menu.jspf"%>

</body>
</html>