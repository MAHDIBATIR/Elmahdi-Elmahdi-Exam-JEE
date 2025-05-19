import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ClientListComponent } from './components/client/client-list/client-list.component';
import { ClientFormComponent } from './components/client/client-form/client-form.component';
import { CreditListComponent } from './components/credit/credit-list/credit-list.component';
import { CreditFormComponent } from './components/credit/credit-form/credit-form.component';
import { RemboursementListComponent } from './components/remboursement/remboursement-list/remboursement-list.component';
import { RemboursementFormComponent } from './components/remboursement/remboursement-form/remboursement-form.component';

import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    ClientListComponent,
    ClientFormComponent,
    CreditListComponent,
    CreditFormComponent,
    RemboursementListComponent,
    RemboursementFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { } 