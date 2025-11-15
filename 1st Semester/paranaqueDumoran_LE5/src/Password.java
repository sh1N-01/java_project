/*
 * Class: Password
 * -> This class is responsible for generating and storing a true PIN and its encrypted version.
 *    It provides functionality to retrieve the true and encrypted PINs securely.
 * 
 * Exclusive Functions:
 * Password() - Constructor that initializes the true PIN and generates an encrypted PIN
 * @param - int pin, @return - none
 * 
 * getEncryptedPIN() - Returns a copy of the encrypted PIN array
 * @param - none, @return - int[]
 * 
 * getTruePIN() - Returns a copy of the true PIN array
 * @param - none, @return - int[]
 */
import java.util.Random;

public class Password 
{
    private int[] encryptedPIN = new int[9];
    private int[] truePIN = new int[9];

    public Password(int pin)
    {
        encryption(pin);
    }

    public int[] getEncryptedPIN()
    {
        return this.encryptedPIN.clone();
    }

    public int[] getTruePIN()
    {
        return this.truePIN.clone();
    }

    public void encryption(int pin)
    {
        Random rand = new Random();

        for(int i = 0; i < 9; i++)
        {
            int r = rand.nextInt(3) + 1;
            encryptedPIN[8-i] = r;
            truePIN[8-i] = (pin % 10);

            pin /= 10;
        }
    }
}
