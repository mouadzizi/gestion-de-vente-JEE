package com.gestionDeVente.service;

import java.util.List;

import com.gestionDeVente.entities.Users;

public interface IServiceUsers {
   public List<Users> findAll();
	
	public void save(Users p);
	
	public Users getUser(String login, String pass);
	
	public Users findByName(String login);
	
	public void update(Users u);
	
}