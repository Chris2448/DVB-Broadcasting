import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ScriptRunner
{
    //Variables regarding file duration(accessible from anywhere)
    public static String mp4duration;
    public static String tsduration;

    public static String result;

    public static void runScript(String scriptpath)
    {
        try
        {
            ProcessBuilder b = new ProcessBuilder(scriptpath);
            b.redirectErrorStream(true);

            //Execute Bash script
            Process p = b.start();
            System.out.println("Process Running...");
            System.out.println("");

            //Get output from terminal
            InputStream is = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            result = null;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
                result = result + line + "\n";


            }
            //JOptionPane.showMessageDialog(null,result);


            //Clear contents of script after commands have been executed
            WriteFile data = new WriteFile(scriptpath,false);
            System.out.println("(Preparing to clear " + scriptpath + "...)");
            data.writeToFile("");
            System.out.println("(" + scriptpath + " is now cleared.)");
            System.out.println("");

            //JOptionPane.showMessageDialog(null,"Process Complete!");

        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void install(String installscriptpath)
    {
        try
        {
            ProcessBuilder b = new ProcessBuilder(installscriptpath);
            b.redirectErrorStream(true);

            //Execute Bash script
            Process p = b.start();
            System.out.println("Installing...");
            System.out.println("");

            //Get output from terminal
            InputStream is = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;

            while ((line = reader.readLine()) != null)
                System.out.println(line);

            JOptionPane.showMessageDialog(null,"Install Complete");
        }

        catch(IOException e)
        {
                e.printStackTrace();
        }

    }

    public static void findmp4Duration(String scriptpath)
    {
        try
        {
            ProcessBuilder b = new ProcessBuilder(scriptpath);
            b.redirectErrorStream(true);

            //Execute Bash script
            Process p = b.start();
            System.out.println("Calculating .mp4 duration...");
            System.out.println("");

            //Get output from terminal
            InputStream is = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;

           if ((line = reader.readLine()) != null)
                //System.out.println(line);
               mp4duration = line;

            //Clear contents of script after commands have been executed
            WriteFile data = new WriteFile(scriptpath,false);
            data.writeToFile("");
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void findtsDuration(String scriptpath)
    {
        try
        {
            ProcessBuilder b = new ProcessBuilder(scriptpath);
            b.redirectErrorStream(true);

            //Execute Bash script
            Process p = b.start();
            System.out.println("Calculating .ts duration");
            System.out.println("");

            //Get output from terminal
            InputStream is = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;

            if ((line = reader.readLine()) != null)
                //System.out.println(line);
                tsduration = line;

            //Clear contents of script after commands have been executed
            WriteFile data = new WriteFile(scriptpath,false);
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


