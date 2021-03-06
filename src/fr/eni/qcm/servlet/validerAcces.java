package fr.eni.qcm.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.jee.bo.Utilisateur;
import fr.eni.jee.dal.UtilisateurDAO;
import fr.eni.jee.dal.testBdd;



/**
 * Servlet implementation class ValiderAccesAnimateur
 */
public class validerAcces extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validerAcces() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		valider(request, response);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		valider(request, response);
		
	}


	protected void valider(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher;
		Utilisateur utilisateurConnecte = null;
		
		// Si l'utilisateur est d�j� connect�, on redirige vers le menu animateur
		utilisateurConnecte = (Utilisateur)request.getSession().getAttribute("utilisateurConnecte");
		System.out.println("utilisateurConnecte !" + utilisateurConnecte);
		if (utilisateurConnecte!=null) {
			if ("Candidat".equals(utilisateurConnecte.getRole())) {
				redirectionMenuCandidat(request, response);
			}
			else if ("Administrateur".equals(utilisateurConnecte.getRole())) {
				redirectionMenuAdministratif(request, response);
			}
			else if ("Formateur".equals(utilisateurConnecte.getRole())) {
				redirectionMenuFormateur(request, response);
			}
			else{
				//Redirection par defaut
				redirectionMenuCandidat(request, response);
			}
			return;
		}		
		
		// R�cup�ration des informations saisies dans le formulaire
		String identifiant = request.getParameter("identifiant");
		String motdepasse = request.getParameter("motdepasse");

		// Controle des informations :
		// si tous les champs ne sont pas renseign�s, revenir sur la page du formulaire
		if ((identifiant == null) || (identifiant.length() == 0) 
			|| (motdepasse == null) || (motdepasse.length() == 0)) {
			dispatcher = getServletContext().getRequestDispatcher("/login/accesUtilisateur.jsp");
			dispatcher.forward(request, response);
			return;
		}
		

		try {
			// Valider l'identification par rapport aux informations de la base
			utilisateurConnecte = UtilisateurDAO.rechercher(identifiant, motdepasse);
			
			// Si l'authentification est r�ussie...
			if (utilisateurConnecte != null) {
				request.getSession().setAttribute("utilisateurConnecte", utilisateurConnecte);
				if ("Candidat".equals(utilisateurConnecte.getRole())) {
					redirectionMenuCandidat(request, response);
				}
				else if ("Administrateur".equals(utilisateurConnecte.getRole())) {
					redirectionMenuAdministratif(request, response);
				}
				else if ("Formateur".equals(utilisateurConnecte.getRole())) {
					redirectionMenuFormateur(request, response);
				}else{
					redirectionMenuCandidat(request, response);
				}
				return;
						
			}
			else {
				System.out.println("Pas Connect� !");
				// Retourner � l'�cran d'identification			
				dispatcher = getServletContext().getRequestDispatcher("/login/accesUtilisateur.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException sqle) {
			// Placer l'objet repr�sentant l'exception dans le contexte de requete
			//request.setAttribute("erreur", sqle);
			// Passer la main � la page de pr�sentation des erreurs
			//dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			//dispatcher.forward(request, response);
			return;
		}	
	}
	
	protected void redirectionMenuCandidat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Redirection !");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listerQcm");
		dispatcher.forward(request, response);
		
	}
	
	protected void redirectionMenuAdministratif(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Redirection !");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administratif/menuAdministratif.jsp");
		dispatcher.forward(request, response);
		
	}
	
	protected void redirectionMenuFormateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Redirection !");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listerQcm");
		dispatcher.forward(request, response);
		
	}

}
