package controllers;

import java.util.Collections;
import java.util.List;

import models.Requirement;
import models.User;

import org.ektorp.CouchDbConnector;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;

@View(name = "all", map = "function(doc) { if (doc.type == 'Requirement' ) emit( null, doc )}")
public class RequirementRepository extends
		CouchDbRepositorySupport<Requirement> {

	protected RequirementRepository(CouchDbConnector db) {
		super(Requirement.class, db);
		initStandardDesignDocument();
	}

	public List<Requirement> findInvolving(String username) {
		// return find.where().eq("stakeholders.username", username).findList();
		return Collections.EMPTY_LIST;
	}

	public List<Requirement> findInvolving(User user) {
		// return find.where().in("stakeholders", user).findList();
		return Collections.EMPTY_LIST;
	}

	@GenerateView
	public List<Requirement> findByCreatorId(String creatorId) {
		return queryView("by_creatorId", creatorId);
	}

	public void like(String id, String userId) {
		Requirement requirement = get(id);
		requirement.likes.add(userId);
		update(requirement);
	}

	public void unlike(String id, String userId) {
		Requirement requirement = get(id);
		requirement.likes.remove(userId);
		update(requirement);
	}

}
