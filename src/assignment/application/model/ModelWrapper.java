package assignment.application.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import assignment.application.GlobalVar;
import assignment.application.data.FileReadWrite;

public class ModelWrapper
{
    // Scanner and file read object declearatrion
    Scanner input = new Scanner(System.in);
    FileReadWrite data = new FileReadWrite();

    // class private treemap
    private TreeMap<String, Company> company;
    private TreeMap<String, Project> project;
    private TreeMap<String, ProjectOwner> projectOwner;
    private TreeMap<String, Student> student;
    private TreeMap<String, Team> team;

    private static ModelWrapper instance = null;

    // Class constructor
    private ModelWrapper()
    {
        student = new TreeMap<String, Student>();
        company = new TreeMap<String, Company>();
        project = new TreeMap<String, Project>();
        projectOwner = new TreeMap<String, ProjectOwner>();
        team = new TreeMap<String, Team>();
    }

    // Singleton pattern implementation
    // Method to pass instance of this class
    public static ModelWrapper getInstance()
    {
        if (instance == null)
        {
            instance = new ModelWrapper();
        }
        return instance;
    }

    // Method to add companies to company map
    public void addCompany(Company objCompany, boolean write)
    {
        company.put(objCompany.getCompanyID(), objCompany);
        if (write)
        {
            data.writeCompany(company);
        }
    }

    // Method to add project owners to projectOwner map
    public void addProjectOwner(ProjectOwner owner, boolean write)
    {
        projectOwner.put(owner.getOwnerID(), owner);
        if (write)
        {
            data.writeOwners(projectOwner);
        }
    }

    // Method to add projects to project map and trigger project.txt generation
    public void addProject(Project objProject, boolean write)
    {
        project.put(objProject.getProjectID(), objProject);
        if (write)
        {
            data.writeProject(project);
        }
    }

    // Method to add Students to student map
    public void addStudent(Student objStudent)
    {
        student.put(objStudent.getStudentID(), objStudent);
    }

    // Method to add teams to teamMap and calculate team metrics
    public void addTeam(Team objTeam)
    {
        team.put(objTeam.getTeamID(), objTeam);
        team.get(objTeam.getTeamID()).setAvgStudentSkillMap(student);
        team.get(objTeam.getTeamID()).setPreferncePercentage(student);
        team.get(objTeam.getTeamID()).setTotalSkillGap(project.get(objTeam.getTeamID()));
    }

    // Getter for class treeMaps
    public TreeMap<String, Team> getTeam()
    {
        return team;
    }

    public TreeMap<String, Company> getCompany()
    {
        return company;
    }

    public TreeMap<String, ProjectOwner> getProjectOwner()
    {
        return projectOwner;
    }

    public TreeMap<String, Project> getProject()
    {
        return project;
    }

    public TreeMap<String, Student> getStudent()
    {
        return student;
    }

    // Function to load student and project details form file
    public void readAll(boolean testMode)
    {
        readStudentInfo(testMode);
        readProject(testMode);
        readStudentPreference(testMode);
    }

    // Function to load project information form file to Project Map
    public void readProject(boolean testMode)
    {
        String[][] content;
        if (testMode)
        {
            content = data.readFile("testProjects.txt");
        }
        else
        {
            content = data.readFile("projects.txt");
        }

        for (int i = 0; i < content.length; i++)
        {
            project.put(content[i][1].strip(), new Project(content[i][0], content[i][1], content[i][2], content[i][3], content[i][4]));
        }
    }

    // Function to load Student basic information form file to Student Map
    public void readStudent()
    {
        String id;
        String[][] content = data.readFile("students.txt");

        for (int i = 0; i < content.length; i++)
        {
            id = content[i][0].trim().toUpperCase();
            student.put(id, new Student(id, content[i][1], ' ', GlobalVar.emptyString));
        }

    }

