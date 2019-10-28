package Model;

import java.io.Serializable;

/**
 * This class is for users and implements Serializable, 
 * so that state of an object can be converted into a byte stream
 * @author Group 25
 *
 */
public class User implements Serializable{
	
	private String title;
	private String FirstName;
	private String LastName;
	private String gender;
	private String DateOfBirth;
	private String passportNumber;
	private String nationality;
	private String CountryOfResidence;
	private String email;
	private String address;
	private String city;
	private String zipCode;
	private String telephone;
	private String username;
	private String password;
	private String securityQuestion;
	private String securityAnswer;

	/**
	 * Constructor of User's information
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @param gender
	 * @param dateOfBirth
	 * @param passportNumber
	 * @param nationality
	 * @param countryOfResidence
	 * @param email
	 * @param address
	 * @param city
	 * @param zipCode
	 * @param telephone
	 * @param username
	 * @param password
	 * @param securityQuestion
	 * @param securityAnswer
	 */
	public User(String title, String firstName, String lastName, String gender, String dateOfBirth, String passportNumber,String nationality,
			String countryOfResidence, String email, String address, String city, String zipCode, String telephone,
			String username, String password, String securityQuestion, String securityAnswer) {
		this.title = title;
		this.FirstName = firstName;
		this.LastName = lastName;
		this.gender = gender;
		this.DateOfBirth = dateOfBirth;
		this.passportNumber = passportNumber;
		this.nationality = nationality;
		this.CountryOfResidence = countryOfResidence;
		this.email = email;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.telephone = telephone;
		this.username = username;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return user's firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * @return user's lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @return user's gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return user's dateOfBirth
	 */
	public String getDateOfBirth() {
		return DateOfBirth;
	}
	
	/**
	 * @return user's passportNumber
	 */
	public String getPassportNumber() {
		return passportNumber;
	}

	/**
	 * @return user's nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @return user's countryOfResidence
	 */
	public String getCountryOfResidence() {
		return CountryOfResidence;
	}

	/**
	 * @return user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return user's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return user's city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return user's zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @return user's telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @return user's username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return user's password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @return user's security question
	 */
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	
	/**
	 * 
	 * @return user's security answer
	 */
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	
}
