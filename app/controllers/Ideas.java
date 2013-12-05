package controllers;

import static play.data.Form.form;
import models.Idea;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.ideas.*;

@With(SideMenu.class)
@Security.Authenticated(Secured.class)
public class Ideas extends Controller {

	private static final Result GO_HOME = redirect(routes.Ideas.list());
	private static final Form<Idea> ideaForm = Form.form(Idea.class);

	public static Result list() {
		return ok(list.render(ideaForm, Idea.find.all()));
	}

	public static Result add() {
		Form<Idea> ideaForm = form(Idea.class).bindFromRequest();
		if (ideaForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(list.render(ideaForm, Idea.find.all()));
		} else {
			Idea idea = ideaForm.get();
			idea.creator = getUser();
			// FIXME crap this is
			Idea.create(idea);
			flash("success", "New idea created :)");
			return GO_HOME;
		}
	}

	// TODO refactor
	private static User getUser() {
		return User.find.byId(session("username"));
	}

}
