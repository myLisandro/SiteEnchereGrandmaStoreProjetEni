/**
 * 
 */
package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

/**
 * Classe en charge de gérer les articles vendus
 */
public class ArticleVenduManagerImpl implements ArticleVenduManager {

	private static class ArticleVenduManagerHolder {
		private static ArticleVenduManagerImpl instance = new ArticleVenduManagerImpl();
	}

	private ArticleVenduManagerImpl() {
	}

	public static ArticleVenduManagerImpl getInstance() {
		return ArticleVenduManagerHolder.instance;

	}

	@Override
	public ArticleVendu ajouterUnArticle(ArticleVendu articleAVendre) throws BLLException {
		ArticleVendu nouvelArticle;
		try {
			nouvelArticle = DAOFactory.getArticleVenduDAO().ajouterArticleAVendre(articleAVendre);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		return nouvelArticle;
	}

	@Override
	public ArticleVendu selectBestEnchereByNoArticle(Integer noArticle) throws BLLException {
		try {
			return DAOFactory.getArticleVenduDAO().selectArticleByIdBestEnchere(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
	}

	@Override
	public void miseAJourPrixVente(Integer noArticleEnchere, Integer proposition) throws BLLException {
		try {
			DAOFactory.getArticleVenduDAO().updatePrixVente(noArticleEnchere, proposition);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

	}

	@Override
	public List<ArticleVendu> RecuperationArticleEtUtilisateur() throws BLLException {

		try {
			return DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

	}

	/*
	 * ======================================================== 
	 * 				*** TRAITEMENT D'UNE VENTE *** 
	 * ========================================================
	 * 
	 */

	/**
	 * Méthode en charge de mettre à jour l'état des enchères en fonction de la date
	 * du jour
	 * 
	 * @param dateDuJour
	 * @throws BLLException
	 */
	public void actualisationEtatEnchereBDD(LocalDate dateDuJour) throws BLLException {
		List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
		List<ArticleVendu> lstArticlesUpdate = new ArrayList<ArticleVendu>();

		// Récuparation de tous les articles de la BDD
		try {
			lstArticles = DAOFactory.getArticleVenduDAO().getAllArticleVendu();
		} catch (DALException e) {
			e.printStackTrace();
		}

		// Mise à jour des états vente et insertion dans une liste des articles qui
		// devront être mis à jour en BDD
		for (ArticleVendu articleVendu : lstArticles) {
			if ("CREE".equals(articleVendu.getEtatVente()) && (dateDuJour.isAfter(articleVendu.getDateDebutEncheres())
					|| dateDuJour.isEqual(articleVendu.getDateDebutEncheres()))) {
				articleVendu.setEtatVente("ENCOURS");
				lstArticlesUpdate.add(articleVendu);
			}

			if ("ENCOURS".equals(articleVendu.getEtatVente())
					&& dateDuJour.isAfter(articleVendu.getDateFinEncheres())) {
				articleVendu.setEtatVente("CLOTURE");
				lstArticlesUpdate.add(articleVendu);
				ajoutCreditVendeur(articleVendu, articleVendu.getPrixVente());
			}
		}

		// Mise à jour de l'état de vente des articles en BDD

		lstArticlesUpdate.forEach(a -> {
			try {
				DAOFactory.getArticleVenduDAO().updateEtatVente(a);
			} catch (DALException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Méthode en charge de créditer le vendeur à la clôture de l'enchère
	 * 
	 * @param prixVente
	 * @throws BLLException
	 */
	private void ajoutCreditVendeur(ArticleVendu article, Integer prixVente) throws BLLException {
		try {
			DAOFactory.getUtilisateurDAO().modifierCreditUtilisateur(article.getUtilisateur().getNoUtilisateur(),
					prixVente);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

	}

	/*
	 * ======================================================== 
	 * 				*** FILTRES SUR MES ARTICLES *** 
	 * ========================================================
	 * 
	 */

	// Methode qui retourne la liste des articles en cours d'enchère
	public List<ArticleVendu> filtreArticleEncoursParDate(List<ArticleVendu> lstEntree) {
		List<ArticleVendu> lstReturn = new ArrayList<ArticleVendu>();
		for (ArticleVendu articleVendu : lstEntree) {

			if (articleVendu.getDateFinEncheres().isAfter(LocalDate.now())
					& (articleVendu.getDateDebutEncheres().isBefore(LocalDate.now()))
					| articleVendu.getDateDebutEncheres().equals(LocalDate.now())) {
				lstReturn.add(articleVendu);
			}
		}
		return lstReturn;
	}

	// Filtre des articles par catégorie
	public List<ArticleVendu> FiltreSuivantCategorie(String categorie) throws BLLException {
		List<ArticleVendu> lstAReturn = new ArrayList<>();
		try {
			List<ArticleVendu> lstGlobal = DAOFactory.getArticleVenduDAO().selectJointArticleUtilisateur();

			if (!categorie.equals("Toutes")) {

				for (ArticleVendu articleVendu : lstGlobal) {
					if (articleVendu.getCategorieArticle().getLibelle().equals(categorie)) {
						lstAReturn.add(articleVendu);
					}
				}
			} else {
				lstAReturn = lstGlobal;
			}

		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}

		return lstAReturn;
	}

	/**
	 * Méthode en charge de rechercher les articles d'une catégorie par mot clé
	 * 
	 * @param lstEntree
	 * @param motClef
	 * @return liste d'articles triés par catégorie et mot clé
	 */
	public List<ArticleVendu> RechercheDansLeNomDelArticle(List<ArticleVendu> lstEntree, String motClef) {
		List<ArticleVendu> LstIssueDeLaRecherche = new ArrayList<ArticleVendu>();

		for (ArticleVendu articleVendu : lstEntree) {

			if (articleVendu.getNomArticle().toUpperCase().contains(motClef.toUpperCase())) {
				LstIssueDeLaRecherche.add(articleVendu);
			}
		}
		return LstIssueDeLaRecherche;
	}

	/*
	 * ======================================================== 
	 * 				*** FILTRES SUR MES ACHATS *** 
	 * ========================================================
	 * 
	 */
	// Methode pour filtrer par Achat
	public List<ArticleVendu> FiltreListeParArticleAchetable(List<ArticleVendu> lstEntree, String pseudoSession) {
		List<ArticleVendu> lstAretourner = new ArrayList<ArticleVendu>();

		for (ArticleVendu article : lstEntree) {
			if (article.getUtilisateur().getPseudo() != pseudoSession) {
				lstAretourner.add(article);
			}
		}

		return lstAretourner;
	}
	

	//Methode pour filtrer par ventes 
    public List<ArticleVendu> FiltreListeParArticleDeLAcheteur(List<ArticleVendu> lstEntree, String pseudoSession){
        List<ArticleVendu> lstAretourner = new ArrayList<ArticleVendu>();
        
        for (ArticleVendu article : lstEntree) {
            if (article.getUtilisateur().getPseudo().equals(pseudoSession)) {
                lstAretourner.add(article);
            }
        }
        
        return lstAretourner;   
    }

	// Methode pour les checkbox D'Achat

	public List<ArticleVendu> filtreCheckboxAchat(List<Integer> lstCheck, List<ArticleVendu> lstIssuDeRecherche,
			Utilisateur utilisateurSession) {

		System.out.println("je suis dans filtreCheckBoxAchat");
		List<ArticleVendu> lstReturn = new ArrayList<ArticleVendu>();

		for (Integer integer : lstCheck) {
			switch (integer) {
			case 1: // Dans Achat -> Enchère Ouverte
				System.out.println("je suis dans achat case 1");
				List<ArticleVendu> LstTemporaire = new ArrayList<ArticleVendu>();
				for (ArticleVendu articleVendu : lstIssuDeRecherche) {
					if (articleVendu.getUtilisateur().getNoUtilisateur() != (utilisateurSession.getNoUtilisateur())) {
						// je récupère les articles avec le statut ENCOURS et j'exclue les articles que
						// l'utilisateur vend
						LstTemporaire.add(articleVendu);
					}
					lstReturn = filtreArticleEncoursParDate(LstTemporaire);
				}

				break;

			case 2: // Achat -> mes enchères
				System.out.println("je suis dans achat case 2");

				for (ArticleVendu articleVendu : lstIssuDeRecherche) {

					try {
						articleVendu
								.setLstEncheres(DAOFactory.getEnchereDAO().selectAllEncheresByArticle(articleVendu));

					} catch (DALException e) {
						e.printStackTrace();
					}

					for (Enchere enchere : articleVendu.getLstEncheres()) {

						if (enchere.getNoEncheriste() == utilisateurSession.getNoUtilisateur()) {
							// je récupère la liste des articles sur lesquels j'ai enchéris et je check si
							// l'id de l'utilisateur en session est présent si oui j'ajoute à la liste à
							// retourner
							lstReturn.add(articleVendu);
						}
					}
				}

				break;

			case 3:// mes enchères remportées
				System.out.println("je suis dans achat case 3");
				for (ArticleVendu articleVendu : lstIssuDeRecherche) {
					// TODO : ESSAYER de récuperer la BestEnchere en itérant dans la liste puis de
					// set l'articles avec

					ArticleVendu articlestockage = new ArticleVendu();
					try {
						articlestockage = DAOFactory.getArticleVenduDAO()
								.selectArticleByIdBestEnchere(articleVendu.getNoArticle());
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					articleVendu.setEnchereMaximum(articlestockage.getEnchereMaximum());

					if (articleVendu.getDateFinEncheres().isBefore(LocalDate.now()) & articleVendu.getEnchereMaximum()
							.getUtilisateur().getNoUtilisateur() == utilisateurSession.getNoUtilisateur()) {
						// tu comprendras plus tard
						lstReturn.add(articleVendu);
					}
				}
				break;

			default:
				System.out.println("error tu es dans le default du switch de la boucle des choix");
				break;
			}
		}

		List<ArticleVendu> lstDesSurvivants = lstReturn.stream().distinct().collect(Collectors.toList());

		return lstDesSurvivants;

	}

	/*
	 * ======================================================== 
	 * 				*** FILTRES SUR MES VENTES *** 
	 * ========================================================
	 * 
	 */

	public List<ArticleVendu> lstFiltreMesVentes(String pseudo, List<Integer> lstCheck,
			List<ArticleVendu> lstMesVentes) {
		List<ArticleVendu> lstFiltreMesVentes = new ArrayList<ArticleVendu>();

		for (Integer check : lstCheck) {
			switch (check) {
			case 1: // Mes ventes en cours
				lstMesVentesEnCours(pseudo, lstMesVentes).forEach(v -> lstFiltreMesVentes.add(v));
				break;
			case 2: // Mes ventes non débutées
				lstMesVentesNonDebutes(pseudo, lstMesVentes).forEach(v -> lstFiltreMesVentes.add(v));
				break;
			case 3: // Mes ventes terminées
				lstMesVentesTerminees(pseudo, lstMesVentes).forEach(v -> lstFiltreMesVentes.add(v));
				break;

			default:
				System.out.println("tu es dans ton default");
				break;
			}
		}
		// Supression des doublons
		List<ArticleVendu> lstReturn = lstFiltreMesVentes.stream().distinct().collect(Collectors.toList());

		return lstReturn;
	}

	/**
	 * Méthode en charge de retourner la liste de mes ventes en cours
	 * 
	 * @param pseudo
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> lstMesVentesEnCours(String pseudo, List<ArticleVendu> lstEntree) {
		List<ArticleVendu> lstMesVentesEnCours = new ArrayList<ArticleVendu>();
		lstMesVentesEnCours = filtreArticleEncoursParDate(lstEntree);
		return lstMesVentesEnCours.stream().filter(a -> pseudo.equals(a.getUtilisateur().getPseudo()))
				.collect(Collectors.toList());
	}

	/**
	 * Méthode en charge de retourner la liste de mes ventes non débutées
	 * 
	 * @param pseudo
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> lstMesVentesNonDebutes(String pseudo, List<ArticleVendu> lstEntree) {
		return lstEntree.stream().filter(
				a -> a.getDateDebutEncheres().isAfter(LocalDate.now()) & pseudo.equals(a.getUtilisateur().getPseudo()))
				.collect(Collectors.toList());
	}

	/**
	 * Méthode en charge de retourner la liste de mes ventes terminées
	 * 
	 * @param pseudo
	 * @return
	 * @throws BLLException
	 */
	public List<ArticleVendu> lstMesVentesTerminees(String pseudo, List<ArticleVendu> lstEntree) {
		return lstEntree.stream().filter(
				a -> a.getDateFinEncheres().isBefore(LocalDate.now()) & pseudo.equals(a.getUtilisateur().getPseudo()))
				.collect(Collectors.toList());
	}

}