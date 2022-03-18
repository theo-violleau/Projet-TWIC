package com.dao;


import java.sql.*;
import java.util.ArrayList;

import com.dto.Ville;

public class VilleDaoImpl implements VilleDao {
	private DaoFactory daoFactory;
	
	/**
	 * @param daoFactory
	 */
	protected VilleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	private ArrayList<String> nom_ville = new ArrayList<String>(); 
	@Override
	public ArrayList<String> getVille() {
		try {
			Connection connexion = daoFactory.getConnection();
			
			// Récupération de l'id du match
			PreparedStatement requete_id = connexion.prepareStatement("SELECT * FROM ville_france;");
			ResultSet result = requete_id.executeQuery();
			while (result.next()) {
				String firstName = result.getString("Nom_commune");
		        nom_ville.add(firstName);
		        
		        // print the results
		        System.out.format("%s,\n",firstName);
		      }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nom_ville;		
	}

	@Override
	public void addVille(String code_commune, String nom_commune, String code_postale, String libelle, String ligne5,
			String longitude, String latitude) {
		// TODO Auto-generated method stub
		
	}
	
}
