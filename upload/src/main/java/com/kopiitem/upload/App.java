package com.kopiitem.upload;

import com.kopiitem.upload.common.FileProxy;
import com.kopiitem.upload.common.ModuleEnum;
import com.kopiitem.upload.common.ProjectEnum;
import com.kopiitem.upload.common.TrackEnum;
import com.kopiitem.upload.service.UploadManager;
import com.kopiitem.upload.ui.UploadJFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            runWithUI();
            return;
        }

        try {
            if ((!args[0].isEmpty() && (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("-help") || args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("/h")))) {
                printHelp();
            } else {
                if (args.length != 5) {
                    throw new Exception("Incorrect parameter input!");
                }
                if (args.length == 5 && !(args[0].isEmpty() && args[1].isEmpty() && args[2].isEmpty() && args[3].isEmpty() && args[4].isEmpty())) {
                    execute(args);
                }
            }
        } catch (Exception e) {
            printError(e.getMessage());
        }
    }

    private static void runWithUI() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UploadJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UploadJFrame().setVisible(true);
            }
        });
    }

    private static void execute(String... args) {
        logger.log(Level.INFO, "Start to Upload..");
        UploadManager uploadManager = new UploadManager(new FileProxy(
                ProjectEnum.valueOf(args[0].toUpperCase()),
                TrackEnum.valueOf(args[1].toUpperCase()),
                ProjectEnum.valueOf(args[2].toUpperCase()),
                TrackEnum.valueOf(args[3].toUpperCase()),
                ModuleEnum.valueOf(args[4].toUpperCase())
        ));
        uploadManager.doUpload();
        logger.log(Level.INFO, "Finished to Upload..");

    }

    private static void printError(String error) {
        System.out.println("");
        System.out.println("Error Decription:");
        System.out.println("\t* " + error);
        System.out.println("");
        System.out.println("Please type help or -help or -h or /h to get more detail information! ");
        System.out.println("");
    }

    private static void printHelp() {
        System.out.println("");
        System.out.println("Manual Upload: ");
        System.out.println("");
        System.out.println("Decription:");
        System.out.println("\t* Do fast upload into different environment [TRACK1/TRACK2] SIT <-> DEV, set you param argument 'Src' and 'Dst'. ");
        System.out.println("\t  Src mean is your source file.");
        System.out.println("\t  Dst mean is your destination file.");
        System.out.println("\t  This will determine the environment. Please follow the commnad instruction.");
        System.out.println("Command:");
        System.out.println("\t* java -jar upload.jar [ProjectSrc] [TrackSrc] [ProjectDst] [TrackDst] [Module]");
        System.out.println("Legend:");
        System.out.println("\t* Project PTN_T1('partner'), ENT('enterprise-new'), PTN_T2('partner-t2')");
        System.out.println("\t* Track SITT1(''), DEVT1('-devsvn'), SITT2(''), DEVT2('-dev-t2svn')");
        System.out.println("\t* SUPPLIER('supplier'), GTPREGISTRATION('gtpregistration'), SUPPLIERINTERFACE('supplierinterface')");
        System.out.println("");
    }
}
