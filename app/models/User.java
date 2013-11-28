package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class User extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public String username;
	// TODO Hash password
	public String password;

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public static Finder<String, User> find = new Finder<String, User>(
			String.class, User.class);

	public static User authenticate(String username, String password) {
		return find.where().eq("username", username).eq("password", password)
				.findUnique();
	}
}