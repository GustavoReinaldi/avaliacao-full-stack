import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TransferResponse } from 'src/app/interfaces/transfer-response';
import { TransferService } from 'src/app/services/transfer.service';
import { getBrazilianCurrency } from 'src/app/utils/currency-utils'
@Component({
  selector: 'app-list-transfers',
  templateUrl: './list-transfers.component.html',
  styleUrls: ['./list-transfers.component.scss']
})
export class ListTransfersComponent implements OnInit {
  @Output()
  onDoubleClick = new EventEmitter<string>();
  @Input()
  transferInputList : TransferResponse [] = [];
  constructor(private transferService : TransferService) { }

  ngOnInit(): void {
  }
  moneyMask(value : number) {
    return getBrazilianCurrency(value)
  }
  deleteRow(id : string){
    this.onDoubleClick.emit(id);
  }
}
