package com.kopiitem.upload.common;

/**
 *
 * @author donny.fm
 */
public enum ModuleEnum {

    SUPPLIER("supplier"), GTPREGISTRATION("gtpregistration"), SUPPLIERINTERFACE("supplierinterface"), FILEATTACHMENTPORTAL("fileattachmentPortal");

    private String desc;

    private ModuleEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
