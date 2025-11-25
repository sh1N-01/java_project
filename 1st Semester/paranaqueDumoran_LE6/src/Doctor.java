/*
 * Class: Doctor
 * -> This class represents a doctor, inheriting from Person, and manages specific details regarding medical specialty and fees.
 * * Exclusive Functions:
 * Doctor() - Constructor for initializing the doctor with default values.
 * @param - none
 * @return - none
 * * Doctor(String, String, double) - Constructor for initializing the doctor with a specific name, specialty, and fee.
 * @param - String, String, double
 * @return - none
 * * setSpecialty() - Method for updating the doctor's medical specialty.
 * @param - String
 * @return - void
 * * setOfficeVisitFee() - Method for updating the cost of an office visit.
 * @param - double
 * @return - void
 * * getSpecialty() - Method for retrieving the doctor's medical specialty.
 * @param - none
 * @return - String
 * * getOfficeVisitFee() - Method for retrieving the cost of an office visit.
 * @param - none
 * @return - double
 * * toString() - Method for generating a string representation of the doctor's details.
 * @param - none
 * @return - String
 * * equals() - Method for comparing this doctor instance with another doctor object.
 * @param - Doctor
 * @return - boolean
 */
public class Doctor extends Person
{
    private String specialty;
    private double officeVisitFee;

    public Doctor()
    {
        super();
        specialty = "General Practitioner";
        officeVisitFee = 0.0;
    }

    public Doctor(String name, String specialty, double officeVisitFee)
    {
        super(name);
        this.specialty = specialty;
        this.officeVisitFee = officeVisitFee;
    }
    
    public void setSpecialty(String specialty)
    {
        this.specialty = specialty;
    }

    public void setOfficeVisitFee(double officeVisitFee)
    {
        this.officeVisitFee = officeVisitFee;
    }

    public String getSpecialty()
    {
        return this.specialty;
    }

    public double getOfficeVisitFee()
    {
        return this.officeVisitFee;
    }

    @Override
    public String toString() 
    {
        return super.toString() + "\n" +
               "Specialty: " + this.specialty + "\n" +
               "Office Visit Fee: $" + String.format("%.2f", this.officeVisitFee);
    }

    public boolean equals(Doctor otherDoctor)
    {
        return super.hasSameName(otherDoctor) &&
               this.specialty.equals(otherDoctor.specialty) &&
               this.officeVisitFee == otherDoctor.officeVisitFee;
    }
}
