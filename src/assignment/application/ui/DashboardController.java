package assignment.application.ui;

import assignment.application.Main;
import assignment.application.model.Company;
import assignment.application.model.ModelWrapper;
import assignment.application.model.Project;
import assignment.application.model.ProjectOwner;
import assignment.application.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DashboardController
{
    @FXML
    private Pane pn_company, pn_project, pn_owner, pn_student, pn_team;

    @FXML
    private Button btn_company, btn_project, btn_owner, btn_student, btn_team;

    private Main mainApp;
    private ModelWrapper wrapper;

    // To return reference to itself
    public void setMainApp(Main mainApp)
    {
        this.mainApp = mainApp;
    }

    //
    public void setWrapper(ModelWrapper wrapper)
    {
        this.wrapper = wrapper;
    }

    @FXML
    private void handleDashboardButtonAction(ActionEvent event)
    {
        if (event.getSource() == btn_company)
        {
            pn_company.toFront();
        }
        else if (event.getSource() == btn_project)
        {
            pn_project.toFront();
        }
        else if (event.getSource() == btn_owner)
        {
            pn_owner.toFront();
        }
        else if (event.getSource() == btn_student)
        {
            pn_student.toFront();
        }
        else if (event.getSource() == btn_team)
        {
            pn_team.toFront();
        }
    }

    @FXML
    private void handleAddCompany(ActionEvent event)
    {
        Company tempCompany = new Company();
        boolean okClicked = mainApp.showAddCompany(tempCompany);
        if (okClicked)
        {
            wrapper.addCompany(tempCompany);
        }
    }

    @FXML
    private void handleAddOwner(ActionEvent event)
    {
        ProjectOwner tempOwner = new ProjectOwner();
        if (!wrapper.getCompanyList().isEmpty())
        {
            boolean okClicked = mainApp.showAddProjectOwner(tempOwner, wrapper.getCompanyList());
            if (okClicked)
            {
                wrapper.addProjectOwner(tempOwner);
            }
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Companies Missing");
            alert.setHeaderText("Atleast one company required");
            alert.setContentText("Please add atleast one company to add project owner");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleAddProject(ActionEvent event)
    {
        Project tempProject = new Project();
        if (!wrapper.getProjectOwnerList().isEmpty())
        {
            boolean okClicked = mainApp.showAddProject(tempProject, wrapper.getProjectOwnerList());
            if (okClicked)
            {
                wrapper.addProject(tempProject);
            }
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Project Owners Missing");
            alert.setHeaderText("Atleast one Owner required");
            alert.setContentText("Please add atleast one project owner to add projects");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleReadStudent(ActionEvent event)
    {
        wrapper.readAll();
        //wrapper.readStudent();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Data Loaded");
        alert.setHeaderText("Students prefernce and project data loaded");
        alert.showAndWait();
    }
    
    @FXML
    private void handleAddTeam(ActionEvent event)
    {
        Team tempTeam = new Team();
        if (!(wrapper.getProjectList().isEmpty() && wrapper.getStudentList().isEmpty()))
        {
            boolean okClicked = mainApp.showAddTeam(tempTeam, wrapper.getStudentList(), wrapper.getProjectList());
            if (okClicked)
            {
                //wrapper.addTeam(tempTeam);
            }
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Project or/and Students Missing");
            alert.setHeaderText("Atleast one Project and four Students required");
            alert.setContentText("Please use the 'Read Student' button to load projects and students");
            alert.showAndWait();
        }
    }
}
