package Model;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Controller.Controller;

/**
 * this class includes main method of server
 * @author Group 25
 *
 */
public class ServerMain {

	private static boolean isRunning = true;
   
    //open server
	public static void main(String[] args) throws Exception {
		//creating server socket with port 7766
		try (ServerSocket server = new ServerSocket(7777)) {
            System.out.println("The server is running...");
            // creating pool of threads
            ExecutorService pool = Executors.newFixedThreadPool(20);
            while (isRunning) {
            	//server accepts the request from client and turn over to thread
            	pool.execute(new Controller(server.accept()));
                if(server.isClosed()) {
                	isRunning = false;
                }
            }
        } catch(Exception e) {
        	System.out.print("Server opening error!");
        }
    }
}
