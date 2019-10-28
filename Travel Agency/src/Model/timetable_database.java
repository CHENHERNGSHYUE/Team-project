package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
/**
 * This class is used to build up the databases
 * @author Group 25
 *
 */
public class timetable_database {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/group25";
		String user = "group25";
		String password = "uwnzx8afju";
		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stm = conn.createStatement();	
		
		try
		{
/**
  * create airline timetable
 **/				
//			String sql = "CREATE TABLE americanairline (airline VARCHAR(40), departure VARCHAR(40), destination VARCHAR(40), " + 
//	                   " date VARCHAR(40), time VARCHAR(40), price DOUBLE PRECISION, availableseats INTEGER)"; 	
//			stm.executeUpdate(sql);

			
/**
  * delete airline timetable
 **/	
//			stm.executeUpdate("DROP table americanairline;");
			
			
/**
  * insert flight data to timetable
 **/
//			PreparedStatement insertStm = conn.prepareStatement("INSERT INTO BritishAirWays (airline, departure, destination,"
//					+ " date, time, price, availableSeats) VALUES(?, ?, ?, ?, ?, ?, ?)");
//			String[] departure = {"London","Paris","Amsterdam","Munich","Berlin","Birmingham","Rome","Milan",
//								  "Athens","Vienna","Prague","Stockholm","Copenhagen","Bucharest","Sofia",
//								  "Kiev","Nicosia","Madrid","Washington","New York","Sydney"};
//			String[] date = {"2019-05-01","2019-05-02","2019-05-03","2019-05-04","2019-05-05",
//							 "2019-05-06","2019-05-07","2019-05-08","2019-05-09","2019-05-10",
//							 "2019-05-11","2019-05-12","2019-05-13","2019-05-14","2019-05-15",
//							 "2019-05-16","2019-05-17","2019-05-18","2019-05-19","2019-05-20",
//							 "2019-05-21","2019-05-22","2019-05-23","2019-05-24","2019-05-25",
//							 "2019-05-26","2019-05-27","2019-05-28","2019-05-29","2019-05-30","2019-05-31"};
//			String[] time = {"07:00","08:10","09:20","10:30","11:40","12:50",
//						     "13:00","14:10","15:20","16:30","17:40","18:50",
//						     "19:05","20:15","21:25","22:35","23:45","00:55",
//						     "01:00","02:10","03:20","04:30","05:40","06:50",
//						     "07:35","08:45","09:55","11:05","12:15","13:25"};
//			double[] price = {99,137,159,169,182,199,217,230,255,274,289,304,336,354,379,385,420,429,447,468,499};
//			
//			for(int i=0;i<departure.length;i++) {
//				for(int j=0;j<departure.length;j++ ) {
//					for (int k=0;k<date.length;k++) {	
//						insertStm.setString(1, "British AirWays");
//						insertStm.setString(2, departure[i]);
//						insertStm.setString(3, departure[j]);
//						insertStm.setString(4, date[k]);
//						insertStm.setString(5, time[new Random().nextInt(time.length)]);
//						insertStm.setDouble(6, price[new Random().nextInt(price.length)]);
//						insertStm.setInt(7, (int)Math.ceil(Math.random()*100));
//						insertStm.executeUpdate();
//					}
//				}		
//			}

			
/**
  * delete flight data from timetable
**/
//			PreparedStatement deleteStm = conn.prepareStatement("DELETE FROM BritishAirWays WHERE departure = destination");
//			PreparedStatement deleteStm = conn.prepareStatement("DELETE FROM BritishAirWays WHERE date like '2019-05-%'");
//			PreparedStatement deleteStm = conn.prepareStatement("DELETE FROM canadianairline");
//			deleteStm.executeUpdate();
	
			
/**
  * Query flight data from timetable
**/			
			String insertQuery = "select * from BritishAirWays where departure = ? and destination = ? and date = ? union all " +
				            	 "select * from canadianAirline where departure = ? and destination = ? and date = ? union all " + 
				            	 "select * from americanairline where departure = ? and destination = ? and date = ?";
			PreparedStatement selectStm = conn.prepareStatement(insertQuery);
			selectStm.setString(1, "Athens");
			selectStm.setString(2, "Madrid");
			selectStm.setString(3, "2019-05-27");
			selectStm.setString(4, "Athens");
			selectStm.setString(5, "Madrid");
			selectStm.setString(6, "2019-05-27");
			selectStm.setString(7, "Athens");
			selectStm.setString(8, "Madrid");
			selectStm.setString(9, "2019-05-27");
			ResultSet rs =selectStm.executeQuery();
			System.out.println("The one-way flight searching result as follows:");
			if (rs.next() == false) {
				System.out.println("\tSorry, we can't find suitable flight for you. \n\t" + "Please try other departing date.");
			}
			else {
				do {
					String airline = rs.getString(1);
					String departureselect = rs.getString(2);
					String destinationselect = rs.getString(3);
					String dateselect = rs.getString(4);
					String timeselect = rs.getString(5);
					double priceselect = rs.getDouble(6);
					int availableseats = rs.getInt(7);
					System.out.println("\t airline = " + airline + "\t departure = " + departureselect + 
					     "; destination = " + destinationselect + "; date = " + dateselect + "; time = " + timeselect + 
						 "; price = " +"\u00A3 " + priceselect + "; availableseats = " + availableseats);
				}
				while(rs.next()); 
			}

	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stm!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	}//end main
}//end	
	