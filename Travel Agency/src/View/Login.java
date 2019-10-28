package View;

import javafx.application.Application; 
import static javafx.application.Application.launch;

import java.io.IOException;

import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField; 
import javafx.scene.layout.GridPane; 
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;  
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *  This class shows a 
 *  @author Group 25
 */
         
public class Login extends Application { 
	
	/**
     *  start method
     * 
     *  The fields and buttons are added to a GridPane, which is added
     *  to the Scene.
     *  @param stage The window to be displayed.
     */
    @Override 
    public void start(Stage stage) {      
       
        Text usernameText = new Text("Username");       
      
        //creating label password 
        Text passwordText = new Text("Password"); 
       
        //Creating text field for userame
        TextField usernameTextField = new TextField();       
      
        //Creating text field for password        
        PasswordField passwordTextField = new PasswordField();  
       
        //Creating buttons 
        Button submitButton = new Button("Submit"); 
        Button clearButton = new Button("Clear");  

        //The event handler for the event of clear button
        final EventHandler<MouseEvent> eventHandlerClear = e -> {
            passwordTextField.setText("");
            usernameTextField.setText("");
        };
        clearButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerClear);

        final EventHandler<MouseEvent> eventHandlerSubmit = e -> {
        	boolean check =false;
        	Alert WarningAlert = new Alert(AlertType.ERROR);
    		WarningAlert.setTitle("Warning!");
    		/**
    		 * send login details information to server to check in the database if this
    		 * user is registered or not and a boolean will be received from server
    		 * if it's true that means user can login
    		 */
        	String [] LoginInputs= {"login",usernameTextField.getText(),passwordTextField.getText()};
        	try {
				Client.dos.writeObject(LoginInputs);
				check = (boolean) Client.dis.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			
    		if(check==true) {
				//user refers to the registered page home page
				RegisteredUsers registerUserPage = new RegisteredUsers();
				try {
					registerUserPage.start(stage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				stage.show();
    		}
    		else if(usernameTextField.getText().equals("") || passwordTextField.getText().equals("")) {
        		WarningAlert.setContentText("Please fill out all the blanks");
				WarningAlert.show();
        	}
    		else {
    			//if users have not registered they will receive an alert 
    			WarningAlert.setContentText("These Username and password do not exist");
				WarningAlert.show();
    		}
        };
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerSubmit);
        
        //Create the Event Handlers for the back button
        Button ClientB =new Button("Back to Home Page");
        ClientB.setCursor(Cursor.HAND);
        ClientB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Client newhmp = new Client();
            	try {
					newhmp.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
      
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        gridPane.setPadding(new Insets(10, 10, 10, 10));       
        gridPane.setAlignment(Pos.CENTER);
        //Adding the nodes to the grid 
        gridPane.add(usernameText, 0, 0); 
        gridPane.add(usernameTextField, 1, 0); 
        gridPane.add(passwordText, 0, 1);       
        gridPane.add(passwordTextField, 1, 1); 
        gridPane.add(clearButton, 0, 2); 
        gridPane.add(submitButton, 1, 2); 
        gridPane.add(ClientB, 2, 2); 
        
        gridPane.setStyle("-fx-background-color: #DCDCDC;"+
        		"-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #4B0082;");
        
        //Creating a scene object 
        Scene scene = new Scene(gridPane); 
       
        //Setting title to the Stage 
        stage.setTitle("Login page"); 
         
        //Adding scene to the stage 
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(620);
      
        //Displaying the contents of the stage 
        stage.show(); 
    }      
}
