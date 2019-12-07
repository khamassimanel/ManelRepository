package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Events database table.
 * 
 */
@Entity
@Table(name="Events")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	public Event(Date datedebut, Date datefin, String description, String imageEvent, int nbPlaces, String nom,
			float price) {
		super();
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.description = description;
		this.imageEvent = imageEvent;
		this.nbPlaces = nbPlaces;
		this.nom = nom;
		this.price = price;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdEvent")
	private int idEvent;

	public Event(int idEvent, Date datedebut, Date datefin, String description, int nbPlaces, String nom, float price) {
		super();
		this.idEvent = idEvent;
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.description = description;
		this.nbPlaces = nbPlaces;
		this.nom = nom;
		this.price = price;
	}

	@Column(name="Datedebut")
	private Date datedebut;

	@Column(name="Datefin")
	private Date datefin;

	private String description;

	@Column(name="ImageEvent")
	private String imageEvent;

	private int nbPlaces;

	private String nom;

	@Column(name="Price")
	private float price;

	private String statut;

	

	//bi-directional many-to-many association to Client
	@ManyToMany
	@JoinTable(
		name="EventClients"
		, joinColumns={
			@JoinColumn(name="Event_IdEvent")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Client_IdClient")
			}
		)
	private List<Client> clients;

	public Event() {
	}

	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	
	public Event(Date datedebut, Date datefin, String description, int nbPlaces, float price) {
		super();
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.description = description;
		this.nbPlaces = nbPlaces;
		this.price = price;
	}

	public Event(Date datedebut, Date datefin, String description, int nbPlaces, String nom, float price) {
		super();
		this.datedebut = datedebut;
		this.datefin = datefin;
		this.description = description;
		this.nbPlaces = nbPlaces;
		this.nom = nom;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageEvent() {
		return this.imageEvent;
	}

	public void setImageEvent(String imageEvent) {
		this.imageEvent = imageEvent;
	}

	public int getNbPlaces() {
		return this.nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	

	

	public List<Client> getClients() {
		return this.clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}