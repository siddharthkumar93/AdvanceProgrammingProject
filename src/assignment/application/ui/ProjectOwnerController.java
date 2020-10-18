package assignment.application.ui;

import java.util.Set;

import assignment.application.GlobalVar;
import assignment.application.model.ModelWrapper;
import assignment.application.model.ProjectOwner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProjectOwnerController
{
    @FXML
    private Button btn_ok, btn_cancel;

    @FXML
    private TextField txt_id, txt_firstName, txt_lastName, txt_role, txt_emailID;

    @FXML
    private ComboBox<String> cmb_company = new ComboBox<String>();

    private Stage projectOwnerStage;

    public void setDialogStage(Stage companyDialogStage)
    {
        this.projectOwnerStage = companyDialogStage;
    }

    // Add button event handler
    @FXML
    private void handleOk()
    {
        String firstName, lastName, ownerID, role, emailID, companyID;

        if (validateInput())
        {
            ownerID = "PR" + txt_id.getText();
            firstName = txt_firstName.getText();
            lastName = txt_lastName.getText();
            role = txt_role.getText();
            emailID = txt_emailID.getText();
            companyID = cmb_company.getValue();

            ModelWrapper.getInstance().addProjectOwner(new ProjectOwner(firstName, lastName, ownerID, role, emailID, companyID), true);
            projectOwnerStage.close();
        }
    }

    // Cancel button event handler
    @FXML
    private void handleCancel()
    {
        projectOwnerStage.close();
    }

    private boolean validateInput()
    {
        if (txt_id.getText().equals(GlobalVar.emptyString) || txt_firstName.getText().equals(GlobalVar.emptyString)
                    || txt_lastName.getText().equals(GlobalVar.emptyString) || txt_role.getText().equals(GlobalVar.emptyString)
                    || txt_emailID.getText().equals(GlobalVar.emptyString) || cmb_company.getValue() == null)
        {
            GlobalVar.requiredFieldError(projectOwnerStage);
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
                GlobalVar.numberFieldError("Project Owner ID", projectOwnerStage);
                return false;
            }
        }

        return true;
    }

    public void setCompanyList(Set<String> companyList)
    {
        cmb_company.getItems().addAll(companyList);
    }

}
