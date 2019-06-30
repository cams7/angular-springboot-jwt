import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost:8080/api/test/user';
  private pmUrl = 'http://localhost:8080/api/test/pm';
  private adminUrl = 'http://localhost:8080/api/test/admin';

  constructor(
    private http: HttpClient
  ) { }

  get userBoard(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }

  get pmBoard(): Observable<string> {
    return this.http.get(this.pmUrl, { responseType: 'text' });
  }

  get adminBoard(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }
}
