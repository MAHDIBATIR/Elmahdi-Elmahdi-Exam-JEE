import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RemboursementService } from '../../../services/remboursement.service';
import { CreditService } from '../../../services/credit.service';
import { Remboursement } from '../../../models/remboursement.model';
import { Credit } from '../../../models/credit.model';

@Component({
  selector: 'app-remboursement-list',
  templateUrl: './remboursement-list.component.html',
  styleUrls: ['./remboursement-list.component.css']
})
export class RemboursementListComponent implements OnInit {
  remboursements: Remboursement[] = [];
  creditId!: number;
  credit?: Credit;
  loading = true;
  error: string | null = null;
  paymentProcessing = false;

  constructor(
    private remboursementService: RemboursementService,
    private creditService: CreditService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('creditId');
      if (id) {
        this.creditId = +id;
        this.loadRemboursements();
        this.loadCreditDetails();
      } else {
        this.router.navigate(['/credits']);
      }
    });
  }

  loadRemboursements(): void {
    this.loading = true;
    this.remboursementService.getAllRemboursements().subscribe({
      next: (data: Remboursement[]) => {
        this.remboursements = data.filter(r => r.creditId === this.creditId);
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Erreur lors du chargement des remboursements';
        this.loading = false;
        console.error(err);
      }
    });
  }

  loadCreditDetails(): void {
    this.creditService.getCredit(this.creditId).subscribe({
      next: (data: Credit) => {
        this.credit = data;
      },
      error: (err: any) => {
        console.error('Erreur lors du chargement des détails du crédit', err);
      }
    });
  }

  getCreditType(): string {
    if (!this.credit) return '';
    if ('montantAchat' in this.credit) {
      return 'Immobilier';
    } else if ('motif' in this.credit) {
      return 'Personnel';
    } else if ('nomProjet' in this.credit) {
      return 'Professionnel';
    }
    return 'Inconnu';
  }

  markAsPaid(remboursement: Remboursement): void {
    if (remboursement.regle || !remboursement.id) return;

    if (confirm('Êtes-vous sûr de vouloir marquer cette échéance comme payée?')) {
      this.paymentProcessing = true;
      const today = new Date();
      
      this.remboursementService.markAsPaid(remboursement.id, today).subscribe({
        next: (updatedRemboursement: Remboursement) => {
          const index = this.remboursements.findIndex(r => r.id === updatedRemboursement.id);
          if (index !== -1) {
            this.remboursements[index] = updatedRemboursement;
          }
          this.paymentProcessing = false;
        },
        error: (err: any) => {
          this.error = 'Erreur lors de l\'enregistrement du paiement.';
          this.paymentProcessing = false;
          console.error(err);
        }
      });
    }
  }

  isOverdue(remboursement: Remboursement): boolean {
    if (remboursement.regle) return false;
    const today = new Date();
    const echeance = new Date(remboursement.dateEcheance);
    return echeance < today;
  }

  getPaidCount(): number {
    return this.remboursements.filter(r => r.regle).length;
  }

  getTotalAmount(): number {
    return this.remboursements.reduce((sum, r) => sum + r.montant, 0);
  }

  getPaidAmount(): number {
    return this.remboursements
      .filter(r => r.regle)
      .reduce((sum, r) => sum + r.montant, 0);
  }

  getRemainingAmount(): number {
    return this.getTotalAmount() - this.getPaidAmount();
  }

  getProgressPercentage(): number {
    if (this.getTotalAmount() === 0) return 0;
    return (this.getPaidAmount() / this.getTotalAmount()) * 100;
  }

  deleteRemboursement(id: number | undefined): void {
    if (!id) return;
    
    if (confirm('Êtes-vous sûr de vouloir supprimer ce remboursement ?')) {
      this.remboursementService.deleteRemboursement(id).subscribe({
        next: () => {
          this.remboursements = this.remboursements.filter(r => r.id !== id);
        },
        error: (err: any) => {
          this.error = 'Erreur lors de la suppression du remboursement';
          console.error(err);
        }
      });
    }
  }
} 