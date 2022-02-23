/**
 * 
 */
package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Categorie;

public interface CategorieManager {
	
	List<Categorie> listeDesCategories() throws BLLException;
	
	void ajouterCategorie(Categorie categorie) throws BLLException;

}
