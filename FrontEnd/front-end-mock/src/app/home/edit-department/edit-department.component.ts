import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ActionSequence } from 'protractor';
import { Department } from 'src/app/model/Department';
import { DepartmentServiceService } from 'src/app/services/department-service.service';

@Component({
  selector: 'app-edit-department',
  templateUrl: './edit-department.component.html',
  styleUrls: ['./edit-department.component.css']
})
export class EditDepartmentComponent implements OnInit {

  formGroup: FormGroup;
  department: Department;
  id;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private departmentService: DepartmentServiceService
  ) {
    this.department = new Department();
   }

  ngOnInit() {
    this.departmentService.findDepartment(this.route.snapshot.paramMap.get('id')).subscribe(data => {
      console.log(data);
      this.department = data;
      console.log(this.route.snapshot.paramMap.get('id'));
    }, error => console.log(error));
  }

  public editDepartment(): void{
    const formData = new FormData();
    formData.append('code', this.department.code);
    formData.append('description',  this.department.description);
    console.log(formData);
    if(this.id !== 1){
      this.id = this.route.snapshot.paramMap.get('id');
      formData.append('id', `${this.id}`);
    }
    this.departmentService.editDepartments(formData).subscribe(
      data =>{
        this.department = data;
        alert('ok');
        this.router.navigateByUrl('listDepartment');
      }, (error: any) => {
        alert('Thất bại');
        console.log(error);
      });
  }

}
