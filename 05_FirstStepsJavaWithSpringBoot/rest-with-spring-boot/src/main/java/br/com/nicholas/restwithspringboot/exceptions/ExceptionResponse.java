package br.com.nicholas.restwithspringboot.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String Details;

    public ExceptionResponse(Date timestamp, String message, String Details) {
        this.timestamp = timestamp;
        this.message = message;
        this.Details = Details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return Details;
    }

}
