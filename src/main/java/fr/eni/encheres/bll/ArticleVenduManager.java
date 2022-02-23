/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

/**
 * Interface de l'implémentation article vendu
 */
public interface ArticleVenduManager {
	
	ArticleVendu ajouterUnArticle(ArticleVendu articleAVendre) throws BLLException;
	
	ArticleVendu selectBestEnchereByNoArticle(Integer noArticle) throws BLLException;

	void miseAJourPrixVente(Integer noArticleEnchere, Integer proposition) throws BLLException;
	
	List<ArticleVendu> RecuperationArticleEtUtilisateur() throws BLLException;
	
	List<ArticleVendu> lstFiltreMesVentes(String pseudo, List<Integer> lstCheck, List<ArticleVendu> lstMesVentes);
		
}
