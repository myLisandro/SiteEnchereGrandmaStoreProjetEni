/**
 * 
 */
package fr.eni.encheres.test;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.ArticleVenduManagerImpl;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bo.ArticleVendu;

/**
 * Classe en charge de tester la couche BLL
 * 
 */
public class TestBLL {
	public static void main(String[] args) {
		
	/*
	 * =========================================================
	 * 				TEST INSCRIPTION UTILISATEUR
	 * =========================================================
	*/
//		// Vérification erreur pseudo avec caractères spéciaux
//		Utilisateur utilisateurErreurPseudo = new Utilisateur("Pseudo!", "Erreur", "Pseudo", "erreur.pseudo@outlook.fr", "15 rue de l'impasse", "35000", "Rennes", "monErreur", 100, false);
//		try {
//			mger.ajouterNouvelUtilisateur(utilisateurErreurPseudo);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
//		
//		// Vérification erreur de doublon de pseudo
//		Utilisateur utilisateurDoublonPseudo = new Utilisateur("mamie", "Doublon", "Pseudo", "erreur.pseudo@outlook.fr", "16 rue de l'impasse", "35000", "Rennes", "monErreur", 100, false);
//		try {
//			mger.ajouterNouvelUtilisateur(utilisateurDoublonPseudo);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
//		
//		// Vérification erreur de doublon d'email
//		Utilisateur utilisateurDoublonEmail = new Utilisateur("Alfred", "Doublon", "Email", "clairegoarnisson@gmail.com", "17 rue de l'impasse", "35000", "Rennes", "monErreur", 100, false);
//		try {
//			mger.ajouterNouvelUtilisateur(utilisateurDoublonEmail);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
//		
//		// Vérification insertion utilisateur sans anomalie
//		Utilisateur utilisateurSansAnomalies = new Utilisateur("JeSaisPas", "Cérien", "Jean", "jeancerien@gmail.com", "13 la voie est libre", "35000", "Rennes", "monErreur", 100, false);
//		try {
//			mger.ajouterNouvelUtilisateur(utilisateurSansAnomalies);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
//		
//	
//		/*
//		 * =========================================================
//		 * 				TEST CONNEXION UTILISATEUR
//		 * =========================================================
//		*/
//		
//		// test identifiant 1
//		// Utilisateur claire 
//				
//		Utilisateur test = new Utilisateur("clairegoarnisson@gmail.com", "mdp5");
//		Utilisateur recuperer = new Utilisateur();
//		try {
//			recuperer = UtilisateurManagerImpl.getInstance().verificationLogin(test);
//		} catch (BLLException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//		
//		// constat en cas de combinaison juste je fait bien remonter l'objet utilisateur et toute les donn�es corespondante en base de donn�e
//		// TODO message d'exception non remont�
//		// en cas d'erreur l'exception est bien lev�e mais le message pr�d�finie n'est pas pr�sent� (ni les donn�e saisie en entr�e)
//		System.out.println(recuperer);
//
//		
		/*
		 * =========================================================
		 * 				TEST ARTICLE BY ID
		 * =========================================================
		*/
		
//		try {
//			ArticleVendu monArticle = ArticleVenduManagerImpl.getInstance().selectBestEnchereByNoArticle(1);
//			System.out.println(monArticle);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
		
		
		/*
		 * =========================================================
		 * 				TEST Liste des articles
		 * =========================================================
		*/
		
//		try {
//		List<ArticleVendu> lstArticles=	UtilisateurManagerImplAngelo.getInstance().RecuperationArticleEtUtilisateur();
//		lstArticles.forEach(System.out::println);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
		
		
//		try {
//			List<ArticleVendu> lstArticlesVendus = DAOFactory.getArticleVenduDAO().getAllArticleVendu();
//			lstArticlesVendus.forEach(System.out::println);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
		
		
		// Liste de mes ventes en cours
//		List<ArticleVendu> lstMesVentesEnCours = new ArrayList<ArticleVendu>();
//		List<ArticleVendu> lstAllVentes;
//		try {
//			lstAllVentes = UtilisateurManagerImplAngelo.getInstance().FiltreSuivantCategorie("Toutes");
//			lstMesVentesEnCours = ArticleVenduManagerImpl.getInstance().lstMesVentesEnCours("mamie", lstAllVentes);
//			lstMesVentesEnCours.forEach(System.out::println);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
		
		// liste de mes ventes non débutées
//		List<ArticleVendu> lstMesVentesNonDebutees = new ArrayList<ArticleVendu>();
//	
//		try {
//			lstMesVentesNonDebutees = ArticleVenduManagerImpl.getInstance().lstMesVentesNonDebutes("Roxy");
//			lstMesVentesNonDebutees.forEach(System.out::println);
//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
		
		// Liste de mes ventes terminées
//		List<ArticleVendu> lstMesVentesTerminees = new ArrayList<ArticleVendu>();
//		List<ArticleVendu> lstAllVentes;
//		try {
//			lstAllVentes = UtilisateurManagerImplAngelo.getInstance().FiltreSuivantCategorie("Toutes");
//			lstMesVentesTerminees = ArticleVenduManagerImpl.getInstance().lstMesVentesTerminees("Roxy",lstAllVentes);
//			lstMesVentesTerminees.forEach(System.out::println);
//		} catch (BLLException e1) {
//			e1.printStackTrace();
//		}
		
		// Liste filtre mes ventes
		List<Integer> lstCheck = new ArrayList<Integer>();
		lstCheck.add(1);
		
		List<ArticleVendu> lstMesVentes = new ArrayList<ArticleVendu>();
		try {
			lstMesVentes = ArticleVenduManagerImpl.getInstance().FiltreSuivantCategorie("Toutes");
		} catch (BLLException e) {
			e.printStackTrace();
		}
				
		
		List<ArticleVendu> lstMesVentesCheck = ArticleVenduManagerImpl.getInstance().lstFiltreMesVentes("mamie", lstCheck, lstMesVentes);
		
		lstMesVentesCheck.forEach(System.out::println);
	
		
	}

}
