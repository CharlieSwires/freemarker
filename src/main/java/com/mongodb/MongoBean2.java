package com.mongodb;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@Document(collection="General")
public class MongoBean2 {

	public MongoBean2() {
		
	}

    @Id
    private String id;
    
    private String who;
    private Date dateRequested;
    private String outfilename;

    private String fileB64;
    private String sha1;

    private String inputFTL;
    private String replacementStringsCSV;
    
    public String getInputFTL() {
        return inputFTL;
    }

    public void setInputFTL(String inputFTL) {
        this.inputFTL = inputFTL;
    }


    public String getReplacementStringsCSV() {
        return replacementStringsCSV;
    }


    public void setReplacementStringsCSV(String replacementStringsCSV) {
        this.replacementStringsCSV = replacementStringsCSV;
    }

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

    public String getFileB64() {
        return fileB64;
    }

    public void setFileB64(String fileB64) {
        this.fileB64 = fileB64;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getOutfilename() {
        return outfilename;
    }

    public void setOutfilename(String outfilename) {
        this.outfilename = outfilename;
    }
}
