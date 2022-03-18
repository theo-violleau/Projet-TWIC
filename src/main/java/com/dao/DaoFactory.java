package com.dao;

import java.sql.*;

public class DaoFactory {
	private String url;
	private String utilisateur;
	private String mot_de_passe;
	
	/**
	 * @param url
	 * @param utilisateur
	 * @param mot_de_passe
	 */
	protected DaoFactory(String url, String utilisateur, String mot_de_passe) {
		this.url = url;
		this.utilisateur = utilisateur;
		this.mot_de_passe = mot_de_passe;
	}
	
	/**
	 * Vérifier le jdbc et récupérer une instance de DaoFactory
	 * @return
	 */
	public static DaoFactory getInstance() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return new DaoFactory("jdbc:mariadb://localhost:3306/mavensql", "app", "root");
		 
	}
	
	/**
	 * Obtenir la connexion à la base de données
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.url, this.utilisateur, this.mot_de_passe);
	}
	
	/**
	 * Récupérer le DAO
	 * @return
	 */
	public VilleDao getVilleDao() {
		return new VilleDaoImpl(this);
	}
	
}
