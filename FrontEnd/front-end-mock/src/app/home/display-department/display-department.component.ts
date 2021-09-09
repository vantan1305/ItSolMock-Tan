import { FocusMonitor } from '@angular/cdk/a11y';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, provideRoutes, Router } from '@angular/router';
import { Department } from 'src/app/model/Department';
import { DepartmentServiceService } from 'src/app/services/department-service.service';
import { ProjectServiceService } from 'src/app/services/project-service.service';
import { UserService } from 'src/app/services/user.service';
import { AddProjectToDepartmentComponent } from '../add-project-to-department/add-project-to-department.component';

@Component({
  selector: 'app-display-department',
  templateUrl: './display-department.component.html',
  styleUrls: ['./display-department.component.css']
})
export class DisplayDepartmentComponent implements OnInit {
  department: Department;
  id;
  userSelect;
  users: any;

  constructor(
    private route: ActivatedRoute,
    private departmentService: DepartmentServiceService,
    private userService: UserService,
    private projectService: ProjectServiceService,
    private router: Router
  ) {
    this.department = new Department();
   }

   loadUser():void{
     this.userService.getUserBoard().subscribe(
       data =>{
         this.users = data;
       }
     )
   }

  ngOnInit():void {
    this.loadUser();
    this.departmentService.findDepartment(this.route.snapshot.paramMap.get('id')).subscribe(
      data =>{
        console.log(data);
        this.department = data;
      })
  }

  addProject() {}

  addUser(id){
    const formData = {
      departmentId: id,
      userId: this.userSelect
    }
    // const formData = new FormData();
    // formData.append('user', this.userSelect);
    // if(this.id !== 1){
    //   this.id = this.route.snapshot.paramMap.get('id');
    //   formData.append('id', `${this.id}`);
    // }
    this.departmentService.addDepartment(formData).subscribe(
      data =>{
        this.department = data;
        alert('ok');
        this.router.navigateByUrl('listDepartment');
      }
    )
  }

  editDepartment(){}

  deleteDepartment(){}

}
