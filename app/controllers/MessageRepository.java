package controllers;

import models.Message;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;

@View(name = "all", map = "function(doc) { if (doc.type == 'Message') { emit(null, doc) } }")
public class MessageRepository extends CouchDbRepositorySupport<Message> {

	protected MessageRepository(CouchDbConnector db) {
		super(Message.class, db);
		initStandardDesignDocument();
	}
}
