package controllers;

import models.User;

import org.apache.commons.lang3.StringUtils;

import play.mvc.Controller;

public abstract class BaseController extends Controller {

	private static UserRepository USER_REPO = new UserRepository(
			EktorpDb.getDb());

	protected static User getUser() {
		String username = session().get("username");
		if (StringUtils.isNotBlank(username)) {
			return USER_REPO.findByUsername(username);
		}
		return null;
	}

	protected static String getUserId() {
		User user = getUser();
		if (user != null) {
			return user.getId();
		}
		return null;
	}
}
