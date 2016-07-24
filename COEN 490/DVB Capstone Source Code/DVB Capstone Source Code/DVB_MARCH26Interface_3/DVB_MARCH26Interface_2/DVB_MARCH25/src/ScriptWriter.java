import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class ScriptWriter
{


    //This function will delete a file if it already exists.
    //If the name of an output file already exists,ffmpeg just freezes rather than overwrite it.
    //This function fixes this issue.
    //For example, test_cut.ts already exists, but you wish to name the output of a file you are cutting as test_cut.ts.
    //This function will delete test_cut.ts so that you can create a new output file with the same name.
    public static void delete(String path, String filetodelete)
    {
        File file = new File(path + filetodelete);
        if(file.exists())
        {
            file.delete();
        }
    }

    public static void tsCutter(String scriptpath,String tspath,String input)
    {
        System.out.println("------------------Cut TS FILES-----------------------------");

        //Automatically define file extensions(input and output)
        String inputTS = input + ".ts";
        String outputcutTS = input + "_cut.ts";

        //Initialize
        String outputcutTSstart = null;
        String outputcutTSduration = null;

        //Ensure that it cannot create a cut file larger than the original
        boolean valid = false;
        while(!valid) {
            System.out.println("The duration of this file is: " + ScriptRunner.tsduration + " s");
            //Convert string to double for comparison(for length of original TS file)
            double tsoriginalduration = Double.parseDouble(ScriptRunner.tsduration);

            double start_seconds = 0;
            boolean cutstart_valid = false;
            while(!cutstart_valid)
            {
                try {
                    //Input starting point of new video
                    System.out.println("Enter starting point of the video");
                    System.out.println("Make sure to use the HH:MM:SS format");

                    outputcutTSstart = JOptionPane.showInputDialog("Enter starting point of the video\n Make sure to use the HH:MM:SS format");

                    //Convert hh:mm:ss to seconds
                    TimeFormat.stringtoseconds(outputcutTSstart);
                    start_seconds = TimeFormat.duration_seconds;
                    cutstart_valid = true;
                }
                catch (ArrayIndexOutOfBoundsException a)
                {
                    JOptionPane.showMessageDialog(null,"Invalid \n Please use hh:mm:ss format");
                    System.out.println("INVALID");
                    System.out.println("Please use hh:mm:ss format");
                }
                catch(NumberFormatException n)
                {
                    JOptionPane.showMessageDialog(null,"Invalid \n Please use hh:mm:ss format");

                    System.out.println("INVALID");
                    System.out.println("Please use hh:mm:ss format");
                }
            }


            //Input duration of new video
            System.out.println("Enter duration of new video in seconds");


            outputcutTSduration = JOptionPane.showInputDialog("Enter duration of new video in seconds\n");

            //Convert string to double
            double newduration = Double.parseDouble(outputcutTSduration);

            //Total duration of cut video
            double totaldurationincut = start_seconds + newduration;

            //Compare
            if (totaldurationincut <= tsoriginalduration && newduration > 0 && totaldurationincut > 0) {
                valid = true;
            } else {
                JOptionPane.showMessageDialog(null,"Invalid \n Original Video Duration is: " + tsoriginalduration);

                System.out.println("Original Video Duration is: " + tsoriginalduration);
                System.out.println("INVALID");
            }
        }

        //Delete file if it already exists
        delete(tspath,outputcutTS);

        try
        {
            //Write Terminal Commands to File
            WriteFile data = new WriteFile(scriptpath, false);
            data.writeToFile("ffmpeg -i " + tspath + inputTS + " -ss " + outputcutTSstart + " -codec copy -t " + outputcutTSduration + " " + tspath + outputcutTS);

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void ts_mp4Converter(String input,String scriptpath,String tspath,String mp4path)
    {
        System.out.println("------------------TS to MP4 CONVERSION-----------------------");

        //Automatically define file extensions
        String inputTS = input + ".ts";
        String outputMP4 = input + ".mp4";

        //Delete file if it already exists
        delete(mp4path,outputMP4);

        try {
            //Write Terminal Commands to File
            WriteFile data = new WriteFile(scriptpath, false);

            //TS to MP4 Conversion
            data.writeToFile("ffmpeg -i " + tspath + inputTS + " -vcodec h264 -acodec aac -strict -2 " + mp4path + outputMP4);
            //data.writeToFile("");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }


    public static void extract(String scriptpath, String mp4path, String input)
    {
        System.out.println("------------------JPG and Metadata Extraction-----------------------");

        //Automatically define file extensions
        String outputMP4 = input + ".mp4";
        String JPG= input + ".jpg";
        String outputmeta = input + "_meta.txt";

        String frame=null;

        boolean valid = false;
        while(!valid)
        {
            System.out.println("The duration of this file is: " + ScriptRunner.mp4duration);
            //Convert string to double for comparison(for length of original MP4 file)
            double mp4durationdouble = Double.parseDouble(ScriptRunner.mp4duration);

            boolean frame_valid = false;

            while(!frame_valid)
            {
                try
                {
                    //Input frame to extract jpg image
                    System.out.println("Enter the time in which you wish a 100x100 frame to be extracted");
                    System.out.println("Make sure you use the HH:MM:SS format");

                    frame = JOptionPane.showInputDialog("Enter the time in which you wish a 100x100 frame to be extracted\n Make sure you use the HH:MM:SS format");

                    //Convert hh:mm:ss to seconds
                    TimeFormat.stringtoseconds(frame);
                    System.out.println("Frame location in seconds is: " + TimeFormat.duration_seconds);
                    frame_valid = true;
                }
                catch (ArrayIndexOutOfBoundsException a)
                {
                    JOptionPane.showMessageDialog(null,"Invalid \n Please use hh:mm:ss format");
                    System.out.println("Invalid");
                    System.out.println("Please use hh:mm:ss format");
                }
                catch (NumberFormatException n)
                {
                    JOptionPane.showMessageDialog(null,"Invalid \n Please use hh:mm:ss format");

                    System.out.println("Invalid");
                    System.out.println("Please use hh:mm:ss format");
                }
            }
            //Compare
            if (TimeFormat.duration_seconds <= (mp4durationdouble-1) && TimeFormat.duration_seconds > 0)
            {
                valid = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"MP4 Original Duration is: " + mp4durationdouble + "\n Invalid Frame");

                System.out.println("MP4 Original Duration is: " + mp4durationdouble);
                System.out.println("INVALID FRAME, PLEASE TRY AGAIN");
            }
        }
        //Delete file if it already exists
        delete(mp4path,JPG);
        delete(mp4path,outputmeta);

        try{
            //Write Terminal Commands to File
            WriteFile data = new WriteFile(scriptpath,true);

            //Extract 100x100 Image from Resulting MP4
            data.writeToFile("ffmpeg -ss " + frame +" -i "+ mp4path + outputMP4 + " -t 1 -s 100x100 -vframes 1 "+ mp4path + JPG);
            data.writeToFile("");

            //Extract metadata from Resulting MP4
            data.writeToFile("exiftool " + mp4path+ outputMP4 + " >" + mp4path + outputmeta);
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void user_extract(String scriptpath, String mp4path, String input)
    {
        System.out.println("------------------JPG and Metadata Extraction-----------------------");

        //Automatically define file extensions
        String outputMP4 = input + ".mp4";
        String JPG= input + ".jpg";
        String outputmeta = input + "_meta.txt";

        String frame=null;

        boolean valid = false;
        while(!valid)
        {
            System.out.println("The duration of this file is: " + ScriptRunner.mp4duration);
            //Convert string to double for comparison(for length of original MP4 file)
            double mp4durationdouble = Double.parseDouble(ScriptRunner.mp4duration);


                try
                {
                    //Find location of middle of video and cast it to int
                    double midframe = mp4durationdouble/2;
                    int midframeint = (int) midframe;

                    //COnvert from int to hh:mm:ss
                    frame = TimeFormat.secondstostring(midframeint);
                    System.out.println("JPG will be extracted from the following frame location: " + frame);

                    //Convert hh:mm:ss to seconds
                    TimeFormat.stringtoseconds(frame);
                    System.out.println("Frame location in seconds is: " + TimeFormat.duration_seconds);

                }
                catch (ArrayIndexOutOfBoundsException a)
                {
                    JOptionPane.showMessageDialog(null,"Invalid \n Please use hh:mm:ss format");

                    System.out.println("Invalid");
                    System.out.println("Please use hh:mm:ss format");
                }
                catch (NumberFormatException n)
                {
                    JOptionPane.showMessageDialog(null,"Invalid \n Please use hh:mm:ss format");

                    System.out.println("Invalid");
                    System.out.println("Please use hh:mm:ss format");
                }

            //Compare
            if (TimeFormat.duration_seconds <= (mp4durationdouble-1))
            {
                valid = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null,"MP4 Original Duration is: " + mp4durationdouble + "\n Invalid Frame");

                System.out.println("MP4 Original Duration is: " + mp4durationdouble);
                System.out.println("INVALID FRAME, PLEASE TRY AGAIN");
            }
        }
        //Delete file if it already exists
        delete(mp4path,JPG);
        delete(mp4path,outputmeta);

        try{
            //Write Terminal Commands to File
            WriteFile data = new WriteFile(scriptpath,true);

            //Extract 100x100 Image from Resulting MP4
            data.writeToFile("ffmpeg -ss " + frame +" -i "+ mp4path + outputMP4 + " -t 1 -s 100x100 -vframes 1 "+ mp4path + JPG);
            data.writeToFile("");

            //Extract metadata from Resulting MP4
            data.writeToFile("exiftool " + mp4path+ outputMP4 + " >" + mp4path + outputmeta);
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void tsSender(String input, String scriptpath, String tspath, String titletxt, String titlescriptpath)
    {
        System.out.println("------------------TS Sender-----------------------");



        //Automatically define file extension
        String sendfile = input + ".ts";


            //Input IP Address to send to
            System.out.println("Enter IP Address to which you wish to send");

            String clientIP = JOptionPane.showInputDialog("Enter IP Address to which you wish to send");

            //String clientIP = "192.168.0.100";
            //Input Port to send to
            System.out.println("Enter the Port of the Client");

            String clientPort = JOptionPane.showInputDialog("Enter the Port of the Client");
            //String clientPort = "7777";


        try{
            //Write Terminal Commands to File
            WriteFile data = new WriteFile(scriptpath,false);
            //Write title to txt. Second parameter is set to false to overwrite entire file everytime something is written to it.
            WriteFile title = new WriteFile(titletxt,false);
            //Write Terminal Command to send title
            WriteFile sendtitle = new WriteFile(titlescriptpath,false);

            //Write title to txt
            title.writeToFile(sendfile);
            //Send title
            sendtitle.writeToFile("cat " + tspath + "title.txt" + " | pv -L 2M | nc -u -w 5 " + clientIP + " " + clientPort);
            //sendtitle.writeToFile("");
            //Send TS
            data.writeToFile("cat " + tspath + sendfile + " | pv -L 2M | nc -u -w 1 " + clientIP + " " + clientPort);
            //data.writeToFile("");
            //Calculate hash of entire file before sending then validate
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void atscSender(String input, String scriptpath, String tspath,String IP, String port)
    {
        System.out.println("------------------TS Sender-----------------------");

        //Automatically define file extension
        String sendfile = input + ".ts";
        try
        {
            //Write Terminal Commands to File
            WriteFile data = new WriteFile(scriptpath,false);

            //Send TS
            data.writeToFile("cat " + tspath + sendfile + " | pv -L 2M | nc -u -w 1 " + IP + " " + port);
            //data.writeToFile("");
            //Calculate hash of entire file before sending then validate
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void tsReceiver(String input,String scriptpath, String tspath, String serverPort)
    {
        System.out.println("------------------TS Receiver-----------------------");

        //Automatically define file extension
        String receivefile = input + ".ts";

        //Delete if already exists
        delete(tspath,receivefile);

        try{
            //Write Terminal Commands to File
            WriteFile data = new WriteFile(scriptpath,false);

            //Receive TS
            //-w 1 specifies that it will wait for 1 second after receving file and then closes
            data.writeToFile("nc -u -l -w 1 "+serverPort + " >" + tspath + receivefile);
            //data.writeToFile("");
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void txtReceiver(String scriptpath, String tspath, String serverPort)
    {
        System.out.println("------------------Txt Receiver-----------------------");

        try{
            //Write Terminal Commands to File
            WriteFile data = new WriteFile(scriptpath,false);

            //Receive TS
            //-w 1 specifies that it will wait for 1 second after receving file and then closes
            data.writeToFile("nc -u -l -w 1 "+serverPort + " >" + tspath + "title.txt");
            //data.writeToFile("");
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void rename(String scriptpath, String tspath, String txtfiletitle, String receivedfile)
    {
        String receivedfile_ext = receivedfile + ".ts";
        try
        {
            WriteFile data = new WriteFile(scriptpath,false);
            data.writeToFile("mv " + tspath + receivedfile_ext + " " + tspath + txtfiletitle);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void duration(String scriptpath, String filepath, String file) //Does not automatically set extensions
    {
        //This function writes a bash script that can find the length of a video and output it as string
        try
        {
            //Write Terminal Commands to File
            WriteFile data = new WriteFile(scriptpath, false);
            data.writeToFile("ffprobe -i " + filepath + file + " " + "-show_entries format=duration -v quiet -of csv=\"p=0\"");
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void startVLC(String scriptpath, String filepath, String file)
    {
        WriteFile start = new WriteFile(scriptpath, false);
        try
        {
            start.writeToFile("vlc " + "\"" + filepath + file + "\"");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void stopVLC(String scriptpath)
    {
        WriteFile end = new WriteFile(scriptpath, false);
        try
        {
            end.writeToFile("kilall vlc");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void network(String scriptpath)
    {
        WriteFile network = new WriteFile(scriptpath,false);
        try
        {
        network.writeToFile("ifconfig");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public static void main(String[] args)
    {


    }
}

