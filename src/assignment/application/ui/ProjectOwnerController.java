package assignment.application.ui;

import java.util.Set;

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
    private ProjectOwner owner;

    private boolean okClicked = false;

    public void setDialogStage(Stage companyDialogStage)
    {
        this.projectOwnerStage = companyDialogStage;
    }

    public void setOwner(ProjectOwner owner)
    {
        this.owner = owner;
    }

    public boolean isOkClicked()
    {
        return okClicked;
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

            owner.setAll(firstName, lastName, ownerID, role, emailID, companyID);

            okClicked = true;
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
        return true;
    }

    public void setCompanyList(Set<String> companyList)
    {
        cmb_company.getItems().addAll(companyList);
    }

}