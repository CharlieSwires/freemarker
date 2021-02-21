package freemarker;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
public class RController  {
    private static final Logger LOGGER = LoggerFactory.getLogger(RController.class);

    @Autowired
    private Freemarker service;
    @Autowired
    private Encryption encryption;

    public RController(Freemarker service2) {
        service = service2;
    }
    

    @PostMapping(path="TabularToPDF/columns/{cols}", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> postFile(@PathVariable("cols") Integer cols, @RequestBody InputBean input) {
        String result=null;
        try {
            result = service.convert(input.getTitle(),
                    input.getDatetime(),
                    input.getPrintedby(),
                    input.getHeadings(),
                    input.getFile(),
                    cols);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    @PostMapping(path="GeneralToPDF", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> postFile(@RequestBody InputBeanGeneral input) {
        String result=null;
        try {
            result = service.convert(input.getInputFTL(),
                    input.getReplacementStrings());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFileB64(result);
        Base64.Decoder b64d = Base64.getDecoder();
        rb.setSha1(encryption.sha1(b64d.decode(result)));
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }
    @PostMapping(path="isTamperdWith", produces="application/json", consumes="application/json")
    public ResponseEntity<Boolean> isTamperedWith(@RequestBody TamperedBean input) {
        Base64.Decoder b64d = Base64.getDecoder();
        return new ResponseEntity<Boolean>(!input.getSha1().equals(
                encryption.sha1(
                        b64d.decode(input.getFileB64()))), HttpStatus.OK);
    }


}