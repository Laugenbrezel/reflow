package controllers;

import models.Note;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;

@View(name = "all", map = "function(doc) { if (doc.type == 'Note' ) emit( null, doc._id )}")
public class NoteRepository extends CouchDbRepositorySupport<Note> {

	protected NoteRepository(CouchDbConnector db) {
		super(Note.class, db);
		initStandardDesignDocument();
	}

	public static Note create(Note note, String requirement) {
		note.setRequirementId(requirement);
		getRepository().add(note);
		return note;
	}

	// FIXME this is crap
	private static NoteRepository getRepository() {
		return new NoteRepository(EktorpDb.getDb());
	}
}
