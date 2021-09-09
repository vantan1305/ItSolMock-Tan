import { Component, OnInit } from '@angular/core';
import { Department } from 'src/app/model/Department';
import { DepartmentServiceService } from 'src/app/services/department-service.service';
import * as _ from 'lodash';
import * as moment from 'moment';
import { Router } from '@angular/router';
import { SeachForm } from 'src/app/model/seachForm';

@Component({
  selector: 'app-list-department',
  templateUrl: './list-department.component.html',
  styleUrls: ['./list-department.component.css']
})
export class ListDepartmentComponent implements OnInit {
  department: Department = {} as Department;
  p = 1;
  seachFrom: SeachForm = new SeachForm();
  deleteId: number;
  deleteName: string;
  constructor(
    private departmentService: DepartmentServiceService,
    private router: Router
  ) { }

  ngOnInit() {
    this.departmentService.getAll().subscribe(
      data =>{
        this.department = data;
      }, error => console.log(error));
  }

  public search(){
    this.departmentService.search(this.seachFrom).subscribe(
      data =>{
        this.department =  data;
        if(data = ""){
          alert('Not Found Department');
        }
      },error =>{
        console.log(error);
        alert('Department Not Found');
        }
      );
  }

  addDepartment(){
    this.router.navigate(['/addDepartment']);
  }

  deleteSuccess(){
    this.ngOnInit();
  }

  public sortCode(dir:any){
    if(dir === 'up' ){
      this.department = _.orderBy(this.department,['name'],['asc']);
    }
    else{
      this.department = _.orderBy(this.department,['name'],['desc']);
    }
  }

}
