package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDao {
	ArrayList<String> getVille();
	void addVille(String code_commune, String nom_commune, String code_postale, String libelle, String ligne5,
			String longitude, String latitude);
}
