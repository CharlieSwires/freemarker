package freemarker;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
public class TamperedBean {

    private String fileB64;
    private String resultFilename;

    public TamperedBean() {
    }

    public String getFileB64() {
        return fileB64;
    }


    public void setFileB64(String fileB64) {
        this.fileB64 = fileB64;
    }

    public String getResultFilename() {
        return resultFilename;
    }

    public void setResultFilename(String resultFilename) {
        this.resultFilename = resultFilename;
    }

   
}
