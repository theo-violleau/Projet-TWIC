package com.dto;
import org.springframework.boot.SpringApplication;

public class Ville {
	private String code_commune, nom_commune,code_postale, libelle, ligne5, longitude, latitude;

	public Ville(String code_commune, String nom_commune, String code_postale, String libelle, String ligne5,
			String longitude, String latitude) {
		this.code_commune = code_commune;
		this.nom_commune = nom_commune;
		this.code_postale = code_postale;
		this.libelle = libelle;
		this.ligne5 = ligne5;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getCode_commune() {
		return code_commune;
	}

	public void setCode_commune(String code_commune) {
		this.code_commune = code_commune;
	}

	public String getNom_commune() {
		return nom_commune;
	}

	public void setNom_commune(String nom_commune) {
		this.nom_commune = nom_commune;
	}

	public String getCode_postale() {
		return code_postale;
	}

	public void setCode_postale(String code_postale) {
		this.code_postale = code_postale;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getLigne5() {
		return ligne5;
	}

	public void setLigne5(String ligne5) {
		this.ligne5 = ligne5;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
}
