package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoFactory;
import com.dao.VilleDao;
import com.dao.VilleDaoImpl;

@RestController
public class VilleController {
		
	private VilleDao villeDao;
	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public String get(@RequestParam(required  = false, value="codePostal") String codePostal) {
		System.out.println("get");
		// TODO : mon code vers la BDD
		this.villeDao = DaoFactory.getInstance().getVilleDao();
		return afficher(villeDao.getVille());
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

}