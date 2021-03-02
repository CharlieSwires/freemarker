package freemarker;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoBeanRepository;
import com.mongodb.MongoBeanRepository2;
import com.mongodb.MongoBeanRepository3;
import com.mongodb.MongoBeanRepository4;
import com.mongodb.MongoBeanRepository5;
import com.mongodb.MongoBeanRepository6;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses= {MongoBeanRepository.class,
        MongoBeanRepository2.class,
        MongoBeanRepository3.class,
        MongoBeanRepository4.class,
        MongoBeanRepository5.class,
        MongoBeanRepository6.class
        })
public class MainApp extends SpringBootServletInitializer{

//@SpringBootApplication
//public class MainApp{
//   public static void main(String[] args) {
//       SpringApplication.run(MainApp.class, args);
//   }
}