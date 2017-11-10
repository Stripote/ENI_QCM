<%@ page import="fr.eni.jee.bo.*, java.util.*, java.text.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/theme/css/style.css">
  <script src="<%=request.getContextPath() %>/theme/bootstrap/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath() %>/theme/js/style.js"></script>
  <script src="<%=request.getContextPath()%>/theme/js/jquery-3.2.1.min.js"></script>
<title>ENI Ecole - Inscription</title>
</head>
<body>

<h1>Inscription des candidats</h1>

<a class="btn btn-info" id="addUser" href="<%=request.getContextPath() %>/administratif/accesCreationUtilisateur">Ajouter un utilisateur</a>

<form action="<%=request.getContextPath() %>/administratif/validerInscription" method="post">
	<div class="container">
			<div id="inscriptions">
				<fieldset class="fsInscription" id="firstSection"><legend>Inscription <span id="idInscription"></span></legend>
					<h4>Choisir un candidat</h4>
					<select name="candidats" class="form-control">
					<%
					ArrayList<Utilisateur> listeUtilisateurs = (ArrayList<Utilisateur>) request.getSession().getAttribute("listeUtilisateurs");
					for (Utilisateur U : listeUtilisateurs) {
						if("Candidat".equals(U.getRole())){
						int idUtilisateur = U.getId();
						%>
						<option value="<%=idUtilisateur%>">
							<a href="<%=request.getContextPath() %>/administratif/validerInscription?idUtilisateur=<%=idUtilisateur%>"><%=U.getPrenom()+" "+U.getNom()%></a>
						</option>
					<%}}%>
					</select> 
					<br />
					
					<h4>Choisir les tests</h4>
					<select name="qcms" class="form-control">
					<%
					ArrayList<Qcm> listeQcm = (ArrayList<Qcm>) request.getSession().getAttribute("listeQcms");
					for (Qcm Q : listeQcm) {
						int idQcm = Q.getId();
						%>
						<option value="<%=idQcm%>">
							<a href="<%=request.getContextPath() %>/administratif/validerInscription?idQcm=<%=idQcm%>"><%=Q.getNom()%></a>
						</option>
					<%}%>
					</select>
						
				</fieldset>
			</div>
			<br>
			<button type="submit" class="btn btn-primary" value="Inscription">Envoyer</button>	
	</div>
</form>

<%@ include file="/structure/menu.jspf" %>

</body>
</html>