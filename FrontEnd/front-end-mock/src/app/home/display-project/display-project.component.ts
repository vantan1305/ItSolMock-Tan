import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { UpdateProject } from 'src/app/model/UpdateProject';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { AddProjectToDepartmentComponent } from '../add-project-to-department/add-project-to-department.component';

@Component({
  selector: 'app-display-project',
  templateUrl: './display-project.component.html',
  styleUrls: ['./display-project.component.css']
})
export class DisplayProjectComponent implements OnInit {
  project: UpdateProject = {} as UpdateProject;
  id: number;

  constructor(
    private projectService: ProjectServiceService,
    private route: ActivatedRoute,
    private dialog: MatDialog
  ) {
    this.route.params.subscribe(
      params => {
        this.id = this.route.snapshot.params.id;
        this.projectService.getProjectById(this.id).subscribe(project => {
          this.project = project;
        })
      }
    )
   }

  ngOnInit() {
  }

  deleteProject(id) {
    if(confirm('Are you sure')){
      this.projectService.deleteProject(id).subscribe(() => {
       window.location.replace(`/dashbaord`);
      });
    }
   }
   editProject(idProject) {
     this.dialog.open(AddProjectToDepartmentComponent, {
       data: {idProject},
       height: '480px',
       width: '400px',
       })
   }

}
