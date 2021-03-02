package com.mongodb;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@Document(collection="Templates")
public class MongoBean6 {

	public MongoBean6() {
		
	}

    @Id
    private String id;
    
    private String who;
    private Date dateRequested;
    private String name;
    private String headerHTML;
    private String footerHTML;
    private String insideBodyFTL;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHeaderHTML() {
        return headerHTML;
    }
    public void setHeaderHTML(String headerHTML) {
        this.headerHTML = headerHTML;
    }
    public String getFooterHTML() {
        return footerHTML;
    }
    public void setFooterHTML(String footerHTML) {
        this.footerHTML = footerHTML;
    }
    public String getInsideBodyFTL() {
        return insideBodyFTL;
    }
    public void setInsideBodyFTL(String insideBodyFTL) {
        this.insideBodyFTL = insideBodyFTL;
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
 
}
