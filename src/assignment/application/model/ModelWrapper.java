package assignment.application.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import assignment.application.GlobalVar;
import assignment.application.data.FileReadWrite;

public class ModelWrapper 
{

    transient Scanner input = new Scanner(System.in);
    transient FileReadWrite data = new FileReadWrite();
    transient private Set<String> availbeTeamMember;

    private TreeMap<String, Company> company;
    private TreeMap<String, Project> project;
    private TreeMap<String, ProjectOwner> projectOwner;
    private TreeMap<String, Student> student;
    private TreeMap<String, Team> team;

    private static boolean enableMetric;

    public ModelWrapper()
    {
        student = new TreeMap<String, Student>();
        company = new TreeMap<String, Company>();
        project = new TreeMap<String, Project>();
        projectOwner = new TreeMap<String, ProjectOwner>();
        team = new TreeMap<String, Team>();
        enableMetric = false;
    }

    public boolean isEnableMetric()
    {
        return (team.size() > 0 ? true : false);
    }

    public void teamMetrics()
    {
        boolean flag = true;
        int option;
        String projID;
        if (isEnableMetric())
        {
            while (flag)
            {
                System.out.println("\n\t Team Metric Menu \r\n" + "1. Average student skill competency \r\n"
                            + "2. First and Second preferences students Percentage \r\n" + "3. Skills shortfall for each project \r\n"
                            + "4. Calculate Standard Deviation for the team \r\n" + "0. Previous Menu");
                option = input.nextInt();
                input.nextLine();

                switch (option) {
                case 1:
                    showAvgTeamCompetancy();
                    break;

                case 2:
                    preferncePercentage();
                    break;

                case 3:
                    skillShortage();
                    break;

                case 4:
                    sdCalculate();
                    break;

                case 0:
                    return;

                default:
                    System.out.println(GlobalVar.formatIncorrectOption);
                }
            }
        }
        else
        {
            System.out.println("Team metric menu is currently disabled. \r\n" + "Please form a team to enable this menu!!");
        }
    }

    private void sdCalculate()
    {
        // TODO Auto-generated method stub

    }

    private void skillShortage()
    {
        // TODO Auto-generated method stub

    }

    public void fix()
    {
        input = new Scanner(System.in);
        data = new FileReadWrite();
    }

    public void addCompany()
    {
        System.out.println("\n\t Adding new company \n");
        boolean flag = true;
        String name, address, url, companyId, abn;
        String more;

        if (input.equals(null))
        {
            System.out.println("Gotcha");
        }

        while (flag)
        {

            try
            {
                System.out.println("Enter Company ID (Enter number only, without C prefix)");
                companyId = "C" + Integer.parseInt(input.nextLine());

                System.out.println("Enter Company Name");
                name = input.nextLine();

                System.out.println("Enter Company ABN");
                abn = input.nextLine();

                System.out.println("Enter Company Address");
                address = input.nextLine();

                System.out.println("Enter Company URL");
                url = input.nextLine();

                company.put(companyId, new Company(companyId, name, abn, url, address));

                System.out.println("Do you want to add more company? Yes - Y/y | No - N/n");
                more = input.nextLine();

                if (more.equals("Y") || more.equals("y"))
                {
                    flag = true;
                }
                else if (more.equals("N") || more.equals("n"))
                {
                    flag = false;
                }
                else
                {
                    System.out.println("Input not recognized, reverting to previous menu");
                    flag = false;
                }

            }
            catch (Exception exp)
            {
                System.out.println("Inccorect input format. Please enter values in the required format");
            }
        }

        data.writeCompany(company);
    }

    public void addProjectOwner()
    {
        System.out.println("\n\t Adding new Project Ownner \n");
        boolean flag = true;
        String firstName, lastName, ownerID, role, emailID, companyID;
        String more;

        while (flag)
        {
            try
            {
                System.out.println("Enter first name of project owner");
                firstName = input.nextLine();

                System.out.println("Enter last name of project owner");
                lastName = input.nextLine();

                System.out.println("Enter owner ID (enter interger without Own prefix)");
                ownerID = "Own" + Integer.parseInt(input.nextLine());

                System.out.println("Enter owner role");
                role = input.nextLine();

                System.out.println("Enter owner e-mail id");
                emailID = input.nextLine();

                System.out.println("Enter owner company id (enter interger without C prefix)");
                companyID = "C" + Integer.parseInt(input.nextLine());

                projectOwner.put(ownerID, new ProjectOwner(firstName, lastName, ownerID, role, emailID, companyID));

                System.out.println("Do you want to add more project owner? Yes - Y/y | No - N/n");
                more = input.nextLine();

                if (more.equals("Y") || more.equals("y"))
                {
                    flag = true;
                }
                else if (more.equals("N") || more.equals("n"))
                {
                    flag = false;
                }
                else
                {
                    System.out.println("Input not recognized, reverting to previous menu");
                    flag = false;
                }

            }
            catch (Exception exp)
            {
                System.out.println("Inccorect input format. Please enter values in the required format");
            }
        }

        data.writeOwners(projectOwner);
    }

