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
<<<<<<< HEAD
  //  debugger
=======
   debugger
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
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
<<<<<<< HEAD
    this.router.navigateByUrl('managerProject/list');
=======
    this.router.navigateByUrl('managerProject');
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
  }, (error: any) => {
    alert('Th????t ba??i');
    console.log(error);
  });

}


  validation_messages = {
    name: [
      {type: 'required', message: 'Vui l??ng nh???p t??n'},
      {type: 'maxlength', message: 'Vui l??ng nh???p t??n kh??ng qu?? 40 k?? t???.'},
      {type: 'minlength', message: 'Vui l??ng nh???p t??n c?? ??t nh???t 6 k?? t???'},
      {type: 'pattern', message: 'Vui l??ng nh???p t??n ????ng'}
    ],
    timeStart: [
      {type: 'required', message: 'Vui l??ng nh???p ng??y b???t ?????u d??? ??n'},
    ],
    timeEnd: [
      {type: 'required', message: 'Vui l??ng nh???p ng??y k???t th??c d??? ??n'},
    ],
    description: [
      {type: 'maxlength', message: 'Vui l??ng nh???p chi ti???t d??? ??n'}
    ],
    status: [
      {type: 'maxlength', message: 'Vui l??ng nh???p tr???ng th??i c???a d??? ??n'}
    ],
  };

}
