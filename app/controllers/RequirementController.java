package controllers;

import models.Requirement;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.requirement.*;

public class RequirementController extends Controller {

	public static Result list() {
		return ok(list.render(Requirement.find.all()));
	}
}
