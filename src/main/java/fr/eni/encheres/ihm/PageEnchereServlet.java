package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleVenduManagerImpl;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EnchereManagerImpl;
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/DetailEnchere")
public class PageEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageEnchereServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageEnchereModel model = new PageEnchereModel();
		Utilisateur ancienEncheriste = null;
		Utilisateur nouvelEncheriste = null;
		Integer noArticleEnchere;
		ArticleVendu detailArticle = null;
		Integer enchereMax;

		// Réccupération du noArticle envoyé par la requête en "hidden" via l'url
		noArticleEnchere = Integer.valueOf(request.getParameter("noArticle"));
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		Integer noEncheriste = utilisateur.getNoUtilisateur();
		request.setAttribute("pseudoUtilisateur", utilisateur.getPseudo());
		
		try {
			// Réccupération de l'article en cours et de sa meilleure enchère
			detailArticle = ArticleVenduManagerImpl.getInstance().selectBestEnchereByNoArticle(noArticleEnchere);
			
			// Réccupération des informations du vendeur
			Utilisateur vendeur = UtilisateurManagerImpl.getInstance().getByIdUtilisateur(detailArticle.getUtilisateur().getNoUtilisateur());
			request.setAttribute("nomVendeur", vendeur.getPseudo());
			
			// Réccupération des informations de l'ancien enchériste
			ancienEncheriste = UtilisateurManagerImpl.getInstance().getByIdUtilisateur(detailArticle.getEnchereMaximum()
					.getUtilisateur().getNoUtilisateur());	
			if(ancienEncheriste == null) {
				request.setAttribute("aucuneEnchere", "aucune enchère actuellement");
			} else {
				model.setEncheriste(ancienEncheriste);
			}
			
			model.setArticle(detailArticle);
			model.setVendeur(vendeur);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (BLLException e) {
			e.printStackTrace();
		}
				
		if(request.getParameter("nouvelleEnchere") != null) {
			try {
				// Récupération des informations de l'enchériste
				nouvelEncheriste = UtilisateurManagerImpl.getInstance().getByIdUtilisateur(noEncheriste);
				
				// Récupération de la proposition effectuée
				Integer proposition = Integer.parseInt(request.getParameter("proposition"));
				
				// Récupération de l'ancienne proposition maximale ou 0 si pas d'enchère précédente
				enchereMax = detailArticle.getEnchereMaximum().getMontantEnchere();
				
				if(proposition > enchereMax && proposition > detailArticle.getMiseAPrix()) {
					// Ajout d'une nouvelle enchère
					EnchereManagerImpl.getInstance().ajouterNouvelleEnchere(noArticleEnchere, noEncheriste, proposition);
					
					// Mise à jour du prix de vente du produit
					ArticleVenduManagerImpl.getInstance().miseAJourPrixVente(noArticleEnchere, proposition);
					
					// Modification du crédit de point de l'enchériste (moins)
					UtilisateurManagerImpl.getInstance().modifierCreditEncheriste(nouvelEncheriste, proposition);
					
					// Modification du crédit de point de l'ancien enchériste (plus)
					UtilisateurManagerImpl.getInstance().modifierCreditAncienEncheriste(ancienEncheriste, enchereMax);
					
					// Mise à jour de l'article en cours
					detailArticle.getEnchereMaximum().setMontantEnchere(proposition);
					
					// Mise à jour de l'enchériste ayant effectué la plus haute enchère
					detailArticle.getEnchereMaximum().setUtilisateur(nouvelEncheriste);
					
					
					// Mise à jour du model
					model.setArticle(detailArticle);
					model.setEncheriste(nouvelEncheriste);
					model.setMessage("Votre enchère est bien enregistrée");
					request.setAttribute("aucuneEnchere", "");
				}
				
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("messageErreur", e.toString());
			}
			
		}
		
		request.setAttribute("model", model);
		
		request.getRequestDispatcher("WEB-INF/PageEnchere.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
