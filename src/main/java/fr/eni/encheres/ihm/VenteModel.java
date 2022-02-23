/**
 * 
 */
package fr.eni.encheres.ihm;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;

/**
 * Classe en charge de définir un model pour la vente d'un article
 */
public class VenteModel {
	ArticleVendu article;
	Retrait retrait;
	Categorie categorie;
	/**
	 * Constructeur.
	 */
	public VenteModel() {
	}
	/**
	 * Constructeur.
	 * @param article
	 * @param retrait
	 * @param categorie
	 */
	public VenteModel(ArticleVendu article, Retrait retrait, Categorie categorie) {
		super();
		this.article = article;
		this.retrait = retrait;
		this.categorie = categorie;
	}
	/**
	 * Getter pour article.
	 * @return the article
	 */
	public ArticleVendu getArticle() {
		return article;
	}
	/**
	 * Setter pour article.
	 * @param article the article to set
	 */
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	/**
	 * Getter pour retrait.
	 * @return the retrait
	 */
	public Retrait getRetrait() {
		return retrait;
	}
	/**
	 * Setter pour retrait.
	 * @param retrait the retrait to set
	 */
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}
	/**
	 * Getter pour categorie.
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	/**
	 * Setter pour categorie.
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

}
