/**
 * 
 */
package fr.eni.encheres.bo;

import java.time.LocalDateTime;

/**
 * Classe en charge de créer un objet enchères
 *
 */
public class Enchere {
	LocalDateTime dateEnchere;
	Integer montantEnchere;
	ArticleVendu articleVendu;
	Utilisateur utilisateur;
	Integer noEncheriste;
	


	/**
	 * Constructeur.
	 */
	public Enchere() {
	}
	/**
	 * Constructeur.
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param articleVendu
	 * @param noEncheriste
	 */
	public Enchere(LocalDateTime dateEnchere, Integer montantEnchere, ArticleVendu articleVendu, Integer noEncheriste) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.articleVendu = articleVendu;
		this.noEncheriste = noEncheriste;
	}

	/**
	 * Constructeur.
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param utilisateur
	 */
	public Enchere(LocalDateTime dateEnchere, Integer montantEnchere, Utilisateur utilisateur) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.utilisateur = utilisateur;
	}

	/**
	 * Constructeur.
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param articleVendu
	 * @param utilisateur
	 */
	public Enchere(Integer montantEnchere, ArticleVendu articleVendu, Utilisateur utilisateur) {
		this.montantEnchere = montantEnchere;
		this.articleVendu = articleVendu;
		this.utilisateur = utilisateur;
	}
	/**
	 * Constructeur.
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param articleVendu
	 * @param utilisateur
	 */
	public Enchere(LocalDateTime dateEnchere, Integer montantEnchere, ArticleVendu articleVendu, Utilisateur utilisateur) {
		this(montantEnchere, articleVendu, utilisateur);
		this.dateEnchere = dateEnchere;
	}
	/**
	 * Getter pour dateEnchere.
	 * @return the dateEnchere
	 */
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}
	/**
	 * Setter pour dateEnchere.
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	/**
	 * Getter pour montantEnchere.
	 * @return the montantEnchere
	 */
	public Integer getMontantEnchere() {
		return montantEnchere;
	}
	/**
	 * Setter pour montantEnchere.
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	/**
	 * Getter pour articleVendu.
	 * @return the articleVendu
	 */
	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}
	/**
	 * Setter pour articleVendu.
	 * @param articleVendu the articleVendu to set
	 */
	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}
	/**
	 * Getter pour utilisateur.
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	/**
	 * Setter pour utilisateur.
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public Integer getNoEncheriste() {
		return noEncheriste;
	}
	
	
	public void setNoEncheriste(Integer noEncheriste) {
		this.noEncheriste = noEncheriste;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [dateEnchere=").append(dateEnchere).append(", montantEnchere=").append(montantEnchere)
				.append(", articleVendu=").append(articleVendu).append(", utilisateur=").append(utilisateur)
				.append("]");
		return builder.toString();
	}
	

}
