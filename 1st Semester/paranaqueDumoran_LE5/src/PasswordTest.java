/*
 * Class: PasswordTest
 * -> This class is responsible for testing the functionality of the Password class,
 *    including the encryption process and the retrieval of encrypted PINs.
 * 
 * Exclusive Functions:
 * testPasswordEncrypter() - Tests the encryption process and ensures that the encrypted PINs are unique
 * @param - none, @return - void
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PasswordTest 
{
    @Test
    void testPasswordEncrypter()
    {
        int pin = 999999999;

        Password password = new Password(pin);

        int[] encryptedPIN1 = password.getEncryptedPIN();

        password.encryption(pin);
        int[] encryptedPIN2 = password.getEncryptedPIN();

        password.encryption(pin);
        int[] encryptedPIN3 = password.getEncryptedPIN();

        assertTrue(pin == 999999999);
        assertFalse(encryptedPIN1 == encryptedPIN2);
        assertFalse(encryptedPIN2 == encryptedPIN3);
    }
}
