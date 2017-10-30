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
		
		testBdd.test();
		
		/*
		// Si l'animateur est déjà  connecté, on redirige vers le menu animateur
		utilisateurConnecte = (Utilisateur)request.getSession().getAttribute("utilisateurConnecte");
		System.out.println("utilisateurConnecte !" + utilisateurConnecte);
		if (utilisateurConnecte!=null) {
			System.out.println("Connecté !");
			redirectionMenuUtilisateur(request, response);
			return;
		}		
		
		// Récupération des informations saisies dans le formulaire
		String identifiant = request.getParameter("identifiant");
		String motdepasse = request.getParameter("motdepasse");

		// Controle des informations :
		// si tous les champs ne sont pas renseignés, revenir sur la page du formulaire
		if ((identifiant == null) || (identifiant.length() == 0) 
			|| (motdepasse == null) || (motdepasse.length() == 0)) {
			dispatcher = getServletContext().getRequestDispatcher("/login/accesUtilisateur");
			dispatcher.forward(request, response);
			return;
		}
		

		try {
			// Valider l'identification par rapport aux informations de la base
			utilisateurConnecte = UtilisateurDAO.rechercher(identifiant, motdepasse);
			// Si l'authentification est réussie...
			if (utilisateurConnecte != null) {
				System.out.println("authentification réussie !");

				//request.getSession().setAttribute("utilisateurConnecte", utilisateurConnecte);

				//redirectionMenuUtilisateur(request, response);
			}
			// ...sinon
			else {
				System.out.println("Pas Connecté !");
				// Retourner à l'écran d'identification			
				dispatcher = getServletContext().getRequestDispatcher("/login/accesUtilisateur");
				dispatcher.forward(request, response);
			}
		} catch (SQLException sqle) {
			// Placer l'objet représentant l'exception dans le contexte de requete
			//request.setAttribute("erreur", sqle);
			// Passer la main à la page de présentation des erreurs
			//dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			//dispatcher.forward(request, response);
			return;
		}		*/
	}
	
	protected void redirectionMenuUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// En fonction de la méthode de redirection utilisée (forward ou sendRedirect()),
		// l'utilisateur pourra voir ou non l'URL de la ressource : 
		
		// L'utilisation d'un forward masque la nouvelle ressource demandée (car tout 
		// se passe au sein du serveur d'application) 
		System.out.println("Redirection !");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/animateur/menuAnimateurPage");
		dispatcher.forward(request, response);
		
	}

}
