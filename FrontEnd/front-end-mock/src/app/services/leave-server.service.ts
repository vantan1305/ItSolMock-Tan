import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class LeaveServerService {

  constructor(
     private http: HttpClient
     ) { }

     senEmail(leave: any):Observable<any>{
      return this.http.post<any>(Constants.API_BASE_URL + '/leave/post' , leave);
     }
}
