import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  private _board: string;
  private _errorMessage: string;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit() {
    this.userService.userBoard.subscribe(
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
