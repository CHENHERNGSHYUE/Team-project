package View;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class contains a video and sliders which plays a video
 * @author Group 25
 *
 */
public class AboutUs extends Application{
	
	/**
     *  start method
     * 
     *  The fields and buttons are added to a GridPane, which is added
     *  to the Scene.
     *  @param stage The window to be displayed.
     */
	@Override
	public void start(Stage stage){
		
		Font myFont = new Font("Segoe Print", 40);
		Text ads = new Text("Discover the world with us");
		ads.setFill(Color.LIGHTSEAGREEN);
		ads.setFont(myFont);
		
		// Create the Sliders
		final Slider cycleSlider = new Slider(1, 5, 1);
		cycleSlider.setMajorTickUnit(1);
		cycleSlider.setShowTickLabels(true);

		final Slider volumeSlider = new Slider(0.0, 1.0, 0.5);
		volumeSlider.setMajorTickUnit(0.1);
		volumeSlider.setShowTickLabels(true);

		final Slider rateSlider = new Slider(0, 8, 4);
		rateSlider.setMajorTickUnit(1);
		rateSlider.setShowTickLabels(true);

		// Locate the media content in the CLASSPATH
		URL mediaUrl = getClass().getResource("video.mp4");
		String mediaStringUrl = mediaUrl.toExternalForm();

		// Create a Media
		final Media media = new Media(mediaStringUrl);

		// Create a Media Player and automatically begin the playback
		final MediaPlayer player = new MediaPlayer(media);
		player.setAutoPlay(true);

		// Create a 400X300 MediaView
		final MediaView mediaView = new MediaView(player);
		mediaView.setFitWidth(400);
		mediaView.setFitHeight(300);
		mediaView.setSmooth(true);

		// Create the DropShadow effect
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetY(5.0);
		dropshadow.setOffsetX(5.0);
		dropshadow.setColor(Color.PINK);

		mediaView.setEffect(dropshadow);

		// Create the Buttons
		Button playButton = new Button("Play");
		Button stopButton = new Button("Stop");
		Button backHomeButton = new Button("Back home");

		// Create the Event Handlers for the Button
		playButton.setOnAction(new EventHandler <ActionEvent>()
		{
            public void handle(ActionEvent event)
            {
            	if (player.getStatus() == Status.PLAYING)
            	{
            		player.stop();
            		player.play();
            	}
            	else
            	{
            		player.play();
            	}
            }
        });

		stopButton.setOnAction(new EventHandler <ActionEvent>(){		
            public void handle(ActionEvent event){
            	player.stop();
            }
        });
		
		backHomeButton.setOnAction(new EventHandler <ActionEvent>(){
            public void handle(ActionEvent event){
            	Client client = new Client();
            	try {
					client.start(stage);
					player.stop();
				} catch (IOException e) {
					System.out.println("Client linking error");
				}
            	stage.show();
            }
        });
		
		// Create the Listener for the Sliders
		cycleSlider.valueProperty().addListener(new InvalidationListener(){
			@Override
			public void invalidated(Observable ov)
			{
				if (cycleSlider.isValueChanging())
				{
					player.setCycleCount((int)cycleSlider.getValue());
				}
			}
		});

		volumeSlider.valueProperty().addListener(new InvalidationListener(){
			@Override
			public void invalidated(Observable ov)
			{
				if (volumeSlider.isValueChanging())
				{
					player.setVolume(volumeSlider.getValue());
				}
			}
		});

		rateSlider.valueProperty().addListener(new InvalidationListener(){
			@Override
			public void invalidated(Observable ov)
			{
				if (rateSlider.isValueChanging())
				{
					player.setRate(rateSlider.getValue());
				}
			}
		});

		// Add Handlers for End and Repeat
		player.setOnEndOfMedia(new Runnable(){
            public void run()
            {
            	
            }
		});

		player.setOnRepeat(new Runnable(){
            public void run()
            {
            }
		});

		// Create the GridPane
		GridPane sliderPane = new GridPane();
		// Set horizontal and vertical Spacing
		sliderPane.setHgap(5);
		sliderPane.setVgap(10);

		// Add the details to the GridPane
		sliderPane.addRow(0, new Label("CycleCount:"), cycleSlider);
		sliderPane.addRow(1, new Label("Volume:"), volumeSlider);
		sliderPane.addRow(2, new Label("Rate:"), rateSlider);

		// Create the HBox
		HBox controlBox = new HBox(5, playButton, stopButton, backHomeButton);

		// Create the VBox
		VBox root = new VBox(5,ads,mediaView,controlBox,sliderPane);

		// Set the Style-properties of the HBox
		root.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #4B0082;");
		root.setAlignment(Pos.CENTER);
		controlBox.setAlignment(Pos.CENTER);
		sliderPane.setAlignment(Pos.CENTER);
		
		// Create the Scene
		Scene scene = new Scene(root, 1200, 620, Color.GAINSBORO);
		// Add the scene to the Stage
		stage.setScene(scene);
		// Set the title of the Stage
		stage.setTitle("About Us");
		// Display the Stage
		stage.show();
	}
}
