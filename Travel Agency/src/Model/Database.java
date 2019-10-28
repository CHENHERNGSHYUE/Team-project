package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Database class which have many function for coping with the server request
 * @author Group 25
 *
 */
public class Database {
	
	private static String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/group25";
	private static Connection connection;
	
	// connect to database
	public static void main(String[]args) throws SQLException {
		connection = DriverManager.getConnection(url, "group25", "uwnzx8afju");
		Statement st = connection.createStatement();	
	}
	
	// create new user's information
	public static void NewUser(String title,String firstname,String lastname,String gender,
			String DoB,String passportNO, String nationality, String country, String email,
			String address, String city, String zipcode, String telephone, String username,
			String password, String securityQuestion, String securityAnswer) {
		try {
			String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/group25";
			Connection	conn = DriverManager.getConnection(url, "group25", "uwnzx8afju");
			
			String IQuery = "INSERT INTO Users (title,firstname,lastname,gender,DoB,passportNumber,nationality,"
					+ "country,email,address,city,zipcode,telephone,username,password,security_question,"
					+ "security_answer) "+"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(IQuery);
		    pst.setString(1, title);
		    pst.setString(2, firstname);
		    pst.setString(3, lastname);
		    pst.setString(4, gender);
		    pst.setString(5, DoB);
		    pst.setString(6, passportNO);
		    pst.setString(7, nationality);
		    pst.setString(8, country);
		    pst.setString(9, email);
		    pst.setString(10, address);
		    pst.setString(11, city);
		    pst.setString(12, zipcode);
		    pst.setString(13, telephone);
		    pst.setString(14, username);
		    pst.setString(15, password);
		    pst.setString(16, securityQuestion);
		    pst.setString(17, securityAnswer);
		    pst.executeUpdate();
			 
		    conn.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	//This method adds new traveler to the Tickets table in our database
	public static void NewTraveler(String ticketNo,String firstname,String lastname,
			String passportNO,String DoB, String nationality, String dep, String des,
			String from, String to, String time1,String time2, String type, String meal,
			String seat1,String seat2, String airline1,String airline2,String available1,
			String available2) {
		try {
			String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/group25";
			Connection	conn = DriverManager.getConnection(url, "group25", "uwnzx8afju");
			
			String IQuery = "INSERT INTO Tickets (ticketNumber,firstname,lastname,passportNumber,DateOfBirth,"
					+ "nationality, departure,destination,FromDate,ToDate,time1,time2,travelType,"
					+ "meal,SeatNumber1,SeatNumber2,AirLine1,AirLine2,AvailableSeats1,AvailableSeats2)"+
							"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(IQuery);
		    pst.setString(1, ticketNo);
		    pst.setString(2, firstname);
		    pst.setString(3, lastname);
		    pst.setString(4, passportNO);
		    pst.setString(5, DoB);
		    pst.setString(6, nationality);
		    pst.setString(7, dep);
		    pst.setString(8, des);
		    pst.setString(9, from);
		    pst.setString(10, to);
		    pst.setString(11, time1);
		    pst.setString(12, time2);
		    pst.setString(13, type);
		    pst.setString(14, meal);
		    pst.setString(15, seat1);
		    pst.setString(16, seat2);
		    pst.setString(17, airline1);
		    pst.setString(18, airline2);
		    pst.setString(19, available1);
		    pst.setString(20, available2);
		    pst.executeUpdate();			 
			//close connection
			conn.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	//This method return a boolean to check whether the password is correct or not
	public static boolean checkPassword(String username, String password) throws SQLException, ClassNotFoundException {
		boolean check = false;
		ArrayList<User> ListOfUsers = new ArrayList<>();
        ListOfUsers.addAll(Database.userList());
        for(int i=0;i<ListOfUsers.size();i++) {
        	if(ListOfUsers.get(i).getUsername().equals(username)&&ListOfUsers.get(i).getPassword().equals(password)) {
        		check=true;
        		User user =new User(ListOfUsers.get(i).getTitle(),ListOfUsers.get(i).getFirstName(),ListOfUsers.get(i).getLastName(),
        				ListOfUsers.get(i).getGender(),ListOfUsers.get(i).getDateOfBirth(),ListOfUsers.get(i).getPassportNumber(),
        				ListOfUsers.get(i).getNationality(),ListOfUsers.get(i).getCountryOfResidence(),ListOfUsers.get(i).getEmail(),
        				ListOfUsers.get(i).getAddress(),ListOfUsers.get(i).getCity(),ListOfUsers.get(i).getZipCode(),
        				ListOfUsers.get(i).getTelephone(),ListOfUsers.get(i).getUsername(),ListOfUsers.get(i).getPassword(), ListOfUsers.get(i).getSecurityQuestion(), ListOfUsers.get(i).getSecurityAnswer());
        	}
        }
		return check;
	}
	
	// This method reset User's password
	public static void reset(String username, String password) throws SQLException {
		String sql = "UPDATE users SET password = ? WHERE username = ? ";
		connection = DriverManager.getConnection(url, "group25", "uwnzx8afju");
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setString(2, username);
        pstmt.setString(1, password);
        pstmt.executeUpdate();
		
	}	
	
	//This method checks available flights in our database
	public static ArrayList<Airplane> checkFlights(String travelType, String departure,
			String destination,String Fromdate,String Todate) throws SQLException, 
	ClassNotFoundException {
		String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/group25";
		Connection	conn = DriverManager.getConnection(url, "group25", "uwnzx8afju");
		String selectSQL="";
		PreparedStatement pst=null;
		ResultSet rs = null;
		ArrayList<Airplane> airplanes = new ArrayList<>();
		if(travelType.equals("One Way")) {
			selectSQL= "SELECT * FROM BritishAirWays WHERE availableSeats>0 AND departure = ? AND destination = ? AND date = ? ORDER BY price";
			pst = conn.prepareStatement(selectSQL);
 			pst.setString(1, departure );
 			pst.setString(2,destination);
 			pst.setString(3,Fromdate );
 			rs = pst.executeQuery();
			while (rs.next()) {
				Airplane airplane = new Airplane(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
				airplanes.add(airplane);
			}
			selectSQL= "SELECT * FROM AmericanAirLine WHERE availableSeats>0 AND departure = ? AND destination = ? AND date = ? ORDER BY price";
			pst = conn.prepareStatement(selectSQL);
 			pst.setString(1, departure );
 			pst.setString(2,destination);
 			pst.setString(3,Fromdate );
 			rs = pst.executeQuery();
			while (rs.next()) {
				Airplane airplane = new Airplane(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
				airplanes.add(airplane);
			}
			selectSQL= "SELECT * FROM CanadianAirLine WHERE availableSeats>0 AND departure = ? AND destination = ? AND date = ? ORDER BY price";
			pst = conn.prepareStatement(selectSQL);
 			pst.setString(1, departure );
 			pst.setString(2,destination);
 			pst.setString(3,Fromdate );
 			rs = pst.executeQuery();
			while (rs.next()) {
				Airplane airplane = new Airplane(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
				airplanes.add(airplane);
			}
		}
		
		else{
			selectSQL= "SELECT * FROM BritishAirWays WHERE availableSeats>0  AND ((departure = ? AND destination = ? AND date = ?) OR (departure = ? AND destination = ? AND Date = ?)) ORDER BY price";
			pst = conn.prepareStatement(selectSQL);
			pst.setString(1,departure);
 			pst.setString(2, destination);
 			pst.setString(3,Fromdate);
 			pst.setString(4,destination );
 			pst.setString(5,departure);
 			pst.setString(6, Todate);
 			rs = pst.executeQuery();
 			while (rs.next()) {
				Airplane airplane = new Airplane(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
				airplanes.add(airplane);
			}
 			selectSQL= "SELECT * FROM AmericanAirLine WHERE availableSeats>0  AND ((departure = ? AND destination = ? AND date = ?) OR (departure = ? AND destination = ? AND Date = ?)) ORDER BY price";
			pst = conn.prepareStatement(selectSQL);
			pst.setString(1,departure);
 			pst.setString(2, destination);
 			pst.setString(3,Fromdate);
 			pst.setString(4,destination );
 			pst.setString(5,departure);
 			pst.setString(6, Todate);
 			rs = pst.executeQuery();
 			while (rs.next()) {
				Airplane airplane = new Airplane(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
				airplanes.add(airplane);
			}
 			selectSQL= "SELECT * FROM CanadianAirLine WHERE availableSeats>0  AND ((departure = ? AND destination = ? AND date = ?) OR (departure = ? AND destination = ? AND Date = ?)) ORDER BY price";
			pst = conn.prepareStatement(selectSQL);
			pst.setString(1,departure);
 			pst.setString(2, destination);
 			pst.setString(3,Fromdate);
 			pst.setString(4,destination );
 			pst.setString(5,departure);
 			pst.setString(6, Todate);
 			rs = pst.executeQuery();
 			while (rs.next()) {
				Airplane airplane = new Airplane(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7));
				airplanes.add(airplane);
			}
		}
		return airplanes;
	}	
	
	/*
	 * userList method searches in Users table for all the users and create
	 *  an object of user and adds it the ArrayList of users
	 */
	public static ArrayList<User> userList(){
		ArrayList<User> myList=new ArrayList<>();
		try {
			String selectSQL = "SELECT * FROM users";
			connection = DriverManager.getConnection(url, "group25", "uwnzx8afju");
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(selectSQL);
			User user;
			while (rs.next()) {
    		    user =new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
    		    		rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
    		    		rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
    		    		rs.getString(15), rs.getString(16), rs.getString(17));
    		    myList.add(user);
    		}
			connection.close();
		}
		catch (SQLException e1) {
		    e1.printStackTrace();
		}
		return myList;
	}
	
	/*
	 * checkTicketNumber method checks the ticket number in the tickets table and returns
	 * of the ticket number is available in this table and returns false if it's not available
	 */
	public static Boolean checkTicketNumber(String number){
		boolean confirm =false;
		try {
			String selectSQL = "SELECT ticketNumber FROM Tickets WHERE ticketNumber=?";
			connection = DriverManager.getConnection(url, "group25", "uwnzx8afju");
			PreparedStatement pst = connection.prepareStatement(selectSQL);
 			pst.setString(1, number);
 			ResultSet rs = pst.executeQuery();
 			while(rs.next()) {
 				confirm = true;
 			}
			connection.close();
		}
		catch (SQLException e1) {
		    e1.printStackTrace();
		}
		return confirm;
	}
	
	/*
	 * This method gets the ticket number and searches it in ticket table in our database
	 * if this ticket number is available in this table then it returns all the 
	 * information about this ticket
	 */
	public static Traveler TravelerTicketInfo(String Num){
		Traveler traveler = null;
		try {
			String selectSQL = "SELECT * FROM Tickets WHERE ticketNumber =?";
			connection = DriverManager.getConnection(url, "group25", "uwnzx8afju");
			PreparedStatement pst = connection.prepareStatement(selectSQL);
			pst.setString(1, Num);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
    		   traveler =new Traveler(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
    		    		rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
    		    		rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
    		    		rs.getString(15), rs.getString(16), rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20));
    		    
    		}
			connection.close();
		}
		catch (SQLException e1) {
		    e1.printStackTrace();
		}
		return traveler;
	}	
	
	/*
	 * This method updates the airlines table. when a ticket is booked, available seats of
	 * that airline will be decreased by one
	 */
	public static void UpdateSeats(String dep,String des,String date, String airline,String seat) {
		try {
			int temp = Integer.parseInt(seat)-1;
			String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/group25";
			Connection	conn = DriverManager.getConnection(url, "group25", "uwnzx8afju");
			
			if(airline.equals("BritishAirWays")) {
				String IQuery = "UPDATE BritishAirWays SET availableSeats=? WHERE departure=? AND destination=? AND date=?";
				PreparedStatement pst = conn.prepareStatement(IQuery);
			    pst.setInt(1, temp);
			    pst.setString(2, dep);
			    pst.setString(3, des);
			    pst.setString(4, date);
			    pst.executeUpdate();
			}
			else if(airline.equals("AmericanAirLine")) {
					String IQuery = "UPDATE AmericanAirLine SET availableSeats=? WHERE departure=? AND destination=? AND date=?";
					PreparedStatement pst = conn.prepareStatement(IQuery);
				    pst.setInt(1, temp);
				    pst.setString(2, dep);
				    pst.setString(3, des);
				    pst.setString(4, date);
				    pst.executeUpdate();
			}
			else {
				String IQuery = "UPDATE CanadianAirLine SET availableSeats=? WHERE departure=? AND destination=? AND date=?";
				PreparedStatement pst = conn.prepareStatement(IQuery);
			    pst.setInt(1, temp);
			    pst.setString(2, dep);
			    pst.setString(3, des);
			    pst.setString(4, date);
			    pst.executeUpdate();
			}
			 
			 //close connection
			 conn.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}	
	
	/*
	 * This method update the Tickets table with the new meal which user has ordered
	 * First, this method gets the ticket number and searches it in the table. If this
	 * ticket number is available then meal will be added to the table
	 */
	public static void NewMeal(String meal,String tnumber) {
		try {
			String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/group25";
			Connection	conn = DriverManager.getConnection(url, "group25", "uwnzx8afju");
			
			String IQuery = "UPDATE Tickets SET meal=? WHERE ticketNumber=?";
			PreparedStatement pst = conn.prepareStatement(IQuery);
		    pst.setString(1, meal);
		    pst.setString(2, tnumber);
		    pst.executeUpdate();
			 
			 //close connection
			 conn.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}
}