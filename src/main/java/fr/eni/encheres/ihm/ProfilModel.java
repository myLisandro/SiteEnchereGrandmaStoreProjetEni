package fr.eni.encheres.ihm;

import fr.eni.encheres.bo.Utilisateur;

public class ProfilModel {
	
	Utilisateur utilisateur;

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ProfilModel(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}
	
	public ProfilModel() {
	}
	

}
