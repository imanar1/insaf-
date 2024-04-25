/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insafproject;
import insafproject.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author USER
 */
public class insafproject extends Application {
Scene scene1;
Scene sceneSignUp;
Scene sceneLogin;
Scene scenechoose;
Scene scenestore;
Scene scenetotal;
Scene sceneDebtscheduler;
Scene scenebasket;
Scene informationdebet;
Scene allDebts;
Scene cred;
       private boolean isNameValid(String name) {
        // Check if name, national ID, or date of birth is null or empty
        if (name == null || name.isEmpty()) {
            return false;
        }

        // Check if name contains only letters
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))) {
                return false;
            }
        }
      return true;
  
    }
private static boolean isPasswordValid(String passworde) {
    
    // Validation logic for password field
    boolean hasUppercase = !passworde.equals(passworde.toLowerCase());
    boolean hasLowercase = !passworde.equals(passworde.toUpperCase());
    boolean hasSpecialChar = containsSpecialCharacter(passworde);

    return passworde != null && !passworde.isEmpty() && passworde.length() >= 8 && hasUppercase && hasLowercase && hasSpecialChar;
}

private static boolean containsSpecialCharacter(String password) {
    // Define the special characters you want to include
    String specialChars = "!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";

    for (char ch : password.toCharArray()) {
        if (specialChars.indexOf(ch) != -1) {
            return true;
        }
    }
    
    return false;
}
private boolean isNationalIdValid(String nationalId) {
    // Validation logic for national ID field
    if (nationalId == null || nationalId.length() != 10) {
        return false;
    }

    for (char ch : nationalId.toCharArray()) {
        if (ch < '0' || ch > '9') {
            return false;
        }
    }

    return true;
}
 private boolean isFieldEmpty(String value) {
        return value == null || value.isEmpty();
    }
 
       private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
       
    @Override
    
    public void start(Stage primaryStage){
//    scene 1 - first page to choise log in or sing up 

Image insaf = new Image("/images/insaflogo2.png");
ImageView insafim = new ImageView(insaf);
insafim.setFitHeight(100);
insafim.setFitWidth(200);
 // Create a fade-inanimation for the root VBox container by 4 seconds 
FadeTransition fadeImage = new FadeTransition(Duration.seconds(4), insafim);
fadeImage.setFromValue(0);
fadeImage.setToValue(1);
fadeImage.play();

Label first = new Label("قال تعالى");
first.setStyle("-fx-font-size: 18px; -fx-text-fill: #5F7470;-fx-font-family:AlNile; -fx-font-weight: bold;");

Label second = new Label("إن الله يأمركم أن تؤدوا الامانات إلى أهلها‍");
second.setStyle("-fx-font-size: 15px;-fx-text-fill: #5F7470;-fx-font-family: AlNile;");



Button singButton = new Button("Sing Up");
singButton.setStyle("-fx-background-color: #5F7470;-fx-background-radius:15; -fx-text-fill: white;");
singButton.setPrefWidth(120);
// move to sing up scene 
singButton.setOnAction((e)-> {
      
       primaryStage.setScene(sceneSignUp);
});


Button loginButtoncredtor = new Button("log In");
loginButtoncredtor.setStyle("-fx-background-color: #5F7470;-fx-background-radius:15;-fx-text-fill: white;");
loginButtoncredtor.setPrefWidth(130);

// action 
        loginButtoncredtor.setOnAction((e)-> {
      
       primaryStage.setScene(sceneLogin);
});
        
// vbox to show loginButtoncredtor and singButton under each other respectively
VBox buttonContainer = new VBox();
buttonContainer.setSpacing(40);
buttonContainer.setPadding(new Insets(10));
buttonContainer.setAlignment(Pos.CENTER);
buttonContainer.getChildren().addAll(loginButtoncredtor,singButton); // Added signupButton after loginButton


// Create layout vbox root to show all thing in scene under each other respectively by 10 spaceing 
VBox root = new VBox(10);
root.setAlignment(Pos.CENTER);
root.setPrefSize(300, 500);
root.setPadding(new Insets(10));

root.getChildren().addAll(insafim, first, second, buttonContainer);

//add background color 

root.setStyle("-fx-background-color: #e0e2db;");


 // Create a fade-inanimation for the root VBox container by 2 seconds
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(2);
        fadeTransition.play();
        
String QuranFile = "file:///" + "C:/Users/USER/Downloads/insafQuran2.mp3";
Media sound = new Media(QuranFile);
MediaPlayer mediaPlayer = new MediaPlayer(sound);
mediaPlayer.setAutoPlay(true);

 /*-----------------Scene 3  -----------------*/
        ObservableList<String> obNames =  FXCollections.observableArrayList();
        ObservableList<Integer> obnationalIdField =  FXCollections.observableArrayList();
        ObservableList<String> obstatus =  FXCollections.observableArrayList();
        ObservableList<String> obSpasswordField  =  FXCollections.observableArrayList();
        ObservableList<String>  obsbirthDate  =  FXCollections.observableArrayList();
        
        
        Image insaf2 = new Image("/images/insaflogo2.png");
        ImageView insafim2 = new ImageView(insaf2);
        insafim2.setFitWidth(150);
        insafim2.setFitHeight(70);
        insafim2.setTranslateY(-10);

        Label welcomeBackLabel = new Label("Welcome Back!");
        welcomeBackLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #e0e2db; -fx-font-weight: bold;");

        Label idLabel = new Label("National ID");
        idLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
        TextField idField = new TextField();
        idField.setPromptText("Enter National ID");
        idField.setPrefHeight(40);
        idField.setStyle("-fx-background-radius: 10px;");

        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: ##000000;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setPrefHeight(40);
        passwordField.setStyle("-fx-background-radius: 10px;");

        VBox fieldsVBox = new VBox(15, idLabel, idField, passwordLabel, passwordField);
        fieldsVBox.setAlignment(Pos.CENTER_LEFT);

        Label bottomLabel = new Label("Don't have an account?");
        bottomLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #000000; -fx-font-weight: bold;");

        Label signUpLabel = new Label("Sign Up");
        signUpLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #159396; -fx-font-weight: bold;");

        signUpLabel.setOnMouseClicked(event -> {
            primaryStage.setScene(sceneSignUp);
        });

        Button loginButton = new Button("Log In");
        loginButton.setStyle("-fx-background-color: #5f7470; -fx-text-fill: #ffffff; -fx-background-radius: 10px; -fx-font-weight: bold;");
        loginButton.setPrefWidth(240);
        loginButton.setPrefHeight(40);
        

        VBox topBox = new VBox(insafim2, welcomeBackLabel);
        topBox.setAlignment(Pos.TOP_CENTER);
        topBox.setPadding(new Insets(10));
        topBox.setStyle("-fx-background-color: #e0e2db;");

        HBox bottomBox = new HBox(bottomLabel, signUpLabel);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setStyle("-fx-background-color: #e0e2db;");

        VBox root2 = new VBox(10);
        root2.setAlignment(Pos.CENTER);
        root2.setPrefSize(300, 500);
        root2.setPadding(new Insets(10));
        VBox.setMargin(fieldsVBox, new Insets(0, 20, 0, 20));

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");


 Label lbname = new Label();
 Label userNameLabel = new Label();
Label userNameLabel2 = new Label();


loginButton.setOnAction(event -> {
String id = idField.getText();
String password = passwordField.getText();
    if (password.isEmpty() || id.isEmpty()) {
        errorLabel.setText("Password or ID cannot be empty.");
    } else {
        
        List<userinfo> userinfos = new ArrayList<>();
        Session sessionread = HibernateUtil.getSessionFactory().openSession();
        Query query = sessionread.createQuery("from userinfo");
        userinfos = query.list();
        sessionread.close();
        boolean isLoginSuccessful = false;
        for (userinfo c : userinfos) {
    System.out.println("User ID: " + c.getNationalId());
    System.out.println("User Password: " + c.getUserPassword());

            obnationalIdField.add(c.getNationalId());
            obSpasswordField.add(String.valueOf(c.getUserPassword()));
            
            if (id.equals(String.valueOf(c.getNationalId())) && password.equals(String.valueOf(c.getUserPassword()))) {
                isLoginSuccessful = true;
                // userStatus = c.getUserStatus(); // Store the status of the user
                lbname.setText("Welcom "+ c.getUserName());
                userNameLabel.setText("Welcom "+ c.getUserName());
                userNameLabel2.setText("Welcom "+ c.getUserName());
                break;
            }
        }
        if (isLoginSuccessful) {
        
            primaryStage.setScene(scenechoose);
            
        } else {
            errorLabel.setText("Invalid ID or password. Please try again.");
        }
    }
});


        root2.getChildren().addAll(topBox, fieldsVBox, errorLabel, loginButton, bottomBox);
        root2.setStyle("-fx-background-color: #e0e2db;");
     
    
        /*-----------------Scene 3  -----------------*/
   
   
        TextField fullNameField = new TextField();
        fullNameField.setPromptText("Enter your name");
        fullNameField.setPrefHeight(30);
        fullNameField.setStyle("-fx-background-radius: 10px;");
        
        Label binding1 = new Label("hello");
        Label binding2 = new Label();
        binding1.setTextFill(Color.web("#159396"));
        binding2.textProperty().bind(fullNameField.textProperty());
        binding2.setTextFill(Color.web("#159396"));
        
        // Create national ID field
        TextField nationalIdField = new TextField();
        nationalIdField.setPromptText("Enter your national ID");
        nationalIdField.setPrefHeight(30);
        nationalIdField.setStyle("-fx-background-radius: 10px;");

        // Create ComboBox option
        ComboBox<String> option = new ComboBox<>();
        option.setStyle("-fx-background-radius: 10px;-fx-background-color: #5f7470;");
        option.setValue("Choose your status:");
        option.getItems().addAll("Debtor", "Creditor");
        option.setPrefHeight(30);
        option.setStyle("-fx-background-radius: 10px;-fx-background-color: #5f7470;");
        // Create date of birth field
        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("Date of Birth");
        dobPicker.setPrefHeight(20);
        dobPicker.setPrefWidth(230);

        // Create password fields
        PasswordField passwordField2 = new PasswordField();
        passwordField2.setPromptText("Enter your password");
        passwordField2.setPrefHeight(30);
        passwordField2.setStyle("-fx-background-radius: 10px;");

        Label userNameLabel3 = new Label();
        Label userNameLabel4 = new Label();
        // Create sign up button
        Button signUp = new Button("Sign Up");
        signUp.setStyle("-fx-background-color: #5f7470;-fx-text-fill: #ffffff; -fx-background-radius: 10px;-fx-font-weight: bold;");
        signUp.setPrefWidth(200);
        signUp.setPrefHeight(40);
//        check before sing up 

        signUp.setOnAction((e)-> {
    String Name = fullNameField.getText();
    String nationalId = nationalIdField.getText();
    String status = option.getValue().toString();
    String password2 = passwordField2.getText();
    LocalDate birthDate = dobPicker.getValue();
        userinfo info = new userinfo();
            HBox bindHbox = new HBox();
  Label userName1 = new Label();
            if (Name.isEmpty() || nationalId.isEmpty() || password2.isEmpty()) {
    showAlert("All fields are required!");
} else if ( option.getSelectionModel().isEmpty()) {
    showAlert("Please select your status!");
} else if (!isNationalIdValid(nationalId)) {
    showAlert("Invalid national ID format! National ID should contain exactly 10 digits.");
} else if (!isPasswordValid(password2)) {
    showAlert("Invalid password format! Password must contain at least 8 characters including lowercase and uppercase letters, numbers, and special characters.");
}
else {
    obNames.add(Name);
    obnationalIdField.add(Integer.parseInt(nationalId));
    obstatus.add(status);
    obSpasswordField.add(password2);
    obsbirthDate.add(birthDate.toString());
    
    info.setUserName(fullNameField.getText());
info.setNationalId(Integer.parseInt(nationalIdField.getText()));
info.setUserPassword(passwordField2.getText());
info.setBirthdate(dobPicker.getValue().toString());
//info.setStatus(option.getValue());

                    Session session = HibernateUtil.getSessionFactory().openSession();
                    Transaction t = session.beginTransaction();
                    session.save(info);
                    t.commit();
                    session.close();
  lbname.setText("Welcom "+ info.getUserName());
  userNameLabel3.setText("Welcom "+ info.getUserName());
  userNameLabel4.setText("Welcom "+ info.getUserName());
    primaryStage.setScene(scenechoose);
}         
});
        

        // Create "Welcome Back" label
        Label welcomeBackLabel2 = new Label("Register With Us !");
        welcomeBackLabel2.setStyle("-fx-font-size: 15px;-fx-text-fill: #000000; -fx-font-weight: bold;");

        // Create "Your information is safe with us" label
        Label infoLabel = new Label("Your information is safe with us");
        infoLabel.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;-fx-text-fill: #bcbcbc;");

        VBox labelsBox = new VBox(5);
        labelsBox.getChildren().addAll(welcomeBackLabel2, infoLabel);
        labelsBox.setAlignment(Pos.CENTER);

        // Create "bottom" label
        Label bottomText2 = new Label("Already have an account !");
        bottomText2.setStyle("-fx-font-size: 12px; -fx-text-fill: #000000;-fx-font-weight: bold;");

        // Create "Welcome Back" label
        Label login = new Label(" Log In ");
        login.setStyle("-fx-font-size: 12px;-fx-text-fill: #159396; -fx-font-weight: bold;");
//   if have Already have an account then move to login scene 
    login.setOnMouseClicked(event -> {
            primaryStage.setScene(sceneLogin);
        });

        VBox topBox2 = new VBox(20);
        topBox2.getChildren().addAll(labelsBox);
        topBox2.setAlignment(Pos.TOP_CENTER); // Align topBox to the top center of the screen
        topBox2.setPadding(new Insets(10));

        HBox bottomBox2 = new HBox();
        bottomBox2.getChildren().addAll(bottomText2, login);
        bottomBox2.setAlignment(Pos.BOTTOM_CENTER); // Align topBox to the top center of the screen
        bottomBox2.setPadding(new Insets(10));
        bottomBox2.setStyle("-fx-background-color: #e0e2db;");
        // Create layout
        VBox root3 = new VBox(10);
        root3.setAlignment(Pos.CENTER);
        root3.setPrefSize(300, 500);
        root3.setPadding(new Insets(10));
        VBox.setMargin(fullNameField, new Insets(20, 20, 4, 20));
        VBox.setMargin(nationalIdField, new Insets(0, 20, 4, 20));
        VBox.setMargin(option, new Insets(0, 20, 4, 20)); // Add margin to the ComboBox
        VBox.setMargin(dobPicker, new Insets(0, 0, 4, 0));
        VBox.setMargin(passwordField2, new Insets(0, 20, 4, 20));
//        VBox.setMargin(bindHbox, new Insets(0, 20, 30, 20));
        root3.getChildren().addAll(topBox2, binding1, binding2, fullNameField, nationalIdField, option, dobPicker, passwordField2, signUp, bottomBox2);
                                     
     
        // Set background color of root layout
        root3.setStyle("-fx-background-color: #e0e2db;");
       
                /*-----------------Scene 4  -----------------*/
// 
//        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/imagebackground.png"));
//        ImageView backgroundImageView = new ImageView(backgroundImage);
//        backgroundImageView.setFitWidth(300);
//        backgroundImageView.setFitHeight(500);
//        backgroundImageView.setPreserveRatio(false);
//
//        Image userImage = new Image(getClass().getResourceAsStream("/images/userpurple2.png"));
//        ImageView userImageView = new ImageView(userImage);
//        userImageView.setX(10);
//        userImageView.setY(30);
//     
//        userImageView.setFitHeight(70);
//        userImageView.setFitWidth(70);
//        
//       
//      //userinfo info = new userinfo();
//      
//        //Label lbname = new Label();
//       // lbname.setText("welcome,"+info.getUserName());
//        lbname.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
//
////        lbname.setStyle("-fx-text-fill: #968393;");
//        lbname.setStyle("-fx-text-fill: #889696;");
//
//        HBox topuser= new HBox();
//        topuser.setSpacing(10);
////      topuser.setAlignment(Pos.CENTER);
//        topuser.getChildren().addAll(userImageView,lbname);  
//        topuser.setTranslateX(30);
//        topuser.setTranslateY(20);
//        
////        shahad
//        Image homePicm = new Image(getClass().getResourceAsStream("/images/home.png"));
//        Image searchPicm = new Image(getClass().getResourceAsStream("/images/loupe.png"));
//        Image user2Picm = new Image(getClass().getResourceAsStream("/images/account.png"));
//
//        ImageView userImageView2m = new ImageView(user2Picm);
//        userImageView2m.setFitHeight(30);
//        userImageView2m.setFitWidth(30);
//
//        ImageView serchm = new ImageView(searchPicm);
//        serchm.setFitWidth(30);
//        serchm.setFitHeight(30);
//
//        ImageView homem = new ImageView(homePicm);
//        homem.setFitWidth(30);
//        homem.setFitHeight(30);
////        
//HBox mainBtns = new HBox(90);
//mainBtns.setAlignment(Pos.CENTER);
//mainBtns.setPadding(new Insets(10));
//
//mainBtns.setStyle("-fx-background-color:#5f7470");
//mainBtns.setPrefHeight(50); // set the height of the HBox
//
//mainBtns.setPrefWidth(backgroundImageView.getFitWidth()); // set the width of the HBox
//mainBtns.setLayoutX(0);
//mainBtns.setLayoutY(backgroundImageView.getFitHeight() - mainBtns.getPrefHeight());
//
//mainBtns.getChildren().addAll(homem,serchm,userImageView2m);
//
//       Label cho = new Label("Please choose : ");
//      cho.setStyle("-fx-font-size: 15px; -fx-text-fill: #000000;");
//            cho.setTranslateY(-60);
//            cho.setTranslateX(-20);
//
////        ComboBox comboBox1 = new ComboBox();
////        comboBox1.setValue("choose your city");
////        comboBox1.getItems().addAll("Makkah", "Jeddah");
////      
//
// ObservableList<String> obSDistrct  =  FXCollections.observableArrayList();
//        ObservableList<String>  obsGroceryname  =  FXCollections.observableArrayList();
//        
//        ComboBox comboBox2 = new ComboBox();
//        comboBox2.setValue("choose your Distrct");
//        comboBox2.getItems().addAll("Al-awali", "Al-sharayie");
//
//        ComboBox comboBox3 = new ComboBox();
//        comboBox3.setValue("choose Grocery name");
//        comboBox3.getItems().addAll("AlRemal");
//        comboBox3.setStyle("-fx-background-color: #d2d4c8;-fx-background-radius: 10px;");
//        comboBox2.setStyle("-fx-background-color: #d2d4c8;-fx-background-radius: 10px;");
//        comboBox3.setTranslateY(-50);
//        comboBox2.setTranslateY(-60);
//           comboBox2.setTranslateX(-10);
//            comboBox3.setTranslateX(-10);
//        
////           creditordistct c= new creditordistct();
////           String Groceryn=(String) comboBox2.getValue();
////           String Distrct= (String)comboBox3.getValue();
//
////errorLabelok.setVisible(false);
//        Button okButton = new Button("ok");
//        okButton.setStyle("-fx-background-color: #ad9baa; -fx-text-fill: #ffffff; -fx-background-radius: 10px; -fx-font-weight: bold;");
//        okButton.setPrefWidth(90);
//        okButton.setPrefHeight(40); 
//        okButton.setTranslateY(-40);
//        okButton.setTranslateX(-40);
//        okButton.setAlignment(Pos.CENTER);
//   okButton.setOnAction((e) -> {
//       
//    if (comboBox2.getSelectionModel().isEmpty() || comboBox3.getSelectionModel().isEmpty()) {
////        errorLabelok.setText("Please select a value from all ComboBox.");
//        showAlert("Please select a value from all ComboBox.");
//    } else {
//        
//        String Groceryn = comboBox3.getValue().toString();
//        String Distrct = comboBox2.getValue().toString();
//
//        creditordistct c= new creditordistct();
//        c.setCreditorname(Groceryn);
//        c.setDistctname(Distrct);
//         Random random = new Random();
//        int randomNum = random.nextInt(); 
//       c.setId(randomNum);
// 
//       
//        obSDistrct.add(Distrct);
//        obsGroceryname.add(Groceryn);
//        
//     
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction t = session.beginTransaction();
//        session.save(c);
//        t.commit();
//        session.close();
//        primaryStage.setScene(scenestore);
//            }
//        }
//        );
//   
//        VBox vb = new VBox();
//        vb.setPadding(new Insets(20));
//        vb.setSpacing(20);
//        vb.setAlignment(Pos.CENTER);
//        vb.getChildren().addAll(cho,comboBox2,comboBox3,okButton);
//        
//        
//        BorderPane pan = new BorderPane();
//        pan.setTop(topuser);
//        pan.setCenter(vb);
//        pan.setBottom(mainBtns);
// 
//       Button home1 = new Button("",homem);
//       home1.setBackground(null);
//       Button serch1 = new Button("",serchm);
//       serch1.setBackground(null);
//       Button userbt1 = new Button("",userImageView2m);
//       userbt1.setBackground(null);
//         home1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//           public void handle(ActionEvent e) {
//               primaryStage.setScene(scene1);
//                }   
//            } );
// 
// 
// 
// serch1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//           public void handle(ActionEvent e) {
//          primaryStage.setScene(scenechoose);
//                ;}   
//            } );
// 
// 
// userbt1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//           public void handle(ActionEvent e) {
//        primaryStage.setScene(scenebasket);
//              }});
//       
//StackPane root4 = new StackPane();
//root4.getChildren().addAll(backgroundImageView, pan);


        Image backgroundImage = new Image(getClass().getResourceAsStream("/images/imagebackground.png"));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(300);
        backgroundImageView.setFitHeight(500);
        backgroundImageView.setPreserveRatio(false);

        Image userImage = new Image(getClass().getResourceAsStream("/images/userpurple2.png"));
        ImageView userImageView = new ImageView(userImage);
        userImageView.setX(10);
        userImageView.setY(30);
     
        userImageView.setFitHeight(60);
        userImageView.setFitWidth(60);
        
            
      //  Label lbname = new Label();
        String name2 = fullNameField.getText();
      //  lbname.setText("welcome,"+info.getUserName());
      
      
        lbname.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
//        lbname.setStyle("-fx-text-fill: #968393;");
        lbname.setStyle("-fx-text-fill: #889696;");
        HBox topuser= new HBox();
        topuser.setSpacing(10);
//      topuser.setAlignment(Pos.CENTER);
        topuser.getChildren().addAll(userImageView,lbname);  
        topuser.setTranslateX(30);
        topuser.setTranslateY(20);
        
        Image homePicm = new Image(getClass().getResourceAsStream("/images/home.png"));
        Image searchPicm = new Image(getClass().getResourceAsStream("/images/loupe.png"));
        Image user2Picm = new Image(getClass().getResourceAsStream("/images/account.png"));

        ImageView userImageView2m = new ImageView(user2Picm);
        userImageView2m.setFitHeight(30);
        userImageView2m.setFitWidth(30);

        ImageView serchm = new ImageView(searchPicm);
        serchm.setFitWidth(30);
        serchm.setFitHeight(30);

        ImageView homem = new ImageView(homePicm);
        homem.setFitWidth(30);
        homem.setFitHeight(30);
        
        
         Button home1 = new Button("",homem);
       home1.setBackground(null);
       Button serch1 = new Button("",serchm);
       serch1.setBackground(null);
       Button userbt1 = new Button("",userImageView2m);
       userbt1.setBackground(null);
        
        home1.setOnAction(e-> {
      primaryStage.setScene(scene1);
                });
 
 
 
 serch1.setOnAction(e->  {
         
          primaryStage.setScene(scenechoose);
                   
            } );
 
 
 userbt1.setOnAction(e-> {
        primaryStage.setScene(scenebasket);
              }
 );
 
 
//        
HBox mainBtns = new HBox(70);
mainBtns.setAlignment(Pos.CENTER);
mainBtns.setPadding(new Insets(10));

mainBtns.setStyle("-fx-background-color:#5f7470");
mainBtns.setPrefHeight(50); // set the height of the HBox

mainBtns.setPrefWidth(backgroundImageView.getFitWidth()); // set the width of the HBox
mainBtns.setLayoutX(0);
mainBtns.setLayoutY(backgroundImageView.getFitHeight() - mainBtns.getPrefHeight());

mainBtns.getChildren().addAll(home1,serch1,userbt1);

       Label cho = new Label("Please choose : ");
        cho.setStyle("-fx-font-size: 13px;-fx-text-fill: #889696;");
//      cho.setStyle("-fx-font-size: 15px; -fx-text-fill: #000000;");
            cho.setTranslateY(-60);
            cho.setTranslateX(-20);

//        ComboBox comboBox1 = new ComboBox();
//        comboBox1.setValue("choose your city");
//        comboBox1.getItems().addAll("Makkah", "Jeddah");
//     

 ObservableList<String> obSDistrct  =  FXCollections.observableArrayList();
        ObservableList<String>  obsGroceryname  =  FXCollections.observableArrayList();
        
        ComboBox comboBox2 = new ComboBox();
        comboBox2.setValue("choose your Distrct");
        comboBox2.getItems().addAll("Al-awali", "Al-sharayie");

        ComboBox comboBox3 = new ComboBox();
        comboBox3.setValue("choose Grocery name");
        comboBox3.getItems().addAll("AlRemal");
//        comboBox3.setStyle("-fx-background-color: #d2d4c8;-fx-background-radius: 10px;");
//        comboBox2.setStyle("-fx-background-color: #d2d4c8;-fx-background-radius: 10px;");
        comboBox3.setTranslateY(-50);
        comboBox2.setTranslateY(-60);
           comboBox2.setTranslateX(-10);
            comboBox3.setTranslateX(-10);
        
        // creditordistct c= new creditordistct();
        //  String Groceryn=(String) comboBox2.getValue();
        //  String Distrct= (String)comboBox3.getValue();
   Label errorLabelok = new Label();
    errorLabelok.setStyle("-fx-text-fill: red;");
//errorLabelok.setVisible(false);
        Button okButton = new Button("ok");
        okButton.setStyle("-fx-background-color:#5F7470; -fx-text-fill: #ffffff; -fx-background-radius: 10px; -fx-font-weight: bold;");
        okButton.setPrefWidth(90);
        okButton.setPrefHeight(40); 
        okButton.setTranslateY(-40);
        okButton.setTranslateX(-40);
        okButton.setAlignment(Pos.CENTER);
   okButton.setOnAction((e) -> {
    if (comboBox2.getSelectionModel().isEmpty() || comboBox3.getSelectionModel().isEmpty()) {
//        errorLabelok.setText("Please select a value from all ComboBox.");
        showAlert("Please select a value from all ComboBox.");
    } else {
        String Groceryn = comboBox3.getValue().toString();
        String Distrct = comboBox2.getValue().toString();

        creditordistct c= new creditordistct();
        c.setCreditorname(Groceryn);
        c.setDistctname(Distrct);
         Random random = new Random();
       int randomNum = random.nextInt(); 
       c.setId(randomNum);
       
       
        obSDistrct.add(Distrct);
        obsGroceryname.add(Groceryn);
        
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        session.save(c);
        t.commit();
        session.close();
        primaryStage.setScene(scenestore);
            }
        }
        );
        VBox vb = new VBox();
        vb.setPadding(new Insets(20));
        vb.setSpacing(40);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(cho,comboBox2,comboBox3,okButton);
        
        
        BorderPane pan = new BorderPane();
        pan.setTop(topuser);
        pan.setCenter(vb);
        pan.setBottom(mainBtns);
       
StackPane root4 = new StackPane();
root4.getChildren().addAll(backgroundImageView, pan);



        // Create scene and show stage
                 
           /*-----------------Scene 5  -----------------*/
           
         Image corianderPic= new Image(getClass().getResourceAsStream("/images/coriander2.png"),70,60,false,false);
         Image sugarPic= new Image(getClass().getResourceAsStream("/images/sugar .png"),70,60,false,false);
         ImageView coranderPicMgV = new ImageView(corianderPic);//Setting the image view
         ImageView sugarPicPicMgV = new ImageView(sugarPic);//Setting the image view
         Image waterPic= new Image(getClass().getResourceAsStream("/images/berain.png"),70,60,false,false);
         ImageView waterPicMgV = new ImageView(waterPic);//Setting the image view
//         // coriander imag     
//        Image corianderimag = new Image(getClass().getResourceAsStream("/images/coriander2.png"));
//        
        AnchorPane Droot=new AnchorPane();
        VBox Dvb=new VBox();
        VBox Dvb2=new VBox();
        HBox DdebtInfo=new HBox();
        HBox DgoodsNo1=new HBox();
        HBox DgoodsNo2=new HBox();
        HBox DgoodsNo3=new HBox();
        VBox DgoodsInfo=new VBox();
        VBox DgoodsBtn=new VBox();
        VBox DgoodsInfo2=new VBox();
        VBox DgoodsBtn2=new VBox();
        VBox DgoodsInfo3=new VBox();
        VBox DgoodsBtn3=new VBox();

        Label DgoodsPic1=new Label();
        Label Dname1=new Label("berain");
        Label Dsize1=new Label("200 ml ");
        Label Dprice1=new Label("5 SAR");
        
        
        Label DgoodsPic2=new Label();
        Label Dname2=new Label("coriander");
        Label Dsize2=new Label("3 kg");
        Label Dprice2=new Label("12 SAR");
        
        Label DgoodsPic3=new Label();
        Label Dname3=new Label("sugar");
        Label Dsize3=new Label("classic family size");
        Label Dprice3=new Label("5 SAR");
        
        Label DgoodsPic4=new Label();
        Label DgoodsPic5=new Label();
        Label DgoodsPic6=new Label();
        
        
        
        Label Dgreeting=new Label("Hey");
        Label Dgreeting2=new Label("we wish you have a good time");
        Label DitemsNo=new Label("No");
        Label DTotalPrice=new Label("Total:0 SAR");
        //Button addBtn1=new Button("+");
        Button DNoBtn1=new Button("0");
        //Button removeBtn1=new Button(" -");
        //Button addBtn2=new Button("+");
        Button DNoBtn2=new Button("0");
        //Button removeBtn2=new Button(" -");
        //Button addBtn3=new Button("+");
        Button DNoBtn3=new Button("0");
        //Button removeBtn3=new Button(" -");
        Button DTransferBtn=new Button("    debt Transfer    ");
        Button Dindicator=new Button("<");
        Button Dindicator2=new Button(">");
        DTransferBtn.setOnAction(e->{
        
       primaryStage.setScene(sceneDebtscheduler); 
       
        });
        
        
        
        
        
        //desgin gui
        Dgreeting.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        Dgreeting2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
        DTotalPrice.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 13));
        DitemsNo.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
        Dvb.setStyle("-fx-background-color:white;-fx-background-radius:20;");
        DgoodsPic1.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:30;");
        
      
      DgoodsPic2.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:30;");
      DgoodsPic2.setPrefSize(150, 60);
      DgoodsPic2.setTranslateX(-30);
      DgoodsPic2.setTranslateY(10);
      DgoodsPic1.setTranslateX(30);
      DgoodsPic1.setTranslateY(-71);
      DgoodsPic1.setGraphic(coranderPicMgV);
      DgoodsPic4.setGraphic(sugarPicPicMgV);
      DgoodsPic3.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:30;");
      DgoodsPic3.setPrefSize(150, 60);
      DgoodsPic4.setTranslateX(-90);
      DgoodsPic4.setTranslateY(-5);
      DgoodsPic3.setTranslateX(-10);
      DgoodsPic6.setGraphic(waterPicMgV);
      DgoodsPic5.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:30;");
      DgoodsPic6.setTranslateX(-110);
      DgoodsPic6.setTranslateY(10);
      DgoodsPic5.setTranslateX(-70);
      DgoodsPic5.setTranslateY(10);
      DgoodsPic5.setPrefSize(150, 60);
        
        Dvb.setLayoutX(10);
        Dvb.setLayoutY(60);
        DgoodsInfo.setTranslateX(-20);
        DgoodsInfo2.setTranslateX(-20);
        DgoodsBtn.setTranslateX(20);
        DgoodsBtn2.setTranslateX(-20);
        DgoodsBtn3.setTranslateX(15);
        DgoodsInfo.setSpacing(10);
        DgoodsInfo2.setSpacing(10);
        DgoodsInfo3.setSpacing(10);
        DgoodsBtn.setSpacing(5);
        DgoodsBtn2.setSpacing(5);
        DgoodsBtn3.setSpacing(5);
        Droot.setStyle("-fx-background-color:#e0e2db");
        DTransferBtn.setStyle("-fx-background-color: purple;-fx-background-radius:20;-fx-text-fill: white;");
        //addBtn1.setStyle("-fx-background-color:#d2bece;-fx-background-radius:20;");
        //addBtn2.setStyle("-fx-background-color:#d2bece;-fx-background-radius:20;");
        //addBtn3.setStyle("-fx-background-color:#d2bece;-fx-background-radius:20;");
        DNoBtn1.setStyle("-fx-background-color:#ad9baa;-fx-background-radius:20;");
        DNoBtn2.setStyle("-fx-background-color:#ad9baa;-fx-background-radius:20;");
        DNoBtn3.setStyle("-fx-background-color:#ad9baa;-fx-background-radius:20;");
        //removeBtn1.setStyle("-fx-background-color:#d2bece;-fx-background-radius:20;");
        //removeBtn2.setStyle("-fx-background-color:#d2bece;-fx-background-radius:20;");
        //removeBtn3.setStyle("-fx-background-color:#d2bece;-fx-background-radius:20;");
        Dgreeting.setStyle("-fx-text-fill: #968393;");
        Dgreeting2.setStyle("-fx-text-fill: #889696;");
       
        
        DgoodsNo3.setPadding(new Insets(1,30,1,-20));
        DgoodsNo1.setSpacing(-40);
        DgoodsNo2.setSpacing(10);
        DgoodsNo3.setSpacing(-20);
        DdebtInfo.setSpacing(40);
        DdebtInfo.setPadding(new Insets(10,10,10,10));
        Dvb2.setSpacing(10);
        Dvb2.setPadding(new Insets(30,30,90,-10));
        DTransferBtn.setTranslateX(90);
        DTransferBtn.setTranslateY(-80);
        Dgreeting.setTranslateX(50);
        Dgreeting.setTranslateY(20);
        Dgreeting2.setTranslateX(60);
        Dgreeting2.setTranslateY(40);
        DdebtInfo.setTranslateX(50);
        Dindicator2.setTranslateX(250);
        Dindicator2.setTranslateY(10);
        Dindicator.setTranslateY(10);
        Dindicator.setTranslateX(10);
        Dindicator.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
        Dindicator2.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
       // indicator.setOnAction(this);
        
        Droot.getChildren().addAll(Dindicator,Dindicator2,Dgreeting,Dgreeting2,Dvb);
        Dvb.getChildren().addAll(Dvb2,DTransferBtn);
        DgoodsInfo.getChildren().addAll(Dname1,Dsize1,Dprice1);
        DgoodsNo1.getChildren().addAll(DgoodsPic1,DgoodsPic5,DgoodsPic6,DgoodsInfo,DgoodsBtn);
        DgoodsInfo2.getChildren().addAll(Dname2,Dsize2,Dprice2);
        
        DgoodsNo2.getChildren().addAll(DgoodsPic2,DgoodsInfo2,DgoodsBtn2);
        
        DgoodsInfo3.getChildren().addAll(Dname3,Dsize3,Dprice3);
        DgoodsNo3.getChildren().addAll(DgoodsPic3,DgoodsPic4,DgoodsInfo3,DgoodsBtn3);
        Dvb2.getChildren().addAll(DgoodsNo2,DgoodsNo1,DgoodsNo3,DdebtInfo);
        DgoodsBtn.getChildren().add(DNoBtn1/*removeBtn1*/);
        DgoodsBtn2.getChildren().add(DNoBtn2/*addBtn1*/);
        DgoodsBtn3.getChildren().add(DNoBtn3/*addBtn1*/);
        DdebtInfo.getChildren().addAll(DitemsNo,DTotalPrice);
        
        
