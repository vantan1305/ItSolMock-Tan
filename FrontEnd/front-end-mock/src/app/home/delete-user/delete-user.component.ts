import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-delete-user',
  templateUrl: './delete-user.component.html',
  styleUrls: ['./delete-user.component.css']
})
export class DeleteUserComponent implements OnInit {
  @Input()
  deleteId: number;
  @Input()
  deleteUserName: string;

  @Output()
  deleteComplete = new EventEmitter<boolean>();

  constructor(
    private UserService: UserService
  ) { }

  ngOnInit() {
  }

  deletePatient(){
    this.UserService.deleteUser(this.deleteId).subscribe(
      data =>{
        document.getElementById('closeModal').click();
        this.deleteComplete.emit(true);
      });
      alert('ok');
  }

}
