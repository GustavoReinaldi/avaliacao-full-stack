import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransferFormComponent } from './transfer-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxMaskModule } from 'ngx-mask';



@NgModule({
  declarations: [
    TransferFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    NgxMaskModule.forRoot()
  ],
  exports : [TransferFormComponent]
})
export class TransferFormModule { }