//        addBtn1.setOnMouseClicked(e->{
//            int No1=Integer.parseInt(NoBtn1.getText());
//            int No11=No1+1;
//           
//            NoBtn1.setText((String.valueOf(No11)));
//        });
//        addBtn2.setOnMouseClicked(e->{
//            int No2=Integer.parseInt(NoBtn2.getText());
//            int No22=No2+1;
//           
//            NoBtn2.setText((String.valueOf(No22)));
//        });
//        addBtn3.setOnMouseClicked(e->{
//            int No3=Integer.parseInt(NoBtn3.getText());
//            int No33=No3+1;
//            NoBtn3.setText((String.valueOf(No33)));
//        });
//        removeBtn1.setOnMouseClicked(e->{
//            int rNo=Integer.parseInt(NoBtn1.getText());
//            int rNo11=rNo-1;
//           
//            NoBtn1.setText((String.valueOf(rNo11)));
//        });
//        removeBtn2.setOnMouseClicked(e->{
//            int rNo2=Integer.parseInt(NoBtn2.getText());
//            int rNo22=rNo2-1;
//           
//            NoBtn2.setText((String.valueOf(rNo22)));
//        });
//        removeBtn3.setOnMouseClicked(e->{
//            int rNo3=Integer.parseInt(NoBtn3.getText());
//            int rNo33=rNo3-1;
//            NoBtn3.setText((String.valueOf(rNo33)));
//        });
        
       
       
      
      


        //debt scene
        
        
        // store scene
         Image homePic2= new Image(getClass().getResourceAsStream("/images/home.png"));
        Image searchPic2= new Image(getClass().getResourceAsStream("/images/loupe.png"));
        Image user2Pic2= new Image(getClass().getResourceAsStream("/images/account.png"));
         ImageView user2PicMgV = new ImageView(user2Pic2);//Setting the image view
        ImageView homePicMgV = new ImageView(homePic2);//Setting the image view
        ImageView searchPicMgV = new ImageView(searchPic2);//Setting the image view
        Image supMPic= new Image(getClass().getResourceAsStream("/images/supermarkets.png"));
        ImageView supMPicMgV = new ImageView(supMPic);
        //store image 
        
        Image corianderPic2= new Image(getClass().getResourceAsStream("/images/coriander2.png"),70,60,false,false);
         Image sugarPic2= new Image(getClass().getResourceAsStream("/images/sugar .png"),70,60,false,false);
         ImageView coranderPicMgV2 = new ImageView(corianderPic);//Setting the image view
         ImageView sugarPicPicMgV2 = new ImageView(sugarPic);//Setting the image view
         Image waterPic2= new Image(getClass().getResourceAsStream("/images/berain.png"),40,40,false,false);
         ImageView waterPicMgV2 = new ImageView(waterPic);//Setting the image view
         Image sunTopPic2= new Image(getClass().getResourceAsStream("/images/suntop.png"),40,40,false,false);
         ImageView sunTopPicMgV2 = new ImageView(sunTopPic2);//Setting the image view
        
        
        //
          AnchorPane root5=new AnchorPane();
          AnchorPane base=new AnchorPane();
          AnchorPane mainButtons=new AnchorPane();
          VBox vb2=new VBox();
          HBox StoreInfo=new HBox();
          HBox storeList=new HBox();
          VBox goods1Vb=new VBox();
          VBox goods11Vb=new VBox();
          VBox goods111Vb=new VBox();
          HBox goodsNo1=new HBox();
          VBox goods2Vb=new VBox();
          VBox goods22Vb=new VBox();
          VBox goods222Vb=new VBox();
          HBox goodsNo2=new HBox();
          VBox goods3Vb=new VBox();
          VBox goods33Vb=new VBox();
          VBox goods333Vb=new VBox();
          HBox goodsNo3=new HBox();
          VBox goods4Vb=new VBox();
          VBox goods44Vb=new VBox();
          VBox goods444Vb=new VBox();
          HBox goodsNo4=new HBox();
          Button indicator=new Button("<");
          Button indicator2=new Button(">");
          //Button listItem=new Button("vegetables&fruits");
          //Button listItem2=new Button("daily necessities");
          //Button listItem3=new Button("dairy");
        Label storePic1=new Label();
        Label storePic2=new Label();
        Label storePic3=new Label();
        Label storePic4=new Label();
       // ImageView storePicImgVw=new ImageView(/*storePic*/);
       Label goodsName1=new Label("coriander ");
       Label goodsInfo1=new Label("12 SAR");
       Label goodsName2=new Label("berain");
       Label goodsInfo2=new Label("5 SAR");
        Label goodsName3=new Label("sugar");
       Label goodsInfo3=new Label("5 SAR");
       Label goodsName4=new Label("sun Top");
       Label goodsInfo4=new Label("2.00SAR");
       Label storeName=new Label("Al Remal");
       Label storeRate=new Label("  4.9  ");
       Button addGoods=new Button("+");
       Button removeGoods=new Button("- ");
       Button ADD=new Button("ADD");
       Button No=new Button("0");
       Button addGoods2=new Button("+");
       Button removeGoods2=new Button("- ");
       Button ADD2=new Button("ADD");
       Button No2=new Button("0");
       Button addGoods3=new Button("+");
       Button removeGoods3=new Button("- ");
       Button ADD3=new Button("ADD");
       Button No3=new Button("0");
       Button addGoods4=new Button("+");
       Button removeGoods4=new Button("- ");
       Button ADD4=new Button("ADD");
       Button No4=new Button("0");
       Button home2=new Button();
       Button search=new Button();
       Button user=new Button();
       //set image
       storePic1.setGraphic(coranderPicMgV2);
       storePic2.setGraphic(waterPicMgV2);
       storePic3.setGraphic(sugarPicPicMgV2);
       storePic4.setGraphic(sunTopPicMgV2);
       //
      
       indicator.setOnAction(e-> 
       primaryStage.setScene(scenechoose));
       
       indicator2.setOnAction(e-> 
       primaryStage.setScene(scenetotal));

       home2.setGraphic(homePicMgV);
       search.setGraphic(searchPicMgV);
       user.setGraphic(user2PicMgV);
       homePicMgV.setFitWidth(20);
       homePicMgV.setFitHeight(20);
       searchPicMgV.setFitWidth(20);
       searchPicMgV.setFitHeight(20);
       user2PicMgV.setFitWidth(20);
       user2PicMgV.setFitHeight(20);
       supMPicMgV.setFitWidth(190);
       supMPicMgV.setFitHeight(70);
       supMPicMgV.setTranslateX(50);
       supMPicMgV.setTranslateY(10);
       
       storeName.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
       storeRate.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14));
       root5.setStyle("-fx-background-color:#e0e2db");
       base.setStyle("-fx-background-color:9e6f21");
       vb2.setStyle("-fx-background-color:white;-fx-background-radius:20;");
       storeRate.setStyle("-fx-background-color: #9e6f21;-fx-background-radius:20;-fx-text-fill: white;");
       storeName.setStyle("-fx-text-fill: black;");
       mainButtons.setStyle("-fx-background-color:#5f7470;-fx-background-radius:20;");
       indicator.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
       indicator2.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
    
       addGoods.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       addGoods2.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       addGoods3.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       addGoods4.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       removeGoods.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       removeGoods2.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       removeGoods3.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       removeGoods4.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       ADD.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       ADD2.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       ADD3.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       ADD4.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       No.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       No2.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       No3.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       No4.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:20;");
       home2.setStyle("-fx-background-color:#5f7470;-fx-background-radius:30;");
       search.setStyle("-fx-background-color:#5f7470;-fx-background-radius:30;");
       user.setStyle("-fx-background-color:#5f7470;-fx-background-radius:30;");
      mainButtons.setPadding(new Insets(10,10,10,90));
       vb.setPadding(new Insets(20,10,0,-60));
       VBox.setMargin(StoreInfo, new Insets(10,10,0,0));
       VBox.setMargin(goodsNo1, new Insets(0,30,-10,0));
       VBox.setMargin(goodsNo2, new Insets(0,30,-10,0));
       VBox.setMargin(goodsNo3, new Insets(0,30,-10,0));
       VBox.setMargin(goodsNo4, new Insets(0,10,25,0));
        VBox.setMargin(mainButtons, new Insets(-3,-5,0,-5));
       StoreInfo.setLayoutX(10);
       StoreInfo.setLayoutY(100);
       StoreInfo.setSpacing(100);
       storeList.setLayoutX(10);
       storeList.setLayoutY(150);
       storeList.setSpacing(4);
       indicator.setTranslateY(10);
       indicator.setTranslateX(10);
       indicator2.setTranslateY(10);
       indicator2.setTranslateX(250);
       vb2.setSpacing(2);

       storeList.setPadding(new Insets(20,10,10,10));
       
       vb2.setLayoutY(90);
       goodsNo1.setSpacing(10);
       goodsNo2.setSpacing(20);
       goodsNo3.setSpacing(23);
       goodsNo4.setSpacing(40);
       goodsNo1.setPadding(new Insets(10,10,10,10));
       goodsNo2.setPadding(new Insets(10,10,10,10));
       goodsNo3.setPadding(new Insets(10,10,10,10));
       goodsNo4.setPadding(new Insets(10,10,0,10));
       home2.setTranslateX(40);
       home2.setTranslateY(10);
       search.setTranslateX(120);
       search.setTranslateY(10);
       user.setTranslateX(220);
       user.setTranslateY(10);
       StoreInfo.setTranslateX(30);
       StoreInfo.setTranslateY(10);
       goods2Vb.setSpacing(4);
       goods22Vb.setSpacing(4);
       goods222Vb.setSpacing(4);
       goods1Vb.setSpacing(4);
       goods11Vb.setSpacing(4);
       goods111Vb.setSpacing(4);
       goods3Vb.setSpacing(4);
       goods33Vb.setSpacing(4);
       goods333Vb.setSpacing(4);
       goods4Vb.setSpacing(4);
       goods44Vb.setSpacing(4);
       goods444Vb.setSpacing(4);
       goods111Vb.setTranslateX(15);
       goods222Vb.setTranslateX(1);
       goods333Vb.setTranslateX(1);
       goods444Vb.setTranslateX(-20);
       storePic4.setTranslateX(10);
       goods4Vb.setTranslateX(2);
       goods3Vb.setTranslateX(-10);
       goods2Vb.setTranslateX(-10);
       goods22Vb.setTranslateX(10);
       goods11Vb.setTranslateX(15);
       goods33Vb.setTranslateX(10);
       goods44Vb.setTranslateX(10);
       VBox.setMargin(base, new Insets(-20));
       //add methods
       StoreInfo.getChildren().addAll(storeName,storeRate);
       root5.getChildren().addAll(base);
       base.getChildren().addAll(supMPicMgV,vb2,indicator,indicator2); 
      // storeList.getChildren().addAll(listItem,listItem2,listItem3);
       vb2.getChildren().addAll(StoreInfo,/*storePicImgVw*/storeList,goodsNo1,goodsNo2,goodsNo3,goodsNo4,mainButtons); 
       goodsNo1.getChildren().addAll(storePic1,goods1Vb,goods11Vb,goods111Vb);
       goods1Vb.getChildren().addAll(goodsName1,goodsInfo1);
       goods11Vb.getChildren().addAll(addGoods,removeGoods);
       goods111Vb.getChildren().addAll(ADD,No);
       goodsNo2.getChildren().addAll(storePic2,goods2Vb,goods22Vb,goods222Vb);
       goods2Vb.getChildren().addAll(goodsName2,goodsInfo2);
       goods22Vb.getChildren().addAll(addGoods2,removeGoods2);
       goods222Vb.getChildren().addAll(ADD2,No2);
       goodsNo3.getChildren().addAll(storePic3,goods3Vb,goods33Vb,goods333Vb);
       goods3Vb.getChildren().addAll(goodsName3,goodsInfo3);
       goods33Vb.getChildren().addAll(addGoods3,removeGoods3);
       goods333Vb.getChildren().addAll(ADD3,No3);
       goods4Vb.getChildren().addAll(goodsName4,goodsInfo4);
       goods44Vb.getChildren().addAll(addGoods4,removeGoods4);
       goods444Vb.getChildren().addAll(ADD4,No4);
       goodsNo4.getChildren().addAll(storePic4,goods4Vb,goods44Vb,goods444Vb);
       mainButtons.getChildren().addAll(home2,search,user);
       //Event
       
        home2.setOnAction(e-> {
            
               primaryStage.setScene(scene1);  
            } );
 
 
 
 search.setOnAction(e-> {
          primaryStage.setScene(scenechoose);  
            } );
 
 
 
 user.setOnAction( e-> {
        primaryStage.setScene(scenebasket);
              });
 
       addGoods.setOnMouseClicked(e->{
            int No1=Integer.parseInt(No.getText());
            int No11=No1+1;
            No.setText((String.valueOf(No11)));
            
        });
        addGoods2.setOnMouseClicked(e->{
            int rNo2=Integer.parseInt(No2.getText());
            int No22=rNo2+1;
           
            No2.setText((String.valueOf(No22)));
        });
        
        addGoods3.setOnMouseClicked(e->{
            int rNo3=Integer.parseInt(No3.getText());
            int No33=rNo3+1;
            No3.setText((String.valueOf(No33)));
        });
         addGoods4.setOnMouseClicked(e->{
            int rNo4=Integer.parseInt(No4.getText());
            int No44=(rNo4)+1;
            No4.setText((String.valueOf(No44)));
        });
        removeGoods.setOnMouseClicked(e->{
            int rNo=Integer.parseInt(No.getText());
            int rNo11=rNo;
           
            No.setText((String.valueOf(rNo11-1)));
        });
        removeGoods2.setOnMouseClicked(e->{
            int rNo2=Integer.parseInt(No2.getText());
            int rNo22=(rNo2);
           
            No2.setText((String.valueOf(rNo22-1)));
        });
        removeGoods3.setOnMouseClicked(e->{
            int rNo3=Integer.parseInt(No3.getText());
            int rNo33=(rNo3);
            No3.setText((String.valueOf(rNo33-1)));
        });
        removeGoods4.setOnMouseClicked(e->{
            int rNo4=Integer.parseInt(No4.getText());
            int rNo44=rNo4;
            No4.setText((String.valueOf(rNo44-1)));
        });
        
