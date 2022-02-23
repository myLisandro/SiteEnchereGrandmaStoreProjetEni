/**
 * 
 */
package fr.eni.encheres.ihm;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;

public class PageEnchereModel {
	ArticleVendu article;
	Utilisateur vendeur;
	Utilisateur encheriste;
	String message;
	/**
	 * Constructeur.
	 */
	public PageEnchereModel() {
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
	 * Getter pour vendeur.
	 * @return the vendeur
	 */
	public Utilisateur getVendeur() {
		return vendeur;
	}
	/**
	 * Setter pour vendeur.
	 * @param vendeur the vendeur to set
	 */
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	/**
	 * Getter pour message.
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Setter pour message.
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * Getter pour encheriste.
	 * @return the encheriste
	 */
	public Utilisateur getEncheriste() {
		return encheriste;
	}
	/**
	 * Setter pour encheriste.
	 * @param encheriste the encheriste to set
	 */
	public void setEncheriste(Utilisateur encheriste) {
		this.encheriste = encheriste;
	}
	

}
