package View;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is for registered resetting theit password
 * @author chenmth
 *
 */
public class ResetPassword extends Application{
	
	private static final Font myFont = new Font("Segoe Print", 20);
	private static final String questions[] = { "What's your favorite food?", "What's your favorite animal?",
			"What's your graduating school?", "What's your hobby?", "What's your major?"};
	
	/**
     *  start method
     * 
     *  The fields and buttons are added to a GridPane, which is added
     *  to the Scene.
     *  @param stage The window to be displayed.
     */
	@Override
	public void start(Stage stage) throws Exception {
		
		//Texts
		Text informationCheck = new Text("Reset your password");
		informationCheck.setFont(myFont);
		Text username = new Text("Username : ");
		Text securityQuestion = new Text("Security question :");
        Text securityAnswer = new Text("Security answer :");
        Text passwordText = new Text("Password :");
        Text passwordText2 = new Text("Confirm Password :");
        
        PasswordField passwordTextField = new PasswordField(); 
        PasswordField passwordTextField2 = new PasswordField(); 
        
        TextField usernameTextField = new TextField();
        TextField securityAnswerTextField = new TextField();
        Tooltip tooltip=new Tooltip("Answer the question you have selected");
        securityAnswerTextField.setTooltip(tooltip);
        
        ChoiceBox<String> securityQuestionList = new ChoiceBox<>(FXCollections.observableArrayList(questions));
        securityQuestionList.setValue(questions[0]);
        
        Button confirm = new Button("Confirm");
        confirm.setCursor(Cursor.HAND);
        confirm.setAlignment(Pos.CENTER);
        // Create the Event Handlers for the confirm button
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	String username = usernameTextField.getText();
            	String sq = securityQuestionList.getValue();
            	String sa = securityAnswerTextField.getText();
            	String password = passwordTextField.getText();
            	String password2 = passwordTextField2.getText();
            	//an alert is shown if all the blanks are not filled
            	if(username.equals("") || sa.equals("") || password.equals("") || password2.equals("")) {
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Reset Password");
                    alert.setContentText("Please fill out all the blanks");
                    alert.show();
            	}
            	//passwords should be the same
            	else if(!password.equals(password2)) {
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Reset Password");
                    alert.setContentText("Your password input are not consistent");
                    alert.show();
            	}
            	//checking username and security question
            	else {
            		Alert WarningAlert = new Alert(AlertType.ERROR);
            		WarningAlert.setTitle("Warning!");
            		boolean check = false;
            		for (int i=0;i<Client.UsersList.size();i++) {
            			if(Client.UsersList.get(i).getUsername().equals(username.trim())) {
            				if(Client.UsersList.get(i).getSecurityAnswer().equals(sa.trim())) {
            					if(Client.UsersList.get(i).getSecurityQuestion().equals(sq)) {
            						check = true;
            					}
            				}
            			}
            		}
            		
            		//information goes to the server to update Users table in database with new password
            		String [] inputs= {"reset", username, password};
            		if(check==true) {
            			try {
        					Client.dos.writeObject(inputs);
        				}  catch (IOException e) {
        					System.out.println("Something wrong with outputting register information");
        				}
            			
            			//alert for confirmation of changing password
            			Alert WelcomeAlert = new Alert(AlertType.CONFIRMATION);
    					WelcomeAlert.setTitle("Confirmation");
    					WelcomeAlert.setContentText("Password has reseted successfully :)");
    					WelcomeAlert.show();
    					Client registerUserPage = new Client();
    					try {
							registerUserPage.start(stage);
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
    					stage.show();
    				}else {
            			WarningAlert.setContentText("Your information is not correct");
        				WarningAlert.show();
    				}
            	}
            }
        });
		
        // Create the Event Handlers for the back to home Button
		Button homePage =new Button("Back to Home Page");
        homePage.setCursor(Cursor.HAND);
        homePage.setAlignment(Pos.CENTER);
        homePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Client homepage = new Client();
	            	try {
	            		homepage.start(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}
	            	stage.show();                
            }
        });
        
        GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		
		gridPane.add(informationCheck, 0, 0);
		gridPane.add(username, 0, 1);
		gridPane.add(usernameTextField, 1, 1);
		gridPane.add(securityQuestion, 0, 2);
		gridPane.add(securityQuestionList, 1, 2);
		gridPane.add(securityAnswer, 0, 3);
		gridPane.add(securityAnswerTextField, 1, 3);
		gridPane.add(passwordText, 0, 4);
		gridPane.add(passwordTextField, 1, 4);
		gridPane.add(passwordText2, 0, 5);
		gridPane.add(passwordTextField2, 1, 5);
		gridPane.add(homePage, 0, 6);
		gridPane.add(confirm, 1, 6);
		
		Scene root = new Scene(gridPane,600,400);
		stage.setScene(root);
		stage.show();	
	}
}
