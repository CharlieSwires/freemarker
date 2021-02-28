package com.mongodb;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@Document(collection="FTLFile")
public class MongoBean5 {

	public MongoBean5() {
		
	}

    @Id
    private String id;
    
    private String who;
    private Date dateRequested;
    private String inputFTL;
    private String filename;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getWho() {
        return who;
    }
    public void setWho(String who) {
        this.who = who;
    }
    public Date getDateRequested() {
        return dateRequested;
    }
    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }
    public String getInputFTL() {
        return inputFTL;
    }
    public void setInputFTL(String inputFTL) {
        this.inputFTL = inputFTL;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }


}