    // Function to load Student basic information and conflicts form file to Student
    // Map
    public void readStudentInfo(boolean testMode)
    {
        char personality;
        String id;
        String[][] content;

        if (testMode)
        {
            content = data.readFile("testStudentinfo.txt");
        }
        else
        {
            content = data.readFile("studentinfo.txt");
        }

        for (int i = 0; i < content.length; i++)
        {
            personality = content[i][2].strip().charAt(0);
            id = content[i][0].trim().toUpperCase();
            student.put(id, new Student(id, content[i][1], personality, content[i][3]));
        }
    }

    // Function to read student preferred team
    public void readStudentPreference(boolean testMode)
    {
        String id;
        String[][] content;

        if (testMode)
        {
            content = data.readFile("testPreferences.txt");
        }
        else
        {
            content = data.readFile("preferences.txt");
        }

        for (int i = 0; i < content.length; i++)
        {
            id = content[i][0].trim().toUpperCase();
            student.get(id).setPreferenceString(content[i][1].strip());
        }
    }

    // Method to check for conflicts between team members
    public boolean confictCheck(ArrayList<String> tempMember, String studentID)
    {
        boolean confictFlag = false;

        for (String memberID : tempMember)
        {
            if (memberID != null)
            {
                for (String confict : student.get(memberID).getConflict())
                {
                    if (confict.equalsIgnoreCase(studentID))
                    {
                        confictFlag = true;
                    }
                }

                for (String confict : student.get(studentID).getConflict())
                {
                    if (confict.equalsIgnoreCase(memberID))
                    {
                        confictFlag = true;
                    }
                }
            }
        }
        return confictFlag;
    }

    // Method to check that at-least on of the team member has personality A
    public boolean teamPersonalityCheck(ArrayList<String> teamMembers)
    {
        boolean flag = false;

        for (String studentID : teamMembers)
        {
            if (student.get(studentID).getPersonality() == 'A')
            {
                flag = true;
            }
        }
        return flag;
    }

    // Function to update students preferences
    public void updateStudentDetails(Student studentObj, boolean preferenceFlag)
    {
        student.put(studentObj.getStudentID(), studentObj);

        if (preferenceFlag)
        {
            data.writeStudent(student, "preferences.txt");
        }
        else
        {
            data.writeStudent(student, "studentinfo.txt");
        }
    }

    // Method to short list projects based on their popularity
    // Reference :
    // https://stackoverflow.com/questions/29567575/sort-map-by-value-using-lambdas-and-streams
    public void shortlistProjects()
    {
        SortedMap<String, Integer> tempMap = new TreeMap<String, Integer>();
        SortedSet<Map.Entry<String, Integer>> sortedset = new TreeSet<Map.Entry<String, Integer>>(
                    new Comparator<Map.Entry<String, Integer>>()
                    {
                        @Override
                        public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2)
                        {
                            return e1.getValue().compareTo(e2.getValue());
                        }
                    });

        String key;

        for (Map.Entry<String, Student> entry : student.entrySet())
        {
            Map<String, Integer> temp = new HashMap<String, Integer>(entry.getValue().getPreference());

            if (temp != null && temp.size() > 0)
            {
                for (Map.Entry<String, Integer> entry2 : temp.entrySet())
                {
                    key = entry2.getKey();
                    if (!tempMap.containsKey(key))
                    {
                        tempMap.put(key, entry2.getValue());
                    }
                    else
                    {
                        tempMap.put(key, tempMap.get(key) + entry2.getValue());
                    }
                    key = "";
                }
            }
        }

