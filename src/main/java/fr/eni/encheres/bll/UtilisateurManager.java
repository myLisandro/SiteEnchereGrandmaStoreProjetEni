/**
 * 
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dao.DALException;

/**
 * Interface de la BLL  
 */
public interface UtilisateurManager {
	
	Utilisateur getByIdUtilisateur (int IdUtilisateur) throws BLLException;
	
	Utilisateur getByPseudoUtilisateur (String pseudoUtilisateur) throws BLLException;
	
	Utilisateur ajouterNouvelUtilisateur(Utilisateur utilisateur) throws BLLException;
	
	void modifierUtilisateur(Utilisateur utilisateur, Utilisateur utilisateurModif) throws BLLException;
	
	void supprimerUtilisateur(Integer idUtilisateur) throws BLLException;
	
	void modifierCreditEncheriste(Utilisateur utilisateur, Integer montantEnchere) throws BLLException;

	void modifierCreditAncienEncheriste(Utilisateur ancienEncheriste, Integer enchereMax) throws BLLException;
}
