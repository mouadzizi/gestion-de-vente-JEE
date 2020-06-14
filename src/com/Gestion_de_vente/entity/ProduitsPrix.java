package com.Gestion_de_vente.entity;
import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="produits_prix")
public class ProduitsPrix implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codeProduit")
	private int codeProduit;
	
	@Column(name="nomProduit")
	private String nomProduit;
	
	@Column(name="descProduit")
	private String descProduit;
	
	@Column(name="prixPdt")
	private int prixPdt;
	
	@OneToMany(mappedBy="produitsPrix", fetch=FetchType.EAGER)
	private List<Commandes> commandes = new ArrayList<Commandes>();

	public int getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(int codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public String getDescProduit() {
		return descProduit;
	}

	public void setDescProduit(String descProduit) {
		this.descProduit = descProduit;
	}

	public int getPrixPdt() {
		return prixPdt;
	}

	public void setPrixPdt(int prixPdt) {
		this.prixPdt = prixPdt;
	}

	public List<Commandes> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commandes> commandes) {
		this.commandes = commandes;
	}

	public ProduitsPrix(String nomProduit, String descProduit, int prixPdt, List<Commandes> commandes) {
		super();
		this.nomProduit = nomProduit;
		this.descProduit = descProduit;
		this.prixPdt = prixPdt;
		this.commandes = commandes;
	}

	public ProduitsPrix() {
		super();
	}
	
}
