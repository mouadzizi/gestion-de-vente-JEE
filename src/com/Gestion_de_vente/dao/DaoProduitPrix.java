package com.Gestion_de_vente.dao;

import com.Gestion_de_vente.entity.ProduitsPrix;
import java.util.List;

public interface DaoProduitPrix {

	public List<ProduitsPrix> findAll();
	
	public void save(ProduitsPrix p);
	
	public void update(ProduitsPrix p);
	
	public ProduitsPrix findById(int id);
	
	public ProduitsPrix findByName(String nomPdt);
}
