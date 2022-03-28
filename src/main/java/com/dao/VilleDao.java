package com.dao;

import java.util.ArrayList;

import com.dto.Ville;

public interface VilleDao {
	ArrayList<String> getVille();
	void addVille(Ville ville);
}
