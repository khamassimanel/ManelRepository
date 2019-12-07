package Implimentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Interfaces.IEventServiceRemote;
import model.Comevent;
import model.Event;
import model.EventClient;
@Stateless
@LocalBean
public class EventServiceImpl implements IEventServiceRemote{
	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static final String USERNAME = "manel.khamassi@esprit.tn";
	private static final String PASSWORD = "183JFT0873";
	static final int PORT = 587;
	
      @PersistenceContext(unitName = "primary")
	EntityManager em;

	@Override
	public int ajouterEvent(Event event) {
		System.out.println("In addevent : ");
		em.persist(event);
		System.out.println("Out of addevent" + event.getIdEvent());
		return event.getIdEvent();
	}

	@Override
	public List<Event> getAllEvenements() {
		List<Event> ev = em.createQuery("Select e from Event e", 
				Event.class).getResultList();
				return ev;
	}

	@Override
	public void removeEvent(int id) {
		System.out.println("In removeUser : ");
		em.remove(em.find(Event.class, id));
		System.out.println("Out of removeUser : ");
		
	}

	@Override
	public void updateEvent(Event event) {
		
		Event ev = em.find(Event.class, event.getIdEvent()); 
		ev.setNom(event.getNom());
		ev.setDescription(event.getDescription());
		ev.setNbPlaces(event.getNbPlaces());
		ev.setPrice(event.getPrice());
		ev.setDatedebut(event.getDatedebut());
		ev.setDatefin(event.getDatefin());
		
		
	}

	@Override
	public Event getEventById(int id) {
		Event emp = em.find(Event.class, id);
		return  emp;
	}

	@Override
	public void Addparticipation(EventClient participation) {
            em.persist(participation);		
	}

	@Override
	public boolean checkifparticipated(int idevent, int idclient) {
		TypedQuery<EventClient> q = em.createQuery("select e from EventClient e where e.id.event_IdEvent = :idevent"
				+ " and e.id.client_IdClient = :idclient",EventClient.class);
		 q.setParameter("idevent", idevent);
		 q.setParameter("idclient", idclient);
		
		return !q.getResultList().isEmpty();
	}
    
	@Override
	public void DeleteParticipation(int idevent) {
		Query q  = em.createQuery("select p from EventClient p where p.id.event_IdEvent=:idevent and p.id.client_IdClient = 2");
		q.setParameter("idevent", idevent);
		EventClient ev = (EventClient) q.getSingleResult();
		em.remove(em.find(EventClient.class, ev.getId()));
	}

	@Override
	public List<Event> recherche(String nom) {
		
		Query q = em.createQuery("select e from Event e where e.nom like :nom or e.description like :description ");
		q.setParameter("nom","%" + nom + "%");
		q.setParameter("description", "%" + nom + "%");
		
		if(q.getResultList().isEmpty())
			return new ArrayList<Event>();
		
		return (List<Event>)q.getResultList();
	}

	public void sendMail(String recipient , String content ,String subject,String image) throws MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", SMTP_SERVER);
		props.put("mail.smtp.user", USERNAME);
		props.put("mail.smtp.password", PASSWORD);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		String[] to = { recipient };

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		try {

			message.setFrom(new InternetAddress(USERNAME));

			InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int i = 0; i < to.length; i++)
				toAddress[i] = new InternetAddress(to[i]);

			for (int i = 0; i < toAddress.length; i++)

				message.addRecipient(Message.RecipientType.TO, toAddress[i]);

			message.setSubject(subject);
			//message.setText(content);
			
			 // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<H3>"+content+"</H3><img src=\"cid:image\">";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource(
	            "C:/Users/Manel/eclipse-workspace/PIDEVCRM/PIDEVCRM-web/src/main/webapp/Images/Events/"+image);

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");

	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // put everything together
	         message.setContent(multipart);
			Transport transport;
			transport = session.getTransport("smtp");
			transport.connect(SMTP_SERVER, USERNAME, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (NoSuchProviderException e2) {
			// TODO Auto-generated catch block
			System.out.println(e2.getMessage());
			e2.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
      
	

}
