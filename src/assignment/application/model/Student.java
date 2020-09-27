package assignment.application.model;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Student 
{

    private char personality;
    private String studentID;
    private String[] conflict;
    private TreeMap<String, Integer> grades;
    private Map<String, Integer> preference;

    public Student()
    {
        this.conflict = new String[] { "", "" };
        this.personality = ' ';
        setPreference(new HashMap<String, Integer>());
    }

    public Student(String studentID, String grades, char personality, String conflict)
    {
        this.conflict = new String[] { "", "" };
        this.grades = new TreeMap<String, Integer>();
        this.preference = new HashMap<String, Integer>();
        this.setConflict(conflict);
        this.setGrades(grades);
        this.setPersonality(personality);
        this.setStudentID(studentID);
    }

    public char getPersonality()
    {
        return personality;
    }

    public void setPersonality(char personality)
    {
        this.personality = personality;
    }

    public String getStudentID()
    {
        return studentID;
    }

    public void setStudentID(String studentID)
    {
        this.studentID = studentID;
    }

    public String getGradesString()
    {
        String temp = "";
        for (Entry<String, Integer> entry : grades.entrySet())
        {
            temp += entry.getKey() + " " + entry.getValue() + " ";
        }
        return temp.strip();
    }

    public Map<String, Integer> getGrades()
    {
        return grades;
    }

    public void setGrades(String grades)
    {
        StringTokenizer st = new StringTokenizer(grades, " ");
        String key = null;
        int value = 0;
        int i = 1;

        while (st.hasMoreTokens())
        {
            if (i % 2 != 0)
            {
                key = st.nextToken();
            }
            else
            {
                try
                {
                    value = Integer.parseInt(st.nextToken());
                }
                catch (NumberFormatException nfe)
                {
                    value = 0;
                }
                this.grades.put(key, value);
            }
            i++;
        }

    }

    public String[] getConflict()
    {
        return conflict;
    }

    public String getConflictString()
    {
        String temp = "";
        for (int i = 0; i < conflict.length; i++)
        {
            temp += conflict[i] + " ";
        }
        return temp;
    }

    public void setConflict(String conflict)
    {
        StringTokenizer st = new StringTokenizer(conflict, " ");
        if (st.hasMoreTokens())
        {
            this.conflict[0] = st.nextToken();
            if (st.hasMoreTokens())
            {
                this.conflict[1] = st.nextToken();
            }
            else
            {
                this.conflict[1] = "";
            }
        }
    }

    public Map<String, Integer> getPreference()
    {
        return preference;
    }

    public void setPreference(Map<String, Integer> preference)
    {
        this.preference = preference;
    }

    public void setPreferenceString(String preference)
    {
        StringTokenizer st = new StringTokenizer(preference, " ");
        String key = null;
        int value = 0;
        int i = 1;

        while (st.hasMoreTokens())
        {
            if (i % 2 != 0)
            {
                key = st.nextToken();
            }
            else
            {
                try
                {
                    value = Integer.parseInt(st.nextToken());
                }
                catch (NumberFormatException nfe)
                {
                    value = 0;
                }

                this.preference.put(key, value);
            }
            i++;
        }
    }

    @Override
    public String toString()
    {
        return String.format("%s; %s; %c;  %s; ", this.getStudentID(), this.getGradesString(), this.getPersonality(),
                    this.getConflictString());
    }

}
