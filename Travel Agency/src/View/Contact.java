package View;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import Controller.SendEmail;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Contact class for registered user to link
 * @author Group 25
 *
 */
public class Contact extends Application {
	
	private final String[] problem = {"Problem", "Booking Error", "Register Error", "Payment Error", "Other"};
	private final String[] names = {"Manger", "Dennis", "Hasti", "Kenny", "Robert"};
	private final String[] email = {"kxc855@student.bham.ac.uk", "zxh870@student.bham.ac.uk", "hxc773@student.bham.ac.uk", "cxc967@student.bham.ac.uk"};

	@Override
	public void start(Stage stage) throws Exception {
			
		Font myFont = new Font("Segoe Print", 20);
	
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);
		GridPane gridPane2 = new GridPane();
        gridPane2.setAlignment(Pos.CENTER);
        gridPane2.setStyle("-fx-grid-lines-visible: true");
        for(int i=0; i<names.length; i++) {
        	Label l = new Label(" "+problem[i]+" ");
        	l.setFont(myFont);
        	gridPane2.add(l, 0, i);
        	gridPane2.setHalignment(l, HPos.CENTER);
        	Label l2 = new Label(" "+names[i]+" ");
        	l2.setFont(myFont);
        	gridPane2.add(l2, 1, i);
        	gridPane2.setHalignment(l2, HPos.CENTER);
        }
        
        Label l = new Label(" Email Link ");
        l.setFont(myFont);
        gridPane2.add(l, 2, 0);
        gridPane2.setHalignment(l, HPos.CENTER);
        
        Hyperlink mail = new Hyperlink(" "+email[0]+" ");
        mail.setFont(myFont);
        mail.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 try {
					SendEmail.mailto(Arrays.asList(email[0]), "Contact Dennis", "------Please leave message in below------");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} catch (URISyntaxException e) {
					System.out.println(e.getMessage());
				}	                 
             }

        });
        gridPane2.add(mail, 2, 1);
    	gridPane2.setHalignment(mail, HPos.CENTER);
        Hyperlink mail2 = new Hyperlink(" "+email[1]+" ");
        mail2.setFont(myFont);
        mail2.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 try {
					SendEmail.mailto(Arrays.asList(email[1]), "Contact Hasti", "------Please leave message in below------");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} catch (URISyntaxException e) {
					System.out.println(e.getMessage());
				}	                 
             }

        });
        gridPane2.add(mail2, 2, 2);
        gridPane2.setHalignment(mail2, HPos.CENTER);
        Hyperlink mail3 = new Hyperlink(" "+email[2]+" ");
        mail3.setFont(myFont);
        mail3.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 try {
					SendEmail.mailto(Arrays.asList(email[2]), "Contact Kenny", "------Please leave message in below------");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} catch (URISyntaxException e) {
					System.out.println(e.getMessage());
				}	                 
             }

        });
        gridPane2.add(mail3, 2, 3);
        gridPane2.setHalignment(mail3, HPos.CENTER);
        Hyperlink mail4 = new Hyperlink(" "+email[3]+" ");
        mail4.setFont(myFont);
        mail4.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 try {
					SendEmail.mailto(Arrays.asList(email[3]), "Contact Robert", "------Please leave message in below------");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} catch (URISyntaxException e) {
					System.out.println(e.getMessage());
				}	                 
             }

        });
        gridPane2.add(mail4, 2, 4);
        gridPane2.setHalignment(mail4, HPos.CENTER);
        
        gridPane.add(gridPane2, 0, 0);
        
        Button backHome = new Button("Back");
        gridPane.setStyle("-fx-grid-lines-visible: false");
        gridPane.add(backHome, 0, 1);
        
        backHome.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	RegisteredUsers homepage = new RegisteredUsers();
            	try {
					homepage.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();     
            }
        });
        
        
        
       
        //Creating a scene object 
        Scene scene = new Scene(gridPane); 
       
        //Setting title to the Stage 
        stage.setTitle("Contact us"); 
         
        //Adding scene to the stage 
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(600);
      
        //Displaying the contents of the stage 
        stage.show(); 
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
