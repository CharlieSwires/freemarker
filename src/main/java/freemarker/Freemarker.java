package freemarker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.mongodb.MongoBean;
import com.mongodb.MongoBean2;
import com.mongodb.MongoBean3;
import com.mongodb.MongoBean4;
import com.mongodb.MongoBean5;
import com.mongodb.MongoBean6;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@Service
public class Freemarker {

    static boolean doneOnce = false;
    @SuppressWarnings("unchecked")
    public synchronized String convert(String title,
            Date date,
            String printedby,
            String csvHeadings,
            String csvList,
            int columns) throws Exception {
        assert(title != null);
        assert(date != null);
        assert(printedby != null);
        assert(csvHeadings != null);
        assert(csvList != null);

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

        input.put("title", title);
        input.put("printedby", printedby);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        input.put("datetime", ""+cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));

        List systems = new ArrayList();

        Reader in = new StringReader(csvHeadings);
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

        input.put("headings", systems);
        systems = new ArrayList();

        in = new StringReader(csvList);
        records = CSVFormat.EXCEL.parse(in);
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

        return publishPDF();
    }

    public synchronized Boolean init(String inputHTML, String filename) throws Exception {
        assert(inputHTML != null);
        assert(filename != null);

        FileWriter fw = new FileWriter(new File("/usr/local/tomcat/"+filename));
        fw.write(inputHTML);
        fw.close();
        return true;
    } 
    public synchronized String convert(String inputHTML, String replacementStrings, String who, Date date) throws Exception {
        assert(inputHTML != null);
        assert(replacementStrings != null);
        assert(who != null);
        assert(date != null);

        FileWriter fw = new FileWriter(new File("/usr/local/tomcat/input.ftl"));
        fw.write(inputHTML);
        fw.close();

        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setDirectoryForTemplateLoading(new File("/usr/local/tomcat"));
        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<String, Object>();
        input.put("who", who);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        input.put("datePrinted", ""+cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));

        Reader in = new StringReader(replacementStrings);
        Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
        for (CSVRecord record : records) {
            input.put(record.get(0), record.get(1)); 
        }

        Template template = cfg.getTemplate("input.ftl");

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

        return publishPDF();
    }

    public synchronized String convert2(Partha1InputBean[] input2) throws Exception {

        assert(input2 != null);
        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setDirectoryForTemplateLoading(new File("/usr/local/tomcat"));

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
        // Write output to the console
        Writer consoleWriter = new OutputStreamWriter(System.out);

        List<ColumnsPartha1InputBean> systems = new ArrayList<ColumnsPartha1InputBean>();
        consoleWriter.write(Arrays.toString(input2));
        for(int i = 0; i < input2.length; i++) {
            systems.add(input2[i].convert());
        }
        input.put("systems", systems);
        consoleWriter.flush();


        // 2.2. Get the template

        Template template = null;
        template = cfg.getTemplate("columnsPartha1Template.ftl");

        // 2.3. Generate the output

        // Write output to the console
        consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);
        consoleWriter.flush();

        // For the sake of example, also write output into a file:
        Writer fileWriter = new FileWriter(new File("/usr/local/tomcat/output.html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }

        return publishPDF();
    }
    public synchronized String convert2(InputHTMLString input) throws Exception {
        assert(input != null);

        FileWriter fw = new FileWriter(new File("/usr/local/tomcat/output.html"));
        fw.write(input.getInputHTML());
        fw.close();

        Writer consoleWriter = new OutputStreamWriter(System.out);
        consoleWriter.write(input.getInputHTML());
        consoleWriter.flush();
        return publishPDF();
    }


    private synchronized String publishPDF() throws Exception {

        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        ProcessBuilder builder = new ProcessBuilder();
        if (isWindows) {
            throw new RuntimeException("Not Supported");
            //            builder.command("cmd.exe", "/c", "del test.pdf");
            //            builder.command("cmd.exe", "/c", "node index.js");
        } else {
            if (System.getenv("OVERRIDE_WITH_HTML") != null && System.getenv("OVERRIDE_WITH_HTML").equals("true")) {
                builder.command("sh", "-c", "cp output.html test.html");
                builder.directory(new File("/usr/local/tomcat"));
                Process process = builder.start(); 
                File file = new File("/usr/local/tomcat/test.html");
                byte[] fileContent = Files.readAllBytes(file.toPath());
                Base64.Encoder b64e = Base64.getEncoder();
                //        Base64.Decoder b64d = Base64.getDecoder();
                String result = b64e.encodeToString(fileContent);
                return result;
            } 
            builder.command("sh", "-c", "chmod a+xr index.js");
            builder.directory(new File("/usr/local/tomcat"));
            Process process = builder.start();

            if(!doneOnce) {
                builder.command("sh", "-c", "adduser user");
                builder.directory(new File("/usr/local/tomcat"));
                process = builder.start();
                OutputStream os = process.getOutputStream();
                os.write("\n1\n1\n\n".getBytes());
                doneOnce = true;
            }
        }

        builder = new ProcessBuilder();
        if (isWindows) {
            throw new RuntimeException("Not Supported");
            //            builder.command("cmd.exe", "/c", "del test.pdf");
            //            builder.command("cmd.exe", "/c", "node index.js");
        } else {
                builder.command("sh", "-c", "rm *.pdf");
                builder.directory(new File("/usr/local/tomcat"));
                Process process = builder.start();
                builder.command("sh", "-c", "su user");
                builder.directory(new File("/usr/local/tomcat"));
                process = builder.start();
                builder.command("sh", "-c", "node index.js");
                builder.directory(new File("/usr/local/tomcat"));
                process = builder.start();
            
        }
        int i = 0;
        byte[] fileContent = null;
        while (i++ < 30) {
            try {
                File file = new File("/usr/local/tomcat/test.pdf");
                fileContent = Files.readAllBytes(file.toPath());
            } catch (Exception e) {
                Thread.sleep(10000);
                fileContent = null;
            }
        }
        if (fileContent == null) throw new RuntimeException("Timed out");
        Base64.Encoder b64e = Base64.getEncoder();
        //        Base64.Decoder b64d = Base64.getDecoder();
        String result = b64e.encodeToString(fileContent);

        builder = new ProcessBuilder();
        builder.command("sh", "-c", "exit");
        builder.directory(new File("/usr/local/tomcat"));
        Process process = builder.start();
        //        try (FileOutputStream fos = new FileOutputStream("/usr/local/tomcat/result.pdf")) {
        //            fos.write(b64d.decode(result));
        //        }        
        return result;    
    }

    public synchronized String convert(InputBeanGeneral2 input, Date date) throws Exception {
        assert(input != null);
        assert(date != null);
        Writer consoleWriter = new OutputStreamWriter(System.out);
        consoleWriter.write("=====================>"+input.toString());
        consoleWriter.flush();
        // 1. Configure FreeMarker
        //
        // You should do this ONLY ONCE, when your application starts,
        // then reuse the same Configuration object elsewhere.

        Configuration cfg = new Configuration();
        // Where do we load the templates from:
        cfg.setDirectoryForTemplateLoading(new File("/usr/local/tomcat"));

        // Some other recommended settings:
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        FileWriter fw = new FileWriter(new File("/usr/local/tomcat/input.ftl"));
        fw.write(input.getBodyFTL());
        fw.close();

        // 2. Proccess template(s)
        //
        // You will do this for several times in typical applications.

        // 2.1. Prepare the template input:
        for(int i = 0; input.getArrayOfItems() != null && i< input.getArrayOfItems().length; i++)
        {
            Map<String, Object> input2 = new HashMap<String, Object>();
            List<Object> systems = new ArrayList<Object>();

            input2.put("who", input.getWho());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            input2.put("datePrinted", ""+cal.get(Calendar.DATE)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));

            for(int j = 0; input.getArrayOfItems()[i].getFindingsText() != null && j < input.getArrayOfItems()[i].getFindingsText().length; j++ ) {
                InputBeanGeneral2.ArrayOfItems.FindingsText item = new InputBeanGeneral2.ArrayOfItems.FindingsText();
                item.setType(input.getArrayOfItems()[i].getFindingsText()[j].getType());
                item.setNote(input.getArrayOfItems()[i].getFindingsText()[j].getNote());
                systems.add((Object)item);

            }

            Reader in = new StringReader(input.getArrayOfItems()[i].getInputCSV());
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            for (CSVRecord record : records) {
                input2.put(record.get(0), record.get(1)); 
            }

            Template template = cfg.getTemplate("input.ftl");
            input2.put("systems", systems);

            // Write output to the console
            consoleWriter = new OutputStreamWriter(System.out);
            template.process(input2, consoleWriter);
            consoleWriter.flush();
            // For the sake of example, also write output into a file:
            Writer fileWriter = new FileWriter(new File("/usr/local/tomcat/output"+i+".html"));
            try {
                template.process(input2, fileWriter);
            } finally {
                fileWriter.close();
            }

        }
        StringBuilder sb = new StringBuilder();
        sb.append(input.getHeaderHTML());
        for(int i = 0; i< input.getArrayOfItems().length; i++) {
            String line;
            BufferedReader fileReader = new BufferedReader(new FileReader(new File("/usr/local/tomcat/output"+i+".html")));
            while((line = fileReader.readLine())!=null){
                sb.append(line+"\n");
            }
            fileReader.close();
        }
        sb.append(input.getFooterHTML());

        FileWriter fw1 = new FileWriter(new File("/usr/local/tomcat/output.html"));
        fw1.write(sb.toString());
        fw1.close();

        return publishPDF();
    }

    public MongoBean converter(InputBeanGeneral2 input, ReturnBean rb, String outFile, Date date) {
        assert(input != null);
        assert(rb != null);
        assert(outFile != null);
        assert(date != null);

        MongoBean mb = new MongoBean();
        mb.setBodyFTL(input.getBodyFTL());
        mb.setDateRequested(date);
        mb.setFileB64(rb.getFileB64());
        mb.setFooterHTML(input.getFooterHTML());
        mb.setHeaderHTML(input.getHeaderHTML());
        mb.setSha1(rb.getSha1());
        mb.setWho(input.getWho());
        mb.setOutfilename(outFile);
        MongoBean.ArrayOfItems[] arrayOfItems = new MongoBean.ArrayOfItems[input.getArrayOfItems().length];
        int i = 0;
        if(input.getArrayOfItems() != null) {
            for(InputBeanGeneral2.ArrayOfItems item : input.getArrayOfItems()) {
                MongoBean.ArrayOfItems arrayItem = new MongoBean.ArrayOfItems();
                arrayItem.setInputCSV(item.getInputCSV());
                MongoBean.ArrayOfItems.FindingsText[] findings = null;
                if(item.getFindingsText() != null) {
                    findings = new MongoBean.ArrayOfItems.FindingsText[item.getFindingsText().length];
                    int j = 0;
                    for(InputBeanGeneral2.ArrayOfItems.FindingsText item2: item.getFindingsText()) {
                        MongoBean.ArrayOfItems.FindingsText findingItem = new MongoBean.ArrayOfItems.FindingsText();
                        findingItem.setType(item2.getType());
                        findingItem.setNote(item2.getNote());
                        findings[j++] = findingItem;
                    }
                }
                arrayItem.setFindingsText(findings);
                arrayOfItems[i++] = arrayItem;
            }
        }
        mb.setArrayOfItems(arrayOfItems);

        return mb;
    }

    public MongoBean2 converter(InputBeanGeneral input, ReturnBean rb, String outFile, Date date) {
        assert(input != null);
        assert(rb != null);
        assert(outFile != null);
        assert(date != null);
        MongoBean2 mb = new MongoBean2();
        mb.setDateRequested(date);
        mb.setFileB64(rb.getFileB64());
        mb.setInputFTL(input.getInputFTL());
        mb.setOutfilename(outFile);
        mb.setReplacementStringsCSV(input.getReplacementStringsCSV());
        mb.setSha1(rb.getSha1());
        mb.setWho(input.getWho());
        return mb;
    }

    public MongoBean3 converter(InputBean input, ReturnBean rb, String string, Date date) {
        assert(input != null);
        assert(rb != null);
        assert(string != null);
        assert(date != null);
        MongoBean3 mb = new MongoBean3();
        mb.setDateRequested(date);
        mb.setFileB64(rb.getFileB64());
        mb.setFileCSV(input.getFileCSV());
        mb.setHeadingsCSV(input.getHeadingsCSV());
        mb.setOutfilename(string);
        mb.setSha1(rb.getSha1());
        mb.setTitle(input.getTitle());
        mb.setWho(input.getPrintedby());
        return mb;
    }

    public MongoBean4 converter(InputHTMLString input, ReturnBean rb, String outFile, Date date) {
        assert(input != null);
        assert(rb != null);
        assert(outFile != null);
        assert(date != null);
        MongoBean4 mb = new MongoBean4();
        mb.setDateRequested(date);
        mb.setFileB64(rb.getFileB64());
        mb.setSha1(rb.getSha1());
        mb.setWho(input.getWho());
        mb.setInputHTML(input.getInputHTML());
        return mb;
    }

    public MongoBean5 converter(InputBeanInit input, Date date) {
        MongoBean5 mb = new MongoBean5();
        mb.setDateRequested(date);
        mb.setFilename(input.getFilename());
        mb.setInputFTL(input.getInputFTL());
        mb.setWho(input.getWho());
        return mb;
    }

    public MongoBean6 converter(TemplateBean input, Date date) {
        MongoBean6 mb = new MongoBean6();
        mb.setDateRequested(date);;
        mb.setFooterHTML(input.getFooterHTML());
        mb.setHeaderHTML(input.getHeaderHTML());
        mb.setInsideBodyFTL(input.getInsideBodyFTL());
        mb.setName(input.getName());
        mb.setWho(input.getWho());
        return mb;
    }
    public TemplateBeanResponse converter(MongoBean6 input) {
        TemplateBeanResponse mb = new TemplateBeanResponse();
        mb.setFooterHTML(input.getFooterHTML());
        mb.setHeaderHTML(input.getHeaderHTML());
        mb.setInsideBodyFTL(input.getInsideBodyFTL());
        mb.setName(input.getName());
        mb.setWho(input.getWho());
        mb.setDateStored(input.getDateRequested());
        return mb;
    }
}