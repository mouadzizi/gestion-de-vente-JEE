package com.Gestion_de_vente.dao;

import java.util.List;
import com.Gestion_de_vente.entity.Commandes;

public interface DaoCommandes {

	public void save(Commandes c);
	
	public void remove(Commandes c);
	
	public void modify(Commandes c);

	public List<Commandes> findAll();
	
	public Commandes findById(int id);
}
