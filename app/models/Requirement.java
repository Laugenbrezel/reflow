package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class Requirement extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	public String text;
	@ManyToOne
	public User creator;
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<User> stakeholders = new ArrayList<User>();

	public Requirement(String text, User owner) {
		this.text = text;
		this.stakeholders.add(owner);
	}

	public static Model.Finder<Long, Requirement> find = new Model.Finder(
			Long.class, Requirement.class);

	public static Requirement create(String text, String creator) {
		Requirement project = new Requirement(text, User.find.ref(creator));
		project.save();
		project.saveManyToManyAssociations("stakeholders");
		return project;
	}

	public static List<Requirement> findInvolving(String username) {
		return find.where().eq("stakeholders.username", username).findList();
	}
}
