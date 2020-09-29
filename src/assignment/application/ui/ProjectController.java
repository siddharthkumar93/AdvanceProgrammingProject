package assignment.application.ui;

import java.util.Set;
import java.util.TreeMap;

import assignment.application.model.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProjectController
{
    @FXML
    private Button btn_ok, btn_cancel;

    @FXML
    private TextField txt_id, txt_title;
    
    @FXML
    private TextArea txt_description;

    @FXML
    private ComboBox<String> cmb_projectOwnerID = new ComboBox<String>();
    
    @FXML
    private ChoiceBox<Integer> chb_a = new ChoiceBox<Integer>();
    
    @FXML
    private ChoiceBox<Integer> chb_n = new ChoiceBox<Integer>();
    
    @FXML
    private ChoiceBox<Integer> chb_p = new ChoiceBox<Integer>();
    
    @FXML
    private ChoiceBox<Integer> chb_w = new ChoiceBox<Integer>();

    private Stage projectStage;
    private Project project;

    private boolean okClicked = false;

    public void setDialogStage(Stage projectStage)
    {
        this.projectStage = projectStage;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    public boolean isOkClicked()
    {
        return okClicked;
    }

    // Add button event handler
    @FXML
    private void handleOk()
    {
        String title, projectID, description, ownerID;
        TreeMap<String, Integer> ranking = new TreeMap<String, Integer>();

        if (validateInput())
        {
            projectID = "PR" + txt_id.getText();
            title = txt_title.getText();
            description  = txt_description.getText();
            ownerID = cmb_projectOwnerID.getValue();
            ranking.put("A", chb_a.getValue());
            ranking.put("N", chb_n.getValue());
            ranking.put("P", chb_p.getValue());
            ranking.put("W", chb_w.getValue());
            project.setAll(title, projectID, description, ownerID, ranking);

            okClicked = true;
            projectStage.close();
        }
    }

    // Cancel button event handler
    @FXML
    private void handleCancel()
    {
        projectStage.close();
    }


    private boolean validateInput()
    {
        return true;
    }

    public void setProjectOwnerList(Set<String> ownerList)
    {
        cmb_projectOwnerID.getItems().addAll(ownerList);
    }

}
