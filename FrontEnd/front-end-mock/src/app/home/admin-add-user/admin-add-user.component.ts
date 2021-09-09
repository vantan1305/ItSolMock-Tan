import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { updateUser } from 'src/app/model/updateUser';
import { OauthLoginService } from 'src/app/services/oauth-login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-admin-add-user',
  templateUrl: './admin-add-user.component.html',
  styleUrls: ['./admin-add-user.component.css']
})
export class AdminAddUserComponent implements OnInit {

  users: updateUser = {} as updateUser;

  constructor(
    private http: HttpClient,
    private router: Router,
    private routerA: ActivatedRoute,
    private userService: UserService,
    private oauthService: OauthLoginService,
    private fb: FormBuilder,
  ) { }

  ngOnInit() {
  }

  addUser(){
    const formData = new FormData();

    formData.append('password', this.users.password);
    formData.append('userName', this.users.userName);
    formData.append('avatar', this.users.avatar);
    formData.append('email', this.users.email);
    formData.append('specialized', this.users.specialized);
    formData.append('phone', this.users.phone);
    formData.append('staffType', this.users.staffType);
    formData.append('identityCard', this.users.identityCard);
    formData.append('homeTown', this.users.homeTown);
    formData.append('education', this.users.education);
    formData.append('school', this.users.school);
    formData.append('dob', this.users.dob);
    formData.append('firstName', this.users.firstName);
    formData.append('lastName', this.users.lastName);
    formData.append('middleName', this.users.middleName);
    formData.append('sex', this.users.sex);
    formData.append('unit', this.users.unit);
    console.log(formData);

    this.userService.updateUserProfile(formData).subscribe(
      data =>{
        this.users = data;
        console.log('ok', data);
        alert('ok');
        this.router.navigate(['managerUser']);
      }, (error: any) => {
        alert('Thất bại');
        console.log(error);
      });

  }

  selectFile($event){
    this.users.avatar = $event.target.files[0].name;
  }

}
