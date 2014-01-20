package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Idea extends Model {

	private static final long serialVersionUID = 1L;

	public static Model.Finder<Long, Idea> find = new Model.Finder(Long.class,
			Idea.class);

	@Id
	public Long id;
	@Required
	public String text;
	@ManyToOne(optional = false)
	public User creator;
	@Enumerated(EnumType.STRING)
	public IdeaStatus status;
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<User> likes = new ArrayList<User>();

	public Idea(String text, User creator) {
		this.text = text;
		this.creator = creator;
	}

	public static Idea create(Idea idea) {
		idea.save();
		// idea.saveManyToManyAssociations("stakeholders");
		return idea;
	}

	public static Idea create(String text, User creator) {
		Idea idea = new Idea(text, creator);
		return create(idea);
	}

	public static void publish(Long id, User user) {
		Idea idea = find.byId(id);
		if (idea.creator.equals(user)) {
			idea.status = IdeaStatus.UP_FOR_VOTE;
		}
		// TODO error
	}

	public static void like(Long id, User user) {
		Idea idea = find.byId(id);
		idea.likes.add(user);
		idea.saveManyToManyAssociations("likes");
	}
}
