package assignment.application.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.Set;

import assignment.application.model.ModelWrapper;
import assignment.application.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TeamController
{
    @FXML
    ComboBox<String> cmb_team1 = new ComboBox<String>();

    @FXML
    ComboBox<String> cmb_team2 = new ComboBox<String>();

    @FXML
    ComboBox<String> cmb_team3 = new ComboBox<String>();

    @FXML
    ComboBox<String> cmb_team4 = new ComboBox<String>();

    @FXML
    ComboBox<String> cmb_team5 = new ComboBox<String>();

    @FXML
    ComboBox<String> cmb_student = new ComboBox<String>();

    @FXML
    Button btn_add, btn_swap, btn_close, btn_update;

    @FXML
    AnchorPane apn_team;

    @FXML
    Label lbl_team1Student1, lbl_team1Student2, lbl_team1Student3, lbl_team1Student4;

    @FXML
    Label lbl_team2Student1, lbl_team2Student2, lbl_team2Student3, lbl_team2Student4;

    @FXML
    Label lbl_team3Student1, lbl_team3Student2, lbl_team3Student3, lbl_team3Student4;

    @FXML
    Label lbl_team4Student1, lbl_team4Student2, lbl_team4Student3, lbl_team4Student4;

    @FXML
    Label lbl_team5Student1, lbl_team5Student2, lbl_team5Student3, lbl_team5Student4;

    @FXML
    BarChart graph_prefernce, graph_compentancy, graph_skillGap;

    private Stage teamStage;
    private ModelWrapper wrapper;

    private ArrayList<String> selectedCheckBoxes = new ArrayList<String>();

    private ArrayList<Label> tempLabel = new ArrayList<Label>();

    public void setDialogStage(Stage teamStage)
    {
        this.teamStage = teamStage;
    }

    public void graph()
    {
        graph_prefernce.getData().clear();
        graph_compentancy.getData().clear();
        graph_skillGap.getData().clear();
       
        XYChart.Series seriesPrefernece = new XYChart.Series();
        XYChart.Series seriesCompentancy = new XYChart.Series();
        XYChart.Series seriesSkillGap = new XYChart.Series();
        for (Entry<String, Team> team : wrapper.getTeam().entrySet())
        {
            seriesPrefernece.getData().add(new XYChart.Data(team.getKey(), team.getValue().getPreferncePercentage()));
            seriesCompentancy.getData().add(new XYChart.Data(team.getKey(), team.getValue().getAverageStudentSkill()));
            seriesSkillGap.getData().add(new XYChart.Data(team.getKey(), team.getValue().getTotalSkillGap()));
        }
        graph_prefernce.getData().add(seriesPrefernece);
        graph_compentancy.getData().add(seriesCompentancy);
        graph_skillGap.getData().add(seriesSkillGap);
    }

    public void setWrapper(ModelWrapper wrapper)
    {
        this.wrapper = wrapper;
    }

    private ArrayList<String> getTeamMembers(String checkBox)
    {
        ArrayList<String> tempMembers = new ArrayList<String>();

        tempLabel.clear();
        getStudentLabelNodes(apn_team, checkBox, true);

        for (Label temp : tempLabel)
        {
            if (!temp.getText().isEmpty())
            {
                tempMembers.add(temp.getText());
            }
        }

        return tempMembers;
    }

    @FXML
    private void handleUpdate()
    {
        String teamName;
        ArrayList<String> teamMembers = new ArrayList<String>();

        ArrayList<ComboBox<String>> tempComboBoxList = new ArrayList<ComboBox<String>>(
                    Arrays.asList(cmb_team1, cmb_team2, cmb_team3, cmb_team4, cmb_team5));

        for (ComboBox<String> tempComboBox : tempComboBoxList)
        {
            if (tempComboBox.getValue() != (null))
            {
                for (Node component : tempComboBox.getParent().getChildrenUnmodifiable())
                {
                    if ((component instanceof Label) && (!((Label) component).getText().equals("")))
                    {
                        teamMembers.add(((Label) component).getText());
                    }
                }
                if (teamMembers.size() > 0)
                {
                    teamName = tempComboBox.getId().substring(4);
                    if (wrapper.teamPersonalityCheck(teamMembers))
                    {
                        wrapper.addTeam(new Team(tempComboBox.getValue(), teamName, teamMembers));
                    }
                    else
                    {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.initOwner(teamStage);
                        alert.setTitle("Team personality failed!!");
                        alert.setHeaderText("Atleast one type A personality required");
                        alert.setContentText("Please add team members to " + teamName + " with type A personality");
                        alert.showAndWait();
                    }
                }
            }
        }
        graph();
    }

    // Cancel button event handler
    @FXML
    private void handleClose()
    {
        teamStage.close();
    }

    @FXML
    private void handleAdd()
    {
        if (selectedCheckBoxes.size() != 1)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(teamStage);
            alert.setTitle("Incorrect Selection");
            alert.setHeaderText("Checkbox selection issue.");
            alert.setContentText("Only one checkbox selection is allowed when adding a student");
            alert.showAndWait();
        }
        else if (wrapper.confictCheck(getTeamMembers(selectedCheckBoxes.get(0)), cmb_student.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("A student conflict has been identified !!!");
            alert.setContentText("Do you wish to continue adding this student?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type.getText().equalsIgnoreCase("YES"))
                {
                    updateLabel(selectedCheckBoxes.get(0));
                }
            });
        }
        else
        {
            updateLabel(selectedCheckBoxes.get(0));
        }
    }

    @FXML
    private void handleSwap()
    {
        String Student1 = getStudentLabel(selectedCheckBoxes.get(0));
        String Student2 = getStudentLabel(selectedCheckBoxes.get(1));

        if (selectedCheckBoxes.size() != 2)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(teamStage);
            alert.setTitle("Incorrect Selections");
            alert.setHeaderText("Checkbox selection issue.");
            alert.setContentText("Only 2 checkbox selection are allowed when swapping");
            alert.showAndWait();
        }
        else if (selectedCheckBoxes.get(0).regionMatches(0, selectedCheckBoxes.get(1), 0, 10))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(teamStage);
            alert.setTitle("Incorrect Selections");
            alert.setHeaderText("Checkbox selection issue.");
            alert.setContentText("Checkbox selection of same team isn't allowed");
            alert.showAndWait();
        }
        else if (wrapper.confictCheck(getTeamMembers(selectedCheckBoxes.get(0)), Student2)
                    || wrapper.confictCheck(getTeamMembers(selectedCheckBoxes.get(1)), Student1))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("A student conflict has been identified !!!");
            alert.setContentText("Do you wish to continue swapping these student?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type.getText().equalsIgnoreCase("YES"))
                {
                    swapLabel(selectedCheckBoxes, Student1, Student2);
                }
            });
        }
        else
        {
            swapLabel(selectedCheckBoxes, Student1, Student2);
        }
    }

    @FXML
    public void handleSelectItem(ActionEvent event)
    {
        if (event.getSource() instanceof CheckBox)
        {
            CheckBox checkBox = (CheckBox) event.getSource();
            if (checkBox.isSelected())
            {
                selectedCheckBoxes.add(checkBox.getId());
            }
            else
            {
                selectedCheckBoxes.remove(checkBox.getId());
            }
            btn_swap.setDisable(selectedCheckBoxes.size() < 2);
        }
    }

    @FXML
    public void handleStudentComboBox(ActionEvent event)
    {
        if (event.getSource() instanceof ComboBox)
        {
            ComboBox<String> tempCombox = (ComboBox) event.getSource();
            if (tempCombox.getValue() != null)
            {
                btn_add.setDisable(false);
            }
            else
            {
                btn_add.setDisable(true);
            }

        }
    }

    public void setProjectList(Set<String> projectList)
    {
        cmb_team1.getItems().addAll(projectList);
        cmb_team2.getItems().addAll(projectList);
        cmb_team3.getItems().addAll(projectList);
        cmb_team4.getItems().addAll(projectList);
        cmb_team5.getItems().addAll(projectList);
    }

    public void setStudentList(Set<String> studentList)
    {
        cmb_student.getItems().addAll(studentList);
    }

    private void updateLabel(String checkBox)
    {
        String value = (getStudentLabel(checkBox) != null) ? value = getStudentLabel(checkBox) : null;

        if (!value.isEmpty())
        {
            // https://tagmycode.com/snippet/5207/yes-no-cancel-dialog-in-javafx
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("A Student already exis at this slot.!!!");
            alert.setContentText("Do you wish to continue?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type.getText().equalsIgnoreCase("YES"))
                {
                    cmb_student.getItems().add(getStudentLabel(checkBox));
                    setStudentLabel(cmb_student.getValue(), checkBox);
                    cmb_student.getItems().remove(cmb_student.getValue());
                    cmb_student.setValue(null);
                }
            });
        }
        else
        {
            setStudentLabel(cmb_student.getValue(), checkBox);
            cmb_student.getItems().remove(cmb_student.getValue());
            cmb_student.setValue(null);
        }

    }

    private void swapLabel(ArrayList<String> checkBox, String Student1, String Student2)
    {
        setStudentLabel(Student2, checkBox.get(0));
        setStudentLabel(Student1, checkBox.get(1));

    }

    private void setStudentLabel(String value, String checkBox)
    {
        tempLabel.clear();
        getStudentLabelNodes(apn_team, checkBox, false);
        tempLabel.get(0).setText(value);
    }

    private String getStudentLabel(String checkBox)
    {
        tempLabel.clear();
        getStudentLabelNodes(apn_team, checkBox, false);
        return tempLabel.get(0).getText();
    }

    // Reference :
    // https://stackoverflow.com/questions/13011023/javafx-looping-over-scenegraph-controls
    private void getStudentLabelNodes(Pane parent, String checkBox, boolean getAll)
    {
        for (Node component : parent.getChildren())
        {
            if (component instanceof Pane)
            {
                // if the component is a container, scan its children
                getStudentLabelNodes((Pane) component, checkBox, getAll);
            }
            else if ((component instanceof Label) && (component.getId() != null))
            {

                if (getAll && component.getId().substring(4, 9).compareTo(checkBox.substring(4, 9)) == 0)
                {
                    tempLabel.add((Label) component);
                }
                else if (component.getId().substring(4).compareTo(checkBox.substring(4)) == 0)
                {
                    tempLabel.add((Label) component);
                }

            }
        }
    }
}
