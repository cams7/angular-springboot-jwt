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
        console.error('UserComponent.ngOnInit() => error: ', error);
        this._errorMessage = `${error.status}: ${this.getErrorMessage(error)}`;
      },
      () => {   
        console.log('UserComponent.ngOnInit() => completed');     
      }
    );
  }

  private getErrorMessage(error: any) {
    return error.error.message ? error.error.message : JSON.parse(error.error).message ? JSON.parse(error.error).message : error.message;
  }

  get board() {
    return this._board;
  }

  get errorMessage() {
    return this._errorMessage;
  }

}
