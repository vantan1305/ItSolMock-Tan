import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { DepartmentServiceService } from 'src/app/services/department-service.service';

@Component({
  selector: 'app-delete-department',
  templateUrl: './delete-department.component.html',
  styleUrls: ['./delete-department.component.css']
})
export class DeleteDepartmentComponent implements OnInit {
  @Input()
  deleteId: number;
  @Input()
  deleteName: string;
  @Output()
  deleteComplete = new EventEmitter<boolean>();

  constructor(
    private departmentService: DepartmentServiceService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  deletePatient(){
    this.departmentService.deleteDepartment(this.deleteId).subscribe(
      data =>{
        document.getElementById('closeModal').click();
        this.deleteComplete.emit(true);
      });
      alert('ok');
  }

}
