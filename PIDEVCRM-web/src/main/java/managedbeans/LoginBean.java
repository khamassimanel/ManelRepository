package managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Implimentation.ClientServiceImpl;
import model.Client;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private Client client;
	private Boolean loggedIn;
	@EJB
	ClientServiceImpl clientservice;
	public String doLogin() {
		String navigateTo = "null"; 
		client = clientservice.getClientByEmailAndPassword(email, password); 
		if (client != null && client.getClientType().equals("admin")) {
			navigateTo = "/Event?faces-redirect=true"; 
			loggedIn = true; }
		else if(client != null && client.getClientType().equals("client")) {
			navigateTo = "/EventList?faces-redirect=true"; 
			loggedIn = true;}
		else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));}
		
		return navigateTo; } 

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";

	}

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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	
}
