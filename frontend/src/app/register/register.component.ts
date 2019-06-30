import { Component, OnInit, OnDestroy } from '@angular/core';
import { RegisterService } from './register.service';
import { User } from '../common/model/user';
import { Role, RoleName } from '../common/model/role';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {
  
  form: any = {};

  private _isSignedUp = false;
  private _isSignUpFailed = false;
  private _errorMessage = '';

  constructor(
    private registerService: RegisterService
  ) { }

  ngOnInit() {
  }

  ngOnDestroy() {
  }

  onSubmit() {
    let user = <User>{
      name: this.form.name, 
      username: this.form.username, 
      email: this.form.email, 
      password: this.form.password, 
      roles: [<Role>{name: RoleName.ROLE_USER}]
    };

    this.registerService.signUp(user).subscribe(
      data => {
        console.log('RegisterComponent.onSubmit() => data: ', data);
        this._isSignedUp = true;
        this._isSignUpFailed = false;
      },
      error => {
        console.error('RegisterComponent.onSubmit() => error: ', error);
        this._errorMessage = this.getErrorMessage(error);
        this._isSignUpFailed = true;
      },
      () => {   
        console.log('RegisterComponent.onSubmit() => completed');     
      }
    );
  }

  private getErrorMessage(error: any) {
    return error.error.message ? error.error.message : JSON.parse(error.error).message ? JSON.parse(error.error).message : error.message;
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
