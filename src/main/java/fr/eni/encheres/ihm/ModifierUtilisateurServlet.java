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
 * Servlet implementation class ModifierUtilisateurServlet
 */
@WebServlet("/ModifierUtilisateur")
public class ModifierUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierUtilisateurServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProfilModel model = new ProfilModel();
		
		Utilisateur utilisateur =(Utilisateur) request.getSession().getAttribute("utilisateur");
		model.setUtilisateur(utilisateur);
		request.setAttribute("model", model);

		//MODIFIER SON PROFIL
		if(request.getParameter("modifierUtilisateur") != null) {
			System.out.println("Clic sur le bouton");
			//V�rifier si l'utilisateur a le bon mot de passe avant d'appliquer les modifications :
			if(request.getParameter("motDePasse").equals(utilisateur.getMotDePasse())) {
				String pseudo = request.getParameter("pseudo");
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String email = request.getParameter("email");
				String telephone = request.getParameter("telephone");
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");
				String motDePasse = request.getParameter("motDePasse");
				String nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
				System.out.println(codePostal);
				//Verifier si il y a une modification de mot de passe :
				Utilisateur utilisateurModif;
				
				//Si le nv mdp = confirmation nv mp ET le nv mdp n'est pas vide alors donner nv mdp � l'utilisateur
				if((request.getParameter("nouveauMotDePasse").equals(request.getParameter("confirmationMotDePasse")) && (! request.getParameter("nouveauMotDePasse").isBlank())))  {
					System.out.println(request.getParameter("confirmationMotDePasse"));
					utilisateurModif = new Utilisateur(utilisateur.getNoUtilisateur(),pseudo, nom, prenom, email, telephone, rue, codePostal, ville, nouveauMotDePasse, 100, false);

				}else{
					 utilisateurModif = new Utilisateur(utilisateur.getNoUtilisateur(),pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 100, false);
				}				
				try {
					UtilisateurManagerImpl.getInstance().modifierUtilisateur(model.getUtilisateur(),utilisateurModif);				
					model.setUtilisateur(utilisateurModif);
					request.getSession().setAttribute("utilisateur", utilisateurModif);
					request.setAttribute("message", "Modifications enregistrées");

					
				} catch (BLLException e) {
					e.printStackTrace();
					request.setAttribute("message", e.toString());
				}

			} else {
				request.setAttribute("message", "Attention, vous avez saisi un mauvais mot de passe. Saisissez votre mot de passe pour enregistrer vos modifications.");
			}
		}
				
		// SUPPRIMER
		if(request.getParameter("suppressionCompte")!=null) {
			try {				
				UtilisateurManagerImpl.getInstance().supprimerUtilisateur(model.getUtilisateur().getNoUtilisateur());
				request.getSession().setAttribute("utilisateur", null);
			} catch (BLLException e) {
			} 
			request.getRequestDispatcher("WEB-INF/Accueil.jsp").forward(request, response);			
		}
		request.getRequestDispatcher("WEB-INF/ModifierProfil.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);

		}
}

