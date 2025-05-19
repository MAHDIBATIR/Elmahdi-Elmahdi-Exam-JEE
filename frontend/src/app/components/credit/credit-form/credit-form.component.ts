import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../../../services/client.service';
import { CreditService } from '../../../services/credit.service';
import { Client } from '../../../models/client.model';

@Component({
  selector: 'app-credit-form',
  templateUrl: './credit-form.component.html',
  styleUrls: ['./credit-form.component.css']
})
export class CreditFormComponent implements OnInit {
  creditForm!: FormGroup;
  immobilierForm!: FormGroup;
  personnelForm!: FormGroup;
  professionnelForm!: FormGroup;
  
  clients: Client[] = [];
  selectedCreditType = '';
  loading = false;
  submitting = false;
  error: string | null = null;
  clientId?: number;
  
  creditTypes = [
    { id: 'immobilier', label: 'Crédit Immobilier' },
    { id: 'personnel', label: 'Crédit Personnel' },
    { id: 'professionnel', label: 'Crédit Professionnel' }
  ];

  constructor(
    private formBuilder: FormBuilder,
    private clientService: ClientService,
    private creditService: CreditService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initForms();
    this.loadClients();
    
    this.route.paramMap.subscribe(params => {
      const id = params.get('clientId');
      if (id) {
        this.clientId = +id;
        this.creditForm.patchValue({ clientId: this.clientId });
      }
    });
  }

  initForms(): void {
    // Base credit form
    this.creditForm = this.formBuilder.group({
      montant: ['', [Validators.required, Validators.min(1000)]],
      duree: ['', [Validators.required, Validators.min(1)]],
      tauxInteret: ['', [Validators.required, Validators.min(0), Validators.max(100)]],
      date: [this.formatDate(new Date()), Validators.required],
      clientId: ['', Validators.required]
    });

    // Immobilier specific form
    this.immobilierForm = this.formBuilder.group({
      montantAchat: ['', [Validators.required, Validators.min(1000)]],
      adresseBien: ['', Validators.required],
      superficieBien: ['', [Validators.required, Validators.min(1)]]
    });

    // Personnel specific form
    this.personnelForm = this.formBuilder.group({
      motif: ['', Validators.required],
      revenuMensuel: ['', [Validators.required, Validators.min(100)]]
    });

    // Professionnel specific form
    this.professionnelForm = this.formBuilder.group({
      chiffreAffaire: ['', [Validators.required, Validators.min(0)]],
      secteurActivite: ['', Validators.required],
      nomProjet: ['', Validators.required]
    });
  }

  loadClients(): void {
    this.loading = true;
    this.clientService.getClients().subscribe({
      next: (data) => {
        this.clients = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des clients.';
        this.loading = false;
        console.error(err);
      }
    });
  }

  onSubmit(): void {
    if (this.creditForm.invalid || !this.selectedCreditType || 
       (this.selectedCreditType === 'immobilier' && this.immobilierForm.invalid) ||
       (this.selectedCreditType === 'personnel' && this.personnelForm.invalid) ||
       (this.selectedCreditType === 'professionnel' && this.professionnelForm.invalid)) {
      return;
    }

    this.submitting = true;
    const baseCredit = {
      ...this.creditForm.value,
      etat: 'EN_ATTENTE',
      date: new Date(this.creditForm.value.date)
    };

    switch (this.selectedCreditType) {
      case 'immobilier':
        const immobilierCredit = {
          ...baseCredit,
          ...this.immobilierForm.value
        };
        this.creditService.createCreditImmobilier(immobilierCredit).subscribe({
          next: this.handleSuccess.bind(this),
          error: this.handleError.bind(this)
        });
        break;
      
      case 'personnel':
        const personnelCredit = {
          ...baseCredit,
          ...this.personnelForm.value
        };
        this.creditService.createCreditPersonnel(personnelCredit).subscribe({
          next: this.handleSuccess.bind(this),
          error: this.handleError.bind(this)
        });
        break;
      
      case 'professionnel':
        const professionnelCredit = {
          ...baseCredit,
          ...this.professionnelForm.value
        };
        this.creditService.createCreditProfessionnel(professionnelCredit).subscribe({
          next: this.handleSuccess.bind(this),
          error: this.handleError.bind(this)
        });
        break;
    }
  }

  private handleSuccess(result: any): void {
    this.submitting = false;
    if (this.clientId) {
      this.router.navigate(['/clients', this.clientId, 'credits']);
    } else {
      this.router.navigate(['/credits']);
    }
  }

  private handleError(err: any): void {
    this.submitting = false;
    this.error = 'Erreur lors de la création du crédit.';
    console.error(err);
  }

  private formatDate(date: Date): string {
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
  }
} 