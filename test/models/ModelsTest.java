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
	/*
	 * @Before public void setUp() { start(fakeApplication(inMemoryDatabase()));
	 * }
	 * 
	 * @Test public void createAndRetrieveUser() { new User("bob",
	 * "secret").save(); User bob = User.find.where().eq("username",
	 * "bob").findUnique(); assertNotNull(bob); assertEquals("secret",
	 * bob.password); }
	 * 
	 * @Test public void tryAuthenticateUser() { new User("bob",
	 * "secret").save();
	 * 
	 * assertNotNull(User.authenticate("bob", "secret"));
	 * assertNull(User.authenticate("bob", "badpassword"));
	 * assertNull(User.authenticate("tom", "secret")); }
	 * 
	 * @Test public void findRequirementsInvolving() { User user1 = new
	 * User("bob", "secret"); user1.save(); User user2 = new User("jane",
	 * "secret"); user2.save();
	 * 
	 * Requirement.create("P2", "Play 2", user1); Requirement.create("P1",
	 * "Play 1", user2);
	 * 
	 * List<Requirement> results = Requirement.findInvolving("bob");
	 * assertEquals(1, results.size()); assertEquals("Play 2",
	 * results.get(0).text); }
	 */
}