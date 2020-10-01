package assignment.application.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import assignment.application.data.FileReadWrite;

public class ModelWrapper
{

    transient Scanner input = new Scanner(System.in);
    transient FileReadWrite data = new FileReadWrite();

    private TreeMap<String, Company> company;
    private TreeMap<String, Project> project;
    private TreeMap<String, ProjectOwner> projectOwner;
    private TreeMap<String, Student> student;
    private TreeMap<String, Team> team;

    public ModelWrapper()
    {
        student = new TreeMap<String, Student>();
        company = new TreeMap<String, Company>();
        project = new TreeMap<String, Project>();
        projectOwner = new TreeMap<String, ProjectOwner>();
        team = new TreeMap<String, Team>();
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

    public Set<String> getCompanyList()
    {
        return company.keySet();
    }

    public Set<String> getProjectOwnerList()
    {
        return projectOwner.keySet();
    }

    public Set<String> getProjectList()
    {
        return project.keySet();
    }

    public Set<String> getStudentList()
    {
        return student.keySet();
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
        
        for(String studentID: teamMembers)
        {
            if(student.get(studentID).getPersonality() == 'A')
            {
                flag = true;
            }
        }    
        return flag;
    }

    //need update
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

    public Map<String, Project> getProjectMap()
    {
        return project;
    }
    
    public Map<String, Student> getStudentMap()
    {
        return student;
    }

  
}
