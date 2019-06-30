import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../common/model/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' })
};
@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private signupUrl = 'http://localhost:8080/api/auth/signup';

  constructor(
    private http: HttpClient
  ) { }

  signUp(user: User): Observable<User> {
    return this.http.post<User>(this.signupUrl, user, httpOptions);
  }
}
