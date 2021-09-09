import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SeachForm } from 'src/app/model/seachForm';
import { UpdateProject } from 'src/app/model/UpdateProject';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import * as _ from 'lodash';
import * as moment from 'moment';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  updateProject: UpdateProject;
  id;
  seachFrom: SeachForm = new SeachForm();
  p = 1;
  deleteId: number;
  deleteName: string;

  constructor(
    private http: HttpClient,
    private projectService: ProjectServiceService,
    private router: Router
  ) {this.updateProject = new UpdateProject();}

  ngOnInit() {
    this.projectService.getAllProject()
      .subscribe(data => {
        console.log(data)
        this.updateProject = data;
      }, error => console.log(error));
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

  deleteSuccess(){
    this.ngOnInit();
  }

  addProject(){
    this.router.navigate(['/managerProject']);
  }

  public sortCode(dir:any){
    if(dir === 'up' ){
      this.updateProject = _.orderBy(this.updateProject,['name'],['asc']);
    }
    else{
      this.updateProject = _.orderBy(this.updateProject,['name'],['desc']);
    }
  }

}
