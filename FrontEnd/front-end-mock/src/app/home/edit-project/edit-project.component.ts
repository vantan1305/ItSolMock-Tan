import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UpdateProject } from 'src/app/model/UpdateProject';
import { ProjectServiceService } from 'src/app/services/project-service.service';


@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.css']
})
export class EditProjectComponent implements OnInit {
  formGroup: FormGroup;
  updateproject: UpdateProject;
  id;

  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private projectService: ProjectServiceService,
    private formBuilder: FormBuilder,
  ) {
    this.updateproject = new UpdateProject();
   }

  ngOnInit():void {

    this.projectService.getProjectById(this.route.snapshot.paramMap.get('id')).subscribe(data => {
      console.log(data);
      this.updateproject = data;
      console.log(this.route.snapshot.paramMap.get('id'));

  }, error => console.log(error));
}

 public editProject():void {
   debugger
  const formData = new FormData();
  formData.append('unit', this.updateproject.unit);
  formData.append('name', this.updateproject.name);
  formData.append('description', this.updateproject.description);
  formData.append('status', this.updateproject.status);
  formData.append('timeEnd', this.updateproject.timeEnd);
  formData.append('timeStart', this.updateproject.timeStart);
  if(this.id !== 1){
    this.id = this.route.snapshot.paramMap.get('id');
    formData.append('id', `${this.id}`);
  }
  this.projectService.updateProject(formData).subscribe(data => {
    this.updateproject = data;
    alert('ok');
    this.router.navigateByUrl('managerProject');
  }, (error: any) => {
    alert('Thất bại');
    console.log(error);
  });

}


  validation_messages = {
    name: [
      {type: 'required', message: 'Vui lòng nhập tên'},
      {type: 'maxlength', message: 'Vui lòng nhập tên không quá 40 kí tự.'},
      {type: 'minlength', message: 'Vui lòng nhập tên có ít nhất 6 kí tự'},
      {type: 'pattern', message: 'Vui lòng nhập tên đúng'}
    ],
    timeStart: [
      {type: 'required', message: 'Vui lòng nhập ngày bắt đầu dự án'},
    ],
    timeEnd: [
      {type: 'required', message: 'Vui lòng nhập ngày kết thúc dự án'},
    ],
    description: [
      {type: 'maxlength', message: 'Vui lòng nhập chi tiết dự án'}
    ],
    status: [
      {type: 'maxlength', message: 'Vui lòng nhập trạng thái của dự án'}
    ],
  };

}
