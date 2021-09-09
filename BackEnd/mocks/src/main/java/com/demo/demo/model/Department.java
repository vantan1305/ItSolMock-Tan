package com.demo.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_auto")
    @SequenceGenerator(name = "department_auto", sequenceName = "department_auto", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, name = "code")
    private String code;

    private String location;

    @Column( name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "creat_At")
    private Date createAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Users> usersList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Project> projectsList;

    public Department(String code, String location, String description, Date createAt, List<Users> usersList, List<Project> projectsList) {
        this.code = code;
        this.location = location;
        this.description = description;
        this.createAt = createAt;
        this.usersList = usersList;
        this.projectsList = projectsList;
    }

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public List<Project> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
    }

    public void addUserToDepartment(Users users) {
        if(getUsersList () == null) {
            this.usersList = new ArrayList<> ();
        }
        getUsersList ().add(users);
        users.setDepartment(this);
    }

    public void addProjectToDepartment(Project project) {
        if(getProjectsList () == null) {
            this.projectsList = new ArrayList<>();
        }
        getProjectsList ().add(project);
        project.setDepartment(this);
    }
}
