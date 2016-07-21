package ranveer;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class Todo{
	 
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
	@Persistent
	String name;
	@Persistent
	boolean checked=false;
	@Persistent
	String date;

	@Persistent
	private User parent;
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDate(String date) {
		this.date = date;
	}	
	public void setStatus(boolean b){
		checked=b;
	}
	public boolean isChecked(){return checked;}
	public User getParent() {
		return parent;
	}
	public void setParent(User parent) {
		this.parent = parent;
	}
	public Todo(String name, String date) {
		this.name = name;
		this.date = date;
	}
	public String getDate() {
		return date;
	}
}
