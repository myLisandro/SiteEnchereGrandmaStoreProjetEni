/**
 * 
 */
package fr.eni.encheres.bll;

public interface EnchereManager {

	void ajouterNouvelleEnchere(Integer noArticle, Integer noEncheriste, Integer montantNouvelleEnchere)
			throws BLLException;
}
