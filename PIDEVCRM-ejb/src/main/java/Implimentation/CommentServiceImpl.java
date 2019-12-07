package Implimentation;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Interfaces.ICommentServiceRemote;
import model.Comevent;
import model.Event;
@Stateless
@LocalBean
public class CommentServiceImpl implements ICommentServiceRemote{
	 @PersistenceContext(unitName = "primary")
		EntityManager em;

	@Override
	public int ajouterComment(Comevent comment) {
		System.out.println("In addcomment : ");
		em.persist(comment);
		System.out.println("Out of addcomment" + comment.getIdCom_event());
		return comment.getIdCom_event();
		
	}

	@Override
	public List<Comevent> getAllcomments() {
		List<Comevent> emp = em.createQuery("Select e from Comevent e", 
				Comevent.class).getResultList();
				return emp;
	}

	@Override
	public void removeComment(int id) {
		System.out.println("In removeUser : ");
		em.remove(em.find(Comevent.class, id));
		System.out.println("Out of removeUser : ");
		
	}
	@Override
	public void updateComment(Comevent comment) {
		Comevent ev = em.find(Comevent.class, comment.getIdCom_event()); 
		ev.setContent(comment.getContent());;
		
	}


}
