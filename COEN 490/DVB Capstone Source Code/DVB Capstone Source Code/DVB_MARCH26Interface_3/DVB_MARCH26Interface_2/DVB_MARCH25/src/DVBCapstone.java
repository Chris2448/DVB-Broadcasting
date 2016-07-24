import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class DVBCapstone {


    private JFrame frame;
    private JPanel panelMainMenu;
    private JPanel panelAdminMenu;
    private JPanel panelUserMenu;
    private JPanel panelInstall;
    private JPanel panelSendToClient;
    private JPanel panelSendToATSC;
    private JPanel panelReceive;
    private JPanel panelCut;
    private JPanel panelConvert;
    private JPanel panelUpload;
    private JPanel panelNetworkDetails;
    private JPanel panelViewCurrentPaths;
    private JPanel panelViewFolderContents;
    private JPanel panelChangeDefaultPaths;
    private JPanel panelPlayVideos;

/*
    //Default absolute paths, used to facilitate testing
    static String installscriptpath = "/home/chris/Desktop/BashScripts/Install.sh";
    static String scriptpath = "/home/chris/Desktop/BashScripts/script.sh";
    static String scriptfolderpath = "/home/chris/Desktop/BashScripts/";
    static String tspath = "/home/chris/Desktop/TS/";
    static String mp4path = "/home/chris/Desktop/MP4/";
    static String titletxt = "/home/chris/Desktop/TS/title.txt";
    static String titlepath = "/home/chris/Desktop/BashScripts/titlescript.sh";
    */


    //Default Relative Paths

    static String installscriptpath = "./BashScripts/Install.sh";
    static String scriptpath = "./BashScripts/script.sh";
    static String scriptfolderpath = "./BashScripts/";
    static String tspath = "./TS/";
    static String mp4path = "./MP4/";
    static String titletxt = "./TS/title.txt";
    static String titlepath = "./BashScripts/titlescript.sh";


    //Default Parameters for ATSC Modulator
    static String IP = "192.168.0.100";
    static String Port = "7777";

    //Default Credentials
    static String hostName = "198.27.70.45";
    static String username = "dvbcapst";
    static String password = "0bU1a4rlP9";


    /**
     * Launch the application.
     */
    public static void main(String[] args) throws IOException {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DVBCapstone window = new DVBCapstone();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Create the application.
     */
    public DVBCapstone() {
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));
        frame.setTitle("DVB Application");

        initializeMainMenu();
        initializeInstall();
        initializeSendToClient();
        initializeSendToATSC();
        initializeReceive();
        initializeCut();
        initializeConvert();
        initializeUpload();
        initializeNetworkDetails();
        initializeViewCurrentPaths();
        initializeViewFolderContents();
        initializeChangeDefaultPaths();
        initializePlayVideos();
        initializeAdminMenu();
        initializeUserMenu();

    }

    private void initializeMainMenu() {

        panelMainMenu = new JPanel();
        frame.getContentPane().add(panelMainMenu, "name_16047687241529");
        panelMainMenu.setLayout(null);
        panelMainMenu.setVisible(true);


        JButton btnAdminButton = new JButton("Admin Mode");
        btnAdminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelAdminMenu.setVisible(true);
                panelMainMenu.setVisible(false);
                panelUserMenu.setVisible(false);
            }
        });
        //btnAdminButton.setBorder(null);
        btnAdminButton.setBounds(92, 157, 120, 50);
        panelMainMenu.add(btnAdminButton);

        JButton btnUserButton = new JButton("User Mode");
        btnUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelAdminMenu.setVisible(false);
                panelMainMenu.setVisible(false);
                panelUserMenu.setVisible(true);
            }
        });
        btnUserButton.setBounds(342, 157, 117, 50);
        panelMainMenu.add(btnUserButton);

        JLabel lblWelcomeToDigital = new JLabel("Welcome To Digital Video Broadcasting Application ");
        lblWelcomeToDigital.setBounds(46, 26, 367, 35);
        panelMainMenu.add(lblWelcomeToDigital);

        JLabel lblSelectMode = new JLabel("Please select a mode of operation:");
        lblSelectMode.setBounds(46, 110, 260, 16);
        panelMainMenu.add(lblSelectMode);
    }

    private void initializeAdminMenu() {

        panelAdminMenu = new JPanel();
        frame.getContentPane().add(panelAdminMenu, "name_16052752994452");
        panelAdminMenu.setLayout(null);
        panelAdminMenu.setVisible(false);

        JComboBox adminComboBox = new JComboBox();
        adminComboBox.setModel(new DefaultComboBoxModel(new String[] {"1. Install Packages", "2. Send a file to Client","3. Send a file to ATSC Modulator", "4. Receive a file ", "5. Cut a video for demo purposes", "6. Convert TS to MP4", "7. Upload MP4 File to website", "8. View Network details"
                ,"9. View Current Paths","10. View Folder Contents", "11. Change Default Paths", "12. Play Videos"}));
        adminComboBox.setMaximumRowCount(11);
        adminComboBox.setBounds(60, 100, 329, 37);
        panelAdminMenu.add(adminComboBox);

        JLabel lblPleaseSelectAn = new JLabel("Please select an option from the drop down menu:");
        lblPleaseSelectAn.setBounds(48, 38, 341, 16);
        panelAdminMenu.add(lblPleaseSelectAn);

        JButton btnAdminNext = new JButton("Next");
        btnAdminNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panelAdminMenu.setVisible(false);
                panelMainMenu.setVisible(false);
                panelUserMenu.setVisible(false);
                panelInstall.setVisible(false);
                panelSendToClient.setVisible(false);
                panelSendToATSC.setVisible(false);
                panelReceive.setVisible(false);
                panelCut.setVisible(false);
                panelConvert.setVisible(false);
                panelUpload.setVisible(false);
                panelNetworkDetails.setVisible(false);

                // does it work?
                Folder.clear(scriptpath);
                Folder.clear(titlepath);


                int choice = adminComboBox.getSelectedIndex() + 1;
                switch(choice){
                    case 0:
                        break;
                    case 1:
                        // install
                        panelInstall.setVisible(true);
                        break;
                    case 2:
                        // send to client
                        panelSendToClient.setVisible(true);
                        break;
                    case 3:
                        // send to ATSC
                        panelSendToATSC.setVisible(true);

                        break;
                    case 4:
                        // Receive
                        panelReceive.setVisible(true);

                        break;
                    case 5:
                        //cut
                        panelCut.setVisible(true);

                        break;
                    case 6:
                        //convert
                        panelConvert.setVisible(true);

                        break;

                    case 7:
                        // upload
                        panelUpload.setVisible(true);

                        break;

                    case 8:
                        // network details
                        panelNetworkDetails.setVisible(true);

                        break;
                    case 9:
                        // view current paths
                        panelViewCurrentPaths.setVisible(true);
                        break;

                    case 10:
                        // view folder contents
                        panelViewFolderContents.setVisible(true);
                        break;

                    case 11:
                        // change default paths
                        panelChangeDefaultPaths.setVisible(true);
                        break;

                    case 12:
                        // play videos
                        panelPlayVideos.setVisible(true);
                        break;
                    default:
                        break;
                }

            }
        });
        btnAdminNext.setBounds(297, 225, 117, 29);
        panelAdminMenu.add(btnAdminNext);

        JButton btnAdminBack = new JButton("Back");
        btnAdminBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panelAdminMenu.setVisible(false);
                panelMainMenu.setVisible(true);
                panelUserMenu.setVisible(false);
                panelInstall.setVisible(false);
            }
        });
        btnAdminBack.setBounds(89, 225, 117, 29);
        panelAdminMenu.add(btnAdminBack);
    }

    private void initializeUserMenu() {

        panelUserMenu = new JPanel();
        frame.getContentPane().add(panelUserMenu, "name_16055971319058");
        panelUserMenu.setLayout(null);


        final JButton btnUserSendButton = new JButton("Send TS file via UDP");
        final JButton btnUserReceiveButton = new JButton("Receive TS file, convert to MP4 and upload ");
        final JButton btnUserBackButton = new JButton("Back");

        JTextArea textAreaUser = new JTextArea();
        textAreaUser.append("---------------------------------------------USER MODE-------------------------------------------\n");

        JScrollPane scrollPaneUser = new JScrollPane(textAreaUser);
        scrollPaneUser.setBounds(83, 193, 482, 229);
        panelUserMenu.add(scrollPaneUser);

        btnUserSendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnUserBackButton.setEnabled(false);
                btnUserReceiveButton.setEnabled(false);
                btnUserSendButton.setEnabled(false);
                try {
                    //Clear bash scripts
                    Folder.clear(scriptpath);
                    Folder.clear(titlepath);

                    textAreaUser.append("---------------------SEND DATA TO CLIENT SYSTEM---------------------\n");

                    String sendfile = null;
                    String sendfile_ext;
                    Folder.reset();
                    while (Folder.file_exists == false) {

                        sendfile = JOptionPane.showInputDialog("Enter file name to send");
                        sendfile_ext = sendfile + ".ts";

                        Folder.fileCheck(tspath, sendfile_ext);
                    }

                    ScriptWriter.tsSender(sendfile, scriptpath, tspath, titletxt, titlepath);
                    textAreaUser.append("Preparing to send txt file...\n");
                    textAreaUser.update(textAreaUser.getGraphics());

                    ScriptRunner.runScript(titlepath);
                    textAreaUser.append("Text file has been sent\n");
                    textAreaUser.update(textAreaUser.getGraphics());

                    textAreaUser.append("Preparing to send TS file...\n");
                    textAreaUser.update(textAreaUser.getGraphics());
                    ScriptRunner.runScript(scriptpath);
                    textAreaUser.append("TS file has been sent\n");
                    textAreaUser.append("Process Complete!\n");
                    textAreaUser.append("\n");
                    textAreaUser.update(textAreaUser.getGraphics());

                    //Delete text file
                    ScriptWriter.delete(tspath, "title.txt");

                    btnUserBackButton.setEnabled(true);
                    btnUserReceiveButton.setEnabled(true);
                    btnUserSendButton.setEnabled(true);

                }

                catch (InputMismatchException i)
                {
                    System.out.println("INVALID");
                    System.out.println("");
                }
            }
        });
        btnUserSendButton.setBounds(83, 455, 217, 29);
        panelUserMenu.add(btnUserSendButton);


        btnUserReceiveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnUserBackButton.setEnabled(false);
                btnUserReceiveButton.setEnabled(false);
                btnUserSendButton.setEnabled(false);
                try {
                    //Clear bash scripts
                    Folder.clear(scriptpath);
                    Folder.clear(titlepath);
                    textAreaUser.append("---------------------RECEIVE DATA AND UPLOAD TO WEBSITE---------------------\n");
                    textAreaUser.append("NOTE: The program will listen to a specified port until data is received\n");

                    //Input port

                    String user_Port = JOptionPane.showInputDialog("Enter Port to listen to");

                    //Receive txt file
                    ScriptWriter.txtReceiver(titlepath, tspath, user_Port);
                    textAreaUser.append("Listening to port: " + user_Port+"\n");
                    textAreaUser.update(textAreaUser.getGraphics());
                    textAreaUser.append("Preparing to receive txt file containing metdata...\n");
                    textAreaUser.update(textAreaUser.getGraphics());

                    ScriptRunner.runScript(titlepath);
                    textAreaUser.append("Txt file has been received.\n");
                    textAreaUser.update(textAreaUser.getGraphics());


                    //Receive TS file
                    ScriptWriter.tsReceiver("temp", scriptpath, tspath, user_Port);
                    System.out.println("Listening to port: " + user_Port);
                    System.out.println("Preparing to receive .ts video file...");
                    textAreaUser.append("Listening to port: " + user_Port  + "\n");
                    textAreaUser.append("Preparing to receive .ts video file...\n");
                    textAreaUser.update(textAreaUser.getGraphics());
                    ScriptRunner.runScript(scriptpath);
                    System.out.println("TS file has been received");
                    textAreaUser.append("TS file has been received\n");
                    textAreaUser.update(textAreaUser.getGraphics());

                    //Rename file
                    String content = null;
                    try {
                        content = new Scanner(new File(titletxt)).useDelimiter("\\Z").next();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("Received File: " + content);
                    ScriptWriter.rename(scriptpath, tspath, content, "temp");
                    ScriptRunner.runScript(scriptpath);
                    textAreaUser.append("Received File: " + content + " \n");
                    textAreaUser.append("\n");
                    textAreaUser.update(textAreaUser.getGraphics());

                    //Delete received text file
                    ScriptWriter.delete(tspath, "title.txt");

                    //Remove last 3 characters
                    content = content.substring(0, content.length() - 3);
                    System.out.println("Content variable is now: " + content);

                    //Convert selected video to MP4
                    ScriptWriter.ts_mp4Converter(content, scriptpath, tspath, mp4path);
                    textAreaUser.append("Preparing to convert to MP4...\n");
                    textAreaUser.update(textAreaUser.getGraphics());
                    ScriptRunner.runScript(scriptpath);
                    textAreaUser.append("Conversion Successful!\n");
                    textAreaUser.append("\n");
                    textAreaUser.update(textAreaUser.getGraphics());

                    //Set extension
                    String content_ext = content + ".mp4";

                    //Find length of MP4 file
                    ScriptWriter.duration(scriptpath, mp4path, content_ext);
                    ScriptRunner.findmp4Duration(scriptpath);
                    System.out.println("Generated MP4 file duration is: " + ScriptRunner.mp4duration + " seconds\n");
                    textAreaUser.append("Generated MP4 file duration is: " + ScriptRunner.mp4duration + " seconds\n");
                    textAreaUser.update(textAreaUser.getGraphics());


                    //Extract
                    ScriptWriter.user_extract(scriptpath, mp4path, content);
                    textAreaUser.append("Preparing to extract JPG thumbnail...\n");
                    textAreaUser.update(textAreaUser.getGraphics());
                    ScriptRunner.runScript(scriptpath);
                    textAreaUser.append("Successfully extracted JPG thumbnail!\n");
                    textAreaUser.append("\n");
                    textAreaUser.update(textAreaUser.getGraphics());

                    //Upload
                    JOptionPane.showMessageDialog(null,"Please make sure that you have a stable internet connection before proceeding!");
                    textAreaUser.append("Preparing to upload MP4 to website...\n");
                    textAreaUser.update(textAreaUser.getGraphics());
                    Uploader.uploadToFtp(content, scriptpath, mp4path, hostName, username, password);
                    System.out.println("");
                    System.out.println("Video Duration is: " + ScriptRunner.mp4duration + " s");
                    textAreaUser.append("Successfully uploaded MP4 to website!\n");
                    textAreaUser.append("Uploaded MP4 duration is: " + ScriptRunner.mp4duration + " seconds\n");
                    textAreaUser.append("Process Complete!\n");
                    textAreaUser.append("\n");
                    textAreaUser.update(textAreaUser.getGraphics());


                    btnUserBackButton.setEnabled(true);
                    btnUserReceiveButton.setEnabled(true);
                    btnUserSendButton.setEnabled(true);

                }

                catch (InputMismatchException i)
                {
                    System.out.println("INVALID");
                    System.out.println("");
                }

            }
        });
        btnUserReceiveButton.setBounds(83, 496, 380, 29);
        panelUserMenu.add(btnUserReceiveButton);

        btnUserBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panelUserMenu.setVisible(false);
                panelMainMenu.setVisible(true);
                textAreaUser.setText("\n");
            }
        });
        btnUserBackButton.setBounds(83, 537, 117, 29);
        panelUserMenu.add(btnUserBackButton);

        JLabel lblPleaseChooseOne = new JLabel("Please Choose one of the following options:");
        lblPleaseChooseOne.setBounds(93, 427, 330, 16);
        panelUserMenu.add(lblPleaseChooseOne);

    }

    private void initializePlayVideos() {


        panelPlayVideos = new JPanel();
        frame.getContentPane().add(panelPlayVideos, "name_35640817521406");
        panelPlayVideos.setLayout(null);

        JButton btnPlayBackButton = new JButton("Back");
        JButton btnPlayMP4Button = new JButton("MP4 Folder");
        JButton btnPlayTSButton = new JButton("TS Folder");


        JTextArea textAreaPlayVideos = new JTextArea();
        textAreaPlayVideos.append("---------------------------------------------PLAY VIDEOS---------------------------------------------\n");

        JScrollPane scrollPane = new JScrollPane(textAreaPlayVideos);
        scrollPane.setBounds(40, 61, 461, 228);
        panelPlayVideos.add(scrollPane);

        btnPlayTSButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnPlayMP4Button.setEnabled(false);
                btnPlayTSButton.setEnabled(false);
                btnPlayBackButton.setEnabled(false);

                textAreaPlayVideos.append("The files in the TS folder are: \n");
                Folder.showFiles(tspath);
                textAreaPlayVideos.append(Folder.fileResult);
                textAreaPlayVideos.append("\n");

                // append here

                Folder.reset();

                String vlc_ts_ext = null;
                while(Folder.file_exists == false){
                    vlc_ts_ext = JOptionPane.showInputDialog("Enter TS file to view\n");
                    vlc_ts_ext = vlc_ts_ext + ".ts";
                    Folder.fileCheck(tspath, vlc_ts_ext);

                }

                ScriptWriter.startVLC(scriptpath, tspath, vlc_ts_ext);
                ScriptRunner.runScript(scriptpath);


                btnPlayMP4Button.setEnabled(true);
                btnPlayTSButton.setEnabled(true);
                btnPlayBackButton.setEnabled(true);

            }
        });
        btnPlayTSButton.setBounds(40, 325, 117, 29);
        panelPlayVideos.add(btnPlayTSButton);

        btnPlayMP4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnPlayMP4Button.setEnabled(false);
                btnPlayTSButton.setEnabled(false);
                btnPlayBackButton.setEnabled(false);

                textAreaPlayVideos.append("The files in the MP4 folder are: \n");
                Folder.showFiles(mp4path);
                textAreaPlayVideos.append(Folder.fileResult);
                textAreaPlayVideos.append("\n");

                Folder.reset();

                String vlc_mp4_ext = null;
                while(Folder.file_exists == false){
                    vlc_mp4_ext = JOptionPane.showInputDialog("Enter TS file to view\n");
                    vlc_mp4_ext = vlc_mp4_ext + ".mp4";
                    Folder.fileCheck(mp4path, vlc_mp4_ext);

                }

                ScriptWriter.startVLC(scriptpath, mp4path, vlc_mp4_ext);
                ScriptRunner.runScript(scriptpath);

                btnPlayMP4Button.setEnabled(true);
                btnPlayTSButton.setEnabled(true);
                btnPlayBackButton.setEnabled(true);



            }
        });
        btnPlayMP4Button.setBounds(40, 366, 117, 29);
        panelPlayVideos.add(btnPlayMP4Button);

        btnPlayBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panelPlayVideos.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaPlayVideos.setText("\n");
            }
        });
        btnPlayBackButton.setBounds(40, 418, 80, 29);
        panelPlayVideos.add(btnPlayBackButton);

        JLabel lblPleaseSelectA_1 = new JLabel("Please select a folder: ");
        lblPleaseSelectA_1.setBounds(50, 301, 200, 16);
        panelPlayVideos.add(lblPleaseSelectA_1);
    }

    private void initializeChangeDefaultPaths() {

        panelChangeDefaultPaths = new JPanel();
        frame.getContentPane().add(panelChangeDefaultPaths, "name_35628684252556");
        panelChangeDefaultPaths.setLayout(null);

        JTextArea textAreaChangeDefaultPaths = new JTextArea();
        textAreaChangeDefaultPaths.append("---------------------------------------CHANGE DEFAULT PATHS---------------------------------------\n");

        JScrollPane scrollPane = new JScrollPane(textAreaChangeDefaultPaths);
        scrollPane.setBounds(71, 164, 483, 217);
        panelChangeDefaultPaths.add(scrollPane);

        JLabel lblPleaseSelectA = new JLabel("Please select a path that you would like to change:");
        lblPleaseSelectA.setBounds(71, 396, 400, 16);
        panelChangeDefaultPaths.add(lblPleaseSelectA);

        JComboBox comboBoxChangePathChoice = new JComboBox();
        comboBoxChangePathChoice.setModel(new DefaultComboBoxModel(new String[] {"1. Executable Script path", "2. Install script path","3. Bash Script Folder path", "4. TS Folder path ", "5. MP4 Folder path", "6. Title Script path"}));
        comboBoxChangePathChoice.setMaximumRowCount(5);
        comboBoxChangePathChoice.setBounds(71, 436, 258, 27);
        panelChangeDefaultPaths.add(comboBoxChangePathChoice);

        JButton btnChangePathsBackButton = new JButton("Back");
        btnChangePathsBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panelChangeDefaultPaths.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaChangeDefaultPaths.setText("\n");
            }
        });
        btnChangePathsBackButton.setBounds(71, 532, 117, 29);
        panelChangeDefaultPaths.add(btnChangePathsBackButton);

        JButton btnChangePathsStartButton = new JButton("Start");


        btnChangePathsStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String path = JOptionPane.showInputDialog("Enter the new path\n");
                JOptionPane.showMessageDialog(null,"WARNING: Setting an invalid path may result in improper functioning of the program");
                int choice = comboBoxChangePathChoice.getSelectedIndex() + 1;
                switch(choice){
                    case 0:
                        break;
                    case 1:
                        // Executable Script path
                        scriptpath = path;
                        textAreaChangeDefaultPaths.append("The script path is now: " + scriptpath + "\n");
                        Folder.clear(scriptpath);
                        break;
                    case 2:
                        // Install Script path
                        installscriptpath = path;
                        textAreaChangeDefaultPaths.append("The Install script path is now: " + installscriptpath + "\n");
                        break;
                    case 3:
                        // Bash Script path
                        scriptfolderpath = path;
                        textAreaChangeDefaultPaths.append("The Bash script path is now is now: " + scriptfolderpath + "\n");

                        break;
                    case 4:
                        // TS Folder path
                        tspath = path;
                        textAreaChangeDefaultPaths.append("The TS path is now: " + tspath + "\n");
                        break;
                    case 5:
                        // MP4 Folder path
                        mp4path = path;
                        textAreaChangeDefaultPaths.append("The MP4 path is now: " + mp4path + "\n");
                        break;
                    case 6:
                        // Title Script path
                        titlepath = path;
                        textAreaChangeDefaultPaths.append("The title script path is now: " + titlepath + "\n");
                        Folder.clear(titlepath);
                        break;
                    default:
                        break;
                }
            }
        });
        btnChangePathsStartButton.setBounds(426, 532, 117, 29);
        panelChangeDefaultPaths.add(btnChangePathsStartButton);


    }

    private void initializeViewFolderContents() {

        panelViewFolderContents = new JPanel();
        frame.getContentPane().add(panelViewFolderContents, "name_35617035007041");
        panelViewFolderContents.setLayout(null);

        final JButton btnViewTSFolderButton = new JButton("View TS Folder Contents");
        final JButton btnViewBashFolderButton = new JButton("View BashScript Folder Contents");
        final JButton btnViewMP4FolderButton = new JButton("View MP4 Folder Contents");
        final JButton btnViewFolderBackButton = new JButton("Back");

        JTextArea textAreaViewFolderContents = new JTextArea();
        textAreaViewFolderContents.append("---------------------VIEW FOLDER CONTENTS---------------------\n");

        JScrollPane scrollPane = new JScrollPane(textAreaViewFolderContents);
        scrollPane.setBounds(49, 22, 477, 199);
        panelViewFolderContents.add(scrollPane);

        JLabel lblViewFolderContentsLabel = new JLabel("Please select a folder to view its contents:");
        lblViewFolderContentsLabel.setBounds(49, 233, 304, 16);
        panelViewFolderContents.add(lblViewFolderContentsLabel);

        btnViewBashFolderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                btnViewMP4FolderButton.setEnabled(false);
                btnViewTSFolderButton.setEnabled(false);
                btnViewBashFolderButton.setEnabled(false);
                btnViewFolderBackButton.setEnabled(false);

                textAreaViewFolderContents.append("BashScript folder contains: \n");
                Folder.showFiles(scriptfolderpath);
                textAreaViewFolderContents.append(Folder.fileResult);
                textAreaViewFolderContents.append("\n\n");

                btnViewMP4FolderButton.setEnabled(true);
                btnViewTSFolderButton.setEnabled(true);
                btnViewBashFolderButton.setEnabled(true);
                btnViewFolderBackButton.setEnabled(true);

            }
        });
        btnViewBashFolderButton.setBounds(49, 259, 266, 29);
        panelViewFolderContents.add(btnViewBashFolderButton);

        btnViewTSFolderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnViewMP4FolderButton.setEnabled(false);
                btnViewTSFolderButton.setEnabled(false);
                btnViewBashFolderButton.setEnabled(false);
                btnViewFolderBackButton.setEnabled(false);

                textAreaViewFolderContents.append("TS folder contains: \n");
                Folder.showFiles(tspath);
                textAreaViewFolderContents.append(Folder.fileResult);
                textAreaViewFolderContents.append("\n\n");

                btnViewMP4FolderButton.setEnabled(true);
                btnViewTSFolderButton.setEnabled(true);
                btnViewBashFolderButton.setEnabled(true);
                btnViewFolderBackButton.setEnabled(true);

            }
        });
        btnViewTSFolderButton.setBounds(49, 294, 266, 29);
        panelViewFolderContents.add(btnViewTSFolderButton);

        btnViewMP4FolderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnViewMP4FolderButton.setEnabled(false);
                btnViewTSFolderButton.setEnabled(false);
                btnViewBashFolderButton.setEnabled(false);
                btnViewFolderBackButton.setEnabled(false);

                textAreaViewFolderContents.append("MP4 folder contains: \n");
                Folder.showFiles(mp4path);
                textAreaViewFolderContents.append(Folder.fileResult);

                textAreaViewFolderContents.append("\n\n");

                btnViewMP4FolderButton.setEnabled(true);
                btnViewTSFolderButton.setEnabled(true);
                btnViewBashFolderButton.setEnabled(true);
                btnViewFolderBackButton.setEnabled(true);

            }
        });
        btnViewMP4FolderButton.setBounds(49, 336, 266, 29);
        panelViewFolderContents.add(btnViewMP4FolderButton);

        btnViewFolderBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelViewFolderContents.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaViewFolderContents.setText("\n");
            }
        });
        btnViewFolderBackButton.setBounds(49, 380, 89, 29);
        panelViewFolderContents.add(btnViewFolderBackButton);
    }

    private void initializeViewCurrentPaths() {

        panelViewCurrentPaths = new JPanel();
        frame.getContentPane().add(panelViewCurrentPaths, "name_33965051879396");
        panelViewCurrentPaths.setLayout(null);

        JTextArea textAreaViewCurrentPaths = new JTextArea();
        textAreaViewCurrentPaths.append("----------------------------------------------VIEW CURRENT PATHS----------------------------------\n");

        final JButton btnViewPathsStartButton = new JButton("View Current Paths");
        final JButton btnViewPathsBackButton = new JButton("Back");

        JScrollPane scrollPane = new JScrollPane(textAreaViewCurrentPaths);
        scrollPane.setBounds(63, 23, 487, 212);
        panelViewCurrentPaths.add(scrollPane);

        btnViewPathsStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textAreaViewCurrentPaths.append("The Current paths are: \n");
                textAreaViewCurrentPaths.append("-The BashScript folder:" + scriptfolderpath + "\n");
                textAreaViewCurrentPaths.append("-The TS folder:" + tspath + "\n");
                textAreaViewCurrentPaths.append("-The MP4 folder:" + mp4path + "\n");
                textAreaViewCurrentPaths.append("-The Install BashScript:" + installscriptpath + "\n");
                textAreaViewCurrentPaths.append("-The Bash Script:" + scriptpath + "\n");
                textAreaViewCurrentPaths.append("-The Title Script:" + titlepath + "\n");
                textAreaViewCurrentPaths.append("\n");

            }
        });
        btnViewPathsStartButton.setBounds(301, 259, 249, 29);
        panelViewCurrentPaths.add(btnViewPathsStartButton);

        btnViewPathsBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelViewCurrentPaths.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaViewCurrentPaths.setText("\n");
            }
        });
        btnViewPathsBackButton.setBounds(63, 259, 117, 29);
        panelViewCurrentPaths.add(btnViewPathsBackButton);
    }

    private void initializeNetworkDetails() {

        panelNetworkDetails = new JPanel();
        frame.getContentPane().add(panelNetworkDetails, "name_35438704918703");
        panelNetworkDetails.setLayout(null);

        JTextArea textAreaNetworkDetails = new JTextArea();
        textAreaNetworkDetails.append("-------------------------------------------NETWORK DETAILS---------------------------------------\n");

        JScrollPane scrollPaneNetworkDetails = new JScrollPane(textAreaNetworkDetails);
        scrollPaneNetworkDetails.setBounds(97, 187, 450, 192);
        panelNetworkDetails.add(scrollPaneNetworkDetails);

        JButton btnNetworkDetailsBackButton = new JButton("Back");
        btnNetworkDetailsBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelNetworkDetails.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaNetworkDetails.setText("\n");
            }
        });
        btnNetworkDetailsBackButton.setBounds(97, 416, 117, 29);
        panelNetworkDetails.add(btnNetworkDetailsBackButton);

        JButton btnNetworkDetailsViewButton = new JButton("View Network Details");
        //btnNetworkDetailsViewButton.setBounds(110,416,200,29);
        //btnNetworkDetailsViewButton.setBorder(null);
        btnNetworkDetailsViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                ScriptWriter.network(scriptpath);
                ScriptRunner.runScript(scriptpath);
                textAreaNetworkDetails.append("Current Network Details\n");
                textAreaNetworkDetails.append("\n");
                textAreaNetworkDetails.append(ScriptRunner.result);
                textAreaNetworkDetails.append("\n");
            }
        });
        btnNetworkDetailsViewButton.setBounds(406, 416, 187, 29);
        panelNetworkDetails.add(btnNetworkDetailsViewButton);
        //panelUserMenu.setVisible(false);
    }

    private void initializeUpload() {

        panelUpload = new JPanel();
        frame.getContentPane().add(panelUpload, "name_35410219054495");
        panelUpload.setLayout(null);

        final JButton btnUploadBackButton = new JButton("Back");
        final JButton btnUploadStartButton = new JButton("Start");
        final JButton btnUploadChangeCredentialsButton = new JButton("Change Credentials");
        btnUploadChangeCredentialsButton.setBounds(92, 157, 120, 100);
        btnUploadChangeCredentialsButton.setBorder(null);

        JTextArea textAreaUpload = new JTextArea();
        textAreaUpload.append("---------------------------------------------UPLOAD TO WEBSITE------------------------------------------\n");

        JScrollPane scrollPaneUpload = new JScrollPane(textAreaUpload);
        scrollPaneUpload.setBounds(90, 125, 478, 204);
        panelUpload.add(scrollPaneUpload);

        btnUploadChangeCredentialsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnUploadBackButton.setEnabled(false);
                btnUploadStartButton.setEnabled(false);
                textAreaUpload.append("------------SET NEW CREDENTIALS--------------\n");

                String in_host = JOptionPane.showInputDialog("Enter Host Name");
                hostName = in_host;
                textAreaUpload.append("Host Name has been set to: " + hostName + "\n");
                String in_user = JOptionPane.showInputDialog("Enter Username");
                username = in_user;
                textAreaUpload.append("Username has been set to: " + username + "\n");
                String in_pass = JOptionPane.showInputDialog("Enter Password");
                password = in_pass;
                textAreaUpload.append("Password has been set to: " + password + "\n");
                textAreaUpload.append("\n");

                btnUploadBackButton.setEnabled(true);
                btnUploadStartButton.setEnabled(true);
            }
        });
        btnUploadChangeCredentialsButton.setBounds(89, 341, 155, 29);
        panelUpload.add(btnUploadChangeCredentialsButton);

        btnUploadBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelUpload.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaUpload.setText("\n");
            }
        });
        btnUploadBackButton.setBounds(90, 380, 117, 29);
        panelUpload.add(btnUploadBackButton);

        btnUploadStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnUploadBackButton.setEnabled(false);
                btnUploadChangeCredentialsButton.setEnabled(false);
                btnUploadStartButton.setEnabled(false);
                textAreaUpload.append("---------------------UPLOAD MP4 FILE TO WEBSITE---------------------\n");

                String input = null;
                String input_ext;
                Folder.reset();

                while (Folder.file_exists == false) {

                    input = JOptionPane.showInputDialog("Enter MP4 file you wish to upload");
                    input_ext = input + ".mp4";

                    Folder.fileCheck(mp4path, input_ext);
                }

                JOptionPane.showMessageDialog(null,"Please make sure that you have a stable internet connection before proceeding!");
                textAreaUpload.append("Preparing to upload MP4...\n");
                textAreaUpload.update(textAreaUpload.getGraphics());
                Uploader.uploadToFtp(input, scriptpath, mp4path, hostName, username, password);
                textAreaUpload.append("Uploaded MP4 Duration is: " + ScriptRunner.mp4duration + " seconds\n");
                textAreaUpload.append("Process Complete!\n");
                textAreaUpload.append("\n");
                textAreaUpload.update(textAreaUpload.getGraphics());

                btnUploadBackButton.setEnabled(true);
                btnUploadChangeCredentialsButton.setEnabled(true);
                btnUploadStartButton.setEnabled(true);
            }
        });
        btnUploadStartButton.setBounds(449, 380, 117, 29);
        panelUpload.add(btnUploadStartButton);
    }

    private void initializeConvert() {

        panelConvert = new JPanel();
        frame.getContentPane().add(panelConvert, "name_35375201080694");
        panelConvert.setLayout(null);

        final JButton btnConvertBackButton = new JButton("Back");
        final JButton btnConvertStartButton = new JButton("Start");

        JTextArea textAreaConvert = new JTextArea();

        textAreaConvert.append("---------------------------------------------TS to MP4 CONVERTER---------------------------------------------\n");

        JScrollPane scrollPaneConvert = new JScrollPane(textAreaConvert);
        scrollPaneConvert.setBounds(74, 116, 507, 174);
        panelConvert.add(scrollPaneConvert);

        btnConvertBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panelConvert.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaConvert.setText("\n");
            }
        });
        btnConvertBackButton.setBounds(76, 318, 117, 29);
        panelConvert.add(btnConvertBackButton);

        btnConvertStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnConvertBackButton.setEnabled(false);
                btnConvertStartButton.setEnabled(false);
                String ts_mp4 = null;
                String ts_mp4_ext = null;
                String ts_mp4_ext2 = null;

                Folder.reset();

                while (Folder.file_exists == false) {
                    //Input

                    ts_mp4 = JOptionPane.showInputDialog("Enter input TS File to Convert to MP4\n");
                    ts_mp4_ext = ts_mp4 + ".ts";
                    ts_mp4_ext2 = ts_mp4 + ".mp4";

                    //FileCheck
                    Folder.fileCheck(tspath, ts_mp4_ext);

                }

                //Convert selected video to MP4
                ScriptWriter.ts_mp4Converter(ts_mp4, scriptpath, tspath, mp4path);
                textAreaConvert.append("Preparing to convert to MP4...\n");
                textAreaConvert.update(textAreaConvert.getGraphics());
                ScriptRunner.runScript(scriptpath);

                textAreaConvert.append("\n");
                textAreaConvert.append(ScriptRunner.result);

                //Find length of MP4 file
                ScriptWriter.duration(scriptpath, mp4path, ts_mp4_ext2);
                ScriptRunner.findmp4Duration(scriptpath);
                //System.out.println("Original MP4 file DURATION IS:" + ScriptRunner.mp4duration);
                textAreaConvert.append("Generated MP4 file duration: " + ScriptRunner.mp4duration+" seconds\n");
                textAreaConvert.append("\n");

                //Extract
                ScriptWriter.extract(scriptpath, mp4path, ts_mp4);
                textAreaConvert.append("Preparing to extract JPG thumbnail...\n");
                textAreaConvert.update(textAreaConvert.getGraphics());
                ScriptRunner.runScript(scriptpath);

                textAreaConvert.append("\n");
                textAreaConvert.append(ScriptRunner.result);
                textAreaConvert.append("Process Complete!\n");
                textAreaConvert.append("\n");

                btnConvertBackButton.setEnabled(true);
                btnConvertStartButton.setEnabled(true);
            }
        });
        btnConvertStartButton.setBounds(399, 318, 117, 29);
        panelConvert.add(btnConvertStartButton);
    }

    private void initializeCut() {
        panelCut = new JPanel();
        frame.getContentPane().add(panelCut, "name_35339858918278");
        panelCut.setLayout(null);

        JTextArea textAreaCut = new JTextArea();
        textAreaCut.append("------------------------------------------------VIDEO EDITOR------------------------------------------------\n");

        JScrollPane scrollPaneCut = new JScrollPane(textAreaCut);
        scrollPaneCut.setBounds(80, 113, 473, 220);
        panelCut.add(scrollPaneCut);

        JButton btnCutBackButton = new JButton("Back");
        btnCutBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelCut.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaCut.setText("\n");
            }
        });
        btnCutBackButton.setBounds(110, 345, 117, 29);
        panelCut.add(btnCutBackButton);
        JButton btnCutStartButton = new JButton("Start");
        btnCutStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCutStartButton.setEnabled(false);
                btnCutBackButton.setEnabled(false);

                String inputcut = null;
                String inputcut_ext = null;
                Folder.reset();
                while (Folder.file_exists == false) {
                    //Input TS file to cut
                    textAreaCut.append("NOTE: The resulting cut TS file will have the name convention: filename_cut.ts\n");
                    inputcut = JOptionPane.showInputDialog("Enter input TS file to cut\n");
                    inputcut_ext = inputcut + ".ts";

                    //FileCheck
                    Folder.fileCheck(tspath, inputcut_ext);
                }

                //Find length of TS file
                ScriptWriter.duration(scriptpath, tspath, inputcut_ext);
                ScriptRunner.findtsDuration(scriptpath);
                textAreaCut.append("Original TS file length: " + ScriptRunner.tsduration + " seconds\n");


                //Cut video
                ScriptWriter.tsCutter(scriptpath, tspath, inputcut);
                textAreaCut.append("Preparing to cut file...\n");
                textAreaCut.update(textAreaCut.getGraphics());
                ScriptRunner.runScript(scriptpath);

                textAreaCut.append("\n");
                textAreaCut.append(ScriptRunner.result);
                textAreaCut.append("Process complete!\n");
                textAreaCut.append("\n");


                btnCutStartButton.setEnabled(true);
                btnCutBackButton.setEnabled(true);
            }
        });
        btnCutStartButton.setBounds(383, 345, 117, 29);
        panelCut.add(btnCutStartButton);


    }

    private void initializeReceive() {

        panelReceive = new JPanel();
        frame.getContentPane().add(panelReceive, "name_35324852842952");
        panelReceive.setLayout(null);

        final JButton btnReceiveClientButton = new JButton("Receive Using Client System");
        final JButton btnReceiveATSCButton = new JButton("Receive Using ATSC Modulator");
        final JButton btnReceiveBackButton = new JButton("Back");

        JTextArea textAreaReceive = new JTextArea();
        textAreaReceive.setEditable(false);
        textAreaReceive.append("------------------------------------------RECEIVE DATA-----------------------------------------\n");

        JScrollPane scrollPaneReceive = new JScrollPane(textAreaReceive);
        scrollPaneReceive.setBounds(73, 21, 482, 193);
        panelReceive.add(scrollPaneReceive);

        btnReceiveClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnReceiveATSCButton.setEnabled(false);
                btnReceiveBackButton.setEnabled(false);
                btnReceiveClientButton.setEnabled(false);
                textAreaReceive.append("---------------------LISTEN TO PORT USING CLIENT SYSTEM---------------------\n");


                String Port = JOptionPane.showInputDialog("Enter Port to listen to");


                //Receive txt file
                ScriptWriter.txtReceiver(titlepath, tspath, Port);
                textAreaReceive.append("Listening to port: "+ Port+"\n");
                textAreaReceive.append("Preparing to receive txt file containing metadata...\n");
                textAreaReceive.update(textAreaReceive.getGraphics());

                ScriptRunner.runScript(titlepath);
                textAreaReceive.append("Txt file has been received\n");
                textAreaReceive.update(textAreaReceive.getGraphics());

                //Receive TS file
                ScriptWriter.tsReceiver("temp", scriptpath, tspath, Port);
                textAreaReceive.append("Listening to port: "+ Port+"\n");
                textAreaReceive.append("Preparing to receive .ts video file...\n");
                textAreaReceive.update(textAreaReceive.getGraphics());

                ScriptRunner.runScript(scriptpath);
                textAreaReceive.append("TS file has been received\n");
                textAreaReceive.update(textAreaReceive.getGraphics());

                //Rename file
                String content = null;
                try {
                    content = new Scanner(new File(titletxt)).useDelimiter("\\Z").next();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }


                ScriptWriter.rename(scriptpath, tspath, content, "temp");
                ScriptRunner.runScript(scriptpath);

                textAreaReceive.append("Received File: "+ content+"\n");
                textAreaReceive.append("Process Complete!\n");
                textAreaReceive.append("\n");
                textAreaReceive.update(textAreaReceive.getGraphics());

                //Delete received text file
                ScriptWriter.delete(tspath, "title.txt");

                btnReceiveATSCButton.setEnabled(true);
                btnReceiveBackButton.setEnabled(true);
                btnReceiveClientButton.setEnabled(true);
            }
        });
        btnReceiveClientButton.setBounds(73, 254, 236, 29);
        panelReceive.add(btnReceiveClientButton);

        btnReceiveATSCButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnReceiveATSCButton.setEnabled(false);
                btnReceiveBackButton.setEnabled(false);
                btnReceiveClientButton.setEnabled(false);

                textAreaReceive.append("---------------------LISTEN TO PORT USING ATSC MODULATOR---------------------\n");
                String Port_atsc = JOptionPane.showInputDialog("Enter Port to listen to");
                String receive_name = JOptionPane.showInputDialog("Enter file name which will receive the data sent via UDP");
                //Receive TS file
                ScriptWriter.tsReceiver(receive_name, scriptpath, tspath, Port_atsc);
                textAreaReceive.append("Preparing to receive .ts video file..\n");
                textAreaReceive.update(textAreaReceive.getGraphics());
                ScriptRunner.runScript(scriptpath);
                textAreaReceive.append("TS file has been received\n");
                textAreaReceive.append("Process Complete!\n");
                textAreaReceive.append("\n");
                textAreaReceive.update(textAreaReceive.getGraphics());

                btnReceiveATSCButton.setEnabled(true);
                btnReceiveBackButton.setEnabled(true);
                btnReceiveClientButton.setEnabled(true);
            }
        });
        btnReceiveATSCButton.setBounds(73, 295, 260, 29);
        panelReceive.add(btnReceiveATSCButton);

        JLabel lblReceiveLabel = new JLabel("Please select one of the following options:");
        lblReceiveLabel.setBounds(83, 226, 472, 16);
        panelReceive.add(lblReceiveLabel);

        btnReceiveBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelReceive.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaReceive.setText("\n");
            }
        });
        btnReceiveBackButton.setBounds(73, 336, 117, 29);
        panelReceive.add(btnReceiveBackButton);
    }

    private void initializeSendToATSC() {

        panelSendToATSC = new JPanel();
        frame.getContentPane().add(panelSendToATSC, "name_35303134821129");
        panelSendToATSC.setLayout(null);

        final JButton btnATSCBackButton = new JButton("Back");
        final JButton btnATSCSendButton = new JButton("Send");
        final JButton btnATSCSetIpPortButton = new JButton("Set IP And Port");


        JTextArea textAreaSendToATSC = new JTextArea();
        textAreaSendToATSC.setEditable(false);
        textAreaSendToATSC.append("-----------------------------------SEND TO ATSC MODULATOR--------------------------------\n");


        JScrollPane scrollPaneSendToATSC = new JScrollPane(textAreaSendToATSC);
        scrollPaneSendToATSC.setBounds(87, 38, 441, 155);
        panelSendToATSC.add(scrollPaneSendToATSC);

        btnATSCBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelSendToATSC.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaSendToATSC.setText("\n");
            }
        });
        btnATSCBackButton.setBounds(134, 250, 117, 29);
        panelSendToATSC.add(btnATSCBackButton);

        btnATSCSendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnATSCBackButton.setEnabled(false);
                btnATSCSendButton.setEnabled(false);
                btnATSCSetIpPortButton.setEnabled(false);
                textAreaSendToATSC.append("Starting...\n");
                textAreaSendToATSC.append("\n");
                String sendfile_atsc = null;
                Folder.reset();
                //while loop
                while(Folder.file_exists == false) {
                    sendfile_atsc = JOptionPane.showInputDialog("Enter file name to send");
                    String sendfile_atsc_ext = sendfile_atsc + ".ts";
                    Folder.fileCheck(tspath,sendfile_atsc_ext);
                }
                //end while
                ScriptWriter.atscSender(sendfile_atsc, scriptpath, tspath, IP, Port);
                textAreaSendToATSC.append("Preparing to send...\n");
                textAreaSendToATSC.update(textAreaSendToATSC.getGraphics());
                ScriptRunner.runScript(scriptpath);
                textAreaSendToATSC.append(".ts video file has been sent\n");
                textAreaSendToATSC.append("Process complete!\n");
                textAreaSendToATSC.update(textAreaSendToATSC.getGraphics());

                btnATSCBackButton.setEnabled(true);
                btnATSCSendButton.setEnabled(true);
                btnATSCSetIpPortButton.setEnabled(true);

            }
        });
        btnATSCSendButton.setBounds(367, 250, 117, 29);
        panelSendToATSC.add(btnATSCSendButton);

        btnATSCSetIpPortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnATSCBackButton.setEnabled(false);
                btnATSCSendButton.setEnabled(false);
                btnATSCSetIpPortButton.setEnabled(false);
                textAreaSendToATSC.append("------------------SET IP AND PORT OF ATSC MODULATOR------------------\n");

                String ip = JOptionPane.showInputDialog("Enter the IP Address of the ATSC Modulator");
                IP= ip;
                textAreaSendToATSC.append("The ATSC Modulator IP is set to: " + ip + "\n");
                String port = JOptionPane.showInputDialog("Enter the Port of the ATSC Modulator");
                Port = port;
                textAreaSendToATSC.append("The ATSC Modulator port is set to: " + port + "\n");

                btnATSCBackButton.setEnabled(true);
                btnATSCSendButton.setEnabled(true);
                btnATSCSetIpPortButton.setEnabled(true);
            }
        });
        btnATSCSetIpPortButton.setBounds(350, 196, 180, 29);
        panelSendToATSC.add(btnATSCSetIpPortButton);
    }

    private void initializeSendToClient() {

        panelSendToClient = new JPanel();
        frame.getContentPane().add(panelSendToClient, "name_35270252473759");
        panelSendToClient.setLayout(null);

        final JButton btnClientStartButton = new JButton("Start");
        final JButton btnClientBackButton = new JButton("Back");


        JTextArea textAreaSendToClient = new JTextArea();
        textAreaSendToClient.setEditable(false);
        textAreaSendToClient.append("---------------------------------------------SEND TO CLIENT---------------------------------------------\n");

        JScrollPane scrollPaneSendToClient = new JScrollPane(textAreaSendToClient);
        scrollPaneSendToClient.setBounds(74, 27, 479, 177);
        panelSendToClient.add(scrollPaneSendToClient);

        btnClientStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                btnClientBackButton.setEnabled(false);
                btnClientStartButton.setEnabled(false);
                String sendfile = null;
                Folder.reset();
                // put this in the while statement
                while(Folder.file_exists == false) {
                    textAreaSendToClient.append("Starting...\n");
                    textAreaSendToClient.append("\n");
                    sendfile = JOptionPane.showInputDialog("Enter file name to send");

                    String sendfile_ext = sendfile + ".ts";

                    Folder.fileCheck(tspath,sendfile_ext);

                }
                //end while

                //Write script to send both txt and ts
                ScriptWriter.tsSender(sendfile, scriptpath, tspath, titletxt, titlepath);


                //Send txt
                textAreaSendToClient.append("Preparing to send txt file containing metadata...\n");
                textAreaSendToClient.update(textAreaSendToClient.getGraphics());


                ScriptRunner.runScript(titlepath);
                textAreaSendToClient.append("Text file containing metadata has been sent\n");
                textAreaSendToClient.update(textAreaSendToClient.getGraphics());


                //Send TS
                textAreaSendToClient.append("Preparing to send .ts video file\n");
                textAreaSendToClient.update(textAreaSendToClient.getGraphics());


                ScriptRunner.runScript(scriptpath);
                textAreaSendToClient.append(".ts video file has been sent\n");
                textAreaSendToClient.update(textAreaSendToClient.getGraphics());


                //Delete text file since it is no longer needed
                ScriptWriter.delete(tspath, "title.txt");


                btnClientBackButton.setEnabled(true);
                btnClientStartButton.setEnabled(true);
                textAreaSendToClient.append("Process Complete!\n");
                textAreaSendToClient.append("\n");
            }
        });
        btnClientStartButton.setBounds(403, 216, 117, 29);
        panelSendToClient.add(btnClientStartButton);

        btnClientBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panelSendToClient.setVisible(false);
                panelAdminMenu.setVisible(true);
                textAreaSendToClient.setText("\n");
            }
        });
        btnClientBackButton.setBounds(143, 216, 117, 29);
        panelSendToClient.add(btnClientBackButton);
    }

    private void initializeInstall() {

        panelInstall = new JPanel();
        frame.getContentPane().add(panelInstall, "name_26237026773858");
        panelInstall.setLayout(null);
        panelInstall.setVisible(false);

        JTextArea textAreaInstall = new JTextArea();
        textAreaInstall.setEditable(false);
        textAreaInstall.append("---------------------INSTALLER---------------------\n");
        textAreaInstall.append("Caution: Select this option only if you do not have any of the following installed:\n");
        textAreaInstall.append("-Ffmpeg\n");
        textAreaInstall.append("-Exiftool\n");
        textAreaInstall.append("-Pipeview\n");
        textAreaInstall.append("-Libx264\n");
        textAreaInstall.append("-JAVA 8\n");

        panelInstall.add(textAreaInstall);

        JScrollPane scrollPaneInstall = new JScrollPane(textAreaInstall);
        scrollPaneInstall.setBounds(31, 45, 549, 300);
        panelInstall.add(scrollPaneInstall);

        JButton btnCancelInstallButton = new JButton("Back");
        btnCancelInstallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelAdminMenu.setVisible(true);
                panelMainMenu.setVisible(false);
                panelUserMenu.setVisible(false);
                panelInstall.setVisible(false);
            }
        });


        btnCancelInstallButton.setBounds(112, 381, 117, 29);
        panelInstall.add(btnCancelInstallButton);

        JButton btnConfirmInstallButton = new JButton("Install");
        btnConfirmInstallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                btnCancelInstallButton.setEnabled(false);
                btnConfirmInstallButton.setEnabled(false);
                // add script to install here
                String input = JOptionPane.showInputDialog("Are you sure you want to continue? Input y for yes or n for no");
                if(input.equals("y")){
                    // add script to install here
                    textAreaInstall.append("\n");
                    textAreaInstall.append("Installing...\n");
                    textAreaInstall.update(textAreaInstall.getGraphics());
                    ScriptRunner.install(installscriptpath);

                    textAreaInstall.append(ScriptRunner.result);
                    textAreaInstall.update(textAreaInstall.getGraphics());



                }

                btnCancelInstallButton.setEnabled(true);
                btnConfirmInstallButton.setEnabled(true);

            }
        });
        btnConfirmInstallButton.setBounds(365, 381, 117, 29);
        panelInstall.add(btnConfirmInstallButton);
    }
}








