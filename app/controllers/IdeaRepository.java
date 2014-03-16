package controllers;

import models.Idea;
import models.IdeaStatus;
import models.User;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;

@View(name = "all", map = "function(doc) { if (doc.type == 'Idea' ) emit(null, doc)}")
public class IdeaRepository extends CouchDbRepositorySupport<Idea> {

	protected IdeaRepository(CouchDbConnector db) {
		super(Idea.class, db);
		initStandardDesignDocument();
	}

	public void publish(String id, User user) {
		Idea idea = get(id);
		if (idea.getCreatorId().equals(user.getId())) {
			idea.setStatus(IdeaStatus.UP_FOR_VOTE);
		}
		// TODO error
	}

	public void like(String id, User user) {
		Idea idea = get(id);
		idea.getLikes().add(user.getId());
		update(idea);
	}
}
