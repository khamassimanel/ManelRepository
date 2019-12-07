package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;



/**
 * The persistent class for the Comevents database table.
 * 
 */
@Entity
@Table(name="Comevents")
@NamedQuery(name="Comevent.findAll", query="SELECT c FROM Comevent c")
public class Comevent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdCom_event")
	private int idCom_event;

	@Column(name="CommentDate")
	private Date commentDate;

	public Comevent(int idCom_event, String content) {
		super();
		this.idCom_event = idCom_event;
		this.content = content;
	}

	@Column(name="Content")
	private String content;

	@Column(name="IdEvent")
	private int idEvent;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="IdClient")
	private Client client;

	public Comevent() {
	}

	public int getIdCom_event() {
		return this.idCom_event;
	}

	public void setIdCom_event(int idCom_event) {
		this.idCom_event = idCom_event;
	}

	

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Comevent(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}


	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}