package com.gestionDeVente.dao;

import java.util.List;

import com.gestionDeVente.entities.Commandes;

public interface IDAOCommandes {

	public List<Commandes> findAll();
	
	public void save(Commandes c);
	
	public List<Commandes> findByUser(String login);
	
	public Commandes findById(int id);
	
	public void remove(Commandes c);
	
}
