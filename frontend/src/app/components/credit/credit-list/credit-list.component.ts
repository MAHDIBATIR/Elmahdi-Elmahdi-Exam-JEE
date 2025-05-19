import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Credit } from '../../../models/credit.model';
import { CreditService } from '../../../services/credit.service';
import { ClientService } from '../../../services/client.service';

@Component({
  selector: 'app-credit-list',
  templateUrl: './credit-list.component.html',
  styleUrls: ['./credit-list.component.css']
})
export class CreditListComponent implements OnInit {
  credits: Credit[] = [];
  clientId?: number;
  clientName = '';
  loading = true;
  error: string | null = null;

  constructor(
    private creditService: CreditService,
    private clientService: ClientService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('clientId');
      if (id) {
        this.clientId = +id;
        this.loadClientCredits(this.clientId);
        this.loadClientName(this.clientId);
      } else {
        this.loadAllCredits();
      }
    });
  }

  loadAllCredits(): void {
    this.loading = true;
    this.creditService.getAllCredits().subscribe({
      next: (data) => {
        this.credits = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des crédits.';
        this.loading = false;
        console.error(err);
      }
    });
  }

  loadClientCredits(clientId: number): void {
    this.loading = true;
    this.creditService.getCreditsByClient(clientId).subscribe({
      next: (data) => {
        this.credits = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des crédits du client.';
        this.loading = false;
        console.error(err);
      }
    });
  }

  loadClientName(clientId: number): void {
    this.clientService.getClient(clientId).subscribe({
      next: (client) => {
        this.clientName = `${client.prenom} ${client.nom}`;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des détails du client', err);
      }
    });
  }

  getCreditType(credit: Credit): string {
    if ('montantAchat' in credit) {
      return 'Immobilier';
    } else if ('motif' in credit) {
      return 'Personnel';
    } else if ('nomProjet' in credit) {
      return 'Professionnel';
    }
    return 'Inconnu';
  }

  deleteCredit(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce crédit?')) {
      this.creditService.deleteCredit(id).subscribe({
        next: () => {
          this.credits = this.credits.filter(credit => credit.id !== id);
        },
        error: (err) => {
          this.error = 'Erreur lors de la suppression du crédit.';
          console.error(err);
        }
      });
    }
  }
} 