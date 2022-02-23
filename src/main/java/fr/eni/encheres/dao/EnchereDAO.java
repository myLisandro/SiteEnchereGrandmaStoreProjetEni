/**
 * 
 */
package fr.eni.encheres.dao;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public interface EnchereDAO {


	void ajouterNouvelleEnchere(Integer noArticle, Integer noEncheriste, Integer montantNouvelleEnchere) throws DALException;
	List<Enchere> selectAllEncheresByUser(Utilisateur utilisateur) throws DALException;
	List<Enchere> selectAllEncheresByArticle(ArticleVendu article) throws DALException;
}
