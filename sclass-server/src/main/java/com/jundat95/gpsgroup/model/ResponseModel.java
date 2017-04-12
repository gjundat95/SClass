package com.jundat95.gpsgroup.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by tinhngo on 2/26/17.
 */

@Document(collection = "Responses")
public class ResponseModel {

    private String status;
    private String data;
    private String message;

    public ResponseModel() {
    }

    public ResponseModel(String status, String data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
