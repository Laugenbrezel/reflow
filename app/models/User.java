package models;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.ektorp.docref.DocumentReferences;
import org.ektorp.docref.FetchType;
import org.ektorp.support.TypeDiscriminator;

@TypeDiscriminator("doc.type == 'User'")
public class User extends DocumentEntity {

	private static final long serialVersionUID = 1L;

	public String username;

	@DocumentReferences(backReference = "creatorId", fetch = FetchType.LAZY, descendingSortOrder = true, orderBy = "title")
	public Set<Requirement> createdRequirements = new HashSet<>();

	@DocumentReferences(backReference = "creatorId", fetch = FetchType.LAZY)
	public Set<Message> messages = new HashSet<>();

	// TODO Hash password
	public String password;

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Requirement> getCreatedRequirements() {
		return createdRequirements;
	}

	public void setCreatedRequirements(Set<Requirement> createdRequirements) {
		this.createdRequirements = createdRequirements;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User == false) {
			return false;
		}
		User that = (User) obj;
		System.out.println(this.username + " vs. " + that.username);
		return StringUtils.equals(this.username, that.username);
	}

	@Override
	public String toString() {
		return this.username;
	}
}