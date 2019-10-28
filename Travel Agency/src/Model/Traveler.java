package Model;

import java.io.Serializable;

/**
 * This class is for users who bought ticket. an object of this class is created for 
 * each users who bought ticket and their information is saved in our database
 * @author Group 25
 *
 */
public class Traveler implements Serializable {
	
	private String TravelerTicketNumber;
	private String TravelerFirstName;
	private String TravelerLastname;
	private String TravelerPassportNumber;
	private String TravelerDateOfBirth;
	private String TravelerNationality;
	private String TravelerDeparture;
	private String TravelerDestination;
	private String TravelFromDate;
	private String TravelToDate;
	private String TravelTime1;
	private String TravelTime2;
	private String TravelerTravelType;
	private String TravelerMeal;
	private String TravelerSeatNumber1;
	private String TravelerSeatNumber2;
	private String AirlineName1;
	private String AirlineName2;
	private String AvailableSeats1;
	private String AvailableSeats2;
	
	/**
	 * Constructor of Traveler class
	 * @param travelerTicketNumber
	 * @param travelerFirstName
	 * @param travelerLastname
	 * @param travelerPassportNumber
	 * @param travelerDateOfBirth
	 * @param travelerDeparture
	 * @param travelerDestination
	 * @param travelDate
	 * @param travelTime
	 * @param travelerMeal
	 * @param travelerSeatNumber
	 */
	public Traveler(String travelerTicketNumber, String travelerFirstName, String travelerLastname,
			String travelerPassportNumber, String travelerDateOfBirth,String travelerNationality ,String travelerDeparture,
			String travelerDestination, String travelFromDate, String travelToDate, String travelTime1, String travelTime2, String travelerTraveltype, String travelerMeal,
			String travelerSeatNumber1,String travelerSeatNumber2, String airlineName1,String airlineName2, String AvailableSeats1,String AvailableSeats2) {
		this.TravelerTicketNumber = travelerTicketNumber;
		this.TravelerFirstName = travelerFirstName;
		this.TravelerLastname = travelerLastname;
		this.TravelerPassportNumber = travelerPassportNumber;
		this.TravelerDateOfBirth = travelerDateOfBirth;
		this.TravelerNationality = travelerNationality;
		this.TravelerDeparture = travelerDeparture;
		this.TravelerDestination = travelerDestination;
		this.TravelFromDate = travelFromDate;
		this.TravelToDate = travelToDate;
		this.TravelTime1 = travelTime1;
		this.TravelTime2 = travelTime2;
		this.TravelerTravelType = travelerTraveltype;
		this.TravelerMeal = travelerMeal;
		this.TravelerSeatNumber1 = travelerSeatNumber1;
		this.TravelerSeatNumber2 = travelerSeatNumber2;
		this.AirlineName1 = airlineName1;
		this.AirlineName2 = airlineName2;
		this.AvailableSeats1 = AvailableSeats1;
		this.AvailableSeats2 = AvailableSeats2;
	}
	
	/**
	 * @return the travelTime2
	 */
	public String getTravelTime2() {
		return TravelTime2;
	}
	
	/**
	 * @param travelTime2 the travelTime2 to set
	 */
	public void setTravelTime2(String travelTime2) {
		TravelTime2 = travelTime2;
	}
	
	/**
	 * @return the availableSeats2
	 */
	public String getAvailableSeats2() {
		return AvailableSeats2;
	}
	
	/**
	 * @param availableSeats2 the availableSeats2 to set
	 */
	public void setAvailableSeats2(String availableSeats2) {
		AvailableSeats2 = availableSeats2;
	}
	
	/**
	 * @return the airlineName
	 */
	public String getAirlineName1() {
		return AirlineName1;
	}
	
	/**
	 * @param airlineName the airlineName to set
	 */
	public void setAirlineName1(String airlineName1) {
		AirlineName1 = airlineName1;
	}
	
	/**
	 * @return the availableSeats
	 */
	public String getAvailableSeats1() {
		return AvailableSeats1;
	}
	
	/**
	 * @param availableSeats the availableSeats to set
	 */
	public void setAvailableSeats1(String availableSeats1) {
		AvailableSeats1 = availableSeats1;
	}
	
	/**
	 * @return the travelerTravelType
	 */
	public String getTravelerTravelType() {
		return TravelerTravelType;
	}
	
	/**
	 * @param travelerTravelType the travelerTravelType to set
	 */
	public void setTravelerTravelType(String travelerTravelType) {
		TravelerTravelType = travelerTravelType;
	}
	
	/**
	 * @return the travelerNationality
	 */
	public String getTravelerNationality() {
		return TravelerNationality;
	}
	
	/**
	 * @param travelerNationality the travelerNationality to set
	 */
	public void setTravelerNationality(String travelerNationality) {
		TravelerNationality = travelerNationality;
	}
	
