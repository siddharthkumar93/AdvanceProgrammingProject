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

    // Database create String
    private static String tableExistString = "CREATE TABLE IF NOT EXISTS ";

    public static String createCompanyString = tableExistString + "Company" 
                + "(CompanyID varchar(30) PRIMARY KEY NOT NULL, "
                + "Name varchar(250), " + "ABNNumber varchar(30), " + "Url varchar(150), " + "Address varchar(250))";

    public static String createProjectOwnerString = tableExistString + "ProjectOwner"
                + "(OwnerID varchar(30) PRIMARY KEY NOT NULL, " + "FirstName varchar(250), " + "LastName varchar(250), "
                + "Role varchar(100), " + "EmailID varchar(150), " + "CompanyID varchar(30))";

    public static String createProjectString = GlobalVar.tableExistString + "Project"
                + "(ProjectID varchar(30) PRIMARY KEY NOT NULL, " + "Title varchar(30), " + "Description varchar(250), "
                + "OwnerID varchar(30), " + "Ranking varchar(30))";

    public static String createStudentString = GlobalVar.tableExistString + "Student"
                + "(StudentID varchar(30) PRIMARY KEY NOT NULL, " + "Grades varchar(30), " + "Personality varchar(5), "
                + "Confict varchar(30), " + "Preference varchar(30))";

    public static String createTeamString = GlobalVar.tableExistString + "Team" 
                + "(TeamID varchar(30) PRIMARY KEY NOT NULL, "
                + "Name varchar(100), " + "Members varchar(200))";
    
    
    // Commonly used message boxes

    // Method to throw required field error message boxes
    public static void requiredFeildError(Stage currentStage)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.initOwner(currentStage);
        alert.setTitle("Required Field Missing!!!");
        alert.setHeaderText("All required field values haven't been filled.");
        alert.setContentText(
                    "Atleast one required field is missing value. Please verify the fiels with (*) mark are filled before submitting");
        alert.showAndWait();
    }

    public static void requiredDataMissing(String missingValue)
    {

    }

}