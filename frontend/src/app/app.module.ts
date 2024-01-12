import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { TransferFormModule } from './components/transfer-form/transfer-form.module';
import { HttpClientModule } from '@angular/common/http';
import { ListTransfersModule } from './components/list-transfers/list-transfers.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    TransferFormModule,
    ListTransfersModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
