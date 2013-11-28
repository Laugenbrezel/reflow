package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import play.test.WithApplication;

public class ModelsTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void createAndRetrieveUser() {
		new User("bob", "secret").save();
		User bob = User.find.where().eq("username", "bob").findUnique();
		assertNotNull(bob);
		assertEquals("secret", bob.password);
	}

	@Test
	public void tryAuthenticateUser() {
		new User("bob", "secret").save();

		assertNotNull(User.authenticate("bob", "secret"));
		assertNull(User.authenticate("bob", "badpassword"));
		assertNull(User.authenticate("tom", "secret"));
	}

	@Test
	public void findRequirementsInvolving() {
		new User("bob", "secret").save();
		new User("jane", "secret").save();

		Requirement.create("Play 2", "bob");
		Requirement.create("Play 1", "jane");

		List<Requirement> results = Requirement.findInvolving("bob");
		assertEquals(1, results.size());
		assertEquals("Play 2", results.get(0).text);
	}
}