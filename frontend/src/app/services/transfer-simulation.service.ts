import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransferSimulation } from '../interfaces/transfer-simulation';

@Injectable({
  providedIn: 'root'
})
export class TransferSimulationService {

  constructor(private httpClient : HttpClient) { }
  
  public simulateTransfer (amount : number, date: string) : Observable<TransferSimulation> {
    return this.httpClient.get("api/v1/simulate-taxes", {params: {"schedule-date": date, "transfer-amount" : amount}});
  }
}
