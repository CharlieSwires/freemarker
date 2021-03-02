package freemarker;

public class TemplateBean {
    private String name;
    private String who;
    private String headerHTML;
    private String footerHTML;
    private String insideBodyFTL;
    
    public TemplateBean() {
        
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHeaderHTML() {
        return headerHTML;
    }
    public void setHeaderHTML(String headerHTML) {
        this.headerHTML = headerHTML;
    }
    public String getFooterHTML() {
        return footerHTML;
    }
    public void setFooterHTML(String footerHTML) {
        this.footerHTML = footerHTML;
    }
    public String getInsideBodyFTL() {
        return insideBodyFTL;
    }
    public void setInsideBodyFTL(String insideBodyFTL) {
        this.insideBodyFTL = insideBodyFTL;
    }
    public String getWho() {
        return who;
    }
    public void setWho(String who) {
        this.who = who;
    }
    
}
