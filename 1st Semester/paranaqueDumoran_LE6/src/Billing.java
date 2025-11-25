/*
 * Class: Billing
 * -> This class represents a billing record that associates a specific Patient 
 * with a Doctor and calculates the total amount due based on the doctor's fee.
 * * Exclusive Functions:
 * Billing() - Constructor for initializing the billing record with default Patient and Doctor objects.
 * @param - none
 * @return - none
 * * Billing(Patient, Doctor) - Constructor for initializing the billing record with specific objects.
 * @param - Patient, Doctor
 * @return - none
 * * getPatient() - Method for retrieving the associated Patient object.
 * @param - none
 * @return - Patient
 * * setPatient() - Method for updating the associated Patient object.
 * @param - Patient
 * @return - void
 * * getDoctor() - Method for retrieving the associated Doctor object.
 * @param - none
 * @return - Doctor
 * * setDoctor() - Method for updating the associated Doctor object.
 * @param - Doctor
 * @return - void
 * * getBillingAmount() - Method for retrieving the doctor's office visit fee.
 * @param - none
 * @return - double
 * * toString() - Method for generating a formatted string summary of the billing record.
 * @param - none
 * @return - String
 * * equals() - Method for comparing this billing record with another object for equality.
 * @param - Object
 * @return - boolean
 */
public class Billing 
{
    private Patient patient;
    private Doctor doctor;

    public Billing() 
    {
        this.patient = new Patient();
        this.doctor = new Doctor();
    }

    public Billing(Patient patient, Doctor doctor) 
    {
        this.patient = patient;
        this.doctor = doctor;
    }

    public Patient getPatient() 
    {
        return patient;
    }

    public void setPatient(Patient patient) 
    {
        this.patient = patient;
    }

    public Doctor getDoctor()
    {
        return doctor;
    }

    public void setDoctor(Doctor doctor) 
    {
        this.doctor = doctor;
    }
    
    
    public double getBillingAmount() 
    {
        return doctor.getOfficeVisitFee();
    }

    @Override
    public String toString() 
    {
        return "--- Billing Record ---\n" +
               "Patient: " + patient.getName() + " (" + patient.getIdNumber() + ")\n" +
               "Doctor: " + doctor.getName() + " (" + doctor.getSpecialty() + ")\n" +
               "Total Due: $" + String.format("%.2f", getBillingAmount());
    }

    @Override
    public boolean equals(Object obj) 
    {
        if(!(obj instanceof Billing))
        {
            return false;
        }

        Billing other = (Billing) obj;
        return this.patient.equals(other.patient) && 
               this.doctor.equals(other.doctor);
    }
}