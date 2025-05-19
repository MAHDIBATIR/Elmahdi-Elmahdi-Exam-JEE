import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Remboursement } from '../../../models/remboursement.model';
import { RemboursementService } from '../../../services/remboursement.service';
import { CreditService } from '../../../services/credit.service';
import { Credit } from '../../../models/credit.model';

@Component({
  selector: 'app-remboursement-form',
  templateUrl: './remboursement-form.component.html',
  styleUrls: ['./remboursement-form.component.css']
})
export class RemboursementFormComponent implements OnInit {
  remboursementForm: FormGroup;
  isEditMode = false;
  loading = false;
  error = '';
  credits: Credit[] = [];

  constructor(
    private fb: FormBuilder,
    private remboursementService: RemboursementService,
    private creditService: CreditService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.remboursementForm = this.fb.group({
      creditId: ['', Validators.required],
      montant: ['', [Validators.required, Validators.min(0)]],
      dateEcheance: ['', Validators.required],
      regle: [false],
      datePaiement: ['']
    });
  }

  ngOnInit(): void {
    this.loadCredits();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.loadRemboursement(Number(id));
    }
  }

  loadCredits(): void {
    this.creditService.getAllCredits().subscribe({
      next: (credits: Credit[]) => {
        this.credits = credits;
      },
      error: (err: any) => {
        this.error = 'Erreur lors du chargement des crédits';
        console.error(err);
      }
    });
  }

  loadRemboursement(id: number): void {
    this.loading = true;
    this.remboursementService.getRemboursementById(id).subscribe({
      next: (remboursement: Remboursement) => {
        this.remboursementForm.patchValue({
          creditId: remboursement.creditId,
          montant: remboursement.montant,
          dateEcheance: remboursement.dateEcheance,
          regle: remboursement.regle,
          datePaiement: remboursement.datePaiement
        });
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Erreur lors du chargement du remboursement';
        this.loading = false;
        console.error(err);
      }
    });
  }

  onSubmit(): void {
    if (this.remboursementForm.valid) {
      this.loading = true;
      const remboursement: Remboursement = this.remboursementForm.value;

      const request = this.isEditMode
        ? this.remboursementService.updateRemboursement(remboursement)
        : this.remboursementService.createRemboursement(remboursement);

      request.subscribe({
        next: () => {
          this.router.navigate(['/remboursements']);
        },
        error: (err: any) => {
          this.error = `Erreur lors de ${this.isEditMode ? 'la modification' : 'la création'} du remboursement`;
          this.loading = false;
          console.error(err);
        }
      });
    }
  }
} 