package controllers;

import java.util.List;

import models.User;

import org.apache.commons.lang3.StringUtils;
import org.ektorp.CouchDbConnector;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;

public class UserRepository extends CouchDbRepositorySupport<User> {

	public UserRepository(CouchDbConnector db) {
		super(User.class, db);
		initStandardDesignDocument();
	}

	@GenerateView
	@Override
	public List<User> getAll() {
		ViewQuery q = createQuery("all").descending(true);
		return db.queryView(q, User.class);
	}

	public User authenticate(String username, String password) {
		// User user = get(username);
		User user = findByUsername(username);
		System.out.println("User: " + user);
		return (user != null && StringUtils.equals(password, user.password) ? user
				: null);
	}

	@GenerateView
	public User findByUsername(String username) {
		try {
			List<User> users = queryView("by_username", username);
			if (!users.isEmpty()) {
				return users.get(0);
			}
		} catch (DocumentNotFoundException e) {
			e.printStackTrace();
			// ok
		}
		return null;
	}
}
