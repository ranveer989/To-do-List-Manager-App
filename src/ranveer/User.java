package ranveer;
import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class User {
	@Persistent
	private String email;

	@PrimaryKey
	@Persistent
	private Key id;
	
	@Persistent(mappedBy="parent")
	private ArrayList<Todo> todos;
	
	
	
	public void addTodo(Todo t){
		if (todos==null)	
			todos=new ArrayList<Todo>();
    	todos.add(t);
    }
	public ArrayList<Todo> getTodos(){ return todos;}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public User(Key id ,String n){
		email=n;
		this.id=id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
