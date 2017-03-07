package com.kopiitem.upload.service;

import com.kopiitem.upload.common.FileProxy;
import com.kopiitem.upload.exception.CustomException;
import java.io.IOException;

/**
 *
 * @author donny.fm
 */
public class UploadManager {

    private final FileProxy fileProxy;

    public UploadManager(FileProxy fileProxy) {
        this.fileProxy = fileProxy;
    }

    public void doUpload() {
        fileProxy.upload();
    }

    public void doUploadByFile(String file, int index) throws IOException, CustomException {
        fileProxy.upload(file, index);
    }
    
    public String getLog()  {
        return fileProxy.getLog();
    }

}
