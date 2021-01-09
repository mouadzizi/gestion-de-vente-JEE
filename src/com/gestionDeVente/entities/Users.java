package com.gestionDeVente.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Users implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codeUser")
	private int codeUser;
	
	@Column(name="login")
	private String login;
	
	@Column(name="pass")
	private String pass;
	

	
	public Users() {
		super();
	}

	

	
	public Users(int codeUser, String login, String pass) {
		super();
		this.codeUser = codeUser;
		this.login = login;
		this.pass = pass;
	}



	public int getCode() {
		return codeUser;
	}

	public void setCode(int code) {
		this.codeUser = code;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	


	@Override
	public String toString() {
		return "Users [code=" + codeUser + ", login=" + login + ", pass=" + pass + "]";
	}
	
	
	
}
