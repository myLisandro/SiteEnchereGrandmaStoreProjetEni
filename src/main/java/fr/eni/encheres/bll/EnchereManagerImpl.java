/**
 * 
 */
package fr.eni.encheres.bll;

import java.time.LocalDate;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;
import fr.eni.encheres.dao.DAOFactory;

public class EnchereManagerImpl implements EnchereManager{

	private static class EnchereManagerImplHolder {
		private static EnchereManagerImpl instance = new EnchereManagerImpl();
	}
	
	private EnchereManagerImpl() {}
	
	public static EnchereManagerImpl getInstance() {
		return EnchereManagerImplHolder.instance;
		
	}
	
	
	@Override
	public void ajouterNouvelleEnchere(Integer noArticle, Integer noEncheriste, Integer montantNouvelleEnchere) throws BLLException  {
		BLLException be = new BLLException();
		
		verificationIdentiteEncheriste(noArticle, noEncheriste, be);
		verificationMontantEnchere(noArticle, montantNouvelleEnchere, be);
		verificationNombrePointEncheriste(montantNouvelleEnchere, noEncheriste, be);
		
		if(be.hasErreur()) {
			throw be;
		}
		
		try {
			DAOFactory.getEnchereDAO().ajouterNouvelleEnchere(noArticle, noEncheriste, montantNouvelleEnchere);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
	}

	/**
	 * Méthode en charge de vérifier l'identité de l'enchériste. Un vendeur ne peut pas enchérir sur son propre article
	 * @param noEncheriste
	 * @param be
	 */
	private void verificationIdentiteEncheriste(Integer noArticle, Integer noEncheriste, BLLException be) throws BLLException{
		ArticleVendu article = selectArticleById(noArticle);
		if(article.getUtilisateur().getNoUtilisateur() == noEncheriste) {
			be.ajouterErreur(new ParameterException("Vous ne pouvez pas enchérir sur votre propre article"));
		}
	}
	
	/**
	 * Méthode en charge de vérifier que la proposition de l'enchériste soit supérieure
	 * au montant de l'enchère maximale actuelle
	 * @param montantNouvelleEnchere
	 * @param be
	 * @throws BLLException 
	 */
	private void verificationMontantEnchere(Integer noArticle, Integer montantNouvelleEnchere, BLLException be) throws BLLException {
		ArticleVendu article = selectArticleById(noArticle);
		
		if(montantNouvelleEnchere <= article.getEnchereMaximum().getMontantEnchere()) {
			be.ajouterErreur(new ParameterException("Le montant de votre proposition doit être supérieure à l'enchère maximale actuelle" ));
		}
		
	}
	
	/**
	 * Méthode en charge de vérifier que l'enchériste possède suffisament de point pour enchérir
	 * @param montantNouvelleEnchere
	 * @param noEncheriste
	 * @param be
	 * @throws BLLException 
	 */
	private void verificationNombrePointEncheriste(Integer montantNouvelleEnchere, Integer noEncheriste,
			BLLException be) throws BLLException {
		Utilisateur encheriste = null;
		try {
			encheriste = DAOFactory.getUtilisateurDAO().selectUtilisateurById(noEncheriste);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		
		if(encheriste.getCredit() < montantNouvelleEnchere) {
			be.ajouterErreur(new ParameterException("Votre crédit est insuffisant pour enchérir"));
		}
	}



	/**
	 * Méthode en charge de remonter un article par son ID
	 * @param noArticle
	 * @return
	 * @throws BLLException
	 */
	public ArticleVendu selectArticleById(Integer noArticle) throws BLLException {
		ArticleVendu article = null;
		try {
			article = DAOFactory.getArticleVenduDAO().selectArticleByIdBestEnchere(noArticle);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException(e);
		}
		return article;
	}
		

}
