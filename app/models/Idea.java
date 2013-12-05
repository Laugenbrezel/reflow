package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
}
