package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import Model.Meal;
import Controller.NumericCheck;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Class for ordering meals
 * @author Group 25
 *
 */
public class OrderMeal extends Application{
	private ArrayList<Meal> ShoppingList =new ArrayList<>();
	private String NumberOfTicket="";
	private Text YourList = new Text("Your Shopping List");
	private TableView<Meal> Mealtable = new TableView<Meal>();
	private Button delete = new Button("Delete");
	private Button ConfirmList = new Button("Confirm Your Shopping List");
	private Scene PaymentPage;
	private double TotalMealPrice=0;
	@Override
	public void start(Stage stage) throws Exception {
		
		//set visible false until confirming ticket number
		YourList.setVisible(false);
		delete.setVisible(false);
		ConfirmList.setVisible(false);
		GridPane Shop =new GridPane(); 
		Shop.setVisible(false);
		Font myFont = new Font("Segoe Print", 15);
		Font myFont2 = new Font("Segoe Print", 40);
		Text orderText = new Text("Order your special meal");
		YourList.setFont(myFont);
		orderText.setFont(myFont2);
		Text ticketNOText = new Text("Please enter your ticket number:");
		ticketNOText.setFont(myFont);
		TextField ticketNOTextField = new TextField();
		Alert WarningAlert = new Alert(AlertType.INFORMATION);
		WarningAlert.setTitle("Confirmation");
		
		//Locate the image content in the CLASSPATH
		Image image = new Image(getClass().getResourceAsStream("steak.jpg"),100,100,false,false);
		
		ImageView imgview = new ImageView(image);
		imgview.maxWidth(50);
		imgview.maxHeight(50);
		
		// Create the Event Handlers for the steak Button
        Button steakButton = new Button();
        steakButton.setMaxSize(50,50);
        steakButton.setGraphic(imgview);
        steakButton.setVisible(false);
        Text steakPrice = new Text("Steak \u00A3 25 ");
        steakPrice.setFont(myFont);
        steakPrice.setVisible(false);
        steakButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal steak = new Meal("Steak", 25);
				ShoppingList.add(steak);
            }
        });
        
        //Locate the image content in the CLASSPATH
        Image chickenImage = new Image(getClass().getResourceAsStream("chickenNugget.jpg"),100,100,false,false);
        ImageView imgview2 = new ImageView(chickenImage);
		imgview2.maxWidth(30);
		imgview2.maxHeight(30);
		
		// Create the Event Handlers for the chicken Button
        Button chickenButton = new Button();
        chickenButton.setMaxSize(30, 30);
        chickenButton.setGraphic(imgview2);
        chickenButton.setMaxSize(50, 50);
        chickenButton.setVisible(false);
        Text chickenPrice = new Text("Chicken nuggets \u00A3 20 ");
        chickenPrice.setFont(myFont);
        chickenPrice.setVisible(false);
        chickenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal chicken = new Meal("Chicken",20);
				ShoppingList.add(chicken);
            }
        });
        
        //Locate the image content in the CLASSPATH
        Image fishImage = new Image(getClass().getResourceAsStream("fishFillet.jpg"),100,100,false,false);
        ImageView imgview3 = new ImageView(fishImage);
		imgview3.maxWidth(30);
		imgview3.maxHeight(30);
		
		// Create the Event Handlers for the fish Button
        Button fishButton = new Button();
        fishButton.setMaxSize(30, 30);
        fishButton.setGraphic(imgview3);
        fishButton.setMaxSize(50, 50);
        fishButton.setVisible(false);
        Text fishkPrice = new Text("Fish Fillet \u00A3 17.5");
        fishkPrice.setFont(myFont);
        fishkPrice.setVisible(false);
        fishButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal fish = new Meal("Fish",17.5);
				ShoppingList.add(fish);
            }
        });
		
        //Locate the image content in the CLASSPATH
        Image PizzaImage = new Image(getClass().getResourceAsStream("pizza.jpg"),100,100,false,false);
        ImageView imgview4 = new ImageView(PizzaImage);
		imgview4.maxWidth(30);
		imgview4.maxHeight(30);
		
		// Create the Event Handlers for the pizza Button
        Button pizzaButton = new Button();
        pizzaButton.setMaxSize(30, 30);
        pizzaButton.setGraphic(imgview4);
        pizzaButton.setMaxSize(50, 50);
        pizzaButton.setVisible(false);
        Text pizzaPrice = new Text("Pizza \u00A3 25");
        pizzaPrice.setVisible(false);
        pizzaPrice.setFont(myFont);
        pizzaButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal pizza = new Meal("Pizza",25);
				ShoppingList.add(pizza);
            }
        });
        
        //buttons and texts are added to Hbox
        HBox hbox = new HBox(50);
        HBox hbox5 = new HBox(50);
        hbox.getChildren().add(steakButton);
        hbox5.getChildren().add(steakPrice);
        hbox.getChildren().add(chickenButton);
        hbox5.getChildren().add(chickenPrice);
        hbox.getChildren().add(fishButton);
        hbox5.getChildren().add(fishkPrice);
        hbox.getChildren().add(pizzaButton);
        hbox5.getChildren().add(pizzaPrice);
        
        //Locate the image content in the CLASSPATH
        Image hamburgerImage = new Image(getClass().getResourceAsStream("hamburger.jpg"),100,100,false,false);
        ImageView imgview5 = new ImageView(hamburgerImage);
		imgview5.maxWidth(30);
		imgview5.maxHeight(30);
		
		// Create the Event Handlers for the hamburger Button
        Button hamburgerButton = new Button();
        hamburgerButton.setMaxSize(30, 30);
        hamburgerButton.setGraphic(imgview5);
        hamburgerButton.setMaxSize(50, 50);
        hamburgerButton.setVisible(false);
        Text hamburgerPrice = new Text("Hamburger \u00A3 20");
        hamburgerPrice.setFont(myFont);
        hamburgerPrice.setVisible(false);
        hamburgerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal ham = new Meal("Hamburger", 20);
				ShoppingList.add(ham);
            }
        });
        
        //Locate the image content in the CLASSPATH
        Image soupImage = new Image(getClass().getResourceAsStream("soup.jpg"),100,100,false,false);
        ImageView imgview6 = new ImageView(soupImage);
		imgview6.maxWidth(30);
		imgview6.maxHeight(30);
		
		// Create the Event Handlers for the soup Button
        Button soupButton = new Button();
        soupButton.setMaxSize(30, 30);
        soupButton.setGraphic(imgview6);
        soupButton.setMaxSize(50, 50);
        soupButton.setVisible(false);
        Text soupPrice = new Text("Soup \u00A3 15");
        soupPrice.setFont(myFont);
        soupPrice.setVisible(false);
        soupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal soup = new Meal("Soup",15);
				ShoppingList.add(soup);
            }
        });
		
        //Locate the image content in the CLASSPATH
        Image latteImage = new Image(getClass().getResourceAsStream("latte.jpg"),100,100,false,false);
        ImageView imgview7 = new ImageView(latteImage);
		imgview7.maxWidth(30);
		imgview7.maxHeight(30);
		
		// Create the Event Handlers for the coffee Button
        Button latteButton = new Button();
        latteButton.setMaxSize(30, 30);
        latteButton.setGraphic(imgview7);
        latteButton.setMaxSize(50, 50);
        latteButton.setVisible(false);
        Text lattePrice = new Text("Coffee Latte \u00A3 25");
        lattePrice.setFont(myFont);
        lattePrice.setVisible(false);
        latteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal latte = new Meal("Latte", 25);
				ShoppingList.add(latte);
            }
        });
        
        //Locate the image content in the CLASSPATH
        Image cokeImage = new Image(getClass().getResourceAsStream("coke.jpg"),100,100,false,false);
        ImageView imgview8 = new ImageView(cokeImage);
		imgview8.maxWidth(30);
		imgview8.maxHeight(30);
		
		// Create the Event Handlers for the coke Button
        Button cokeButton = new Button();
        cokeButton.setMaxSize(30, 30);
        cokeButton.setGraphic(imgview8);
        cokeButton.setMaxSize(50, 50);
        cokeButton.setVisible(false);
        Text cokePrice = new Text("Coke \u00A3 20");
        cokePrice.setFont(myFont);
        cokePrice.setVisible(false);
        cokeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal coke = new Meal("Coke", 20);
				ShoppingList.add(coke);
            }
        });
        
        HBox hbox2 = new HBox(50);
        HBox hbox6 = new HBox(50);
        
        hbox2.getChildren().add(hamburgerButton);
        hbox6.getChildren().add(hamburgerPrice);
        hbox2.getChildren().add(soupButton);
        hbox6.getChildren().add(soupPrice);
        hbox2.getChildren().add(latteButton);
        hbox6.getChildren().add(lattePrice);
        hbox2.getChildren().add(cokeButton);
        hbox6.getChildren().add(cokePrice);
           
        //Locate the image content in the CLASSPATH
        Image orangeJuiceImage = new Image(getClass().getResourceAsStream("orangeJuice.jpg"),100,100,false,false);
        ImageView imgview9 = new ImageView(orangeJuiceImage);
		imgview9.maxWidth(30);
		imgview9.maxHeight(30);
        Button orangeJuiceButton = new Button();
        orangeJuiceButton.setMaxSize(30, 30);
        orangeJuiceButton.setGraphic(imgview9);
        orangeJuiceButton.setMaxSize(50, 50);
        orangeJuiceButton.setVisible(false);
        Text orangeJuicePrice = new Text("Orange Juice \u00A3 15");
        orangeJuicePrice.setFont(myFont);
        orangeJuicePrice.setVisible(false);
        
        // Create the Event Handlers for the orange juice Button
        orangeJuiceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal Ojuice = new Meal("Orange juice",15);
				ShoppingList.add(Ojuice);
            }
        });
        
        //Locate the image content in the CLASSPATH
        Image appleJuiceImage = new Image(getClass().getResourceAsStream("apple_juice.jpeg"),100,100,false,false);
        ImageView imgview10 = new ImageView(appleJuiceImage);
		imgview10.maxWidth(30);
		imgview10.maxHeight(30);
        Button appleJuiceButton = new Button();
        appleJuiceButton.setMaxSize(30, 30);
        appleJuiceButton.setGraphic(imgview10);
        appleJuiceButton.setMaxSize(50, 50);
        appleJuiceButton.setVisible(false);
        Text appleJuicePrice = new Text("Apple Juice \u00A3 25");
        appleJuicePrice.setFont(myFont);
        appleJuicePrice.setVisible(false);
        // Create the Event Handlers for the juice Button
        appleJuiceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal Ajuice = new Meal("Apple juice",25);
				ShoppingList.add(Ajuice);
            }
        });
        
        //Locate the image content in the CLASSPATH
        Image chocImage = new Image(getClass().getResourceAsStream("choc.jpg"),100,100,false,false);
        ImageView imgview11 = new ImageView(chocImage);
		imgview11.maxWidth(30);
		imgview11.maxHeight(30);
        Button chocButton = new Button();
        chocButton.setMaxSize(30, 30);
        chocButton.setGraphic(imgview11);
        chocButton.setMaxSize(50, 50);
        chocButton.setVisible(false);
        Text chocPrice = new Text("Chocolate Cake \u00A3 20");
        chocPrice.setFont(myFont);
        chocPrice.setVisible(false);
        
        // Create the Event Handlers for the chocolate Button
        chocButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal ChocolateCake = new Meal("Chocolate Cake", 20);
				ShoppingList.add(ChocolateCake);
            }
        });
        
        //Locate the image content in the CLASSPATH
        Image strawberryCakeImage = new Image(getClass().getResourceAsStream("strawberryCake.jpg"),100,100,false,false);
        ImageView imgview12 = new ImageView(strawberryCakeImage);
		imgview12.maxWidth(30);
		imgview12.maxHeight(30);
        Button strawberryCakeButton = new Button();
        strawberryCakeButton.setMaxSize(30, 30);
        strawberryCakeButton.setGraphic(imgview12);
        strawberryCakeButton.setMaxSize(50, 50);
        strawberryCakeButton.setVisible(false);
        Text strawberryCakePrice = new Text("Strawberry Cake \u00A3 15");
        strawberryCakePrice.setFont(myFont);
        strawberryCakePrice.setVisible(false);
        
        // Create the Event Handlers for the strawberry cake Button
        strawberryCakeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	WarningAlert.setContentText("This meal is added to your shopping cart");
				WarningAlert.show();
				Meal StrawberryCake = new Meal("Strawberry Cake", 15);
				ShoppingList.add(StrawberryCake);
            }
        });
		
        //buttons and texts are added to Hbox
        HBox hbox4 = new HBox(50);
        HBox hbox8 = new HBox(50);
        hbox4.getChildren().add(orangeJuiceButton);
        hbox8.getChildren().add(orangeJuicePrice);
        hbox4.getChildren().add(appleJuiceButton);
        hbox8.getChildren().add(appleJuicePrice);
        hbox4.getChildren().add(chocButton);
        hbox8.getChildren().add(chocPrice);
        hbox4.getChildren().add(strawberryCakeButton);
        hbox8.getChildren().add(strawberryCakePrice);
        
        // Create the Event Handlers for the confirm Button to confirm the shopping list
        Button  ShoppingCart=new Button("Confirm");
        ShoppingCart.setCursor(Cursor.HAND);
        ShoppingCart.setVisible(false);
        ShoppingCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Shop.setVisible(true);
            	ConfirmList.setVisible(true);
            	delete.setVisible(true);
            	YourList.setVisible(true);
            	//creating a table for user's shopping list
    			TableColumn MealColumn = new TableColumn("Meal");
    			MealColumn.setMinWidth(100);
    			MealColumn.setCellValueFactory(
		                new PropertyValueFactory<Meal, String>("MealName"));
    			
				//create column for checkbox
    			TableColumn ChooseColumn = new TableColumn("Choose");
    			ChooseColumn.setMinWidth(200);
    			ChooseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Meal, CheckBox>, ObservableValue<CheckBox>>() {
    				
		            @Override
		            public ObservableValue<CheckBox> call(
		                    TableColumn.CellDataFeatures<Meal, CheckBox> box) {
		                Meal user = box.getValue();

		                CheckBox checkBox = new CheckBox();

		                checkBox.selectedProperty().setValue(user.isSelect());

		                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
		                    public void changed(ObservableValue<? extends Boolean> ov,
		                            Boolean old_val, Boolean new_val) {

		                        user.setSelect(new_val);

		                    }
		                });

		                return new SimpleObjectProperty<CheckBox>(checkBox);

		            }

		        });
		        
		        ObservableList<Meal> OurData = FXCollections.observableArrayList(ShoppingList);
		        Mealtable.setItems(OurData);
				Mealtable.getColumns().addAll(MealColumn,ChooseColumn);
				
				/**
				 * Create the Event Handlers for delete button to delete meals
				 * from shopping list
				 */
		        delete.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		            	ObservableList<Meal> selectMeals = FXCollections.observableArrayList();
    	            	
    	        		for(Meal m: OurData) {
    	        			if(m.isSelect()) {
    	        			selectMeals.add(m);
    	 
    	        			}
    	        		}
    	        		OurData.removeAll(selectMeals);
    	        		Mealtable.refresh();
    	        		Mealtable.setItems(OurData);   	        		 
		            }
		        });
		        
		        // Create the Event Handlers for the confirm Button to confrim the shopping list
		        ConfirmList.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		            	ObservableList<Meal> FinalselectMeals = FXCollections.observableArrayList();
		            	for(Meal m: OurData) {
    	        			if(m.isSelect()) {
    	        			FinalselectMeals.add(m);
    	 
    	        			}
    	        		}
		            	Font myFont = new Font("Segoe Print", 20);
    	        		Text bank = new Text("Welcome to the \"Safest\" Bank");
    	        		bank.setStyle("-fx-background-color: #708090");
    	        		bank.setFont(myFont);
    	        		Text CardNumberText = new Text("Card Number:");
    	        		Text CvvText = new Text("CVV:");
    	        		Text CardNameText = new Text("Card holder's name:");
    	        		TextField CardNumberTextField = new TextField();
    	                TextField CvvTextField = new TextField();
    	                TextField CardNameTextField = new TextField();
    	                
    	                for (int f=0;f<FinalselectMeals.size();f++) {
    	                	TotalMealPrice += FinalselectMeals.get(f).getMealprice();
    	                }
    	                Text TotalPriceText = new Text("Total price: "+ TotalMealPrice);
     	              	TotalPriceText.setFont(myFont);
     	               
     	              	URL myImg = getClass().getResource("emoji.png");
     	       			String myImgUrl = myImg.toExternalForm();
     	       			Image myImage = new Image(myImgUrl);
     	       			ImageView MyImgView = new ImageView();
     	       			MyImgView.setImage(myImage);
     	              
     	       			Button ordermealPage =new Button("Back to OrderMeal Page");
     	       			ordermealPage.setCursor(Cursor.HAND);
     	       			ordermealPage.setOnAction(new EventHandler<ActionEvent>() {
	          	            @Override
	          	            public void handle(ActionEvent event) {
	          	            	OrderMeal ordermeal = new OrderMeal();
	          	            	try {
	          	            		ordermeal.start(stage);
	          					} catch (Exception e) {
	          						e.printStackTrace();
	          					}
	          	            	stage.show();
	          	            }
	
	          	        });
     	               
     	       			// Create the Event Handlers for the confirm Button to confirm the payment
     	       			Button ConfirmPayment =new Button("Confirm");
     	       			ConfirmPayment.setCursor(Cursor.HAND);
     	       			ConfirmPayment.setOnAction(new EventHandler<ActionEvent>() {
       	                 @Override
       	                 public void handle(ActionEvent event) {
       	                	
       	                	//check if all the blank are filled or not
       	                	if(CardNumberTextField.getText().trim().equals("")||CvvTextField.getText().trim().equals("")||CardNameTextField.getText().equals("") ) {
           	            		Alert EmptyFieldAlert2 = new Alert(AlertType.INFORMATION);
           	            		EmptyFieldAlert2.setTitle("Warning");
           	            		EmptyFieldAlert2.setContentText("Please fill out all the blanks");
           	            		EmptyFieldAlert2.show();
           	            		
           	            	//card number should be 16 digits and should be numeric
           	            	} else if (!NumericCheck.isNumeric(CardNumberTextField.getText().trim()) || CardNumberTextField.getText().trim().length() != 16) {
           	            		Alert EmptyFieldAlert2 = new Alert(AlertType.INFORMATION);
           	            		CardNumberTextField.requestFocus();
           	            		EmptyFieldAlert2.setTitle("Warning");
           	            		EmptyFieldAlert2.setContentText("Please check your card number " + CardNumberTextField.getText().trim().length());
           	            		EmptyFieldAlert2.show();
           	            		
           	            	//cvv number should be 3 digits and should be numeric
           	            	} else if (!NumericCheck.isNumeric(CvvTextField.getText().trim()) || CvvTextField.getText().trim().length() != 3) {
           	            		Alert EmptyFieldAlert2 = new Alert(AlertType.INFORMATION);
           	            		CvvTextField.requestFocus();
           	            		EmptyFieldAlert2.setTitle("Warning");
           	            		EmptyFieldAlert2.setContentText("Please check your CVV number");
           	            		EmptyFieldAlert2.show();
           	            	}
       	                	else {
       	                	//if nothing is chosen an alert is shown
       	                		if(TotalMealPrice==0) {
       	                			Alert NothingAlert = new Alert(AlertType.CONFIRMATION);
       	                			NothingAlert.setTitle("Confirmation");
       	                			NothingAlert.setContentText("You didn't choose any meal");
       	                			NothingAlert.show();
           	                		RegisteredUsers back2 = new RegisteredUsers();
                  	                 try {
           	     	            		back2.start(stage);
           	     					} catch (Exception e) {
           	     						e.printStackTrace();
           	     					}
           	     	            	stage.show();
       	                		}
       	                	else {
       	                		String FinalMeals="";
       	                		for (int fm=0;fm<OurData.size();fm++) {
       	                			FinalMeals =FinalMeals+ OurData.get(fm).getMealName() +" ";
       	                		}
       	                		
       	                		//passing data to server to save information in database
       	                		try {
       	                			String [] entities= {"mealList",FinalMeals,NumberOfTicket};
       	                			Client.dos.writeObject(entities);
       	                		} catch (IOException e) {
       	                			e.printStackTrace();
       	                		}
       	                		
	       	                		//create alert to show to the user that meal is added to their shopping list
	       	                		Alert MealConfirmationAlert = new Alert(AlertType.CONFIRMATION);
	       	                		MealConfirmationAlert.setTitle("Confirmation");
	       	                		MealConfirmationAlert.setContentText("Your Special meal is added successfully");
	       	                		MealConfirmationAlert.show();
	       	                		RegisteredUsers back = new RegisteredUsers();
	              	                try {
	       	     	            		back.start(stage);
	       	     					} catch (Exception e) {
	       	     						e.printStackTrace();
	       	     					}
	       	     	            	stage.show();
	       	                	}
       	                	}
       	                 }

       	             });
     	               
     	              GridPane grid2 = new GridPane();
     	              grid2.add(bank, 0, 0);
     	              grid2.add(MyImgView, 1, 0);
     	              grid2.add(CardNumberText, 0, 1);
     	              grid2.add(CardNumberTextField, 1, 1);
     	              grid2.add(CvvText, 0, 2);
     	              grid2.add(CvvTextField, 1, 2);
     	              grid2.add(CardNameText, 0, 3);
     	              grid2.add(CardNameTextField, 1, 3);
     	              grid2.add(TotalPriceText, 0, 4);
     	              grid2.add(ordermealPage, 0, 5);
     	              grid2.add(ConfirmPayment, 1, 5);
     	              grid2.setAlignment(Pos.CENTER);
   	               
   	        		PaymentPage = new Scene(grid2, 1200, 600,Color.GAINSBORO);
           			stage.setScene(PaymentPage);
		            }

		        });
				
		        Shop.add(YourList,4,0);
		        Shop.add(Mealtable,4,1);
		        Shop.add(ConfirmList,4,2);
		        Shop.add(delete,5,2);
            }

        });
        
        // Create the Event Handlers for the ok Button to confirm ticket number
        Button  FlightNumberButton=new Button("OK");
        FlightNumberButton.setCursor(Cursor.HAND);
        FlightNumberButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	boolean checkTicketNum =false;
            	Alert WarningAlertTicket = new Alert(AlertType.ERROR);
            	WarningAlertTicket.setTitle("Warning!");
        		NumberOfTicket = ticketNOTextField.getText();
        		
        		//if nothing is entered an alert is shown
        		if(NumberOfTicket.equals("")) {
        			WarningAlertTicket.setContentText("Please Enter Your Ticket Number");
        			WarningAlertTicket.show();
        		}
        		else {
        			//data is sent to server to check whether the ticket number is available or not
        			// and a boolean is returned. if it's true then users can order their special meal
        			String [] OrderMealInputs= {"meal",NumberOfTicket};
                	try {
        				Client.dos.writeObject(OrderMealInputs);
        				checkTicketNum = (boolean) Client.dis.readObject();
        			} catch (ClassNotFoundException e1) {
        				e1.printStackTrace();
        			} catch (IOException e1) {
        				e1.printStackTrace();
        			}
                	
                	//if ticket number is not available an alert is shown
                	if(checkTicketNum==false) {
                		Alert WarningAlertWrongTicketNumber = new Alert(AlertType.ERROR);
                		WarningAlertWrongTicketNumber.setTitle("Warning!");
                		WarningAlertWrongTicketNumber.setContentText("Sorry, You didn't book any flights");
                		WarningAlertWrongTicketNumber.show();
                	}
                	else {
                		steakButton.setVisible(true);
                        steakPrice.setVisible(true);
                        chickenButton.setVisible(true);
                        chickenPrice.setVisible(true);
                        fishButton.setVisible(true);
                        fishkPrice.setVisible(true);
                        pizzaButton.setVisible(true);
                        pizzaPrice.setVisible(true);
                        hamburgerButton.setVisible(true);
                        hamburgerPrice.setVisible(true);
                        soupButton.setVisible(true);
                        soupPrice.setVisible(true);
                        latteButton.setVisible(true);
                        lattePrice.setVisible(true);
                        cokeButton.setVisible(true);
                        cokePrice.setVisible(true);
                        orangeJuiceButton.setVisible(true);
                        orangeJuicePrice.setVisible(true);
                        appleJuiceButton.setVisible(true);
                        appleJuicePrice.setVisible(true);
                        chocButton.setVisible(true);
                        chocPrice.setVisible(true);
                        strawberryCakeButton.setVisible(true);
                        strawberryCakePrice.setVisible(true);
                        ShoppingCart.setVisible(true);
                	}
        		}
            }

        });
        
        // Create the Event Handlers for the back Button to go back to the homepage
        Button back = new Button("Back");
        back.setCursor(Cursor.HAND);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	RegisteredUsers lnga = new RegisteredUsers();
            	try {
            		lnga.start(stage);
				} catch (Exception e) {
					System.out.println(e.getMessage());;
				}
            	stage.show();
                
            }

        });
     
        
        GridPane grid = new GridPane();
        grid.setVgap(17);
        grid.setHgap(5);       
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(ticketNOText, 0, 0);
        grid.add(ticketNOTextField, 1, 0);
        grid.add(back, 2, 1);
        grid.setMargin(back, new Insets(-50,0,0,0));
        grid.add(FlightNumberButton, 2, 0);
        grid.add(Shop, 3, 0);
        grid.add(orderText, 0, 1);
        
        grid.add(hbox, 0, 2);
        grid.add(hbox5, 0, 3);
        grid.add(hbox2, 0, 4);
        grid.add(hbox6, 0, 5);
        grid.add(hbox4, 0, 6);
        grid.add(hbox8, 0, 7);
        grid.add(ShoppingCart, 1, 7);
        grid.setStyle("-fx-background-color: #DCDCDC;"+
        		"-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #4B0082;");
        grid.setAlignment(Pos.CENTER);
        BorderPane myborder = new BorderPane();
        myborder.setLeft(grid);
        myborder.setRight(Shop);
        Scene scene = new Scene(myborder, 1500, 650, Color.GAINSBORO); 
        
        //Setting title to the Stage 
        stage.setTitle("Your Special Meal"); 
        //Adding scene to the stage 
        stage.setScene(scene);
        //Displaying the contents of the stage 
        stage.show();
	}
	


}
