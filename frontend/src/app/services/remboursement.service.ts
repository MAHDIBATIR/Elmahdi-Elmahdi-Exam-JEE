import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Remboursement } from '../models/remboursement.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RemboursementService {
  private apiUrl = `${environment.apiUrl}/remboursements`;

  constructor(private http: HttpClient) { }

  getAllRemboursements(): Observable<Remboursement[]> {
    return this.http.get<Remboursement[]>(this.apiUrl);
  }

  getRemboursementById(id: number): Observable<Remboursement> {
    return this.http.get<Remboursement>(`${this.apiUrl}/${id}`);
  }

  createRemboursement(remboursement: Remboursement): Observable<Remboursement> {
    return this.http.post<Remboursement>(this.apiUrl, remboursement);
  }

  updateRemboursement(remboursement: Remboursement): Observable<Remboursement> {
    return this.http.put<Remboursement>(`${this.apiUrl}/${remboursement.id}`, remboursement);
  }

  deleteRemboursement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  markAsPaid(id: number, datePaiement: Date): Observable<Remboursement> {
    return this.http.patch<Remboursement>(`${this.apiUrl}/${id}/mark-as-paid`, { datePaiement });
  }
} 