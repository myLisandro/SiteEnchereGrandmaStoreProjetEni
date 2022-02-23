/**
 * 
 */
package fr.eni.encheres.dao;

import fr.eni.encheres.bo.Retrait;

public interface RetraitDAO {
	
	void ajouterRetrait(Retrait retrait) throws DALException;

}
