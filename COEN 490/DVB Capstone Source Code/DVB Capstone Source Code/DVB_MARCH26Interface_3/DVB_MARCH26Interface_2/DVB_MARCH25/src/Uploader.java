import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class Uploader
{
    //Default test Metadata
    static String category = "Movie";
    static String description = "Demo";


    static void uploadToFtp(String input,String scriptpath,String mp4path , String hostName, String username, String password)
    {
        //---------------File Names and Locations---------------
        String title = input;
        String uploadvideo = input +".mp4";
        String uploadthumbnail = input + ".jpg";
        String videolocation = mp4path + uploadvideo;
        String thumbnaillocation= mp4path + uploadthumbnail;

        //----------Metadata----------------------------
        String thumbnailuri = "thumbnails/" + uploadthumbnail;
        String videouri = "videos/" + uploadvideo;
        //Find today's date
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateRecorded = df.format(new Date());
        //Find duration of video
        ScriptWriter.duration(scriptpath,mp4path,uploadvideo);
        ScriptRunner.findmp4Duration(scriptpath);
        String length=ScriptRunner.mp4duration;

        //--------Login------------
        //String hostName = "198.27.70.45";
        //String username = "dvbcapst";
        //String password = "0bU1a4rlP9";

        //-------------FTP---------------
        FTPClient ftp = null;
        InputStream in = null;
        InputStream in2 = null;

        try
        {
            ftp = new FTPClient();
            ftp.connect(hostName);
            ftp.login(username, password);

            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            //Video Upload
            ftp.changeWorkingDirectory("/public_html/videos");

            int reply = ftp.getReplyCode();
            System.out.println("Received Reply from FTP Connection:" + reply);

            if (FTPReply.isPositiveCompletion(reply))
            {
                System.out.println("Connected Successfully");
            }

            File f1 = new File(videolocation);
            in = new FileInputStream(f1);

            ftp.storeFile(uploadvideo, in);

            System.out.println("SUCCESS Uploading video");

            //Thumbnail upload
            ftp.changeWorkingDirectory("/public_html/thumbnails");


            int reply2 = ftp.getReplyCode();
            System.out.println("Received Reply from FTP Connection:" + reply2);

            if (FTPReply.isPositiveCompletion(reply))
            {
                System.out.println("Connected Successfully");
            }

            File f2 = new File(thumbnaillocation);
            in2 = new FileInputStream(f2);

            ftp.storeFile(uploadthumbnail, in2);

            System.out.println("SUCCESS uploading thumbnail");

            //Upload metadata to database
            meta(title,category,length,description,dateRecorded,videouri,thumbnailuri);

            //Close session
            ftp.logout();
            ftp.disconnect();
            JOptionPane.showMessageDialog(null,"Upload Successful!");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void meta(String title,String category,String length,String description,String dateRecorded,String uri,String thumbnail) throws Exception
    {
        String urlParameters  = "category="+category+"&title="+title+"&length="+length+"&dateRecorded="+dateRecorded+"&uri="+uri+"&description="+description+"&thumbnail="+thumbnail+"&isValid=1";
        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int    postDataLength = postData.length;
        String request        = "http://dvbcapstone.com/insert.php";
        URL url               = new URL( request );
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setDoOutput( true );
        conn.setInstanceFollowRedirects( false );
        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty( "charset", "utf-8");
        conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        conn.setUseCaches( false );

        try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream()))
        {
            wr.write( postData );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    }
    public static void main(String[] args)
    {

    }
}
