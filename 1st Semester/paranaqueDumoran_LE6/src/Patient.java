/*
 * Class: Patient
 * -> This class represents a patient, inheriting from Person, and manages the specific attribute of an identification number.
 * * Exclusive Functions:
 * Patient() - Constructor for initializing the patient with default values.
 * @param - none
 * @return - none
 * * Patient(String, String) - Constructor for initializing the patient with a specific name and ID number.
 * @param - String, String
 * @return - none
 * * getIdNumber() - Method for retrieving the patient's ID number.
 * @param - none
 * @return - String
 * * setIdNumber() - Method for updating the patient's ID number.
 * @param - String
 * @return - void
 * * toString() - Method for generating a string representation of the patient's details.
 * @param - none
 * @return - String
 * * equals() - Method for comparing this patient instance with another object for equality.
 * @param - Object
 * @return - boolean
 */
public class Patient extends Person 
{
    private String idNumber;

    public Patient() 
    {
        super();
        this.idNumber = "Unknown ID";
    }

    public Patient(String name, String idNumber) 
    {
        super(name);
        this.idNumber = idNumber;
    }

    public String getIdNumber() 
    {
        return idNumber;
    }


    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() 
    {
        return super.toString() + "\nID Number: " + this.idNumber;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (!(obj instanceof Patient)) 
        {
            return false;
        }
        
        Patient other = (Patient) obj;

        return super.hasSameName(other) && 
               this.idNumber.equalsIgnoreCase(other.idNumber);
    }
}