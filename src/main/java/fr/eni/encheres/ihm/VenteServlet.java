package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManagerImpl;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.RetraitManagerImpl;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class VenteServlet
 */
@WebServlet("/NouvelleVente")
public class VenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VenteServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextScreen = "WEB-INF/Vente.jsp";
		VenteModel model = new VenteModel();
		Integer prix_article = 100;
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		Retrait retrait = new Retrait(utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille());
		model.setRetrait(retrait);
		
		if(request.getParameter("enregistrer") != null) {
			
			// Traitement des données de la catégorie
			Categorie categorie = new Categorie(Integer.parseInt(request.getParameter("categorie")));
			model.setCategorie(categorie);
			
			// Traitement des données de l'article
			String nom_article = request.getParameter("nom_article");
			String description = request.getParameter("description");
			LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("debut_enchere"));
			LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("fin_enchere"));
			prix_article = Integer.parseInt(request.getParameter("prix_article"));
		
			
			ArticleVendu articleAVendre = new ArticleVendu(nom_article, description, dateDebutEnchere, dateFinEnchere, prix_article, utilisateur,categorie,retrait);
			
			// Traitement des données du lieu de retrait
			retrait.setLieu(request.getParameter("rue"));
			retrait.setCodePostal(request.getParameter("cp"));
			retrait.setVille(request.getParameter("ville"));
			model.setRetrait(retrait);
			
			try {
				ArticleVendu nouvelArticle = ArticleVenduManagerImpl.getInstance().ajouterUnArticle(articleAVendre);
				retrait.setArticleVendu(nouvelArticle);
				RetraitManagerImpl.getInstance().ajouterNouveauRetrait(retrait);
				request.setAttribute("message", "Vente ajoutée");
			} catch (BLLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		if(request.getParameter("annuler")!=null) {
			model = new VenteModel();
		}
		
		if(request.getParameter("retour_accueil") != null) {
			nextScreen = "AccueilConnecte";
		}
		request.setAttribute("model", model);
		request.getRequestDispatcher(nextScreen).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
