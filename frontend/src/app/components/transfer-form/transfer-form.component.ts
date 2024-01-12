import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TransferInput } from 'src/app/interfaces/transfer-input';
import { TransferSimulation } from 'src/app/interfaces/transfer-simulation';
import { TransferSimulationService } from 'src/app/services/transfer-simulation.service';
import { TransferService } from 'src/app/services/transfer.service';
import { getBrazilianCurrency } from 'src/app/utils/currency-utils';

@Component({
  selector: 'app-transfer-form',
  templateUrl: './transfer-form.component.html',
  styleUrls: ['./transfer-form.component.scss']
})
export class TransferFormComponent implements OnInit {
  @Output()
  newTransferEmitter = new EventEmitter<TransferInput>();

  simulatedTransfer: TransferSimulation = {};
  simulationErrorMessage = '';

  transferForm: FormGroup = new FormGroup({
    originAccount: new FormControl('', [Validators.minLength(6), Validators.maxLength(6), Validators.required]),
    destinationAccount: new FormControl('', [Validators.minLength(6), Validators.maxLength(6), Validators.required]),
    transferAmount: new FormControl(0, [Validators.min(1),Validators.max(999999999999), Validators.required]),
    scheduledDate: new FormControl('', [Validators.required])
  });

  constructor(
    private transferSimulationService: TransferSimulationService,
    private transferService: TransferService
    ) { }

  ngOnInit(): void {
  }
  simulateFees() {
    let amount: number = this.transferForm.get('transferAmount')?.value || 0;
    let date = this.transferForm.get('scheduledDate')?.value || null;
    if (amount != 0 && date != null)
      this.transferSimulationService.simulateTransfer(amount, date).subscribe({
        next: response => this.simulatedTransfer = response,
        error: message => this.simulationErrorMessage = message
      })
  }

  createTransfer() {
    let newTransfer = this.transferForm.value as TransferInput;
    this.newTransferEmitter.emit(newTransfer);
    this.transferForm.reset();
    this.simulatedTransfer = {};
  }
  moneyMask(value : number | undefined) {
    if (value != undefined) return getBrazilianCurrency(value)
    else return "R$ 0,00"
  }
}
