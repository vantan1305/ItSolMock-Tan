package com.demo.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "project_user")
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_user_auto")
    @SequenceGenerator(name = "project_user_auto", sequenceName = "project_user_auto", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "projectId", referencedColumnName = "id", nullable = false)
    private Project project;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private Users users;

    public ProjectUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
