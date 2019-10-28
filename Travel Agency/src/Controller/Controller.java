package Controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Model.Database;
import Model.User;
import Model.Traveler;
import Model.Airplane;

/**
 * 
 * Controller class is defined for communication between client and server and also between
 * server and database
 * @author Group 25
 *
 */
public class Controller implements Runnable {
	
    private Socket socket;
	private boolean isRunning = true;
    
	/**
     * constructor of Controller class
     * @param socket
     */
	
	public Controller(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Connected: " + socket);
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
			socket.setTcpNoDelay(true);// to decrease the delay of communication
            while (isRunning) {//read objects from the input socket of server
            	String message[] = (String[]) in.readObject();
                
            	//list of users is written on the output of server socket to send to client
            	if(message[0].equals("List")) {
                	ArrayList<User> myList=new ArrayList<>();
                	myList.addAll((ArrayList<User>)Database.userList());
                	out.writeObject(myList);
                }
                
                // aboolean is returned for permttion of login
                if(message[0].equals("login")) {
                	boolean checkLogin = Database.checkPassword(message[1],message[2]);
                	out.writeObject(checkLogin);
                }
                
                // new user is added to database
                if(message[0].equals("register")) {
                	Database.NewUser(message[1],message[2],message[3],message[4],message[5],
                			message[6],message[7],message[8],message[9],message[10],
                			message[11],message[12],message[13],message[14],message[15],message[16],message[17]);
                }
                
                //checking availabla flights
                if(message[0].equals("checkFlights")) {
                	ArrayList<Airplane> myList2 = new ArrayList<>();
                	myList2.addAll((ArrayList<Airplane>)Database.checkFlights(message[1],message[2],message[3],message[4],message[5]));
                	out.writeObject(myList2);
                }
                
                // reseting user's password
                if(message[0].equals("reset")) {
                	Database.reset(message[1],message[2]);
                }
                
                //list of travellers is written on the output of server socket to send to client
                if(message[0].equals("TravelList")) {
                	Traveler tr = (Traveler)Database.TravelerTicketInfo(message[1]);
                	out.writeObject(tr);
                }
                
                // a boolean is returned to check whether this ticket number exists ot not
                if(message[0].equals("meal")) {
                	boolean checkTicket = Database.checkTicketNumber(message[1]);
                	out.writeObject(checkTicket);
                }
                
                //the meal that user orders will be added to the database
                if(message[0].equals("mealList")) {
                	Database.NewMeal(message[1],message[2]);
                }
                
                //database is updated with new ticket
                if(message[0].equals("OneWayUpdate")) {
                	Database.UpdateSeats(message[1],message[2],message[3],message[4],message[5]);
                }
                
                //database is updated with new ticket
                if(message[0].equals("ReturnUpdate")) {
                	Database.UpdateSeats(message[1],message[2],message[3],message[5],message[7]);
                	Database.UpdateSeats(message[2],message[1],message[4],message[6],message[8]);
                }
                
                //booking a flight, and a new traveler is added to the database
                if(message[0].equals("booking")) {
                	Database.NewTraveler(message[1],message[2],message[3],message[4],message[5],
                			message[6],message[7],message[8],message[9],message[10],
                			message[11],message[12],message[13],message[14],message[15],message[16],
                			message[17],message[18],message[19],message[20]);
                }
                
            }
        } catch (Exception e) {
            isRunning = false;
        } 
    }



}

