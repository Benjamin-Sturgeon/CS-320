package org.snhu.cs320.contact;

//Benjamin Sturgeon CS-320

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ContactTest {
	
	@Test //test successful creation of contact object
	void testCreationSuccess() throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThat(contact)
			.hasFieldOrPropertyWithValue("id", "1")
			.hasFieldOrPropertyWithValue("firstName", "Benjamin")
			.hasFieldOrPropertyWithValue("lastName", "Sturgeon")
			.hasFieldOrPropertyWithValue("phoneNum", "3343729730")
			.hasFieldOrPropertyWithValue("address", "18234 John Broussard Rd");
	}
	
	@Test //test that setters correctly set contact details to correct values
	void testSettersSuccess() throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		contact.setFirstName("Larry");
		contact.setLastName("Doolittle");
		contact.setPhoneNum("2223334561");
		contact.setAddress("little duckling lane");
		
		assertThat(contact)
			.hasFieldOrPropertyWithValue("id", "1")
			.hasFieldOrPropertyWithValue("firstName", "Larry")
			.hasFieldOrPropertyWithValue("lastName", "Doolittle")
			.hasFieldOrPropertyWithValue("phoneNum", "2223334561")
			.hasFieldOrPropertyWithValue("address", "little duckling lane");
	}
	// source created to test incorrect contact entries
	@CsvSource({
		"'', Benjamin, Sturgeon, 3343729730, 18234 John Broussard Rd", 					// empty Id string
		", Benjamin, Sturgeon, 3343729730, 18234 John Broussard Rd",					// Null id
		"' ', Benjamin, Sturgeon, 3343729730, 18234 John Broussard Rd",					// blank id
		"12345678910, Benjamin, Sturgeon, 3343729730, 18234 John Broussard Rd",			// id that is too long
		"1, '', Sturgeon, 3343729730, 18234 John Broussard Rd",							// first name empty
		"1, , Sturgeon, 3343729730, 18234 John Broussard Rd",							// first name Null
		"1, Benjaminsadkfjghlkjsdhfg, Sturgeon, 3343729730, 18234 John Broussard Rd",	// first name too long
		"1, ' ', Sturgeon, 3343729730, 18234 John Broussard Rd",						// first name blank
		"1, Benjamin, '', 3343729730, 18234 John Broussard Rd",							// last name empty
		"1, Benjamin, , 3343729730, 18234 John Broussard Rd",							// last name Null
		"1, Benjamin, Sturgeonskdfjhgshdfg, 3343729730, 18234 John Broussard Rd",		// last name too long
		"1, Benjamin, ' ', 3343729730, 18234 John Broussard Rd",						// last name blank
		"1, Benjamin, Sturgeon, '', 18234 John Broussard Rd",							// phone number empty
		"1, Benjamin, Sturgeon, ' ', 18234 John Broussard Rd",							// phone number blank
		"1, Benjamin, Sturgeon, , 18234 John Broussard Rd",								// phone number Null
		"1, Benjamin, Sturgeon, 3343729730654312, 18234 John Broussard Rd",				// phone number too long
		"1, Benjamin, Sturgeon, 33437297, 18234 John Broussard Rd",						// phone number too short
		"1, Benjamin, Sturgeon, 33437297df, 18234 John Broussard Rd",					// phone number with letters
		"1, Benjamin, Sturgeon, 33437297!!, 18234 John Broussard Rd",					// phone number with punctuation
		"1, Benjamin, Sturgeon, '33437297  ', 18234 John Broussard Rd",					// phone number with spaces
		"1, Benjamin, Sturgeon, 3343729730, ''",										// address empty
		"1, Benjamin, Sturgeon, 3343729730, ' '",										// address blank
		"1, Benjamin, Sturgeon, 3343729730, ",											// address Null
		"1, Benjamin, Sturgeon, 3343729730, 18234 John Broussard Rdaspiwoertpoiwert",	// address too long
		
		
		
	})
	
	@ParameterizedTest //parameterized test used to test all values given in CsvSource
	void testCreationFailure(String id, String firstName, String lastName, String phoneNum, String address) {
		assertThatThrownBy(() -> new Contact(id, firstName, lastName, phoneNum, address))
			.isNotNull();
	}
	
	//Setter Testing 
	@CsvSource ({
		",",  						// First Name Null
		"' ',",						// First Name empty or blank
		"TooLongNAmeWayTooLong,"	// First Name too long
	})
	@ParameterizedTest //test first name setter
	void testFirstNameSetter(String firstName) throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThatThrownBy(() -> contact.setFirstName(firstName))
		.isNotNull();
	}
	
	@CsvSource ({
		",",  						// Last Name Null
		"' ',",						// Last Name empty or blank
		"TooLongNAmeWayTooLong,"	// Last Name too long
	})
	@ParameterizedTest //test last name setter
	void testLastNameSetter(String lastName) throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThatThrownBy(() -> contact.setLastName(lastName))
		.isNotNull();
	}
	
	@CsvSource ({
		",",  						// phone number Null
		"' ',",						// phone number empty or blank
		"3343729730654312,",		// phone number too long
		"33437297,",				// phone number too short
		"33437297df,",				// phone number with letters
		"33437297!!,",				// phone number with punctuation
		"'33437297  ',"				// phone number with spaces
	})
	@ParameterizedTest // test phone number setter
	void testPhoneNumSetter(String phoneNum) throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThatThrownBy(() -> contact.setPhoneNum(phoneNum))
		.isNotNull();
	}
	
	@CsvSource ({
		",",  						// Address Null
		"' ',",						// Address empty or blank
		"18234 John Broussard Rdaspiwoertpoiwert,"	// Address too long
	})
	@ParameterizedTest //test address setter
	void testAddressSetter(String address) throws Exception {
		Contact contact = new Contact("1", "Benjamin", "Sturgeon", "3343729730", "18234 John Broussard Rd");
		assertThatThrownBy(() -> contact.setAddress(address))
		.isNotNull();
	}
}