//        listItem.setOnMouseClicked(e->{
//            listItem2.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-radius:20;");
//            listItem3.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-radius:20;");
//            listItem.setStyle("-fx-background-color:#e0e2db;-fx-background-radius:20;");
//        });
//        listItem2.setOnMouseClicked(e->{
//            listItem.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-radius:20;");
//            listItem3.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-radius:20;");
//            listItem2.setStyle("-fx-background-color:#e0e2db;-fx-background-radius:20;");
//        });
//        listItem3.setOnMouseClicked(e->{
//            listItem.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-radius:20;");
//            listItem2.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-radius:20;");
//            listItem3.setStyle("-fx-background-color:#e0e2db;-fx-background-radius:20;");
//        });
//       

 List<String >ProductNamesList = new ArrayList<String>();
 List<String >ImagePathList = new ArrayList<String>();
 List<Integer >obProductPriceList = new ArrayList<Integer>();
 List<Integer >obProductNoList= new ArrayList<Integer>();
 

// Now add observability by wrapping it with ObservableList
    ObservableList<String> obProductNames = FXCollections.observableList(ProductNamesList);
    ObservableList<String> obImagePath = FXCollections.observableList(ImagePathList);
    ObservableList<Integer> obProductPrice = FXCollections.observableList(obProductPriceList);
    ObservableList<Integer> obProductNo = FXCollections.observableList(obProductNoList);
       
    ADD.setOnMouseClicked(e->{
       int itemNo= Integer.parseInt(No.getText());
        for(int i=0;i<=itemNo;i++){
    DNoBtn2.setText(No.getText()); 
      
        }

Product product=new Product();
Product product2=new Product();
Integer prNo=Integer.valueOf(No.getText());
product.setNo_of_Product(Integer.parseInt(No.getText()));
    Session session = HibernateUtil.getSessionFactory().openSession();
   Transaction tx = session.beginTransaction();
String pId="coriander";
product2 = (Product)session.get(Product.class, pId);
product2.setNo_of_Product(prNo);


session.update(product2);
obProductNoList.add(0,product2.getNo_of_Product());
obProductPriceList.add(0,product2.getProductPrice());
product2.setTotalAmount(product2.getNo_of_Product()*product2.getProductPrice());
tx.commit();


session.close();
System.out.println("inserted ProductNos: "+obProductNo.get(0));
 System.out.println("inserted ProductNos: "+obProductPrice.get(0));      
        
    });
    
    ADD2.setOnMouseClicked(e->{
       int itemNo2= Integer.parseInt(No2.getText());
        for(int i=0;i<=itemNo2;i++){  
    DNoBtn1.setText(No2.getText());}

Product product=new Product();
Product product3;
Integer prNo=Integer.valueOf(No2.getText());
    Session session = HibernateUtil.getSessionFactory().openSession();
   Transaction tx = session.beginTransaction();

product3 =(Product)session.get(Product.class,"berain water");
product3.setNo_of_Product(prNo);

session.update(product3);
obProductNoList.add(1,product3.getNo_of_Product());
obProductPriceList.add(1,product3.getProductPrice());
product3.setTotalAmount(product3.getNo_of_Product()*product3.getProductPrice());
tx.commit();
session.close();
System.out.println("inserted ProductNos: "+obProductNo.get(1));
System.out.println("inserted ProductNos: "+obProductPrice.get(1));
    });
    ADD3.setOnMouseClicked(e->{
       int itemNo3= Integer.parseInt(No3.getText());
        for(int i=0;i<=itemNo3;i++){
    DNoBtn3.setText(No3.getText());}
        Product product=new Product();
Product product2;
Integer prNo=Integer.valueOf(No3.getText());
    Session session = HibernateUtil.getSessionFactory().openSession();
   Transaction tx = session.beginTransaction();
String pId3="sugar";
product2 = (Product)session.get(Product.class, pId3);
product2.setNo_of_Product(prNo);
session.update(product2);
obProductNoList.add(2,product2.getNo_of_Product());
obProductPriceList.add(2,product2.getProductPrice());
product2.setTotalAmount(product2.getNo_of_Product()*product2.getProductPrice());
tx.commit();
session.close();

System.out.println("inserted ProductNos: "+obProductNo.get(2));
System.out.println("inserted ProductNos: "+obProductPrice.get(2));
    });
    ADD4.setOnMouseClicked(e->{
       int itemNo4= Integer.parseInt(No4.getText());
        for(int i=0;i<=itemNo4;i++){}
        //DNoBtn4.setText(No4.getText());}
    });
   
    
    // statements to find the total Nos of product in debt GUI
               Product product2=new Product();
               Product product3=new Product();
               Product product4=new Product();
               Session session = HibernateUtil.getSessionFactory().openSession();
               Transaction tx = session.beginTransaction();