        sortedset.addAll(tempMap.entrySet());
        System.out.println(sortedset);
    }

    // Function to calculate team metrics standard deviation
    public ArrayList<Double> getTeamMetricSD()
    {
        ArrayList<Double> teamMetricSD = new ArrayList<Double>();
        float prefPercentageAvg = 0f;
        float skillCompentancyAvg = 0f;
        float skillGapAvg = 0f;

        float totalPrefPercentage = 0f;
        float totalSkillCompentancy = 0f;
        float totalSkillGap = 0f;

        double prefPercentageSD = 0d;
        double skillCompentancySD = 0d;
        double skillGapSD = 0d;

        for (Team teamEntry : team.values())
        {
            prefPercentageAvg += (teamEntry.getPreferncePercentage() / team.size());
            skillCompentancyAvg += (teamEntry.getAverageStudentSkill() / team.size());
            skillGapAvg += (teamEntry.getTotalSkillGap() / team.size());
        }

        for (Team teamEntry : team.values())
        {
            totalPrefPercentage += Math.pow((teamEntry.getPreferncePercentage() - prefPercentageAvg), 2);
            totalSkillCompentancy += Math.pow((teamEntry.getAverageStudentSkill() - skillCompentancyAvg), 2);
            totalSkillGap += Math.pow((teamEntry.getTotalSkillGap() - skillGapAvg), 2);
        }

        prefPercentageSD = Math.sqrt(totalPrefPercentage / team.size());
        skillCompentancySD = Math.sqrt(totalSkillCompentancy / team.size());
        skillGapSD = Math.sqrt(totalSkillGap / team.size());

        teamMetricSD.add(prefPercentageSD);
        teamMetricSD.add(skillCompentancySD);
        teamMetricSD.add(skillGapSD);

        return teamMetricSD;
    }

    // Method to generate heuristic suggestions
    public ArrayList<String> getSwapSuggestions()
    {
        ArrayList<String> swapMembers = new ArrayList<String>();
        List<Team> teamSet = new ArrayList<Team>(team.values());

        int startIndex = 0;
        int endIndex = teamSet.size() - 1;

        // Sorting the team based on skill gap
        teamSet.sort((Team t1, Team t2) -> Float.compare(t1.getTotalSkillGap(), t2.getTotalSkillGap()));

        while (swapMembers.size() == 0)
        {
            // Starting the swap member check at from the best and worst team and move till
            // the middle
            swapMembers = findSwapMembers(teamSet.get(startIndex).getTeamID(), teamSet.get(endIndex).getTeamID());
            startIndex++;
            endIndex--;
            if (startIndex == endIndex || startIndex > endIndex)
            {
                break;
            }
        }

        if (swapMembers.size() == 0)
        {
            swapMembers.add("No suggestions found");
        }

        return swapMembers;
    }

    // Method to find swappable members between two teams
    private ArrayList<String> findSwapMembers(String team1, String team2)
    {
        float team1CurrentSkillGap = team.get(team1).getTotalSkillGap();
        float team2CurrentSkillGap = team.get(team2).getTotalSkillGap();

        ArrayList<String> team1Members = team.get(team1).getTeamMembers();
        ArrayList<String> team2Members = team.get(team2).getTeamMembers();
        ArrayList<String> swapMemberSuggestions = new ArrayList<String>();

        String temp;

        for (int i = 0; i < team1Members.size(); i++)
        {
            for (int j = 0; j < team2Members.size(); j++)
            {
                temp = team1Members.get(i);
                team1Members.set(i, team2Members.get(j));
                team2Members.set(j, temp);

                if (getNewSkillGap(team1Members, team1) < team1CurrentSkillGap
                            && getNewSkillGap(team2Members, team2) < team2CurrentSkillGap)
                {
                    swapMemberSuggestions.add(team1Members.get(i) + " <-> " + team2Members.get(j));
                    team1CurrentSkillGap = getNewSkillGap(team1Members, team1);
                    team2CurrentSkillGap = getNewSkillGap(team2Members, team2);
                }

                team2Members.set(j, team1Members.get(i));
                team1Members.set(i, temp);
            }
        }
        return swapMemberSuggestions;
    }

    // Function to calculate skill gap to temporary teams
    private float getNewSkillGap(ArrayList<String> tempTeamMember, String teamID)
    {
        Team tempTeam = new Team("tempID", "tempName", tempTeamMember);
        tempTeam.setAvgStudentSkillMap(student);
        tempTeam.setPreferncePercentage(student);
        tempTeam.setTotalSkillGap(project.get(teamID));

        return tempTeam.getTotalSkillGap();
    }
}
