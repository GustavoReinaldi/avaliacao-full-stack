import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListTransfersComponent } from './list-transfers.component';
import { NgxMaskModule } from 'ngx-mask';

@NgModule({
  declarations: [
    ListTransfersComponent
  ],
  imports: [
    CommonModule,
    NgxMaskModule.forRoot()
  ],
  exports: [ListTransfersComponent]
})
export class ListTransfersModule { }
