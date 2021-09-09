package com.demo.demo.message.request;

import com.demo.demo.model.Department;
import com.demo.demo.model.Issue;
import com.demo.demo.model.Users;

import java.util.Date;
import java.util.List;

public class UpdateProject {
    private Long id;
    private String unit;
    private String name;
    private String description;
    private String status;
    private Date timeStart;
    private Date timeEnd;
    private List<Users> usersList;
    private Boolean deleteFlag;
<<<<<<< HEAD
    private Long departmentId;
    private List<Issue> issueList;
    private Department department;
    private Long userId;
=======
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5

    public UpdateProject() {
    }

<<<<<<< HEAD
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Issue> getIssueList() {
        return issueList;
    }

    public void setIssueList(List<Issue> issueList) {
        this.issueList = issueList;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
=======
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
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
