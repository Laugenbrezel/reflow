package controllers;

import static play.data.Form.form;
import models.Requirement;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.requirements.*;

@With(SideMenu.class)
@Security.Authenticated(Secured.class)
public class Requirements extends Controller {

	private static final Result GO_HOME = redirect(routes.Requirements.list());
	private static final Form<Requirement> requirementForm = Form
			.form(Requirement.class);

	public static Result newRequirement() {
		return ok(create.render(requirementForm));
	}

	public static Result load(Long id) {
		Form<Requirement> requirementForm = form(Requirement.class).fill(
				Requirement.find.byId(id));
		return ok(update.render(id, requirementForm));
	}

	public static Result list() {
		return ok(list.render(Requirement.find.all()));
	}

	public static Result add() {
		Form<Requirement> requirementForm = form(Requirement.class)
				.bindFromRequest();
		if (requirementForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(create.render(requirementForm));
		} else {
			Requirement requirement = requirementForm.get();
			requirement.creator = getUser();
			// FIXME crap this is
			Requirement.create(requirement);
			flash("success", "New Requirement created :)");
			return GO_HOME;
		}
	}

	public static Result update(Long id) {
		Form<Requirement> requirementForm = form(Requirement.class)
				.bindFromRequest();
		if (requirementForm.hasErrors()) {
			return badRequest(update.render(id, requirementForm));
		}
		requirementForm.get().update(id);
		flash("success", "Requirement " + requirementForm.get().id
				+ " has been updated");
		return GO_HOME;
	}

	public static Result delete(Long id) {
		final Requirement requirement = Requirement.find.byId(id);
		if (requirement == null) {
			return notFound(String
					.format("Requirement %s does not exists.", id));
		}
		requirement.delete();
		flash("info", "Requirement deleted! *boom*");
		return GO_HOME;
	}

	public static Result like(Long id) {
		Requirement.like(id, getUser());
		flash("info", "Seems you like that!");
		return GO_HOME;
	}

	public static Result unlike(Long id) {
		Requirement.unlike(id, getUser());
		flash("info", "Seems you do not that anymore!");
		return GO_HOME;
	}

	// TODO refactor
	private static User getUser() {
		return User.find.byId(session("username"));
	}

}
