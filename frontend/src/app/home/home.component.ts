import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private _info: any;

  constructor(
    private authService: AuthService
  ) { }

  ngOnInit() {
    let loggedUser = this.authService.loggedUser;
    this._info = {
      token : this.authService.token, 
      username : loggedUser.username, 
      authorities : loggedUser.roles.map(role => role.name)
    };
  }

  get info() {
    return this._info;
  }

}
