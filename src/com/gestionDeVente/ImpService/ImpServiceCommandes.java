package com.gestionDeVente.ImpService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionDeVente.dao.IDAOCommandes;
import com.gestionDeVente.entities.Commandes;
import com.gestionDeVente.service.IServiceCommandes;

@Service
public class ImpServiceCommandes implements IServiceCommandes {

	@Autowired
	IDAOCommandes daoCom ;
	
	@Override
	public List<Commandes> findAll() {
		// TODO Auto-generated method stub
		return daoCom.findAll();
	}

	@Override
	public void save(Commandes c) {
		// TODO Auto-generated method stub
      daoCom.save(c);
	}

	@Override
	public List<Commandes> findByUser(String login) {
		// TODO Auto-generated method stub
		return daoCom.findByUser(login);
	}

	@Override
	public Commandes findById(int id) {
		// TODO Auto-generated method stub
		return daoCom.findById(id);
	}

	@Override
	public void remove(Commandes c) {
		// TODO Auto-generated method stub
		this.daoCom.remove(c);
	}


	

}
