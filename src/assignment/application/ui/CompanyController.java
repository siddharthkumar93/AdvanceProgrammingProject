package assignment.application.ui;

import assignment.application.model.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompanyController
{
    @FXML
    private Button btn_ok, btn_cancel;

    @FXML
    private TextField txt_id, txt_name, txt_abn, txt_url, txt_address;

    private Stage companyStage;
    private Company company;
    private boolean okClicked = false;

    public void setDialogStage(Stage companyDialogStage)
    {
        this.companyStage = companyDialogStage;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }

    public boolean isOkClicked()
    {
        return okClicked;
    }

    // Add button event handler
    @FXML
    private void handleOk()
    {
        String companyName, address, companyURL, companyID, abnNumber;

        if (validateInput())
        {
            companyID = "C" + txt_id.getText();
            companyName = txt_name.getText();
            abnNumber = txt_abn.getText();
            companyURL = txt_url.getText();
            address = txt_address.getText();
            company.setAll(companyID, companyName, abnNumber, companyURL, address);
            
            okClicked = true;
            companyStage.close();
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(companyStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Correct the fields");

            alert.showAndWait();

        }
    }

    // Cancel button event handler
    @FXML
    private void handleCancel()
    {
        companyStage.close();
    }

    // Initializes the controller class.
    @FXML
    private void initialize()
    {
    }

    private boolean validateInput()
    {
        return true;
    }

}
