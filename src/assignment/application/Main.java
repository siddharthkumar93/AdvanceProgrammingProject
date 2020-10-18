package assignment.application;

import java.io.IOException;
import java.util.Set;

import assignment.application.model.Student;
import assignment.application.ui.CompanyController;
import assignment.application.ui.DashboardController;
import assignment.application.ui.PreferenceController;
import assignment.application.ui.ProjectController;
import assignment.application.ui.ProjectOwnerController;
import assignment.application.ui.StudentController;
import assignment.application.ui.TeamController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application
{
    private Stage primaryStage;

    /**
     * @author Siddharth Kumar
     *
     */
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle(GlobalVar.mainAppTitle);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Dashboard.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            DashboardController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    // Method to access primary stage
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    // Method to show Add Company form
    public void showAddCompany()
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Company.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(GlobalVar.companyWindowTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CompanyController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        }
        catch (IOException expection)
        {
            expection.printStackTrace();
        }
    }

    // Method to show Add Project Owner form
    public void showAddProjectOwner(Set<String> companyList)
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/ProjectOwner.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(GlobalVar.ownerWindowTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ProjectOwnerController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCompanyList(companyList);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        }
        catch (IOException expection)
        {
            expection.printStackTrace();
        }
    }

    // Method to show Add Project form
    public void showAddProject(Set<String> ownerList)
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Project.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(GlobalVar.projectWindowTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ProjectController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProjectOwnerList(ownerList);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        }
        catch (IOException expection)
        {
            expection.printStackTrace();
        }
    }

    // Method to show Edit Student details form
    public void showStudent(Set<String> studentList, Student studentObj)
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Student.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(GlobalVar.studentWindowUpdateTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            StudentController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.initializeStudent(studentList, studentObj);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        }
        catch (IOException expection)
        {
            expection.printStackTrace();
        }
    }

    // Method to show Add Student preference form
    public void showAddPreference(Set<String> projectList, Student studentObj)
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Preference.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(GlobalVar.studentWindowUpdateTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PreferenceController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.initializePreference(projectList, studentObj);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        }
        catch (IOException expection)
        {
            expection.printStackTrace();
        }
    }

    // Method to show teamFormation form
    public void showAddTeam()
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Team.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(GlobalVar.teamWindowTitle);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            TeamController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProjectList();
            controller.setStudentList();

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        }
        catch (IOException expection)
        {
            expection.printStackTrace();
        }
    }
}
