import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SeachForm } from 'src/app/model/seachForm';
import { OauthLoginService } from 'src/app/services/oauth-login.service';
import { UserService } from 'src/app/services/user.service';
import * as _ from 'lodash';
import * as moment from 'moment';
import { updateUser } from 'src/app/model/updateUser';
import { SeachEmail } from 'src/app/model/searchEmail';
import { SeachSpecialized } from 'src/app/model/searchSpecialized';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  userid:any;
  id:any;
  dob:any;
  private roles : string[];
  invalidLogin = false;
  onParentLoaded: boolean = false;
  showAdminBoard =false;
  showManagerBoard = false;
  profileUser1:any[] =[];
  p = 1;
  seachFrom: SeachForm = new SeachForm();
  searchEmail: SeachEmail = new SeachEmail();
  searchSpecialized: SeachSpecialized = new SeachSpecialized();
  user: updateUser = {} as updateUser;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private oauthService: OauthLoginService
    ) {
      // this.id = this.oauthService.getUsers().id;
  }

  ngOnInit() {
    this.userService.getUserBoard()
      .subscribe((data) => {
<<<<<<< HEAD
        console.log(data)
=======
        console.log(data)// format dob ở đây

        // this.dob = new Date().toLocaleString();
        // this.dob = new Date();
        // this.dd = this.dob.getDate();
        // this.mm = this.dob.getMonth()+1;
        // this.yyyy = this.dob.getFullYear();
        // if(this.dd<10)
        //   {
        //    this.dd='0'+ this.dd;
        // }
        // if(this.mm<10)
        //   {
        //     this.mm='0'+ this.mm;
        // }
        // this.dob = this.dd+'/'+this.mm+'/'+this.yyyy;
>>>>>>> de475c2b74b9a33b9fa23fd4eac9c1511f8091c5
        if(this.invalidLogin){
          const user = this.oauthService.getUsers();
          this.roles = user.roles;
        }
        this.profileUser1 = data;
      }, error => console.log(error));
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

  deletaData(){
  }

  editData(){}
}
