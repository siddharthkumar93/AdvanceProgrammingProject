package assignment.application.model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Project 
{

    private String title;
    private String projectID;
    private String description;
    private String ownerID;
    private TreeMap<String, Integer> ranking;

    public Project()
    {

    }

    public Project(String title, String projectID, String description, String ownerID, String ranking)
    {
        this.title = title;
        this.projectID = projectID;
        this.description = description;
        this.ownerID = ownerID;
        this.ranking = new TreeMap<String, Integer>();
        setRanking(ranking);
    }
    
    public Project(String title, String projectID, String description, String ownerID, TreeMap<String, Integer> ranking)
    {
        this.title = title;
        this.projectID = projectID;
        this.description = description;
        this.ownerID = ownerID;
        // this works for now, may cause issues later
        this.ranking = ranking;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getProjectID()
    {
        return projectID;
    }

    public void setProjectID(String projectID)
    {
        this.projectID = projectID;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getOwnerID()
    {
        return ownerID;
    }

    public void setOwnerID(String ownerID)
    {
        this.ownerID = ownerID;
    }

    public String getRankingString()
    {
        String temp = "";
        for (Entry<String, Integer> entry : this.ranking.entrySet())
        {
            temp += entry.getKey() + " " + entry.getValue() + " ";
        }
        return temp.strip();
    }

    public Map<String, Integer> getRanking()
    {
        return ranking;
    }

    public void setRanking(String ranking)
    {
        StringTokenizer st = new StringTokenizer(ranking, " ");
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

                this.ranking.put(key, value);
            }
            i++;
        }
    }

    @Override
    public String toString()
    {
        return String.format("%s; %s; %s; %s; %s;", this.getTitle(), this.getProjectID(), this.getDescription(), this.getOwnerID(),
                    this.getRankingString());
    }
}
