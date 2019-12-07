package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Clients database table.
 * 
 */
@Entity
@Table(name="Clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdClient")
	private int idClient;

	@Column(name="BirthDate")
	private Date birthDate;

	@Column(name="ClientType")
	private String clientType;

	@Column(name="Email")
	private String email;

	@Column(name="LastName")
	private String lastName;

	@Column(name="Name")
	private String name;

	@Column(name="Password")
	private String password;

	@Column(name="PhoneNumber")
	private String phoneNumber;

	//bi-directional many-to-one association to Comevent
	@OneToMany(mappedBy="client")
	private List<Comevent> comevents;


	//bi-directional many-to-many association to Event
	@ManyToMany(mappedBy="clients")
	private List<Event> events;

	public Client() {
	}

	public int getIdClient() {
		return this.idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getClientType() {
		return this.clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Comevent> getComevents() {
		return this.comevents;
	}

	public void setComevents(List<Comevent> comevents) {
		this.comevents = comevents;
	}

	public Comevent addComevent(Comevent comevent) {
		getComevents().add(comevent);
		comevent.setClient(this);

		return comevent;
	}

	public Comevent removeComevent(Comevent comevent) {
		getComevents().remove(comevent);
		comevent.setClient(null);

		return comevent;
	}

	

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}