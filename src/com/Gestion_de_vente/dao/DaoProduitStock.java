package com.Gestion_de_vente.dao;

import java.util.List;
import com.Gestion_de_vente.entity.ProduitStock;


public interface DaoProduitStock {

	public List<ProduitStock> findAll();
	
	public ProduitStock findById(int id);
	
	public ProduitStock findByName(String produitStock);
	
	
}
