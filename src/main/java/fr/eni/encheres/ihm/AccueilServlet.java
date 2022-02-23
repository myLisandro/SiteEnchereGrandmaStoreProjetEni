package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManagerImpl;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategorieManagerImpl;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;


@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccueilServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = "Toutes";
		String motClef = null;
		
		List<ArticleVendu> lstARetouner = new ArrayList<ArticleVendu>();
		
		// Remonter la liste des catégories disponibles
		try {
			List<Categorie> lstCategories = CategorieManagerImpl.getInstance().listeDesCategories();
			request.getServletContext().setAttribute("lstCategories", lstCategories);
		} catch (BLLException e1) {
			e1.printStackTrace();
		}
		
		//Au clique sur rechercher on r�cup�re la cat�gorie et un possible motClef
		if (request.getParameter("recherche")!= null) {
			option = request.getParameter("listeDeroulante");
			motClef = request.getParameter("nomArticle");
		}
		
		
		// si il n'y a pas de mot cl�e on retourne la Liste global filtrer par le choix de cat�gorie 
		if (motClef == null || motClef.isBlank()) {
			
			try {
				List<ArticleVendu> LstFinal = ArticleVenduManagerImpl.getInstance().FiltreSuivantCategorie(option);
				request.setAttribute("Liste", ArticleVenduManagerImpl.getInstance().filtreArticleEncoursParDate(LstFinal));
			} catch (BLLException e) {
				e.printStackTrace();
			}
			//si il y a un mot cl�e je r�cup�re la liste tri� par cat�gorie puis je la trie par recherche par mot cl� avant de la renvoyer 
		}else {
			List<ArticleVendu> LstFinal = new ArrayList<ArticleVendu>();
			try {
				lstARetouner = ArticleVenduManagerImpl.getInstance().FiltreSuivantCategorie(option);
				 LstFinal = ArticleVenduManagerImpl.getInstance().filtreArticleEncoursParDate(lstARetouner);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("Liste", ArticleVenduManagerImpl.getInstance().RechercheDansLeNomDelArticle(LstFinal, motClef));
		}
		
		
		request.getRequestDispatcher("WEB-INF/Accueil.jsp").forward(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
