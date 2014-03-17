package controllers;

import static play.data.Form.form;
import models.Requirement;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.requirements.create;
import views.html.requirements.list;
import views.html.requirements.update;

@With(SideMenu.class)
@Security.Authenticated(Secured.class)
public class Requirements extends BaseController {

	private static RequirementRepository REPO = new RequirementRepository(
			EktorpDb.getDb());
	private static final Result GO_HOME = redirect(routes.Requirements.list());
	private static final Form<Requirement> requirementForm = Form
			.form(Requirement.class);

	public static Result newRequirement() {
		return ok(create.render(requirementForm));
	}

	public static Result load(String id) {
		Form<Requirement> requirementForm = form(Requirement.class).fill(
				REPO.get(id));
		return ok(update.render(requirementForm));
	}

	public static Result list() {
		return ok(list.render(REPO.getAll()));
	}

	public static Result add() {
		Form<Requirement> requirementForm = form(Requirement.class)
				.bindFromRequest();
		if (requirementForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(create.render(requirementForm));
		} else {
			Requirement requirement = requirementForm.get();
			requirement.creatorId = getUserId();
			// FIXME crap this is
			REPO.add(requirement);
			flash("success", "New Requirement created :)");
			return GO_HOME;
		}
	}

	public static Result update() {
		Form<Requirement> requirementForm = form(Requirement.class)
				.bindFromRequest();
		if (requirementForm.hasErrors()) {
			return badRequest(update.render(requirementForm));
		}
		Requirement requirement = requirementForm.get();
		requirement.creatorId = getUserId();
		REPO.update(requirement);
		flash("success", "Requirement " + requirementForm.get().getId()
				+ " has been updated");
		return GO_HOME;
	}

	public static Result delete(String id) {
		final Requirement requirement = REPO.get(id);
		if (requirement == null) {
			return notFound(String
					.format("Requirement %s does not exists.", id));
		}
		REPO.remove(requirement);
		flash("info", "Requirement deleted! *boom*");
		return GO_HOME;
	}

	public static Result like(String id) {
		REPO.like(id, getUserId());
		flash("info", "Seems you like that!");
		return GO_HOME;
	}

	public static Result unlike(String id) {
		REPO.unlike(id, getUserId());
		flash("info", "Seems you do not like that anymore!");
		return GO_HOME;
	}

}
