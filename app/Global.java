import models.User;
import play.Application;
import play.GlobalSettings;
import controllers.EktorpDb;
import controllers.UserRepository;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		// setup Ektorp
		EktorpDb.initialize();

		// Check if the database is empty
		// FIXME this is also evil crap! :)
		long userCount = getRepository().getAll().size();

		System.out.println("User count: " + userCount);
		if (userCount == 0) {
			// Ebean.save((List) Yaml.load("initial-data.yml"));
			createUsers();
		}
	}

	private void createUsers() {
		User user1 = new User("admin", "admin");
		getRepository().add(user1);

		User user2 = new User("bisasam", "bisabisa");
		getRepository().add(user2);
	}

	// FIXME this is crap
	private static UserRepository getRepository() {
		return new UserRepository(EktorpDb.getDb());
	}
}