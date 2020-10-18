package assignment.application.ui;

import java.util.Set;
import java.util.TreeMap;

import assignment.application.GlobalVar;
import assignment.application.model.ModelWrapper;
import assignment.application.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProjectController
{
    private Stage projectStage;
    private ObservableList<Integer> ratingValue = FXCollections.observableArrayList(1, 2, 3, 4);

    @FXML
    private Button btn_ok, btn_cancel;

    @FXML
    private TextField txt_id, txt_title;

    @FXML
    private TextArea txt_description;

    @FXML
    private ComboBox<String> cmb_projectOwnerID = new ComboBox<String>();

    @FXML
    private ChoiceBox<Integer> chb_a = new ChoiceBox<Integer>(ratingValue);

    @FXML
    private ChoiceBox<Integer> chb_n = new ChoiceBox<Integer>(ratingValue);

    @FXML
    private ChoiceBox<Integer> chb_p = new ChoiceBox<Integer>(ratingValue);

    @FXML
    private ChoiceBox<Integer> chb_w = new ChoiceBox<Integer>(ratingValue);

    public void setDialogStage(Stage projectStage)
    {
        this.projectStage = projectStage;
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
            description = txt_description.getText();
            ownerID = cmb_projectOwnerID.getValue();
            ranking.put("A", chb_a.getValue());
            ranking.put("N", chb_n.getValue());
            ranking.put("P", chb_p.getValue());
            ranking.put("W", chb_w.getValue());

            ModelWrapper.getInstance().addProject(new Project(title, projectID, description, ownerID, ranking), true);

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
        if (txt_id.getText().equals(GlobalVar.emptyString) || txt_title.getText().equals(GlobalVar.emptyString)
                    || txt_description.getText().equals(GlobalVar.emptyString)
                    || cmb_projectOwnerID.getValue().equals(GlobalVar.emptyString) || chb_a.getValue() == null || chb_n.getValue() == null
                    || chb_p.getValue() == null || chb_w.getValue() == null)
        {
            GlobalVar.requiredFieldError(projectStage);
            return false;
        }
        else
        {
            try
            {
                Integer.parseInt(txt_id.getText());
            }
            catch (Exception exception)
            {
                GlobalVar.numberFieldError("Project ID", projectStage);
                return false;
            }
        }

        return true;
    }

    public void setProjectOwnerList(Set<String> ownerList)
    {
        cmb_projectOwnerID.getItems().addAll(ownerList);
    }

}
