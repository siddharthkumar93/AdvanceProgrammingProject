package assignment.application.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import assignment.application.GlobalVar;
import assignment.application.model.ModelWrapper;
import assignment.application.model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PreferenceController
{
    private Stage preferenceStage;
    private Student tempStudent;

    private String prefence1, prefence2, prefence3, prefence4;

    @FXML
    private ComboBox<String> cmb_pref1 = new ComboBox<String>();

    @FXML
    private ComboBox<String> cmb_pref2 = new ComboBox<String>();

    @FXML
    private ComboBox<String> cmb_pref3 = new ComboBox<String>();

    @FXML
    private ComboBox<String> cmb_pref4 = new ComboBox<String>();

    @FXML
    private Label lbl_studentID;

    @FXML
    private void handleSave()
    {
        Map<String, Integer> preference = new HashMap<String, Integer>();

        prefence1 = cmb_pref1.getValue() != null ? cmb_pref1.getValue() : GlobalVar.emptyString;
        prefence2 = cmb_pref2.getValue() != null ? cmb_pref2.getValue() : GlobalVar.emptyString;
        prefence3 = cmb_pref3.getValue() != null ? cmb_pref2.getValue() : GlobalVar.emptyString;
        prefence4 = cmb_pref4.getValue() != null ? cmb_pref2.getValue() : GlobalVar.emptyString;

        if (validateInput())
        {
            preference.put(prefence1, 1);
            preference.put(prefence2, 2);
            preference.put(prefence3, 3);
            preference.put(prefence4, 4);

            tempStudent.setPreference(preference);
            ModelWrapper.getInstance().updateStudentDetails(tempStudent, true);
            preferenceStage.close();
        }
    }

    @FXML
    private void handleCancel()
    {
        preferenceStage.close();
    }

    private boolean validateInput()
    {
        if (prefence1.equals(GlobalVar.emptyString) && prefence2.equals(GlobalVar.emptyString) && prefence3.equals(GlobalVar.emptyString)
                    && prefence4.equals(GlobalVar.emptyString))
        {
            GlobalVar.requiredFeildError(preferenceStage);
            return false;
        }
        return true;
    }

    public void setDialogStage(Stage dialogStage)
    {
        this.preferenceStage = dialogStage;
    }

    public void initializePreference(Set<String> projectList, Student studentObj)
    {
        this.tempStudent = studentObj;
        cmb_pref1.getItems().addAll(projectList);
        cmb_pref2.getItems().addAll(projectList);
        cmb_pref3.getItems().addAll(projectList);
        cmb_pref4.getItems().addAll(projectList);
    }

}
