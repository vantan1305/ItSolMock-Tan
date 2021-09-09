import { HttpClient, HttpErrorResponse, HttpEvent, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Constants } from '../constants';
import { updateUser } from '../model/updateUser';
import { UserDTO } from '../model/userDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }
  loggedInStatus = false
  setLoggedIn(value:boolean){
    this.loggedInStatus = value
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    'Access-Control-Allow-Origin': 'http://localhost:4200',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
  };

  private httpOptions1 = {
    headers : new HttpHeaders({
      'Content-Type': 'application/json',
      // 'Authorization': 'may-auth-token'
    }),
  };
  private httpOptions2 = {
    headers : new HttpHeaders({
      'Content-Type': 'multipart/form-data',
      // 'Authorization': 'may-auth-token'
    }),
  };

  // private url= 'http://localhost:8888/api/role/user/getId';
  // private Url='http://localhost:8888/api/role/user/update';

  public getUserBoard():Observable<any>{
    return this.http.get(Constants.API_BASE_URL + '/api/role/user/all',
     {observe: 'body'})
     .pipe(catchError((err) => throwError(err)));;
  }

  getUserIdAndShift(empid: string,shift: string) {
    console.log("VENAM PLz"+empid);
    console.log("from employee account service"+empid);
    return this.http.get<updateUser>(Constants.API_BASE_URL + '/api/role/user/findByUserIdShift?id='+empid+'&shift='+shift);
  }

  public getUser(id: number): Observable<any>{
    return this.http.get(`http://localhost:8888/api/role/user/getId/` + id);

  }

  public updateUserProfile(formData: any): Observable<any>{
    return this.http.put(Constants.API_BASE_URL + '/api/role/user/updateUser1', formData);
  }

  public searchDataUser(data:any): Observable<any>{
    const params = new HttpParams().set('userName', data.seach);
    return this.http.get(Constants.API_BASE_URL + '/api/role/user/search', {observe: 'body', params});
  }

  public searchByEmail(data:any): Observable<any>{
    const params = new HttpParams().set('email', data.seach1);
    return this.http.get(Constants.API_BASE_URL + '/api/role/user/searchByEmail', {observe: 'body', params});
  }

  public searchBySpecialized(data:any): Observable<any>{
    const params = new HttpParams().set('specialized', data.seach2);
    return this.http.get(Constants.API_BASE_URL + '/api/role/user/searchBySpecialized', {observe: 'body', params});
  }

  public deleteUser(id:any):Observable<any>{
    return this.http.post(Constants.API_BASE_URL + '/api/role/user/deleteUser/' + id, this.httpOptions);
  }



//   public uploadImage(file):Observable<any> {
//     const formData = new FormData();

//     formData.append("file", file, file.name);

//     return this.http.post('http://localhost:8888/image/uploadAvatar' , formData)
// }

private handleError(error: HttpErrorResponse) {
  if (error.error instanceof ErrorEvent) {
    // A client-side or network error occurred. Handle it accordingly.
    console.error('An error occurred:', error.error.message);
  } else {
    // The backend returned an unsuccessful response code.
    // The response body may contain clues as to what went wrong,
    console.error(
      `Backend returned code ${error.status}, ` + `body was: ${error.error}`
    );
  }
  // return an observable with a user-facing error message
  return throwError('Something bad happened; please try again later.');
}

}
