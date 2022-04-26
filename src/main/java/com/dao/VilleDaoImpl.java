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
	private ArrayList<Ville> villes = new ArrayList<Ville>(); 
	@Override
	public ArrayList<Ville> getVille() {
		try {
			Connection connexion = daoFactory.getConnection();
			
			// Récupération de l'id du match
			PreparedStatement requete_id = connexion.prepareStatement("SELECT * FROM ville_france;");
			ResultSet result = requete_id.executeQuery();
			while (result.next()) {
				String code_commune = result.getString("Code_commune_INSEE"); 
				String nom_commune = result.getString("Nom_commune");
				String code_postale = result.getString("Code_postal");
				String libelle = result.getString("Libelle_acheminement");
				String ligne5 = result.getString("Ligne_5");
				String longitude = result.getString("Longitude");
				String latitude = result.getString("Latitude");
				
		        villes.add(new Ville(code_commune, nom_commune,code_postale, libelle, ligne5, longitude, latitude));
		       
		      }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return villes;		
	}
	
	

	@Override
	public void addVille(Ville ville) {
		try {
			Connection connexion = daoFactory.getConnection();
//			Création d'une nouvelle ville dans la BDD
			PreparedStatement requete = connexion.prepareStatement("INSERT INTO ville_france(Code_commune_INSEE, Nom_commune, Code_postal,"
					+ "Libelle_acheminement, Ligne_5, Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?);");
			requete.setString(1, ville.getCode_commune());
			requete.setString(2, ville.getNom_commune());
			requete.setString(3, ville.getCode_postale());
			requete.setString(4, ville.getLibelle());
			requete.setString(5, ville.getLigne5());
			requete.setString(6, ville.getLatitude());
			requete.setString(7, ville.getLongitude());
			requete.executeUpdate();
			System.out.println("Création de la nouvelle ville");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifVille(String codeCommune, String nomCommune, String codePostal, 
			String libelle, String ligne5, String latitude, String longitude) {
		try {
			Connection connexion = daoFactory.getConnection();
			String statement ="UPDATE ville_france SET ";
			if(nomCommune != null) {
				statement+= "Nom_commune = '"+nomCommune+"',";
			}
			if(codePostal != null) {
				statement+= "Code_postal = '"+codePostal+"',";
			}
			if(libelle != null) {
				statement+= "Libelle_acheminement = '"+libelle+"',";
			}
			if(ligne5 != null) {
				statement+= "Ligne_5 = '"+ligne5+"',";
			}
			if(latitude != null) {
				statement+= "Latitude = '"+latitude+"',";
			}
			if(longitude != null) {
				statement+= "Longitude = '"+longitude+"',";
			}
//			On enlève la virgule de fin de requête
			String pattern = statement.substring(statement.length()-1);
			if(",".equalsIgnoreCase(pattern)) {
				statement = statement.substring(0, statement.length()-1);
				
			}
			statement = statement+" WHERE Code_commune_INSEE = '"+codeCommune+"';";
			PreparedStatement requete = connexion.prepareStatement(statement);
			requete.executeUpdate();
			System.out.println("Modification de la ville");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void supprimerVille(String codeCommune) {
		try {
			Connection connexion = daoFactory.getConnection();
			String statement ="DELETE FROM ville_france WHERE Code_commune_INSEE = '"+codeCommune+"';";
			PreparedStatement requete = connexion.prepareStatement(statement);
			requete.executeUpdate();
			System.out.println("Supression de la ville");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}



	@Override
	public ArrayList<Ville> getVilleFiltre(String codeCommune, String nomCommune, String codePostal, 
			String libelle, String ligne5, String latitude, String longitude) {
		try {
			Connection connexion = daoFactory.getConnection();
			String statement ="SELECT * FROM ville_france WHERE ";
			
			if(codeCommune != null) {
				statement+= "Code_commune_INSEE LIKE '%"+codeCommune+"%' and ";
			}
			if(nomCommune != null) {
				statement+= "Nom_commune LIKE '%"+nomCommune+"%' and ";
			}
			if(codePostal != null) {
				statement+= "Code_postal LIKE '%"+codePostal+"%' and ";
			}
			if(libelle != null) {
				statement+= "Libelle_acheminement LIKE '%"+libelle+"%' and ";
			}
			if(ligne5 != null) {
				statement+= "Ligne_5 LIKE '%"+ligne5+"%' and ";
			}
			if(latitude != null) {
				statement+= "Latitude LIKE '%"+latitude+"%' and ";
			}
			if(longitude != null) {
				statement+= "Longitude LIKE '%"+longitude+"%' and ";
			}
			
//			On enlève le and de fin de requête
			String pattern = statement.substring(statement.length()-4);
			if("and ".equalsIgnoreCase(pattern)) {
				statement = statement.substring(0, statement.length()-4);
			}
			statement+=";";
			PreparedStatement requete_id = connexion.prepareStatement(statement);
			ResultSet result = requete_id.executeQuery();
			while (result.next()) {
				String code_commune = result.getString("Code_commune_INSEE"); 
				String nom_commune = result.getString("Nom_commune");
				String code_postale = result.getString("Code_postal");
				String libelle1 = result.getString("Libelle_acheminement");
				String ligne51 = result.getString("Ligne_5");
				String longitude1 = result.getString("Longitude");
				String latitude1 = result.getString("Latitude");
				
		        villes.add(new Ville(code_commune, nom_commune,code_postale, libelle1, ligne51, longitude1, latitude1));
		    
		        System.out.println(villes.get(villes.size()-1).getNom_commune());
		      
		      }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return villes;		
	}
	
}
