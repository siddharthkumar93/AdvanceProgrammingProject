package assignment.application.ui;

import java.util.Set;

import assignment.application.model.Team;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class TeamController
{
    @FXML
    ComboBox<String> cmb_team1 = new ComboBox<String>();
    
    @FXML
    ComboBox<String> cmb_team2 = new ComboBox<String>();
    
    @FXML
    ComboBox<String> cmb_team3 = new ComboBox<String>();
    
    @FXML
    ComboBox<String> cmb_team4 = new ComboBox<String>();
    
    @FXML
    ComboBox<String> cmb_team5 = new ComboBox<String>();
    
    @FXML
    ComboBox<String> cmb_student = new ComboBox<String>();
    
    @FXML
    Button btn_add, btn_swap,btn_cancel,btn_save;
    
    
    
    private Stage teamStage;
    private Team team;

    private boolean okClicked = false;

    public void setDialogStage(Stage teamStage)
    {
        this.teamStage = teamStage;
    }

    public void setTeam(Team team)
    {
        this.team = team;
    }

    public boolean isOkClicked()
    {
        return okClicked;
    }

    @FXML
    private void handleOk()
    {
        String title, projectID, description, ownerID;
        
        if (validateInput())
        {            
            okClicked = true;
            teamStage.close();
        }
    }

    // Cancel button event handler
    @FXML
    private void handleCancel()
    {
        teamStage.close();
    }


    private boolean validateInput()
    {
        return true;
    }

    public void setProjectList(Set<String> projectList)
    {
        cmb_team1.getItems().addAll(projectList);
        cmb_team2.getItems().addAll(projectList);
        cmb_team3.getItems().addAll(projectList);
        cmb_team4.getItems().addAll(projectList);
        cmb_team5.getItems().addAll(projectList);
    }

    public void setStudentList(Set<String> studentList)
    {
        cmb_student.getItems().addAll(studentList);        
    }
}
