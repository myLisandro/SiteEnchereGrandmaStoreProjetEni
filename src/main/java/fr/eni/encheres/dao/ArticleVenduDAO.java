package fr.eni.encheres.dao;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	ArticleVendu ajouterArticleAVendre(ArticleVendu articleVendu) throws DALException;
	
	List<ArticleVendu> getAllArticleVendu() throws DALException;
	
	List<ArticleVendu> selectJointArticleUtilisateur() throws DALException;
	
	ArticleVendu selectArticleByIdBestEnchere(Integer idArticle) throws DALException;
	
	void updatePrixVente(Integer noArticleEnchere, Integer proposition) throws DALException;

	void updateEtatVente(ArticleVendu article) throws DALException;
	
}
