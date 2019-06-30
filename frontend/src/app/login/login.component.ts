import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { AuthService, AuthStatus } from '../auth/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthLoginInfo } from '../auth/model/auth-login-info';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  form: any = {};
  private _isLoggedIn = false;
  private _isLoginFailed = false;
  private _roles: string[] = [];
  private _errorMessage = '';

  private loginInfo: AuthLoginInfo;

  private statusSubscription: Subscription;

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit() {
    this.statusSubscription = this.authService.authStatus.subscribe(status => {
      switch (status) {
        case AuthStatus.LOGGED_IN: {
          this.loggedIn();
          break;
        }
        case AuthStatus.LOGGED_OUT: {
          this.loggedIn(false);
          break;
        }
        default:
          break;
      }
    });
  }

  ngOnDestroy() {
    this.statusSubscription.unsubscribe();
  }

  onSubmit() {
    this.loginInfo = <AuthLoginInfo>{
      username: this.form.username,
      password: this.form.password
    };

    this.authService.login(this.loginInfo).subscribe(
      data => {
        this._isLoginFailed = false;
        //this.reloadPage();
      },
      error => {
        console.error(error);
        this._errorMessage = error.error.message;
        this._isLoginFailed = true;
      },
      () => {   
      }
    );
  }

  private loggedIn(isLoggedIn = true) {
    this._isLoggedIn = isLoggedIn;
    this._roles = isLoggedIn ? this.tokenStorage.authorities : undefined;
  }

  private reloadPage() {
    window.location.reload();
  }

  get isLoggedIn() {
    return this._isLoggedIn;
  }

  get isLoginFailed() {
    return this._isLoginFailed;
  }

  get roles() {
    return this._roles;
  }

  get errorMessage() {
    return this._errorMessage;
  }

}
