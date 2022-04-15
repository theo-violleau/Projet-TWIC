package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoFactory;
import com.dao.VilleDao;
import com.dao.VilleDaoImpl;
import com.dto.Ville;

@RestController
public class VilleController {
	private HashMap<String, Object> listeVilles;
	private VilleDao villeDao;
	private Ville ville;
	
	// Fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public String get(@RequestParam(required  = false, value="Code_commune_INSEE") String codeCommune, @RequestParam(required  = false, value="Nom_commune") String nomCommune,
			@RequestParam(required  = false, value="Code_postal") String codePostal, @RequestParam(required  = false, value="Libelle_acheminement") String libelle,
			@RequestParam(required  = false, value="Ligne_5") String ligne5, @RequestParam(required  = false, value="Latitude") String latitude, 
			@RequestParam(required  = false, value="Longitude") String longitude) {
		System.out.println("get");
		// TODO : mon code vers la BDD
		if(codeCommune != null || nomCommune != null || codePostal != null || libelle != null || 
				ligne5 != null || latitude != null || longitude != null) {
			this.villeDao = DaoFactory.getInstance().getVilleDao();
			return afficher(villeDao.getVilleFiltre(codeCommune, nomCommune, codePostal,libelle, ligne5, latitude, longitude));
		}
		else {
			this.villeDao = DaoFactory.getInstance().getVilleDao();
			return afficher(villeDao.getVille());
		}
	}
	
	
	
	private String afficher(ArrayList<String> array) {
		String result = "";
		for(String str : array){
			result += "<li>";
			result += str;
			result += "</li> ";
		}
		return result;
	}
	// TODO : 
	// fonction pour enregistrer un element dans la BDD
	@RequestMapping(value="/ville", method=RequestMethod.POST,consumes = "application/json")
	public void post(@RequestBody Map<String, Object> liste) {
		System.out.println("post");
		String ligne_5;
		if(liste.get("Ligne_5") == null) {
			ligne_5 = "";
		}
		else {
			ligne_5 = liste.get("Ligne_5").toString();
		}
		ville = new Ville(liste.get("Code_commune_INSEE").toString(),liste.get("Nom_commune").toString(), liste.get("Code_postal").toString(), liste.get("Libelle_acheminement").toString(),
				ligne_5, liste.get("Latitude").toString(), liste.get("Longitude").toString());
		DaoFactory.getInstance().getVilleDao().addVille(ville);
	}
	
	// fonction pour modifier un element dans la BDD
		@RequestMapping(value="/ville", method=RequestMethod.PUT,consumes = "application/json")
		public void put(@RequestParam(required  = true, value="Code_commune_INSEE") String codeCommune, @RequestParam(required  = false, value="Nom_commune") String nomCommune,
				@RequestParam(required  = false, value="Code_postal") String codePostal, @RequestParam(required  = false, value="Libelle_acheminement") String libelle,
				@RequestParam(required  = false, value="Ligne_5") String ligne5, @RequestParam(required  = false, value="Latitude") String latitude, 
				@RequestParam(required  = false, value="Longitude") String longitude) {
			System.out.println("put");
			DaoFactory.getInstance().getVilleDao().modifVille(codeCommune, nomCommune, codePostal,libelle, ligne5, latitude, longitude);		
		}
		
	// fonction pour supprimer un element dans la BDD
			@RequestMapping(value="/ville", method=RequestMethod.DELETE,consumes = "application/json")
			public void put(@RequestParam(required  = true, value="Code_commune_INSEE") String codeCommune) {
				System.out.println("put");
				DaoFactory.getInstance().getVilleDao().supprimerVille(codeCommune);	
			}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	

}