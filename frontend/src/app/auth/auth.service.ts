import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable, ReplaySubject } from 'rxjs';
import { tap } from 'rxjs/operators';

import { TokenStorageService } from './token-storage.service';
import { AuthLoginInfo } from './model/auth-login-info';
import { JwtResponse } from './model/jwt-response';
import { SignUpInfo } from './model/sign-up-info';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/api/auth/signin';
  private signupUrl = 'http://localhost:8080/api/auth/signup';

  private _authStatus: ReplaySubject<AuthStatus> = new ReplaySubject();

  constructor(
    private http: HttpClient,
    private tokenStorage: TokenStorageService
  ) { }

  login(credentials: AuthLoginInfo) {
    return this.attemptAuth(credentials).pipe(
      tap(data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);
        this._authStatus.next(AuthStatus.LOGGED_IN);
      })
    );
  }

  logout() {
    this.tokenStorage.signOut();
    this._authStatus.next(AuthStatus.LOGGED_OUT);
  }

  signUp(info: SignUpInfo): Observable<string> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }

  private attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  get authStatus(): Observable<AuthStatus> {
    return this._authStatus.asObservable();
  }
}

export enum AuthStatus {
  LOGGED_IN,
  LOGGED_OUT
}
