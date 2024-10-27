package org.snhu.cs320.contact;

//Benjamin Sturgeon CS-320

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
	
	@BeforeEach //clears database before each test
	void init() {
		ContactService.getInstance().contactDatabase.clear();
	}
	@Test //test get instance of Contact
	void testGetInstance() {
		assertThat(ContactService.getInstance()).isNotNull();
	}
	@Test //test adding a contact
	void testAddContact() throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThat(ContactService.getInstance().addContact(contact)).isTrue();
		assertThat(ContactService.getInstance().contactDatabase)
			.containsEntry("1", contact);
	}
	
	@Test //test failure to add a contact
	void testFailAddContact() throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThat(ContactService.getInstance().addContact(contact)).isTrue();
		
		//try to add again with contact already present, should fail
		assertThat(ContactService.getInstance().addContact(contact)).isFalse();
	}
	
	@Test //test deleting a contact
	void testDeleteContact() throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThat(ContactService.getInstance().addContact(contact)).isTrue();
		assertThat(ContactService.getInstance().deleteContact("1")).isTrue();
		assertThat(ContactService.getInstance().contactDatabase)
			.doesNotContainEntry("1", contact);
	}
	
	@Test //test failure to delete a contact
	void testFailDeleteContact() throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThat(ContactService.getInstance().addContact(contact)).isTrue();
		//now try to delete a contact with incorrect information or missing information, should fail
		assertThat(ContactService.getInstance().deleteContact("2")).isFalse();
	}
	@Test //test updating a contact
	void testUpdateContact() throws Exception {
		//add contact for testing
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThat(ContactService.getInstance().addContact(contact)).isTrue();
		
		Contact updatedContact = new Contact("1", "Killian", "Hemsworth", "2253370832", "Silly Lane");
		assertThat(ContactService.getInstance().updateContact("1", updatedContact)).isTrue();
		assertThat(ContactService.getInstance().contactDatabase)
			.extracting("1") //extract contact from database
			.hasFieldOrPropertyWithValue("firstName", "Killian") //check that contact contains updated fields
			.hasFieldOrPropertyWithValue("lastName", "Hemsworth")
			.hasFieldOrPropertyWithValue("phoneNum", "2253370832")
			.hasFieldOrPropertyWithValue("address", "Silly Lane");
	}
	
	@Test //test failure updating a contact
	void testFailUpdateContact() throws Exception {
		// no contact present in system with this id so it will be null and should fail
		Contact updatedContact = new Contact("1", "Killian", "Hemsworth", "2253370832", "Silly Lane");
		assertThat(ContactService.getInstance().updateContact("1", updatedContact)).isFalse();
	}
	
}
