package freemarker;

public class InputBeanGeneral {

    private String inputFTL;
    private String replacementStringsCSV;
    private String who;
    
    public InputBeanGeneral() {
        
    }


    public String getInputFTL() {
        return inputFTL;
    }

    public void setInputFTL(String inputFTL) {
        this.inputFTL = inputFTL;
    }


    public String getReplacementStringsCSV() {
        return replacementStringsCSV;
    }


    public void setReplacementStringsCSV(String replacementStringsCSV) {
        this.replacementStringsCSV = replacementStringsCSV;
    }


    public String getWho() {
        return who;
    }


    public void setWho(String who) {
        this.who = who;
    }
}
