import { Component } from '@angular/core';
import { AuthLoginInfoVO } from '../common/model/vo/auth-login-info-vo';
import { BaseAuth } from '../auth/base-auth';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent extends BaseAuth {

  form: any = {};

  private _isLoginFailed = false;
  private _errorMessage = '';

  onSubmit() {
    let loginInfo = <AuthLoginInfoVO>{
      username: this.form.username,
      password: this.form.password
    };

    this.authService.login(loginInfo).subscribe(
      data => {
        console.log('LoginComponent.onSubmit() => data: ', data);
        this._isLoginFailed = false;
        //this.reloadPage();
      },
      error => {
        console.error('LoginComponent.onSubmit() => error: ', error);
        this._errorMessage = this.getErrorMessage(error);
        this._isLoginFailed = true;
      },
      () => {
        console.log('LoginComponent.onSubmit() => completed'); 
      }
    );
  }

  private getErrorMessage(error: any) {
    return error.error.message ? error.error.message : JSON.parse(error.error).message ? JSON.parse(error.error).message : error.message;
  }

  private reloadPage() {
    window.location.reload();
  }

  get isLoginFailed() {
    return this._isLoginFailed;
  }

  get errorMessage() {
    return this._errorMessage;
  }

}
