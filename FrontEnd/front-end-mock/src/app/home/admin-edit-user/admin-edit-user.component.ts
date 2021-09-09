import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UpdateProject } from 'src/app/model/UpdateProject';
import { updateUser } from 'src/app/model/updateUser';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-edit-user',
  templateUrl: './admin-edit-user.component.html',
  styleUrls: ['./admin-edit-user.component.css']
})
export class AdminEditUserComponent implements OnInit {

  formGroup: FormGroup;
  users: updateUser = {} as updateUser;
  id;

  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private formBuilder: FormBuilder,
  ) {
    this.users = new updateUser();
   }

  ngOnInit() {
    this.userService.getUser(this.id).subscribe(data => {
      console.log(data);
      this.users = data;
      console.log(this.route.snapshot.paramMap.get('id'));

  }, error => console.log(error));
  }

  updateProfile(){
    const formData = new FormData();

    formData.append('email', this.users.email);
    formData.append('unit', this.users.unit);
    formData.append('staffType', this.users.staffType);

    if(this.id !== 1){
      this.id = this.route.snapshot.paramMap.get('id');
      formData.append('id', `${this.id}`);
    }
    this.userService.updateUserProfile(formData).subscribe(
      data =>{
        alert('ok');
        this.router.navigateByUrl('managerUser');
      }, (error: any) => {
        alert('Thất bại');
        console.log(error);
      });
  }

}
