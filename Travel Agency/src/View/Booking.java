package View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import Model.Airplane;
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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * This class contains all the booking scene for user to book their flight
 * @author Group 25
 *
 */
public class Booking extends Application{
	
	Stage window;
	Scene BookingPage,PaymentPage, TicketPage;
	private ArrayList<Airplane> dataSet = new ArrayList<>();
	private String adult,child,infant,cabin="";
	private Font myFont;
	private String departure,destination,travelType="";
	private String Fromdate,Todate;
	private String ticketNumber="";
	private String []chosenTime= new String[2]; 
	private String []NameAirLine=new String[2];
	private String[] NOSeats=new String[2];
	
	/**
     *  start method
     * 
     *  The fields and buttons are added to a GridPane, which is added
     *  to the Scene.
     *  @param stage, window The window to be displayed.
     */
	@Override
	public void start(Stage Stage) throws Exception {
		
		window = Stage;
		
		//create font
		myFont = new Font("Segoe Print", 20);
		
		//create Texts
		Text fromText = new Text("From");
		fromText.setStyle("-fx-background-color: #708090");
		fromText.setFont(myFont);
		Text ToText = new Text("To");
		Text To2 = new Text("To");
		To2.setVisible(false);
		ToText.setStyle("-fx-background-color: #708090");
		ToText.setFont(myFont);
		Text travellers = new Text("Travellers");
		travellers.setStyle("-fx-background-color: #708090");
		travellers.setFont(myFont);
		Text cabinText = new Text("Cabin");
		cabinText.setStyle("-fx-background-color: #708090");
		cabinText.setFont(myFont);
		Text adultText = new Text("Adult (12+)");
		Text childText = new Text("Child (2-12)");
		Text infantText = new Text("Infant (0-2)");
		
		//DatePicker to choose date
		DatePicker FromDateCalendar = new DatePicker();
		DatePicker ToDateCalendar = new DatePicker();
		ToDateCalendar.setVisible(false);
		
		/**ToggleGroup for radio buttons to make them in one group,
		 *  so users can choose only one of them
		 */
		ToggleGroup Tgroup = new ToggleGroup();
        RadioButton oneWay  = new RadioButton("One Way");
        oneWay.setToggleGroup(Tgroup);
        oneWay.setSelected(true);
        RadioButton RoundTrip  = new RadioButton("Round Trip");
        RoundTrip.setToggleGroup(Tgroup);
        
        //Create the Event Handlers for the oneWay radio button
        oneWay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToDateCalendar.setVisible(false);
				To2.setVisible(false);
			}
        });
        
        //Create the Event Handlers for the RoundTrip radio button
        RoundTrip.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ToDateCalendar.setVisible(true);
				To2.setVisible(true);
			}
        });
        
        //ArrayList of cities
        ArrayList<String> cities =new ArrayList<>();
        cities.add("London");
        cities.add("Munich");
        cities.add("Stockholm");
        cities.add("Berlin");
        cities.add("Amsterdam");
        cities.add("Paris");
        cities.add("Birmingham");
        cities.add("New York");
        cities.add("Sydney");
        cities.add("Rome");
        cities.add("Milan");
        cities.add("Washington");
        cities.add("Madrid");
        cities.add("Athens");
        cities.add("Nicosia");
        cities.add("Kiev");
        cities.add("Prague");
        cities.add("Vienna");
        cities.add("Sofia");
        cities.add("Bucharest");
        cities.add("Copenhagen");
        
        //sorting cities alphabetically
        Collections.sort(cities,new Comparator<String>(){
			@Override
			public int compare(String s1, String s2) {
				return s1.compareTo(s2);
			}
		});
        
        // create a choiceBox 
        ChoiceBox<String> FromList = new ChoiceBox<>(FXCollections.observableArrayList(cities));
        ChoiceBox<String> ToList = new ChoiceBox<>(FXCollections.observableArrayList(cities));
		
        String numbers[] = {"0","1", "2","3","4","5","6","7","8","9","10"};
        ChoiceBox<String> numListAdult = new ChoiceBox<>(FXCollections.observableArrayList(numbers));
        ChoiceBox<String> numListChild = new ChoiceBox<>(FXCollections.observableArrayList(numbers));
        ChoiceBox<String> numListInfant = new ChoiceBox<>(FXCollections.observableArrayList(numbers));
        
        String cabins[] = { "Economy", "Business"};
        ChoiceBox<String> cabinList = new ChoiceBox<>(FXCollections.observableArrayList(cabins));
        
        // Create the Event Handlers for the search flight
        Button search = new Button("Search Flights");
        search.setCursor(Cursor.HAND);
        search.setStyle("-fx-background-color: #B0C4DE");
        search.setOnAction(new EventHandler<ActionEvent>() {
			@SuppressWarnings({ "unchecked"})
			@Override
			public void handle(ActionEvent event) {
				//checking if any values are null or not
            	departure="";
            	if(FromList.getValue()!=null) {
            		departure=FromList.getValue().toString();
            	}
            	destination="";
            	if(ToList.getValue()!=null) {
            		destination=ToList.getValue().toString();
            	}
            	Fromdate="";
            	if(FromDateCalendar.getValue()!=null) {
            		Fromdate=FromDateCalendar.getValue().toString();
            	}
            	Todate="";
            	if(ToDateCalendar.getValue()!=null) {
            		Todate=ToDateCalendar.getValue().toString();
            	}           	
            	RadioButton selectedRadioButton = (RadioButton)Tgroup.getSelectedToggle();
            	if(selectedRadioButton==null) {
            		travelType="";
            	}
            	else {
            		travelType =selectedRadioButton.getText();
            	}          	
            	if(numListAdult.getValue()!=null) {
            		adult=numListAdult.getValue().toString();
            	}
            	else
            		adult = "";
            	if(numListChild.getValue()!=null) {
            		child=numListChild.getValue().toString();
            	}
            	else
            		child ="";
            	if(numListInfant.getValue()!=null) {
            		infant=numListInfant.getValue().toString();
            	}
            	else
            		infant ="";
            	if(cabinList.getValue()!=null) {
            		cabin=cabinList.getValue().toString();
            	}
            	
            	//if fields are empty an alert will be shown
            	if(departure.equals("") || destination.equals("") || (Fromdate.equals("") & Todate.equals(""))||
            			travelType.equals("")|| cabin.equals("")||(child.equals("")& adult.equals("")&infant.equals("")) ) {
            		Alert EmptyFieldAlert = new Alert(AlertType.INFORMATION);
            		EmptyFieldAlert.setTitle("Warning");
            		EmptyFieldAlert.setContentText("Please fill out all the blanks");
            		EmptyFieldAlert.show();
            	}
            	else {
            		int childN=0;
            		if(!child.equals("")) {
            			childN = Integer.parseInt(child);
            		}
            		int infantN =0;
            		if(!infant.equals("")) {
            		infantN = Integer.parseInt(infant);
            		}
            		
            		// if to date is before the from date an alert will be shown 
            		if(Fromdate.compareTo(Todate)==1) {
                		Alert UnvalidDateAlert = new Alert(AlertType.INFORMATION);
                		UnvalidDateAlert.setTitle("Warning");
                		UnvalidDateAlert.setContentText("Please choose a valid date");
                		UnvalidDateAlert.show();
            		}
            		
            		// if the the number of adults are zero or it's not chosen
            		// but child or infants are chosen, an alert will be shown 
            		else if((adult.equals("")|| adult.equals("0"))&& (childN>0 || infantN>0)) {
            			Alert UnvalidAlert = new Alert(AlertType.INFORMATION);
                		UnvalidAlert.setTitle("Warning");
                		UnvalidAlert.setContentText("Children should be accompanied by adults");
                		UnvalidAlert.show();
            		}
            		
            		//if the number of all passengers are zero or they are not chosen
            		// an alert will be shown
            		else if((adult.equals("")|| adult.equals("0"))&& (child.equals("")|| child.equals("0")&&(infant.equals("")|| infant.equals("0")))) {
            			Alert UnvalidPassengersAlert = new Alert(AlertType.INFORMATION);
            			UnvalidPassengersAlert.setTitle("Warning");
            			UnvalidPassengersAlert.setContentText("Please choose the number of passengers correctly");
            			UnvalidPassengersAlert.show();
            		}
            		else {
            			String [] info= {"checkFlights",travelType,departure,destination, Fromdate,Todate};
            			
            				try {
            					Client.dos.writeObject(info);
            					dataSet.addAll((ArrayList<Airplane>) Client.dis.readObject());
            				}  catch (IOException e) {
            					e.printStackTrace();
            				} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
            				
            				//dataSet is not empty means that there are available flights
            				// a table of available flights will be shown
            				if(!dataSet.isEmpty()) {
            					TableView<Airplane> table = new TableView<Airplane>();
            					TableColumn airlineColumn = new TableColumn("Airline");
                    			airlineColumn.setMinWidth(150);
                    			airlineColumn.setCellValueFactory(
            			                new PropertyValueFactory<Airplane, String>("airline"));
                    			TableColumn departureColumn = new TableColumn("Departure");
                    			departureColumn.setMinWidth(100);
                    			departureColumn.setCellValueFactory(
            			                new PropertyValueFactory<Airplane, String>("departure"));
                    	        TableColumn destinationColumn = new TableColumn("Destination");
                    	        destinationColumn.setMinWidth(100);
                    	        destinationColumn.setCellValueFactory(
            			                new PropertyValueFactory<Airplane, String>("destination"));
                    	        TableColumn dateColumn = new TableColumn("Date");
                    	        dateColumn.setCellValueFactory(
            			                new PropertyValueFactory<Airplane, String>("date"));
                    	        TableColumn timeColumn = new TableColumn("Time");
                    	        timeColumn.setCellValueFactory(
            			                new PropertyValueFactory<Airplane, String>("time"));
                    	        TableColumn priceColumn = new TableColumn("Price");
                    	        priceColumn.setCellValueFactory(
            			                new PropertyValueFactory<Airplane, Integer>("price"));
                    	        TableColumn seatsColumn = new TableColumn("Available Seats");
                    	        seatsColumn.setMinWidth(200);
            			        seatsColumn.setCellValueFactory(
            			                new PropertyValueFactory<Airplane, Integer>("availableSeats"));
            			        
            			        // creating a column of checkbox
            			        TableColumn SelectColumn = new TableColumn("Select");
            			        SelectColumn.setMinWidth(200);
            			        SelectColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Airplane, CheckBox>, ObservableValue<CheckBox>>() {

            			            @Override
            			            public ObservableValue<CheckBox> call(
            			                    TableColumn.CellDataFeatures<Airplane, CheckBox> box) {
            			                Airplane user = box.getValue();

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
            			        
            			        ObservableList<Airplane> dataSetNew = FXCollections.observableArrayList(dataSet);
            			        table.setItems(dataSetNew);
                				table.getColumns().addAll(airlineColumn,departureColumn,destinationColumn,dateColumn,timeColumn,priceColumn,seatsColumn,SelectColumn);
                				
                				
                        	    //Creating a Grid Pane 
                        	    GridPane gridPane2 = new GridPane();    
                        	      
                    	        gridPane2.setVgap(5); 
                    	        gridPane2.setHgap(5);       
                    	        gridPane2.setPadding(new Insets(10, 10, 10, 10));
                    	        gridPane2.setStyle("-fx-background-color: #DCDCDC;"+
                    	        		"-fx-padding: 10;" +
                    					"-fx-border-style: solid inside;" +
                    					"-fx-border-width: 2;" +
                    					"-fx-border-insets: 5;" +
                    					"-fx-border-radius: 5;" +
                    					"-fx-border-color: #4B0082;");
                        	    
                    	        //back to Booking page button
                    	        Button bookPage =new Button("Back to Booking Page");
                    	        bookPage.setMinWidth(160);
                    	        bookPage.setCursor(Cursor.HAND);
                    	        bookPage.setOnAction(new EventHandler<ActionEvent>() {
                    	            @Override
                    	            public void handle(ActionEvent event) {
                    	            	Booking booking = new Booking();
                    	            	try {
                    						booking.start(Stage);
                    					} catch (Exception e) {
                    						e.printStackTrace();
                    					}
                    	            	Stage.show();
                    	                
                    	            }

                    	        });
                        	    
                    	        //create confirm button to confirm the list of chosen flights
                    	        Button confirm =new Button("Confirm");
                    	        confirm.setMinWidth(70);
                    	        confirm.setCursor(Cursor.HAND);
                    	        confirm.setOnAction(new EventHandler<ActionEvent>() {
                    	            @Override
                    	            public void handle(ActionEvent event) {
                    	            	ObservableList<Airplane> selectFlights = FXCollections.observableArrayList();
                    	            	
                    	            	//create a list of chosen flights
                    	        		for(Airplane plane: dataSetNew) {
                    	        			if(plane.isSelect()) {
                    	        			selectFlights.add(plane);
                    	 
                    	        			}
                    	        		}
                    	        		//if none of the flights are chosen an alert will be shown
                    	        		if(selectFlights.size()==0) {
                    	        			Alert FlightNumAlert = new Alert(AlertType.ERROR);
                    	        			FlightNumAlert.setTitle("Warning");
                    	        			FlightNumAlert.setContentText("Please choose at least one ticket");
                    	        			FlightNumAlert.show();
                    	        		}
                    	        		//users cannot choose more than two flights
                    	        		else if(selectFlights.size()>2) {
                    	        			Alert FlightNumAlert = new Alert(AlertType.ERROR);
                    	        			FlightNumAlert.setTitle("Warning");
                    	        			FlightNumAlert.setContentText("You cannot choose flights more than two, Please choose your your return ticket correctly");
                    	        			FlightNumAlert.show();
                    	        		}
                    	        		else {
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
                        	                double TotalPrice=0;
                        	                for (int i=0;i<selectFlights.size();i++) {
                        	            	   double cost = selectFlights.get(i).getPrice();
                        	            	   chosenTime[i] = selectFlights.get(i).getTime();
                        	            	   NameAirLine[i] = selectFlights.get(i).getAirline();
                        	            	   NOSeats[i] = Integer.toString(selectFlights.get(i).getAvailableSeats());
                        	            	   if(cabin.equals("Business")) {
                        	            		   cost = cost*2;
                        	            	   }
                        	            	   double Adultcost = 0;
                        	            	   double ChildrenCost = 0;
                        	            	   if(!adult.equals("")) {
                        	            		   int NumberOfAdults = Integer.parseInt(adult);
                        	            		   Adultcost = NumberOfAdults*cost;
                        	            	   }
                        	            	   if(!child.equals("")) {
                        	            		   int NumberOfChildren = Integer.parseInt(child);
                        	            		   ChildrenCost = NumberOfChildren*(cost/2);
                        	            	   }
                        	            	   TotalPrice += Adultcost +ChildrenCost;
                        	               }
                        	               
                        	               Text TotalPriceText = new Text("Total price for "+ adult+ " adults and "+ child +" children is: "+ TotalPrice);
                        	               TotalPriceText.setFont(myFont);
                        	               
                        	               URL myImg = getClass().getResource("emoji.png");
                        	               String myImgUrl = myImg.toExternalForm();
                        	               Image myImage = new Image(myImgUrl);
                        	               ImageView MyImgView = new ImageView();
                        	               MyImgView.setImage(myImage);
                        	               
                        	               // Create the Event Handlers for button to confitm payment
                        	               Button PaymentConfirmation =new Button("Confirm");
                        	               PaymentConfirmation.setCursor(Cursor.HAND);
                        	               PaymentConfirmation.setOnAction(new EventHandler<ActionEvent>() {
                               	           
                        	            	   //check to see if all the blanks are filled or not 
                        	            	   @Override
                        	            	   public void handle(ActionEvent event) {
	                               	            	if(CardNumberTextField.getText().trim().equals("")||CvvTextField.getText().trim().equals("")||CardNameTextField.getText().equals("") ) {
	                               	            		Alert EmptyFieldAlert2 = new Alert(AlertType.INFORMATION);
	                               	            		EmptyFieldAlert2.setTitle("Warning");
	                               	            		EmptyFieldAlert2.setContentText("Please fill out all the blanks");
	                               	            		EmptyFieldAlert2.show();
	                               	            	/**
	                               	            	 * check to see if the card number has 16 digits or not
	                               	            	 * and to see if it is number or not
	                               	            	 */
	                               	            	} else if (!NumericCheck.isNumeric(CardNumberTextField.getText().trim()) || CardNumberTextField.getText().trim().length() != 16) {
	                               	            		Alert EmptyFieldAlert2 = new Alert(AlertType.INFORMATION);
	                               	            		CardNumberTextField.requestFocus();
	                               	            		EmptyFieldAlert2.setTitle("Warning");
	                               	            		EmptyFieldAlert2.setContentText("Please check your card number should be 16 numbers ");
	                               	            		EmptyFieldAlert2.show();
	                               	            	/**
	                               	            	 * check to see if the cvv number has 3 digits or not
	                               	            	 * and to see if it is number or not
	                               	            	 */
	                               	            	} else if (!NumericCheck.isNumeric(CvvTextField.getText().trim()) || CvvTextField.getText().trim().length() != 3) {
		                               	            		Alert EmptyFieldAlert2 = new Alert(AlertType.INFORMATION);
	                               	            		CvvTextField.requestFocus();
	                               	            		EmptyFieldAlert2.setTitle("Warning");
	                               	            		EmptyFieldAlert2.setContentText("Please check your CVV number should be 3 numbers");
	                               	            		EmptyFieldAlert2.show();
	                               	            	}
	                               	            	
	                               	            	//generate a code for ticket number
	                               	            	else {
	                               	            		ticketNumber = UUID.randomUUID().toString();
	                                   	            	Text ticketext = new Text("You booked a ticket from "+departure+ " to "+destination);
	                                   	            	
	                                   	            	ticketext.setFont(myFont);
	                                   	            	Text tickeNumbertext = new Text("Your ticket number is: "+ticketNumber);
	                                   	            	tickeNumbertext.setFont(myFont);
	                                   	            	Text informationText = new Text("This ticket is issued for "+Client.OnlineUser.getTitle()+" "+Client.OnlineUser.getFirstName()+" "+Client.OnlineUser.getLastName());
	                                   	            	
	                                   	            	informationText.setFont(myFont);
	                                   	            	Text informationText2 = new Text("Your passport number is: "+Client.OnlineUser.getPassportNumber());
	               
	                                   	            	informationText2.setFont(myFont);
	                                   	            	Text wishText = new Text("Wish you a nice trip :)");
	                                   	            	wishText.setFont(myFont);
	                                   	            	Text ThankYouText = new Text("Thank you for choosing us");
	                                   	            	ThankYouText.setFont(myFont);
	                                   	            	String [] travelersInfo = new String[21];
	                                   	            	if (travelType.equals("Round Trip")) {
		                                   	            	travelersInfo[0]="booking";
		                                   	            	travelersInfo[1] = ticketNumber;
		                                   	            	travelersInfo[2] = Client.OnlineUser.getFirstName();
		                                   	            	travelersInfo[3] = Client.OnlineUser.getLastName();
		                                   	            	travelersInfo[4]=Client.OnlineUser.getPassportNumber();
		                                   	            	travelersInfo[5]=Client.OnlineUser.getDateOfBirth();
		                                   	            	travelersInfo[6]=Client.OnlineUser.getNationality();
		                                   	            	travelersInfo[7] = departure;
		                                   	            	travelersInfo[8] = destination;
		                                   	            	travelersInfo[9] = Fromdate;
		                                   	            	travelersInfo[10] =Todate;
		                                   	            	travelersInfo[11] = chosenTime[0];
		                                   	            	travelersInfo[12] = chosenTime[1];
		                                   	            	travelersInfo[13] = travelType;
		                                   	            	travelersInfo[14] = "";
		                                   	            	travelersInfo[15] = "";
		                                   	            	travelersInfo[16] = "";
		                                   	            	travelersInfo[17] = NameAirLine[0];
		                                   	            	travelersInfo[18] = NameAirLine[1];
		                                   	            	travelersInfo[19] = NOSeats[0];
		                                   	            	travelersInfo[20] = NOSeats[1];
	                                   	            	}
	                                   	            	else {
	                                       	            	travelersInfo[0]="booking";
	                                       	            	travelersInfo[1] = ticketNumber;
	                                       	            	travelersInfo[2] = Client.OnlineUser.getFirstName();
	                                       	            	travelersInfo[3] = Client.OnlineUser.getLastName();
	                                       	            	travelersInfo[4]=Client.OnlineUser.getPassportNumber();
	                                       	            	travelersInfo[5]=Client.OnlineUser.getDateOfBirth();
	                                       	            	travelersInfo[6]=Client.OnlineUser.getNationality();
	                                       	            	travelersInfo[7] = departure;
	                                       	            	travelersInfo[8] = destination;
	                                       	            	travelersInfo[9] = Fromdate;
	                                       	            	travelersInfo[10] ="";
	                                       	            	travelersInfo[11] = chosenTime[0];
	                                       	            	travelersInfo[12] = "";
	                                       	            	travelersInfo[13] = travelType;
	                                       	            	travelersInfo[14] = "";
	                                       	            	travelersInfo[15] = "";
	                                       	            	travelersInfo[16] = "";
	                                       	            	travelersInfo[17] = NameAirLine[0];
	                                       	            	travelersInfo[18] = "";
	                                       	            	travelersInfo[19] = NOSeats[0];
	                                       	            	travelersInfo[20] = "";
	                                       	            	}
	                                   	            	
	                                   	            		//writing data to pass to the server
	                                            			try {
	                                            				Client.dos.writeObject(travelersInfo);
	                                        				}  catch (IOException e) {
	                                        					e.printStackTrace();
	                                        				}
	                                            			
	                                            			// write data to pass to the server to update airlines table in database
	                                            			if(travelType.equals("One Way")) {
	                                            				try {
	                                                				String [] UpdateAirLineTable= {"OneWayUpdate",departure,destination, 
	                                                						Fromdate,NameAirLine[0],NOSeats[0]};
	                                                				Client.dos.writeObject(UpdateAirLineTable);
	                                                			} catch (IOException e) {
	                                                				e.printStackTrace();
	                                                			}
	                                            			}
	                                            			
	                                            			// write data to pass to the server to update airlines table in database
	                                            			else {
	                                            				try {
	                                                				String [] UpdateAirLineTable= {"ReturnUpdate",departure,destination,
	                                                						Fromdate,Todate,NameAirLine[0],NameAirLine[1],NOSeats[0],NOSeats[1]};
	                                                				Client.dos.writeObject(UpdateAirLineTable);
	                                                			} catch (IOException e) {
	                                                				e.printStackTrace();
	                                                			}
	                                            			}
	                                            			
	                                            			//create back to homepage button
	                                            			Button HomePage3 =new Button("Back to Home Page");
	                                            			HomePage3.setCursor(Cursor.HAND);
	                                            			HomePage3.setStyle("-fx-background-color: #B0C4DE");
	                                            			HomePage3.setOnAction(new EventHandler<ActionEvent>() {
	                                      	                @Override
	                                      	                public void handle(ActionEvent event) {
                                      	                 		RegisteredUsers BackToHomePage3 = new RegisteredUsers();
                                      	     	            	try {
                                      	     	            		BackToHomePage3.start(Stage);
                                      	     					} catch (Exception e) {
                                      	     						e.printStackTrace();
                                      	     					}
                                      	     	            	Stage.show();
	                                      	                 }
	                                      	             });
                                      	             
                                   	            	
                                   	            	GridPane gridPane4 = new GridPane();
                                   	            	gridPane4.add(ticketext, 1, 1);
                                   	            	gridPane4.add(tickeNumbertext, 1, 2);
                                   	            	gridPane4.add(informationText, 1, 3);
                                   	            	gridPane4.add(informationText2, 1, 4);
                                   	            	gridPane4.add(wishText, 1, 5);
                                   	            	gridPane4.add(ThankYouText, 1, 6);
                                   	            	gridPane4.add(HomePage3, 1, 8);
                                   	            	gridPane4.setAlignment(Pos.CENTER);
                                   	            	
                                   	            	
                                   	            	StackPane mainroot = new StackPane();
                                   	            	mainroot.setId("pane");
                                   	            	mainroot.getChildren().add(gridPane4);
                                   	            	mainroot.setStyle("-fx-padding: 10;" +
                                   	     				"-fx-border-style: solid inside;" +
                                   	 				"-fx-border-width: 2;" +
                                   	 				"-fx-border-insets: 5;" +
                                   	 				"-fx-border-radius: 5;" +
                                   	 				"-fx-border-color: #4B0082;"+
                                   	 				"-fx-background-color: #DCDCDC;");
                                   	            	
                                	        		TicketPage = new Scene(mainroot, 1200, 600,Color.GAINSBORO);
                                	        		TicketPage.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
                                        			window.setScene(TicketPage);
                               	            	}
                               	            }

                               	        });
                        	               
                        	            
                               	      	Button goBack =new Button("Back");
                               	      	goBack.setCursor(Cursor.HAND);
                               	      	goBack.setOnAction(new EventHandler<ActionEvent>() {
                         	                 @Override
                         	                 public void handle(ActionEvent event) {
                         	                	 window.setScene(BookingPage);
                         	                 }

                         	            });
                        	               
                        	            GridPane gridPane3 = new GridPane();
                        	            gridPane3.add(bank, 0, 0);
                        	            gridPane3.add(MyImgView, 1, 0);
                        	            gridPane3.add(CardNumberText, 0, 1);
                        	            gridPane3.add(CardNumberTextField, 1, 1);
                        	            gridPane3.add(CvvText, 0, 2);
                        	            gridPane3.add(CvvTextField, 1, 2);
                        	            gridPane3.add(CardNameText, 0, 3);
                        	            gridPane3.add(CardNameTextField, 1, 3);
                        	            gridPane3.add(TotalPriceText, 0, 4);
                        	            gridPane3.add(PaymentConfirmation, 0, 5);
                        	            gridPane3.add(goBack, 1, 5);
                        	            gridPane3.setAlignment(Pos.CENTER);
                        	               
                        	            PaymentPage = new Scene(gridPane3, 1200, 600,Color.GAINSBORO);
                        	            window.setScene(PaymentPage);
                    	        	}
                    	                
                	            }

                	        });
                    	        
                	        gridPane2.add(table, 0, 1);
                	        gridPane2.add(bookPage,1,1);
                	        gridPane2.add(confirm,2,1);
                	        BookingPage = new Scene(gridPane2, 1200, 600,Color.GAINSBORO);
                			window.setScene(BookingPage);
        				}
            				
            			//if there is no avaialable flight, an alert will be shown
        				else {
        					Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Warning");
                            alert.setContentText("Sorry, There is no available flight");
                            alert.show();
        				}
            		}
            	}
			}
        	
        });
        
        //create back button
        Button HomePage =new Button("Back to Home Page");
        HomePage.setCursor(Cursor.HAND);
        HomePage.setStyle("-fx-background-color: #B0C4DE");
        HomePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	RegisteredUsers BackToHomePage2 = new RegisteredUsers();
            	try {
            		BackToHomePage2.start(Stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	Stage.show();            
            }
        });
        
        URL bookingimg = getClass().getResource("Homepage.jpg");
		String bookingimgUrl = bookingimg.toExternalForm();
        Image bookingimage = new Image(bookingimgUrl);
        
        // defines a viewport into the source image (achieving a "zoom" effect) and
        // displays it rotated
        ImageView ivbooking = new ImageView();
        ivbooking.setImage(bookingimage);
        ivbooking.setFitWidth(650);
        ivbooking.setFitHeight(350);
        ivbooking.setSmooth(true);
        ivbooking.setCache(true);
        HBox hbox = new HBox();
		GridPane gridPane = new GridPane();
		gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(fromText, 0, 0);
        gridPane.add(FromList, 1, 0);
        gridPane.add(ToText, 0, 1);
        gridPane.add(ToList, 1, 1);
        gridPane.add(oneWay, 0, 2);
        gridPane.add(RoundTrip, 1, 2);
        gridPane.add(FromDateCalendar, 0, 3);
        gridPane.add(To2, 1, 3);
        gridPane.add(ToDateCalendar, 2, 3);
        gridPane.add(travellers, 0, 4);
        gridPane.add(adultText, 0, 5);
        gridPane.add(numListAdult, 1, 5);
        gridPane.add(childText, 2, 5);
        gridPane.add(numListChild, 3, 5);
        gridPane.add(infantText, 4, 5);
        gridPane.add(numListInfant, 5, 5);
        gridPane.add(cabinText, 0, 6);
        gridPane.add(cabinList, 1, 6);
        gridPane.add(search, 2, 7);
        gridPane.add(HomePage, 2, 8);
        
        gridPane.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #4B0082;"+
				"-fx-background-color: #DCDCDC;");
        
        gridPane.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #4B0082;"+
				"-fx-background-color: #DCDCDC;");
        hbox.getChildren().add(gridPane);
        hbox.getChildren().add(ivbooking);
        hbox.setAlignment(Pos.CENTER);
		Group root = new Group(hbox);
		
        //Creating a scene object 
        Scene scene = new Scene(root, 1200, 620, Color.GAINSBORO); 

        //Setting title to the Stage 
        window.setTitle("Booking");
        
        //Adding scene to the stage
        window.setScene(scene);
        window.setWidth(1450);
        window.setHeight(620);
        //Displaying the contents of the stage 
        window.show();
		
	}


}
