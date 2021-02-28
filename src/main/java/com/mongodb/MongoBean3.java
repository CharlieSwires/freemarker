package com.mongodb;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@Document(collection="Tables")
public class MongoBean3 {

	public MongoBean3() {
		
	}

    @Id
    private String id;
    
    private String who;
    private Date dateRequested;
    private String outfilename;

    private String fileB64;
    private String sha1;

    private String fileCSV;
    private String headingsCSV;
    private String title;

 
    public String getFileCSV() {
        return fileCSV;
    }

    public void setFileCSV(String fileCSV) {
        this.fileCSV = fileCSV;
    }

    public String getHeadingsCSV() {
        return headingsCSV;
    }

    public void setHeadingsCSV(String headingsCSV) {
        this.headingsCSV = headingsCSV;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getOutfilename() {
        return outfilename;
    }

    public void setOutfilename(String outfilename) {
        this.outfilename = outfilename;
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

}
