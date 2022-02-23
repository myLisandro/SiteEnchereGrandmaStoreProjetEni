/**
 * 
 */
package fr.eni.encheres.dao;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Interface Utilisateur DAO
 */
public interface UtilisateurDAO {
	void ajouterUtilisateur(Utilisateur utilisateur) throws DALException;
	
	List<Utilisateur> getAllUtilisateurs() throws DALException;
	
	Utilisateur selectUtilisateurById(Integer no_utilisateur) throws DALException;
	
	Utilisateur getByPseudo(String pseudoUtilisateur) throws DALException;
	
	void modifierUtilisateur(Utilisateur utilisateurModif) throws DALException;
	
	void supprimerUtilisateur(Integer idUtilisateur) throws DALException;
	
	void modifierCreditUtilisateur(Integer noUtilisateur, Integer montantCredit) throws DALException;

}
