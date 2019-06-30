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
