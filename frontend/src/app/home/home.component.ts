import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private _info: any;

  constructor(
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit() {
    this._info = {
      token : this.tokenStorage.token, 
      username : this.tokenStorage.username, 
      authorities : this.tokenStorage.authorities
    };
  }

  get info() {
    return this._info;
  }

}
