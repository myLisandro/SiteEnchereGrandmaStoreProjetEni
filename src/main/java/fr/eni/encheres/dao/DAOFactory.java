/**
 * 
 */
package fr.eni.encheres.dao;

import fr.eni.encheres.dao.jdbc.ArticleVenduDAOImpl;
import fr.eni.encheres.dao.jdbc.CategorieDAOImpl;
import fr.eni.encheres.dao.jdbc.EnchereDAOImpl;
import fr.eni.encheres.dao.jdbc.RetraitDAOImpl;
import fr.eni.encheres.dao.jdbc.UtilisateurDAOImpl;


public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}
	
	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOImpl();
	}

	
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOImpl();
	}
}
