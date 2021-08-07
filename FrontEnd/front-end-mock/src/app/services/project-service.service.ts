import { HttpClient, HttpParams } from '@angular/common/http';
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

  public updateProject(formData: any): Observable<any>{
    return this.http.put(Constants.API_BASE_URL + '/project/update', formData);
  }

  public getAllProject():Observable<any>{
    return this.http.get(Constants.API_BASE_URL + '/project/all');
  }

  public getProjectById(id: number): Observable<any>{
    return this.http.get(Constants.API_BASE_URL + '/project/getId/' + `${id}`);
  }

  public searchProject(data:any): Observable<any>{
    const params = new HttpParams().set('name', data.seach);
    return this.http.get(Constants.API_BASE_URL + '/project/search', {observe: 'body', params});
  }
}
