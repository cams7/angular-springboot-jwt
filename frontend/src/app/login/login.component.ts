import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';

import { AuthService, AuthStatus } from '../auth/auth.service';
import { AuthLoginInfo } from '../auth/model/auth-login-info';
import { RoleName } from '../common/model/role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  form: any = {};
  private _isLoggedIn = false;
  private _isLoginFailed = false;
  private _roles: RoleName[];
  private _errorMessage = '';

  private loginInfo: AuthLoginInfo;

  private statusSubscription: Subscription;

  constructor(
    private authService: AuthService
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
    this._roles = isLoggedIn ? this.authService.loggedUser.roles.map(role => role.name) : undefined;
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
