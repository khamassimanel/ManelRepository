package Interfaces;
import java.util.List;
import javax.ejb.Remote;
import model.Event;
import model.EventClient;

@Remote
public interface IEventServiceRemote {
	public int ajouterEvent(Event event);
	public List<Event> getAllEvenements();
	public void removeEvent(int id); 
	public void updateEvent(Event event);
	public Event getEventById(int id);
	public void Addparticipation(EventClient participation);
	public boolean checkifparticipated(int idevent, int idclient);
	public void DeleteParticipation(int idevent);
	public List<Event> recherche(String nom);
	

}
