package com.gestionDeVente.beans;


import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestionDeVente.entities.Users;
import com.gestionDeVente.service.IServiceUsers;


@ManagedBean
@SessionScoped
@Component
public class UsersBean {
	
	@Autowired
	IServiceUsers serviceUser;
	
	private int codeUser;
	private String login;
	private String pass;
	
	
	private List<Users> listUsers;
	
	private String confPassword;
	
	private String newPass;
	
	private Users loginUser = new Users();

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

	
	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}
	

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	public Users getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(Users loginUser) {
		this.loginUser = loginUser;
	}

	
	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	
	public String loginMethod()throws IOException {
		
		Users user = serviceUser.getUser(loginUser.getLogin(), loginUser.getPass());
		System.out.println(loginUser.getLogin());
		
		if( user == null ) {
			user = new Users();
			
			return "index";

		}
		else {
			
			return "Home";
		}
    	
	}
	
	
	public IServiceUsers getServiceUser() {
		return serviceUser;
	}

	public void setServiceUser(IServiceUsers serviceUser) {
		this.serviceUser = serviceUser;
	}

	public String registerMethod() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		listUsers = serviceUser.findAll();
		
		for(Users user : listUsers) {
			if(user.getLogin().equals(loginUser.getLogin())) {
				context.addMessage(null, new FacesMessage("User exists"));
				return "signup";
			}
		}
		if(loginUser.getPass().equals(confPassword)) {
			serviceUser.save(loginUser);
			context.getExternalContext().getSessionMap().put("success", "Votre compte est ajouter avec succes.");
			return "Home";
		}else {
			context.addMessage(null, new FacesMessage("Passwords do not match") );
			return "signup";
		}
		
	}
	

	
	public String Edit() {
		
		if(!loginUser.getPass().equals(pass)) {

			return "Settings";
		}
		else if(newPass.equals(confPassword)) {
				String name= loginUser.getLogin();
				Users user = serviceUser.findByName(name);
				
				user.setPass(newPass);
				
				serviceUser.update(user);
				return "Home";
			}
			else 

				return "Settings";
			}
	

}
