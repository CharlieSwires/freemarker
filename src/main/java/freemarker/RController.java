package freemarker;

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
@RequestMapping(path = "/PDF/")
public class RController  {
    private static final Logger LOGGER = LoggerFactory.getLogger(RController.class);

    @Autowired
    private Freemarker service;

    public RController(Freemarker service2) {
        service = service2;
    }
    

    @PostMapping(path="columns/{cols}", produces="application/json", consumes="application/json")
    public ResponseEntity<ReturnBean> postFile(@PathVariable("cols") Integer cols, @RequestBody InputBean input) {
        String result=null;
        try {
            result = service.convert(input.getFile(), cols);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReturnBean rb = new ReturnBean();
        rb.setFile(result);
        return new ResponseEntity<ReturnBean>(rb, HttpStatus.OK);
    }


}