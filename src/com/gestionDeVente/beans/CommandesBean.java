package com.gestionDeVente.beans;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestionDeVente.entities.Commandes;
import com.gestionDeVente.entities.Facture;
import com.gestionDeVente.entities.ProduitsPrix;
import com.gestionDeVente.entities.ProduitsStock;
import com.gestionDeVente.entities.Users;
import com.gestionDeVente.service.IServiceCommandes;
import com.gestionDeVente.service.IServicePStock;
import com.gestionDeVente.service.IServiceProduits;
import com.gestionDeVente.service.IServiceUsers;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@SessionScoped
@Component
public class CommandesBean {
	
	@Autowired
	IServiceCommandes serviceCommande;
	
	@Autowired
	IServiceUsers serviceUser;
	
	@Autowired
	IServiceProduits serviceProduit;
	
	@Autowired 
	IServicePStock serviceStock;
	
	private List<Commandes> commandList;


	private String qteCmd;
	
	private Commandes commande = new Commandes();
	
	private boolean editable;
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Commandes getCommande() {
		
		return commande;
	}

	public void setCommande(Commandes commande) {
		this.commande = commande;
	}
	
	public List<Commandes> getCommandList(String login) {
		
		commandList = serviceCommande.findByUser(login);
		
		return commandList;
	}

	
	public void setCommandList(List<Commandes> commandList) {
		this.commandList = commandList;
	}

	public String getQteCmd() {
		return qteCmd;
	}

	public void setQteCmd(String qteCmd) {
		this.qteCmd = qteCmd;
	}
	
	//Add commande Function
	public String saveCommand(String nomPdt, String login) {
		
		ProduitsPrix produit = serviceProduit.findByName(nomPdt);
		Users user = serviceUser.findByName(login);

		ProduitsStock produitStock = new ProduitsStock();
		
		produitStock = serviceStock.findByName(produit.getNomP());
		
		LocalDate dateNow = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date d = Date.from(dateNow.atStartOfDay(defaultZoneId).toInstant());
		
		if(Integer.valueOf(qteCmd) == 0) {
			qteCmd="";
			return "AddCommande";
		}
		
		else if(Integer.valueOf(qteCmd) <= produitStock.getQtePdt()) {
			commande.setProduitsPrix(produit);
			commande.setUsers(user);
			
			commande.setDateCmd(d);
	
		    commande.setQteCmd(Integer.valueOf(qteCmd));
			

			serviceCommande.save(commande);
			
			
			int quantite = produitStock.getQtePdt() - commande.getQteCmd();
			
			produitStock.setQtePdt(quantite);
			
			serviceStock.update(produitStock);
			
			

			return "Home";
		}else {
			qteCmd="";
			return "AddCommande";
		}
		
		
	}
	
	
	


	//Print Facture Function
	public void printPDF(int code) throws JRException, IOException {
		
		Commandes commande = serviceCommande.findById(code);
		
		
		
		List<Facture> datasource = new ArrayList<Facture>();

		int total = commande.getQteCmd() * commande.getProduitsPrix().getPrixP();
		
		datasource.add(new Facture(commande.getProduitsPrix().getNomP(), commande.getDateCmd(), commande.getProduitsPrix().getPrixP(), commande.getQteCmd(), total));
		
			
		String filename = "recu.pdf";
		
		String jasperPath = "/resources/facture.jasper";
		
		this.PDf(null, jasperPath, datasource, filename);
	}
	
	//Facture Function
	public void PDf(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
		
		String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
		
		File file = new File(relativeWebPath);
		
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
		
		JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		response.addHeader("Content-disposition", "attachment;filename=" + fileName);
		
		ServletOutputStream stream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(print, stream);
		
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	//EDIT Function
	public String ediAction(Commandes c) {
		
		editable=true;
		
		return null;
	}

	public String saveAction() {
		
			editable=false;
	
		return null;
	}
	
	//REMOVE Function
	public void removeCmd(Commandes c) {
		this.serviceCommande.remove(c);
	}
}
