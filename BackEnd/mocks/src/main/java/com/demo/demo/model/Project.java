package com.demo.demo.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_auto")
    @SequenceGenerator(name = "project_auto", sequenceName = "project_auto", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "unit")
    private String unit;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "status", nullable = true)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "timestart", nullable = true)
    private Date timeStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "timeend", nullable = true)
    private Date timeEnd;

    private Boolean deleteFlag;


    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Collection<ProjectUser> projectUserList;

    public Project(Long id, String unit, String name, String description, String status, Date timeStart, Date timeEnd, Boolean deleteFlag) {
        this.id = id;
        this.unit = unit;
        this.name = name;
        this.description = description;
        this.status = status;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.deleteFlag = deleteFlag;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Project() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Collection<ProjectUser> getProjectUserList() {
        return projectUserList;
    }

    public void setProjectUserList(Collection<ProjectUser> projectUserList) {
        this.projectUserList = projectUserList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

}
