package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Utilisateur;

/**
 * En tant qu�utilisateur, je peux afficher le profil d�un utilisateur. Le
 * pseudo, nom, pr�nom, email, t�l�phone, rue, code postal, ville sont affich�s.
 */
@WebServlet("/AfficherPofilServlet")
public class AfficherPofilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficherPofilServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ProfilModel model = new ProfilModel();
		
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		model.setUtilisateur(utilisateur);
		request.setAttribute("model", model);

		
		request.getRequestDispatcher("WEB-INF/Profil.jsp").forward(request, response);

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
