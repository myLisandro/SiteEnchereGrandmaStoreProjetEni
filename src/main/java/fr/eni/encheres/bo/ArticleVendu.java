/**
 * 
 */
package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe en charge de créer un objet articleVendu
 *
 */
public class ArticleVendu {
	
	Integer noArticle;
	String nomArticle;
	String description;
	LocalDate dateDebutEncheres;
	LocalDate dateFinEncheres;
	Integer miseAPrix;
	Integer prixVente;
	String etatVente;
	Enchere enchereMaximum;
	Utilisateur utilisateur;
	List<Enchere> lstEncheres;
	Categorie categorieArticle;
	Retrait lieuRetrait;
	/**
	 * Constructeur.
	 */
	public ArticleVendu() {
	}
	
	/**
	 * Constructeur.
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param etatVente
	 * @param utilisateur
	 * @param categorieArticle
	 * @param lieuRetrait
	 */
	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDate dateFinEncheres,
			Integer miseAPrix, String etatVente, Utilisateur utilisateur, Categorie categorieArticle,
			Retrait lieuRetrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
		this.categorieArticle = categorieArticle;
		this.lieuRetrait = lieuRetrait;
	}
	/**
	 * Constructeur.
	 * @param noArticle
	 * @param nomArticle
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param prixVente
	 * @param etatVente
	 * @param utilisateur
	 */
	public ArticleVendu(Integer noArticle, String nomArticle, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer prixVente, String etatVente, Utilisateur utilisateur) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateur = utilisateur;
	}
	/**
	 * Constructeur.
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param utilisateur
	 * @param categorieArticle
	 * @param lieuRetrait
	 */
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Integer miseAPrix, Utilisateur utilisateur, Categorie categorieArticle, Retrait lieuRetrait) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.utilisateur = utilisateur;
		this.categorieArticle = categorieArticle;
		this.lieuRetrait = lieuRetrait;
	}
	/**
	 * Constructeur.
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param utilisateur
	 * @param categorieArticle
	 * @param lieuRetrait
	 */
	public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,LocalDate dateFinEncheres,
			Integer miseAPrix, Utilisateur utilisateur, Categorie categorieArticle, Retrait lieuRetrait) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, utilisateur, categorieArticle, lieuRetrait);
		this.noArticle = noArticle;
	}
	
	/**
	 * Getter pour enchereMaximum.
	 * @return the enchereMaximum
	 */
	public Enchere getEnchereMaximum() {
		return enchereMaximum;
	}
	/**
	 * Setter pour enchereMaximum.
	 * @param enchereMaximum the enchereMaximum to set
	 */
	public void setEnchereMaximum(Enchere enchereMaximum) {
		this.enchereMaximum = enchereMaximum;
	}
	/**
	 * Getter pour noArticle.
	 * @return the noArticle
	 */
	public Integer getNoArticle() {
		return noArticle;
	}
	/**
	 * Setter pour noArticle.
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	/**
	 * Getter pour nomArticle.
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}
	/**
	 * Setter pour nomArticle.
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	/**
	 * Getter pour description.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Setter pour description.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Getter pour dateDebutEncheres.
	 * @return the dateDebutEncheres
	 */
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	/**
	 * Setter pour dateDebutEncheres.
	 * @param dateDebutEncheres the dateDebutEncheres to set
	 */
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	/**
	 * Getter pour dateFinEncheres.
	 * @return the dateFinEncheres
	 */
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	/**
	 * Setter pour dateFinEncheres.
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	/**
	 * Getter pour miseAPrix.
	 * @return the miseAPrix
	 */
	public Integer getMiseAPrix() {
		return miseAPrix;
	}
	/**
	 * Setter pour miseAPrix.
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(Integer miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	/**
	 * Getter pour prixVente.
	 * @return the prixVente
	 */
	public Integer getPrixVente() {
		return prixVente;
	}
	/**
	 * Setter pour prixVente.
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}
	/**
	 * Getter pour etatVente.
	 * @return the etatVente
	 */
	public String getEtatVente() {
		return etatVente;
	}
	/**
	 * Setter pour etatVente.
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
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
	/**
	 * Getter pour lstEncheres.
	 * @return the lstEncheres
	 */
	public List<Enchere> getLstEncheres() {
		return lstEncheres;
	}
	/**
	 * Setter pour lstEncheres.
	 * @param lstEncheres the lstEncheres to set
	 */
	public void setLstEncheres(List<Enchere> lstEncheres) {
		this.lstEncheres = lstEncheres;
	}
	/**
	 * Getter pour categorieArticle.
	 * @return the categorieArticle
	 */
	public Categorie getCategorieArticle() {
		return categorieArticle;
	}
	/**
	 * Setter pour categorieArticle.
	 * @param categorieArticle the categorieArticle to set
	 */
	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}
	/**
	 * Getter pour lieuRetrait.
	 * @return the lieuRetrait
	 */
	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}
	/**
	 * Setter pour lieuRetrait.
	 * @param lieuRetrait the lieuRetrait to set
	 */
	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleVendu [noArticle=").append(noArticle).append(", nomArticle=").append(nomArticle)
				.append(", description=").append(description).append(", dateDebutEncheres=").append(dateDebutEncheres)
				.append(", dateFinEncheres=").append(dateFinEncheres).append(", miseAPrix=").append(miseAPrix)
				.append(", prixVente=").append(prixVente).append(", etatVente=").append(etatVente)
				.append(", utilisateur=").append(utilisateur)
				.append(", categorieArticle=").append(categorieArticle).append(", lieuRetrait=").append(lieuRetrait)
				.append("]");
		return builder.toString();
	}

	

}
