package controllers;

import java.io.Serializable;

import models.Requirement;
import models.User;

public class SideMenuItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public User user;
	public int countMyRequirements;

	public SideMenuItem(User user) {
		this.user = user;
		// TODO implement
		this.countMyRequirements = Requirement.findCreatedBy(user).size();
	}
}
