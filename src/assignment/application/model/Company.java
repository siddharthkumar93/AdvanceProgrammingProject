package assignment.application.model;

public class Company
{
    private String companyID;
    private String companyName;
    private String abnNumber;
    private String companyURL;
    private String address;

    // Class constructor
    public Company(String companyID, String companyName, String abnNumber, String companyURL, String address)
    {
        this.companyID = companyID;
        this.companyName = companyName;
        this.abnNumber = abnNumber;
        this.companyURL = companyURL;
        this.address = address;
    }

    // getter-setter for companyID
    public String getCompanyID()
    {
        return companyID;
    }

    public void setCompanyID(String companyID)
    {
        this.companyID = companyID;
    }

    // getter-setter for companyName
    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    // getter-setter for ABN number
    public String getAbnNumber()
    {
        return abnNumber;
    }

    public void setAbnNumber(String abnNumber)
    {
        this.abnNumber = abnNumber;
    }

    // getter-setter for companyURL
    public String getCompanyURL()
    {
        return companyURL;
    }

    public void setCompanyURL(String companyURL)
    {
        this.companyURL = companyURL;
    }

 // getter-setter for company address
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
