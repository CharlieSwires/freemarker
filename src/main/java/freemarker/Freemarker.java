package freemarker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

@Service
public class Freemarker {

    @SuppressWarnings("unchecked")
    public synchronized String convert(String csvFilename, int columns) throws Exception {
        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setDirectoryForTemplateLoading(new File("/usr/local/tomcat/webapps/freemarker/WEB-INF"));

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.

        // 2.1. Prepare the template input:

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("title", "Charlie example");

        List systems = new ArrayList();

        Reader in = new StringReader(csvFilename);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            Object c = null;
            switch(columns) {
            
            case 1:
                c = new Columns1();
                ((Columns1) c).setCol0(record.get(0));
                break;
            case 2:
                c = new Columns2();
                ((Columns2) c).setCol0(record.get(0));
                ((Columns2) c).setCol1(record.get(1));
                break;
            case 3:
                c = new Columns3();
                ((Columns3) c).setCol0(record.get(0));
                ((Columns3) c).setCol1(record.get(1));
                ((Columns3) c).setCol2(record.get(2));
                break;
            case 4:
                c = new Columns4();
                ((Columns4) c).setCol0(record.get(0));
                ((Columns4) c).setCol1(record.get(1));
                ((Columns4) c).setCol2(record.get(2));
                ((Columns4) c).setCol3(record.get(3));
                 break;
            case 5:
                c = new Columns5();
                ((Columns5) c).setCol0(record.get(0));
                ((Columns5) c).setCol1(record.get(1));
                ((Columns5) c).setCol2(record.get(2));
                ((Columns5) c).setCol3(record.get(3));
                ((Columns5) c).setCol4(record.get(4));
                 break;
                default:
                    throw new IllegalArgumentException();
            }
            systems.add(c);
        }

        input.put("systems", systems);

        // 2.2. Get the template

        Template template = null;
        switch(columns) {
        
        case 1:
            template = cfg.getTemplate("columns1.ftl");
            break;
        case 2:
            template = cfg.getTemplate("columns2.ftl");
            break;
        case 3:
            template = cfg.getTemplate("columns3.ftl");
            break;
        case 4:
            template = cfg.getTemplate("columns4.ftl");
            break;
        case 5:
            template = cfg.getTemplate("columns5.ftl");
            break;
        default:
            throw new IllegalArgumentException();
        }

        // 2.3. Generate the output

        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);
        consoleWriter.flush();

        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File("/usr/local/tomcat/output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }

        template =cfg.getTemplate("index.js");;
        consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);
        consoleWriter.flush();
        fileWriter = new FileWriter(new File("/usr/local/tomcat/index.js"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }
      
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            throw new RuntimeException("Not Supported");
//            builder.command("cmd.exe", "/c", "del test.pdf");
//            builder.command("cmd.exe", "/c", "node index.js");
        } else {
            builder.command("sh", "-c", "rm test.pdf");
            builder.command("sh", "-c", "node index.js");
        }
        builder.directory(new File("/usr/local/tomcat"));
        Process process = builder.start();
        int i = 0;
        BufferedReader br = null;
        while (i++ < 30) {
            try {
                br =  new BufferedReader(new FileReader(new File("/usr/local/tomcat/test.pdf")));
            } catch (Exception e) {
                Thread.sleep(10000);
                br = null;
            }
        }
        String result ="";
        if (br == null) throw new RuntimeException("Timed out");
        result = "";
        while(true) {
            int c = br.read();
            if (c == -1) break;
            result += ""+(char)c;
        }
        br.close();
        return result;
    }
}
