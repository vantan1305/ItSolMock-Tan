package com.demo.demo.message.request;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class LeavePost {

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Min (20)
    private String reason;

    @NotNull
    private Date formDate;

    @NotNull
    private Date toDate;

    @NotNull
    private String yourMail;

    @NotNull
    private String message;

    public LeavePost() {
    }

    public String getYourMail() {
        return yourMail;
    }

    public void setYourMail(String yourMail) {
        this.yourMail = yourMail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
