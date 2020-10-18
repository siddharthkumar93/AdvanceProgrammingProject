package assignment.application.ui;

import java.util.ArrayList;
import java.util.Set;

import assignment.application.GlobalVar;
import assignment.application.model.ModelWrapper;
import assignment.application.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentController
{
    private Stage studentStage;
    private Student tempStudent;
    private ObservableList<Character> personalityValue = FXCollections.observableArrayList('A', 'B', 'C', 'D');

    @FXML
    private TextField txt_p, txt_n, txt_a, txt_w, txt_studentID;

    @FXML
    private Button btn_save, btn_cancel;

    @FXML
    private ComboBox<Character> cmb_personality = new ComboBox<Character>();

    @FXML
    private ComboBox<String> cmb_conflict1 = new ComboBox<String>();

    @FXML
    private ComboBox<String> cmb_conflict2 = new ComboBox<String>();

    @FXML
    private void handleSave()
    {
        String conflict1 = cmb_conflict1.getValue() != null ? cmb_conflict1.getValue() : GlobalVar.emptyString;
        String conflict2 = cmb_conflict2.getValue() != null ? cmb_conflict2.getValue() : GlobalVar.emptyString;

        ArrayList<String> tempConflict = new ArrayList<String>();
        tempConflict.add(conflict1);
        tempConflict.add(conflict2);
        
        if (validateInput(tempConflict))
        {
            tempStudent.setPersonality(cmb_personality.getValue());
            tempStudent.setConflict(tempConflict);
            
            ModelWrapper.getInstance().updateStudentDetails(tempStudent, false);
        }
        studentStage.close();
    }

    private boolean validateInput(ArrayList<String> tempConflict)
    {
        if (cmb_personality.getValue() == null)
        {
            return false;
        }
        else if (tempConflict.get(0).equals(tempConflict.get(1)))
        {
            return false;
        }
        return true;
    }

    @FXML
    private void handleCancel()
    {
        studentStage.close();
    }

    // Method to show current selected student's details
    private void updateStudentDetails()
    {
        cmb_conflict1.getSelectionModel().select(tempStudent.getConflict().get(0));
        cmb_conflict2.getSelectionModel().select(tempStudent.getConflict().get(1));
        cmb_personality.getSelectionModel().select(tempStudent.getPersonality());

        txt_studentID.setText(tempStudent.getStudentID());
        txt_p.setText(tempStudent.getGrades().get("P").toString());
        txt_n.setText(tempStudent.getGrades().get("N").toString());
        txt_a.setText(tempStudent.getGrades().get("A").toString());
        txt_w.setText(tempStudent.getGrades().get("W").toString());
    }

    // Method to set the student window as current staging area
    public void setDialogStage(Stage studentStage)
    {
        this.studentStage = studentStage;
    }

    // Initializing the combobox with students id and loading student details
    public void initializeStudent(Set<String> studentList, Student studentObj)
    {
        tempStudent = studentObj;
        studentList.remove(studentObj.getStudentID());
        
        cmb_conflict1.getItems().addAll(studentList);
        cmb_conflict2.getItems().addAll(studentList);
        cmb_personality.getItems().addAll(personalityValue);
        
        updateStudentDetails();
    }

}
