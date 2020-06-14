package com.Gestion_de_vente.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Gestion_de_vente.dao.DaoUsers;
import com.Gestion_de_vente.entity.users;


@ManagedBean
@SessionScoped
@Component
public class LoginBean {  
	
	@Autowired
	DaoUsers dao;
	
	private String email="";
	private String password="";
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@PostConstruct
	public void initBean() {
		users u = new users();
		
		u.setLogin("mouad");
		u.setPass("mouad");
		
		dao.save(u);
	}
	
	
	
}