product2=(Product)session.get(Product.class,"coriander");
product3=(Product)session.get(Product.class,"berain water");
product4=(Product)session.get(Product.class,"sugar");


int sum=product2.getNo_of_Product()+product3.getNo_of_Product()+product4.getNo_of_Product();
int Totalsum=product2.getTotalAmount()+product3.getTotalAmount()+product4.getTotalAmount();
String Ssum=Integer.toString(sum);

String totalSsum=Integer.toString(Totalsum);

DitemsNo.setText(Ssum);

DTotalPrice.setText("total: "+totalSsum+" SAR");

session.close();
  
  Dindicator.setOnMouseClicked(e->{
            primaryStage.setScene(scenestore);
                });
       
        
    Dindicator2.setOnMouseClicked(e->{
            primaryStage.setScene(sceneDebtscheduler);
  
                });
    
    
      /*-----------------Scene 6  -----------------*/

//        Image backgroundImage6 = new Image(getClass().getResourceAsStream("/images/imagebackground.png"));
//        ImageView backgroundImageView6 = new ImageView(backgroundImage6);
//        backgroundImageView6.setFitWidth(300);
//        backgroundImageView6.setFitHeight(500);
//        backgroundImageView6.setPreserveRatio(false);
//
//        // Load the user image
//        Image userImage6 = new Image(getClass().getResourceAsStream("/images/userpurple2.png"));
//        ImageView userImageView6 = new ImageView(userImage6);
//        userImageView6.setX(10);
//        userImageView6.setY(30);
//        userImageView6.setFitHeight(60);
//        userImageView6.setFitWidth(60);
//        
//        
//        Ellipse ellipse6 = new Ellipse();
//        ellipse6.setCenterX(140);
//        ellipse6.setCenterY(150);
//        ellipse6.setRadiusX(80);
//        ellipse6.setRadiusY(20);
//        
//      
//        // Create a linear gradient for the ellipse fill
//        LinearGradient fillGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
//                new Stop(0, Color.web("#4ABBC9")),
//                new Stop(1, Color.web("#D561E8")));
//
//        // Set the fill color of the ellipse to the linear gradient
//        ellipse6.setFill(fillGradient);
//        
//        // Create a label with the user name
//        Label userNameLabel6 = new Label("");
//        userNameLabel6.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
//        userNameLabel6.setTextFill(Color.BLACK);
//        userNameLabel6.setAlignment(Pos.CENTER_LEFT);
//        userNameLabel6.setLayoutX(100);
//        userNameLabel6.setLayoutY(60);
//        
//        // Create a DatePicker for selecting the debt payment date
//      Label debtLabel = new Label("Please choose when to pay off the debt");
//        DatePicker datePicker = new DatePicker();
//        
//        // Create a label to display the selected debt payment date
//        Label debtPaymentLabel = new Label("Date for the debt payment will be ");
//        debtPaymentLabel.setVisible(false);
//        
//        // Add an event listener to the DatePicker to update the debtPaymentLabel
//        datePicker.setOnAction(e -> {
//            debtPaymentLabel.setText("Date for the debt payment will be (" + datePicker.getValue() + ")");
//            debtPaymentLabel.setVisible(true);
//        });
////       ObservableList<String> Paymentdate =  FXCollections.observableArrayList();
////
//////      add Paymentdate to the ObservableList
////       String Payment = datePicker.getValue().toString();
////          Paymentdate.add(Payment);
//
//             // Add a hyperlink to display the terms and conditions
//        Hyperlink termsLink = new Hyperlink("Terms and Conditions");
//        termsLink.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
//
//        // Create a TextArea to display the terms and conditions
//        TextArea termsArea = new TextArea("You must deliver the amount on time and the time is related to the DatePicker chosen by the person");
//        termsArea.setWrapText(true);
//        termsArea.setEditable(false);
//        termsArea.setVisible(false);
//        termsArea.setPrefSize(200, 40);
//        // Add an event listener to the hyperlink to show the terms and conditions
//        termsLink.setOnAction(e -> {
//            termsArea.setVisible(!termsArea.isVisible());
//        });
//        
//        
//        CheckBox termsCheckbox = new CheckBox("I agree to the request and registration of the debt");
//termsCheckbox.setSelected(false);
//termsCheckbox.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
//// If he agrees, he will be recorded as a debt and then transferred to the registered debt page
//termsCheckbox.setOnKeyPressed(e -> {
//  if (e.getCode().equals(KeyCode.ENTER))
//    if (termsCheckbox.isSelected()) {
//        primaryStage.setScene(sceneDebtscheduler);
//    }
//});
//
//        // Add the hyperlink and TextArea to the VBox
////        vbox.getChildren().addAll(termsLink, termsArea);
//
//        // Create a VBox to hold the checkbox, DatePicker, and debtPaymentLabel
//VBox vbox6 = new VBox();
//vbox6.setAlignment(Pos.CENTER_LEFT);
//vbox6.setSpacing(20);
//vbox6.setPadding(new Insets(20));
////vbox.setLayoutX(20);
////vbox.setLayoutY(200);
//
//// Create an HBox for the main buttons
//HBox mainBtns6 = new HBox(90);
//mainBtns6.setPadding(new Insets(10));
//
//mainBtns6.setStyle("-fx-background-color:#5f7470");
//mainBtns6.setPrefHeight(50); // set the height of the HBox
//
//mainBtns6.setPrefWidth(backgroundImageView6.getFitWidth()); // set the width of the HBox
//mainBtns6.setLayoutX(0);
//mainBtns6.setLayoutY(backgroundImageView6.getFitHeight() - mainBtns6.getPrefHeight());
//
//
//Image homePic6 = new Image(getClass().getResourceAsStream("/images/home.png"));
//Image searchPic6 = new Image(getClass().getResourceAsStream("/images/loupe.png"));
//Image user2Pic6 = new Image(getClass().getResourceAsStream("/images/account.png"));
//
//ImageView userImageView66 = new ImageView(user2Pic6);
//userImageView66.setFitHeight(30);
//userImageView66.setFitWidth(30);
//
//ImageView serch6 = new ImageView(searchPic6);
//serch6.setFitWidth(30);
//serch6.setFitHeight(30);
//
//ImageView home6 = new ImageView(homePic6);
//home6.setFitWidth(30);
//home6.setFitHeight(30);
//
//
//mainBtns6.getChildren().addAll(home6,serch6,userImageView66);
//
//     Button indicator6=new Button("<");
//        Button indicator66=new Button(">");
//        indicator6.setTranslateY(10);
//        indicator66.setTranslateX(10);
//        
//        indicator6.setTranslateY(10);
//        indicator66.setTranslateX(10);
//        
//        indicator6.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
//        indicator66.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
//        
//       indicator6.setOnAction(e->{
////           primaryStage.setScene(scenetotal);
//           primaryStage.setScene(scenechoose);
//       });
//       indicator66.setOnAction(e->{
////           primaryStage.setScene(scenetotal);
//           primaryStage.setScene(scenebasket);
//       });; 
//        HBox swich6 =new HBox(230);
//        swich6.getChildren().addAll(indicator6,indicator66);
//vbox6.getChildren().addAll(swich6,userImageView6,userNameLabel6,debtLabel,datePicker, debtPaymentLabel,termsLink, termsArea,termsCheckbox,mainBtns6);
////Group root = new Group(backgroundImageView, userImageView, ellipse, userNameLabel, vbox, text,mainBtns);
//  StackPane root66 = new StackPane();
//root66.getChildren().addAll(backgroundImageView6, vbox6);
//      

        Image backgroundImage6 = new Image(getClass().getResourceAsStream("/images/imagebackground.png"));
        ImageView backgroundImageView66 = new ImageView(backgroundImage6);
        backgroundImageView66.setFitWidth(300);
