package com.demo.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "BIRTHDAY_MAIL_CONFIG")
public class BirthDayConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "birthday_auto")
    @SequenceGenerator(name = "birthday_auto", sequenceName = "birthday_auto", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "CONTENT_HEAD_LINE")
    private String mailHeadLine;

    @Column(name = "MAIL_INTRO_CONTENT")
    private String mailIntroContent;

    @Column(name = "MAIL_MAIN_CONTENT")
    private String mailMainContent;

    @Column(name = "REGARDS_FROM")
    private String regardsFrom;

    @Column(name = "MAIL_IMAGE_LINK")
    private String mailImageLink;

    @Column(name = "FROM_EMAIL")
    private String fromEmail;

    @Column(name = "FROM_NAME")
    private String fromName;

    @Column(name = "SUBJECT_LINE")
    private String mailSubject;

    @Column(name = "CC_RECIPIENTS")
    private String ccRecipients;

    @Column(name = "COMPANY_LOGO_LINK")
    private String companyLogoLink;

    public BirthDayConfig() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMailHeadLine() {
        return mailHeadLine;
    }

    public void setMailHeadLine(String mailHeadLine) {
        this.mailHeadLine = mailHeadLine;
    }

    public String getMailIntroContent() {
        return mailIntroContent;
    }

    public void setMailIntroContent(String mailIntroContent) {
        this.mailIntroContent = mailIntroContent;
    }

    public String getMailMainContent() {
        return mailMainContent;
    }

    public void setMailMainContent(String mailMainContent) {
        this.mailMainContent = mailMainContent;
    }

    public String getRegardsFrom() {
        return regardsFrom;
    }

    public void setRegardsFrom(String regardsFrom) {
        this.regardsFrom = regardsFrom;
    }

    public String getMailImageLink() {
        return mailImageLink;
    }

    public void setMailImageLink(String mailImageLink) {
        this.mailImageLink = mailImageLink;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getCcRecipients() {
        return ccRecipients;
    }

    public void setCcRecipients(String ccRecipients) {
        this.ccRecipients = ccRecipients;
    }

    public String getCompanyLogoLink() {
        return companyLogoLink;
    }

    public void setCompanyLogoLink(String companyLogoLink) {
        this.companyLogoLink = companyLogoLink;
    }
}
