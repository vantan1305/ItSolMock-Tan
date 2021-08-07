import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SeachForm } from 'src/app/model/seachForm';
import { UpdateProject } from 'src/app/model/UpdateProject';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import * as _ from 'lodash';
import * as moment from 'moment';

@Component({
  selector: 'app-admin-project',
  templateUrl: './admin-project.component.html',
  styleUrls: ['./admin-project.component.css']
})
export class AdminProjectComponent implements OnInit {
  updateProject: UpdateProject;
  id;
  seachFrom: SeachForm = new SeachForm();
  p = 1;

  constructor(
    private http: HttpClient,
    private projectService: ProjectServiceService,
    private router: Router
  ) {
    this.updateProject = new UpdateProject();
  }

  ngOnInit() {
    console.log(this.id)

    this.projectService.getAllProject()
      .subscribe(data => {
        console.log(data)
        this.updateProject = data;
      }, error => console.log(error));


  }

  public updateNewProject(){
    const formData = new FormData;

    formData.append('name', this.updateProject.name);
    formData.append('description', this.updateProject.description);
    formData.append('status',  this.updateProject.status);
    formData.append('timeStart', this.updateProject.timeStart);
    formData.append('timeEnd', this.updateProject.timeEnd);

    this.projectService.updateProject(formData).subscribe(
      data =>{
        console.log(data);
        alert('ok');
        this.updateProject = data;
        this.router.navigate(['managerProject']);
      }, (error: any) => {
        alert('Thất bại');
        console.log(error);
      }
    );
  }

  public search(): void{
    this.projectService.searchProject(this.seachFrom).subscribe(
    data =>{
      this.updateProject =  data;
      if(data = ""){
        alert('Not Found Project');
      }
    },error =>{
      console.log(error);
      alert('Project Not Found');
      }
    );
  }

  public EditProject(){

  }

  public DeleteProject(){}

  public sortCode(dir:any){
    if(dir === 'up' ){
      this.updateProject = _.orderBy(this.updateProject,['name'],['asc']);
    }
    else{
      this.updateProject = _.orderBy(this.updateProject,['name'],['desc']);
    }
  }

}
