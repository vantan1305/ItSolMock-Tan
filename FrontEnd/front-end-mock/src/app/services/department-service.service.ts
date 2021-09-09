import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class DepartmentServiceService {

  constructor(
    private http: HttpClient
  ) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    'Access-Control-Allow-Origin': 'http://localhost:4200',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
  };

  addDepartment(formData: any): Observable<any> {
    return this.http.post<any>(Constants.API_BASE_URL + '/department/addDepartment', formData);
  }

  editDepartments(formData:any): Observable<any>{
    return this.http.put<any>(Constants.API_BASE_URL + '/department/editDepartment' , formData);
  }

  findDepartment(id: any): Observable<any> {
    return this.http.get<any>(Constants.API_BASE_URL + '/department/findDepartment/' + id);
  }

  deleteDepartment(id: number): Observable<any> {
    return this.http.delete<any>(Constants.API_BASE_URL + '/department/deleteDepartment/' + id, this.httpOptions);
  }

  getAll(): Observable<any>{
    return this.http.get(Constants.API_BASE_URL + '/department/all');
  }

  public search(data: any): Observable<any>{
    const params = new HttpParams().set('code', data.seach);
    return this.http.get(Constants.API_BASE_URL + '/department/search', {observe: 'body', params});
  }
}
