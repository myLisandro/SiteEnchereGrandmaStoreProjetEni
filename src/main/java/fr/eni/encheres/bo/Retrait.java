/**
 * 
 */
package fr.eni.encheres.bo;

/**
 * Classe en charge de créer un objet retrait
 *  
 */
public class Retrait {
	String lieu;
	String codePostal;
	String ville;
	ArticleVendu articleVendu;
	/**
	 * Constructeur.
	 */
	public Retrait() {
	}

	/**
	 * Constructeur.
	 * @param lieu
	 * @param codePostal
	 * @param ville
	 * @param articleVendu
	 */
	public Retrait(String lieu, String codePostal, String ville, ArticleVendu articleVendu) {
		this(lieu, codePostal, ville);
		this.articleVendu = articleVendu;
	}

	/**
	 * Constructeur.
	 * @param lieu
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(String lieu, String codePostal, String ville) {
		this.lieu = lieu;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	/**
	 * Getter pour lieu.
	 * @return the lieu
	 */
	public String getLieu() {
		return lieu;
	}
	/**
	 * Setter pour lieu.
	 * @param lieu the lieu to set
	 */
	public void setLieu(String lieu) {
		this.lieu = lieu;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Retrait [lieu=").append(lieu).append(", codePostal=").append(codePostal).append(", ville=")
				.append(ville).append(", articleVendu=").append(articleVendu).append("]");
		return builder.toString();
	}
	
}
