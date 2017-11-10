<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.jee.bo.*, java.util.*, java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/theme/css/style.css">
<script src="<%=request.getContextPath()%>/theme/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath() %>/theme/js/style.js"></script>
<script src="<%=request.getContextPath()%>/theme/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/theme/js/qcmForm.js"></script>

<title>ENI Ecole - Creation Theme Questiont</title>
</head>
<body>
	<%@ include file="/structure/logo.jspf"%>
	<center>
		<font color="#6495ED"><h1>Creation Theme</h1></font>
	</center>

	<form action="<%=request.getContextPath()%>/administrerQcm"
		method="POST">
		<div class="container">
			<div class="form-group">
				<label for="libelle"><h3>Libelle</h3></label><input name="libelle"
					type="libelle" class="form-control" id="libelle">
			</div>
			<br>
			<button type="submit" class="btn btn-primary" value="Qcm">Envoyer</button>
		</div>
	</form>
	<center>
		<font color="#6495ED"><h1>Creation Question</h1></font>
	</center>

	<form action="<%=request.getContextPath()%>/administrerQcm"
		method="POST">

		<div class="container">

			<h3>Choisir un theme</h3>
			<br> <select name="themes" class="form-control">
				<%
					ArrayList<Theme> listeThemes = (ArrayList<Theme>) request.getAttribute("listeThemes");
					for (Theme T : listeThemes) {
						int idTheme = T.getId();
				%>
				<option value="<%=idTheme%>"><a
						href="/ENI_QCM/test/administrerQcm?idTheme=<%=idTheme%>"><%=T.getNom()%></a>
				</option>
				<%
					}
				%>
			</select>


			<div class="form-group">
				<label for="enonce"><h3>Enonce</h3></label><input name="enonce"
					type="enonce" class="form-control" id="enonce">
			</div>
			<br>
			<fieldset>
			<legend><h3>Réponse(s)</h3><span id="bonneReponseLbl">Bonne(s) réponse(s) ?</span></legend>
				<table id="reponses">
					<tr class="fsReponse" id="firstreponse">
						<td>
							<h4>Reponse <span id="idReponse">1</span></h4>
							<div class="form-group">
								<input placeholder="Libellé de la réponse" name="reponse" type="reponse" class="form-control" id="reponse">
							</div>	
						</td>
						<td>
							<input id="vraiFaux" name="vraiFaux" type="checkbox" value="1" class="reponse"><br>
						</td>
					</tr>
				</table>	
			</fieldset>
			<a class="btn btn-info" id="addReponse">Ajouter une Reponse</a>
			<button type="submit" class="btn btn-primary" value="Qcm">Envoyer</button>
			<br>
		</div>
	</form>
</body>
<%@ include file="/structure/menu.jspf" %>