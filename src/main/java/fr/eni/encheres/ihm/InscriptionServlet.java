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
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/Inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextScreen = "WEB-INF/Inscription.jsp";
		
		if(request.getParameter("creerUtilisateur") != null) {
			if(request.getParameter("motDePasse").equals(request.getParameter("confirmationMotDePasse"))) {
				String pseudo = request.getParameter("pseudo");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");	
				String email = request.getParameter("email");
				String telephone = request.getParameter("telephone");
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");
				String motDePasse = request.getParameter("motDePasse");
				
				Utilisateur nouvelUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 100, false);

				request.setAttribute("messageInscriptionOk", "Merci pour votre inscription! Vous pouvez vous connecter");
				
				try {
					UtilisateurManagerImpl.getInstance().ajouterNouvelUtilisateur(nouvelUtilisateur);
					request.getSession().setAttribute("nouvelUtilisateur", "nouveau");
					nextScreen ="Connexion";
				
				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("message", e.toString());
				}
				
			} else {
				request.setAttribute("message", "Attention, vous avez saisi deux mots de passes différents. Veuillez recommancer s'il vous plait");
			}
		}
		
		if(request.getParameter("annulerUtilisateur") != null) {
			nextScreen = "Connexion";
		}
		
		
		request.getRequestDispatcher(nextScreen).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
