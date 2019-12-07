package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EventClients database table.
 * 
 */
@Entity
@Table(name="EventClients")
@NamedQuery(name="EventClient.findAll", query="SELECT e FROM EventClient e")
public class EventClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventClientPK id;

	

	public EventClient(EventClientPK id) {
		super();
		this.id = id;
	}

	public EventClient() {
	}

	public EventClientPK getId() {
		return this.id;
	}

	public void setId(EventClientPK id) {
		this.id = id;
	}

	

}