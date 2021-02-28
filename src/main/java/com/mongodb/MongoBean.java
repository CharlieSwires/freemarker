package com.mongodb;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 * Copyright 2021 Charles Swires All Rights Reserved
 * @author charl
 *
 */
@Document(collection="General2")
public class MongoBean {

	public MongoBean() {
		
	}

    @Id
    private String id;
    
    private String who;
    private Date dateRequested;
    private String outfilename;

    private String fileB64;
    private String sha1;

    private String headerHTML;
    private String bodyFTL;
    private String footerHTML;
    private ArrayOfItems[] arrayOfItems;

    public static class ArrayOfItems {
        private String inputCSV;
        private FindingsText[] findingsText;
  
        public ArrayOfItems() {
            
        }
        public static class FindingsText{
            private String type;
            private String note;
            public FindingsText() {
                
            }
                 public String getType() {
                return type;
            }
            public void setType(String type) {
                this.type = type;
            }
            public String getNote() {
                return note;
            }
            public void setNote(String note) {
                this.note = note;
            }
            @Override
            public String toString() {
                return "FindingsText [type=" + type + ", note=" + note + "]";
            }
        }

        public String getInputCSV() {
            return inputCSV;
        }
        public void setInputCSV(String inputCSV) {
            this.inputCSV = inputCSV;
        }
        public FindingsText[] getFindingsText() {
            return findingsText;
        }
        public void setFindingsText(FindingsText[] findingsText) {
            this.findingsText = findingsText;
        }
        @Override
        public String toString() {
            return "ArrayOfItems [inputCSV=" + inputCSV + ", findingsText="
                    + Arrays.toString(findingsText) + "]";
        }
    }

 
    public String getHeaderHTML() {
        return headerHTML;
    }

    public void setHeaderHTML(String headerHTML) {
        this.headerHTML = headerHTML;
    }

    public String getBodyFTL() {
        return bodyFTL;
    }

    public void setBodyFTL(String bodyFTL) {
        this.bodyFTL = bodyFTL;
    }

    public String getFooterHTML() {
        return footerHTML;
    }

    public void setFooterHTML(String footerHTML) {
        this.footerHTML = footerHTML;
    }

    public ArrayOfItems[] getArrayOfItems() {
        return arrayOfItems;
    }

    public void setArrayOfItems(ArrayOfItems[] arrayOfItems) {
        this.arrayOfItems = arrayOfItems;
    }

    @Override
    public String toString() {
        return "InputBeanGeneral2 [headerHTML=" + headerHTML + ", bodyFTL=" + bodyFTL
                + ", footerHTML=" + footerHTML + ", arrayOfItems=" + Arrays.toString(arrayOfItems)
                + "]";
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
