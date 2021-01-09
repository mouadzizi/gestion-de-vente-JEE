package com.gestionDeVente.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.gestionDeVente.entities.ProduitsStock;
import com.gestionDeVente.service.IServicePStock;

@ManagedBean
@SessionScoped
public class ProduitStockBean {
	
	@javax.faces.bean.ManagedProperty(value="#{stockSerivice}")
	IServicePStock stockService;
	
	private List<ProduitsStock> all;

	public IServicePStock getStockService() {
		return stockService;
	}

	public void setStockService(IServicePStock stockService) {
		this.stockService = stockService;
	}

	public List<ProduitsStock> getAll() {
		all=stockService.findAll();
		return all;
	}

	public void setAll(List<ProduitsStock> all) {
		this.all = all;
	}
	



}
