package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManagerImpl;

/**
 * Servlet implementation class MotDePasseOublie
 */
@WebServlet("/MotDePasseOublie")
public class MotDePasseOublieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MotDePasseOublieServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		if (request.getParameter("Recuperation Mot de passe") != null) {
			String identifiant = request.getParameter("identifiant");
			String message = "BLLException catch au niveau de la servlet";
			try {
				message = UtilisateurManagerImpl.getInstance().RecuperationMotDePasse(identifiant);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("message", message);
			
			
		}
		request.getRequestDispatcher("WEB-INF/MotDePasseOublie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
