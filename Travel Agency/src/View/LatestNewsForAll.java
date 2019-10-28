package View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Latest ews for all users
 * @author Group 25
 *
 */
public class LatestNewsForAll extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Font myFont = new Font("Segoe Print", 20);
		
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(5);
		Label content1 = new Label("1. Travel agency is a newly established company.");
		content1.setFont(myFont);
		content1.setTextFill(javafx.scene.paint.Color.RED);
		Label content2 = new Label("2. We are committed to providing the best quality service and the cheapest airfare.");
		content2.setFont(myFont);
		content2.setTextFill(javafx.scene.paint.Color.RED);
		Label content3 = new Label("3. During the trial operation, current flight timetable is available until the end of May in 2019.");
		content3.setFont(myFont);
		content3.setTextFill(javafx.scene.paint.Color.RED);
		
		Button back = new Button ("Back");
		back.setCursor(Cursor.HAND);
		back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Client c = new Client();
            	try {
            		c.start(stage);
				} catch (Exception e) {
					System.out.println("Stage chaning error");
				}
            	stage.show();
                
            }

        });
		
		gridPane.add(content1, 0, 0);
		gridPane.add(content2, 0, 1);
		gridPane.add(content3, 0, 2);
		gridPane.add(back, 0, 3);
		
		//Creating a scene object 
        Scene scene = new Scene(gridPane); 
       
        //Setting title to the Stage 
        stage.setTitle("News"); 
         
        //Adding scene to the stage 
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(620);
      
        //Displaying the contents of the stage 
        stage.show(); 
		
	}


}
