import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileDataService } from 'src/app/services/data/profile-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  userid:any;
  // user: Users = new Users();
  onParentLoaded: boolean = false;
  constructor(private userService: ProfileDataService,
    private route: ActivatedRoute,) {

    route.paramMap.subscribe(param => this.userid = param.get('id'));
  }

  ngOnInit() {
    // this.userService.getUserInfo().subscribe(response => {
    //     this.user = response;
    //     console.log(response);
    //     this.onParentLoaded = true;
    //   },
    //   error => { console.log(error) }
    // )
  }
}
