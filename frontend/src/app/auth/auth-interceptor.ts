import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable } from "rxjs";

import { AuthService } from './auth.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
class AuthInterceptor implements HttpInterceptor {
  
  constructor(
    private authService: AuthService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.token;
    if (token) 
      req = req.clone({ 
        headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) 
      }); 
    return next.handle(req);
  }
}

export const httpInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
