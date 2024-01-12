import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TransferResponse } from '../interfaces/transfer-response';
import { TransferInput } from '../interfaces/transfer-input';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  constructor(private httpClient : HttpClient) { }
  
  listAllTransactions() : Observable<TransferResponse[]> {
    return this.httpClient.get('api/v1/transfer') as Observable<TransferResponse[]>;
  }
  createTransfer(newTransfer : TransferInput) : Observable<any> {
    return this.httpClient.post('api/v1/transfer', newTransfer);
  }
  deleteTransfer(transferId : string) : Observable<any> {
    return this.httpClient.delete(`api/v1/transfer/${transferId}`);
  }
}
