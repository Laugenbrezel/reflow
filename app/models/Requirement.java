package models;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.hash.HashCode;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.*;

@Entity
public class Requirement extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	@Required
	public String title;
	@NotBlank
	@MaxLength(140)
	public String text;
	@ManyToOne(optional = false)
	public User creator;
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<User> stakeholders = new ArrayList<User>();
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<User> likes = new ArrayList<User>();

	public Requirement(String title, String text, User owner) {
		this.title = title;
		this.text = text;
		this.creator = owner;
		this.stakeholders.add(owner);
	}

	public static Model.Finder<Long, Requirement> find = new Model.Finder(
			Long.class, Requirement.class);

	public static Requirement create(String title, String text, User creator) {
		Requirement requirement = new Requirement(title, text, creator);
		return create(requirement);
	}

	public static Requirement create(Requirement requirement) {
		requirement.save();
		requirement.saveManyToManyAssociations("stakeholders");
		return requirement;
	}

	public static List<Requirement> findInvolving(String username) {
		return find.where().eq("stakeholders.username", username).findList();
	}

	public static List<Requirement> findInvolving(User user) {
		return find.where().in("stakeholders", user).findList();
	}

	public static List<Requirement> findCreatedBy(User user) {
		return find.where().eq("creator", user).findList();
	}

	public static void like(Long id, User user) {
		Requirement requirement = find.byId(id);
		requirement.likes.add(user);
		requirement.saveManyToManyAssociations("likes");
	}

	public static void unlike(Long id, User user) {
		Requirement requirement = find.byId(id);
		requirement.likes.remove(user);
		requirement.saveManyToManyAssociations("likes");
	}

	public boolean likedBy(User user) {
		return likes.contains(user);
	}

}
