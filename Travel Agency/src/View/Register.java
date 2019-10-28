package View;

import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The class is for user create account
 * @author Group 25
 */
public class Register extends Application {
	
	private static final Font myFont = new Font("Segoe Print", 20);
	private static final String titles[] = { "Mr", "Mrs", "Miss"};
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
		
		//Text
		Text aboutYou = new Text("About You");
		aboutYou.setStyle("-fx-background-color: #708090");
		aboutYou.setFont(myFont);
		Text titleText = new Text("Title :");
		Text firstNameText = new Text("First Name :");
		Text lastNameText = new Text("Last Name :");
		Text genderText = new Text("Gender :");
		Text DoBText = new Text("Date of Birth :");
		Text passportNOText = new Text("Passport Number :");
		Text nationalityText = new Text("Nationality :");
		Text addressText = new Text("Address :");
		Text countryText = new Text("Country of Residence :");
		Text cityText = new Text("City :");
		Text zipCodeText = new Text("Zip Code :");
		Text ContactDetails = new Text("Your contact details");
		ContactDetails.setStyle("-fx-background-color: #708090");
		ContactDetails.setFont(myFont);
		Text telephoneText = new Text("Telephone Number :");
		Text loginInfo = new Text("Login Information");
		loginInfo.setStyle("-fx-background-color: #708090");
		loginInfo.setFont(myFont);
		Text emailText = new Text("Email :"); 
		Text usernameText = new Text("Username :");
        Text passwordText = new Text("Password :");
        Text securityQuestion = new Text("Security question :");
        Text securityAnswer = new Text("Security answer :");
        
        //TextField
        TextField usernameTextField = new TextField();
        TextField firstnameTextField = new TextField();
        TextField lastnameTextField = new TextField();
        TextField passportNOTextField = new TextField();
        TextField nationalityTextField = new TextField();
        TextField CountryTextField = new TextField();
        TextField cityTextField = new TextField();
        TextField emailTextField = new TextField();
        TextField addressTextField = new TextField();
        TextField zipCodeTextField = new TextField();
        TextField telephoneTextField = new TextField();
        TextField securityAnswerTextField = new TextField();
        Tooltip tooltip=new Tooltip("Answer the question you have selected");
        securityAnswerTextField.setTooltip(tooltip);
        
        //password field
        PasswordField passwordTextField = new PasswordField(); 
        
        ToggleGroup Tgroup = new ToggleGroup();
        RadioButton male  = new RadioButton("Male");
        male.setToggleGroup(Tgroup);
        RadioButton female  = new RadioButton("Female");
        female.setToggleGroup(Tgroup);
        female.setSelected(true);
        
        DatePicker calendar = new DatePicker();
        calendar.setValue(LocalDate.now());
        
        //choice box for security question
        ChoiceBox<String> titleList = new ChoiceBox<>(FXCollections.observableArrayList(titles));
        titleList.setValue(titles[0]);
        ChoiceBox<String> securityQuestionList = new ChoiceBox<>(FXCollections.observableArrayList(questions));
        securityQuestionList.setValue(questions[0]);
   
        Button registered = new Button("Register");
        registered.setCursor(Cursor.HAND);
        registered.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	String title = titleList.getValue();
            	String firstname = firstnameTextField.getText();
            	String lastname = lastnameTextField.getText();
            	RadioButton selectedRadioButton = (RadioButton)Tgroup.getSelectedToggle();
            	String gender = selectedRadioButton.getText();
            	String DoB = calendar.getValue().toString();
            	String passportNO = passportNOTextField.getText();
            	String nationality = nationalityTextField.getText();
            	String country = CountryTextField.getText();
            	String email = emailTextField.getText();
            	String address = addressTextField.getText();
            	String city = cityTextField.getText();
            	String zipcode = zipCodeTextField.getText();
            	String telephone = telephoneTextField.getText();
            	String username = usernameTextField.getText();
            	String password = passwordTextField.getText();
            	String sq = securityQuestionList.getValue();
            	String sa = securityAnswerTextField.getText();
            	
