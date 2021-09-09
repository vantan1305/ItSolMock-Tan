import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SeachForm } from 'src/app/model/seachForm';
import { OauthLoginService } from 'src/app/services/oauth-login.service';
import { UserService } from 'src/app/services/user.service';
import * as _ from 'lodash';
import * as moment from 'moment';
import { SeachEmail } from 'src/app/model/searchEmail';
import { SeachSpecialized } from 'src/app/model/searchSpecialized';

@Component({
  selector: 'app-manager-user',
  templateUrl: './manager-user.component.html',
  styleUrls: ['./manager-user.component.css']
})
export class ManagerUserComponent implements OnInit {
  userid:any;
  id:any;
  dob:any;
  role :any;
  invalidLogin = false;
  onParentLoaded: boolean = false;
  profileUser1:any[] =[];
  p = 1;
  seachFrom: SeachForm = new SeachForm();
  searchEmail: SeachEmail = new SeachEmail();
  searchSpecialized: SeachSpecialized = new SeachSpecialized();
  deleteId: number;
  deleteUserName: string;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private oauthService: OauthLoginService,
    private router: Router
  ) { }

  ngOnInit() {
    this.userService.getUserBoard().subscribe(
      data =>{
        // console.log(data)
        if(this.invalidLogin){
          const user = this.oauthService.getUsers();
          this.role = user.roles;
        }
        this.profileUser1 = data;
      },error =>console.error());
  }

  public searchUserName(): void{
    this.userService.searchDataUser(this.seachFrom).subscribe(
    data =>{
      this.profileUser1 =  data;
      if(data = ""){
        alert('Not Found User');
      }
    },error =>{
      console.log(error);
      alert('User Not Found');
      }
    );
  }

  searchEnail(){
    this.userService.searchByEmail(this.searchEmail).subscribe(
      data =>{
        this.profileUser1 =  data;
        if(data = ""){
          alert('Not Found Email');
        }
      },error =>{
        console.log(error);
        alert('Email Not Found');
        }
      );
  }

  searchBySpecialized(){
    this.userService.searchBySpecialized(this.searchSpecialized).subscribe(
      data =>{
        this.profileUser1 =  data;
        if(data = ""){
          alert('Not Found Specialized');
        }
      },error =>{
        console.log(error);
        alert('Specialized Not Found');
        }
      );
  }

  public sortCode(dir: any){
    if(dir === 'up' ){
      this.profileUser1 = _.orderBy(this.profileUser1,['userName'],['asc']);
    }
    else{
      this.profileUser1 = _.orderBy(this.profileUser1,['userName'],['desc']);
    }
  }

  deleteSuccess(){
    this.ngOnInit();
  }

  editData(){}

  addUser(){
    this.router.navigate(['/adminAddUser']);
  }

}
