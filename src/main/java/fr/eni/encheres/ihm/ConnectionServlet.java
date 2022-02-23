package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ConnectionServlet
 */
@WebServlet("/Connexion")
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnectionServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nextScreen = "WEB-INF/ConnexionPage.jsp";
		String message = null;
		
		if(request.getSession().getAttribute("nouvelutilisateur")!= null ) {
			message = "Le compte a été crée avec succés. Veuillez-vous connecter.";
			request.setAttribute("message", message);

		}
		
		if (request.getParameter("Connexion") != null) {
			String identifiant = request.getParameter("identifiant");
			String motDePasse = request.getParameter("motDePasse");
			Utilisateur saisieUtilisateur = new Utilisateur(identifiant, identifiant, motDePasse);
			Utilisateur utilisateurRecupere;

			try {
				utilisateurRecupere = UtilisateurManagerImpl.getInstance()
						.verificationLogin(saisieUtilisateur);
				
				if(utilisateurRecupere.getNoUtilisateur() != null) {
					request.getSession().setAttribute("utilisateur", utilisateurRecupere);
					nextScreen = "AccueilConnecte";
				} 
			} catch (BLLException e) {
				message = e.toString();
				
			}
			
			request.setAttribute("message", message);

		}
		if (request.getParameter("Creation Compte") != null) {
			nextScreen = "Inscription";
		}
		
		
		request.getRequestDispatcher(nextScreen).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
