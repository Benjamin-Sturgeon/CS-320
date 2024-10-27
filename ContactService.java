package org.snhu.cs320.contact;

//Benjamin Sturgeon CS-320

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Singleton Pattern followed
public class ContactService {
	
	private static ContactService INSTANCE; //create instance
	
	Map<String, Contact> contactDatabase = new ConcurrentHashMap<>(); //utilized hashmap for efficiency, created here
	
	private ContactService() {
		
	}
	public static synchronized ContactService getInstance() { // create and return new ContactService instance only if there is not already one
		if (INSTANCE == null) {
			INSTANCE = new ContactService();
		}
		return INSTANCE;
	}
	
	//add contact method
	public boolean addContact(Contact contact) {
		return contactDatabase.putIfAbsent(contact.getId(), contact) == null;
	}
	
	//delete contact method
	public boolean deleteContact(String id) {
		return contactDatabase.remove(id) != null;
	}
	
	//update contact method
	public boolean updateContact(String id, Contact updatedContact) throws Exception {
		Contact currentContact = contactDatabase.get(id);
		
		if(currentContact == null) { //if contact is empty, contact fails to update and boolean is false
			return false;
		}
		
		//retrieve and set update contact fields
		currentContact.setFirstName(updatedContact.getFirstName());
		currentContact.setLastName(updatedContact.getLastName());
		currentContact.setPhoneNum(updatedContact.getPhoneNum());
		currentContact.setAddress(updatedContact.getAddress());
		
		return true; //successful contact update returns true
	}
}
