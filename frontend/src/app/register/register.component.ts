import { Component, OnInit, OnDestroy } from '@angular/core';
import { SignUpInfo } from '../auth/model/sign-up-info';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {
  
  form: any = {};

  private _signupInfo: SignUpInfo;
  private _isSignedUp = false;
  private _isSignUpFailed = false;
  private _errorMessage = '';

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit() {
  }

  ngOnDestroy() {
  }

  onSubmit() {
    this._signupInfo = <SignUpInfo>{
      name: this.form.name, 
      username: this.form.username, 
      email: this.form.email, 
      password: this.form.password, 
      role: ['user']
    };

    this.authService.signUp(this._signupInfo).subscribe(
      data => {
        this._isSignedUp = true;
        this._isSignUpFailed = false;
      },
      error => {
        console.error(error);
        this._errorMessage = error.error.message;
        this._isSignUpFailed = true;
      },
      () => {        
      }
    );
  }

  get signupInfo() {
    return this._signupInfo;
  }

  get isSignedUp() {
    return this._isSignedUp;
  }

  get isSignUpFailed() {
    return this._isSignUpFailed;
  }

  get errorMessage() {
    return this._errorMessage;
  }
}
