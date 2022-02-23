/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Retrait;

public interface RetraitManager {
	
	void ajouterNouveauRetrait(Retrait retrait) throws BLLException;

}
