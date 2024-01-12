import { Component } from '@angular/core';
import { TransferInput } from './interfaces/transfer-input';
import { TransferService } from './services/transfer.service';
import { TransferResponse } from './interfaces/transfer-response';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';
  transferList : TransferResponse [] = [];

  constructor (private transferService: TransferService) {
    this.listAllTransfers();
  }
  
  createNewTransfer(newTransfer : TransferInput) {
    this.transferService.createTransfer(newTransfer).subscribe({
      next: () => this.listAllTransfers(),
      error : message => console.log(message)
    })
  }

  listAllTransfers() {
    this.transferService.listAllTransactions().subscribe({
      next: response => this.transferList = response
    })
  }
  deleteSchedule(id : string) {
    this.transferService.deleteTransfer(id).subscribe({
      next: () => this.listAllTransfers()
    })
  }
}
