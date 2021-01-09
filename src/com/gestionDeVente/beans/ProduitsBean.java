package com.gestionDeVente.beans;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestionDeVente.entities.ProduitsPrix;
import com.gestionDeVente.entities.ProduitsStock;
import com.gestionDeVente.service.IServicePStock;
import com.gestionDeVente.service.IServiceProduits;

@ManagedBean(name = "produitsBean")
@SessionScoped
@Component
public class ProduitsBean {
	
	@Autowired
	IServiceProduits serviceP;
	
	@Autowired
	IServicePStock serviceS;

	private String codePdt;
	private String nomPdt;
	private String descPdt;
	private int prixPdt;
	
	private String data;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	ProduitsPrix theProduct = new ProduitsPrix();

	
	public ProduitsPrix getTheProduct() {
		return theProduct;
	}

	public void setTheProduct(ProduitsPrix theProduct) {
		this.theProduct = theProduct;
	}

	private List<ProduitsPrix> produitsList;
	
	private List<ProduitsStock> pStockList;
 
	private List<ListProduit> listProduits;
	
	ProduitsStock produitStock = new ProduitsStock() ;

	public List<ListProduit> getListProduits() {
		
		listProduits = new ArrayList<ListProduit>();
		
		pStockList = serviceS.findAll();
		produitsList = serviceP.findAll();
		
		
		for(ProduitsStock ps : pStockList) {
			for(ProduitsPrix px : produitsList) {
				if(ps.getNomPdt().equals(px.getNomP())) {
					ListProduit produit = new ListProduit();
					

					if(ps.getQtePdt() == 0) {
						   
							if(produit.getCodePdt() == ps.getCodePdt()) {
								listProduits.remove(produit);
								
								ps.setQtePdt(0);
								serviceS.update(ps);
								
								return listProduits;
							}
							}
				else {

								produit.setCodePdt(ps.getCodePdt());
								produit.setNomPdt(px.getNomP());
								produit.setPrixPdt(px.getPrixP());
								produit.setQtePdt(ps.getQtePdt());
								
								listProduits.add(produit);
							}
					
					
				}
			}
		}

		return listProduits;
	}

	public String getCodePdt() {
		return codePdt;
	}

	public void setCodePdt(String codePdt) {
		this.codePdt = codePdt;
	}

	public String getNomPdt() {
		return nomPdt;
	}

	public void setNomPdt(String nomPdt) {
		this.nomPdt = nomPdt;
	}

	public String getDescPdt() {
		return descPdt;
	}

	public void setDescPdt(String descPdt) {
		this.descPdt = descPdt;
	}

	public int getPrixPdt() {
		return prixPdt;
	}

	public void setPrixPdt(int prixPdt) {
		this.prixPdt = prixPdt;
	}


	public List<ProduitsStock> getpStockList() {
		return pStockList;
	}

	public void setpStockList(List<ProduitsStock> pStockList) {
		this.pStockList = pStockList;
	}

	public List<ProduitsPrix> getProuitsList() {
		return produitsList;
	}

	public void setProuitsList(List<ProduitsPrix> produitsList) {
		this.produitsList = produitsList;
	}
	
	public ProduitsStock getProduitStock() {
		return produitStock;
	}

	public void setProduitStock(ProduitsStock produitStock) {
		this.produitStock = produitStock;
	}

	public String addCommande(String nomPdt) {

		FacesContext context = FacesContext.getCurrentInstance();
		
		produitStock = serviceS.findByName(nomPdt);

			ProduitsPrix produit = serviceP.findByName(nomPdt);
			context.getExternalContext().getSessionMap().put("nomPdt", produit.getNomP());
			System.out.println(produit.getNomP());
			return "AddCommande";
		
	}

	public void addProduit() {
		ProduitsStock ps = serviceS.findByName(this.data);
		System.out.println("item selected"+this.data);
		theProduct.setCodeP(ps.getCodePdt());
		theProduct.setNomP(ps.getNomPdt());
		theProduct.setDescP(this.descPdt);
		theProduct.setPrixP(this.prixPdt);
		serviceP.save(theProduct);
		
		
	}

}