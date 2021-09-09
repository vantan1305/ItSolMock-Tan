import { Component, OnInit } from '@angular/core';
import { Leave } from 'src/app/model/Leave';
import { LeaveServerService } from 'src/app/services/leave-server.service';

@Component({
  selector: 'app-please-leave',
  templateUrl: './please-leave.component.html',
  styleUrls: ['./please-leave.component.css']
})
export class PleaseLeaveComponent implements OnInit {

  leave: Leave = {} as Leave;
  constructor(
    private leaveServe: LeaveServerService
  ) { }

  ngOnInit() {
  }

  onSubmit(){

    this.leaveServe.senEmail(this.leave).subscribe(
      data =>{
        this.leave = data;
        console.log(data);
        alert('Email Sent successfully');
        this.leave.email = '';
        this.leave.message = '';
        this.leave.name = '';
        this.leave.formDate = '';
        this.leave.toDate = '';
        this.leave.reason = '';
        this.leave.yourMail = '';
      });
  }

}
