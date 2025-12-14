import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class helperScore 
{
    public static final String SAVE_FILE = "src/assets/highscore.txt";

    public static int loadHighscore() 
    {
        File file = new File(SAVE_FILE);
        
        
        if (!file.exists()) 
        {
            return 0;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line = reader.readLine();
            if (line != null) 
            {
                return Integer.parseInt(line.trim());
            }
        } 
        catch (IOException | NumberFormatException e) 
        {
            e.printStackTrace();
        }
        
        return 0; 
    }

    public static void saveHighscore(int newScore) 
    {
        int oldScore = loadHighscore();
        if (newScore > oldScore)
        {
            try (FileWriter writer = new FileWriter(SAVE_FILE)) 
            {
                writer.write(String.valueOf(newScore));
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
}
