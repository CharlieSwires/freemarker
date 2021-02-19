package freemarker;

public class TestBean {

    private String name;
    private String developer;

    public TestBean(String name, String developer) {
        this.name = name;
        this.developer = developer;
    }

    public String getName() {
        return name;
    }

    public String getDeveloper() {
        return developer;
    }
}
