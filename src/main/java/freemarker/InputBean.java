package freemarker;

public class InputBean {

    private String file;
    private String headings;
    private String printedby;
    private String datetime;
    private String title;

    public InputBean() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPrintedby() {
        return printedby;
    }

    public void setPrintedby(String printedby) {
        this.printedby = printedby;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadings() {
        return headings;
    }

    public void setHeadings(String headings) {
        this.headings = headings;
    }
   
}
