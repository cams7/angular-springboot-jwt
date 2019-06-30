import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { TokenStorageService } from './auth/token-storage.service';
import { AuthService, AuthStatus } from './auth/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

  private statusSubscription: Subscription;

  private _isLoggedIn = false;
  private authorities: string[];

  constructor(
    private router: Router,
    private tokenStorage: TokenStorageService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.statusSubscription = this.authService.authStatus.subscribe(status => {
      switch (status) {
        case AuthStatus.LOGGED_IN: {
          this._isLoggedIn = true;
          this.authorities = this.tokenStorage.authorities;
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

    return this.authorities.some(role => role === authority);    
  }

  logout() {
    this.authService.logout();
  }
  
  get isLoggedIn() {
    return this._isLoggedIn;
  }
}
