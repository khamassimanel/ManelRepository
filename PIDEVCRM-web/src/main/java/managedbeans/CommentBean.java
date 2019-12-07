package managedbeans;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Implimentation.CommentServiceImpl;
import model.Comevent;
import model.Event;
@ManagedBean(name="commentBean")
@SessionScoped
public class CommentBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	
	@EJB
	CommentServiceImpl commentservice;
	//ajout commentaire
	public void addcomment() {
		commentservice.ajouterComment(new Comevent(content));
		
	}
	//liste commentaires
	private List<Comevent> comments;
	public List<Comevent> getComments() {
	comments = commentservice.getAllcomments(); 
	return comments;
	}
	//remove comment 
	public void removeComment(int CommentId) {
		commentservice.removeComment(CommentId);
		}
	//display comment 
	private int Idcommentupdated;
	public void displayComment(Comevent com) 
	{
	
	this.setContent(com.getContent());
	this.setIdcommentupdated(com.getIdCom_event());
	
	}
	
	//update comment 
	public void updateCom() 
	{  commentservice.updateComment(new Comevent(Idcommentupdated, content)); }
	

	public int getIdcommentupdated() {
		return Idcommentupdated;
	}
	public void setIdcommentupdated(int idcommentupdated) {
		Idcommentupdated = idcommentupdated;
	}
	public void setComments(List<Comevent> comments) {
		this.comments = comments;
	}




	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	



}
