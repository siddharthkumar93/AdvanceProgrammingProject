package assignment.application.model;

public class ProjectOwner
{

    private String firstName;
    private String lastName;
    private String ownerID;
    private String role;
    private String emailID;
    private String companyID;

    public ProjectOwner()
    {

    }

    public ProjectOwner(String firstName, String lastName, String ownerID, String role, String emailID, String companyID)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ownerID = ownerID;
        this.role = role;
        this.emailID = emailID;
        this.companyID = companyID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getOwnerID()
    {
        return ownerID;
    }

    public void setOwnerID(String ownerID)
    {
        this.ownerID = ownerID;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getEmailID()
    {
        return emailID;
    }

    public void setEmailID(String emailID)
    {
        this.emailID = emailID;
    }

    public String getCompanyID()
    {
        return companyID;
    }

    public void setCompanyID(String companyID)
    {
        this.companyID = companyID;
    }

    @Override
    public String toString()
    {
        return String.format("%s; %s;  %s; %s; %s; %s; ", this.getFirstName(), this.getLastName(), this.getOwnerID(), this.getRole(),
                    this.getEmailID(), this.getCompanyID());
    }
}
