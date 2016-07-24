import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class Folder
{
    public static boolean file_exists;
    public static String fileResult;

    public static void showFiles(String path)
    {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        fileResult = null;
        fileResult= "\n";
        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].isFile())
            {
                System.out.println("File " + listOfFiles[i].getName());
                fileResult = fileResult + "File " + listOfFiles[i].getName() + "\n";
            }
            else if (listOfFiles[i].isDirectory())
            {
                System.out.println("Directory " + listOfFiles[i].getName());
                fileResult = fileResult + "Directory " + listOfFiles[i].getName() + "\n";

            }
        }

    }

    public static void fileCheck(String path, String file)
    {
        boolean isValid = false;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++)
        {
            if (listOfFiles[i].getName().equals(file) && listOfFiles[i].isFile())
            {
                System.out.println("File Exists");
                System.out.println("");
                isValid = true;
                file_exists = true;
                break;
            }
        }

        if(isValid == false)
        {
            JOptionPane.showMessageDialog(null,"File does not exist");

            System.out.println("File does not exist...");
            System.out.println("");
            file_exists = false;
        }
    }

    public static void reset()
    {
        file_exists = false;
    }

    public static void clear(String scriptpath)
    {
        try {
            WriteFile data = new WriteFile(scriptpath, false);
            data.writeToFile("");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {

    }
}
