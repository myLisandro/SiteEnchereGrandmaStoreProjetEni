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
 * En tant qu�utilisateur, je peux afficher le profil d�un utilisateur. Le
 * pseudo, nom, pr�nom, email, t�l�phone, rue, code postal, ville sont affich�s.
 */
@WebServlet("/AfficherProfilVendeurServlet")
public class AfficherPofilVendeurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherPofilVendeurServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ProfilModel model = new ProfilModel();
		
		String pseudoVendeur = request.getParameter("pseudoVendeur");
		System.out.println(pseudoVendeur);
		
		Utilisateur utilisateurVendeur;
		try {
			utilisateurVendeur = UtilisateurManagerImpl.getInstance().getByPseudoUtilisateur(pseudoVendeur);
			model.setUtilisateur(utilisateurVendeur);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("model", model);

		
		request.getRequestDispatcher("WEB-INF/ProfilVendeur.jsp").forward(request, response);

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
