package freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoBeanRepository;
import com.mongodb.MongoBeanRepository2;
import com.mongodb.MongoBeanRepository3;
import com.mongodb.MongoBeanRepository4;
import com.mongodb.MongoBeanRepository6;

/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@RestController
@RequestMapping(path = "/")
public class RController  {

    @Autowired
    private Freemarker service;
    @Autowired
    private Encryption encryption;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private MongoBeanRepository beanRepository;
    @Autowired
    private MongoBeanRepository2 beanRepository2;
    @Autowired
    private MongoBeanRepository3 beanRepository3;
    @Autowired
    private MongoBeanRepository4 beanRepository4;
//    @Autowired
//    private MongoBeanRepository5 beanRepository5;
    @Autowired
    private MongoBeanRepository6 beanRepository6;

    public RController(Freemarker service2, Encryption encryption2) {
        service = service2;
        encryption = encryption2;
    }
    
    /**
     * Given cols columns and input choose the correct ftl file
     * and Columns?.java produce a table 1-5 columns in a PDF.
     * CSV indicates EXCEL format CSV input this is used in the 
     * table headingsCSV and fileCSV and should have the same cols
     * 
     * @param cols
     * @param input
     * @return
     */
    @PostMapping(path="tabularToPDF/columns/{cols}", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> postFile(@PathVariable("cols") Integer cols, @RequestBody InputBean input) {
        String result=null;
        Date date = new Date();
        try {
            result = service.convert(input.getTitle(),
                    date,
                    input.getPrintedby(),
                    input.getHeadingsCSV(),
                    input.getFileCSV(),
                    cols);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        String hexString = encryption.byteArrayToHexString(b64d.decode(rb.getSha1()));
        beanRepository3.save(service.converter(input,rb,"result"+hexString+".pdf",date));

        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    /**
     * Given the input which takes inputFTL and replacementStringsCSV
     * pass to freemarker then pass to the PDF generator returning
     * Base64 file and sha1.
     * CSV indicates EXCEL format CSV input this is used in the 
     * replacementStringsCSV and should have columns = 2
     * 
     * @param input
     * @return
     */
    @PostMapping(path="generalToPDF", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> postFile(@RequestBody InputBeanGeneral input) {
        String result=null;
        Date date = new Date();
        try {
            result = service.convert(input.getInputFTL(),
                    input.getReplacementStringsCSV(),
                    input.getWho(),
                    date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        String hexString = encryption.byteArrayToHexString(b64d.decode(rb.getSha1()));
        beanRepository2.save(service.converter(input,rb,"result"+hexString+".pdf",date));
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    /**
     * Given the input which takes bodyFTL, headerHTML, footerHTML
     * and arrayOfItems pass to freemarker then pass to the PDF
     * generator returning Base64 file and sha1.
     * CSV indicates EXCEL format CSV input this is used in the 
     * replacementStringsCSV and should have columns = 2, key, value
     * @param input
     * @return
     */
    @PostMapping(path="generalArrayToPDF", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> postFile(@RequestBody InputBeanGeneral2 input) {
        String result=null;
        Date date = new Date();
        try {
            result = service.convert(input, date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        String hexString = encryption.byteArrayToHexString(b64d.decode(rb.getSha1()));
        beanRepository.save(service.converter(input,rb,"result"+hexString+".pdf",date));
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
//    /**
//     * Saves the template to file
//     * @param input
//     * @return
//     */
//    @PostMapping(path="Init", produces="application/json", consumes="application/json")
//    public ResponseEntity<Boolean> initFile(@RequestBody InputBeanInit input) {
//        Boolean result=null;
//        try {
//            Date date = new Date();
//            result = service.init(input.getInputFTL(),
//                    input.getFilename());
//            beanRepository5.save(service.converter(input,date));
//
//        } catch (Exception e) {
//            result = false;
//            throw new RuntimeException(e);
//        }
//        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
//    }
    /**
     * Converts a HTML string to PDF
     * @param input
     * @return
     */
    @PostMapping(path="toPDF", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> toPDF(@RequestBody InputHTMLString input) {
        String result=null;
        Date date = new Date();
        try {
            result = service.convert2(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        String hexString = encryption.byteArrayToHexString(b64d.decode(rb.getSha1()));
        beanRepository4.save(service.converter(input,rb,"result"+hexString+".pdf",date));

        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    /**
     * Compares the given resultFilename with one generated from the file.
     * Do this before calling if you are in Java.
     * 
     * byte[] bytes = Files.readAllBytes(Paths.get(filePath));
     * Base64.Encoder b64e = Base64.getEncoder();
     * String stringB64 = b64e.encodeToString(bytes);
     * 
     * @param input
     * @return
     */
    @PostMapping(path="isTamperedWith", produces="application/json", consumes="application/json")
    public ResponseEntity<Boolean> isTamperedWith(@RequestBody TamperedBean input) {
        Base64.Decoder b64d = Base64.getDecoder();
        return new ResponseEntity<Boolean>(!input.getResultFilename().equals(
                "result"+encryption.byteArrayToHexString(b64d.decode(encryption.sha1(
                        b64d.decode(input.getFileB64()))))+".pdf"), HttpStatus.OK);
    }
    /**
     * Save the template to the mongo DB.
     * @param input
     * @return
     */
    @PostMapping(path="saveTemplate", produces="application/json", consumes="application/json")
    public synchronized ResponseEntity<Boolean> saveTemplate(@RequestBody TemplateBean input) {
        Date date = new Date();
        beanRepository6.deleteByName(input.getName());
        beanRepository6.save(service.converter(input, date));
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
    /**
     * Load the template form the mongo DB.
     * @param name
     * @return
     */
    @GetMapping(path="loadTemplate/{name}", produces="application/json")
    public ResponseEntity<TemplateBeanResponse> loadTemplate(@PathVariable("name") String name) {
        
        TemplateBeanResponse tb = service.converter(beanRepository6.findByName(name));
        return new ResponseEntity<TemplateBeanResponse>(tb, HttpStatus.OK);
    }
    /**
     * test.pdf -> result<sha1HexString>.pdf downloads to the browser
     * @param request
     * @return
     * @throws Exception
     */
    private ResponseEntity<Resource> downloadFile(HttpServletRequest request) throws Exception {
        File file= new File("test.pdf");
        // ...(file is initialised)...
        byte[] fileContent = Files.readAllBytes(file.toPath());
        Base64.Decoder b64d = Base64.getDecoder();
        String hexString = encryption.byteArrayToHexString(b64d.decode(encryption.sha1(fileContent)));
        try (FileOutputStream fos = new FileOutputStream("result"+hexString+".pdf")) {
            fos.write(fileContent);
         }

        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource("result"+hexString+".pdf");
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    /**
     * Combination method
     * @param cols
     * @param input
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(path="TabularToPDFAndDownload/columns/{cols}", consumes="application/json")
    public synchronized ResponseEntity<Resource> postFileAndDownload(@PathVariable("cols") Integer cols, @RequestBody InputBean input, HttpServletRequest request) throws Exception {
        postFile(cols, input);
        return downloadFile(request);

    }
    /**
     * Combination method
     * @param input
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(path="GeneralToPDFAndDownload", consumes="application/json")
    public synchronized ResponseEntity<Resource> postFileAndDownload(@RequestBody InputBeanGeneral input, HttpServletRequest request) throws Exception {
        postFile(input);
        return downloadFile(request);

    }

    /**
     * Combination method
     * @param input
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(path="GeneralArrayToPDFAndDownload", consumes="application/json")
    public synchronized ResponseEntity<Resource> postFileAndDownload(@RequestBody InputBeanGeneral2 input, HttpServletRequest request) throws Exception {
        postFile(input);
        return downloadFile(request);
    }
    /**
     * Combination method
     * @param input
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(path="ToPDFAndDownload", consumes="application/json")
    public ResponseEntity<Resource> toPDFAndDownload(@RequestBody InputHTMLString input,HttpServletRequest request) throws Exception {
        toPDF(input);
        return downloadFile(request);
    }
 
}