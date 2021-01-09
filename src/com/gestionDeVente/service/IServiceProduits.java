package com.gestionDeVente.service;
import java.util.List;

import com.gestionDeVente.entities.ProduitsPrix;

public interface IServiceProduits {

	
    public List<ProduitsPrix> findAll();
	
	public void save(ProduitsPrix p);
	
	public void update(ProduitsPrix p);
	
	public ProduitsPrix findById(int id);
	
	public ProduitsPrix findByName(String nomPdt);
	
	public int prixTotal();
	
	public int maxValue();
}