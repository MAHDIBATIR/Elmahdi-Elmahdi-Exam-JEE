import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Credit, CreditPersonnel, CreditImmobilier, CreditProfessionnel } from '../models/credit.model';

@Injectable({
  providedIn: 'root'
})
export class CreditService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  // Generic credit methods
  getAllCredits(): Observable<Credit[]> {
    return this.http.get<Credit[]>(`${this.baseUrl}/credits`);
  }

  getCreditsByClient(clientId: number): Observable<Credit[]> {
    return this.http.get<Credit[]>(`${this.baseUrl}/clients/${clientId}/credits`);
  }

  getCredit(id: number): Observable<Credit> {
    return this.http.get<Credit>(`${this.baseUrl}/credits/${id}`);
  }

  // Credit Immobilier methods
  createCreditImmobilier(credit: CreditImmobilier): Observable<CreditImmobilier> {
    return this.http.post<CreditImmobilier>(`${this.baseUrl}/credits/immobilier`, credit);
  }

  // Credit Personnel methods
  createCreditPersonnel(credit: CreditPersonnel): Observable<CreditPersonnel> {
    return this.http.post<CreditPersonnel>(`${this.baseUrl}/credits/personnel`, credit);
  }

  // Credit Professionnel methods
  createCreditProfessionnel(credit: CreditProfessionnel): Observable<CreditProfessionnel> {
    return this.http.post<CreditProfessionnel>(`${this.baseUrl}/credits/professionnel`, credit);
  }

  // Common methods for all credit types
  updateCredit(id: number, credit: Credit): Observable<Credit> {
    return this.http.put<Credit>(`${this.baseUrl}/credits/${id}`, credit);
  }

  deleteCredit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/credits/${id}`);
  }

  simulateCredit(montant: number, duree: number, tauxInteret: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/credits/simulation`, {
      params: { 
        montant: montant.toString(), 
        duree: duree.toString(), 
        tauxInteret: tauxInteret.toString() 
      }
    });
  }
} 