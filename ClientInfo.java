public class ClientInfo {

    String firstName;
    String lastName;
    long phoneNumber;
    String state;
    long zipcode;

    public ClientInfo()
    {

    }

    public ClientInfo(String firstName, String lastName, long phoneNumber, String state, long zip_code)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.zipcode = zip_code;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public long getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getState()
    {
        return state;
    }

    public long getZip_code()
    {
        return zipcode;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPhoneNumber(long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public void setZip_code(long zip_code)
    {
        this.zipcode = zip_code;
    }

    @Override
    public String toString()
    {
        return "ClientInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", state='" + state + '\'' +
                ", zip_code=" + zipcode +
                '}';
    }
}
