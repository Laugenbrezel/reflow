package controllers;

import static play.data.Form.form;
import models.Message;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;
import views.html.messages.create;
import views.html.messages.list;
import views.html.messages.update;

@With(SideMenu.class)
@Security.Authenticated(Secured.class)
public class Messages extends BaseController {

	private static MessageRepository REPO = new MessageRepository(
			EktorpDb.getDb());

	private static final Result GO_HOME = redirect(routes.Messages.list());
	private static final Form<Message> messageForm = Form.form(Message.class);

	public static Result newMessage() {
		return ok(create.render(messageForm));
	}

	public static Result load(String id) {
		Form<Message> messageForm = form(Message.class).fill(REPO.get(id));
		return ok(update.render(messageForm));
	}

	public static Result list() {
		return ok(list.render(REPO.getAll()));
	}

	public static Result add() {
		Form<Message> messageForm = form(Message.class).bindFromRequest();
		if (messageForm.hasErrors()) {
			flash("error", "Please correct the form below.");
			return badRequest(create.render(messageForm));
		} else {
			Message message = messageForm.get();
			// FIXME crap this is
			message.setCreatorId(getUserId());
			REPO.add(message);
			flash("success", "New Message created :)");
			return GO_HOME;
		}
	}

	public static Result update() {
		Form<Message> messageForm = form(Message.class).bindFromRequest();
		if (messageForm.hasErrors()) {
			return badRequest(update.render(messageForm));
		}
		// FIXME that is crap here? (1st try to fix)
		Message message = messageForm.get();
		message.setCreatorId(getUserId());
		REPO.update(message);
		flash("success", "Message " + messageForm.get().getId()
				+ " has been updated");
		return GO_HOME;
	}

	public static Result delete(String id) {
		final Message message = REPO.get(id);
		if (message == null) {
			return notFound(String.format("Message %s does not exists.", id));
		}
		REPO.remove(message);
		flash("info", "Message deleted! *boom*");
		return GO_HOME;
	}

}