backgroundImageView66.setFitHeight(500);
backgroundImageView66.setPreserveRatio(false);

        // Load the user image
        Image userImage22 = new Image(getClass().getResourceAsStream("/images/userpurple2.png"));
        ImageView userImageView56 = new ImageView(userImage22);
        userImageView56.setX(10);
        userImageView56.setY(30);
        userImageView56.setFitHeight(60);
        userImageView56.setFitWidth(60);
        
        
        // Create a label with the user name
   userNameLabel2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));

//        lbname.setStyle("-fx-text-fill: #968393;");
        userNameLabel2.setStyle("-fx-text-fill: #889696;");
        userNameLabel3.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        userNameLabel3.setStyle("-fx-text-fill: #889696;");
//        userNameLabel2.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
//        
//        userNameLabel2.setTextFill(Color.web("#5f7470"));
//        
//        userNameLabel2.setAlignment(Pos.CENTER_LEFT);
//        
        userNameLabel2.setLayoutX(75);
        
        userNameLabel2.setLayoutY(30);
        
         userNameLabel3.setLayoutX(75);
        
        userNameLabel3.setLayoutY(30);
        
//   userNameLabel2.setTranslateX(30);
//        topuser.setTranslateY(20);
        
      
        
        // Create a DatePicker for selecting the debt payment date
        DatePicker datePicker = new DatePicker();
        
        // Create a label to display the selected debt payment date
        Label debtPaymentLabel = new Label("Date for the debt payment will be ");
        debtPaymentLabel.setVisible(false);

        //Create a DatePicker for selecting the debt payment date
      Label debtLabel = new Label("Please choose when to pay off the debt");
                

        // Add an event listener to the DatePicker to update the debtPaymentLabel
        datePicker.setOnAction(e -> {
            debtPaymentLabel.setText("Date for the debt payment will be\n(" + datePicker.getValue() + ")");
            debtPaymentLabel.setVisible(true);
        });
        
             // Add a hyperlink to display the terms and conditions
        Hyperlink termsLink = new Hyperlink("Terms and Conditions");
        termsLink.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));

        // Create a TextArea to display the terms and conditions
        TextArea termsArea = new TextArea("You must deliver the amount on time and the time is related to the DatePicker chosen by the person");
        termsArea.setWrapText(true);
        termsArea.setEditable(false);
        termsArea.setVisible(false);
        termsArea.setPrefSize(200, 80);
        // Add an event listener to the hyperlink to show the terms and conditions
        termsLink.setOnAction(e -> {
            termsArea.setVisible(!termsArea.isVisible());
        });
     
        

        // Add the hyperlink and TextArea to the VBox
//        vbox.getChildren().addAll(termsLink, termsArea);
        // Create a VBox to hold the checkbox, DatePicker, and debtPaymentLabel
        VBox vbox = new VBox();
vbox.setAlignment(Pos.CENTER_LEFT);
vbox.setSpacing(10);
vbox.setLayoutX(20);
vbox.setLayoutY(150);
vbox.getChildren().addAll(debtLabel,datePicker, debtPaymentLabel,termsLink, termsArea);

CheckBox termsCheckbox = new CheckBox("I agree to the request and registration of the debt");
termsCheckbox.setSelected(false);
termsCheckbox.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
termsCheckbox.setLayoutX(20);
termsCheckbox.setLayoutY(350);

termsCheckbox.setOnKeyPressed(e -> {
  if (e.getCode().equals(KeyCode.ENTER))
    if (!termsCheckbox.isSelected()) {
        showAlert("You do not agree to the terms,so you cannot be taken to the next page");
    }
});



//termsCheckbox.setOnAction(e -> {
//    if (termsCheckbox.isSelected()) {
//        primaryStage.setScene(sceneDebtscheduler);
//    }
//    else{
//        showAlert("You do not agree to the terms,so you cannot be taken to the next page");
//    }
//});


// Create an HBox for the main buttons
HBox mainBtns55 = new HBox(70);
mainBtns55.setPadding(new Insets(10));

mainBtns55.setStyle("-fx-background-color:#5f7470");
mainBtns55.setPrefHeight(50); // set the height of the HBox

mainBtns55.setPrefWidth(backgroundImageView.getFitWidth()); // set the width of the HBox
mainBtns55.setLayoutX(0);
mainBtns55.setLayoutY(backgroundImageView.getFitHeight() - mainBtns55.getPrefHeight());

Image homePic66 = new Image(getClass().getResourceAsStream("/images/home.png"));
Image searchPic66 = new Image(getClass().getResourceAsStream("/images/loupe.png"));
Image user2Pic66 = new Image(getClass().getResourceAsStream("/images/account.png"));

