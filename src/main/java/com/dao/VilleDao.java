package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDao {
	ArrayList<String> getVille();
	ArrayList<String> getVilleFiltre(String codeCommune, String nomCommune, String codePostal, 
			String libelle, String ligne5, String latitude, String longitude);
	void addVille(Ville ville);
	void modifVille(String codeCommune, String nomCommune, String codePostal, 
			String libelle, String ligne5, String latitude, String longitude);
	void supprimerVille(String codeCommune);
}