    public void addProject()
    {
        System.out.println("\t Adding new Project");
        boolean flag = true;
        String title, projectID, description, ownerID, ranking;
        String more;

        while (flag)
        {

            try
            {
                System.out.println("Enter title of Project");
                title = input.nextLine();

                System.out.println("Enter the project ID (enter interger without PR prefix)");
                projectID = "PR" + Integer.parseInt(input.nextLine());

                System.out.println("Enter the project description");
                description = input.nextLine();

                System.out.println("Enter owner ID (enter interger without Own prefix)");
                ownerID = "Own" + Integer.parseInt(input.nextLine());

                // todo implement input check
                System.out.println("Enter the ranking (Eg. W 4 P 3 N 2 A 1)");
                ranking = input.nextLine();

                project.put(projectID, new Project(title, projectID, description, ownerID, ranking));

                System.out.println("Do you want to add more project owner? Yes - Y/y | No - N/n");
                more = input.nextLine();

                if (more.equals("Y") || more.equals("y"))
                {
                    flag = true;
                }
                else if (more.equals("N") || more.equals("n"))
                {
                    flag = false;
                }
                else
                {
                    System.out.println("Input not recognized, reverting to previous menu");
                    flag = false;
                }

            }
            catch (Exception exp)
            {
                System.out.println("Inccorect input format. Please enter values in the required format");
            }

        }

        data.writeProject(project);
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

    public void readProject()
    {

        String[][] content = data.readFile("projects.txt");

        for (int i = 0; i < content.length; i++)
        {
            project.put(content[i][1].strip(), new Project(content[i][0], content[i][1], content[i][2], content[i][3], content[i][4]));
        }
    }

    public void capturePersonalities(boolean readStudent)
    {

        boolean flag = true;
        String studentID, temp, tempConflict = "";
        char personality;

        if (readStudent)
        {
            readStudent();
            System.out.println("Student file read");
        }

        while (flag)
        {

            try
            {
                System.out.println("Enter the student id, whoes record you want update.(Press Q/q to go back)");
                studentID = input.nextLine().trim().toUpperCase();
                temp = " ";

                if (studentID.equals("Q"))
                {
                    flag = false;
                }
                else
                {

                    if (student.containsKey(studentID))
                    {
                        System.out.println("Student found with following details");
                        System.out.println(student.get(studentID).toString());

                        if (student.get(studentID).getPersonality() == ' ')
                        {
                            System.out.println("Enter the personality of Student");
                            personality = input.nextLine().toUpperCase().charAt(0);

                            if (personalityCheck(personality))
                            {
                                student.get(studentID).setPersonality(personality);
                            }
                            else
                            {
                                System.out.println("Assign a different grade.");
                            }

                        }

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
                    else
                    {
                        System.out.println("Enter student id doesn't exist");
                    }

                }

            }
            catch (Exception exp)
            {
                System.out.println("In-correct input format. Please enter values in the required format");
            }

        }

        data.writeStudent(student, "studentinfo.txt");
    }

    public void updatePersonalityCount()
    {
        char temp;

        for (Map.Entry<String, Student> entry : student.entrySet())
        {
            temp = entry.getValue().getPersonality();

            switch (temp) {
            case 'A':
                GlobalVar.countA++;
                break;

            case 'B':
                GlobalVar.countB++;
                break;

            case 'C':
                GlobalVar.countC++;
                break;

            case 'D':
                GlobalVar.countD++;
                break;
            }

        }

    }

    public boolean personalityCheck(char type)
    {
        int limit = student.size() / 4;

        // updatePersonalityCount();
        switch (type) {
        case 'A':
            GlobalVar.countA++;
            break;

        case 'B':
            GlobalVar.countB++;
            break;

        case 'C':
            GlobalVar.countC++;
            break;

        case 'D':
            GlobalVar.countD++;
            break;
        }

        if (GlobalVar.countA > limit || GlobalVar.countB > limit || GlobalVar.countC > limit || GlobalVar.countD > limit)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public void preferences()
    {

        String studentID, projID;
        boolean flag = true;

        System.out.println("\n\t Updating student project preference \n");

        while (flag)
        {

            try
            {
                System.out.print("List of existing project : ");

                for (Map.Entry<String, Project> entry : project.entrySet())
                {
                    System.out.print(entry.getKey() + " ");
                }

                System.out.println("\n Enter the student ID for updating prefernece (Press Q/q to go back)");
                studentID = input.nextLine().trim().toUpperCase();

                if (studentID.equals("Q"))
                {
                    flag = false;
                }
                else
                {

                    if (student.containsKey(studentID))
                    {
                        int i = 4;

                        do
                        {
                            System.out.println("Enter the Project IDs for prefernce rating " + i);
                            projID = input.nextLine().strip().toUpperCase();

                            if (project.containsKey(projID))
                            {
                                student.get(studentID).getPreference().put(projID, i);
                                i--;
                            }
                            else
                            {
                                System.out.println("Entered project entered doesn't exist. Please enter the existing project");
                            }

                        } while (i > 0);

                        System.out.println("Prefernce updated");
                    }
                }
            }
            catch (Exception exp)
            {
                System.out.println("Incorrect input format. Please enter values in the required format");
            }

        }

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

    public void suggestedMember()
    {

    }

    // Function to select students for each team
    public String[] teamMemberSelection()
    {
        boolean personalityFlag = true;
        String studentID, choice;
        String[] tempMember = new String[4];

        do
        {
            for (int i = 0; i < 4; i++)
            {
                System.out.println("Enter the Student ID for team member : " + (i + 1));
                studentID = input.nextLine().toUpperCase();

                if (availbeTeamMember.contains(studentID))
                {
                    if (tempMember[0] != null)
                    {
                        if (confictCheck(tempMember, studentID))
                        {
                            System.out.println("Press Y/y to continue addding this student to team");
                            choice = input.nextLine();

                            if (choice.equalsIgnoreCase("Y"))
                            {
                                tempMember[i] = studentID;
                            }
                            else
                            {
                                i--;
                            }
                        }
                        else
                        {
                            tempMember[i] = studentID;
                        }
                    }
                    else
                    {
                        tempMember[0] = studentID;
                    }

                    if (student.get(tempMember[i]).getPersonality() == 'A')
                    {
                        personalityFlag = false;
                    }
                }
                else
                {
                    System.out.println("Entered Student ID is not available");
                    i--;
                }

            }

            if (personalityFlag)
            {
                System.out.println("The Team formed doesn't have atleast one student of personlity type A.");
                System.out.println("Please reform the team.");
            }

        } while (personalityFlag);

        return tempMember;

    }

    private boolean confictCheck(String[] tempMember, String studentID)
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
                        System.out.println("Warning!!! Student : " + memberID + " has a confict with " + studentID);
                        confictFlag = true;
                    }
                }

                for (String confict : student.get(studentID).getConflict())
                {
                    if (confict.equalsIgnoreCase(memberID))
                    {
                        System.out.println("Warning!!! Student : " + studentID + " has a confict with " + memberID);
                        confictFlag = true;
                    }
                }
            }
        }
        return confictFlag;
    }

    // Function to form new teams while checking for existing constraints
    public void formTeam()
    {
        boolean flag = true;
        String projectID, teamID;
        String[] tempMembers;

        if (student.isEmpty() || project.isEmpty())
        {
            readStudentInfo();
            readProject();
            readStudentPreference();
        }

        availbeTeamMember = student.keySet();

        if (!student.isEmpty())
        {
            System.out.print("Here's the list of existing projects : ");
            System.out.print(project.keySet());

            while (flag)
            {
                System.out.println("\nEnter the Project ID to form Team (Press q/Q to return to previous menu)");
                projectID = input.nextLine();

                if (projectID.equalsIgnoreCase("Q"))
                {
                    System.out.println("Returing to previous menu");
                    flag = false;
                }
                else if (project.containsKey(projectID.toUpperCase()))
                {
                    // suggestedMember();
                    tempMembers = teamMemberSelection();

                    if (!tempMembers.equals(null))
                    {
                        teamID = "Team" + (team.size() + 1);
                        team.put(projectID, new Team(teamID, tempMembers));
                        System.out.println(teamID + " formed");
                        //availbeTeamMember.removeAll(Arrays.asList(tempMembers));
                        team.get(projectID).setAvgStudentSkillMap(student);
                    }
                }
                else
                {
                    System.out.println("Entered Project ID not found");
                }

            }

        }

    }

    public void showAvgTeamCompetancy()
    {
        for (Entry<String, Team> entry : team.entrySet())
        {
            System.out.println(entry.getKey().toUpperCase() + " : \t" + entry.getValue().getTeamID());
            System.out.println("Team Members : " + Arrays.toString(entry.getValue().getTeamMembers()));
           
            System.out.println("Average student skill competency for project : " + entry.getValue().getAverageStudentSkill());
            
        }
    }

    public void preferncePercentage()
    {
        String key;
        Map<String, Integer> skillMap;
        for (Entry<String, Team> teamEntry : team.entrySet())
        {
            key = teamEntry.getKey();
            skillMap = project.get(key).getRanking();
            for (Entry<String, Integer> skillEntry : skillMap.entrySet())
            {

            }
        }
    }
}

