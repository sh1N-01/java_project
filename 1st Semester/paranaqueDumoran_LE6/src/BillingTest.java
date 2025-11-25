/*
 * Class: BillingTest
 * -> This class contains unit tests to verify the functionality of the Doctor, Patient, and Billing classes.
 * * Exclusive Functions:
 * testDoctor() - Method for testing the initialization and retrieval of attributes for the Doctor class.
 * @param - none
 * @return - void
 * * testPatient() - Method for testing the initialization and default values of the Patient class.
 * @param - none
 * @return - void
 * * testBilling() - Method for testing the Billing class integration with Patient and Doctor objects and verifying billing amounts.
 * @param - none
 * @return - void
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BillingTest 
{
    @Test
    public void testDoctor()
    {
        Doctor doc1 = new Doctor("Alice", "Vet", 120000);
        Doctor doc2 = new Doctor("Bob", "Surgeon", 150000);
        
        assertFalse(doc1.getName() == doc2.getName());
        assertFalse(doc1.getSpecialty() == doc2.getSpecialty());
        assertFalse(doc1.getOfficeVisitFee() == doc2.getOfficeVisitFee());
    }

    @Test
    public void testPatient()
    {
        Patient pat1 = new Patient("Charlie", "A001");
        Patient pat2 = new Patient();

        assertFalse(pat1.getName() == pat2.getName());
        assertTrue(pat2.getName() == "No name yet");
        assertTrue(pat2.getIdNumber() == "Unknown ID");
    }
    
    @Test
    public void testBilling()
    {
        Patient pat = new Patient("David", "B002");
        Doctor doc = new Doctor("Eve", "Cardiologist", 200000);
        Billing bill1 = new Billing(pat, doc);
        Billing bill2 = new Billing();

        assertFalse(bill1.getPatient().getName() == bill2.getPatient().getName());
        assertFalse(bill1.getDoctor().getName() == bill2.getDoctor().getName());
        assertFalse(bill1.getBillingAmount() == bill2.getBillingAmount());
    }
}
