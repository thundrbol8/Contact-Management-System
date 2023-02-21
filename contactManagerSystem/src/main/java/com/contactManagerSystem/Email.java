package com.contactManagerSystem;

import jakarta.persistence.*;

@Entity
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	public String useremail , contactemail;
	
	public Email() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getContactemail() {
		return contactemail;
	}

	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}


	@Override
	public String toString() {
		return "Email [id=" + id + ", useremail=" + useremail + ", contactemail=" + contactemail + "]";
	}
}
