package com.kopiitem.upload.common;

import com.kopiitem.upload.exception.CustomException;
import com.kopiitem.upload.util.StringFormater;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author donny.fm
 */
public class FileProxy {

    private static final Logger logger = Logger.getLogger(FileProxy.class.getName());
    private ModuleEnum module;
    private ProjectEnum projectSrc;
    private ProjectEnum projectDest;
    private TrackEnum trackSrc;
    private TrackEnum trackDest;
    private String log;

    public FileProxy(ProjectEnum projectSrc, TrackEnum trackSrc, ProjectEnum projectDest, TrackEnum trackDest, ModuleEnum module) {
        this.module = module;
        this.projectSrc = projectSrc;
        this.projectDest = projectDest;
        this.trackSrc = trackSrc;
        this.trackDest = trackDest;
    }

    public void upload(String result, int index) throws IOException, CustomException {
        if (result.isEmpty()) {
            throw new CustomException("Empty String!!!");
        }
        StringTokenizer st = new StringTokenizer(result);
        if (st.countTokens() != 2) {
            throw new CustomException("Error Tokenizer!!!");
        }
        String from = Constants.resourceBundle.getString("path.from").replace("{0}", projectSrc.getDesc()).replace("{1}", module.getDesc()).replace("{2}", trackSrc.getDesc());
        String to = Constants.resourceBundle.getString("path.to").replace("{0}", projectDest.getDesc()).replace("{1}", module.getDesc()).replace("{2}", trackDest.getDesc());

        String mode = st.nextToken().toUpperCase();
        String srcFile = st.nextToken();
        String destFile = srcFile.replaceAll(from, to);

        switch (ModeEnum.valueOf(mode)) {
            case ADDING:
                copyFile(new File(srcFile), new File(destFile));
                logger.log(Level.INFO, "Line {0}. {1} File: {2} to {3}", new Object[]{index, mode, srcFile, destFile});
                setLog(StringFormater.format("Line {0}. {1} File: {2} to {3}", new Object[]{index, mode, srcFile, destFile}) + "\n");
                break;
            case SENDING:
                copyFile(new File(srcFile), new File(destFile));
                logger.log(Level.INFO, "Line {0}. {1} File: {2} to {3}", new Object[]{index, mode, srcFile, destFile});
                setLog(StringFormater.format("Line {0}. {1} File: {2} to {3}", new Object[]{index, mode, srcFile, destFile}) + "\n");
                break;
            case DELETING:
                forceDelete(new File(destFile));
                logger.log(Level.INFO, "Line {0}. {1} File: {2} ", new Object[]{index, mode, destFile});
                setLog(StringFormater.format("Line {0}. {1} File: {2} ", new Object[]{index, mode, destFile}) + "\n");
                break;
        }

        index++;
    }

    public void upload() {
        try {
            List<String> results = readFiles(new File(Constants.resourceBundle.getString("path.file")));
            int index = 1;
            for (String result : results) {
                try {
                    if (result.isEmpty()) {
                        throw new CustomException("Empty String!!!");
                    }
                    StringTokenizer st = new StringTokenizer(result);
                    if (st.countTokens() != 2) {
                        throw new CustomException("Error Tokenizer!!!");
                    }
                    String from = Constants.resourceBundle.getString("path.from").replace("{0}", projectSrc.getDesc()).replace("{1}", module.getDesc()).replace("{2}", trackSrc.getDesc());
                    String to = Constants.resourceBundle.getString("path.to").replace("{0}", projectDest.getDesc()).replace("{1}", module.getDesc()).replace("{2}", trackDest.getDesc());

                    String mode = st.nextToken().toUpperCase();
                    String srcFile = st.nextToken();
                    String destFile = srcFile.replaceAll(from, to);

                    switch (ModeEnum.valueOf(mode)) {
                        case ADDING:
                            copyFile(new File(srcFile), new File(destFile));
                            logger.log(Level.INFO, "Line {0}. {1} File: {2} to {3}", new Object[]{index, mode, srcFile, destFile});
                            break;
                        case SENDING:
                            copyFile(new File(srcFile), new File(destFile));
                            logger.log(Level.INFO, "Line {0}. {1} File: {2} to {3}", new Object[]{index, mode, srcFile, destFile});
                            break;
                        case DELETING:
                            forceDelete(new File(destFile));
                            logger.log(Level.INFO, "Line {0}. {1} File: {2} ", new Object[]{index, mode, destFile});
                            break;
                    }
                } catch (CustomException ce) {
                    logger.log(Level.WARNING, "{0} On Line {1}", new Object[]{ce.getMessage(), index});
                }
                index++;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void copyFile(File srcFile, File destFile) throws IOException {
        if (srcFile.isDirectory()) {
            FileUtils.copyDirectory(srcFile, destFile);
            return;
        }
        FileUtils.copyFile(srcFile, destFile);
    }

    private void forceDelete(File file) throws IOException {
        FileUtils.forceDelete(file);
    }

    private List<String> readFiles(File file) throws IOException {
        return FileUtils.readLines(file);
    }

    public ModuleEnum getModule() {
        return module;
    }

    public void setModule(ModuleEnum module) {
        this.module = module;
    }

    public ProjectEnum getProjectSrc() {
        return projectSrc;
    }

    public void setProjectSrc(ProjectEnum projectSrc) {
        this.projectSrc = projectSrc;
    }

    public ProjectEnum getProjectDest() {
        return projectDest;
    }

    public void setProjectDest(ProjectEnum projectDest) {
        this.projectDest = projectDest;
    }

    public TrackEnum getTrackSrc() {
        return trackSrc;
    }

    public void setTrackSrc(TrackEnum trackSrc) {
        this.trackSrc = trackSrc;
    }

    public TrackEnum getTrackDest() {
        return trackDest;
    }

    public void setTrackDest(TrackEnum trackDest) {
        this.trackDest = trackDest;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

}
