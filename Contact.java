package org.snhu.cs320.contact;

//Benjamin Sturgeon CS-320

public class Contact {
	
	//fields for contact object initialized
	private String id;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String address;
	
	//constructor created for contact object
	public Contact(String id, String firstName, String lastName, String phoneNum, String address) throws Exception {
		super();
		
		//if statement created to throw exception if id values are not valid
		if (id == null || id.trim().length() < 1 || id.length() > 10) {
			throw new Exception("invalid id"); 
		}
		this.id = id; //id constructor
		
		//setter used here for construction in order to maximize throw test efficiency by reducing redundant code
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNum(phoneNum);
		setAddress(address);
	}

	//getters and setters created for fields with Id immutable
	public String getFirstName(){
		
		return firstName;
	}


	public void setFirstName(String firstName) throws Exception {
		if (firstName == null || firstName.trim().length() < 1 || firstName.length() > 10) {
			throw new Exception("invalid firstName"); 
		}
		this.firstName = firstName;
	}


	public String getLastName(){
		return lastName;
	}


	public void setLastName(String lastName) throws Exception {
		if (lastName == null || lastName.trim().length() < 1 || lastName.length() > 10) {
			throw new Exception("invalid lastName"); 
		}
		this.lastName = lastName;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) throws Exception {
		if (phoneNum == null || phoneNum.trim().length() < 10 || phoneNum.length() > 10 || phoneNum.matches(".*\\D+.*")) {
			throw new Exception("invalid phoneNum"); 
		}
		this.phoneNum = phoneNum;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) throws Exception {
		if (address == null || address.trim().length() < 1 || address.length() > 30) {
			throw new Exception("invalid address"); 
		}
		this.address = address;
	}

	//Id field has no setter because it is immutable
	public String getId() {
		return id;
	}
	
	
	
}
