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
import fr.eni.encheres.bll.UtilisateurManagerImpl;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class AccueilConnecte
 */
@WebServlet("/AccueilConnecte")
public class AccueilConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilConnecte() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextScreen = "WEB-INF/AccueilConnecte.jsp";
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
	
		// Remonter la liste des categories disponibles
				try {
					List<Categorie> lstCategories = CategorieManagerImpl.getInstance().listeDesCategories();
					request.getServletContext().setAttribute("lstCategories", lstCategories);
				} catch (BLLException e1) {
					e1.printStackTrace();
				}
				
		//----------------------------------------- Copie fonction recherche--------------------------------------------------
		String option = "Toutes";
		String motClef = null;
		String radioSelect = "rien" ;
		List<Integer> checkBoxSelect = new ArrayList<Integer>();
		Utilisateur utiliPourRecherche = new Utilisateur();
		
		//Au clique sur rechercher on recupere la categorie et un possible motClef
		if (request.getParameter("recherche")!= null) {
			option = request.getParameter("listeDeroulante");
			motClef = request.getParameter("nomArticle");
			try {
				utiliPourRecherche =UtilisateurManagerImpl.getInstance().getByIdUtilisateur(utilisateur.getNoUtilisateur());
			} catch (BLLException e) {
				e.printStackTrace();
			}
			 

			// on récupère les choix utilisateurs dans les radio et checkbox
			if ("AchatRadio".equals(request.getParameter("achatVente"))) {
				radioSelect = "achat";
				if (request.getParameter("encheresOuvertes")!=null) {
					checkBoxSelect.add(1);
				}
				if (request.getParameter("mesEncheres")!=null) {
					checkBoxSelect.add(2);
				}
				if (request.getParameter("encheresRemportees")!=null) {
					checkBoxSelect.add(3);
				}
			} else if ("VenteRadio".equals(request.getParameter("achatVente"))) {
				radioSelect = "mesventes";
				if (request.getParameter("ventesEnCours")!=null) {
					checkBoxSelect.add(1);
				}
				if (request.getParameter("ventesNonDebutees")!=null) {
					checkBoxSelect.add(2);
				}
				if (request.getParameter("ventesTerminees")!=null) {
					checkBoxSelect.add(3);
				}
			} 
			
			
		}
		
		// si il n'y a pas de mot clee on retourne la Liste globale filtrée par le choix de catégorie 
		if (motClef == null || motClef.isBlank()) { 		
			List<ArticleVendu> listTemp = new ArrayList<ArticleVendu>();
			try {
				listTemp = ArticleVenduManagerImpl.getInstance().FiltreSuivantCategorie(option);
				if (radioSelect.equals("achat")) {
					List<ArticleVendu> lstRechercheAchat= ArticleVenduManagerImpl.getInstance().filtreCheckboxAchat(checkBoxSelect, listTemp, utiliPourRecherche);
					request.setAttribute("Liste",lstRechercheAchat );
					
				} else if (radioSelect.equals("mesventes")) {
					List<ArticleVendu> lstRechercheVente =	ArticleVenduManagerImpl.getInstance().lstFiltreMesVentes(utiliPourRecherche.getPseudo(), checkBoxSelect, listTemp);
					request.setAttribute("Liste", lstRechercheVente);
					
				} else {
					//cas ou rien n'est select
					request.setAttribute("Liste", listTemp);
				}
				
				
			} catch (BLLException e) {
				e.printStackTrace();
			}
			//si il y a un mot clee je recupere la liste triee par categorie puis je la trie par recherche par mot clee avant de la renvoyer 
		}else {
			List<ArticleVendu> listPretePourLaRecherche = new ArrayList<ArticleVendu>();
			List<ArticleVendu> listTemp = new ArrayList<ArticleVendu>();
			try {
				listTemp = ArticleVenduManagerImpl.getInstance().FiltreSuivantCategorie(option);
				
				if (radioSelect.equals("achat")) {
					listPretePourLaRecherche = ArticleVenduManagerImpl.getInstance().filtreCheckboxAchat(checkBoxSelect, listTemp, utiliPourRecherche);
					
				} else if (radioSelect.equals("mesventes")) {
					listPretePourLaRecherche =	ArticleVenduManagerImpl.getInstance().lstFiltreMesVentes(utiliPourRecherche.getPseudo(), checkBoxSelect, listTemp);
					
				} else {
					
					listPretePourLaRecherche = listTemp;
				}
				
			} catch (BLLException e) {
				
				e.printStackTrace();
			}
			
			List<ArticleVendu> lstArticlesAttribute = ArticleVenduManagerImpl.getInstance().RechercheDansLeNomDelArticle(listPretePourLaRecherche, motClef);
			
			request.setAttribute("Liste", lstArticlesAttribute);
		}
		
		
		//-----------------------------------------------------fin copie fonction recherche---------------------------------------------------
		
	
		if(request.getParameter("deconnexion") != null) {
			request.getSession().setAttribute("utilisateur", null);
			nextScreen = "Accueil";
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
