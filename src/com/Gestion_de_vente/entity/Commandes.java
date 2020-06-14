package com.Gestion_de_vente.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

public class Commandes implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int codeCmd;

	@Column
	private int qteCmd;
	
	@Column
	private String client;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date dateCmd;

	@ManyToOne
	@JoinColumn(name ="codePdt")
	private ProduitsPrix produitsPrix;

	public int getCodeCmd() {
		return codeCmd;
	}

	public void setCodeCmd(int codeCmd) {
		this.codeCmd = codeCmd;
	}

	public int getQteCmd() {
		return qteCmd;
	}

	public void setQteCmd(int qteCmd) {
		this.qteCmd = qteCmd;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Date getDateCmd() {
		return dateCmd;
	}

	public void setDateCmd(Date dateCmd) {
		this.dateCmd = dateCmd;
	}

	public ProduitsPrix getProduitsPrix() {
		return produitsPrix;
	}

	public void setProduitsPrix(ProduitsPrix produitsPrix) {
		this.produitsPrix = produitsPrix;
	}

	public Commandes(int qteCmd, String client, Date dateCmd, ProduitsPrix produitsPrix) {
		super();
		this.qteCmd = qteCmd;
		this.client = client;
		this.dateCmd = dateCmd;
		this.produitsPrix = produitsPrix;
	}

	public Commandes() {
		super();
	}


}
