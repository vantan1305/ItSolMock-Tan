import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectServiceService } from 'src/app/services/project-service.service';

@Component({
  selector: 'app-delete-project',
  templateUrl: './delete-project.component.html',
  styleUrls: ['./delete-project.component.css']
})
export class DeleteProjectComponent implements OnInit {

  @Input()
  deleteId: number;
  @Input()
  deleteName: string;

  @Output()
  deleteComplete = new EventEmitter<boolean>();

  constructor(
    private projectService: ProjectServiceService,
    private router: Router,
  ) { }

  ngOnInit() {
  }

  deletePatient(){
    this.projectService.deleteProject(this.deleteId).subscribe(
      data =>{
        document.getElementById('closeModal').click();
        this.deleteComplete.emit(true);
      });
      alert('ok');
  }

}
