import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';
@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut() {
    this.removeToken();
  }

  saveToken(token: string) {
    this.removeToken();
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  private removeToken() {
    window.sessionStorage.removeItem(TOKEN_KEY);
  }

  get token() {
    return sessionStorage.getItem(TOKEN_KEY);
  }
}
