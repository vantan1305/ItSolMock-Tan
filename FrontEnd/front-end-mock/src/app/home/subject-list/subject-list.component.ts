import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})


export class SubjectListComponent implements OnInit {

  constructor(
    private http: HttpClient,
  ) {}

  ngOnInit() {}



}
