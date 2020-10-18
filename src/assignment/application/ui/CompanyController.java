package assignment.application.ui;

import assignment.application.GlobalVar;
import assignment.application.model.Company;
import assignment.application.model.ModelWrapper;
import javafx.fxml.FXML;
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

    public void setDialogStage(Stage companyDialogStage)
    {
        this.companyStage = companyDialogStage;
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

            ModelWrapper.getInstance().addCompany(new Company(companyID, companyName, abnNumber, companyURL, address), true);

            companyStage.close();
        }
    }

    // Cancel button event handler
    @FXML
    private void handleCancel()
    {
        companyStage.close();
    }

    private boolean validateInput()
    {

        if (txt_id.getText().equals(GlobalVar.emptyString) || txt_name.getText().equals(GlobalVar.emptyString)
                    || txt_abn.getText().equals(GlobalVar.emptyString) || txt_url.getText().equals(GlobalVar.emptyString)
                    || txt_address.getText().equals(GlobalVar.emptyString))
        {
            GlobalVar.requiredFieldError(companyStage);
            return false;
        }
        // issue with java regex hence using this method
        else
        {
            try
            {
                Integer.parseInt(txt_id.getText());
            }
            catch (Exception exception)
            {
                GlobalVar.numberFieldError("Company ID", companyStage);
                return false;
            }
        }

        return true;
    }

}
