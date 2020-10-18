/**
 * 
 */
package assignment.application.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import assignment.application.model.MemberExistsException;
import assignment.application.model.ModelWrapper;
import assignment.application.model.Team;

/**
 * @author Siddharth Kumar
 *
 */
public class UnitTest
{

    static ModelWrapper wrapper = ModelWrapper.getInstance();
    static ArrayList<String> teamMembers = new ArrayList<String>();
    static ArrayList<String> conflictMembers = new ArrayList<String>();

    @BeforeClass
    public static void setup()
    {
        // Loading project and student data from file
        wrapper.readAll(true);

        // Adding first team
        teamMembers.add("S01");
        teamMembers.add("S02");
        teamMembers.add("S03");
        teamMembers.add("S04");
        wrapper.addTeam(new Team("PR1", "Team1", teamMembers));

        // Adding Second team
        teamMembers.clear();
        teamMembers.add("S05");
        teamMembers.add("S06");
        teamMembers.add("S07");
        teamMembers.add("S08");
        wrapper.addTeam(new Team("PR2", "Team3", teamMembers));

    }

    @Test
    public void testAvgSkillCompetancy()
    {
        assertEquals("Valid Competance Score", 1.0d, wrapper.getTeam().get("PR1").getAverageStudentSkill(), 0.001);
    }

    @Test
    public void testPreferencePercentage()
    {
        assertEquals("Valid Preference Percentage", 50.0f, wrapper.getTeam().get("PR1").getPreferncePercentage(), 0.001);
    }

    @Test
    public void testSkillShortfall()
    {
        assertEquals("Valid Skill Shortfall", 1.5f, wrapper.getTeam().get("PR1").getTotalSkillGap(), 0.001);
    }

    @Test (expected = MemberExistsException.class)
    public void testMemberExists() throws MemberExistsException
    {
        Team team = new Team("PR1", "Team01", "S01 S02 S01 S03");
    }
  
    // Negative test-case
    // Unit test for verifying conflictCheck function works
    // S04 and S08 has conflict
    @Test
    public void testConflictCheck_Negative()
    {
        conflictMembers.add("S03");
        conflictMembers.add("S04");
        assertTrue(wrapper.confictCheck(conflictMembers, "S08"));
    }
    
    // Positive test-case
    // Unit test for verifying conflictCheck function works
    // No team member has any conflict
    @Test
    public void testConflictCheck_Positive()
    {
        conflictMembers.add("S01");
        conflictMembers.add("S02");
        assertFalse(wrapper.confictCheck(conflictMembers, "S08"));
    }

    // Positive test-case
    // Unit test for verifying conflictCheck function works
    // No team member has any conflict
    @Test
    public void testLeaderCheck_Postive()
    {
        assertTrue(wrapper.teamPersonalityCheck(teamMembers));
    }
    
    @Test
    public void testLeaderCheck_Negative()
    {
        teamMembers.clear();
        teamMembers.add("S06");
        teamMembers.add("S07");
        teamMembers.add("S08");
        teamMembers.add("S10");
        assertFalse(wrapper.teamPersonalityCheck(teamMembers));
    }

    // Test case to verify preference percentage SD
    @Test
    public void testPerfPercentageSD()
    {
        assertEquals("Valid Preference Percentage SD", 25.0d, wrapper.getTeamMetricSD().get(0), 0.001);
    }

    // Test case to verify skill competence SD
    @Test
    public void testSkillCompetancyD()
    {
        assertEquals("Valid Skill Competence SD", 0.125d, wrapper.getTeamMetricSD().get(1), 0.001);
    }

    // Test case to verify preference percentage SD
    @Test
    public void testSkillGapSD()
    {
        assertEquals("Valid Skill Gap SD", 0.25d, wrapper.getTeamMetricSD().get(2), 0.001);
    }
  
}
