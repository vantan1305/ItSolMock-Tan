import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { Router } from '@angular/router';
import { Department } from 'src/app/model/Department';
import { updateUser } from 'src/app/model/updateUser';
import { DepartmentServiceService } from 'src/app/services/department-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { UserService } from 'src/app/services/user.service';
import { UserComponent } from 'src/app/shared/user/user.component';

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.css']
})
export class AddDepartmentComponent implements OnInit {
  department: Department = {} as Department;
  progressBar = true;
  users: any;
  userSelect:any;
  project:any;
  projectSelect
  constructor(

    private departmentService: DepartmentServiceService,
    private router: Router,
    private userSerVice: UserService,
    private projectService: ProjectServiceService
  ) { }

  ngOnInit() {
    this.userSerVice.getUserBoard().subscribe(
      data =>{
        this.users = data;
      });

    this.projectService.getAllProject().subscribe(
      data =>{
        this.project = data;
      });
  }

  addDeprtmant(){
    this.progressBar= true;
    const formData = new FormData();

    formData.append('code', this.department.code);
    formData.append('location', this.department.location);
    formData.append('description', this.department.description);
    console.log(formData);
    this.departmentService.addDepartment(formData).subscribe(
      data =>{
        this.department = data;
        console.log('Ok');
        alert('Ok');
        this.router.navigate(['home']);
      }, (error: any) => {
        alert('False');
        console.log(error);
      });
  }
}
