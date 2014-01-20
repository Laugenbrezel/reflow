package controllers;

import static play.data.Form.form;

import java.util.Collections;
import java.util.List;

import models.Idea;
import models.IdeaStatus;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.ideas.list;

@With(SideMenu.class)
@Security.Authenticated(Secured.class)
public class Ideas extends Controller {

	private static final Result GO_HOME = redirect(routes.Ideas.list());
	private static final Form<Idea> ideaForm = Form.form(Idea.class);

	public static Result list() {
		List<Idea> ideasList = Idea.find.all();
		Collections.reverse(ideasList);
		return ok(list.render(ideaForm, ideasList));
	}

	public static Result add() {
		Form<Idea> ideaForm = form(Idea.class).bindFromRequest(
				new String[] { "text" });
		if (ideaForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(list.render(ideaForm, Idea.find.all()));
		} else {
			Idea idea = ideaForm.get();
			// FIXME crap this is
			idea.creator = getUser();
			idea.status = IdeaStatus.CREATED;
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
