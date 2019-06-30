import { Component } from '@angular/core';

import { RoleName } from './common/model/role';
import { BaseAuth } from './auth/base-auth';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent extends BaseAuth {

  isAllowed(authority: string) {
    if (!this.isLoggedIn)
      return false;

    return this.authorities.some(role => role === RoleName[authority]);
  }

  logout() {
    this.authService.logout();
  }
}
