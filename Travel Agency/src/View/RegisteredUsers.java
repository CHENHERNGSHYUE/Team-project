package View;

import javafx.application.Application; 
import static javafx.application.Application.launch;

import java.net.URL;

import javafx.scene.paint.Color;
import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;  
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This class shows a homePgae for registered users 
 * @author Group 25
 *
 */
         
public class RegisteredUsers extends Application { 
	
	/**
     *  start method
     * 
     *  The fields and buttons are added to a GridPane, which is added
     *  to the Scene.
     *  @param stage The window to be displayed.
     */
    @Override 
    public void start(Stage stage) {      
        
        
        Button LogOut =new Button("Log out");
        LogOut.setCursor(Cursor.HAND);
        LogOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Client homepage = null;
				try {
					homepage = new Client();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            	try {
					homepage.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        //booking button to book flights
        Button booking =new Button("Booking");
        booking.setCursor(Cursor.HAND);
        booking.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Booking BookingPage = new Booking();
            	try {
            		BookingPage.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();                
            }
        });
        
        //timetable button to see the timetable of flights
        Button timetable =new Button("Timetable");
        timetable.setCursor(Cursor.HAND);
        timetable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	TimeTableForRegister timetablepage = new TimeTableForRegister();
            	try {
            		timetablepage.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        //this button is for users who bought ticket and want to order their special meal
        Button OrderMealButton =new Button("Order Your Special Meal");
        OrderMealButton.setCursor(Cursor.HAND);
        OrderMealButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	OrderMeal OrderMealpage = new OrderMeal();
            	try {
            		OrderMealpage.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        //tHis button is for contactUs page and includes our contact information
        Button contactUs =new Button("Contact");
        contactUs.setCursor(Cursor.HAND);
        contactUs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Contact contact = new Contact();
            	try {
            		contact.start(stage);
				} catch (Exception e) {
					System.out.println("Stage chaning error");
				}
            	stage.show();               
            }
        });
        
        //to check ticket status
        Button ticket =new Button("Ticket status");
        ticket.setCursor(Cursor.HAND);
        ticket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	TicketStatus ts = new TicketStatus();
            	try {
            		ts.start(stage);
				} catch (Exception e) {
					System.out.println("Stage chaning error");
				}
            	stage.show();
                
            }

        });
        
        //to see latest news
        Button news = new Button("Latest news");
        news.setCursor(Cursor.CLOSED_HAND);
        news.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	LatestNewsPage lnp = new LatestNewsPage();
            	try {
            		lnp.start(stage);
				} catch (Exception e) {
					System.out.println("Stage chaning error");
				}
            	stage.show();
                
            }

        });
        
        VBox vbox = new VBox(8);  
        vbox.getChildren().addAll(timetable, news, contactUs);
        
        HBox hbox = new HBox(4);  
        hbox.getChildren().addAll(booking, ticket, OrderMealButton,LogOut);
        
        // Locate the image content in the CLASSPATH
        URL img = getClass().getResource("travel.jpg");
		String imgUrl = img.toExternalForm();
        Image image = new Image(imgUrl);
        

        ImageView iv3 = new ImageView();
        iv3.setImage(image);
        iv3.setFitWidth(900);
        iv3.setFitHeight(500);
        iv3.setSmooth(true);
        iv3.setCache(true);


        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();    
      
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        gridPane.setPadding(new Insets(10, 10, 10, 10));       
      
        //Adding the nodes to the grid 
        gridPane.add(iv3, 1, 10);
        gridPane.add(vbox, 0, 10);
        gridPane.add(hbox, 1, 8);
        
        gridPane.setStyle("-fx-background-color: #DCDCDC;"+
        		"-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #4B0082;");
        
        Group root = new Group(gridPane);
        //Creating a scene object 
        Scene scene = new Scene(root, 1200, 620, Color.GAINSBORO); 
       
        //Setting title to the Stage 
        stage.setTitle("Home page"); 
        
        //Adding scene to the stage 
        stage.setScene(scene);
        //Displaying the contents of the stage 
        stage.show(); 
    }      
}
