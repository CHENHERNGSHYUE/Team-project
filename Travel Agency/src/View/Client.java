package View;

import javafx.application.Application;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Model.User;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This class shows a home page of our flight booking system
 * @author Group 25
 *
 */
         
public class Client extends Application{ 
	
	protected static Stage PrimaryStage;
	protected static User OnlineUser;
	protected static ArrayList<User> UsersList=new ArrayList<>();
	protected static Socket client;
	protected static ObjectInputStream dis;
	protected static ObjectOutputStream dos;
	
    /**
     *  start method
     * 
     *  The fields and buttons are added to a GridPane, which is added
     *  to the Scene.
     *  @param stage The window to be displayed.
     */
	
    @Override 
    public void start(Stage stage) throws IOException { 
    	try {
    		//list of users are read from server and server from databases
			String [] entities= {"List"};
			dos.writeObject(entities);
			UsersList.addAll((ArrayList<User>) dis.readObject());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	PrimaryStage = stage;
    	Font myFont = new Font("Segoe Print", 40);
		Text travelAgency = new Text("Everywhere Travel Agency");
		travelAgency.setFill(Color.DARKTURQUOISE);
		travelAgency.setFont(myFont);
        Text usernameText = new Text("Username");       
        //creating label password 
        Text passwordText = new Text("Password"); 
        //Creating text field for userame
        TextField usernameTextField = new TextField(); 
        //Creating text field for password        
        PasswordField passwordTextField = new PasswordField();
        Button loginButton = new Button("Login");
        loginButton.setCursor(Cursor.HAND);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Alert WarningAlert = new Alert(AlertType.ERROR);
        		WarningAlert.setTitle("Warning!");
        		
        		//to check if all the blanks are filled or not
        		if(usernameTextField.getText().equals("") || passwordTextField.getText().equals("")) {
            		WarningAlert.setContentText("Please fill out all the blanks");
    				WarningAlert.show();
            	}
            	else {
	    	        boolean check =false;
	    	        
	    	        //check login details
	    	        for (int u=0;u<UsersList.size();u++) {
						if(UsersList.get(u).getUsername().equals(usernameTextField.getText())&& UsersList.get(u).getPassword().equals(passwordTextField.getText())) {
							OnlineUser = UsersList.get(u);
							check = true;
						}
	    	        }
    	        
	    	        //if login details are correct users refer to the registeredUsers homepage,
	    	        //otherwise they receive an alert
		    		if(check==true) {
						RegisteredUsers registerUserPage = new RegisteredUsers();
						try {
							registerUserPage.start(stage);
						} catch (Exception e) {
							e.printStackTrace();
						}
						stage.show();
		    		}
		    		else {
		    			WarningAlert.setContentText("These Username and password do not exist");
						WarningAlert.show();
		    		}
            	}
            }

        });
        
        Text RegisterText = new Text("Don't have an account?");
        
        // Create the Event Handlers for the register button
        Button register =new Button("Register");
        register.setCursor(Cursor.HAND);
        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Register page = new Register();
            	try {
					page.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        // Create the Event Handlers for the login Button
        Button login =new Button("Login");
        login.setCursor(Cursor.HAND);
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Login loginpage = new Login();
            	try {
					loginpage.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        // Create the Event Handlers for the aboutUs Button
        Button AboutUs =new Button("About Us");
        AboutUs.setCursor(Cursor.HAND);
        AboutUs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	AboutUs AboutUspage = new AboutUs();
            	try {
            		AboutUspage.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        // Create the Event Handlers for the timetable Button
        Button timetable =new Button("Timetable");
        timetable.setCursor(Cursor.HAND);
        timetable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Timetable timetablepage = new Timetable();
            	try {
            		timetablepage.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        // Create the Event Handlers for the contact us Button
        Button ContactUs =new Button("Contact Us");
        ContactUs.setCursor(Cursor.HAND);
        ContactUs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	ContactUs contactUs = new ContactUs();
            	try {
            		contactUs.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        // Create the Event Handlers for the forgot password link
        Hyperlink forgetPassword = new Hyperlink("Forget password?");
        forgetPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	ResetPassword reset = new ResetPassword();
            	try {
            		reset.start(stage);
				} catch (Exception e) {
					System.out.println(e.getMessage());;
				}
            	stage.show();
                
            }

        });
     
        // Create the Event Handlers for the news Button
        Button news = new Button("Latest news");
        news.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	LatestNewsForAll lnga = new LatestNewsForAll();
            	try {
            		lnga.start(stage);
				} catch (Exception e) {
					System.out.println(e.getMessage());;
				}
            	stage.show();
                
            }

        });
        
        HBox hboxin = new HBox(2);
        hboxin.getChildren().addAll(loginButton, forgetPassword);
        
        VBox vbox2 = new VBox(4);
        vbox2.getChildren().addAll(usernameText,usernameTextField,passwordText,passwordTextField,
        		hboxin,RegisterText,register);
        
        VBox vbox = new VBox(8);  
        vbox.getChildren().addAll(login,timetable, news, AboutUs,ContactUs);
        
        //Locate the image content in the CLASSPATH
        URL img = getClass().getResource("travel.jpg");
		String imgUrl = img.toExternalForm();
        Image image = new Image(imgUrl);
        ImageView iv3 = new ImageView();
        iv3.setImage(image);
        iv3.setFitWidth(900);
        iv3.setFitHeight(500);
        //iv3.setSmooth(true);
       // iv3.setCache(true);
        
        HBox titleHbox = new HBox();
        titleHbox.getChildren().add(travelAgency);
        //Set the Style-properties
        titleHbox.setStyle("-fx-background-color: #DCDCDC;"+
       		"-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #4B0082;");
        titleHbox.setAlignment(Pos.CENTER); 
       
        BorderPane border = new BorderPane();
        border.setStyle("-fx-background-color: #DCDCDC;"+
        		"-fx-padding: 10");
         
        border.setTop(titleHbox);
        border.setLeft(vbox);
        border.setCenter(iv3);
        border.setRight(vbox2);
        
        Group root = new Group(border);
        //Creating a scene object 
        Scene scene = new Scene(root, 1200, 620, Color.GAINSBORO); 
       
        //Setting title to the Stage 
        PrimaryStage.setTitle("Home page"); 
        
        //Adding scene to the stage 
        PrimaryStage.setScene(scene);
        //Displaying the contents of the stage 
        PrimaryStage.show();
        PrimaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
            	try {
					dos.close();
					dis.close();
	            	client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            	
                Platform.exit();
                System.exit(0);
            }
        });
        
        
    }
    
    
    //main method to launch the application and link to server.
    public static void main(String args[]) throws UnknownHostException, IOException{ 
    	client = new Socket("127.0.0.1", 7777);
    	dis = new ObjectInputStream(client.getInputStream());
    	dos = new ObjectOutputStream(client.getOutputStream());
    	client.setTcpNoDelay(true);
        launch(args); 
    }


 
}
