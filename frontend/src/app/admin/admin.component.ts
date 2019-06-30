import { Component, OnInit } from '@angular/core';
import { AdminService } from './admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  private _board: string;
  private _errorMessage: string;

  constructor(
    private adminService: AdminService
  ) { }

  ngOnInit() {
    this.adminService.adminBoard.subscribe(
      data => {
        this._board = data;
      },
      error => {
        console.error('AdminComponent.ngOnInit() => error: ', error);
        this._errorMessage = `${error.status}: ${this.getErrorMessage(error)}`;
      },
      () => {   
        console.log('AdminComponent.ngOnInit() => completed');     
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
