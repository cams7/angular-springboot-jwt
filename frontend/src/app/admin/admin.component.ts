import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  private _board: string;
  private _errorMessage: string;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit() {
    this.userService.adminBoard.subscribe(
      data => {
        this._board = data;
      },
      error => {
        this._errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
    );
  }

  get board() {
    return this._board;
  }

  get errorMessage() {
    return this._errorMessage;
  }

}
