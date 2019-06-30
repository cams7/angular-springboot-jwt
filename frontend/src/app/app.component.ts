import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { AuthService, AuthStatus } from './auth/auth.service';
import { RoleName } from './common/model/role';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  private statusSubscription: Subscription;

  private _isLoggedIn = false;
  private authorities: RoleName[];

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.statusSubscription = this.authService.authStatus.subscribe(status => {
      switch (status) {
        case AuthStatus.LOGGED_IN: {
          this._isLoggedIn = true;
          this.authorities = this.authService.loggedUser.roles.map(role => role.name);
          break;
        }
        case AuthStatus.LOGGED_OUT: {
          this._isLoggedIn = false;
          this.authorities = undefined;
          //this.router.navigate(['/auth/login']);
          window.location.reload();
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
  
  isAllowed(authority: string) {
    if(!this._isLoggedIn)
      return false;

    return this.authorities.some(role => role === RoleName[authority]);    
  }

  logout() {
    this.authService.logout();
  }
  
  get isLoggedIn() {
    return this._isLoggedIn;
  }
}
