package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EventClients database table.
 * 
 */
@Embeddable
public class EventClientPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="Event_IdEvent", insertable=false, updatable=false)
	private int event_IdEvent;

	@Column(name="Client_IdClient", insertable=false, updatable=false)
	private int client_IdClient;

	
	public EventClientPK(int event_IdEvent, int client_IdClient) {
		super();
		this.event_IdEvent = event_IdEvent;
		this.client_IdClient = client_IdClient;
	}
	public EventClientPK() {
	}
	public int getEvent_IdEvent() {
		return this.event_IdEvent;
	}
	public void setEvent_IdEvent(int event_IdEvent) {
		this.event_IdEvent = event_IdEvent;
	}
	public int getClient_IdClient() {
		return this.client_IdClient;
	}
	public void setClient_IdClient(int client_IdClient) {
		this.client_IdClient = client_IdClient;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EventClientPK)) {
			return false;
		}
		EventClientPK castOther = (EventClientPK)other;
		return 
			(this.event_IdEvent == castOther.event_IdEvent)
			&& (this.client_IdClient == castOther.client_IdClient);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.event_IdEvent;
		hash = hash * prime + this.client_IdClient;
		
		return hash;
	}
}