import { Route } from '@angular/compiler/src/core';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Routes } from '@angular/router';
import { ActionSequence } from 'protractor';
import { Projects, UpdateProject } from 'src/app/model/UpdateProject';
import { DepartmentServiceService } from 'src/app/services/department-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';

@Component({
  selector: 'app-add-project-to-department',
  templateUrl: './add-project-to-department.component.html',
  styleUrls: ['./add-project-to-department.component.css']
})
export class AddProjectToDepartmentComponent implements OnInit {
  project: UpdateProject = {} as UpdateProject;
  progressBar = false;
  process: any = {};
  id;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private projectService: ProjectServiceService,
    private departmentService: DepartmentServiceService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    if(this.data.idProject!=null) {
      this.projectService.getProjectById(this.data.idProject).subscribe(project => {
        this.project = project
      })
  }
  }

  setProcess(e) {
    this.process = e;
  }

  addProjectToDepartment(){
    this.progressBar = true;
    this.project.process = this.process;
    if(this.data.idProject!=null) {
      this.projectService.editProject(this.project, this.data.idProject).subscribe(project => {
        this.project = project;
        window.location.reload();
      })
    } else {
      this.projectService.addProjectToDepartment(this.project, this.data.idDepartment).subscribe(project => {
        this.project = project;
       window.location.reload();
      })
    }
  }

}