ImageView userImageView66 = new ImageView(user2Pic66);
userImageView66.setFitHeight(30);
userImageView66.setFitWidth(30);

ImageView serch6 = new ImageView(searchPic66);
serch6.setFitWidth(30);
serch6.setFitHeight(30);

ImageView home6 = new ImageView(homePic66);
home6.setFitWidth(30);
home6.setFitHeight(30);

  Button home66 = new Button("",home6);
       home66.setBackground(null);
       Button serch66 = new Button("",serch6);
       serch66.setBackground(null);
       Button userbt6 = new Button("",userImageView66);
       userbt6.setBackground(null);
        
        home66.setOnAction(e-> {
               primaryStage.setScene(scene1);
            } );
 
 
 
 serch66.setOnAction(e-> {
     
          primaryStage.setScene(scenechoose);
   
            } );
 
 
 userbt6.setOnAction(e-> {
        
        primaryStage.setScene(scenebasket);
              });
 

mainBtns55.getChildren().addAll(home66,serch66,userImageView66);

     Button indicator6=new Button("<");
        Button indicator66=new Button(">");
        indicator6.setTranslateY(10);
        indicator6.setTranslateX(10);
        
        indicator66.setTranslateY(10);
        indicator66.setTranslateX(10);
        
        indicator6.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
        indicator66.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
        
       indicator6.setOnAction(e->{
//           primaryStage.setScene(scenetotal);
           primaryStage.setScene(scenetotal);
       });
       indicator66.setOnAction(e->{
//           primaryStage.setScene(scenetotal);
           primaryStage.setScene(scenebasket);
       });; 

    
         
        HBox swich6 =new HBox(230);
        swich6.getChildren().addAll(indicator6,indicator66);

Group root66 = new Group(backgroundImageView66, userImageView56, userNameLabel2,userNameLabel3,vbox, termsCheckbox,swich6,mainBtns55);
 // StackPane root66 = new StackPane();
 
//root66.getChildren().addAll(backgroundImageView6, root88);


      /*-----------------Scene 7  -----------------*/
     
       
        Image backgroundImage7 = new Image(getClass().getResourceAsStream("/images/imagebackground.png"));
        ImageView backgroundImageView7 = new ImageView(backgroundImage);
        backgroundImageView7.setFitWidth(300);
        backgroundImageView7.setFitHeight(500);
        backgroundImageView7.setPreserveRatio(false);

        Image userImage7 = new Image(getClass().getResourceAsStream("/images/userpurple2.png"));
        ImageView userImageView77 = new ImageView(userImage7);
        userImageView77.setX(10);
        userImageView77.setY(30);
        userImageView77.setFitHeight(60);
        userImageView77.setFitWidth(60);
        
             // Create a label with the user name
   userNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));

//        lbname.setStyle("-fx-text-fill: #968393;");
        userNameLabel.setStyle("-fx-text-fill: #889696;");
        userNameLabel.setLayoutX(70);
       
        userNameLabel.setLayoutY(50);
        userNameLabel4.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
          userNameLabel4.setStyle("-fx-text-fill: #889696;");
        userNameLabel4.setLayoutX(70);
//        userNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
//
////        lbname.setStyle("-fx-text-fill: #968393;");
//        userNameLabel.setStyle("-fx-text-fill: #889696;");
////        Label userNameLabel = new Label("USER");
//        userNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
//        userNameLabel.setTextFill(Color.BLACK);
//        userNameLabel.setAlignment(Pos.CENTER_LEFT);
//        userNameLabel.setLayoutX(100);
//        userNameLabel.setLayoutY(60);


HBox topimage=new HBox(10);
topimage.setPadding(new Insets(10));
topimage.getChildren().addAll(userImageView77,userNameLabel,userNameLabel4);
       
        Pane ellipsePane = new Pane();
        ellipsePane.setPadding(new Insets(20));
        ellipsePane.setLayoutX(0);
        ellipsePane.setLayoutY(120);

        // Create the elliptical shape
        Ellipse ellipse2 = new Ellipse();
        ellipse2.setCenterX(140);
        ellipse2.setCenterY(30);
        ellipse2.setRadiusX(80);
        ellipse2.setRadiusY(20);

        LinearGradient fillGradient2 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#4ABBC9")),
                new Stop(1, Color.web("#D561E8")));

        // Set the fill color of the ellipse to the linear gradient
        ellipse2.setFill(fillGradient2);

        Text text = new Text("Debt transfer");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        text.setFill(Color.BLACK);
        text.setX(90);
        text.setY(35);

        ellipsePane.getChildren().addAll(ellipse2, text);
  

        // Create an HBox for the main buttons
        HBox mainBtns7 = new HBox(70);
        mainBtns.setAlignment(Pos.CENTER);
        mainBtns7.setPadding(new Insets(10));

        mainBtns7.setStyle("-fx-background-color:#5f7470");
        mainBtns7.setPrefHeight(50); // set the height of the HBox
        mainBtns7.setPrefWidth(backgroundImageView.getFitWidth()); // set the width of the HBox
        mainBtns7.setLayoutX(0);
        mainBtns7.setLayoutY(backgroundImageView.getFitHeight() - mainBtns7.getPrefHeight());

         
        Image homePic7 = new Image(getClass().getResourceAsStream("/images/home.png"));
        Image searchPic7 = new Image(getClass().getResourceAsStream("/images/loupe.png"));
        Image user2Pic7 = new Image(getClass().getResourceAsStream("/images/account.png"));

        ImageView userImageView777 = new ImageView(user2Pic7);
        userImageView777.setFitHeight(30);
        userImageView777.setFitWidth(30);

        ImageView serch7 = new ImageView(searchPic7);
        serch7.setFitWidth(30);
        serch7.setFitHeight(30);

        ImageView home7 = new ImageView(homePic7);
        home7.setFitWidth(30);
        home7.setFitHeight(30);
        
         Button home77 = new Button("",home7);
       home77.setBackground(null);
       Button serch77 = new Button("",serch7);
       serch77.setBackground(null);
       Button userbt7 = new Button("",userImageView777);
       userbt7.setBackground(null);
        
        home77.setOnAction(e->{
         
               primaryStage.setScene(scene1); 
            } );
 
 
 
 serch77.setOnAction(e-> {
         
          primaryStage.setScene(scenechoose);
            } );

 userbt7.setOnAction(e-> {
           
        primaryStage.setScene(scenebasket);
        System.out.println("you are rellay in this page ");
             });
        
        Button back7=new Button("<");
        Button next7=new Button(">");
        back7.setTranslateY(10);
        next7.setTranslateX(10);
        
        back7.setTranslateY(10);
        next7.setTranslateX(10);
        
        back7.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill:#9e6f21;");
        next7.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill:#9e6f21;");
         back7.setOnAction(e->{
             
           primaryStage.setScene(sceneDebtscheduler);
       });
//          next7.setOnAction(e->{
//             
//           primaryStage.setScene(informationdebet);
//       });   
        HBox swich7 =new HBox(230);
        swich7.getChildren().addAll(back7);
        
        mainBtns7.getChildren().addAll(home77,serch77,userbt7);
      
Label nameLabel7 = new Label("Grocer:");
nameLabel7.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
nameLabel7.setTextFill(Color.BLACK);


ObservableList<String> obGroccere =  FXCollections.observableArrayList();
ObservableList<String> obneighborhood =  FXCollections.observableArrayList();
ObservableList<String> obProduct =  FXCollections.observableArrayList();
ObservableList<Integer> obProductotal =  FXCollections.observableArrayList();

ListView<String> nameListView = new ListView<>(obGroccere);
nameListView.setPrefSize(70, 100);


VBox nameBox = new VBox(15);
nameBox.setPadding(new Insets(10));
nameBox.getChildren().addAll(nameLabel7, nameListView);


Label neighborhoodLabel = new Label("Neighborhood:");
neighborhoodLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
neighborhoodLabel.setTextFill(Color.BLACK);

ListView<String> neighborhoodListView = new ListView<>(obneighborhood);
neighborhoodListView.setPrefSize(70,100);

VBox neighborhoodBox = new VBox(15);
neighborhoodBox.getChildren().addAll(neighborhoodLabel, neighborhoodListView);
neighborhoodBox.setPadding(new Insets(10));


Label productsLabel = new Label("Products:");
productsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
productsLabel.setTextFill(Color.BLACK);


ListView<String> productsListView = new ListView<>(obProduct);
productsListView.setPrefSize(90,100);

VBox productsBox = new VBox(15);
productsBox.setPadding(new Insets(10));
productsBox.getChildren().addAll(productsLabel, productsListView);

Label totalLabel = new Label("Total:");
totalLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
totalLabel.setTextFill(Color.BLACK);

ListView<Integer> totalListView = new ListView<>(obProductotal);
totalListView.setPrefSize(50, 100);


comboBox2.setOnAction(e->{
if(comboBox2.getValue()=="Al-awali"){
obneighborhood.add(0, comboBox2.getValue().toString());
        }
else if(comboBox2.getValue()=="Al-sharayie"){
    obneighborhood.add(0, comboBox2.getValue().toString());
}
});
comboBox3.setOnAction(e->{
if(comboBox3.getValue()=="AlRemal"){
    obGroccere.add(0, comboBox3.getValue().toString());}
});
 //productsListView


Session sessionForListView = HibernateUtil.getSessionFactory().openSession();
List<Product> pList ;
String queryStr = "from Product";
Query query = sessionForListView.createQuery(queryStr);
pList = query.list();
sessionForListView.close();

for(Product p: pList){
 obProduct.add(0, p.getProductName());
 obProductotal.add(0, p.getTotalAmount());
}

//=(Product)session.get(Product.class,"berain water");
//p2=(Product)session.get(Product.class,"coriander");
//p3=(Product)session.get(Product.class,"sugar");
//obProduct.add(0, p1.getProductName());
//obProduct.add(0, p2.getProductName());

//txForListView.commit();

/// the rest of the code
VBox totalBox = new VBox(15);
totalBox.setPadding(new Insets(10));
totalBox.getChildren().addAll(totalLabel, totalListView);

HBox lisdt1 = new HBox();
lisdt1.getChildren().addAll(nameBox,neighborhoodBox,productsBox,totalBox);
VBox V7V = new VBox(swich7,topimage);
BorderPane pp=new BorderPane();
pp.setTop(V7V);

VBox root7 = new VBox(ellipsePane,lisdt1);
root7.setAlignment(Pos.CENTER);
//Group root77 = new Group(backgroundImageView7 ,root7,mainBtns7);
pp.setCenter(root7);
pp.setBottom(mainBtns7);

