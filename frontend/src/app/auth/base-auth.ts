import { Injectable, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService, AuthStatus } from './auth.service';
import { RoleName } from '../common/model/role';

@Injectable()
export abstract class BaseAuth implements OnInit, OnDestroy {

    private statusSubscription: Subscription;

    private _authorities: RoleName[];
    private _isLoggedIn = false;

    constructor(
        private _authService: AuthService
    ) { }

    ngOnInit() {
        this.statusSubscription = this._authService.authStatus.subscribe(status => {
            switch (status) {
                case AuthStatus.LOGGED_IN: {
                    this.setLoggedInAndAuthorities();
                    break;
                }
                case AuthStatus.LOGGED_OUT: {
                    this.setLoggedInAndAuthorities(false);
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

    private setLoggedInAndAuthorities(isLoggedIn = true) {
        this._isLoggedIn = isLoggedIn;
        this._authorities = isLoggedIn ? this._authService.loggedUser.roles.map(role => role.name) : undefined;
    }

    get authService() {
        return this._authService;
    }

    get authorities() {
        return this._authorities;
    }

    get isLoggedIn() {
        return this._isLoggedIn;
    }
}
