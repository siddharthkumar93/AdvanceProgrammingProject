package assignment.application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

// Class containing commonly used vales 
public class GlobalVar
{

    // Different window title
    static String mainAppTitle = "Team Formation App";
    static String companyWindowTitle = "Add Company";
    static String ownerWindowTitle = "Add Project Owner";
    static String projectWindowTitle = "Add Project";
    static String studentWindowUpdateTitle = "Update Student Details";
    static String studentWindowAddTitle = "Add New Student";
    static String teamWindowTitle = "Form/Edit Team";

    // Commonly used string declaration
    public static String emptyString = "";

    // Database path
    public static String dbPath = "jdbc:sqlite:D:/Workplace_Java/Final_Assignment/TeamApplication.db";
    
    // Database create strings
    public static String tableExistString = "DROP TABLE IF EXISTS ";

    public static String createCompanyString = "CREATE TABLE Company" + "(CompanyID varchar(30) PRIMARY KEY NOT NULL, "
                + "Name varchar(250), " + "ABNNumber varchar(30), " + "Url varchar(150), " + "Address varchar(250))";

    public static String createProjectOwnerString = "CREATE TABLE ProjectOwner" + "(OwnerID varchar(30) PRIMARY KEY NOT NULL, "
                + "FirstName varchar(250), " + "LastName varchar(250), " + "Role varchar(100), " + "EmailID varchar(150), "
                + "CompanyID varchar(30))";

    public static String createProjectString = "CREATE TABLE Project" + "(ProjectID varchar(30) PRIMARY KEY NOT NULL, "
                + "Title varchar(30), " + "Description varchar(250), " + "OwnerID varchar(30), " + "Ranking varchar(30))";

    public static String createStudentString = "CREATE TABLE Student" + "(StudentID varchar(30) PRIMARY KEY NOT NULL, "
                + "Grades varchar(30), " + "Personality varchar(5), " + "Confict varchar(30), " + "Preference varchar(30))";

    public static String createTeamString = "CREATE TABLE Team" + "(TeamID varchar(30) PRIMARY KEY NOT NULL, "
                + "Name varchar(100), " + "Members varchar(200))";

    // Commonly used message boxes
    
    // Method to throw required field error message boxes
    public static void requiredFieldError(Stage currentStage)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(currentStage);
        alert.setTitle("Required Field Missing!!!");
        alert.setHeaderText("All required field values haven't been filled.");
        alert.setContentText(
                    "Atleast one required field is missing value. Please verify the fiels with (*) mark are filled before submitting");
        alert.showAndWait();
    }

    // Method to throw required data map error box
    public static void requiredDataMissing(String missingValue, Stage currentStage)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(currentStage);
        alert.setTitle(missingValue + " Missing !!!");
        alert.setHeaderText("Atleast one " + missingValue + " required.");
        alert.setContentText("Please add atleast one " + missingValue + " to continue.");
        alert.showAndWait();
    }

    // Method to throw error box for non-numeric input for IDs
    public static void numberFieldError(String fieldName,Stage currentStage)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(currentStage);
        alert.setTitle("Number Field Error!!!");
        alert.setHeaderText("Only numbers accepted in ".concat(fieldName).concat(" field."));
        alert.setContentText(fieldName.concat(" field only accepts numbers. Please verify your input."));
        alert.showAndWait();
        
    }

    // Method to throw error box in case there is no student selection and Add Preference/ Edit Detail button is pressed.
    public static void selectionMissingError(Stage currentStage)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(currentStage);
        alert.setTitle("No Selection Found!!!");
        alert.setHeaderText("No student was selected.");
        alert.setContentText("Please select a student to continue. If no students are present click 'Read Student' button to load student.");
        alert.showAndWait();
        
    }
}