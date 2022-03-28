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
	public void addVille(Ville ville) {
		try {
			Connection connexion = daoFactory.getConnection();
//			Création d'une nouvelle ville dans la BDD
			PreparedStatement requete = connexion.prepareStatement("INSERT INTO Ville(Code_commune_INSEE, Nom_commune, Code_postal,"
					+ "Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?);");
			requete.setString(1, ville.getCode_commune());
			requete.setString(2, ville.getNom_commune());
			requete.setString(3, ville.getCode_postale());
			requete.setString(4, ville.getLibelle());
			requete.setString(5, ville.getLigne5());
			requete.setString(6, ville.getLatitude());
			requete.setString(7, ville.getLongitude());
			requete.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
