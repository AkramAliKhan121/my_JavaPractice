package com.edubridge.contact_app.model.dao;

import java.util.List;

import com.edubridge.contact_app.model.Contact;

public interface ContactDao {

	List<Contact> getAllContacts();

	Contact getContact(String name);

	int addContact(Contact c);

	int updateContact(Contact c);

	Contact deleteContact(String deleteName);

	void deleteAllContacts();


}
