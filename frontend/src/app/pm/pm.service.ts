import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PmService {

  private pmUrl = 'http://localhost:8080/api/test/pm';

  constructor(
    private http: HttpClient
  ) { }

  get pmBoard(): Observable<string> {
    return this.http.get(this.pmUrl, { responseType: 'text' });
  }
}
