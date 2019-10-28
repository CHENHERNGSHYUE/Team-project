package Model;

import java.io.Serializable;

/**
 * Airplane class implements Serializable, 
 * so that state of an object can be converted into a byte stream
 * @author Group 25
 *
 */
public class Airplane implements Serializable {
	private static final long serialVersionUID = 1L;
	private String airline;
	private String departure;
	private String destination;
	private String date;
	private String time;
	private double price;
	private int availableSeats;
	boolean select;
	/**
	 * @return the select
	 */
	public boolean isSelect() {
		return select;
	}
	/**
	 * @param select the select to set
	 */
	public void setSelect(boolean select) {
		this.select = select;
	}
	/**
	 * @param departure
	 * @param destination
	 * @param date
	 * @param time
	 * @param price
	 * @param availableSeats
	 */
	public Airplane(String airline,String departure, String destination, String date, String time, double price, int availableSeats) {
		this.airline = airline;
		this.departure = departure;
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.price = price;
		this.availableSeats = availableSeats;
	}
	/**
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}
	/**
	 * @param airline the airline to set
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}
	/**
	 * @return the departure
	 */
	public String getDeparture() {
		return departure;
	}
	/**
	 * @param departure the departure to set
	 */
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the availableSeats
	 */
	public int getAvailableSeats() {
		return availableSeats;
	}
	/**
	 * @param availableSeats the availableSeats to set
	 */
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
}
