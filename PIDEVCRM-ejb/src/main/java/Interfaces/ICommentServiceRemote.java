package Interfaces;
import java.util.List;
import javax.ejb.Remote;
import model.Comevent;
@Remote
public interface ICommentServiceRemote {
	public int ajouterComment(Comevent comment);
	public List<Comevent> getAllcomments();
	public void removeComment(int id); 
	public void updateComment(Comevent comment);
}