	/**
	 * @return the travelerTicketNumber
	 */
	public String getTravelerTicketNumber() {
		return TravelerTicketNumber;
	}
	
	/**
	 * @param travelerTicketNumber the travelerTicketNumber to set
	 */
	public void setTravelerTicketNumber(String travelerTicketNumber) {
		TravelerTicketNumber = travelerTicketNumber;
	}
	
	/**
	 * @return the travelerFirstName
	 */
	public String getTravelerFirstName() {
		return TravelerFirstName;
	}
	
	/**
	 * @param travelerFirstName the travelerFirstName to set
	 */
	public void setTravelerFirstName(String travelerFirstName) {
		TravelerFirstName = travelerFirstName;
	}
	
	/**
	 * @return the travelerLastname
	 */
	public String getTravelerLastname() {
		return TravelerLastname;
	}
	
	/**
	 * @param travelerLastname the travelerLastname to set
	 */
	public void setTravelerLastname(String travelerLastname) {
		TravelerLastname = travelerLastname;
	}
	
	/**
	 * @return the travelerPassportNumber
	 */
	public String getTravelerPassportNumber() {
		return TravelerPassportNumber;
	}
	
	/**
	 * @param travelerPassportNumber the travelerPassportNumber to set
	 */
	public void setTravelerPassportNumber(String travelerPassportNumber) {
		TravelerPassportNumber = travelerPassportNumber;
	}
	
	/**
	 * @return the travelerDateOfBirth
	 */
	public String getTravelerDateOfBirth() {
		return TravelerDateOfBirth;
	}
	
	/**
	 * @param travelerDateOfBirth the travelerDateOfBirth to set
	 */
	public void setTravelerDateOfBirth(String travelerDateOfBirth) {
		TravelerDateOfBirth = travelerDateOfBirth;
	}
	
	/**
	 * @return the travelerDeparture
	 */
	public String getTravelerDeparture() {
		return TravelerDeparture;
	}
	
	/**
	 * @param travelerDeparture the travelerDeparture to set
	 */
	public void setTravelerDeparture(String travelerDeparture) {
		TravelerDeparture = travelerDeparture;
	}
	
	/**
	 * @return the travelerDestination
	 */
	public String getTravelerDestination() {
		return TravelerDestination;
	}
	
	/**
	 * @param travelerDestination the travelerDestination to set
	 */
	public void setTravelerDestination(String travelerDestination) {
		TravelerDestination = travelerDestination;
	}
	
	/**
	 * @return the travelTime
	 */
	public String getTravelTime1() {
		return TravelTime1;
	}
	
	/**
	 * @param travelTime the travelTime to set
	 */
	public void setTravelTime1(String travelTime1) {
		TravelTime1 = travelTime1;
	}
	
	/**
	 * @return the travelFromDate
	 */
	public String getTravelFromDate() {
		return TravelFromDate;
	}
	
	/**
	 * @param travelFromDate the travelFromDate to set
	 */
	public void setTravelFromDate(String travelFromDate) {
		TravelFromDate = travelFromDate;
	}
	
	/**
	 * @return the travelToDate
	 */
	public String getTravelToDate() {
		return TravelToDate;
	}
	
	/**
	 * @param travelToDate the travelToDate to set
	 */
	public void setTravelToDate(String travelToDate) {
		TravelToDate = travelToDate;
	}
	
	/**
	 * @return the travelerMeal
	 */
	public String getTravelerMeal() {
		return TravelerMeal;
	}
	
	/**
	 * @param travelerMeal the travelerMeal to set
	 */
	public void setTravelerMeal(String travelerMeal) {
		TravelerMeal = travelerMeal;
	}
	
	/**
	 * @return the travelerSeatNumber
	 */
	public String getTravelerSeatNumber1() {
		return TravelerSeatNumber1;
	}
	
	/**
	 * @param travelerSeatNumber the travelerSeatNumber to set
	 */
	public void setTravelerSeatNumber1(String travelerSeatNumber1) {
		TravelerSeatNumber1 = travelerSeatNumber1;
	}
	
	/**
	 * @return the travelerSeatNumber2
	 */
	public String getTravelerSeatNumber2() {
		return TravelerSeatNumber2;
	}
	
	/**
	 * @param travelerSeatNumber2 the travelerSeatNumber2 to set
	 */
	public void setTravelerSeatNumber2(String travelerSeatNumber2) {
		TravelerSeatNumber2 = travelerSeatNumber2;
	}
	
	/**
	 * @return the airlineName2
	 */
	public String getAirlineName2() {
		return AirlineName2;
	}
	
	/**
	 * @param airlineName2 the airlineName2 to set
	 */
	public void setAirlineName2(String airlineName2) {
		AirlineName2 = airlineName2;
	}
}
