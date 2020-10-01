package assignment.application.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import assignment.application.model.Project;
import assignment.application.model.Student;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatabaseReadWrite
{
    private String dbPath = "jdbc:sqlite:D:/Workplace_Java/Final_Assignment/TeamApplication.db";

    public DatabaseReadWrite()
    {
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();

                String Student = "CREATE TABLE Student" + "(StudentID varchar(30) PRIMARY KEY NOT NULL, " + "Grades varchar(30),"
                            + "Personality varchar(5)," + "Confict varchar(30)," + "Preference varchar(30))";
                statement.executeUpdate("DROP TABLE IF EXISTS Student");
                statement.executeUpdate(Student);

                String Project = "CREATE TABLE Project" + "(ProjectID varchar(30) PRIMARY KEY NOT NULL, " + "Title varchar(30),"
                            + "Description varchar(250)," + "OwnerID varchar(30)," + "Ranking varchar(30))";
                statement.executeUpdate("DROP TABLE IF EXISTS Project");
                statement.executeUpdate(Project);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public boolean writeProject(Map<String, Project> allProject)
    {
        boolean flag = false;
        Project tempProject;
        String insertString;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();

                for (Map.Entry<String, Project> project : allProject.entrySet())
                {
                    tempProject = project.getValue();
                    insertString = "INSERT INTO Project VALUES ( '" + tempProject.getProjectID() + "', '" + tempProject.getTitle() + "', '"
                                + tempProject.getDescription() + "', '" + tempProject.getOwnerID() + "', '" + tempProject.getRankingString()
                                + "' )";
                    statement.executeUpdate(insertString);
                }
            }
            flag = true;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean writeStudent(Map<String, Student> allStudent)
    {
        boolean flag = false;
        Student tempStudent;
        String insertString;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();

                for (Map.Entry<String, Student> project : allStudent.entrySet())
                {
                    tempStudent = project.getValue();
                    insertString = "INSERT INTO Student VALUES ( '" + tempStudent.getStudentID() + "', '" + tempStudent.getGradesString()
                                + "', '" + tempStudent.getPersonality() + "', '" + tempStudent.getConflictString() + "', '"
                                + tempStudent.getPreference() + "' )";
                    statement.executeUpdate(insertString);
                }
            }
            flag = true;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public void readProject()
    {
        String tempOutput = "";
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Project;");
                while ( resultSet.next() ) {
                    tempOutput +=   resultSet.getString(1) + " " +
                                    resultSet.getString(2) + " " +
                                    resultSet.getString(3) + " " +
                                    resultSet.getString(4) + " " +
                                    resultSet.getString(5) + "\n";
                }
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success !!!");
                alert.setHeaderText("Data Successfully retrived from Project table");
                alert.setContentText(tempOutput);
                alert.showAndWait();
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void readStudent()
    {
        String tempOutput = "";
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Student;");
                while ( resultSet.next() ) {
                    tempOutput +=   resultSet.getString(1) + " " +
                                    resultSet.getString(2) + " " +
                                    resultSet.getString(3) + " " +
                                    resultSet.getString(4) + " " +
                                    resultSet.getString(5) + "\n";
                }
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success !!!");
                alert.setHeaderText("Data Successfully retrived from Student table");
                alert.setContentText(tempOutput);
                alert.showAndWait();
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
