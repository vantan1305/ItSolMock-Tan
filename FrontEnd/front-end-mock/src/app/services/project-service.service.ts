import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constants } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class ProjectServiceService {

  constructor(
    private http:HttpClient
  ) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    'Access-Control-Allow-Origin': 'http://localhost:4200',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
  };

  public updateProject(formData: any): Observable<any>{
    return this.http.put(Constants.API_BASE_URL + '/project/update', formData);
  }

  public getAllProject():Observable<any>{
    return this.http.get(Constants.API_BASE_URL + '/project/all');
  }

  public getProjectById(id): Observable<any>{
    return this.http.get(Constants.API_BASE_URL + '/project/getId/' + id);
  }

  public searchProject(data:any): Observable<any>{
    const params = new HttpParams().set('name', data.seach);
    return this.http.get(Constants.API_BASE_URL + '/project/search', {observe: 'body', params});
  }

  public editProject(updateProject, id): Observable<any>{
    return this.http.put(Constants.API_BASE_URL + '/project/editProject/' + id, updateProject);
  }

  public deleteProject(id:any):Observable<any>{
    return this.http.post(Constants.API_BASE_URL + '/project/isdelete/' + id, this.httpOptions);
  }

  public addProjectToDepartment(data:any, id: number): Observable<any>{
    return this.http.post<any>(Constants.API_BASE_URL + '/project/addProjectToDepartment/' + id, data);
  }

  public getUser(): Observable<any>{
    return this.http.get(Constants.API_BASE_URL + '/project/allUser');
  }

}
