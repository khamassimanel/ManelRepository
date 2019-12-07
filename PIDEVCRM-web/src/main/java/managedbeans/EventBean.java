package managedbeans;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.Part;

import org.primefaces.event.RateEvent;

import Implimentation.ClientServiceImpl;
import Implimentation.EventServiceImpl;
import model.Event;
import model.EventClient;
import model.EventClientPK;
import utilis.util;


@ManagedBean(name="eventBean")
@SessionScoped
public class EventBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String description;
	private int nbPlaces;
	private float price;
	private String image;
    private boolean participated;
    private String nomEv;
   
    

	public String getNomEv() {
		return nomEv;
	}

	public void setNomEv(String nomEv) {
		this.nomEv = nomEv;
	}

	public boolean isParticipated() {
		return participated;
	}

	public void setParticipated(boolean participated) {
		this.participated = participated;
	}


	//date ***********************************************
	private Date datedebut;
	private Date datefin;
	@EJB
	EventServiceImpl eventservice;
	@EJB
	ClientServiceImpl clientServiceImpl;
     //auto
	private Integer rating2;   
	
	

	public Integer getRating2() {
		return rating2;
	}

	public void setRating2(Integer rating2) {
		this.rating2 = rating2;
	}

	//add event
	public void addEvent() throws IOException  {
		if(datefin.after(datedebut)) {
		eventservice.ajouterEvent(new Event(datedebut, datefin, description, file.getSubmittedFileName(), nbPlaces, nom, price));
		String folderName1 = util.serverI;
		uploadimage(folderName1);}
		else
		{
			FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"invalid start/end dates",
					"start date cannot be after end date"
					);
		}
		
	}
	//recherche
	public void recherche() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    String text = ec.getRequestParameterMap().get("form:search");
		events = eventservice.recherche(text);
	}
	
	public void addparticipation(int idevent) {
		
		this.participated= true;
		eventservice.Addparticipation(new EventClient(new EventClientPK(idevent,2)));
		 FacesContext.getCurrentInstance().addMessage("form:part", new FacesMessage("Registered!"));
		 try {
			eventservice.sendMail(clientServiceImpl.getClientById(2).getEmail() ,
					 "Participation à l'évenement "+ nom+" a été bien enrégistrée !" ,"Confirmation",image);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(clientServiceImpl.getClientById(2).getEmail() );
	}
	
	public void removeparticipation(int idevent) {
		this.participated= false;
		eventservice.DeleteParticipation(idevent);
		FacesContext.getCurrentInstance().addMessage("form:delpart", new FacesMessage("Cancelled!"));
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	//liste events
	private List<Event> events;

	@PostConstruct
	public void init(){
		events = eventservice.getAllEvenements(); 
	}
	
	
	public List<Event> getEvents() {
	
	return events;
	}
	
	//remove event 
	public void removeEv(int employeId) {
		eventservice.removeEvent(employeId);
		}
	//display
	private Integer eventIdToBeUpdated;
	public void displayEvent(Event event) 
	{
		
	this.setNom(event.getNom());
	this.setDescription(event.getDescription());
	this.setNbPlaces(event.getNbPlaces());
	this.setPrice(event.getPrice());
	this.setDatedebut(event.getDatedebut());
	this.setDatefin(event.getDatefin());
	this.setEventIdToBeUpdated(event.getIdEvent());
	
	}
	
	
	public String displayEventDetails(Event event) 
	{
		this.participated = eventservice.checkifparticipated(event.getIdEvent(), 2);
	String navigateTo="/EventDetails?faces-redirect=true";
	this.setNom(event.getNom());
	this.setDescription(event.getDescription());
	this.setNbPlaces(event.getNbPlaces());
	this.setPrice(event.getPrice());
	this.setDatedebut(event.getDatedebut());
	this.setDatefin(event.getDatefin());
	this.setEventIdToBeUpdated(event.getIdEvent());
	this.setImage(event.getImageEvent());
	return navigateTo;
	
	}
	//update
	public void updateEvent() 
	{  eventservice.updateEvent(new Event(eventIdToBeUpdated, datedebut, datefin, description, nbPlaces, nom, price)); }
	
	//imageeeeeeeeeeeeeeeee
	private Part file;
    public Part getFile() {
		return file;
	}
	public void setFile(Part file) {
		this.file = file;
	}
	public String uploadimage(String folderName1) throws IOException {
			
			if (file != null) {
			
			InputStream in = file.getInputStream();
			File f = new File(folderName1 + "\\" + file.getSubmittedFileName());
			f.createNewFile();
			FileOutputStream out = new FileOutputStream(f);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			out.close();
			in.close();
			return "succes-image-uplaod?faces-redirect=true";
			}
			else {
				return "succes-image-uplaod?faces-redirect=true";	
			}	
		}
	
	
	
	
	
	
	
	
	
	public Integer getEventIdToBeUpdated() {
		return eventIdToBeUpdated;
	}
	public void setEventIdToBeUpdated(Integer eventIdToBeUpdated) {
		this.eventIdToBeUpdated = eventIdToBeUpdated;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNbPlaces() {
		return nbPlaces;
	}
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	public Date getDatefin() {
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	
	
	
	
	

  

}
