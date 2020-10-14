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

import assignment.application.data.FileReadWrite;

public class ModelWrapper
{

    Scanner input = new Scanner(System.in);
    FileReadWrite data = new FileReadWrite();

    private TreeMap<String, Company> company;
    private TreeMap<String, Project> project;
    private TreeMap<String, ProjectOwner> projectOwner;
    private TreeMap<String, Student> student;
    private TreeMap<String, Team> team;
    
    private static ModelWrapper instance = null;

    private ModelWrapper()
    {
        student = new TreeMap<String, Student>();
        company = new TreeMap<String, Company>();
        project = new TreeMap<String, Project>();
        projectOwner = new TreeMap<String, ProjectOwner>();
        team = new TreeMap<String, Team>();
    }
    
    public static ModelWrapper getInstance()
    {
        if(instance == null)
        {
            instance = new ModelWrapper();
        }
        return instance;
    }

    public void addCompany(Company objCompany)
    {
        company.put(objCompany.getCompanyID(), objCompany);
        data.writeCompany(company);
    }

    public void addProjectOwner(ProjectOwner owner)
    {
        projectOwner.put(owner.getOwnerID(), owner);
        data.writeOwners(projectOwner);
    }

    public void addProject(Project objProject)
    {
        project.put(objProject.getProjectID(), objProject);
        data.writeProject(project);
    }

    public void addTeam(Team objTeam)
    {
        team.put(objTeam.getTeamID(), objTeam);
        team.get(objTeam.getTeamID()).setAvgStudentSkillMap(student);
        team.get(objTeam.getTeamID()).setPreferncePercentage(student);
        team.get(objTeam.getTeamID()).setTotalSkillGap(project.get(objTeam.getTeamID()));
    }

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

    public void readAll()
    {
        readStudentInfo();
        readProject();
        readStudentPreference();
    }

    public void readProject()
    {
        String[][] content = data.readFile("projects.txt");

        for (int i = 0; i < content.length; i++)
        {
            project.put(content[i][1].strip(), new Project(content[i][0], content[i][1], content[i][2], content[i][3], content[i][4]));
        }
    }

    public void readStudent()
    {
        System.out.println("The following students are availbe with their repective grades");
        String id;
        String[][] content = data.readFile("students.txt");

        for (int i = 0; i < content.length; i++)
        {
            id = content[i][0].trim().toUpperCase();
            student.put(id, new Student(id, content[i][1], ' ', ""));
            System.out.println(content[i][0]);
            System.out.println(content[i][1]);
        }

    }

    public void readStudentInfo()
    {
        char personality;
        String id;
        String[][] content = data.readFile("studentinfo.txt");

        for (int i = 0; i < content.length; i++)
        {
            personality = content[i][2].strip().charAt(0);
            id = content[i][0].trim().toUpperCase();
            student.put(id, new Student(id, content[i][1], personality, content[i][3]));
        }
    }

    public void readStudentPreference()
    {
        String id;
        String[][] content = data.readFile("preferences.txt");
        for (int i = 0; i < content.length; i++)
        {
            id = content[i][0].trim().toUpperCase();
            student.get(id).setPreferenceString(content[i][1].strip());
        }
    }

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

    // need update
    public void capturePersonalities(boolean readStudent)
    {
        boolean flag = true;
        String studentID, temp, tempConflict = "";
        char personality = ' ';

        while (flag)
        {

            try
            {
                System.out.println("Enter the student id, whoes record you want update.(Press Q/q to go back)");
                studentID = input.nextLine().trim().toUpperCase();
                temp = " ";

                student.get(studentID).setPersonality(personality);

                System.out.println("Enter the conflict students id (upto 2 eg. S10 s20)");
                temp = input.nextLine();

                // This code can be improved
                if (!temp.isBlank())
                {
                    tempConflict = temp.toUpperCase();
                    System.out.println("Student conflicts recorded");
                }
                else
                {
                    System.out.println("No student conflicts recorded");
                }

                student.get(studentID).setConflict(tempConflict);

            }
            catch (Exception exp)
            {
                System.out.println("In-correct input format. Please enter values in the required format");
            }

        }

        data.writeStudent(student, "studentinfo.txt");
    }

    public void updatePreferences()
    {
        // student.get(studentID).getPreference().put(projID, i);
        data.writeStudent(student, "preferences.txt");
    }

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

    public Map<String, Project> getProjectMap()
    {
        return project;
    }

    public Map<String, Student> getStudentMap()
    {
        return student;
    }

    // Method to generate heuristic suggestions
    public ArrayList<String> getSwapSuggestions()
    {
        ArrayList<String> swapMembers = new ArrayList<String>();
        List<Team> teamSet = new ArrayList<Team>(team.values());

        int startIndex = 0;
        int endIndex = teamSet.size() - 1;

        // sorting the team based on
        teamSet.sort((Team t1, Team t2) -> Float.compare(t1.getTotalSkillGap(), t2.getTotalSkillGap()));

        while (swapMembers.size() == 0)
        {
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

    private float getNewSkillGap(ArrayList<String> tempTeamMember, String teamID)
    {
        Team tempTeam = new Team("tempID", "tempName", tempTeamMember);
        tempTeam.setAvgStudentSkillMap(student);
        tempTeam.setPreferncePercentage(student);
        tempTeam.setTotalSkillGap(project.get(teamID));

        return tempTeam.getTotalSkillGap();
    }
}
