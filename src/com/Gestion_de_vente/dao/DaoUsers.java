package com.Gestion_de_vente.dao;

import com.Gestion_de_vente.entity.users;
import java.util.List;

public interface DaoUsers {
	
	public void save(users u);
	
	public void update(users u);

	public List<users> findAll();
	
	public users findByName(String login);
	
	public users getUser(String login, String pass);
	
}
