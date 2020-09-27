package assignment.application.model;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Team 
{


    private String teamID;
    private String[] teamMembers;
    private float avgStudentSkill;
    Map<String, Integer> avgStudentSkillMap;

    public Team()
    {
        // TODO Auto-generated constructor stub
    }

    public Team(String teamID, String[] teamMembers)
    {
        this.teamID = teamID;
        this.teamMembers = teamMembers;
        avgStudentSkillMap = new HashMap<String, Integer>();
    }

    public String getTeamID()
    {
        return teamID;
    }

    public String[] getTeamMembers()
    {
        return teamMembers;
    }

    public void setTeamMembers(String[] teamMembers)
    {
        this.teamMembers = teamMembers;
    }

    public float getAverageStudentSkill()
    {
        return avgStudentSkill;
    }

    public Map<String, Integer> getAvgStudentSkillMap()
    {
        return this.avgStudentSkillMap;
    }

    public void setAvgStudentSkillMap(Map<String, Student> student)
    {
        String key;
        int teamSize = 0;
        float total = 0f;
        Map<String, Integer> gradeMap;

        for (String studentID : teamMembers)
        {
            teamSize = teamMembers.length;
            gradeMap = student.get(studentID).getGrades();
            for (Entry<String, Integer> entry : gradeMap.entrySet())
            {
                key = entry.getKey();
                if (!this.avgStudentSkillMap.containsKey(key))
                {
                    this.avgStudentSkillMap.put(key, entry.getValue() / teamSize);
                }
                else
                {
                    this.avgStudentSkillMap.put(key, this.avgStudentSkillMap.get(key) + entry.getValue() / teamSize);
                }

                total += entry.getValue();
                key = "";
            }
        }

        this.avgStudentSkill = total / 4;
    }
}
