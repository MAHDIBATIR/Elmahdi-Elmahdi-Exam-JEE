import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientListComponent } from './components/client/client-list/client-list.component';
import { ClientFormComponent } from './components/client/client-form/client-form.component';
import { CreditListComponent } from './components/credit/credit-list/credit-list.component';
import { CreditFormComponent } from './components/credit/credit-form/credit-form.component';
import { RemboursementListComponent } from './components/remboursement/remboursement-list/remboursement-list.component';
import { RemboursementFormComponent } from './components/remboursement/remboursement-form/remboursement-form.component';

const routes: Routes = [
  { path: '', redirectTo: '/clients', pathMatch: 'full' },
  
  // Client routes
  { path: 'clients', component: ClientListComponent },
  { path: 'clients/new', component: ClientFormComponent },
  { path: 'clients/:id/edit', component: ClientFormComponent },
  
  // Credit routes
  { path: 'credits', component: CreditListComponent },
  { path: 'credits/new', component: CreditFormComponent },
  { path: 'credits/:id/edit', component: CreditFormComponent },
  
  // Remboursement routes
  { path: 'remboursements', component: RemboursementListComponent },
  { path: 'remboursements/new', component: RemboursementFormComponent },
  { path: 'remboursements/:id/edit', component: RemboursementFormComponent },
  
  // Client-Credit routes
  { path: 'clients/:clientId/credits', component: CreditListComponent },
  { path: 'clients/:clientId/credits/nouveau', component: CreditFormComponent },
  
  // Catch all route
  { path: '**', redirectTo: '/clients' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { } 