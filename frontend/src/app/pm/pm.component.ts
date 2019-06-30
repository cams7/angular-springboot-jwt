import { Component, OnInit } from '@angular/core';
import { PmService } from './pm.service';

@Component({
  selector: 'app-pm',
  templateUrl: './pm.component.html',
  styleUrls: ['./pm.component.css']
})
export class PmComponent implements OnInit {
  private _board: string;
  private _errorMessage: string;

  constructor(
    private pmService: PmService
  ) { }

  ngOnInit() {
    this.pmService.pmBoard.subscribe(
      data => {
        this._board = data;
      },
      error => {
        console.error('PmComponent.ngOnInit() => error: ', error);
        this._errorMessage = `${error.status}: ${this.getErrorMessage(error)}`;
      },
      () => {   
        console.log('PmComponent.ngOnInit() => completed');     
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
