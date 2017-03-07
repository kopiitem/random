package com.kopiitem.upload.common;

/**
 *
 * @author donny.fm
 */
public enum ModeEnum {

    SENDING("Sending"), DELETING("Deleting"), ADDING("Adding");

    private String mode;

    private ModeEnum(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

}
