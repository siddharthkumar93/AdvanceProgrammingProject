package assignment.application.model;

public class Company
{
    private String companyID;
    private String companyName;
    private String abnNumber;
    private String companyURL;
    private String address;

    public Company()
    {

    }

    public void setAll (String companyID, String companyName, String abnNumber, String companyURL, String address)
    {
        this.companyID = companyID;
        this.companyName = companyName;
        this.abnNumber = abnNumber;
        this.companyURL = companyURL;
        this.address = address;
    }

    public String getCompanyID()
    {
        return companyID;
    }

    public void setCompanyID(String companyID)
    {
        this.companyID = companyID;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getAbnNumber()
    {
        return abnNumber;
    }

    public void setAbnNumber(String abnNumber)
    {
        this.abnNumber = abnNumber;
    }

    public String getCompanyURL()
    {
        return companyURL;
    }

    public void setCompanyURL(String companyURL)
    {
        this.companyURL = companyURL;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return String.format("%s; %s; %s; %-5s; %-5s; ", this.getCompanyID(), this.getCompanyName(), this.getAbnNumber(),
                    this.getCompanyURL(), this.getAddress());
    }
}
