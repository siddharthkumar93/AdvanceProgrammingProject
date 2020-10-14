package assignment.application.ui;

import assignment.application.Main;
import assignment.application.data.DatabaseReadWrite;
import assignment.application.model.Company;
import assignment.application.model.ModelWrapper;
import assignment.application.model.Project;
import assignment.application.model.ProjectOwner;
import assignment.application.model.Student;
import assignment.application.model.Team;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class DashboardController
{
    @FXML
    private Pane pn_company, pn_project, pn_owner, pn_student, pn_team;

    @FXML
    private Button btn_company, btn_project, btn_owner, btn_student, btn_team, btn_dbInitialize, btn_dbRead, btn_dbWrite;

    @FXML
    private TableView<Company> tbl_company;

    @FXML
    private TableColumn<Company, String> col_companyID, col_companyName, col_companyABN, col_companyUrl, col_companyAddress;

    @FXML
    private TableView<ProjectOwner> tbl_owner;

    @FXML
    private TableColumn<ProjectOwner, String> col_ownerID, col_ownerName, col_ownerRole, col_owenrEmail, col_ownerCompany;

    @FXML
    private TableView<Project> tbl_project;

    @FXML
    private TableColumn<Project, String> col_projectID, col_projectTitle, col_projectDescription, col_projectOwnerID, col_projectSkill;

    @FXML
    private TableView<Student> tbl_student;

    @FXML
    private TableColumn<Student, String> col_studentID, col_studentGrade, col_studentPersonality, col_studentConflict;

    @FXML
    private TableView<Team> tbl_team;

    @FXML
    private TableColumn<Team, String> col_teamID, col_teamMembers;
    
    @FXML
    private TableColumn<Team, Float> col_teamPreference, col_teamCompentancy, col_teamSkillGap;

    private Main mainApp;
    private ModelWrapper wrapper = ModelWrapper.getInstance();
    private DatabaseReadWrite dbWrite;

    // To return reference to itself
    public void setMainApp(Main mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleDashboardButtonAction(ActionEvent event)
    {
        if (event.getSource() == btn_company)
        {
            pn_company.toFront();
            loadCompanyTabel();
        }
        else if (event.getSource() == btn_project)
        {
            pn_project.toFront();
            loadProjectTabel();
        }
        else if (event.getSource() == btn_owner)
        {
            pn_owner.toFront();
            loadOwnerTabel();
        }
        else if (event.getSource() == btn_student)
        {
            pn_student.toFront();
            loadStudentTabel();
        }
        else if (event.getSource() == btn_team)
        {
            pn_team.toFront();
            loadTeamTabel();
        }
        else if (event.getSource() == btn_dbInitialize)
        {
            dbWrite = new DatabaseReadWrite();
        }
        else if (event.getSource() == btn_dbRead)
        {
            dbWrite.readProject();
            dbWrite.readStudent();
        }
        else if (event.getSource() == btn_dbWrite)
        {
            if (dbWrite.writeProject(wrapper.getProjectMap()) && dbWrite.writeStudent(wrapper.getStudentMap()))
            {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Success !!!");
                alert.setHeaderText("Data Successfully written to Project and Student table");
                alert.showAndWait();
            }
        }
    }

    private void loadCompanyTabel()
    {
        col_companyID.setCellValueFactory(new PropertyValueFactory<Company, String>("companyID"));
        col_companyName.setCellValueFactory(new PropertyValueFactory<Company, String>("companyName"));
        col_companyABN.setCellValueFactory(new PropertyValueFactory<Company, String>("abnNumber"));
        col_companyUrl.setCellValueFactory(new PropertyValueFactory<Company, String>("companyURL"));
        col_companyAddress.setCellValueFactory(new PropertyValueFactory<Company, String>("address"));
        tbl_company.setItems(FXCollections.observableArrayList(wrapper.getCompany().values()));
    }

    private void loadOwnerTabel()
    {
        col_ownerID.setCellValueFactory(new PropertyValueFactory<ProjectOwner, String>("ownerID"));
        col_ownerName.setCellValueFactory(cellData -> Bindings
                    .createStringBinding(() -> cellData.getValue().getFirstName().concat(" ").concat(cellData.getValue().getLastName())));
        col_ownerRole.setCellValueFactory(new PropertyValueFactory<ProjectOwner, String>("role"));
        col_owenrEmail.setCellValueFactory(new PropertyValueFactory<ProjectOwner, String>("emailID"));
        col_ownerCompany.setCellValueFactory(new PropertyValueFactory<ProjectOwner, String>("companyID"));
        tbl_owner.setItems(FXCollections.observableArrayList(wrapper.getProjectOwner().values()));
    }

    private void loadProjectTabel()
    {
        col_projectID.setCellValueFactory(new PropertyValueFactory<Project, String>("projectID"));
        col_projectTitle.setCellValueFactory(new PropertyValueFactory<Project, String>("title"));
        col_projectDescription.setCellValueFactory(new PropertyValueFactory<Project, String>("description"));
        col_projectOwnerID.setCellValueFactory(new PropertyValueFactory<Project, String>("ownerID"));
        col_projectSkill.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getRankingString()));
        tbl_project.setItems(FXCollections.observableArrayList(wrapper.getProject().values()));
    }
    
    private void loadStudentTabel()
    {
        col_studentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
        col_studentGrade.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getGradesString()));
        col_studentPersonality.setCellValueFactory(new PropertyValueFactory<Student, String>("personality"));
        col_studentConflict.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getConflictString()));
        tbl_student.setItems(FXCollections.observableArrayList(wrapper.getStudent().values()));
    }

    private void loadTeamTabel()
    {
        col_teamID.setCellValueFactory(new PropertyValueFactory<Team, String>("teamID"));
        col_teamMembers.setCellValueFactory(cellData -> Bindings.createStringBinding(() -> cellData.getValue().getTeamMembers().toString()));
        col_teamPreference.setCellValueFactory(new PropertyValueFactory<Team, Float>("preferncePercentage"));
        col_teamCompentancy.setCellValueFactory(new PropertyValueFactory<Team, Float>("avgStudentSkill"));
        col_teamSkillGap.setCellValueFactory(new PropertyValueFactory<Team, Float>("totalSkillGap"));
        tbl_team.setItems(FXCollections.observableArrayList(wrapper.getTeam().values()));
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
        loadCompanyTabel();
    }

    @FXML
    private void handleAddOwner(ActionEvent event)
    {
        ProjectOwner tempOwner = new ProjectOwner();
        if (!wrapper.getCompany().isEmpty())
        {
            boolean okClicked = mainApp.showAddProjectOwner(tempOwner, wrapper.getCompany().keySet());
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
        loadOwnerTabel();
    }

    @FXML
    private void handleAddProject(ActionEvent event)
    {
        Project tempProject = new Project();
        if (!wrapper.getProjectOwner().isEmpty())
        {
            boolean okClicked = mainApp.showAddProject(tempProject, wrapper.getProjectOwner().keySet());
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
        loadProjectTabel();
    }

    @FXML
    private void handleReadStudent(ActionEvent event)
    {
        wrapper.readAll();
        // wrapper.readStudent();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Data Loaded");
        alert.setHeaderText("Students prefernce and project data loaded");
        alert.showAndWait();
        loadStudentTabel();
    }

    @FXML
    private void handleAddTeam()
    {
        if (!(wrapper.getProject().isEmpty() && wrapper.getStudent().isEmpty()))
        {
            mainApp.showAddTeam(wrapper.getStudent().keySet(), wrapper.getProject().keySet());
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
        loadTeamTabel();
    }

    @FXML
    private void handleDataBaseRead()
    {

    }

    @FXML
    private void handleDataBaseWrite()
    {

    }

    @FXML
    private void initialize()
    {

    }

}
