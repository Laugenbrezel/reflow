package controllers;

import models.User;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.SimpleResult;

public class SideMenu extends Action.Simple {

	public F.Promise<SimpleResult> call(Http.Context ctx) throws Throwable {
		ctx.args.put("sideMenu", new SideMenuItem(getUser(ctx)));
		return delegate.call(ctx);
	}

	public static SideMenuItem current() {
		return (SideMenuItem) Http.Context.current().args.get("sideMenu");
	}

	// TODO refactor
	private static User getUser(Context ctx) {
		return User.find.byId(ctx.session().get("username"));
	}
}