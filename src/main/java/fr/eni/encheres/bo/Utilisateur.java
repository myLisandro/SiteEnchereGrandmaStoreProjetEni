/**
 * 
 */
package fr.eni.encheres.bo;

import java.util.List;

/**
 * Classe en charge de créer un objet utilisateur
 *
 */

public class Utilisateur {
	Integer noUtilisateur;
	String pseudo;
	String nom;
	String prenom;
	String email;
	String telephone;
	String rue;
	String codePostal;
	String ville;
	String motDePasse;
	Integer credit;
	Boolean administrateur;
	List<ArticleVendu> lstVentes;
	List<Enchere> lstEncheres;
	
	/**
	 * Constructeur.
	 */
	public Utilisateur() {
	}
	
	/**
	 * Constructeur.
	 * @param pseudo
	 */
	public Utilisateur(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * Constructeur.
	 * @param noUtilisateur
	 * @param pseudo
	 */
	public Utilisateur(Integer noUtilisateur,String pseudo) {
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
	}
	/**
	 * Constructeur.
	 * @param noUtilisateur
	 */
	public Utilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	/**
	 * Constructeur.
	 * @param email
	 * @param motDePasse
	 */
	
	public Utilisateur(String email, String motDePasse) {
		this.email = email;
		this.motDePasse = motDePasse;
	}

	/**
	 * Constructeur.
	 * @param pseudo
	 * @param lstVentes
	 */
	public Utilisateur(String pseudo, List<ArticleVendu> lstVentes) {
		this.pseudo = pseudo;
		this.lstVentes = lstVentes;
	}

	/**
	 * Constructeur.
	 * @param pseudo
	 * @param email
	 * @param motDePasse
	 */
	public Utilisateur(String pseudo, String email, String motDePasse) {
		this.pseudo = pseudo;
		this.email = email;
		this.motDePasse = motDePasse;
	}

	/**
	 * Constructeur.
	 * @param noUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param administrateur
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email,
			String rue, String codePostal, String ville, String motDePasse, Integer credit, Boolean administrateur) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}
	/**
	 * Constructeur.
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param administrateur
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, Integer credit, Boolean administrateur) {
		this(pseudo, nom, prenom, email, rue, codePostal, ville, motDePasse, credit, administrateur);
		this.telephone = telephone;
	}

	/**
	 * Constructeur.
	 * @param noUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param administrateur
	 */
	public Utilisateur(Integer noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, Integer credit, Boolean administrateur) {
		this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		this.noUtilisateur = noUtilisateur;
	}
	
	/**
	 * Getter pour noUtilisateur.
	 * @return the noUtilisateur
	 */
	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}
	/**
	 * Setter pour noUtilisateur.
	 * @param noUtilisateur the noUtilisateur to set
	 */
	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	/**
	 * Getter pour pseudo.
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * Setter pour pseudo.
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	/**
	 * Getter pour nom.
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Setter pour nom.
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Getter pour prenom.
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * Setter pour prenom.
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * Getter pour email.
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter pour email.
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Getter pour telephone.
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * Setter pour telephone.
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * Getter pour rue.
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * Setter pour rue.
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * Getter pour codePostal.
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * Setter pour codePostal.
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * Getter pour ville.
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * Setter pour ville.
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * Getter pour motDePasse.
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	/**
	 * Setter pour motDePasse.
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	/**
	 * Getter pour credit.
	 * @return the credit
	 */
	public Integer getCredit() {
		return credit;
	}
	/**
	 * Setter pour credit.
	 * @param credit the credit to set
	 */
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	/**
	 * Getter pour administrateur.
	 * @return the administrateur
	 */
	public Boolean getAdministrateur() {
		return administrateur;
	}
	/**
	 * Setter pour administrateur.
	 * @param administrateur the administrateur to set
	 */
	public void setAdministrateur(Boolean administrateur) {
		this.administrateur = administrateur;
	}
	/**
	 * Getter pour lstVentes.
	 * @return the lstVentes
	 */
	public List<ArticleVendu> getLstVentes() {
		return lstVentes;
	}
	/**
	 * Setter pour lstVentes.
	 * @param lstVentes the lstVentes to set
	 */
	public void setLstVentes(List<ArticleVendu> lstVentes) {
		this.lstVentes = lstVentes;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Utilisateur [noUtilisateur=").append(noUtilisateur).append(", pseudo=").append(pseudo)
				.append(", nom=").append(nom).append(", prenom=").append(prenom).append(", email=").append(email)
				.append(", telephone=").append(telephone).append(", rue=").append(rue).append(", codePostal=")
				.append(codePostal).append(", ville=").append(ville).append(", motDePasse=").append(motDePasse)
				.append(", credit=").append(credit).append(", administrateur=").append(administrateur)
				.append(", lstVentes=").append(lstVentes).append(", lstEncheres=").append(lstEncheres).append("]");
		return builder.toString();
	}
	
	

}
