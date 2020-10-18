package assignment.application.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import assignment.application.GlobalVar;
import assignment.application.model.Company;
import assignment.application.model.ModelWrapper;
import assignment.application.model.Project;
import assignment.application.model.ProjectOwner;
import assignment.application.model.Student;
import assignment.application.model.Team;

public class DatabaseReadWrite
{
    // Database path
    private String dbPath = "jdbc:sqlite:D:/Workplace_Java/Final_Assignment/TeamApplication.db";

    private ModelWrapper wrapper = ModelWrapper.getInstance();

    // Class constructor
    public DatabaseReadWrite()
    {
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();

                // Creating tables in database
                statement.executeUpdate(GlobalVar.createCompanyString);
                statement.executeUpdate(GlobalVar.createProjectOwnerString);
                statement.executeUpdate(GlobalVar.createProjectString);
                statement.executeUpdate(GlobalVar.createStudentString);
                statement.executeUpdate(GlobalVar.createTeamString);
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    // Writing companies information to database
    public boolean writeCompany(Map<String, Company> allCompany)
    {
        boolean flag = false;
        Company tempCompany;
        String insertString;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();

                for (Map.Entry<String, Company> company : allCompany.entrySet())
                {
                    tempCompany = company.getValue();
                    insertString = "INSERT INTO Company VALUES ( '" + tempCompany.getCompanyID() + "', '" + tempCompany.getCompanyName()
                                + "', '" + tempCompany.getAbnNumber() + "', '" + tempCompany.getCompanyURL() + "', '"
                                + tempCompany.getAddress() + "' )";
                    statement.executeUpdate(insertString);
                }
                flag = true;
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
        return flag;
    }

    // Writing projectOwner information to database
    public boolean writeProjectOwner(Map<String, ProjectOwner> allOwner)
    {
        boolean flag = false;
        ProjectOwner tempOwner;
        String insertString;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();

                for (Map.Entry<String, ProjectOwner> owner : allOwner.entrySet())
                {
                    tempOwner = owner.getValue();
                    insertString = "INSERT INTO ProjectOwner VALUES ( '" + tempOwner.getOwnerID() + "', '" + tempOwner.getFirstName()
                                + "', '" + tempOwner.getLastName() + "', '" + tempOwner.getRole() + "', '" + tempOwner.getEmailID() + "', '"
                                + tempOwner.getCompanyID() + "' )";
                    statement.executeUpdate(insertString);
                }
                flag = true;
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
        return flag;
    }

    // Writing project information to database
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
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
        return flag;
    }

    // Writing Student information to database
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
                                + tempStudent.getPreferenceString() + "' )";
                    statement.executeUpdate(insertString);
                }
            }
            flag = true;
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
        return flag;
    }

    // Writing Team information to database
    public boolean writeTeam(Map<String, Team> allTeam)
    {
        boolean flag = false;
        Team tempTeam;
        String insertString;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();

                for (Map.Entry<String, Team> team : allTeam.entrySet())
                {
                    tempTeam = team.getValue();
                    insertString = "INSERT INTO Team VALUES ( '" + tempTeam.getTeamID() + "', '" + tempTeam.getTeamName() + "', '"
                                + tempTeam.getTeamMembersString() + "' )";
                    statement.executeUpdate(insertString);
                }
            }
            flag = true;
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
        return flag;
    }

    // Method to read companies from database
    public void readCompany()
    {
        Company tempComapny;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Company;");
                while (resultSet.next())
                {
                    tempComapny = new Company(resultSet.getString(2), resultSet.getString(1), resultSet.getString(3),
                                resultSet.getString(4), resultSet.getString(5));
                    wrapper.addCompany(tempComapny);
                }
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    // Method to read project owners from database
    public void readProjectOwner()
    {
        ProjectOwner tempOwner;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM ProjectOnwer;");
                while (resultSet.next())
                {
                    tempOwner = new ProjectOwner(resultSet.getString(3), resultSet.getString(1), resultSet.getString(2),
                                resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
                    wrapper.addProjectOwner(tempOwner);
                }
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    // Method to read projects from database
    public void readProject()
    {
        Project tempProject;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Project;");
                while (resultSet.next())
                {
                    tempProject = new Project(resultSet.getString(2), resultSet.getString(1), resultSet.getString(3),
                                resultSet.getString(4), resultSet.getString(5));
                    wrapper.addProject(tempProject);
                }
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    // Method to read student details from database
    public void readStudent()
    {
        Student tempStudent;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Student;");
                while (resultSet.next())
                {
                    tempStudent = new Student(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3).charAt(0),
                                resultSet.getString(4));
                    tempStudent.setPreferenceString(resultSet.getString(5));
                    wrapper.addStudent(tempStudent);
                }
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public void readTeam()
    {
        Team tempTeam;
        try (Connection connection = DriverManager.getConnection(dbPath))
        {
            if (connection != null)
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Team;");
                while (resultSet.next())
                {
                    tempTeam = new Team(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                    wrapper.addTeam(tempTeam);
                }
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
    }
}