StackPane root77 = new StackPane();
root77.getChildren().addAll(backgroundImageView7, pp);
//  Image backgroundImage7 = new Image(getClass().getResourceAsStream("/images/imagebackground.png"));
//        ImageView backgroundImageView7 = new ImageView(backgroundImage);
//        backgroundImageView7.setFitWidth(300);
//        backgroundImageView7.setFitHeight(500);
//        backgroundImageView7.setPreserveRatio(false);
//
//        Image userImage7 = new Image(getClass().getResourceAsStream("/images/userpurple2.png"));
//        ImageView userImageView77 = new ImageView(userImage7);
//        userImageView77.setX(10);
//        userImageView77.setY(30);
//        userImageView77.setFitHeight(60);
//        userImageView77.setFitWidth(60);
//
//        Label userNameLabel = new Label("USER");
//        userNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
//        userNameLabel.setTextFill(Color.BLACK);
//        userNameLabel.setAlignment(Pos.CENTER_LEFT);
////        userNameLabel.setLayoutX(100);
////        userNameLabel.setLayoutY(60);
//HBox topimage=new HBox(10);
//topimage.setPadding(new Insets(10));
//topimage.getChildren().addAll(userImageView77,userNameLabel);
//       
//        Pane ellipsePane = new Pane();
//        ellipsePane.setPadding(new Insets(20));
//        ellipsePane.setLayoutX(0);
//        ellipsePane.setLayoutY(120);
//
//        // Create the elliptical shape
//        Ellipse ellipse2 = new Ellipse();
//        ellipse2.setCenterX(140);
//        ellipse2.setCenterY(30);
//        ellipse2.setRadiusX(80);
//        ellipse2.setRadiusY(20);
//
//        LinearGradient fillGradient2 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
//                new Stop(0, Color.web("#4ABBC9")),
//                new Stop(1, Color.web("#D561E8")));
//
//        // Set the fill color of the ellipse to the linear gradient
//        ellipse2.setFill(fillGradient2);
//
//        Text text = new Text("Debt transfer");
//        text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
//        text.setFill(Color.BLACK);
//        text.setX(90);
//        text.setY(35);
//
//        ellipsePane.getChildren().addAll(ellipse2, text);
//
//        // Create an HBox for the main buttons
//        HBox mainBtns7 = new HBox(90);
//        mainBtns7.setPadding(new Insets(10));
//
//        mainBtns7.setStyle("-fx-background-color:#5f7470");
//        mainBtns7.setPrefHeight(50); // set the height of the HBox
//       // mainBtns.setPrefWidth(backgroundImageView.getFitWidth()); // set the width of the HBox
//        mainBtns7.setLayoutX(0);
//        mainBtns7.setLayoutY(backgroundImageView.getFitHeight() - mainBtns7.getPrefHeight());
//
//         
//        Image homePic7 = new Image(getClass().getResourceAsStream("/images/home.png"));
//        Image searchPic7 = new Image(getClass().getResourceAsStream("/images/loupe.png"));
//        Image user2Pic7 = new Image(getClass().getResourceAsStream("/images/account.png"));
//
//        ImageView userImageView777 = new ImageView(user2Pic7);
//        userImageView777.setFitHeight(30);
//        userImageView777.setFitWidth(30);
//
//        ImageView serch7 = new ImageView(searchPic7);
//        serch7.setFitWidth(30);
//        serch7.setFitHeight(30);
//
//        ImageView home7 = new ImageView(homePic7);
//        home7.setFitWidth(30);
//        home7.setFitHeight(30);
//        
//        Button back7=new Button("<");
//        Button next7=new Button(">");
//        back7.setTranslateY(10);
//        next7.setTranslateX(10);
//        
//        back7.setTranslateY(10);
//        next7.setTranslateX(10);
//        
//        back7.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill:#9e6f21;");
//        next7.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill:#9e6f21;");
//         back7.setOnAction(e->{
//             
//           primaryStage.setScene(sceneDebtscheduler);
//       });
//          next7.setOnAction(e->{
//             
//           primaryStage.setScene(informationdebet);
//       });   
//        HBox swich7 =new HBox(230);
//        swich7.getChildren().addAll(back7,next7);
//        
//        mainBtns7.getChildren().addAll(home7,serch7,userImageView777);
//      
//Label nameLabel7 = new Label("Grocer:");
//nameLabel7.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
//nameLabel7.setTextFill(Color.BLACK);
//
//
//ObservableList<String> obGroccere =  FXCollections.observableArrayList();
//ObservableList<String> obneighborhood =  FXCollections.observableArrayList();
//ObservableList<String> obProduct =  FXCollections.observableArrayList();
//ObservableList<Integer> obProductotal =  FXCollections.observableArrayList();
//
//ListView<String> nameListView = new ListView<>(obGroccere);
//nameListView.setPrefSize(70, 100);
//
//
//VBox nameBox = new VBox(15);
//nameBox.setPadding(new Insets(10));
//nameBox.getChildren().addAll(nameLabel7, nameListView);
//
//
//Label neighborhoodLabel = new Label("Neighborhood:");
//neighborhoodLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
//neighborhoodLabel.setTextFill(Color.BLACK);
//
//ListView<String> neighborhoodListView = new ListView<>(obneighborhood);
//neighborhoodListView.setPrefSize(70,100);
//
//VBox neighborhoodBox = new VBox(15);
//neighborhoodBox.getChildren().addAll(neighborhoodLabel, neighborhoodListView);
//neighborhoodBox.setPadding(new Insets(10));
//
//
//Label productsLabel = new Label("Products:");
//productsLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
//productsLabel.setTextFill(Color.BLACK);
//
//
//ListView<String> productsListView = new ListView<>(obProduct);
//productsListView.setPrefSize(70,100);
//
//VBox productsBox = new VBox(15);
//productsBox.setPadding(new Insets(10));
//productsBox.getChildren().addAll(productsLabel, productsListView);
//
//Label totalLabel = new Label("Total:");
//totalLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
//totalLabel.setTextFill(Color.BLACK);
//
//ListView<Integer> totalListView = new ListView<>(obProductotal);
//totalListView.setPrefSize(70, 100);
//
//
//comboBox2.setOnAction(e->{
//if(comboBox2.getValue()=="Al-awali"){
//obneighborhood.add(0, comboBox2.getValue().toString());
//        }
//else if(comboBox2.getValue()=="Al-sharayie"){
//    obneighborhood.add(0, comboBox2.getValue().toString());
//}
//});
//comboBox3.setOnAction(e->{
//if(comboBox3.getValue()=="AlRemal"){
//    obGroccere.add(0, comboBox3.getValue().toString());}
//});
// //productsListView
//
//
//Session sessionForListView = HibernateUtil.getSessionFactory().openSession();
//List<Product> pList ;
//String queryStr = "from Product";
//Query query = sessionForListView.createQuery(queryStr);
//pList = query.list();
//sessionForListView.close();
//
//for(Product p: pList){
// obProduct.add(0, p.getProductName());
// obProductotal.add(0, p.getTotalAmount());
//}
//
////=(Product)session.get(Product.class,"berain water");
////p2=(Product)session.get(Product.class,"coriander");
////p3=(Product)session.get(Product.class,"sugar");
////obProduct.add(0, p1.getProductName());
////obProduct.add(0, p2.getProductName());
//
////txForListView.commit();
//
///// the rest of the code
//VBox totalBox = new VBox(15);
//totalBox.setPadding(new Insets(10));
//totalBox.getChildren().addAll(totalLabel, totalListView);
//
//HBox lisdt1 = new HBox();
//lisdt1.getChildren().addAll(nameBox,neighborhoodBox,productsBox,totalBox);
//HBox lisdt2 = new HBox();
//
//VBox root7 = new VBox(swich7,topimage,ellipsePane,lisdt1,mainBtns7);
//root7.setAlignment(Pos.CENTER);
//StackPane root77 = new StackPane();
//root77.getChildren().addAll(backgroundImageView7, root7);

