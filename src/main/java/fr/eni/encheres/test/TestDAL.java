/**
 * 
 */
package fr.eni.encheres.test;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

public class TestDAL {

	
	/**
	 * Méthode en charge de tester la couche DAL
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {

//		
//		/*
//		 * =========================================================
//		 * 				TEST INSCRIPTION UTILISATEUR
//		 * =========================================================
//		*/
//		
//		// Insertion d'un nouvel utilisateur
//		try {
//			DAOFactory.getUtilisateurDAO().ajouterUtilisateur(new Utilisateur("pépé", "arien", "fernand", "fernandesangelo@gmail.com", "0606060606", "2 rue de la chapelle", "35000", "RENNES", "mdp", 100, false));
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
//		
//		
//		/*
//		 * =========================================================
//		 * 				TEST LISTE UTILISATEURS
//		 * =========================================================
//		*/
//
//		// Liste de tous les utilisateurs
//		try {
//			List<Utilisateur> lstUtilisateurs = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
//			lstUtilisateurs.forEach(System.out::println);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}


	
//		List<ArticleVendu> Lst = new ArrayList<ArticleVendu>();
//		
//		try {
//			Lst = DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();
//			
//			
//		} catch (DALException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		for (ArticleVendu articleVendu : Lst) {
//			System.out.println(articleVendu.getUtilisateur().getPseudo());
//			System.out.println(articleVendu);
//			System.out.println(articleVendu.getLieuRetrait());
//			System.out.println(articleVendu.getCategorieArticle());
//		}
//		System.out.println("l");

		
		/*
		 * =========================================================
		 * 			TEST AJOUT ARTICLE A VENDRE
		 * =========================================================
		*/
//		List<Utilisateur> lstAllUtilisateurs = null;
//		try {
//			lstAllUtilisateurs = DAOFactory.getUtilisateurDAO().getAllUtilisateurs();
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
//		Utilisateur utilisateur = null;
//		try {
//			utilisateur = DAOFactory.getUtilisateurDAO().selectUtilisateurById(3);
//		} catch (DALException e1) {
//			e1.printStackTrace();
//		}
//		
//		Categorie categorieArticle = new Categorie(3);
//		Retrait retraitArticle = new Retrait("11 rue des lilas", "44000","Nanntes");
//		ArticleVendu articleAVendre = new ArticleVendu("Bol pour tricot", "Céramique. 15 x 15 x 10 cm", LocalDate.now(), LocalDate.now().plusDays(15), 100,utilisateur, categorieArticle, retraitArticle);
////		
//		try {
//			DAOFactory.getArticleVenduDAO().ajouterArticleAVendre(articleAVendre, utilisateur);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}


		/*
		 * =========================================================
		 * 			TEST AJOUT RETRAIT
		 * =========================================================
		*/
		
		
		// Récupération d'un utilisateur
//		Utilisateur grandBrother = null;
//		
//		try {
//			grandBrother = DAOFactory.getUtilisateurDAO().getById(19);
//		} catch (DALException e1) {
//			e1.printStackTrace();
//		}
//		
//		// Création d'un objet retrait
//		Retrait monRetrait = new Retrait("Chateau D'Arendel", "35000", "RENNES");
//		
//		// Création d'une nouvelle catégorie
//		Categorie categorie = new Categorie(4);
//		
//		// Création d'un nouvel article et insertion en BDD
//		ArticleVendu monArticle = new ArticleVendu("Nom d'un article", "Sa description", LocalDate.now(),LocalDate.now().plusDays(10), 200, grandBrother, categorie, monRetrait);
//		try {
//			DAOFactory.getArticleVenduDAO().ajouterArticleAVendre(monArticle, grandBrother);
//		} catch (DALException e1) {
//			e1.printStackTrace();
//		}
//		
//		// Mise à jour du lieu de retrait et insertion en BDD
//		monRetrait.setArticleVendu(monArticle);
//		try {
//			DAOFactory.getRetraitDAO().ajouterRetrait(monRetrait);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
		
//		try {
//			List<Categorie> lstCategorie = DAOFactory.getCategorieDAO().listeDesCategories();
//			lstCategorie.forEach(System.out::println);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
		
		
		/*
		 * =========================================================
		 * 			TEST SELECTION ARTICLE BY ID
		 * =========================================================
		*/
		
//		try {
//			ArticleVendu monArticle = DAOFactory.getArticleVenduDAO().selectArticleByIdBestEnchere(1);
//			Integer noEncheriste = monArticle.getEnchereMaximum().getUtilisateur().getNoUtilisateur();
//			Integer noVendeur = monArticle.getUtilisateur().getNoUtilisateur();
//			System.out.println("no encheriste " + noEncheriste);
//			System.out.println("no_vendeur " + noVendeur);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
		
		
		/*
		 * =========================================================
		 * 			TEST AJOUTER NOUVELLE ENCHERE
		 * =========================================================
		*/
		
//		try {
//			DAOFactory.getEnchereDAO().ajouterNouvelleEnchere(2, 2, 150);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
		
		/*
		 * =========================================================
		 * 			TEST UPDATE CREDIT UTILISATEUR
		 * =========================================================
		*/
		
//		try {
//			DAOFactory.getUtilisateurDAO().modifierCreditUtilisateur(2, 50);
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
		
		
		/*
		 * =========================================================
		 * 			TEST LISTE TOUS LES ARTICLES
		 * =========================================================
		*/
		
		try {
			List<ArticleVendu> lstArticles = DAOFactory.getArticleVenduDAO().getAllArticleVendu();
			lstArticles.forEach(System.out::println);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
