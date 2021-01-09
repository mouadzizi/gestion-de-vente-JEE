package com.gestionDeVente.dao;

import java.util.List;

import com.gestionDeVente.entities.ProduitsStock;

public interface IDAOPStock {

	public List<ProduitsStock> findAll();
	
	public ProduitsStock findById(int id);
	
	public ProduitsStock findByName(String produitStock);
	
	public void update(ProduitsStock produitStock);
	
}