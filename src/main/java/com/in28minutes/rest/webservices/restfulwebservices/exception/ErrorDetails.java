package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timeStamp;

    private String message;
    private String details;
    public ErrorDetails(final LocalDateTime timeStamp, final String message, final String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;

    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(final LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(final String details) {
        this.details = details;
    }
    public void setMessage(final String message) {
        this.message = message;
    }

}
