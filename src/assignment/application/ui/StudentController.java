package assignment.application.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StudentController
{
    private Stage studentStage;
    
    @FXML
    private Label lbl_p, lbl_n, lbl_a, lbl_w;
    
    @FXML
    private Button btn_save, btn_cancel;
    
    @FXML
    private ComboBox<String> cmb_personality = new ComboBox<String>();
    
    @FXML
    private ComboBox<String> cmb_conflict1 = new ComboBox<String>();
    
    @FXML
    private ComboBox<String> cmb_conflict2 = new ComboBox<String>();
    

    public void setDialogStage(Stage studentStage)
    {
        this.studentStage = studentStage;
    }
    
    @FXML
    private void handleSave()
    {
        char personality;
        String conflict1 = "";
        String conflict2 = "";

        if (validateInput())
        {
            
        }
    }
    
    private boolean validateInput()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @FXML
    private void handleCancel()
    {
    
    }
    
}
