package assignment.application;

import java.io.IOException;
import java.util.Set;

import assignment.application.model.Company;
import assignment.application.model.Project;
import assignment.application.model.ProjectOwner;
import assignment.application.ui.CompanyController;
import assignment.application.ui.DashboardController;
import assignment.application.ui.ProjectController;
import assignment.application.ui.ProjectOwnerController;
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
     * Primary Application Window 
     */
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Team Formation App");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Dashboard.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ui/Theme.css").toExternalForm());
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

    // Not an ideal design structure, right now a quick fix
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    public boolean showAddCompany(Company company)
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Company.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Company");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CompanyController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCompany(company);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        }
        catch (IOException expection)
        {
            expection.printStackTrace();
            return false;
        }
    }

    public boolean showAddProjectOwner(ProjectOwner owner, Set<String> companyList)
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/ProjectOwner.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Project Owner");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ProjectOwnerController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOwner(owner);
            controller.setCompanyList(companyList);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        }
        catch (IOException expection)
        {
            expection.printStackTrace();
            return false;
        }
    }

    public boolean showAddProject(Project project, Set<String> ownerList)
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Project.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Project");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ProjectController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProject(project);
            controller.setProjectOwnerList(ownerList);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        }
        catch (IOException expection)
        {
            expection.printStackTrace();
            return false;
        }
    }

    public void showAddTeam(Set<String> studentList, Set<String> projectList)
    {
        try
        {
            // Load the fxml file and create a new stage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ui/Team.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create/Edit Team");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setResizable(false);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            TeamController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProjectList(projectList);
            controller.setStudentList(studentList);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        }
        catch (IOException expection)
        {
            expection.printStackTrace();
        }
    }
}