////       ------------------ SCENE 8 ----------------------
//
//        
////   
////        Image homePic = new Image(getClass().getResourceAsStream("/images/home.png"));
////        Image searchPic = new Image(getClass().getResourceAsStream("/images/loupe.png"));
////        Image user2Pic = new Image(getClass().getResourceAsStream("/images/account.png"));
////
////        ImageView userImageView2 = new ImageView(user2Pic);
////        userImageView2.setFitHeight(30);
////        userImageView2.setFitWidth(30);
////
////        ImageView serch = new ImageView(searchPic);
////        serch.setFitWidth(30);
////        serch.setFitHeight(30);
////
////        ImageView home = new ImageView(homePic);
////        home.setFitWidth(30);
////        home.setFitHeight(30);
////        
//
//        Button indicatorback = new Button("<");
//        Button indicatorgnext = new Button(">");
//        indicatorback.setTranslateY(10);
//        indicatorgnext.setTranslateX(10);
//
//        indicatorback.setTranslateY(10);
//        indicatorgnext.setTranslateX(10);
//
//        indicatorback.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
//        indicatorgnext.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
//
////        indicator.setOnAction(new switchs());
////        indicator2.setOnAction(new switchs2());
////        
//
//        Label tlb = new Label("VISA");
//        tlb.setFont((Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20)));
//        GridPane Gpane = new GridPane();
//        Gpane.setHgap(10);
//
//        Rectangle rectanglev = new Rectangle();
//        // rectangle هنا قمنا بتحديد طول و عرض 
//        rectanglev.setWidth(220);
//        rectanglev.setHeight(140);
//        //  rectangle هنا قمنا بإعطاء لون
//        Stop[] stops = new Stop[]{new Stop(0, Color.PURPLE), new Stop(1, Color.DIMGRAY)};
//        LinearGradient lg1 = new LinearGradient(0, 0.5, 1, 0, true, CycleMethod.NO_CYCLE, stops);
//
//        
//        //قديمم
////        Image imagetool1 = new Image(getClass().getResourceAsStream("/javafxinterfaceproject/home-icone.png"));
////        Image imagetool2 = new Image(getClass().getResourceAsStream("/javafxinterfaceproject/profile-iconetool"));
////        Image imagetool3 = new Image(getClass().getResourceAsStream("/javafxinterfaceproject/search-icon.png"));
////        
////        Button tob1 = new Button("search", new ImageView(imagetool1));
////        Button tob2 = new Button("home", new ImageView(imagetool2));
////        Button tob3 = new Button("notifiction", new ImageView(imagetool3));
////
////        tob1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
////        tob2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
////        tob3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
////
////        tob1.setTextFill(Color.WHITE);
////        tob2.setTextFill(Color.WHITE);
////        tob3.setTextFill(Color.WHITE);
////        tob1.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, new CornerRadii(5), Insets.EMPTY)));
////        tob2.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, new CornerRadii(5), Insets.EMPTY)));
////        tob3.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, new CornerRadii(5), Insets.EMPTY)));
////
////         HBox tool = new HBox();
////        tool.setAlignment(Pos.CENTER);
////        tool.setSpacing(10);
////        tool.getChildren().addAll(tob1, tob2, tob3);
////        
//
//        rectanglev.setArcWidth(30);
//        rectanglev.setArcHeight(20);
//        rectanglev.setFill(lg1);
//        rectanglev.setStroke(Color.LIGHTGRAY);
//
//        Label lb1 = new Label("Total indebtedness");
//        lb1.setFont(new Font("Arial", 15));
//
//        Label lb2 = new Label();
//        Label totalds = new Label();
//
//        
//        lb2.setFont((Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20)));
//        lb2.setText("Total Balance\n");        
//        DTotalPrice.setText("total: "+totalSsum+" SAR");
//
//        totalds.setText("Total Balance\n");
//        lb2.setTextFill(Color.WHITE);
////        btn2.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));
//
//        //khhhhhhhhhh
//        Label lb = new Label("Recent Movements");
//        lb.setFont(new Font("Arial", 13));
//
//        Label lb3 = new Label();
//        lb3.setFont(new Font("Arial", 15));
//        lb3.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
//
//        Label lb4 = new Label("mini market\t\t-22.99 SAR");
//        lb4.setFont(new Font("Arial", 15));
//        lb4.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
//
//        Label lb5 = new Label("sara\t\t\t\t-40.99 SAR");
//        lb5.setFont(new Font("Arial", 15));
//        lb5.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
//
//        BorderPane root8 = new BorderPane();
//        root8.setPadding(new Insets(10));
//
//   StackPane stackPane8 = new StackPane(rectanglev, lb2);
//         
//        VBox v = new VBox(10);
//        v.setSpacing(10);
//        v.setAlignment(Pos.CENTER);
//        v.getChildren().add(stackPane8);
//        v.getChildren().add(lb);
//        v.getChildren().add(lb3);
//        v.getChildren().add(lb4);
//        v.getChildren().add(lb5);
//       
////         v.getChildren().add(lb1);       //lable total intendess
//
//
//        //        Gpane.setVgap(15);
//        //        Gpane.setStyle("-fx-background-color: WHITESMOKE");
//        //        Gpane.add(lb, 0, 0);
//        //        Gpane.add(lb1, 0, 1);
//        //        Gpane.add(lb2, 0, 2);
//        //        Gpane.add(lb3, 0, 3);
//        //        Gpane.add(lb4, 0, 4);
//        //        Gpane.add(lb5, 0, 5);
//        //        Gpane.setAlignment(Pos.CENTER_LEFT);
////         HBox tool =new HBox(230);
////         tool.getChildren().addAll(home,serch,userImageView2);
////        
//           HBox h =new HBox(230);
//        h.getChildren().addAll(indicatorback,indicatorgnext);
//        
////        VBox v1 = new VBox(5);
////        v1.setAlignment(Pos.CENTER);
////        v1.getChildren().add(lb1);
////        v1.getChildren().add(stackPane);
//
//        //v1.getChildren().add(lb2);
//        
//        root8.setTop(h);
//        root8.setCenter(v);
////        b.setBottom(v1);
//        
////        b.setBottom(tool);
//
//
////       ------------------ SCENE creditor info    ----------------------
//
//       Rectangle rectanglec = new Rectangle(200, 200);
//
//        rectanglec.setFill(Color.WHITE);
//
//        rectanglec.setStroke(Color.BLACK);
//        VBox vv = new VBox();
//        Image image =  new Image(getClass().getResourceAsStream("/images/profile_icon.png"));
//
//        ImageView imageView = new ImageView(image);
//
//        imageView.setFitWidth(50);
//
//        imageView.setFitHeight(50);
//         
//        Image image2 =  new Image(getClass().getResourceAsStream("/images/profile_icon.png"));
//
//        ImageView imageView2 = new ImageView(image2);
//
//        imageView2.setFitWidth(50);
//
//        imageView2.setFitHeight(50);
//        
//        Image image3 =  new Image(getClass().getResourceAsStream("/images/profile_icon.png"));
//
//        ImageView imageView3 = new ImageView(image3);
//
//        imageView3.setFitWidth(50);
//
//        imageView3.setFitHeight(50);
//        
//        Image image4 =  new Image(getClass().getResourceAsStream("/images/profile_icon.png"));
//
//        ImageView imageView4 = new ImageView(image4);
//
//        imageView4.setFitWidth(50);
//
//        imageView4.setFitHeight(50);
//
//
//       
////Debtor 1 
//
//       Label userName1=new Label("name");
//       userName1.setStyle(" -fx-font-size: 1em;-fx-text-fill: #FFFFFF ");
//       Text d = new Text("debtor");
//       d.setFill(Color.GRAY);
//       
//  //Debtor 2     
//       Label userName2=new Label("name");
//       userName2.setStyle(" -fx-font-size: 1em;-fx-text-fill: #FFFFFF ");
//       Text d1 = new Text("debtor");
//       d1.setFill(Color.GRAY);
//       
//  //Debtor 3     
//       Label userName3=new Label("name");
//       userName3.setStyle(" -fx-font-size: 1em;-fx-text-fill: #FFFFFF");
//       Text d3 = new Text("debtor");
//       d3.setFill(Color.GRAY);
//      
// //Debtor 4      
//      Label userName4=new Label("name");
//       userName4.setStyle(" -fx-font-size: 1em;-fx-text-fill: #FFFFFF ");
//       Text d2 = new Text("debtor");
//       d2.setFill(Color.GRAY);
//       
//   //VBox for the user name and the debtor    
//       VBox v1 = new VBox(userName1,d);
//       v1.setAlignment(Pos.CENTER);
//    //VBox for the user name and the debtor 
//      VBox v2 = new VBox(userName2,d1);
//      v2.setAlignment(Pos.CENTER);
//      //VBox for the user name and the debtor 
//      VBox v3 = new VBox(userName3,d2);
//      v3.setAlignment(Pos.CENTER);
//       //VBox for the user name and the debtor 
//      VBox v4 = new VBox(userName4,d3);
//      v4.setAlignment(Pos.CENTER);
//     
//      Text text3 = new Text("Last debtors");
//      TextField tt=new TextField();
//      tt.setPromptText("Search : Ahlam alsami");
//      //HBox for ImageView and vbox we have created before 
//       HBox hh=new HBox(imageView,v1);
//       hh.setAlignment(Pos.CENTER_LEFT);
//       hh.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:30;");
//       hh.setPrefSize(150, 60);
//       hh.setTranslateX(30);
//        //HBox for ImageView and vbox we have created before 
//       HBox h1=new HBox(imageView2,v3);
//       h1.setAlignment(Pos.CENTER_LEFT);
//        h1.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:30;");
//       h1.setPrefSize(150, 60);
//       h1.setTranslateX(30);
//        //HBox for ImageView and vbox we have created before 
//       HBox h2=new HBox(imageView3,v2);
//       h2.setAlignment(Pos.CENTER_LEFT);
//        h2.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:30;");
//       h2.setPrefSize(150, 60);
//       h2.setTranslateX(30);
//       //HBox for ImageView and vbox we have created before 
//       HBox h3=new HBox(imageView4,v4);
//       h3.setAlignment(Pos.CENTER_LEFT);
//        h3.setStyle("-fx-background-color:#ad9abb;-fx-background-radius:30;");
//       h3.setPrefSize(150, 60);
//       h3.setTranslateX(30);
//       //VBox for all HBox we have created before 
//       VBox vfinal = new VBox(20);
//       vfinal.getChildren().addAll(hh,h1,h2,h3);
//       vfinal.setAlignment(Pos.CENTER);
//       
//     
//       VBox vForText =new VBox(20);
//       
//       Line line = new Line(); 
//line.setStartX(100.0f); //Setting the Properties of the Line
//line.setStartY(100.0f);
//line.setEndX(300.0f);
//line.setEndY(100.0f);
//line.setStroke(Color.web("313E50"));
//       Text Supermarket1 = new Text("Welcom  ");
//       Supermarket1.setFill(Color.web("2E3532"));
//       HBox hForText1=new HBox(50);
////        Text total = new Text("Total SR");
////        total.setFill(Color.WHITE);
////        Text totalDay = new Text("+--- Today");
////        totalDay.setFill(Color.WHITE);
////        hForText1.getChildren().addAll(total,totalDay);
////         hForText1.setAlignment(Pos.CENTER);
////        Supermarket1.setFont(Font.font(STYLESHEET_CASPIAN,FontWeight.BOLD, 24));
//        Text t1 = new Text("Welcome");
//        t1.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 17));
//        t1.setFill(Color.web("4A314D")); 
//        vForText.getChildren().addAll(t1,userName1,line,hForText1);
//        Rectangle r1 = new Rectangle(298, 140);
//
//        r1.setFill(Color.web("889696"));
//
//        r1.setStroke(Color.WHITE);
//         r1.setArcHeight(20.0d);
//        r1.setArcWidth(20.0d);
//       StackPane s = new StackPane(r1,vForText);
//       vForText.setAlignment(Pos.CENTER);
//       s.setAlignment(Pos.CENTER);
//       
//        Button indicatorc8=new Button("<");
//        Button indicator88=new Button(">");
//        indicatorc8.setTranslateY(10);
//        indicator88.setTranslateX(10);
//        
//        indicatorc8.setTranslateY(10);
//        indicator88.setTranslateX(10);
//        
//        indicatorc8.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
//        indicator88.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
//        
//        indicatorc8.setOnAction(e->{
//       
//       });
//       indicator88.setOnAction(e->{
//       primaryStage.setScene(cred);
//       
//       });
//
//         
//        HBox swich =new HBox(230);
//        swich.getChildren().addAll(indicatorc8,indicator88);
//        VBox vFinal=new VBox(swich,text3,vfinal);
//        vFinal.setAlignment(Pos.CENTER);
//       
//        HBox hboxForBar = new HBox(10);
//        
//        
//        
//        hboxForBar.setAlignment(Pos.CENTER);
//        hboxForBar.setStyle("-fx-background-color: #5F7470;");
//       Image homePic88 = new Image(getClass().getResourceAsStream("/images/home.png"));
//Image searchPic888 = new Image(getClass().getResourceAsStream("/images/loupe.png"));
//Image user2Pic888 = new Image(getClass().getResourceAsStream("/images/account.png"));
//ImageView userImageView88 = new ImageView(user2Pic888);
//userImageView88.setFitHeight(30);
//userImageView88.setFitWidth(30);
//ImageView serch88 = new ImageView(searchPic888);
//serch88.setFitWidth(30);
//serch88.setFitHeight(30);
//ImageView home88 = new ImageView(homePic88);
//home88.setFitWidth(30);
//home88.setFitHeight(30);
//
//       
//        
//        Button bHome = new Button("",home88);
//       bHome.setBackground(null);
//       Button bSearch = new Button("",serch88);
//       bSearch.setBackground(null);
//       Button bUser = new Button("",userImageView88);
//       bUser.setBackground(null);
//    
//
// hboxForBar.getChildren().addAll(bHome,bSearch,bUser);
//
//
//       
//        
//       bHome.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//           public void handle(ActionEvent e) {
//               primaryStage.setScene(scene1);
//                System.out.println("home page");}   
//            } );
// 
// 
// 
// bSearch.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//           public void handle(ActionEvent e) {
////           primaryStage.setScene(scene1);
//                System.out.println("search page");}   
//            } );
// 
// 
// bUser.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//           public void handle(ActionEvent e) {
// //       primaryStage.setScene(profile);
//                System.out.println("profile page");
//           }});   
// 
// 
//        // Create a stack pane and add the rectangle and the image view to it
//
//StackPane stackPane2 = new StackPane(rectanglec,h);
//BorderPane root9 = new BorderPane();
//       root9.setCenter(vFinal);
//       root9.setBottom(hboxForBar);
//        root9.setTop(s);
//  
////       SCENE 9 for creditor showw info debetor 
//    Rectangle rectangle3 = new Rectangle(400, 400);
//
//        rectangle3.setFill(Color.WHITE);
//
//        rectangle3.setStroke(Color.WHITE);
//        // Create an image view for the image
//
//        Image imagee =  new Image(getClass().getResourceAsStream("/images/profile_icon.png"));
//
//        ImageView imageVieww = new ImageView(imagee);
//
//        imageVieww.setFitWidth(50);
//
//        imageVieww.setFitHeight(50);
//       
//
//        Label creditorName = new Label("Ahlam Alsami\n debtor");
//
//        creditorName.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15));
//          creditorName.setStyle("-fx-text-fill: #968393;");
//        HBox hboxForBarr = new HBox(10);
//        
//        Rectangle rr1 = new Rectangle(298, 250);
//
//        rr1.setFill(Color.web("AD9BAA"));
//
//        rr1.setStroke(Color.WHITE);
//         rr1.setArcHeight(25.0d);
//        rr1.setArcWidth(25.0d);
//        
//        Label name=new Label("Name:");
//        Label ID=new Label("National ID: ");
//        Label totalc =new Label("The total amount of debt paid :9.50 SR");
//        Label Remaining =new Label("Remaining amount  : 9.50 SR");
//        VBox vForLabel = new VBox(5);
//        vForLabel.getChildren().addAll(name,ID,totalc,Remaining);
//        vForLabel.setAlignment(Pos.CENTER_LEFT);
//        
//      
//        
//        Image imageD = new Image(getClass().getResourceAsStream("/images/debts_icon.png"));
//        ImageView imageViewD = new ImageView(imageD);
//        imageViewD.setX(30);
//        imageViewD.setY(30);
//        imageViewD.setFitHeight(20); //setting the fit height and width of the image view
//        imageViewD.setFitWidth(20);
//        
//        Image imageA = new Image(getClass().getResourceAsStream("/images/amount_icon.png"));
//        ImageView imageViewA = new ImageView(imageA);
//        imageViewA.setX(30);
//        imageViewA.setY(30);
//        imageViewA.setFitHeight(20); //setting the fit height and width of the image view
//        imageViewA.setFitWidth(20);
//        
//        
//        
//        
//        
//        Image imageR = new Image(getClass().getResourceAsStream("/images/remainder_icon.png"));
//        ImageView imageViewR = new ImageView(imageR);
//        imageViewR.setX(30);
//        imageViewR.setY(30);
//        imageViewR.setFitHeight(20); //setting the fit height and width of the image view
//        imageViewR.setFitWidth(20);
//        
//        Image imageM = new Image(getClass().getResourceAsStream("/images/email_icon.png"));
//        ImageView imageViewM = new ImageView(imageM);
//        imageViewM.setX(30);
//        imageViewM.setY(30);
//        imageViewM.setFitHeight(20); //setting the fit height and width of the image view
//        imageViewM.setFitWidth(20);
//        
//        Image imageP = new Image(getClass().getResourceAsStream("/images/payment1_icon.png"));
//        ImageView imageViewP = new ImageView(imageP);
//        imageViewP.setX(30);
//        imageViewP.setY(30);
//        imageViewP.setFitHeight(20); //setting the fit height and width of the image view
//        imageViewP.setFitWidth(20);
//        Label l= new Label(" \n ");
//        
//        VBox vForImage = new VBox();
//        vForImage.getChildren().addAll(l,imageViewA,imageViewD,imageViewP,imageViewR,imageViewM);
//        vForImage.setAlignment(Pos.CENTER_LEFT);
//        
//        
//        
//        HBox hFinal = new HBox (vForLabel,vForImage);
//        hFinal.setAlignment(Pos.CENTER);
//        
//        
//        StackPane p = new StackPane (rr1,hFinal);
//        p.setAlignment(Pos.CENTER);
//        
//       
//        
//        Button indicator9=new Button("<");
//        Button indicator99=new Button(">");
//        indicator9.setTranslateY(10);
//        indicator99.setTranslateX(10);
//        
//        indicator9.setTranslateY(10);
//        indicator99.setTranslateX(10);
//indicator9.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
//        indicator99.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-text-fill: #9e6f21;");
//        
//       indicator9.setOnAction(e->{
//       primaryStage.setScene(allDebts);
//       });
//       indicator99.setOnAction(e->{
//       primaryStage.setScene(allDebts);
//       
//       });
//         
//        HBox swich9 =new HBox(230);
//        swich9.getChildren().addAll(indicator9,indicator99);
//        
//        
//        
//        
//        
//        hboxForBarr.setAlignment(Pos.CENTER);
//        hboxForBarr.setStyle("-fx-background-color: #5F7470;");
//        
//        Image homePic9= new Image(getClass().getResourceAsStream("/images/home.png"));
//Image searchPic9 = new Image(getClass().getResourceAsStream("/images/loupe.png"));
//Image user2Pic9 = new Image(getClass().getResourceAsStream("/images/account.png"));
//ImageView userImageView9 = new ImageView(user2Pic9);
//userImageView9.setFitHeight(30);
//userImageView9.setFitWidth(30);
//ImageView serch9 = new ImageView(searchPic9);
//serch9.setFitWidth(30);
//serch9.setFitHeight(30);
//ImageView home9 = new ImageView(homePic9);
//home9.setFitWidth(30);home9.setFitHeight(30);
//
//       
//        
//        Button bHome9 = new Button("",home9);
//       bHome9.setBackground(null);
//       Button bSearch9 = new Button("",serch9);
//       bSearch9.setBackground(null);
//       Button bUser9 = new Button("",userImageView9);
//       bUser9.setBackground(null);
//    
//
// hboxForBarr.getChildren().addAll(bHome9,bSearch9,bUser9);
//
//
//       
//        
//       bHome.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//           public void handle(ActionEvent e) {
//                System.out.println("home page");}   
//            } );
// 
// 
//
//
//
//        // Create a VBox to hold the image view and the text
//
//        VBox vBox = new VBox(imageVieww,creditorName);
//
//        vBox.setAlignment(Pos.CENTER);
//TextField ttt=new TextField();
//ttt.setPromptText("Search : Ahlam alsami");
//
//
//        // Create a stack pane and add the rectangle and the VBox to it
//
// StackPane stackPane3 = new StackPane(rectangle3, vBox);
//VBox v11=new VBox(ttt,swich9,vBox);
//BorderPane panee = new BorderPane();
//panee.setBottom(hboxForBarr);
//panee.setTop(v11);
//panee.setCenter(p);
//
//        // Create a stack pane and add the rectangle and the image view to it
//
//
//        // Create a scene and add the stack pane to it
//
//
//
//        // Load the background image
//        

scene1 = new Scene(root, 300, 500);
sceneLogin = new Scene(root2, 300, 500);
sceneSignUp = new Scene(root3, 300, 500);
scenechoose = new Scene(root4, 300, 500);
scenestore=new Scene(root5,300,500);
scenetotal=new Scene(Droot,300,500);
sceneDebtscheduler = new Scene(root66,300,500);
scenebasket =  new Scene(root77, 300, 500);

//informationdebet= new Scene(root8, 300, 500);
//cred=new Scene(panee, 300, 500);
//allDebts=new Scene(root9, 300, 500);
        

Image icon = new Image(getClass().getResourceAsStream("/images/insaflogo2.png"));
primaryStage.getIcons().add(icon);
primaryStage.setTitle("insaf");
primaryStage.setScene(scene1);
primaryStage.show();
    }
    
        
             
    public static void main(String[] args) {
        launch(args);
    }
}
