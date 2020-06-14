package com.Gestion_de_vente.entity;

import java.io.Serializable;
import javax.persistence.*;



@Entity
@Table(name="users")
public class users implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int codeUser;
	
	@Column
	private String login;
	
	@Column
	private String pass;
	
	
	public users() {
	 super();
	}


	public int getCodeUser() {
		return codeUser;
	}


	public void setCodeUser(int codeUser) {
		this.codeUser = codeUser;
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


	public users(String login, String pass) {
		super();
		this.login = login;
		this.pass = pass;
	}

}
