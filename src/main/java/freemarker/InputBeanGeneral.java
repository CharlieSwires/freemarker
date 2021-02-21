package freemarker;

public class InputBeanGeneral {

    private String inputFTL;
    private String replacementStrings;
    
    public InputBeanGeneral() {
        
    }

    public String getReplacementStrings() {
        return replacementStrings;
    }
    public void setReplacementStrings(String replacementStrings) {
        this.replacementStrings = replacementStrings;
    }

    public String getInputFTL() {
        return inputFTL;
    }

    public void setInputFTL(String inputFTL) {
        this.inputFTL = inputFTL;
    }
}
