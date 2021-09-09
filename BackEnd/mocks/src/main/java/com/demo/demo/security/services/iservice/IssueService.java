package com.demo.demo.security.services.iservice;

import com.demo.demo.model.Issue;

public interface IssueService {

    Issue addIssueToProject(Issue issue, Long id);
}
