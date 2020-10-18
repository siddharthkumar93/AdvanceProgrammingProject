package assignment.application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import assignment.application.GlobalVar;

public class Team
{
    private String teamID;
    private String teamName;
    private ArrayList<String> teamMembers;
    private float avgStudentSkill;
    private float preferncePercentage;
    private float totalSkillGap;
    private Map<String, Float> avgStudentSkillMap;

    public Team()
    {
        avgStudentSkillMap = new HashMap<String, Float>();
        avgStudentSkill = 0f;
        preferncePercentage = 0f;
    }

    public Team(String teamID, String teamName, ArrayList<String> teamMembers)
    {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamMembers = teamMembers;
        avgStudentSkillMap = new HashMap<String, Float>();
        avgStudentSkill = 0f;
        preferncePercentage = 0f;
    }

    public Team(String teamID, String teamName, String teamMembers) throws MemberExistsException
    {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamMembers = new ArrayList<String>();
        this.setTeamMembers(teamMembers);
        avgStudentSkillMap = new HashMap<String, Float>();
        avgStudentSkill = 0f;
        preferncePercentage = 0f;
    }

    public String getTeamID()
    {
        return teamID;
    }

    public ArrayList<String> getTeamMembers()
    {
        return teamMembers;
    }

    public String getTeamMembersString()
    {
        String teamMembers = GlobalVar.emptyString;
        for (String member : this.teamMembers)
        {
            teamMembers.concat(member).concat(" ");
        }

        return teamMembers;
    }

    public void setTeamMembers(ArrayList<String> teamMembers)
    {
        this.teamMembers = teamMembers;
    }

    public void setTeamMembers(String temaMembers) throws MemberExistsException
    {
        StringTokenizer st = new StringTokenizer(temaMembers, " ");
        
        while (st.hasMoreTokens())
        {
            String studentId = st.nextToken();
            if (!this.teamMembers.contains(studentId))
            {
                this.teamMembers.add(studentId);
            }
            else
            {
                throw new MemberExistsException(st.nextToken() + " member exisits in the team");
            }
        }
    }

    public float getAverageStudentSkill()
    {
        return avgStudentSkill;
    }

    public float getPreferncePercentage()
    {
        return preferncePercentage;
    }

    public Map<String, Float> getAvgStudentSkillMap()
    {
        return this.avgStudentSkillMap;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public void setAvgStudentSkillMap(Map<String, Student> student)
    {
        String key;
        int teamSize = teamMembers.size();
        float total = 0f;
        Map<String, Integer> gradeMap;

        for (String studentID : teamMembers)
        {
            // Grade of this student
            gradeMap = student.get(studentID).getGrades();
            for (Entry<String, Integer> entry : gradeMap.entrySet())
            {

                key = entry.getKey();
                if (this.avgStudentSkillMap.containsKey(key))
                {
                    this.avgStudentSkillMap.put(key, this.avgStudentSkillMap.get(key) + ((float) entry.getValue() / teamSize));
                }
                else
                {
                    this.avgStudentSkillMap.put(key, ((float) entry.getValue() / teamSize));
                }

                total += entry.getValue() / teamSize;
                key = "";
            }
        }

        this.avgStudentSkill = total / 4;
    }

    public void setPreferncePercentage(Map<String, Student> student)
    {
        float count = 0f;
        Map<String, Integer> prefernceMap;
        for (String StudentID : teamMembers)
        {
            prefernceMap = student.get(StudentID).getPreference();
            for (Entry<String, Integer> preference : prefernceMap.entrySet())
            {
                if (preference.getKey().equals(this.teamID))
                {
                    if (preference.getValue() == 3 || preference.getValue() == 4)
                    {
                        count++;
                    }
                }
            }
        }
        if (teamMembers.size() > 0)
        {
            this.preferncePercentage = (count / teamMembers.size()) * 100;
        }
    }

    public void setTotalSkillGap(Project project)
    {
        float tempSkillGap = 0f;

        Map<String, Integer> tempRank = project.getRanking();
        for (Entry<String, Integer> rank : tempRank.entrySet())
        {
            // adding the negative skills as well for better skill map assessment
            if (rank.getValue() - this.avgStudentSkillMap.get(rank.getKey()) > 0)
            {
                tempSkillGap += Math.abs(rank.getValue() - this.avgStudentSkillMap.get(rank.getKey()));
            }
        }

        this.totalSkillGap = tempSkillGap;
    }

    public float getTotalSkillGap()
    {
        return this.totalSkillGap;
    }
}