            	if(firstname.equals("") || lastname.equals("") || passportNO.equals("") || nationality.equals("") ||
            			country.equals("") || email.equals("") || address.equals("") || city.equals("") ||
            			zipcode.equals("") || telephone.equals("") || username.equals("") ||
            			password.equals("") || sa.equals("")) {
            		Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Registration");
                    alert.setContentText("Please fill out all the blanks");
                    alert.show();
            	}
            	else {
            		Alert WarningAlert = new Alert(AlertType.ERROR);
            		WarningAlert.setTitle("Warning!");
            		boolean check = true;
            		//username and emails of users cannot be the same
            		for (int i=0;i<Client.UsersList.size();i++) {
            			if(Client.UsersList.get(i).getEmail().equals(email.trim()) &&
            					Client.UsersList.get(i).getUsername().equals(username.trim())) {
            				WarningAlert.setContentText("These Username and email already exist");
            				WarningAlert.show();
            				check = false;
            			}
            			
            			//users cannot create two accounts with one email 
            			else if(Client.UsersList.get(i).getEmail().equals(email.trim())) {
            				WarningAlert.setContentText("You have an account with this email address");
            				WarningAlert.show();
            				check = false;
            			}
            			
            			//usernames cannot be the same
            			else if(Client.UsersList.get(i).getUsername().equals(username.trim())) {
            				WarningAlert.setContentText("This username exists, Please choose another username");
            				WarningAlert.show();
            				check = false;
            			}
            		}
            		
            		/**
            		 * information of users is sent to server to be
            		 * saved in Users table in database
            		*/
            		String [] inputs= {"register",title,firstname,lastname,gender,DoB,passportNO,nationality,
            				country,email,address,city,zipcode,telephone,username,password,sq,sa};
            		if(check==true) {
            			try {
        					Client.dos.writeObject(inputs);
        				}  catch (IOException e) {
        					System.out.println("Something wrong with outputting register information");
        				}
            			
            			Alert WelcomeAlert = new Alert(AlertType.CONFIRMATION);
    					WelcomeAlert.setTitle("Confirmation");
    					WelcomeAlert.setContentText("Welcome "+firstname+" :)");
    					WelcomeAlert.show();
    					
    					/**
    					 * after creating an accout users are refered to 
    					 * registered user's home page
    					 */
    					Client client = new Client();
    					try {
							client.start(stage);
						} catch (IOException e) {
							e.printStackTrace();
						}
    					stage.show();
    				}
            	}
            }

        });
        
        //back button 
        Button HomePage =new Button("Back to Home Page");
        HomePage.setCursor(Cursor.HAND);
        HomePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	Client hmp = new Client();
            	try {
					hmp.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
            	stage.show();
                
            }

        });
        
        //Creating a Grid Pane 
        GridPane gridPane = new GridPane();
        //GridPane.setHalignment(tmp[currArrPos], HPos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        gridPane.setPadding(new Insets(10,10,10,10)); 
        gridPane.setStyle("-fx-background-color: #DCDCDC");
        gridPane.setMargin(male, new Insets(0,0,0,-60));
        gridPane.add(aboutYou, 0, 0);
        gridPane.add(titleText, 0, 1);
        gridPane.add(titleList, 1, 1);
        gridPane.add(firstNameText, 0, 2);
        gridPane.add(firstnameTextField, 1, 2);
        gridPane.add(lastNameText, 2, 2);
        gridPane.add(lastnameTextField, 3, 2);
        gridPane.add(genderText, 0, 3);
        gridPane.add(female, 1, 3);
        gridPane.add(male, 2, 3);
        gridPane.add(DoBText, 0, 4);
        gridPane.add(calendar,1,4);
        gridPane.add(passportNOText, 0, 5);
        gridPane.add(passportNOTextField, 1, 5);
        gridPane.add(nationalityText, 0, 6);
        gridPane.add(nationalityTextField, 1, 6);
        gridPane.add(countryText, 2, 6);
        gridPane.add(CountryTextField, 3, 6);
        gridPane.add(ContactDetails, 0, 7);
        gridPane.add(emailText, 0, 8);
        gridPane.add(emailTextField, 1, 8);
        gridPane.add(addressText, 0, 9);
        gridPane.add(addressTextField, 1, 9);
        gridPane.add(cityText, 2, 9);
        gridPane.add(cityTextField, 3, 9);
        gridPane.add(zipCodeText, 0, 10);
        gridPane.add(zipCodeTextField, 1, 10);
        gridPane.add(telephoneText, 0, 11);
        gridPane.add(telephoneTextField, 1, 11);
        gridPane.add(loginInfo, 0, 12);
        gridPane.add(usernameText, 0, 13);
        gridPane.add(usernameTextField, 1, 13);
        gridPane.add(passwordText, 0, 14);
        gridPane.add(passwordTextField, 1, 14);
        gridPane.add(registered, 1, 15);
        gridPane.add(HomePage, 2, 15);
        //new
        gridPane.add(securityQuestion, 2, 10);
        gridPane.add(securityQuestionList, 3, 10);
        gridPane.add(securityAnswer, 2, 11);
        gridPane.add(securityAnswerTextField, 3, 11);
        
        gridPane.setStyle("-fx-background-color: #DCDCDC;"+
        		"-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #4B0082;");
        gridPane.setAlignment(Pos.CENTER);
        Group root = new Group(gridPane);
        //Creating a scene object 
        Scene scene = new Scene(root, 1200, 620, Color.GAINSBORO); 
       
        //Setting title to the Stage 
        stage.setTitle("Register"); 
        
        //Adding scene to the stage 
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(600);
        //Displaying the contents of the stage 
        stage.show();		
	}
}
