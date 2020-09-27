package assignment.application.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DashboardController
{
    @FXML
    private Pane pn_company, pn_project, pn_owner, pn_student, pn_team;

    @FXML
    private Button btn_company, btn_project, btn_owner, btn_student, btn_team;
    
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
}
