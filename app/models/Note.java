package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class Note extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	public String text;
	public Date createDate;
	@ManyToOne
	public User assignedTo;
	@ManyToOne
	public Requirement requirement;

	public static Model.Finder<Long, Note> find = new Model.Finder(Long.class,
			Note.class);

	public static Note create(Note note, Long requirement) {
		note.requirement = Requirement.find.ref(requirement);
		note.save();
		return note;
	}
}