package com.demo.demo.security.services;

import com.demo.demo.model.Issue;
import com.demo.demo.model.Project;
import com.demo.demo.repository.IssueRepository;
import com.demo.demo.repository.ProjectRepository;
import com.demo.demo.security.services.iservice.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Issue addIssueToProject(Issue issue, Long id) {
        Project project = projectRepository.findById (id).get ();
        issue.setTimeStart (new Date ());
        project.addIssueToProject (issue);
        return issueRepository.save (issue);
    }
}
