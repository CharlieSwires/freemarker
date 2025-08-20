package freemarker;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
public class InputBeanInit {
    private String inputFTL;
    private String filename;
    private String who;
    
    public InputBeanInit() {
        
    }


    public String getInputFTL() {
        return inputFTL;
    }

    public void setInputFTL(String inputFTL) {
        this.inputFTL = inputFTL;
    }


    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }


    public String getWho() {
        return who;
    }


    public void setWho(String who) {
        this.who = who;
    }
}
